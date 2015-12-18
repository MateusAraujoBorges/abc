package edu.udel.cis.vsl.abc.front.common.token;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;

import edu.udel.cis.vsl.abc.front.IF.token.CToken;
import edu.udel.cis.vsl.abc.front.IF.token.Formation;
import edu.udel.cis.vsl.abc.front.IF.token.SourceFile;
import edu.udel.cis.vsl.abc.front.IF.token.TokenUtils;

/**
 * An extension to ANTLR's generic Token implementation that adds fields to
 * represent the origin of a token through various preprocessing stages. A
 * history of include directives and macro invocations gives detailed
 * information on the origin of the token.
 * 
 * Every token originated as some token in a source file. (Ignoring for now
 * token creation by the preprocessor using '##'.)
 * 
 * These tokens have a next field, so that they can be given the structure of a
 * linked list. Yes, I could have used Java's LinkedList class, but I don't
 * think that supported certain operations efficiently, such as splicing one
 * list into another in constant time.
 * 
 * @author Stephen F. Siegel, University of Delaware, All rights reserved
 * 
 */
public class CommonCToken extends CommonToken implements CToken {

	// Fields...

	/**
	 * Eclipse made me do it.
	 */
	private static final long serialVersionUID = -5508021210762735784L;

	private Formation formation;

	/**
	 * If this is an identifier node and that identifier is for a macro, setting
	 * this bit to false means that the macro will not be expanded. This is
	 * needed for the complex macro expansion policy described in the C99
	 * Standard.
	 */
	private boolean expandable = true;

	/**
	 * The CppTokens emanating from a CppTokenSource form a linked list. This is
	 * the next element in the list.
	 */
	private CToken next = null;

	/**
	 * Index of this token in the list of tokens emanating from CppTokenSource.
	 * First token has index 0.
	 * 
	 * You can't trust CommonToken's index. It gets set to weird values by
	 * things I don't understand.
	 */
	private int tokenIndex = -1;

	// Constructors...

	/**
	 * Creates new instance by copying fields from old one. The two histories
	 * are set to the given arguments. Both must be non-null.
	 * 
	 * @param token
	 *            any kind of Token
	 */
	public CommonCToken(Token token, Formation formation) {
		super(token);
		assert formation != null;
		this.formation = formation;
	}

	public CommonCToken(int type, String text, Formation formation) {
		super(type, text);
		this.formation = formation;
	}

	// Methods...

	/**
	 * Returns the more technical string representation used in the parent class
	 * CommonToken. Useful for debugging, not so good for the end user, and does
	 * not include the extra information provided in this class, such as macro
	 * and include histories.
	 * 
	 * @return technical string representation of the token.
	 */
	public String toStringOld() {
		return super.toString();
	}

	/**
	 * Returns a string representation that is appropriate for reporting this
	 * token and its source to the end user. It includes the text of the token,
	 * source file, line and character index, history through macro expansion
	 * and include directives.
	 */
	@Override
	public String toString() {
		if (this.getType() == Token.EOF)
			return "EOF";
		else {
			// String result = TokenUtils.quotedText(this) + " in "
			// + TokenUtils.location(this, false);
			String result = TokenUtils
					.summarizeRangeLocation(this, this, false)
					+ " "
					+ TokenUtils.quotedText(this);

			if (formation != null)
				result += formation.suffix();
			return result;
		}
	}

	/**
	 * Is this token expandable? This is used only for identifiers that could be
	 * macro invocations. The macro expansion procedure is complex and at a
	 * certain phase, a macro identifier becomes non-expandable. It is mostly to
	 * support self-referential macros.
	 * 
	 * Initially, every token is expandable.
	 * 
	 * @return value of expandable bit
	 */
	@Override
	public boolean isExpandable() {
		return expandable;
	}

	/**
	 * Sets this token's expandable bit to false.
	 */
	@Override
	public void makeNonExpandable() {
		expandable = false;
	}

	/**
	 * Set this token's "next" field to the given token. This is used to give
	 * tokens the structure of a linked list. Initially, this is null.
	 * 
	 * @param nextToken
	 */
	@Override
	public void setNext(CToken nextToken) {
		this.next = nextToken;
	}

	/**
	 * Get this token's "next" field. Could be null.
	 * 
	 * @return the next token
	 */
	@Override
	public CToken getNext() {
		return next;
	}

	@Override
	public void setIndex(int index) {
		this.tokenIndex = index;
	}

	@Override
	public int getIndex() {
		return tokenIndex;
	}

	@Override
	public SourceFile getSourceFile() {
		return formation.getLastFile();
	}

	@Override
	public Formation getFormation() {
		return formation;
	}

}