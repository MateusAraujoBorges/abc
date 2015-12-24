package edu.udel.cis.vsl.abc.front.IF.parse;

import java.util.Stack;

import edu.udel.cis.vsl.abc.front.IF.ptree.ParseTree;
import edu.udel.cis.vsl.abc.front.c.parse.ScopeSymbols;
import edu.udel.cis.vsl.abc.token.IF.CTokenSource;

public interface Parser {
	public static enum RuleKind {
		TRANSLATION_UNIT, BLOCK_ITEM
	}

	/**
	 * Returns the parse tree resulting from parsing the input, after some
	 * "post-processing" has been done to the tree to fill in some fields.
	 * 
	 * @return the parse tree resulting from parsing and clean up
	 * @throws ParseException
	 *             if there is a syntax exception
	 */
	ParseTree parse(CTokenSource tokenSource) throws ParseException;

	/**
	 * Uses a certain rule to parse the given tokens.
	 * 
	 * @param rule
	 *            The rule to be used for parsing
	 * @param tokenSource
	 *            The tokens to be parsed.
	 * @param symbols
	 *            The stack of symbols defined or visible in the current scope
	 * @return The parse tree.
	 * @throws ParseException
	 */
	ParseTree parse(RuleKind blockItem, CTokenSource tokens,
			Stack<ScopeSymbols> scopeSymbolStack) throws ParseException;
}
