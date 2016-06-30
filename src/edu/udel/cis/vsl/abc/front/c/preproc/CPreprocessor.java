package edu.udel.cis.vsl.abc.front.c.preproc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.front.IF.Preprocessor;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorParser.file_return;
import edu.udel.cis.vsl.abc.token.IF.CivlcTokenSource;
import edu.udel.cis.vsl.abc.token.IF.FileIndexer;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.IF.Macro;
import edu.udel.cis.vsl.abc.token.IF.SourceFile;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;
import edu.udel.cis.vsl.abc.token.IF.Tokens;
import edu.udel.cis.vsl.abc.util.IF.ANTLRUtils;

/**
 * A standard C preprocessor.
 * 
 * @author Stephen F. Siegel, University of Delaware
 */
public class CPreprocessor implements Preprocessor {

	// Static members...

	// public final static String SHORT_FILE_NAME_PREFIX = "f";

	/**
	 * Print debugging information.
	 */
	public final static boolean debug = false;

	// Instance fields...

	/**
	 * The configuration governs a number of choices which influence
	 * preprocessing, e.g., shall gnuc.h be included automatically (yes if the
	 * configuration says so).
	 * 
	 */
	private Configuration config;

	/**
	 * The language in which the source files that will be encountered by this
	 * preprocessor are written. A preprocessor can be applied to one and only
	 * one language. For now there is not much difference between CIVL-C and C.
	 * If the language is CIVL-C, then the file civlc.cvh will be automatically
	 * included at the beginning of the translation unit.
	 */
	private Language language;

	/**
	 * The file indexing object which is used to number and track all source
	 * files encountered by this preprocessor, giving each file a unique ID
	 * number. The indexer may be shared with other ABC component, so this
	 * preprocessor is not necessarily the only component which will be adding
	 * files to the indexer.
	 */
	private FileIndexer indexer;

	/**
	 * Factory to be used to create new tokens and related objects.
	 */
	private TokenFactory tokenFactory;

	// Constructors...

	public CPreprocessor(Configuration config, Language language,
			FileIndexer indexer, TokenFactory tokenFactory) {
		this.config = config;
		this.language = language;
		this.indexer = indexer;
		this.tokenFactory = tokenFactory;
	}

	// Helpers...

	/**
	 * Finds the internal library resource and new stream to given stream vector
	 * and adds new formation to given formation vector.
	 * 
	 * @param libraryFilename
	 *            name of library file, e.g., "civlc.cvh" or "gnuc.h"
	 * @param streamVector
	 *            list of streams in which to add new entry
	 * @param formationVector
	 *            list of formations in which to add new entry
	 * @throws PreprocessorException
	 *             if the resource cannot be opened for some reason
	 */
	private void addLibrary(String libraryFilename,
			ArrayList<CharStream> streamVector,
			ArrayList<Formation> formationVector) throws PreprocessorException {
		File file = new File(Preprocessor.ABC_INCLUDE_PATH, libraryFilename);
		SourceFile sourceFile = indexer.getOrAdd(file);
		Formation formation = tokenFactory.newInclusion(sourceFile);
		String resource = file.getAbsolutePath();

		try {
			CharStream stream = PreprocessorUtils
					.newFilteredCharStreamFromResource(libraryFilename,
							resource);

			streamVector.add(stream);
			formationVector.add(formation);
		} catch (IOException e) {
			throw new PreprocessorException(
					"Error in opening civlc.cvh: " + e.getMessage());
		}
	}

	private void addMacros(Map<String, String> predefinedMacros,
			ArrayList<CharStream> streamVector,
			ArrayList<Formation> formationVector) throws PreprocessorException {
		if (!predefinedMacros.isEmpty()) {
			CharStream macroStream = PreprocessorUtils
					.macroMapToCharStream(predefinedMacros);
			File file = new File("predefined macros");
			SourceFile sourceFile = indexer.getOrAdd(file);
			Formation formation = tokenFactory.newInclusion(sourceFile);

			streamVector.add(macroStream);
			formationVector.add(formation);
		}
	}

	/**
	 * Adds streams for macros, svcomp.h, gnuc.h, and civlc.cvh, as needed, to
	 * the stream and formation vectors.
	 * 
	 * @param predefinedMacros
	 * @param streamVector
	 * @param formationVector
	 * @throws PreprocessorException
	 */
	private void addAuxStreams(Map<String, String> predefinedMacros,
			ArrayList<CharStream> streamVector,
			ArrayList<Formation> formationVector) throws PreprocessorException {
		addMacros(predefinedMacros, streamVector, formationVector);
		// note that svcomp.h #includes gnuc.h so no need to include both:
		if (config.getSVCOMP())
			addLibrary("svcomp.h", streamVector, formationVector);
		else if (config.getGNUC())
			addLibrary("gnuc.h", streamVector, formationVector);
		if (language == Language.CIVL_C)
			addLibrary("civlc.cvh", streamVector, formationVector);
	}

