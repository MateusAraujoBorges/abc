package edu.udel.cis.vsl.abc.front.IF.preproc;

import edu.udel.cis.vsl.abc.front.c.preproc.CommonPreprocessorFactory;

public class Preprocess {

	public static PreprocessorFactory newPreprocessorFactory() {
		return new CommonPreprocessorFactory();
	}

}
