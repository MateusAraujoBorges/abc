package edu.udel.cis.vsl.abc.main;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import edu.udel.cis.vsl.abc.analysis.IF.Analysis;
import edu.udel.cis.vsl.abc.analysis.IF.Analyzer;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.IF.ASTs;
import edu.udel.cis.vsl.abc.ast.conversion.IF.ConversionFactory;
import edu.udel.cis.vsl.abc.ast.conversion.IF.Conversions;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entities;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.abc.ast.entity.IF.Scope;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.AttributeKey;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.Nodes;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.abc.ast.type.IF.Types;
import edu.udel.cis.vsl.abc.ast.value.IF.ValueFactory;
import edu.udel.cis.vsl.abc.ast.value.IF.Values;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.front.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.front.IF.Front;
import edu.udel.cis.vsl.abc.front.IF.ParseException;
import edu.udel.cis.vsl.abc.front.IF.ParseTree;
import edu.udel.cis.vsl.abc.front.IF.Parser;
import edu.udel.cis.vsl.abc.front.IF.Preprocessor;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.front.c.ptree.CParseTree;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.abc.program.IF.ProgramFactory;
import edu.udel.cis.vsl.abc.program.IF.Programs;
import edu.udel.cis.vsl.abc.token.IF.CivlcTokenSource;
import edu.udel.cis.vsl.abc.token.IF.FileIndexer;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;
import edu.udel.cis.vsl.abc.token.IF.Tokens;
import edu.udel.cis.vsl.abc.transform.IF.Transform;
import edu.udel.cis.vsl.abc.transform.IF.Transformer;

/**
 * <p>
 * A FrontEnd provides a simple, high-level interface for accessing all of the
 * main functionality of ABC. It provides two different families of methods: (1)
 * methods to get or create individual components of the ABC tool chain, such as
 * factories, {@link Preprocessor}s, {@link Parser}s, etc., and (2) higher-level
 * methods which marshal together these different components in order to carry
 * out a complete translation task, such as compiling a translation unit, or
 * linking several translation units to form a complete {@link Program}.
 * </p>
 * 
 * @author siegel
 * 
 */
public class FrontEnd {

	private Configuration configuration;

	private TokenFactory tokenFactory;

	private TypeFactory typeFactory;

	private ValueFactory valueFactory;

	private NodeFactory nodeFactory;

	private ASTFactory astFactory;

	private EntityFactory entityFactory;

	private ConversionFactory conversionFactory;

	private FileIndexer fileIndexer;

	private Map<Language, Analyzer> analyzers = new HashMap<>();

	private Map<Language, Parser> parsers = new HashMap<>();

	private Map<Language, Preprocessor> preprocessors = new HashMap<>();

	private Map<Language, ASTBuilder> astBuilders = new HashMap<>();

	private Map<Language, ProgramFactory> programFactories = new HashMap<>();

	// TODO: where does this belong?
	private AttributeKey intDivAttributeKey;

	/**
	 * Constructs a new front end. The front end can be used repeatedly to
	 * perform multiple translation tasks. The factories used by this front end
	 * will persist throughout its lifetime, i.e., new factories are not created
	 * for each task.
	 * 
	 * @param configuration
	 *            the configuration object specifying configuration options for
	 *            this ABS session
	 */
	public FrontEnd(Configuration configuration) {
		this.configuration = configuration;
	}

	public TokenFactory getTokenFactory() {
		if (tokenFactory == null)
			tokenFactory = Tokens.newTokenFactory();
		return tokenFactory;
	}

	public TypeFactory getTypeFactory() {
		if (typeFactory == null)
			typeFactory = Types.newTypeFactory();
		return typeFactory;
	}

	public ValueFactory getValueFactory() {
		if (valueFactory == null)
			valueFactory = Values.newValueFactory(configuration,
					getTypeFactory());
		return valueFactory;
	}

	public FileIndexer getFileIndexer() {
		if (fileIndexer == null)
			fileIndexer = getTokenFactory().newFileIndexer();
		return fileIndexer;
	}

