package edu.udel.cis.vsl.abc;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.main.ABCExecutor;
import edu.udel.cis.vsl.abc.main.FrontEnd;
import edu.udel.cis.vsl.abc.main.TranslationTask;

public class ContractTest {

	private static boolean debug = false;

	private static List<String> codes = Arrays.asList("prune", "sef");

	private File root = new File(new File("examples"), "contract");

	private static Configuration config = Configurations.newMinimalConfiguration();

	private static FrontEnd f = new FrontEnd(config);

	private void check(String filenameRoot) throws ABCException, IOException {
		File file = new File(root, filenameRoot + ".cvl");
		TranslationTask task = new TranslationTask(file);

		task.addAllTransformCodes(codes);
		task.setVerbose(debug);
		task.setSilent(!debug);
		if (debug)
			task.setPrettyPrint(true);
		ABCExecutor.execute(f, task);
	}

	@Test
	public void por() throws ABCException, IOException {
		check("por");
	}

	@Test
	public void por2() throws ABCException, IOException {
		check("por2");
	}

	@Test
	public void contracts() throws ABCException, IOException {
		check("contracts");
	}

	@Test
	public void stateNull() throws ABCException, IOException {
		check("stateNull");
	}

	@Test
	public void stateVar() throws ABCException, IOException {
		check("stateVar");
	}
}
