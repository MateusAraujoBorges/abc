package edu.udel.cis.vsl.abc.front.fortran.preproc;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;

import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.token.IF.CivlcToken;
import edu.udel.cis.vsl.abc.token.IF.CivlcTokenSource;
import edu.udel.cis.vsl.abc.token.IF.FileIndexer;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.IF.Macro;
import edu.udel.cis.vsl.abc.token.IF.SourceFile;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;

public class FortranTokenSource implements CivlcTokenSource {

	// Fields...

	/// ** The list of character streams to be parsed and preprocessed */
	// private CharStream[] theStreams;

	/// **
	// * The list of formations corresponding to {@link #theStreams}. The
	// * {@link Formation} corresponding to a {@link CharStream} provides
	// * information on where the stream came from.
	// */
	// private Formation[] theFormations;

	/**
	 * Object to track of all source files encountered by this preprocessing
	 * instance.
	 */
	private FileIndexer indexer;

	/**
	 * The source files used to build this token stream only. These do not
	 * necessarily include all of the source files seen by the preprocessor
	 * creating this token source, because the preprocessor can be re-used
	 * multiple times to create many token sources.
	 */
	private Set<SourceFile> sourceFiles = new LinkedHashSet<>();

	/**
	 * Factory used to produce new {@link CivlcToken}s.
	 */
	private TokenFactory tokenFactory;

	// /**
	// * The directories which should be searched for files that are included
	// * using
	// *
	// * <pre>
	// * #include &lt;filename&gt;
	// * </pre>
	// *
	// * syntax.
	// */
	// private File[] systemIncludePaths;

	// /**
	// * The directories which should be searched for files that are included
	// * using
	// *
	// * <pre>
	// * #include "filename"
	// * </pre>
	// *
	// * syntax. If the file is not found in one of these directories, the
	// system
	// * paths are then searched.
	// */
	// private File[] userIncludePaths;

	private FortranTokenStream tokenStream;

	public FortranTokenSource(Configuration config, FileIndexer indexer,
			CharStream[] streams, Formation[] formations,
			File[] systemIncludePaths, File[] userIncludePaths,
			Map<String, Macro> macroMap, TokenFactory tokenFactory,
			FortranTokenStream tokenStream) throws PreprocessorException {
//		int numStreams = streams.length;

		assert systemIncludePaths != null;
		assert userIncludePaths != null;
		this.indexer = indexer;
		this.tokenFactory = tokenFactory;
//		assert numStreams == formations.length;
		// this.theStreams = streams;
		// this.theFormations = formations;
		// this.systemIncludePaths = systemIncludePaths;
		// this.userIncludePaths = userIncludePaths;
		for (Formation formation : formations) {
			SourceFile sourceFile = formation.getLastFile();

			sourceFiles.add(sourceFile);
			indexer.getOrAdd(sourceFile.getFile());
		}
		this.tokenStream = tokenStream;
	}

	// Public methods...

	@Override
	public String toString() {
		return "PreprocessorTokenSource[" + getSourceName() + "]";
	}

	@Override
	public TokenFactory getTokenFactory() {
		return tokenFactory;
	}

	/**
	 * This method is not used for Fortran Preprocessor.
	 */
	@Override
	@Deprecated
	public Token nextToken() {
		return null;
	}

	@Override
	public String getSourceName() {
		return tokenStream.getSourceName();
	}

	@Override
	public int getNumTokens() {
		return tokenStream.getTokensListSize();
	}

	@Override
	public CivlcToken getToken(int index) {
		return (CivlcToken) tokenStream.getToken(index);
	}

	@Override
	public FileIndexer getIndexer() {
		return indexer;
	}

	@Override
	public Collection<SourceFile> getSourceFiles() {
		return sourceFiles;
	}

	public FortranTokenStream getTokenStream() {
		return tokenStream;
	}
}
