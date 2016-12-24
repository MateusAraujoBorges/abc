package edu.udel.cis.vsl.abc;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.main.ABCExecutor;
import edu.udel.cis.vsl.abc.main.FrontEnd;
import edu.udel.cis.vsl.abc.main.TranslationTask;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

/**
 * Checks a number of simple C programs to make sure they pass on the parsing
 * and analysis stages, while also applying the prune and side-effect-free
 * transformations.
 * 
 * @author siegel
 * 
 */
public class CTranslationTest {

	/**
	 * Turn on a lot of output for debugging? Set this to true only in your
	 * local copy. Be sure to set it back to false before committing!
	 */
	private static boolean debug = false;

	/**
	 * The directory in which the examples are located.
	 */
	private static File root = new File("examples");

	/**
	 * The transformations which will be applied to each example.
	 */
	private static List<String> codes = Arrays.asList("prune", "sef");

	/**
	 * Re-use a single front end for all tests in this class.
	 */
	private static FrontEnd fe = new FrontEnd(
			Configurations.newMinimalConfiguration());

	/**
	 * Compile and transform the specified example.
	 * 
	 * @param filenameRoot
	 *            the name of the source file without the ".c" suffix
	 * @throws ABCException
	 *             if anything goes wrong with compiling or transforming
	 */
	private void check(String filenameRoot) throws ABCException {
		File file = new File(root, filenameRoot + ".c");
		TranslationTask task = new TranslationTask(file);

		task.addAllTransformCodes(codes);
		task.setVerbose(debug);

		ABCExecutor executor = ABCExecutor.newExecutor(fe, task);

		executor.execute();
	}

	@Test
	public void constants() throws ABCException {
		check("constants");
	}

	@Test
	public void adder_seq() throws ABCException {
		check("adder_seq");
	}

	@Test
	public void useNull() throws ABCException {
		check("parse/useNull");
	}

	@Test
	public void pointer1() throws ABCException {
		check("parse/pointer1");
	}

	@Test
	public void pointer2() throws ABCException {
		check("parse/pointer2");
	}

	@Test
	public void varargs() throws ABCException {
		check("varargs");
	}

	@Test
	public void printf() throws ABCException {
		check("printf");
	}

	@Test
	public void compound() throws ABCException {
		check("compound");
	}

	@Test
	public void compound2() throws ABCException {
		check("compound2");
	}

	@Test
	public void enum1() throws ABCException {
		check("enum1");
	}

	@Test
	public void enum2() throws ABCException {
		check("enum2");
	}

	@Test(expected = ABCException.class)
	public void tagBad1() throws ABCException {
		check("tagBad1");
	}

	@Test
	public void tagGood1() throws ABCException {
		check("tagGood1");
	}

	@Test
	public void a2d() throws ABCException {
		check("a2d");
	}

	@Test
	public void labels() throws ABCException {
		check("labels");
	}

	@Test
	public void completeStruct() throws ABCException {
		check("completeStruct");
	}

	@Test
	public void forcomma() throws ABCException {
		check("forcomma");
	}

	@Test
	public void funcPointer() throws ABCException {
		check("funcPointer");
	}

	@Test
	public void comma() throws ABCException {
		check("comma");
	}

	@Test
	public void intChar() throws ABCException {
		check("c/intChar");
	}

	@Test(expected = ABCException.class)
	public void scanf() throws ABCException {
		check("c/scanf");
	}

	@Test
	public void fscanf() throws ABCException {
		check("c/fscanf");
	}

	@Test(expected = SyntaxException.class)
	public void printfBad() throws ABCException {
		check("c/printfBad");
	}

	@Test
	public void chars() throws ABCException {
		check("chars");
	}

	@Test
	public void struct() throws ABCException {
		check("c/struct");
	}

	@Test(expected = SyntaxException.class)
	public void assigns() throws ABCException {
		check("c/assigns");
	}

	@Test
	public void identifier() throws ABCException {
		check("c/ident");
	}

	@Test
	public void identifier2() throws ABCException {
		check("c/ident2");
	}

	@Test
	public void funcDecls() throws ABCException {
		check("c/funcs");
	}

	@Test
	public void anon() throws ABCException {
		check("c/anon");
	}

	@Test(expected = SyntaxException.class)
	public void anon_bad() throws ABCException {
		check("c/anon_bad");
	}

	@Test(expected = SyntaxException.class)
	public void incompatibleFunctions() throws ABCException {
		check("c/incompatibleFunctions");
	}

	@Test
	public void small_pragma() throws ABCException {
		check("small_pragma");
	}
}