	/**
	 * Gets the node factory used by this front end. The node factory is part of
	 * the {@link ASTFactory}.
	 * 
	 * @return the node factory
	 */
	public NodeFactory getNodeFactory() {
		if (nodeFactory == null)
			nodeFactory = Nodes.newNodeFactory(configuration, getTypeFactory(),
					getValueFactory());
		return nodeFactory;
	}

	/**
	 * Returns the {@link ASTFactory} used by this front end. This factory (or
	 * its sub-factories) are used to create all components of an AST, including
	 * new {@link ASTNode}s.
	 * 
	 * @return the AST factory
	 */
	public ASTFactory getASTFactory() {
		if (astFactory == null)
			astFactory = ASTs.newASTFactory(getNodeFactory(), tokenFactory,
					getTypeFactory());
		return astFactory;
	}

	public EntityFactory getEntityFactory() {
		if (entityFactory == null)
			entityFactory = Entities.newEntityFactory();
		return entityFactory;
	}

	public ConversionFactory getConversionFactory() {
		if (conversionFactory == null)
			conversionFactory = Conversions
					.newConversionFactory(getTypeFactory());
		return conversionFactory;
	}

	/**
	 * Creates a {@link Preprocessor} based on the specified system and include
	 * path lists. The new {@link Preprocessor} can be used to preprocess source
	 * files repeatedly. The method {@link Preprocessor#outputTokenSource} is
	 * used to obtain the stream of tokens emanating from the preprocessor.
	 * 
	 * @param language
	 *            the language of requested preprocessor
	 * @return the new Preprocessor
	 */
	public Preprocessor getPreprocessor(Language language) {
		Preprocessor result = preprocessors.get(language);

		if (result == null) {
			result = Front.newPreprocessor(language, configuration,
					getFileIndexer(), getTokenFactory());
			preprocessors.put(language, result);
		}
		return result;
	}

	/**
	 * Returns the parser used by this front end. The parser is used to parse a
	 * token stream and produce a {@link ParseTree}. The parser can be used
	 * repeatedly.
	 * 
	 * @param language
	 *            the language of the requested parser
	 * @return the parser
	 */
	public Parser getParser(Language language) {
		Parser result = parsers.get(language);

		if (result == null) {
			result = Front.newParser(language);
			parsers.put(language, result);
		}
		return result;
	}

	/**
	 * Returns the {@link ASTBuilder} used by this front end. The builder is
	 * used convert a {@link CParseTree} to an {@link AST}. The builder can be
	 * used repeatedly.
	 * 
	 * @param language
	 *            the language of the requested AST builder
	 * @return the builder used to translate parse trees to ASTs
	 */
	public ASTBuilder getASTBuilder(Language language) {
		ASTBuilder result = astBuilders.get(language);

		if (result == null) {
			result = Front.newASTBuilder(language, configuration,
					getASTFactory());
			astBuilders.put(language, result);
		}
		return result;
	}

	/**
	 * Returns a standard {@link Analyzer}, which is used to analyze an AST,
	 * leaving behind information such as (1) the {@link Scope} of every node,
	 * (2) the {@link Type} of every expression, (3) the {@link Entity}
	 * associated to every identifier.
	 * 
	 * @param language
	 *            language of the requested analyzer
	 * @return a standard Analyzer for that language
	 */
	public Analyzer getStandardAnalyzer(Language language) {
		Analyzer result = analyzers.get(language);

		if (result == null) {
			result = Analysis.newStandardAnalyzer(language, configuration,
					getASTFactory(), getEntityFactory(),
					getConversionFactory());
			analyzers.put(language, result);
		}
		return result;
	}

