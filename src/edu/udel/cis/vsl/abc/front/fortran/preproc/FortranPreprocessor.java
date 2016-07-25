package edu.udel.cis.vsl.abc.front.fortran.preproc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.CharStream;

import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.front.IF.Preprocessor;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.front.c.preproc.FilteredANTLRFileStream;
import edu.udel.cis.vsl.abc.front.c.preproc.FilteredANTLRInputStream;
import edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorUtils;
import edu.udel.cis.vsl.abc.token.IF.CivlcTokenSource;
import edu.udel.cis.vsl.abc.token.IF.FileIndexer;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.IF.Macro;
import edu.udel.cis.vsl.abc.token.IF.SourceFile;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;

public class FortranPreprocessor implements Preprocessor {

	// Static members...

	/**
	 * Print debugging information.
	 */
	public final static boolean debug = false;

	// Instance fields...

	/**
	 * The configuration governs a number of choices which influence
	 * preprocessing.
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

	public FortranPreprocessor(Configuration config, Language language,
			FileIndexer indexer, TokenFactory tokenFactory) {
		this.config = config;
		this.language = language;
		this.indexer = indexer;
		this.tokenFactory = tokenFactory;
	}

	// Helpers...

	/**
	 * Adds streams for macros, svcomp.h, gnuc.h, and civlc.cvh, as needed, to
	 * the stream and formation vectors.
	 * 
	 * @param predefinedMacros
	 *            map from macro names (including parameter list) to macro body
	 * @param streamVector
	 *            vector of character streams which will form input to
	 *            preprocessor
	 * @param formationVector
	 *            vector of corresponding formations
	 * @throws PreprocessorException
	 *             if one of the library files cannot be found or opened
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

	/**
	 * Adds a character stream derived from a macro map to a stream vector,
	 * formationVector.
	 * 
	 * @param predefinedMacros
	 *            map from macro names (including parameter list) to macro body
	 * @param streamVector
	 *            vector of character streams which will form input to
	 *            preprocessor
	 * @param formationVector
	 *            vector of corresponding formations
	 */
	private void addMacros(Map<String, String> predefinedMacros,
			ArrayList<CharStream> streamVector,
			ArrayList<Formation> formationVector) {
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
	 * Creates character streams and formations from the files and adds them to
	 * the given stream and formation vectors. ABC will first look for the file
	 * in the usual file system. If it isn't there, it will then look
	 * internally: look relative to the directories in the class path.
	 * 
	 * @param sourceFiles
	 *            the list of source files that will form the input to the
	 *            preprocessor
	 * @param streamVector
	 *            current list of character streams that will form the real
	 *            input;
	 * @param formationVector
	 *            corresponding list of formations for those streams
	 * @throws PreprocessorException
	 *             if any source file cannot be found or opened
	 */
	private void addFiles(File[] sourceFiles,
			ArrayList<CharStream> streamVector,
			ArrayList<Formation> formationVector) throws PreprocessorException {
		int numFiles = sourceFiles.length;

		for (int i = 0; i < numFiles; i++) {
			File file = sourceFiles[i];
			SourceFile sourceFile = indexer.getOrAdd(file);
			CharStream stream;

			try {
				if (file.exists()) {
					stream = new FilteredANTLRFileStream(file);
				} else {
					String fileAbsPath = file.getAbsolutePath();
					InputStream inputStream = FortranPreprocessor.class
							.getResourceAsStream(fileAbsPath);

					stream = inputStream == null ? null
							: new FilteredANTLRInputStream(fileAbsPath,
									inputStream);
				}
			} catch (IOException e) {
				throw new PreprocessorException(
						"Error in opening " + file + ": " + e.getMessage());
			}
			streamVector.add(stream);
			formationVector.add(tokenFactory.newInclusion(sourceFile));
		}
	}

	@Override
	public CivlcTokenSource preprocess(File[] systemIncludePaths,
			File[] userIncludePaths, Map<String, String> predefinedMacros,
			File[] sourceUnit) throws PreprocessorException {
		ArrayList<CharStream> streamVector = new ArrayList<>();
		ArrayList<Formation> formationVector = new ArrayList<>();

		addAuxStreams(predefinedMacros, streamVector, formationVector);
		addFiles(sourceUnit, streamVector, formationVector);

		CharStream[] streams = streamVector
				.toArray(new CharStream[streamVector.size()]);
		Formation[] formations = formationVector
				.toArray(new Formation[formationVector.size()]);
		Map<String, Macro> macroMap = new HashMap<>();
		FortranStream fortranStream = null;

		try {
			fortranStream = new FortranStream(sourceUnit[0].getAbsolutePath());
		} catch (IOException e) {
			throw new PreprocessorException(e.getMessage());
		}

		FortranLexer lexer = new FortranLexer(fortranStream);

		lexer.setIncludeDirs(systemIncludePaths, userIncludePaths, sourceUnit);

		FortranTokenStream tokenStream = new FortranTokenStream(lexer);
		FortranLexicalPrepass prepass = new FortranLexicalPrepass(lexer,
				tokenStream);

		// determine whether the file is fixed or free form and
		// set the source form in the prepass so it knows how to handle lines.
		prepass.setSourceForm(fortranStream.getSourceForm());
		prepass.performPrepass();
		tokenStream.finalizeTokenStream();
		return new FortranTokenSource(config, indexer, streams, formations,
				systemIncludePaths, userIncludePaths, macroMap, tokenFactory,
				tokenStream);
	}

	@Override
	public FileIndexer getFileIndexer() {
		return indexer;
	}

	@Override
	@Deprecated
	public CivlcTokenSource preprocessLibrary(
			Map<String, String> predefinedMacros, String libraryFileName)
			throws PreprocessorException {
		return null;
	}
}
