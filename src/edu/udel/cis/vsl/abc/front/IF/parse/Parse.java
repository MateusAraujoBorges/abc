package edu.udel.cis.vsl.abc.front.IF.parse;

import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCRuntimeException;
import edu.udel.cis.vsl.abc.front.c.parse.CParser;

/**
 * The entry point for the parse module, this class provides static method(s)
 * only to produce new instances of {@link Parser}. This is a simple
 * implementation of the Factory Pattern.
 * 
 * @author siegel
 * 
 */
public class Parse {

	/**
	 * Creates a new instance of a {@link Parser} for the given language. This
	 * method throws a runtime exception if the given language is not supported
	 * yet.
	 * 
	 * @return the new {@link Parser}
	 */
	public static Parser newParser(Language language) {
		switch (language) {
		case C:
		case CIVL_C:
			return new CParser();
		case FORTRAN77:
			return null;
		default:
			throw new ABCRuntimeException(
					"ABC doesn't support parsing programs in " + language + ".");
		}
	}
}
