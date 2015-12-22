package edu.udel.cis.vsl.abc;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import edu.udel.cis.vsl.abc.FrontEnd.FrontEndKind;
import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.front.IF.parse.ParseException;
import edu.udel.cis.vsl.abc.front.IF.preproc.PreprocessorException;
import edu.udel.cis.vsl.abc.front.IF.token.SyntaxException;
import edu.udel.cis.vsl.abc.program.IF.Program;

/**
 * Tests linkage issues: internal, external, or "none".
 * 
 * @author siegel
 * 
 */
public class CIVLLinkageTest {

	public final static boolean debug = false;
	private File root = new File(new File("examples"), "link");
	FrontEnd fe = new FrontEnd(FrontEndKind.C_OR_CIVL_C);

	private boolean compileAndLink(String[] filenames, File[] systemIncludePaths)
			throws ParseException, SyntaxException, PreprocessorException {
		File files[] = new File[filenames.length];
		Program program;

		for (int i = 0; i < filenames.length; i++) {
			files[i] = new File(root, filenames[i]);
		}
		try {
			program = fe.compileAndLink(files, Language.CIVL_C,
					systemIncludePaths, ABC.DEFAULT_USER_INCLUDE_PATHS,
					ABC.DEFAULT_IMPLICIT_MACROS);
		} catch (Exception ex) {
			if (debug)
				throw ex;
			else
				System.out.println(ex.toString());
			return false;
		}
		if (debug)
			program.prettyPrint(System.out);
		return true;
	}

	@Test
	public void barrier() throws ParseException, SyntaxException,
			PreprocessorException {
		assertTrue(compileAndLink(new String[] { "barrier/barrier.cvl",
				"barrier/concurrency.cvl" }, new File[] { new File(root,
				"barrier") }));
	}

	@Test
	public void messageUnpack() throws ParseException, SyntaxException,
			PreprocessorException {
		assertTrue(compileAndLink(new String[] { "comm/messageUnpack.cvl",
				"comm/comm.cvl" }, new File[] { new File(root, "comm") }));
	}

}
