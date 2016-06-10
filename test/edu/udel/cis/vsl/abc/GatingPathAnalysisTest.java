package edu.udel.cis.vsl.abc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.analysis.common.CallAnalyzer;
import edu.udel.cis.vsl.abc.analysis.dataflow.GatingPathAnalysis;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.main.FrontEnd;
import edu.udel.cis.vsl.abc.token.IF.Macro;

/**
 * Checks a number of simple C programs to assess gating path computation.
 * 
 * @author dwyer
 * 
 */
@Ignore("Still running unit tests")
public class GatingPathAnalysisTest {

	/**
	 * Turn on a lot of output for debugging? Set this to true only in your
	 * local copy. Be sure to set it back to false before committing!
	 */
	private static boolean debug = true;

	private static File root = new File(new File("examples"), "c");

	private static Configuration config = Configurations
			.newMinimalConfiguration();

	private static FrontEnd fe = new FrontEnd(config);
	
	private static GatingPathAnalysis gp;
		
	@Before
	public void setUp() throws Exception {	
		gp = GatingPathAnalysis.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		gp.clear();
	}

	private void check(String filenameRoot) throws ABCException, IOException {
		File file = new File(root, filenameRoot + ".c");
		AST ast = fe.compile(file, Language.C, new File[0], new File[0],
				new HashMap<String, Macro>());
		
		// Call graph construction is a standard analysis
		for (Function f : CallAnalyzer.functions(ast)) {
			gp.analyze(f);	
		}		
		
		if (debug) {
			System.out.println(gp.getResultString());
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
	
	@Test
	public void matprod() throws ABCException, IOException {
		check("matprod");
	}

}