	/**
	 * Returns a program factory based on the given analyzer. The factory will
	 * apply that analyzer every time it instantiates a new {@link Program}.
	 * 
	 * @param analyzer
	 *            an analyzer that will be applied to any program created by the
	 *            factory
	 * @return the new program factory based on the analyzer
	 */
	public ProgramFactory getProgramFactory(Language language) {
		ProgramFactory result = programFactories.get(language);

		if (result == null) {
			result = Programs.newProgramFactory(getASTFactory(),
					getStandardAnalyzer(language));
			programFactories.put(language, result);
		}
		return result;
	}

	/**
	 * Creates a new {@link Transformer} specified by the given transformer
	 * code.
	 * 
	 * @param code
	 *            a string code which specifies a transformer
	 * @return the new transformer
	 */
	public Transformer getTransformer(String code) {
		return Transform.newTransformer(code, astFactory);
	}

	// TODO: whoever created this method must add javadoc
	public void setIntDivAttributeKey(AttributeKey intDivAttributeKey) {
		this.intDivAttributeKey = intDivAttributeKey;
	}

	// Actions...

	/**
	 * Preprocesses and parses the specified files, returning an AST
	 * representation. The AST will not be analyzed, and so will not have any
	 * information on types, identifiers, entities, and so on. This result is
	 * known as a "raw" translation unit.
	 * 
	 * @param language
	 *            the language of the translation unit
	 * @param sourceUnit
	 *            the file sequence to parse as a single translation unit
	 * @param systemIncludePaths
	 *            the system include paths to search for included system
	 *            headers; may use {@link ABC#DEFAULT_SYSTEM_INCLUDE_PATHS}
	 * @param userIncludePaths
	 *            the user include paths to search for included user headers;
	 *            may use {@link ABC#DEFAULT_USER_INCLUDE_PATHS}
	 * @param predefinedMacros
	 *            map from macro names to macros bodies to incorporate before
	 *            preprocessing; such macros might be defined on the command
	 *            line via -DMACRO=VALUE, for example; may use
	 *            {@link ABC#DEFAULT_IMPLICIT_MACROS}
	 * @return the raw translation unit obtained by parsing the file
	 * @throws PreprocessorException
	 *             if the file contains a preprocessor error
	 * @throws ParseException
	 *             if the token stream emanating from the preprocessor does not
	 *             satisfy the grammar of the language
	 * @throws SyntaxException
	 *             if the file violates some aspect of the syntax of the
	 *             language
	 */
	public AST parse(Language language, File[] sourceUnit,
			File[] systemIncludePaths, File[] userIncludePaths,
			Map<String, String> predefinedMacros) throws PreprocessorException,
					SyntaxException, ParseException {
		Preprocessor preprocessor = getPreprocessor(language);
		CivlcTokenSource tokens = preprocessor.preprocess(
				systemIncludePaths, userIncludePaths, predefinedMacros,
				sourceUnit);
		Parser parser = this.getParser(language);
		ParseTree parseTree = parser.parse(tokens);
		ASTBuilder builder = this.getASTBuilder(language);
		AST ast = builder.getTranslationUnit(parseTree);

		return ast;
	}

	/**
	 * Compiles the given files as a single translation unit, producing an AST
	 * representation with full analysis results. The AST will contain type
	 * information, symbol table information mapping every identifier to an
	 * {@link Entity}, scope information, and so on. It is an
	 * "analyzed translation unit".
	 * 
	 * @param sourceUnit
	 *            the file to compile
	 * @param language
	 *            the language in which the file is written
	 * @param systemIncludePaths
	 *            the system include paths to search for included system
	 *            headers; may use {@link ABC#DEFAULT_SYSTEM_INCLUDE_PATHS}
	 * @param userIncludePaths
	 *            the user include paths to search for included user headers;
	 *            may use {@link ABC#DEFAULT_USER_INCLUDE_PATHS}
	 * @param implicitMacros
	 *            map from macro names to bodies that are to be incorporated
	 *            before preprocessing; such macros might be defined on the
	 *            command line via -DMACRO=VALUE, for example; may use
	 *            {@link ABC#DEFAULT_IMPLICIT_MACROS}
	 * @return the analyzed AST representing the translation unit
	 * @throws PreprocessorException
	 *             if the file contains a preprocessor error
	 * @throws ParseException
	 *             if the token stream emanating from the preprocessor does not
	 *             satisfy the grammar of the language
	 * @throws SyntaxException
	 *             if the file violates some aspect of the syntax of the
	 *             language
	 */
	public AST compile(File[] sourceUnit, Language language,
			File[] systemIncludePaths, File[] userIncludePaths,
			Map<String, String> implicitMacros) throws PreprocessorException,
					SyntaxException, ParseException {
		AST result = parse(language, sourceUnit, systemIncludePaths,
				userIncludePaths, implicitMacros);
		Analyzer analyzer = getStandardAnalyzer(language);

		analyzer.analyze(result);
		return result;
	}

