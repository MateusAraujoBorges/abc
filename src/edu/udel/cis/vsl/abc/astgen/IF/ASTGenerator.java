package edu.udel.cis.vsl.abc.astgen.IF;

import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.astgen.c.CASTBuilder;
import edu.udel.cis.vsl.abc.front.IF.parse.CParser;

/**
 * A factory class for producing new instances of {@link ASTBuilder} and
 * {@link OmpBuilder} and for using those classes in some common scenarios.
 * 
 * @author siegel
 * 
 */
public class ASTGenerator {

	public static ASTBuilder newCASTBuilder(ASTFactory astFactory, CParser parser) {
		return new CASTBuilder(astFactory, parser);
	}

}
