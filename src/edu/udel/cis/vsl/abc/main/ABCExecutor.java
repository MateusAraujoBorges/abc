package edu.udel.cis.vsl.abc.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import org.antlr.runtime.CommonToken;

import edu.udel.cis.vsl.abc.analysis.IF.Analyzer;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.DifferenceObject;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.front.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.front.IF.ParseTree;
import edu.udel.cis.vsl.abc.front.IF.Parser;
import edu.udel.cis.vsl.abc.front.IF.Preprocessor;
import edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorParser;
import edu.udel.cis.vsl.abc.main.TranslationTask.TranslationStage;
import edu.udel.cis.vsl.abc.program.IF.Program;
import edu.udel.cis.vsl.abc.token.IF.CivlcTokenSource;
import edu.udel.cis.vsl.abc.transform.IF.Transform;
import edu.udel.cis.vsl.abc.transform.IF.Transformer;
import edu.udel.cis.vsl.abc.util.IF.ANTLRUtils;
import edu.udel.cis.vsl.abc.util.IF.Timer;

/**
 * <p>
 * An executor executes a {@link TranslationTask}. To use an executor, first
 * construct a {@link TranslationTask}. You can then create an executor for
 * executing that task using the constructor
 * {@link #ABCExecutor(TranslationTask)}. Then to actually execute that task,
 * invoke the executor's {@link #execute()} method.
 * </p>
 * 
 * <p>
 * Alternatively, the construction of the {@link ABCExecutor} and the execution
 * of the task can be accomplished in a single step using static method
 * {@link #execute()}, which also returns the new executor that was used to
 * execute the task.
 * </p>
 * 
 * <p>
 * The methods above all create a new {@link FrontEnd} instance to execute the
 * task. This can be expensive. If multiple tasks are to be executed, one can
 * re-use the same {@link FrontEnd} by using method {@link #getFrontEnd()} to
 * get the front end from the first executor and using static method
 * {@link #newExecutor(FrontEnd, TranslationTask)} to create a new executor that
 * re-uses that front end. Note however, that you can only re-use a front end
 * for a compatible task, that is, one that shares the same configuration
 * parameters on SV-COMP, GNUC, and Architecture.
 * </p>
 * 
 * @author siegel
 */
public class ABCExecutor {

	// Static members...

	/**
	 * Creates an ABCExecutor to execute a translation task, executes it, and
	 * then returns that executor.
	 * 
	 * @param task
	 *            the translation task
	 * @return the {@link ABCExecutor} used to execute that task
	 * @throws ABCException
	 *             if any preprocessing, parsing, syntax or semantic errors are
	 *             found in the process of carrying out the translation task
	 */
	public final static ABCExecutor execute(TranslationTask task)
			throws ABCException {
		ABCExecutor executor = new ABCExecutor(task);

		executor.execute();
		return executor;
	}

	/**
	 * Creates an ABCExecutor to execute a translation task using the given
	 * front end; executes the task; and returns that executor
	 * 
	 * @param frontEnd
	 *            the front end that will be used to carry out the translation
	 *            task steps
	 * @param task
	 *            the translation task
	 * @return the {@link ABCExecutor} created and used to execute the task
	 * @throws ABCException
	 *             if any preprocessing, parsing, syntax or semantic errors are
	 *             found in the process of carrying out the translation task, or
	 *             if <code>frontEnd</code> is incompatible with
	 *             <code>task</code>
	 */
	public final static ABCExecutor execute(FrontEnd frontEnd,
			TranslationTask task) throws ABCException {
		ABCExecutor executor = newExecutor(frontEnd, task);

		executor.execute();
		return executor;
	}

	/**
	 * Creates a new {@link ABCExecutor} for performing <code>task</code> and
	 * that uses the given <code>frontEnd</code>.
	 * 
	 * @param frontEnd
	 *            an existing front end that will be (re-)used by the new
	 *            executor to perform all translation tasks
	 * @param task
	 *            the translation task to be executed
	 * @return the new executor
	 * @throws ABCException
	 *             if <code>task</code> is incompatible with
	 *             <code>frontEnd</code> due to differing values on SVCOMP or
	 *             Architecture fields.
	 */
	public final static ABCExecutor newExecutor(FrontEnd frontEnd,
			TranslationTask task) throws ABCException {
		Configuration config = frontEnd.getConfiguration();

		if (config.getSVCOMP() != task.getSVCOMP())
			throw new ABCException(
					"Front end cannot be used to perform task due "
							+ "to incompatible SVCOMP values");
		if (config.getArchitecture() != task.getArchitecture())
			throw new ABCException(
					"Front end cannot be used to perform task due "
							+ "to incompatible Architecture values");
		if (config.getGNUC() != task.getGNUC())
			throw new ABCException(
					"Front end cannot be used to perform task due "
							+ "to incompatible GNUC values");
		return new ABCExecutor(frontEnd, task);
	}