	/**
	 * Compiles the given files as a single translation unit, producing an AST
	 * representation with full analysis results. Equivalent to invoking
	 * {@link #compile(File, Language, File[], File[], Map) with the default
	 * values {@link ABC#DEFAULT_SYSTEM_INCLUDE_PATHS},
	 * {@link ABC#DEFAULT_USER_INCLUDE_PATHS},
	 * {@link ABC#DEFAULT_IMPLICIT_MACROS} for the last three arguments.
	 * 
	 * @param sourceUnit
	 *            the file sequence to compile
	 * @param language
	 *            the language in which the file is written
	 * @return the analyzed AST representing the translation unit
	 * @throws PreprocessorException
	 *             if the file contains a preprocessor error
	 * @throws ParseException
	 *             if the token stream emanating from the preprocessor does not
	 *             satisfy the grammar of the language
	 * @throws SyntaxException
	 *             if the file violates some aspect of the syntax of the
	 *             language
	 */
	public AST compile(File[] sourceUnit, Language language)
			throws PreprocessorException, SyntaxException, ParseException {
		return compile(sourceUnit, language, ABC.DEFAULT_SYSTEM_INCLUDE_PATHS,
				ABC.DEFAULT_USER_INCLUDE_PATHS, ABC.DEFAULT_IMPLICIT_MACROS);
	}

	/**
	 * Links the given translation units to form a whole program. The
	 * translation units may be "raw" (containing no analysis information) or
	 * not---it makes no difference since any analysis information will be
	 * erased and replaced with a fresh analysis. The translation units will be
	 * merged to form a single large AST; some entities may have to be renamed
	 * in this process, to avoid naming conflicts.
	 * 
	 * @param translationUnits
	 *            ASTs representing individual translation units
	 * @param language
	 *            the language to use when analyzing and linking
	 * @return the program formed by linking the translation units
	 * @throws SyntaxException
	 *             if any translation unit contains some statically detectable
	 *             error or the units cannot be linked for some reason
	 */
	public Program link(AST[] translationUnits, Language language)
			throws SyntaxException {
		ProgramFactory programFactory;
		Program result;

		programFactory = getProgramFactory(language);
		if (intDivAttributeKey != null)
			programFactory.setIntDivMacroKey(intDivAttributeKey);
		result = programFactory.newProgram(translationUnits);
		return result;
	}

