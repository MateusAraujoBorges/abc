package edu.udel.cis.vsl.abc.fortran;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.udel.cis.vsl.abc.analysis.IF.Analysis;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.main.FrontEndF;

public class SetupTest {
	private static String ARG_VERBOSE = "--verbose";
	private static String ARG_TOKENS = "--tokens";
	private static String ARG_TREE = "--tree";

	private static String BASEPATH = new File("").getAbsolutePath().concat(
			"/examples/fortran/");

	private static String ARG_INCLUSION_BASE = "-I" + BASEPATH;
	private static String ARG_INCLUSION_MXM = ARG_INCLUSION_BASE
			.concat("proversa/MXM/");
	private static String ARG_INCLUSION_SIMPLE = ARG_INCLUSION_BASE
			.concat("simple-test/");

	private static String ARG_FILE_SIMPLE = BASEPATH
			.concat("simple-test/simpleSample.f");
	private static String ARG_FILE_PROVERSA_MP1 = BASEPATH
			.concat("proversa/MINPACK/ssqfcn.f");
	private static String ARG_FILE_PROVERSA_MP2 = BASEPATH
			.concat("proversa/MINPACK/ssqjac.f");
	private static String ARG_FILE_PROVERSA_MXM1a = BASEPATH
			.concat("proversa/MXM/ex1a.F");

	private static String TEST_FILE = BASEPATH.concat("simple-test/simpleSample.f");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void printFortranTokens() throws Exception {
		String[] args = { ARG_TOKENS, TEST_FILE };

		FrontEndF.main(args);
	}

	@Test
	public void printFortranParseSequence() throws Exception {
		String[] args = { ARG_VERBOSE, TEST_FILE };

		FrontEndF.main(args);
	}

	@Test
	public void printFortranParseTree() throws Exception {
		String[] args = { ARG_VERBOSE, ARG_TREE, TEST_FILE };

		AST newAST = FrontEndF.main(args);
		Configuration config = Configurations.newMinimalConfiguration();
		Analysis.performStandardAnalysis(Language.FORTRAN77, config, newAST);
		System.out.println("AbstractSyntaxTree:");
		newAST.print(System.out);
		System.out.println("\n\nCIVLC Representation:");
		newAST.prettyPrint(System.out, false);
	}
}