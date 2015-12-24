package edu.udel.cis.vsl.abc.front.IF.preproc;

import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCRuntimeException;
import edu.udel.cis.vsl.abc.front.c.preproc.CPreprocessor;

public class Preprocess {

	/**
	 * Returns a new Preprocessor using the default include paths. A runtime
	 * exception will be thrown if the language is not yet supported.
	 * 
	 * @param language
	 *            the language of the preprocessor
	 * @param config
	 *            the configuration of the translation task (e.g., is svcomp
	 *            enabled?)
	 * @return a new Preprocessor
	 */
	public static Preprocessor newPreprocessor(Language language,
			Configuration config) {
		switch (language) {
		case C:
		case CIVL_C:
			return new CPreprocessor(config);
		case FORTRAN77:
			return null;
		default:
			throw new ABCRuntimeException(
					"ABC doesn't support preprocessing programs in " + language
							+ ".");
		}
	}
}
