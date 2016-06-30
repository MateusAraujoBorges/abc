package edu.udel.cis.vsl.abc.front.c.preproc;

/**
 * This is the worker of the preprocessor, which is instantiated each time when
 * a preprocessing is requested. The purpose is to make the preprocessor
 * stateless and re-usable.
 * 
 * @author Manchun Zheng
 * 
 */
public class CPreprocessorWorker {

//	/* ******************* Package-private static fields ******************* */
//
//	/**
//	 * The preprocessor which created this worker.
//	 */
//	private CPreprocessor preprocessor;
//
//	private FileIndexer indexer;
//
//	/**
//	 * The system include paths to search for included system headers
//	 */
//	private File[] systemIncludePaths;
//
//	/**
//	 * The user include paths to search for included user headers
//	 */
//	private File[] userIncludePaths;
//
//	/**
//	 * The predefined macros, including those specified by command line
//	 * specification.
//	 */
//	private Map<String, Macro> implicitMacros;
//
//	/**
//	 * The token factory.
//	 */
//	private TokenFactory tokenFactory = Tokens.newTokenFactory();
//
//	/**
//	 * The configuration of the translation task
//	 */
//	private Configuration config;
//
//	/* ******************* Package-private static fields ******************* */
//
//	/**
//	 * Default value for implicit macros, which is just an empty map.
//	 */
//	static Map<String, Macro> defaultImplicitMacros = new HashMap<>();
//
//	/**
//	 * Default value for system include path list.
//	 */
//	static File[] defaultSystemIncludes = new File[] {};
//
//	/**
//	 * Default value for user include path list. Currently, it consists of one
//	 * directory, namely, the working directory.
//	 */
//	static File[] defaultUserIncludes = new File[] {
//			new File(System.getProperty("user.dir")) };
//
//	/* *************************** Constructors **************************** */
//
//	/**
//	 * Creates a new instance of preprocessor worker.
//	 * 
//	 * @param systemIncludePaths
//	 *            the system include paths to search for included system headers
//	 * @param userIncludePaths
//	 *            the user include paths to search for included user headers
//	 * @param macros
//	 *            the predefined macros, including those specified in command
//	 *            line
//	 */
//	public CPreprocessorWorker(Configuration config, CPreprocessor preprocessor,
//			FileIndexer indexer, File[] systemIncludePaths,
//			File[] userIncludePaths, Map<String, Macro> macros) {
//		this.preprocessor = preprocessor;
//		this.config = config;
//		this.indexer = indexer;
//		if (systemIncludePaths == null)
//			this.systemIncludePaths = defaultSystemIncludes;
//		else
//			this.systemIncludePaths = systemIncludePaths;
//		if (userIncludePaths == null)
//			this.userIncludePaths = defaultUserIncludes;
//		else
//			this.userIncludePaths = userIncludePaths;
//		if (macros == null || macros.size() == 0)
//			this.implicitMacros = defaultImplicitMacros;
//		else
//			this.implicitMacros = new HashMap<>(macros);
//	}
//
//	/* ************************ Private methods ************************* */
//
//	// /**
//	// * Given a preprocessor source file, this returns a Token Source that
//	// emits
//	// * the tokens resulting from preprocessing the file.
//	// *
//	// * @param file
//	// * @param macroMap
//	// * @param tokenFactory
//	// * @param preprocessor
//	// * @return
//	// * @throws PreprocessorException
//	// */
//	// private PreprocessorTokenSource outputTokenSource(File file,
//	// Map<String, Macro> macroMap, TokenFactory tokenFactory,
//	// boolean tmpFile) throws PreprocessorException {
//	// PreprocessorParser parser = preprocessor.parser(file);
//	// PreprocessorTokenSource tokenSource = new PreprocessorTokenSource(
//	// this.config, file, parser, systemIncludePaths,
//	// userIncludePaths, macroMap, tokenFactory, this, tmpFile);
//	//
//	// return tokenSource;
//	// }
//
//	/* ************************ Package methods ************************* */
//
//	// /**
//	// * Given a preprocessor source file, this returns a Token Source that
//	// emits
//	// * the tokens resulting from preprocessing the file.
//	// *
//	// * @param file
//	// * the file to be preprocessed
//	// * @param preprocessor
//	// * @return a token source for the token resulting from preprocessing the
//	// * file
//	// * @throws PreprocessorException
//	// * if an I/O error occurs
//	// */
//	// PreprocessorTokenSource outputTokenSource(File file, boolean tmpFile)
//	// throws PreprocessorException {
//	// Map<String, Macro> macroMap = new HashMap<String, Macro>();
//	//
//	// if (implicitMacros != null)
//	// macroMap.putAll(implicitMacros);
//	// return outputTokenSource(file, macroMap, tokenFactory, tmpFile);
//	// }
//
//	/**
//	 * Given a preprocessor source file, this returns a Token Source that emits
//	 * the tokens resulting from preprocessing the file.
//	 * 
//	 * @param filename
//	 *            the name of the file to be preprocessed
//	 * @param preprocessor
//	 * @return a token source for the token resulting from preprocessing the
//	 *         file
//	 * @throws PreprocessorException
//	 *             if an I/O error occurs
//	 */
//	CivlcTokenSource outputTokenSource(boolean isSystem, String filename)
//			throws PreprocessorException, IOException {
//		Map<String, Macro> macroMap = new HashMap<String, Macro>();
//
//		if (implicitMacros != null)
//			macroMap.putAll(implicitMacros);
//
//		File file = null;
//		CharStream charStream;
//		PreprocessorParser parser;
//		PreprocessorLexer lexer;
//		int numErrors;
//
//		try {
//			file = new File(filename);
//			if (isSystem) {
//				charStream = getCharStreamFromSystem(filename);
//				// no such file:
//				if (charStream == null)
//					return null;
//			} else
//				charStream = PreprocessorUtils
//						.newFilteredCharStreamFromFile(file);
//		} catch (FileNotFoundException fof) {
//			// If the target file is not in the current directory, search as a
//			// system library file
//			charStream = getCharStreamFromSystem(filename);
//		}
//		lexer = new PreprocessorLexer(charStream);
//		parser = new PreprocessorParser(new CommonTokenStream(lexer));
//		numErrors = parser.getNumberOfSyntaxErrors();
//		if (numErrors != 0)
//			throw new PreprocessorException(numErrors
//					+ " syntax errors occurred while scanning included file "
//					+ file);
//
//		PreprocessorTokenSource tokenSource = new PreprocessorTokenSource(
//				this.config, file, parser, systemIncludePaths, userIncludePaths,
//				macroMap, tokenFactory, this, false);
//
//		return tokenSource;
//	}
//
//	CivlcTokenSource tokenSourceOfLibrary(String name) {
//		File file = new File(Preprocessor.ABC_INCLUDE_PATH, name);
//
//		try {
//			CharStream charStream = PreprocessorUtils
//					.newFilteredCharStreamFromResource(name,
//							file.getAbsolutePath());
//			PreprocessorLexer lexer = new PreprocessorLexer(charStream);
//			PreprocessorParser parser = new PreprocessorParser(
//					new CommonTokenStream(lexer));
//
//			return new PreprocessorTokenSource(this.config, file, parser,
//					indexer, new File[0], new File[0],
//					new HashMap<String, Macro>(), Tokens.newTokenFactory(),
//					this, false);
//
//		} catch (IOException | PreprocessorException e) {
//			throw new ABCRuntimeException(
//					"errors encountered when preprocessing library " + name);
//		}
//	}

}
