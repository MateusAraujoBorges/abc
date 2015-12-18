package edu.udel.cis.vsl.abc.front.c.preproc;

import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.front.IF.preproc.Preprocessor;
import edu.udel.cis.vsl.abc.front.IF.preproc.PreprocessorFactory;

public class CommonPreprocessorFactory implements PreprocessorFactory {

	@Override
	public Preprocessor newPreprocessor(Configuration config) {
		return new CommonPreprocessor(config);
	}

}
