package edu.udel.cis.vsl.abc.front.common.token;

import org.antlr.runtime.Token;

import edu.udel.cis.vsl.abc.front.IF.token.CToken;
import edu.udel.cis.vsl.abc.front.IF.token.Macro;
import edu.udel.cis.vsl.abc.front.IF.token.MacroExpansion;
import edu.udel.cis.vsl.abc.front.IF.token.SourceFile;

public class CommonMacroExpansion implements MacroExpansion {

	/**
	 * The token that is being expanded by the macro.
	 */
	private CToken startToken;

	/**
	 * The macro doing the expanding. Its name should be the same as the text of
	 * the startToken.
	 */
	private Macro macro;

	/**
	 * The index of the token resulting from the expansion in the list of
	 * replacement tokens for the macro. In other words, the end token is the
	 * index-th replacement token from macro's replacement list.
	 */
	private int index;

	CommonMacroExpansion(CToken startToken, Macro macro, int index) {
		this.startToken = startToken;
		this.macro = macro;
		this.index = index;
	}

	@Override
	public String suffix() {
		return " from " + startToken.toString();
	}

	@Override
	public String toString() {
		return "MacroExpansion[" + startToken.getText() + ", "
				+ macro.getName() + ", " + index + "]";
	}

	@Override
	public CToken getStartToken() {
		return startToken;
	}

	@Override
	public Macro getMacro() {
		return macro;
	}

	@Override
	public int getReplacementTokenIndex() {
		return index;
	}

	@Override
	public Token getReplacementToken() {
		return macro.getReplacementToken(index);
	}

	@Override
	public SourceFile getLastFile() {
		return macro.getFile();
	}

}