	/**
	 * Creates character streams and formations from the files and adds them to
	 * the given stream and formation vectors.
	 * 
	 * @param sourceFiles
	 * @param streamVector
	 * @param formationVector
	 * @throws PreprocessorException
	 */
	private void addFiles(File[] sourceFiles,
			ArrayList<CharStream> streamVector,
			ArrayList<Formation> formationVector) throws PreprocessorException {
		int numFiles = sourceFiles.length;

		for (int i = 0; i < numFiles; i++) {
			File file = sourceFiles[i];
			SourceFile sourceFile = indexer.getOrAdd(file);

			try {
				streamVector.add(
						PreprocessorUtils.newFilteredCharStreamFromFile(file));
			} catch (IOException e) {
				throw new PreprocessorException(
						"Error in opening " + file + ": " + e.getMessage());
			}
			formationVector.add(tokenFactory.newInclusion(sourceFile));
		}
	}

	/**
	 * Produces new {@link PreprocessorTokenSource} object from the given
	 * character streams.
	 * 
	 * @param systemIncludePaths
	 * @param userIncludePaths
	 * @param streamVector
	 * @param formationVector
	 * @return
	 * @throws PreprocessorException
	 */
	private PreprocessorTokenSource newTokenSource(File[] systemIncludePaths,
			File[] userIncludePaths, ArrayList<CharStream> streamVector,
			ArrayList<Formation> formationVector) throws PreprocessorException {
		CharStream[] streams = streamVector
				.toArray(new CharStream[streamVector.size()]);
		Formation[] formations = formationVector
				.toArray(new Formation[formationVector.size()]);
		Map<String, Macro> macroMap = new HashMap<>();
		PreprocessorTokenSource result = new PreprocessorTokenSource(config,
				indexer, streams, formations, systemIncludePaths,
				userIncludePaths, macroMap, tokenFactory);

		return result;
	}

	// Public methods...

	/**
	 * Returns a lexer for the given preprocessor source file. The lexer removes
	 * all occurrences of backslash-newline, scans and tokenizes the input to
	 * produce a sequence of tokens in the preprocessor grammar. It does not
	 * execute the preprocessor directives.
	 * 
	 * @param file
	 *            a preprocessor source file
	 * @return a lexer for the given file
	 * @throws IOException
	 *             if an I/O error occurs while reading the file
	 */
	public PreprocessorLexer lexer(File file) throws PreprocessorException {
		try {
			CharStream charStream = PreprocessorUtils
					.newFilteredCharStreamFromFile(file);

			return new PreprocessorLexer(charStream);
		} catch (IOException e) {
			throw new PreprocessorException(
					"I/O error occurred while scanning " + file + ":\n" + e);
		}
	}

	/**
	 * Prints the results of lexical analysis of the source file. Mainly useful
	 * for debugging.
	 * 
	 * @param out
	 *            a PrintStream to which the output should be sent
	 * @param file
	 *            a preprocessor source file
	 * @throws PreprocessorException
	 *             if any kind of exception comes from ANTLR's lexer, including
	 *             a file which does not conform lexically to the preprocessor
	 *             grammar
	 */
	public void lex(PrintStream out, File file) throws PreprocessorException {
		out.println("Lexical analysis of " + file + ":");
		try {
			PreprocessorLexer lexer = lexer(file);
			int numErrors;

			PreprocessorUtils.printTokenSource(out, lexer);
			numErrors = lexer.getNumberOfSyntaxErrors();

			if (numErrors != 0)
				throw new PreprocessorException(numErrors
						+ " syntax errors occurred while scanning " + file);
		} catch (RuntimeException e) {
			throw new PreprocessorException(e.getMessage());
		}
	}

	/**
	 * Returns a parser for the given preprocessor source file.
	 * 
	 * @param file
	 *            a preprocessor source file
	 * @return a parser for that file
	 * @throws PreprocessorException
	 *             if an I/O error occurs in attempting to open the file
	 */
	public PreprocessorParser parser(File file) throws PreprocessorException {
		PreprocessorLexer lexer = lexer(file);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		if (config != null && config.getSVCOMP()) {
			int numTokens = tokenStream.getNumberOfOnChannelTokens();
			// the second last token is the token before EOF
			Token secondLastToken = tokenStream.get(numTokens - 2);

			if (secondLastToken.getType() != PreprocessorParser.NEWLINE) {
				Token newLineToken = new CommonToken(
						secondLastToken.getInputStream(),
						PreprocessorParser.NEWLINE,
						secondLastToken.getChannel(), -1, -1);

				newLineToken.setText("\n");
				((List<Token>) tokenStream.getTokens()).add(numTokens - 1,
						newLineToken);
			}
		}
		return new PreprocessorParser(tokenStream);
	}

