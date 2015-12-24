package edu.udel.cis.vsl.abc.front.IF.astgen;

import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCRuntimeException;
import edu.udel.cis.vsl.abc.front.c.astgen.CASTBuilder;

/**
 * A factory class for producing new instances of {@link ASTBuilder} and
 * {@link OmpBuilder} and for using those classes in some common scenarios.
 * 
 * @author siegel
 * 
 */
public class ASTGenerator {

	/**
	 * Creates an AST builder for the given language. A runtime exception is
	 * thrown if the language is not yet supported.
	 * 
	 * @param language
	 * @param configuration
	 * @param astFactory
	 * @return
	 */
	public static ASTBuilder newASTBuilder(Language language,
			Configuration configuration, ASTFactory astFactory) {
		switch (language) {
		case C:
		case CIVL_C:
			return new CASTBuilder(configuration, astFactory);
		case FORTRAN77:
			return null;
		default:
			throw new ABCRuntimeException(
					"ABC doesn't support generating AST for programs written in "
							+ language + ".");
		}
	}

}
