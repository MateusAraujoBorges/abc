package edu.udel.cis.vsl.abc.front.IF.parse;

import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public interface OmpPragmaParser {
	CommonTree parse(Source source, TokenStream tokens) throws SyntaxException;
}
