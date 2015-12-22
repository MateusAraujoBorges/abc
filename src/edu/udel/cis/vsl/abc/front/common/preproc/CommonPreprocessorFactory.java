package edu.udel.cis.vsl.abc.front.common.preproc;

import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.front.IF.preproc.Preprocessor;
import edu.udel.cis.vsl.abc.front.IF.preproc.PreprocessorFactory;
import edu.udel.cis.vsl.abc.front.c.preproc.CPreprocessor;

public class CommonPreprocessorFactory implements PreprocessorFactory {

	@Override
	public Preprocessor newCPreprocessor(Configuration config) {
		return new CPreprocessor(config);
	}

}
