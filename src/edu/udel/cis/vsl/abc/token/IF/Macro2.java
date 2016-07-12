package edu.udel.cis.vsl.abc.token.IF;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

/**
 * <p>
 * An abstract representation of a preprocessor macro. Used to represent both
 * object and function macros.
 * </p>
 * 
 * <p>
 * C11 5.1.1.2(3) states that in translation phase 3 (in which the source is
 * decomposed into preprocessing tokens): "Whether each nonempty sequence of
 * white-space characters other than new-line is retained or replaced by one
 * space character is implementation-defined." Note that in the vocabulary of
 * C11, white-space characters are not preprocessing tokens; they are used to
 * separate preprocessing tokens.
 * </p>
 * 
 * <p>
 * A {@link Macro2} object maintains the sequence of all tokens in the macro
 * definition body, including the white space tokens. This is called the body
 * token sequence. The subsequence consisting of the non-whitespace tokens is
 * knows as the replacement token sequence (in accord with the vocabulary of
 * C11). To repeat: the replacement token sequence is a subsequence of the body
 * token sequence. This interface provides methods for navigating both
 * sequences.
 * </p>
 * 
 * <p>
 * The <strong>index</strong> of a token in the macro definition body is its
 * index in the body token sequence, counting from 0. Hence the body tokens have
 * indexes 0, 1, ..., numBodyTokens-1. The <strong>replacement ID</strong> of a
 * replacement token R is the number of replacement tokens that occur before R
 * in the body; hence the replacement tokens have IDs 0, 1, 2, ...,
 * numReplacementTokens-1.
 * </p>
 * 
 * @author siegel
 * 
 */
public interface Macro2 {

	/**
	 * The node in the ANTLR parse tree for the preprocessor grammar which is
	 * the root of the macro definition for this macro.
	 * 
	 * @return ANTLR tree node for the macro definition
	 */
	Tree getDefinitionNode();

	/**
	 * The node in the ANTLR parse tree which is the root of the macro body,
	 * i.e., the sequence of replacement tokens. This is a child of the
	 * definition node.
	 * 
	 * @return the ANTLR tree node for the macro body
	 */
	Tree getBodyNode();

	/**
	 * Gets the number of tokens in the macro definition body. This includes the
	 * white space tokens and the replacement tokens.
	 * 
	 * @return number of tokens in macro definition body
	 */
	int getNumBodyTokens();

	/**
	 * Returns the number of replacement tokens in the macro definition. Recall
	 * that the replacement tokens are the non-whitespace body tokens.
	 * 
	 * @see {@link Macro2}
	 * 
	 * @return the number of replacement tokens
	 */
	int getNumReplacementToken();

	/**
	 * Returns the macro name.
	 * 
	 * @return the macro name
	 */
	String getName();

	/**
	 * Returns the file in which this macro definition occurs.
	 * 
	 * @return file containing this macro definition
	 */
	SourceFile getFile();

	/**
	 * Returns the <code>index</code>-th token of the macro body. Recall the
	 * body tokens include white space tokens and replacement tokens.
	 * 
	 * @param index
	 *            integer in range [0,numBodyTokens-1]
	 * @return the <code>index</code>-th token in the macro definition body
	 * 
	 * @see {@link Macro2}
	 */
	Token getBodyToken(int index);

	/**
	 * <p>
	 * Returns the index in the body token sequence of the i-th replacement
	 * token. Recall that the replacement tokens form a subsequence of the body
	 * tokens. The replacement tokens are the non-whitespace body tokens.
	 * </p>
	 * 
	 * <p>
	 * In the example below, there are 10 tokens in the body sequence, four of
	 * which are replacement (non-whitespace) tokens.
	 * </p>
	 * 
	 * <pre>
	 * index      : 0 1 2 3 4 5 6 7 8 9
	 * non-WS     :     *     * *   * 
	 *
	 * id      : 0 1 2 3
	 * returns : 2 5 6 8
	 * </pre>
	 * 
	 * @param id
	 *            a replacement ID, an integer in [0,numReplacementTokens-1]
	 * @return the index of replacement token <code>id</code> in the body token
	 *         sequence
	 * @see {@link Macro2}
	 */
	int replacementToBody(int id);

	/**
	 * Given the <code>index</code> of a token in the body sequence, this
	 * returns the number of replacement tokens that occur strictly before
	 * position <code>index</code>. In the example below, there are 10 tokens in
	 * the body token sequence. Four of those are replacement tokens, i.e.,
	 * non-whitespace tokens.
	 * 
	 * <pre>
	 * index      : 0 1 2 3 4 5 6 7 8 9
	 * non-WS     :     *     * *   * 
	 * returns    : 0 0 0 1 1 1 2 3 3 4
	 * </pre>
	 * 
	 * <p>
	 * Note that if this method returns numReplacementTokens, then there are no
	 * replacement tokens occurring at or after position <code>index</code> in
	 * the body sequence. If this method returns a value ID that is strictly
	 * less than numReplacementTokens, then ID is the replacement ID of the
	 * first replacement token to occur at or after position <code>index</code>.
	 * </p>
	 * 
	 * @param index
	 *            integer in range [0,numBodyTokens-1]
	 * @return number of replacement tokens occurring strictly before
	 *         <code>index</code>, an integer in range [0,numReplacementTokens]
	 */
	int bodyToReplacement(int index);

	/**
	 * Gets the replacement token with the given ID, i.e., the <code>id</code>
	 * -th element of the replacement token subsequence.
	 * 
	 * @param id
	 *            a replacement token ID, an integer in
	 *            [0,numReplacementTokens-1]
	 * @return the replacement token with ID <code>id</code>
	 * @see {@link Macro2}
	 */
	default Token getReplacementToken(int id) {
		return getBodyToken(replacementToBody(id));
	}

	/**
	 * Gets the index of the first replacement token that occurs strictly after
	 * the body token with index <code>index</code>.
	 * 
	 * @param index
	 *            an index of a body token, an integer in [0,numBodyTokens-1]
	 * @return the index of the first replacement token that occurs strictly
	 *         after the <code>index</code>-th body token, or <code>-1</code> if
	 *         there is no such replacement token
	 */
	default int getNextReplacementIndex(int index) {
		int numTokens = getNumBodyTokens();

		if (index >= numTokens - 1)
			return -1;

		int id = bodyToReplacement(index + 1);

		if (id >= getNumReplacementToken())
			return -1;
		return replacementToBody(id);
	}

}
