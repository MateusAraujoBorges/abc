package edu.udel.cis.vsl.abc.front.c.parse;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.abc.front.common.parse.OmpPragmaParser;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class CAcslParser implements OmpPragmaParser {

	@Override
	public CommonTree parse(Source source, TokenStream tokens)
			throws SyntaxException {
		AcslParser parser = new AcslParser(tokens);

		try {
			return (CommonTree) parser.contract().getTree();
		} catch (RecognitionException e) {
			throw new SyntaxException(e.getMessage(), null);
		}
	}
}
