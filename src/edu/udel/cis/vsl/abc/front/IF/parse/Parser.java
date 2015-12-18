package edu.udel.cis.vsl.abc.front.IF.parse;

import edu.udel.cis.vsl.abc.front.IF.ptree.ParseTree;
import edu.udel.cis.vsl.abc.front.IF.token.CTokenSource;

public interface Parser {
	/**
	 * Returns the parse tree resulting from parsing the input, after some
	 * "post-processing" has been done to the tree to fill in some fields.
	 * 
	 * @return the parse tree resulting from parsing and clean up
	 * @throws ParseException
	 *             if there is a syntax exception
	 */
	ParseTree parse(CTokenSource tokenSource) throws ParseException;
}
