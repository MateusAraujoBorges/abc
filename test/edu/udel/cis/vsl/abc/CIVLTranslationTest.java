package edu.udel.cis.vsl.abc;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer;
import edu.udel.cis.vsl.abc.main.ABCExecutor;
import edu.udel.cis.vsl.abc.main.TranslationTask;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.util.IF.ANTLRUtils;
import edu.udel.cis.vsl.abc.util.IF.ANTLRUtils.LexerFactory;

public class CIVLTranslationTest {

	private static boolean debug = false;

	private static PrintStream out = System.out;

	private static List<String> codes = Arrays.asList("prune", "sef");

	private File root = new File(new File("examples"), "civl");

	private static LexerFactory lf = new LexerFactory() {

		@Override
		public Lexer makeLexer(CharStream stream) {
			return new PreprocessorLexer(stream);
		}

	};

	private void lex(String name) throws IOException {
		String filename = (new File(root, name)).getAbsolutePath();
		PrintStream lexOut = debug ? out : new PrintStream(new OutputStream() {
			public void write(int b) {
				// DO NOTHING
			}
		});

		ANTLRUtils.lex(lexOut, lf, filename);
	}

	private void checkFile(String filename) throws ABCException {
		File file = new File(root, filename);
		TranslationTask task = new TranslationTask(file);

		task.addAllTransformCodes(codes);
		task.setVerbose(debug);
		ABCExecutor.execute(task);
	}

	private void check(String filenameRoot) throws ABCException {
		checkFile(filenameRoot + ".cvl");
	}

	@Test
	public void assertForall() throws ABCException, IOException {
		check("forall");
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

	@Test(expected = SyntaxException.class)
	public void parfor_bad() throws ABCException, IOException {
		check("parfor_bad");
	}

	@Test(expected = SyntaxException.class)
	public void parfor_bad2() throws ABCException, IOException {
		check("parfor_bad2");
	}

	@Test
	public void domainDecomp() throws ABCException, IOException {
		check("domainDecomposition");
	}

	@Test
	public void cond() throws ABCException, IOException {
		check("cond");
	}

	@Test
	public void externalDefs() throws ABCException, IOException {
		check("externaldefs");
	}

	@Test
	public void systemFunction() throws ABCException, IOException {
		check("systemFunction");
	}

	@Test(expected = SyntaxException.class)
	public void sysLibraryBad() throws ABCException, IOException {
		check("sysLibraryBad");
	}

	@Test
	public void quantifiers() throws ABCException, IOException {
		check("quantifiers");
	}

	@Test
	public void arrayLambda() throws ABCException, IOException {
		check("lambda");
	}

	@Test
	public void lex_test() throws ABCException {
		checkFile("text.c");
	}
}