	/**
	 * Scans and parses the given preprocessor source file, sending a textual
	 * description of the resulting tree to out. This does not execute any
	 * preprocessor directives. It is useful mainly for debugging.
	 * 
	 * @param out
	 *            print stream to which the tree representation of the file will
	 *            be sent
	 * @param file
	 *            a preprocessor source file.
	 * @throws PreprocessorException
	 *             if the file does not conform to the preprocessor grammar, or
	 *             an I/O error occurs in reading the file
	 */
	public void parse(PrintStream out, File file) throws PreprocessorException {
		try {
			PreprocessorParser parser = parser(file);
			file_return fileReturn = parser.file();
			int numErrors = parser.getNumberOfSyntaxErrors();
			Tree tree;

			if (numErrors != 0)
				throw new PreprocessorException(numErrors
						+ " syntax errors occurred while scanning " + file);
			out.println("AST for " + file + ":");
			out.flush();
			tree = (Tree) fileReturn.getTree();
			ANTLRUtils.printTree(out, tree);
		} catch (RecognitionException e) {
			throw new PreprocessorException(
					"Recognition error while preprocessing:\n" + e);
		} catch (RuntimeException e) {
			e.printStackTrace(System.err);
			throw new PreprocessorException(e.toString());
		}
	}

	// Methods specified in Preprocessor interface...

	@Override
	public PreprocessorTokenSource outputTokenSource(File[] systemIncludePaths,
			File[] userIncludePaths, Map<String, String> predefinedMacros,
			File[] sourceUnit) throws PreprocessorException {
		ArrayList<CharStream> streamVector = new ArrayList<>();
		ArrayList<Formation> formationVector = new ArrayList<>();

		addAuxStreams(predefinedMacros, streamVector, formationVector);
		addFiles(sourceUnit, streamVector, formationVector);
		return newTokenSource(systemIncludePaths, userIncludePaths,
				streamVector, formationVector);
	}

	@Override
	public CivlcTokenSource preprocessLibrary(
			Map<String, String> predefinedMacros, String libraryFileName)
					throws PreprocessorException {
		ArrayList<CharStream> streamVector = new ArrayList<>();
		ArrayList<Formation> formationVector = new ArrayList<>();

		addAuxStreams(predefinedMacros, streamVector, formationVector);
		addLibrary(libraryFileName, streamVector, formationVector);
		return newTokenSource(Preprocessor.defaultSystemIncludes,
				Preprocessor.defaultUserIncludes, streamVector,
				formationVector);
	}

	@Override
	public FileIndexer getFileIndexer() {
		return indexer;
	}

	// The main method...

	/**
	 * This main method is just here for simple tests. The real main method is
	 * in the main class, ABC.java.
	 */
	public final static void main(String[] args) throws PreprocessorException {
		String filename = args[0];
		Configuration config = Configurations.newMinimalConfiguration();
		TokenFactory tokenFactory = Tokens.newTokenFactory();
		FileIndexer indexer = tokenFactory.newFileIndexer();
		Language language;

		if (filename.endsWith(".c") || filename.endsWith(".h"))
			language = Language.C;
		else if (filename.endsWith(".F") || filename.endsWith(".F77")
				|| filename.endsWith(".f77") || filename.endsWith(".f")
				|| filename.endsWith(".f90") || filename.endsWith(".F90"))
			language = Language.FORTRAN77;
		else
			language = Language.CIVL_C;

		CPreprocessor p = new CPreprocessor(config, language, indexer,
				tokenFactory);
		File file = new File(filename);
		Map<String, String> predefinedMacros = new HashMap<>();

		CivlcTokenSource ts = p.outputTokenSource(
				Preprocessor.defaultSystemIncludes,
				Preprocessor.defaultUserIncludes, predefinedMacros,
				new File[] { file });
		PrintStream out = System.out;

		ANTLRUtils.print(out, ts);
	}

