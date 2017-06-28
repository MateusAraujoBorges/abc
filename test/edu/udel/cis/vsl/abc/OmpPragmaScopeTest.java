package edu.udel.cis.vsl.abc;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.main.ABCExecutor;
import edu.udel.cis.vsl.abc.main.FrontEnd;
import edu.udel.cis.vsl.abc.main.TranslationTask;

/**
 * Adopted from OmpTranslationTest.java
 * 
 * Checks a number of simple C programs to make sure they pass on the parsing
 * and analysis stages, while also applying the prune transformations.
 * @author siegel
 * 
 * ...
 * 
 * These tests also exposes the problem of having continuous pragmas and a
 * following statement without curly brackets. The appearance of pragmas are
 * treated as descriptives to following statement. Thus, the continuous pragmas
 * are combined with a following statement to enforce the scoping rule in C.
 * 
 * @author dxu
 * 
 */
public class OmpPragmaScopeTest {

	/**
	 * Turn on a lot of output for debugging? Set this to true only in your
	 * local copy. Be sure to set it back to false before committing!
	 */
	private static boolean debug = false;

	private static File root = new File(new File("examples"), "omp");

	private static List<String> codes = Arrays.asList("prune");

	private static Configuration config = Configurations
			.newMinimalConfiguration();

	private static FrontEnd fe = new FrontEnd(config);

	private void check(String filenameRoot) throws ABCException {
		File file = new File(root, filenameRoot + ".c");
		TranslationTask task = new TranslationTask(file);

		task.addAllTransformCodes(codes);
		task.setVerbose(debug);
		ABCExecutor.execute(fe, task);
	}

	@Test
	public void pragmaScope() throws ABCException {
		check("pragmaScope");
	}

	@Test
	public void pragmaScope2() throws ABCException {
		check("pragmaScope2");
	}


}
