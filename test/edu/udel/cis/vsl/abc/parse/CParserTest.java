package edu.udel.cis.vsl.abc.parse;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.tree.CommonTree;
import org.junit.Test;

import edu.udel.cis.vsl.abc.front.IF.parse.CParser;
import edu.udel.cis.vsl.abc.front.IF.parse.Parse;
import edu.udel.cis.vsl.abc.front.IF.parse.ParseException;
import edu.udel.cis.vsl.abc.front.IF.preproc.Preprocess;
import edu.udel.cis.vsl.abc.front.IF.preproc.Preprocessor;
import edu.udel.cis.vsl.abc.front.IF.preproc.PreprocessorException;
import edu.udel.cis.vsl.abc.front.IF.token.Macro;
import edu.udel.cis.vsl.abc.front.c.ptree.CParseTree;
import edu.udel.cis.vsl.abc.util.IF.ANTLRUtils;

public class CParserTest {

	private static boolean debug = false;

	private static Preprocessor preprocessor = Preprocess
			.newPreprocessorFactory().newCPreprocessor(null);

	private static PrintStream out = System.out;

	private static File root = new File(new File("examples"), "parse");

	private static File[] systemIncludes = new File[] {};

	private static File[] userIncludes = new File[] { new File(
			System.getProperty("user.dir")) };

	private static Map<String, Macro> implicitMacros = new HashMap<>();

	private void check(String filenameRoot) throws PreprocessorException,
			ParseException {
		File file = new File(root, filenameRoot + ".c");
		CParser parser = Parse.newCParser();
		CParseTree parseTree = parser.parse(preprocessor.outputTokenSource(
				systemIncludes, userIncludes, implicitMacros, file));
		CommonTree tree = parseTree.getRoot();

		if (debug)
			ANTLRUtils.printTree(out, tree);
	}

	@Test
	public void if1() throws PreprocessorException, ParseException {
		check("if1");
	}
}
