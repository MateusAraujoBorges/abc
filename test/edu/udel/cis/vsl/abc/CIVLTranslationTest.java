package edu.udel.cis.vsl.abc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;

public class CIVLTranslationTest {

	private static boolean debug = false;

	private static File[] systemIncludes = new File[0];

	private static File[] userIncludes = new File[0];

	private static PrintStream out = System.out;

	private static List<String> codes = Arrays.asList("prune", "sef");

	private File root = new File(new File("examples"), "civl");

	private void check(String filenameRoot) throws ABCException, IOException {
		Activator a = ABC.activator(new File(root, filenameRoot + ".cvl"),
				systemIncludes, userIncludes);

		if (debug)
			a.showTranslation(out, codes);
		else {
			a.getProgram().applyTransformers(codes);
		}
	}

	@Test
	public void spawn() throws ABCException, IOException {
		check("spawn");
	}

	@Test
	public void choose() throws ABCException, IOException {
		check("choose");
	}

	@Test
	public void duffs() throws ABCException, IOException {
		check("duffs");
	}

	@Test
	public void sideEffects() throws ABCException, IOException {
		check("sideEffects");
	}

	@Test
	public void malloc() throws ABCException, IOException {
		check("malloc");
	}

	// Ignoring because scope-qualified pointers are going away
	@Ignore
	@Test
	public void pointerScopes() throws ABCException, IOException {
		check("pointerScopes");
	}

	@Test
	public void atomicBlock() throws ABCException, IOException {
		check("atomicStatement");
	}

	@Test
	public void potentialBug() throws ABCException, IOException {
		check("potentialBug");
	}

	// ignoring because for now CIVL follows the C11 Standard
	// in that all functions have internal or external linkage
	@Ignore
	@Test
	public void nestedFunctions() throws ABCException, IOException {
		check("nestedFunctions");
	}

	@Test
	public void domain() throws ABCException, IOException {
		check("domain");
	}

	@Test
	public void dollarFor() throws ABCException, IOException {
		check("dollarFor");
	}

	@Test
	public void parfor() throws ABCException, IOException {
		check("parfor");
	}
}