	// /**
	// * Prints the list of tokens that result from preprocessing the file. One
	// * token is printed per line, along with information on the origin of that
	// * token. Useful mainly for debugging.
	// *
	// * @param out
	// * where to send output list
	// * @param file
	// * a preprocessor source file
	// * @throws PreprocessorException
	// * if the file fails to adhere to the preprocessor grammar, or
	// * an I/O occurs
	// */
	// @Override
	// public void printOutputTokens(File[] systemIncludePaths,
	// File[] userIncludePaths, Map<String, String> implicitMacros,
	// PrintStream out, File[] sourceUnit) throws PreprocessorException {
	// CPreprocessorWorker worker = new CPreprocessorWorker(config, this,
	// systemIncludePaths, userIncludePaths, implicitMacros);
	// PreprocessorTokenSource source = worker.outputTokenSource(sourceUnit,
	// false);
	//
	// out.println(
	// "Post-preprocessing token stream for " + sourceUnit + ":\n");
	// PreprocessorUtils.printTokenSource(out, source);
	// out.flush();
	// }
	//
	// @Override
	// public void printOutput(File[] systemIncludePaths, File[]
	// userIncludePaths,
	// Map<String, String> implicitMacros, PrintStream out,
	// File[] sourceUnit) throws PreprocessorException {
	// CPreprocessorWorker worker = new CPreprocessorWorker(config, this,
	// systemIncludePaths, userIncludePaths, implicitMacros);
	// PreprocessorTokenSource source = worker.outputTokenSource(sourceUnit,
	// false);
	//
	// PreprocessorUtils.sourceTokenSource(out, source);
	// out.flush();
	// }
	//
	// @Override
	// public void printOutputDebug(File[] systemIncludePaths,
	// File[] userIncludePaths, Map<String, String> implicitMacros,
	// PrintStream out, File[] sourceUnit) throws PreprocessorException {
	// out.println("Post-preprocessing output for " + file + ":\n");
	// out.println("----------------------------------->");
	// printOutput(systemIncludePaths, userIncludePaths, implicitMacros, out,
	// file);
	// out.println("<-----------------------------------");
	// out.flush();
	// }
	//
	// @Override
	// public void debug(File[] systemIncludePaths, File[] userIncludePaths,
	// Map<String, String> implicitMacros, PrintStream out,
	// File[] sourceUnit) throws PreprocessorException {
	// PreprocessorUtils.source(out, file);
	// out.println();
	// lex(out, file);
	// out.println();
	// parse(out, file);
	// out.println();
	// printOutputTokens(systemIncludePaths, userIncludePaths, implicitMacros,
	// out, file);
	// out.println();
	// printOutputDebug(systemIncludePaths, userIncludePaths, implicitMacros,
	// out, file);
	// out.println();
	// }

	// @Override
	// public Collection<SourceFile> getSourceFiles() {
	// return sourceFiles;
	// }
	//
	// @Override
	// public SourceFile getSourceFile(File file) {
	// return sourceFileMap.get(file);
	// }
	//
	// @Override
	// public int getNumSourceFiles() {
	// return sourceFiles.size();
	// }
	//
	// @Override
	// public SourceFile getSourceFile(int index) {
	// return sourceFiles.get(index);
	// }

	// @Override
	// public void printSourceFiles(PrintStream out) {
	// out.println("Source files:");
	// for (SourceFile sourceFile : sourceFiles) {
	// out.println(
	// sourceFile.getIndexName() + "\t: " + sourceFile.getPath());
	// }
	// out.println();
	// out.flush();
	// }

	// /**
	// * Looks to see if a {@link SourceFile} object has already been created
	// for
	// * the given {@link File}. If so, returns that one. Else creates a new
	// one,
	// * assigns it the next index, and, if it is not a temporary file created
	// by
	// * ABC, stores it.
	// *
	// * @param file
	// * a file that is being read to produce this token source
	// * @param tmpFile
	// * true iff this is a temporary file created by ABC
	// * @return the {@link SourceFile} corresponding to the given file
	// */
	// SourceFile getOrMakeSourceFile(File file, boolean tmpFile) {
	// SourceFile result = sourceFileMap.get(file);
	//
	// if (result == null) {
	// result = new SourceFile(file, sourceFiles.size());
	// if (!tmpFile) {
	// // don't keep track of temp files created by parsing command
	// // line macros
	// sourceFiles.add(result);
	// sourceFileMap.put(file, result);
	// }
	// }
	// return result;
	// }

	// public CivlcTokenSource tokenSourceOfLibrary(String name) {
	// CPreprocessorWorker worker = new CPreprocessorWorker(config, this,
	// new File[0], new File[0], new HashMap<String, Macro>());
	//
	// return worker.tokenSourceOfLibrary(name);
	// }
}
