package edu.udel.cis.vsl.abc;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.program.IF.Program;

/**
 * Checks a number of simple C programs to make sure they pass on the parsing
 * and analysis stages, while also applying the prune and side-effect-free
 * transformations.
 * 
 * @author siegel
 * 
 */
public class OmpTranslationTest {

	/**
	 * Turn on a lot of output for debugging? Set this to true only in your
	 * local copy. Be sure to set it back to false before committing!
	 */
	private static boolean debug = false;

	private static File[] systemIncludes = new File[] { new File("examples/omp") };

	private static File[] userIncludes = new File[] { new File("examples/omp") };

	private static File root = new File("examples/omp");

	private static List<String> codes = Arrays.asList("prune", "sef");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private void check(String filenameRoot) throws ABCException, IOException {
		FrontEnd fe = new FrontEnd();
		File file = new File(root, filenameRoot + ".c");

		if (debug) {
			TranslationTask config = new TranslationTask(Language.CIVL_C, file);

			config.addAllTransformCodes(codes);
			config.setSystemIncludes(systemIncludes);
			config.setUserIncludes(userIncludes);
			fe.showTranslation(config);
		} else {
			// this code should work but fails.
			AST ast = fe.compile(file, Language.CIVL_C, systemIncludes,
					userIncludes);
			Program p = fe.getProgramFactory(
					fe.getStandardAnalyzer(Language.CIVL_C)).newProgram(ast);
			// Program p = fe.compileAndLink(new File[] { file },
			// Language.CIVL_C,
			// systemIncludes, userIncludes);

			// p.prettyPrint(System.out);
			p.applyTransformers(codes);
		}
	}

	@Test
	public void dijkstra_openmp() throws ABCException, IOException {
		check("dijkstra_openmp");
	}

	@Test
	public void dotProduct_critical() throws ABCException, IOException {
		check("dotProduct_critical");
	}

	@Test
	public void dotProduct1() throws ABCException, IOException {
		check("dotProduct1");
	}

	@Ignore
	@Test
	public void fft_openmp() throws ABCException, IOException {
		check("fft_openmp");
	}

	@Ignore
	@Test
	public void fig310_mxv_omp() throws ABCException, IOException {
		check("fig3.10-mxv-omp");
	}

	@Test
	public void fig498_threadprivate() throws ABCException, IOException {
		check("fig4.98-threadprivate");
	}

	@Test
	public void matProduct1() throws ABCException, IOException {
		check("matProduct1");
	}

	@Test
	public void matProduct2() throws ABCException, IOException {
		check("matProduct2");
	}

	@Ignore
	@Test
	public void md_openmp() throws ABCException, IOException {
		check("md_openmp");
	}

	@Test
	public void nested() throws ABCException, IOException {
		check("nested");
	}

	@Test
	public void parallel() throws ABCException, IOException {
		check("parallel");
	}

	@Test
	public void parallelfor() throws ABCException, IOException {
		check("parallelfor");
	}

	@Ignore
	@Test
	public void poisson_openmp() throws ABCException, IOException {
		check("poisson_openmp");
	}

	@Test
	public void quad_openmp() throws ABCException, IOException {
		check("quad_openmp");
	}

	@Test
	public void raceCond1() throws ABCException, IOException {
		check("raceCond1");
	}

	@Test
	public void raceCond2() throws ABCException, IOException {
		check("raceCond2");
	}

	@Test
	public void vecAdd_deadlock() throws ABCException, IOException {
		check("vecAdd_deadlock");
	}

	@Test
	public void vecAdd_fix() throws ABCException, IOException {
		check("vecAdd_fix");
	}
}