	// /**
	// * Compiles the given files and links the resulting translation units to
	// * form a complete program. This is the method that "does everything".
	// *
	// * @param sourceUnits
	// * the source files to compile
	// * @param language
	// * the language to use when compiling the source files
	// * @param systemIncludePaths
	// * the system include paths to search for included system
	// * headers; may use {@link ABC#DEFAULT_SYSTEM_INCLUDE_PATHS}
	// * @param userIncludePaths
	// * the user include paths to search for included user headers;
	// * may use {@link ABC#DEFAULT_USER_INCLUDE_PATHS}
	// * @param implicitMacros
	// * map from macro names to macros that are to be incorporated
	// * before preprocessing each file; such macros might be defined
	// * on the command line via -DMACRO=VALUE, for example; may use
	// * {@link ABC#DEFAULT_IMPLICIT_MACROS}
	// * @return the Program that results from compiling and linking
	// * @throws PreprocessorException
	// * if any file contains a preprocessor error
	// * @throws ParseException
	// * if the token stream emanating from the preprocessing of a
	// * file does not satisfy the grammar of the language
	// * @throws SyntaxException
	// * if any file violates some aspect of the syntax of the
	// * language or the translation units cannot be linked for some
	// * reason
	// */
	// public Program compileAndLink(File[][] sourceUnits, Language language,
	// File[] systemIncludePaths, File[] userIncludePaths,
	// Map<String, String> implicitMacros) throws PreprocessorException,
	// SyntaxException, ParseException {
	// Preprocessor preprocessor;
	// Analyzer analyzer;
	// ProgramFactory programFactory;
	// int numUnits = sourceUnits.length;
	// AST[] asts = new AST[numUnits];
	// Program result;
	// Parser parser = this.getParser(language);
	// ASTBuilder builder = this.getASTBuilder(language);
	//
	// preprocessor = getPreprocessor(language);
	// analyzer = getStandardAnalyzer(language);
	// programFactory = getProgramFactory(analyzer);
	// for (int i = 0; i < numUnits; i++) {
	// CivlcTokenSource tokens = preprocessor.outputTokenSource(
	// systemIncludePaths, userIncludePaths, implicitMacros,
	// sourceUnits[i]);
	// ParseTree parseTree = parser.parse(tokens);
	//
	// asts[i] = builder.getTranslationUnit(parseTree);
	// }
	// result = programFactory.newProgram(asts);
	// return result;
	// }

	// /**
	// * Compiles and links the specified files. Equivalent to invoking
	// * {@link #compileAndLink(File[], Language, File[], File[], Map)} with the
	// * default values {@link ABC#DEFAULT_SYSTEM_INCLUDE_PATHS},
	// * {@link ABC#DEFAULT_USER_INCLUDE_PATHS},
	// * {@link ABC#DEFAULT_IMPLICIT_MACROS} for the last three arguments.
	// *
	// * @param sourceUnits
	// * the source files to compile
	// * @param language
	// * the language to use when compiling the source files
	// * @return the Program that results from compiling and linking
	// * @throws PreprocessorException
	// * if any file contains a preprocessor error
	// * @throws ParseException
	// * if the token stream emanating from the preprocessing of a
	// * file does not satisfy the grammar of the language
	// * @throws SyntaxException
	// * if any file violates some aspect of the syntax of the
	// * language or the translation units cannot be linked for some
	// * reason
	// */
	// public Program compileAndLink(File[][] sourceUnits, Language language)
	// throws PreprocessorException, SyntaxException, ParseException {
	// return compileAndLink(sourceUnits, language,
	// ABC.DEFAULT_SYSTEM_INCLUDE_PATHS,
	// ABC.DEFAULT_USER_INCLUDE_PATHS, ABC.DEFAULT_IMPLICIT_MACROS);
	// }

	/**
	 * Prints the program, symbol table, and type information to the given
	 * output stream in a plain-text, human-readable format.
	 * 
	 * @param out
	 *            the output stream
	 * @param program
	 *            the program
	 * @param pretty
	 *            if true, print AST in the original language, else print in
	 *            hierarchical form
	 * @param showTables
	 *            if true, print the symbol and type tables in addition to the
	 *            AST
	 */
	public void printProgram(PrintStream out, Program program, boolean pretty,
			boolean showTables) {
		if (pretty)
			program.prettyPrint(out);
		else
			program.print(out);
		if (showTables) {
			out.println("\n\nSymbol Table:\n");
			program.printSymbolTable(out);
			out.println("\n\nTypes:\n");
			typeFactory.printTypes(out);
		}
		out.println();
		out.flush();
	}

	/**
	 * Gets the {@link Configuration} object for this ABC Front-end.
	 *
	 * @return the {@link Configuration} object.
	 */
	public Configuration getConfiguration() {
		return this.configuration;
	}

}
