package edu.udel.cis.vsl.abc;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.PrintStream;

import org.junit.Test;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.main.ABCExecutor;
import edu.udel.cis.vsl.abc.main.FrontEnd;
import edu.udel.cis.vsl.abc.main.TranslationTask;
import edu.udel.cis.vsl.abc.program.IF.Program;

public class PruneTest {

	public final static PrintStream out = System.out;

	public final static boolean debug = true;

	private File root = new File("examples/prune");

	private static Configuration config = Configurations
			.newMinimalConfiguration();

	private static FrontEnd fe = new FrontEnd(config);

	private void check(File[] inputs, File oracle) throws ABCException {
		TranslationTask task = new TranslationTask(inputs);

		task.addTransformCode("prune");

		ABCExecutor executor = ABCExecutor.execute(fe, task);
		Program program = executor.getProgram();
		AST actual = program.getAST();
		AST expected = fe.compile(new File[] { oracle }, Language.C);
		if (actual.getRootNode().equiv(expected.getRootNode())) {
			// OK
		} else {
			if (debug) {
				out.println("Expected:");
				expected.prettyPrint(out, false);
				out.println();
				out.println("Actual:");
				actual.prettyPrint(out, false);
			}
			assertTrue(false);
		}
	}

	private void check(String[] inputNames, String oracleName)
			throws ABCException {
		File[] inputs = new File[inputNames.length];
		File oracle;

		for (int i = 0; i < inputs.length; i++)
			inputs[i] = new File(root, inputNames[i]);
		oracle = new File(root, oracleName);
		check(inputs, oracle);

	}

	@Test
	public void structs1() throws ABCException {
		check(new String[] { "structs1.c" }, "structs1_pruned.c");
	}

	@Test
	public void function() throws ABCException {
		check(new String[] { "func.c" }, "func_pruned.c");
	}

	@Test
	public void structsInFunction() throws ABCException {
		check(new String[] { "structsInFunction.cvl" },
				"structsInFunction_pruned.cvl");
	}

	@Test
	public void structsInFunction1() throws ABCException {
		check(new String[] { "structsInFunction1.cvl" },
				"structsInFunction_pruned.cvl");
	}

	@Test
	public void functionDef() throws ABCException {
		check(new String[] { "functionDef.c" }, "functionDef_pruned.c");
	}

}
