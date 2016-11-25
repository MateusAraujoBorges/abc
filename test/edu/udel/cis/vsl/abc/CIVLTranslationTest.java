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

	@SuppressWarnings("unused")
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
		task.setPrettyPrint(true);
		task.setVerbose(debug);
		ABCExecutor.execute(task);
	}

	private void check(String filenameRoot) throws ABCException {
		checkFile(filenameRoot + ".cvl");
	}

	@Test
	public void assertForall() throws ABCException {
		check("forall");
	}

	@Test
	public void spawn() throws ABCException {
		check("spawn");
	}

	@Test
	public void choose() throws ABCException {
		check("choose");
	}

	@Test
	public void duffs() throws ABCException {
		check("duffs");
	}

	@Test
	public void sideEffects() throws ABCException {
		check("sideEffects");
	}

	@Test
	public void malloc() throws ABCException, IOException {
		check("malloc");
	}

	// Ignoring because scope-qualified pointers are going away
	@Ignore
	@Test
	public void pointerScopes() throws ABCException {
		check("pointerScopes");
	}

	@Test
	public void atomicBlock() throws ABCException {
		check("atomicStatement");
	}

	@Test
	public void potentialBug() throws ABCException {
		check("potentialBug");
	}

	@Test
	public void nestedFunctions() throws ABCException {
		check("nestedFunctions");
	}

	@Test
	public void domain() throws ABCException {
		check("domain");
	}

	@Test
	public void dollarFor() throws ABCException {
		check("dollarFor");
	}

	@Test
	public void parfor() throws ABCException {
		check("parfor");
	}

	@Test(expected = SyntaxException.class)
	public void parfor_bad() throws ABCException {
		check("parfor_bad");
	}

	@Test(expected = SyntaxException.class)
	public void parfor_bad2() throws ABCException {
		check("parfor_bad2");
	}

	@Test
	public void domainDecomp() throws ABCException {
		check("domainDecomposition");
	}

	@Test
	public void cond() throws ABCException {
		check("cond");
	}

	@Test
	public void externalDefs() throws ABCException {
		check("externaldefs");
	}

	@Test
	public void systemFunction() throws ABCException {
		check("systemFunction");
	}

	@Test(expected = SyntaxException.class)
	public void sysLibraryBad() throws ABCException {
		check("sysLibraryBad");
	}

	@Test
	public void quantifiers() throws ABCException {
		check("quantifiers");
	}

	@Test
	public void arrayLambda() throws ABCException {
		check("lambda");
	}

	@Test
	public void with() throws ABCException {
		check("with");
	}

	@Test
	public void update() throws ABCException {
		check("update");
	}

	@Test
	public void valueat() throws ABCException {
		check("valueat");
	}

	@Test
	public void defnScopes() throws ABCException {
		check("defnScopes");
	}

	@Test(expected = SyntaxException.class)
	public void lex_test() throws ABCException {
		checkFile("text.c");
	}
}