	/**
	 * A bar used in printing output.
	 */
	private final static String bar = "===================";

	/**
	 * Computes a name for a source unit. The name is the concatenation of the
	 * names of the files comprising the unit, separated with "+". A
	 * "source unit" is a sequence of files which will be preprocessed as a
	 * single translation unit.
	 * 
	 * @param sourceUnit
	 *            sequence of files which will be preprocessed to create a
	 *            single translation unit
	 * @return a name for the sequence of files derived from their file names
	 */
	private final static String getName(File[] sourceUnit) {
		int numFiles = sourceUnit.length;
		String result = "";

		for (int i = 0; i < numFiles; i++) {
			if (i > 0)
				result += "+";
			result += sourceUnit[i].getName();
		}
		return result;
	}

	/**
	 * Prints file scope functions that are used but not defined.
	 * 
	 * @param program
	 *            a non-<code>null</code> {@link Program}
	 */
	private final static void printUnknownFunctions(PrintStream out,
			Program program) {
		SequenceNode<BlockItemNode> root = program.getAST().getRootNode();
		int i = 0;
		Set<String> functionNames = new HashSet<>();

		for (BlockItemNode item : root) {
			if (item instanceof FunctionDeclarationNode) {
				FunctionDeclarationNode function = (FunctionDeclarationNode) item;

				if (function.getEntity().getDefinition() == null) {
					String functionName = function.getName();

					if (!functionNames.contains(functionName)) {
						if (i == 0)
							out.println(
									"==== functions without definition ====");
						else
							out.print(",");
						out.print(functionName);
						functionNames.add(functionName);
						i++;
					}
				}
			}
		}
		if (i > 0)
			out.println();
		out.flush();
	}

	// Instance fields...

	/**
	 * The task to be executed. Set at construction.
	 */
	private TranslationTask task;

	/**
	 * The configuration. Typically determined from the task at construction, or
	 * from the {@link FrontEnd} provided at construction.
	 */
	private Configuration configuration;

	/**
	 * The {@link FrontEnd} that will be used to actually carry out the tasks
	 * specified by {@link #task}. This is either provided to a constructor, or
	 * it is created by the constructor.
	 */
	private FrontEnd frontEnd;

	/**
	 * Where to send output; copy of what's in {@link #task} for convenience.
	 */
	private PrintStream out;

	/**
	 * Print a lot of information? Copy of what's in {@link #task} for
	 * convenience.
	 */
	private boolean verbose;

	/**
	 * Report timing information? Copy of what's in {@link #task} for
	 * convenience.
	 */
	private boolean showTime;

	/**
	 * The {@link Timer} that will be used to take timings. If {@link #showTime}
	 * is <code>false</code>, this will be a (non-<code>null</code>) trivial
	 * {@link Timer} that does nothing.
	 */
	private Timer timer;

	/**
	 * The results of preprocessing the input source units specified in the
	 * {@link #task}. The length of this array is the number of {@link UnitTask}
	 * s specified in the {@link #task}. Initially every entry is
	 * <code>null</code>; they are filled in as the unit tasks are executed
	 * through the preprocessing stage. Note that these sources have state: once
	 * they have been consumed their next token methods will just return EOF
	 * forever.
	 */
	private CivlcTokenSource[] tokenSources = null;

	/**
	 * The results of parsing the preprocessor output for each source unit. The
	 * length of this array is the number of {@link UnitTask}s specified in the
	 * {@link #task}. Initially every entry is <code>null</code>; they are
	 * filled in as the unit tasks are executed through the parsing stage.
	 */
	private ParseTree[] parseTrees = null;

	/**
	 * The ASTs for the translation units. The length of this array is the
	 * number of {@link UnitTask}s specified in the {@link #task}. Initially
	 * every entry is <code>null</code>; they are filled in as the unit tasks
	 * are executed through the AST-building stage.
	 */
	private AST[] asts = null;

	/**
	 * The complete program. Initially null, this is filled in after linking and
	 * further modified after executing transformations.
	 */
	private Program program = null;

	// Constructors...

	/**
	 * <p>
	 * Constructs new executor for executing specified task, using given front
	 * end. The front end and the task must be consistent: the values returned
	 * by methods {@link TranslationTask#getSVCOMP()} and
	 * {@link TranslationTask#getArchitecture()} on <code>task</code> must be
	 * the same as the corresponding methods in the {@link Configuration} of
	 * <code>frontEnd</code>.
	 * </p>
	 * 
	 * <p>
	 * This constructor is for internal use only. Clients who want to re-use an
	 * existing front end should use method
	 * {@link ABCExecutor#newExecutor(FrontEnd, TranslationTask)}.
	 * </p>
	 * 
	 * @param frontEnd
	 *            the front end that will be used by the new executor
	 * @param task
	 *            the task that the new executor will be asked to perform
	 */
	private ABCExecutor(FrontEnd frontEnd, TranslationTask task) {
		this.frontEnd = frontEnd;
		this.configuration = frontEnd.getConfiguration();
		initialize(task);
	}

