package edu.udel.cis.vsl.abc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.analysis.common.CallAnalyzer;
import edu.udel.cis.vsl.abc.analysis.dataflow.ConditionalConstantPropagation;
import edu.udel.cis.vsl.abc.analysis.dataflow.DominatorAnalysis;
import edu.udel.cis.vsl.abc.analysis.dataflow.ReachingDefinitionAnalysis;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.main.FrontEnd;
import edu.udel.cis.vsl.abc.token.IF.Macro;

/**
 * Checks a number of simple C programs to make sure basic data flow analyses work on them.
 * 
 * @author dwyer
 * 
 */
@Ignore
public class DataFlowAnalysisTest {

	/**
	 * Turn on a lot of output for debugging? Set this to true only in your
	 * local copy. Be sure to set it back to false before committing!
	 */
	private static boolean debug = false;

	private static File root = new File(new File("examples"), "c");

	private static Configuration config = Configurations
			.newMinimalConfiguration();

	private static FrontEnd fe = new FrontEnd(config);
	
	private static ReachingDefinitionAnalysis rd;
	private static DominatorAnalysis dom;
	private static ConditionalConstantPropagation ccp;

		
	@Before
	public void setUp() throws Exception {	
		rd = ReachingDefinitionAnalysis.getInstance();
		dom = DominatorAnalysis.getInstance();
		ccp = ConditionalConstantPropagation.getInstance();

	}

	@After
	public void tearDown() throws Exception {
		rd.clear();
		dom.clear();
		ccp.clear();
	}

	private void check(String filenameRoot) throws ABCException, IOException {
		File file = new File(root, filenameRoot + ".c");
		AST ast = fe.compile(file, Language.C, new File[0], new File[0],
				new HashMap<String, Macro>());
		
		// Call graph construction is a standard analysis
		for (Function f : CallAnalyzer.functions(ast)) {
			rd.analyze(f);	
			dom.analyze(f);
			ccp.analyze(f);
		}		
		if (debug) {
			System.out.println(rd.getResultString());
			System.out.println(dom.getResultString());
			System.out.println(ccp.getResultString());

			for (Function f : CallAnalyzer.functions(ast)) {
				System.out.println("Dominator tree for function "+f);
				dom.printDominatorTree(f);
			}
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
	
	@Test
	public void branchconst() throws ABCException, IOException {
		check("branchconst");
	}

}
