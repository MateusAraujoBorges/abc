package edu.udel.cis.vsl.abc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.analysis.dataflow.ControlFlowAnalysis;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.front.IF.ParseException;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.main.FrontEnd;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

/**
 * Checks a number of simple C programs to make sure they pass on the control
 * flow graph construction analyzer.
 * 
 * @author dwyer
 * 
 */
public class ControlFlowGraphTest {

	/**
	 * Turn on a lot of output for debugging? Set this to true only in your
	 * local copy. Be sure to set it back to false before committing!
	 */
	private static boolean debug = false;

	private static File root = new File(new File("examples"), "c");

	private static Configuration config = Configurations
			.newMinimalConfiguration();

	private static FrontEnd fe = new FrontEnd(config);

	private static ControlFlowAnalysis cfa;

	private AST ast;

	@Before
	public void setUp() throws Exception {
		cfa = ControlFlowAnalysis.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		cfa.clear();
	}

	private AST getAST(File file)
			throws ParseException, SyntaxException, PreprocessorException {
		AST ast = fe.compile(new File[] { file }, Language.C, new File[0],
				new File[0], new HashMap<String, String>());
		return ast;
	}

	private void check(String filenameRoot) throws ABCException, IOException {
		File file = new File(root, filenameRoot + ".c");
		ast = getAST(file);

		cfa.analyze(ast);

		if (debug) {
			cfa.printControlFlowGraph(ast);
		}
	}

	@Test
	public void nestedblocks() throws ABCException, IOException {
		check("nestedblocks");
	}

	@Test
	public void ifthen() throws ABCException, IOException {
		check("ifthen");
	}

	@Test
	public void loops() throws ABCException, IOException {
		check("loops");
	}

	@Test
	public void switches() throws ABCException, IOException {
		check("switches");
	}

	@Test
	public void switchloop() throws ABCException, IOException {
		check("switchloop");
	}

	@Test
	public void jumps() throws ABCException, IOException {
		check("jumps");
	}

	@Ignore
	@Test
	public void branchconst() throws ABCException, IOException {
		check("branchconst");
	}

}