	/**
	 * Constructs new executor for performing the specified translation task.
	 * The constructor does not perform the tasks, but it creates a new
	 * {@link FrontEnd} and initializes data structures. The task itself will be
	 * executed by invoking method {@link #execute()}.
	 * 
	 * @param task
	 *            a translation task to execute
	 */
	public ABCExecutor(TranslationTask task) {
		this.configuration = Configurations.newMinimalConfiguration();
		this.configuration.setSVCOMP(task.getSVCOMP());
		this.configuration.setArchitecture(task.getArchitecture());
		this.configuration.setGNUC(task.getGNUC());
		this.frontEnd = new FrontEnd(configuration);
		initialize(task);
	}

	// Helpers...

	/**
	 * Initializes internal data structures. To be used by constructors.
	 * 
	 * @param task
	 *            the translation task that was used as the argument to one of
	 *            the constructors
	 */
	private void initialize(TranslationTask task) {
		this.task = task;
		this.timer = task.getShowTables() ? new Timer(task.getOut())
				: new Timer();
		this.out = task.getOut();
		this.verbose = task.getVerbose();
		this.showTime = task.getShowTime();

		int numUnits = task.getUnitTasks().length;

		this.tokenSources = new CivlcTokenSource[numUnits];
		this.parseTrees = new ParseTree[numUnits];
		this.asts = new AST[numUnits];
	}

	/**
	 * Prints the program, symbol table, and type information to the given
	 * output stream in a plain-text, human-readable format.
	 */
	private void printProgram() {
		if (task.getPrettyPrint())
			program.prettyPrint(out);
		else
			program.print(out);
		if (task.getShowTables()) {
			out.println("\n\nSymbol Table:\n");
			program.printSymbolTable(out);
			out.println("\n\nTypes:\n");
			frontEnd.getTypeFactory().printTypes(out);
		}
		out.println();
		out.flush();
	}

	private void executeComparison() throws ABCException {
		assert task.getShowDiff();

		UnitTask[] unitTasks = task.getUnitTasks();
		int numUnits = unitTasks.length;

		if (numUnits != 2)
			throw new ABCException(
					"-showDiff requires exactly two source units.");

		DifferenceObject diffObj = asts[0].diff(asts[1]);

		if (diffObj == null && !task.isSilent())
			out.println("The AST of " + getName(unitTasks[0].getSourceFiles())
					+ " is equivalent to that of "
					+ getName(unitTasks[1].getSourceFiles()) + ".");
		else
			diffObj.print(out);
		out.flush();
	}

	/**
	 * Executes a single unit task.
	 * 
	 * @param index
	 *            the index of the unit task in the array of unit tasks
	 *            associated to this task
	 * @throws ABCException
	 *             if any I/O, syntax, or semantic problem arises in processing
	 *             the translation unit as specified in the unit task
	 */
	private void executeUnit(int index) throws ABCException {
		TranslationStage stage = task.getStage();
		UnitTask unitTask = task.getUnitTasks()[index];
		File[] sourceFiles = unitTask.getSourceFiles();
		String name = getName(sourceFiles);
		Language language = unitTask.getLanguage();
		Preprocessor preprocessor = frontEnd.getPreprocessor(language);
		int numFiles = sourceFiles.length;

		for (int j = 0; j < numFiles; j++) {
			File file = sourceFiles[j];
			String filename = file.getName();

			if (verbose) {
				out.println(bar + " File " + filename + " " + bar);
				try {
					ANTLRUtils.source(out, file);
				} catch (IOException e) {
					throw new ABCException("Could not open file: " + file);
				}
				out.println();
				out.flush();
			}
		}
		timer.markTime("print source for " + name);

		CivlcTokenSource tokens = preprocessor.outputTokenSource(
				unitTask.getSystemIncludes(), unitTask.getUserIncludes(),
				unitTask.getMacros(), sourceFiles);

		tokenSources[index] = tokens;
		timer.markTime("construct preprocess tree");
		if (stage == TranslationStage.PREPROCESS)
			return;
		if (stage == TranslationStage.PREPROCESS_CONSUME) {
			CommonToken token;
			int type;

			if (verbose)
				out.println(
						bar + " Preprocessor output for " + name + " " + bar);
			if (showTime) {
				do {
					token = (CommonToken) tokens.nextToken();
					type = token.getType();
				} while (type != PreprocessorParser.EOF);
				timer.markTime("preprocess " + name);
			} else {
				while (true) {
					token = (CommonToken) tokens.nextToken();
					type = token.getType();
					if (type == PreprocessorParser.EOF)
						break;
					if (type == PreprocessorParser.COMMENT)
						out.print(" ");
					else {
						if (task.getPreprocTokens()) {
							out.println(token);
						} else {
							out.print(token.getText());
						}
					}
				}
				out.println();
				out.flush();
				timer.markTime("preprocess and write " + name);
			}
			return;
		}

		// go beyond preprocessing...
		Parser parser = frontEnd.getParser(language);
		ParseTree parseTree = parser.parse(tokens);

		parseTrees[index] = parseTree;
		timer.markTime("preprocess, parse, and build ANTLR tree");
		if (verbose) {
			out.println(bar + " ANTLR Tree for " + name + " " + bar);
			ANTLRUtils.printTree(out, parseTree.getRoot());
			out.println();
			out.flush();
			timer.markTime("print ANTLR tree");
		}
		if (stage == TranslationStage.PARSE)
			return;

		ASTBuilder builder = frontEnd.getASTBuilder(language);
		AST ast = builder.getTranslationUnit(parseTree);

		asts[index] = ast;
		timer.markTime("build AST for " + name);
		if (verbose) {
			out.println(bar + " Raw Translation Unit for " + name + " " + bar);
			if (task.getPrettyPrint())
				ast.prettyPrint(out, false);
			else
				ast.print(out);
			out.println();
			out.flush();
			timer.markTime("print AST for " + name);
		}
		if (stage == TranslationStage.GENERATE_ASTS)
			return;
		if (stage == TranslationStage.ANALYZE_ASTS) {
			Analyzer analyzer = frontEnd.getStandardAnalyzer(language);

			analyzer.analyze(ast);
		}
	}

