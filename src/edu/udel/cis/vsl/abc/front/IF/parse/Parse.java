package edu.udel.cis.vsl.abc.front.IF.parse;

import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCRuntimeException;
import edu.udel.cis.vsl.abc.front.c.parse.ImplCParser;
import edu.udel.cis.vsl.abc.front.c.parse.COmpParser;
import edu.udel.cis.vsl.abc.token.IF.CToken;

/**
 * The entry point for the parse module, this class provides static method(s)
 * only to produce new instances of {@link CParser}. This is a simple
 * implementation of the Factory Pattern.
 * 
 * @author siegel
 * 
 */
public class Parse {

	/**
	 * Creates a new instance of a {@link CParser} using the given source of
	 * tokens. It is unspecified whether the parsing process will begin
	 * immediately with the creation of the new parser, or whether it will begin
	 * only when some other method in the parser is invoked. This method throws
	 * a runtime exception if the given language is not supported yet.
	 * 
	 * @param source
	 *            the token source, an abstraction specifying the sequence of
	 *            {@link CToken}s that are to be parsed
	 * @return the new {@link CParser}
	 */
	public static Parser newParser(Language language) {
		switch (language) {
		case C:
		case CIVL_C:
			return new ImplCParser();
		case FORTRAN77:
			return null;
		default:
			throw new ABCRuntimeException(
					"ABC doesn't support parsing programs in " + language + ".");
		}
	}

	public static OmpPragmaParser newOmpPragmaParser(Language language) {
		switch (language) {
		case C:
		case CIVL_C:
			return new COmpParser();
		case FORTRAN77:
		default:
			throw new ABCRuntimeException(
					"ABC doesn't support parsing programs in " + language + ".");
		}
	}

	// public static CParser newCParser(RuleKind rule, CTokenSource source,
	// int startTokenIndex, int lastTokenIndex) {
	// return new CommonCParser(rule, source, startTokenIndex, lastTokenIndex);
	// }
}