	// Public methods...

	/**
	 * Executes the complete translation task.
	 * 
	 * @throws IOException
	 *             if a file is not found
	 * @throws ABCException
	 *             if there are any problems with preprocessing or parsing, or
	 *             syntax or semantics violations in the source code
	 */
	public void execute() throws ABCException {
		int numUnits = asts.length;

		for (int i = 0; i < numUnits; i++)
			executeUnit(i);
		if (task.getShowDiff()) {
			executeComparison();
			return;
		}
		if (task.getStage().compareTo(TranslationStage.LINK) < 0)
			return;

		program = frontEnd.link(asts, task.getLinkLanguage());
		timer.markTime("link " + numUnits + " translation units");
		if (verbose) {
			out.println(bar + " Program " + bar);
			timer.markTime("print linked program");
		}
		if (task.getStage() == TranslationStage.LINK)
			return;
		for (String code : task.getTransformCodes()) {
			Transformer transformer = Transform.newTransformer(code,
					frontEnd.getASTFactory());

			if (verbose) {
				printProgram();
				out.println();
				out.println(bar + " Program after " + transformer + " " + bar);
				out.flush();
			}
			program.apply(transformer);
			timer.markTime(
					"apply transformer " + transformer.getShortDescription());
		}
		if (!showTime && !task.isSilent())
			printProgram();
		if (task.getShowUndefinedFunctions())
			printUnknownFunctions(out, program);
		if (!task.isSilent())
			frontEnd.getFileIndexer().print(out);
		out.flush();
	}

	/**
	 * Returns the {@link FrontEnd} used by this executor to carry out the
	 * components of the task.
	 * 
	 * @return the front end used by this executor
	 */
	public FrontEnd getFrontEnd() {
		return frontEnd;
	}

	/**
	 * Returns the preprocessing output token source for translation unit
	 * <code>index</code>. May be <code>null</code> if the task has not been
	 * executed. May be empty because the stream was fully consumed if the task
	 * extends beyond preprocessing only.
	 * 
	 * @param index
	 *            the index of the unit task
	 * @return the preprocessing output token source for that translation unit
	 */
	public CivlcTokenSource getTokenSource(int index) {
		return tokenSources[index];
	}

	/**
	 * Returns the parse tree for translation unit <code>index</code>. May be
	 * <code>null</code> if the task did not involve creating the parse tree.
	 * 
	 * @param index
	 *            the index of the unit task
	 * @return the parse tree for the <code>index</code>-th translation unit
	 */
	public ParseTree getParseTree(int index) {
		return parseTrees[index];
	}

	/**
	 * Returns the AST for translation unit <code>index</code>. May be
	 * <code>null</code> if the task did not involve AST construction.
	 * 
	 * @param index
	 *            the index of the unit task
	 * @return the AST for the <code>index</code>-th translation unit
	 */
	public AST getAST(int index) {
		return asts[index];
	}

	/**
	 * Returns the whole program. May be <code>null</code> if the task did not
	 * involve linking.
	 * 
	 * @return the whole program
	 */
	public Program getProgram() {
		return program;
	}

}
