package edu.udel.cis.vsl.abc.front.c.preproc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;

import edu.udel.cis.vsl.abc.front.IF.CivlcTokenConstant;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.token.IF.CivlcToken;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.IF.FunctionMacro;
import edu.udel.cis.vsl.abc.token.IF.FunctionMacro.FunctionReplacementUnit;
import edu.udel.cis.vsl.abc.token.IF.Macro;
import edu.udel.cis.vsl.abc.token.IF.Macro.ReplacementUnit;
import edu.udel.cis.vsl.abc.token.IF.ObjectMacro;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;
import edu.udel.cis.vsl.abc.util.IF.Pair;

/**
 * <p>
 * This class is used to execute a single {@link Macro} invocation. It is used
 * for both {@link ObjectMacro}s and {@link FunctionMacro}s. For both kinds of
 * {@link Macro}s, it performs concatenation ("##"). For {@link FunctionMacro}s,
 * it also substitutes arguments for corresponding formal parameters in the
 * macro definition body, and performs stringification ("#").
 * </p>
 * 
 * <p>
 * The result, returned by method {@link #expand()}, is a null-terminated linked
 * list of {@link CivlcToken}. The elements of the {@link Pair} returned are the
 * first and last elements of the list.
 * </p>
 * 
 * <p>
 * For {@link FunctionMacro}s, the given arguments will not be modified and will
 * not be re-used in the returned result. I.e., each node in the result will be
 * a fresh new token.
 * </p>
 * 
 * <p>
 * The histories (@link Formation}s) of the new tokens will all be set
 * appropriately. The history of a new token generated from a replacement token
 * which was not a formal parameter is obtained by appending a new expansion
 * record (macro, replacement-index) to the history of the origin. The history
 * of a new token generated from an argument token (which is substituted for a
 * formal parameter) is obtained by appending the new expansion record to the
 * history of the argument token.
 * </p>
 */
public class MacroExpander {

	// Static constants...

	/**
	 * This is the integer token type for the temporary "placemarker" tokens
	 * used to perform token concatenation. All placemarker tokens will be
	 * removed before the final result is returned. Used in
	 * {@link FunctionMacro}s only.
	 */
	private final static int placemarkerType = -999;

	// Instance fields...

	/**
	 * The preprocessor token source that will be used to expand a list of
	 * tokens (method {@link PreprocessorTokenSource#expandList(CivlcToken)}.
	 * This is needed for {@link FunctionMacro} invocations, because the
	 * arguments may need to be expanded before being substituted for the formal
	 * parameters. Hence there is a mutual recursion between this class and the
	 * token source.
	 */
	private PreprocessorTokenSource ts;

	/**
	 * The token factory that will be used to produce new {@link CivlcToken}s
	 * and {@link Formation}s.
	 */
	private TokenFactory tokenFactory;

	/**
	 * The macro which is being applied.
	 */
	private Macro macro;

	/**
	 * The original {@link CivlcToken} which is an identifier spelling the
	 * macro's name and which led to this expansion.
	 */
	private CivlcToken origin;

	/**
	 * For {@link FunctionMacro}s, the number of actual arguments in the macro
	 * invocation. 0 for {@link ObjectMacro}s.
	 */
	private int numArgs;

	/**
	 * For {@link FunctionMacro}s, actual arguments to macro invocation. Element
	 * i is a null-terminated linked list of tokens. Element i may be
	 * <code>null</code>, indicating an empty argument. Not used for
	 * {@link ObjectMacro}s.
	 */
	private CivlcToken[] arguments;

	/**
	 * For {@link FunctionMacro}s, the cached results of macro expanding each of
	 * the arguments. Each entry gives the first and last elements in a
	 * null-terminated list. In case the expansion is empty, the first and last
	 * elements will both be null. Not used for {@link ObjectMacro}s.
	 */
	private Pair<CivlcToken, CivlcToken>[] expandedArguments;

	/**
	 * Special token used in a temporary phase to perform concatenations ("##");
	 * see C11. The type of this token is {@link #placemarkerType}. Not used for
	 * {@link ObjectMacro}s.
	 */
	private CivlcToken placemarker;

	// Constructor...

	/**
	 * Constructs new expander for a {@link FunctionMacro} application. No work
	 * will be done at this time. The work will be done when method
	 * {@link #expand()} is invoked.
	 * 
	 * @param ts
	 *            the preprocessor token source that will be used to macro
	 *            expand the arguments
	 * @param macro
	 *            the function-like macro that is being applied
	 * @param origin
	 *            the original {@link CivlcToken} which is an identifier
	 *            spelling the macro's name and which led to its expansion.
	 * @param arguments
	 *            actual arguments to macro invocation. Element i is a
	 *            null-terminated linked list of tokens. Element i may be null,
	 *            indicating an empty argument.
	 */
	public MacroExpander(PreprocessorTokenSource ts, FunctionMacro macro,
			CivlcToken origin, CivlcToken[] arguments) {
		this.ts = ts;
		this.tokenFactory = ts.getTokenFactory();
		this.macro = macro;
		this.origin = origin;
		this.arguments = arguments;
		this.numArgs = arguments.length;

		@SuppressWarnings("unchecked")
		Pair<CivlcToken, CivlcToken>[] temp = (Pair<CivlcToken, CivlcToken>[]) new Pair<?, ?>[numArgs];

		this.expandedArguments = temp;
		this.placemarker = tokenFactory.newCivlcToken(placemarkerType, "",
				null);
	}

	/**
	 * Constructs new expander for an invocation of an {@link ObjectMacro}.
	 * 
	 * @param ts
	 *            the preprocessor token source that will be used to macro
	 *            expand the arguments
	 * @param macro
	 *            the object-like macro that is being applied
	 * @param origin
	 *            the original {@link CivlcToken} which is an identifier
	 *            spelling the macro's name and which led to its expansion.
	 */
	public MacroExpander(PreprocessorTokenSource ts, ObjectMacro macro,
			CivlcToken origin) {
		this.ts = ts;
		this.tokenFactory = ts.getTokenFactory();
		this.macro = macro;
		this.origin = origin;
		this.arguments = null;
		this.numArgs = 0;
		this.expandedArguments = null;
		this.placemarker = null;
	}

	// Helpers...

	/**
	 * A class used to represent a single preprocessing token resulting from
	 * expansion, along with possible white space before and after.
	 * 
	 * @author siegel
	 */
	private class ExpandedToken {

		/**
		 * Possible whitespace preceding the token. Always non-<code>null</code>
		 * but may be empty.
		 */
		ArrayList<CivlcToken> prews;

		/**
		 * The token itself. May be <code>null</code>.
		 */
		CivlcToken token;

		/**
		 * Possible whitespace following the token. Always non-<code>null</code>
		 * but may be empty.
		 */
		ArrayList<CivlcToken> postws;

		/**
		 * Is this a concatenation operator ("##") which should be executed?
		 * Default is <code>false</code>.
		 */
		private boolean concat;

		/**
		 * Constructs new instance from the given fields. Nothing is copied.
		 * 
		 * @param prews
		 *            the whitespace preceding the token; non-<code>null</code>
		 * @param token
		 *            the token itself; may be <code>null</code>
		 * @param postws
		 *            the whitespace following the token; non-<code>null</code>
		 */
		ExpandedToken(ArrayList<CivlcToken> prews, CivlcToken token,
				ArrayList<CivlcToken> postws) {
			this.prews = prews;
			this.token = token;
			this.postws = postws;
		}

		/**
		 * Constructs new instance with no preceding whitespace.
		 * 
		 * @param token
		 *            the token itself; may be <code>null</code>
		 * @param postws
		 *            the whitespace following the token; non-<code>null</code>
		 */
		ExpandedToken(CivlcToken token, ArrayList<CivlcToken> postws) {
			this(new ArrayList<CivlcToken>(), token, postws);
		}

		/**
		 * Constructs new instance with no preceding or following whitespace.
		 * 
		 * @param token
		 *            the token itself; may be <code>null</code>
		 */
		ExpandedToken(CivlcToken token) {
			this(new ArrayList<CivlcToken>(), token,
					new ArrayList<CivlcToken>());
		}

		/**
		 * Is this a concatenation operator ("##") which should be executed?
		 * Default is <code>false</code>. Note that "##" tokens that come from
		 * arguments are NOT executed. Only those that come from the macro
		 * replacement sequence are executed.
		 * 
		 * @return <code>true</code> iff is a concatenation operator ("##")
		 *         which should be executed
		 */
		boolean isConcat() {
			return concat;
		}

		/**
		 * Specifies whether this is a concatenation operator ("##") which
		 * should be executed. Default is <code>false</code>.
		 * 
		 * @param flag
		 *            Is this a concatenation operator ("##") which should be
		 *            executed?
		 */
		void setConcat(boolean flag) {
			this.concat = flag;
		}
	}

	/**
	 * Creates clone of null-terminated linked list of {@link CivlcToken}.
	 * 
	 * @param token
	 *            first element in linked list; could be <code>null</code>
	 * @return first element in cloned list
	 */
	private CivlcToken cloneList(CivlcToken token) {
		CivlcToken current, previous = null, result = null;

		for (CivlcToken t = token; t != null; t = t.getNext()) {
			current = tokenFactory.newCivlcToken(t, t.getFormation());
			if (previous != null)
				previous.setNext(current);
			if (result == null)
				result = current;
			previous = current;
		}
		return result;
	}

	/**
	 * Returns the result of macro-expanding the token sequence of the index-th
	 * argument, using the {@link PreprocessorTokenSource} to perform the
	 * expansions.
	 * 
	 * @param index
	 *            index into {@link #arguments} array
	 * @return the result of macro-expanding the token sequence which is the
	 *         link list headed by the index-th argument
	 * @throws PreprocessorException
	 *             if an error occurs in expansion, such as wrong number of
	 *             arguments in a macro invocation
	 */
	private CivlcToken getExpandedArgument(int index)
			throws PreprocessorException {
		if (expandedArguments[index] == null)
			expandedArguments[index] = ts
					.expandList(cloneList(arguments[index]));
		return expandedArguments[index].left;
	}

	/**
	 * Gets the index-th argument that should be substituted for a formal
	 * parameter in the case where that formal parameter immediately precedes or
	 * follows a concatenation operation ("##"). In that case the plain
	 * (unexpanded) argument is used, unless it is empty, in which case a single
	 * placemarker token is used.
	 * 
	 * @param index
	 *            integer in [0,numArgs-1]
	 * @return first token in index-th argument's token sequence, or placemarker
	 */
	private CivlcToken getSpecialArgument(int index) {
		CivlcToken result = arguments[index];

		if (result == null)
			return placemarker;
		return result;
	}

	/**
	 * Creates a single token by concatenating the text of a sequence of a
	 * non-whitespace tokens. A placemarker token is treated as the identity
	 * element for concatenation.
	 * 
	 * @param tokens
	 *            list of tokens, all of which are non-whitespace. List may
	 *            contain placemarkers. List may be empty.
	 * @return a single token whose text is obtained by concatenating the text
	 *         of the give tokens; result may be placemarker
	 * @throws PreprocessorException
	 *             if the text resulting from concatenation is does not spell a
	 *             token name
	 */
	private CivlcToken concatenate(List<CivlcToken> tokens)
			throws PreprocessorException {
		StringBuffer concatBuffer = new StringBuffer();
		Formation formation = tokenFactory.newConcatenation(tokens);

		for (CivlcToken t : tokens) {
			int type = t.getType();

			if (type != placemarkerType)
				concatBuffer.append(t.getText());
		}
		if (concatBuffer.length() == 0)
			return placemarker;

		String concatString = concatBuffer.toString();
		CharStream charStream = new ANTLRStringStream(concatString);
		PreprocessorLexer lexer = new PreprocessorLexer(charStream);
		Token newToken = null;

		try {
			newToken = lexer.nextToken();
		} catch (Exception e) {
		}
		if (newToken != null) {
			Token nextToken = lexer.nextToken();

			if (nextToken != null && nextToken.getType() == Token.EOF)
				return tokenFactory.newCivlcToken(newToken, formation);
		}
		throw new PreprocessorException(
				"Result of # concatenation not token: " + concatString,
				tokens.get(tokens.size() - 1));
	}

	/**
	 * Places a backslash before every backslash and double quote in a string.
	 * 
	 * @param s
	 *            the given string
	 * @return the result of placing a backslash before every backslash and
	 *         double quote in <code>s</code>
	 */
	private String escape(String s) {
		s = s.replaceAll("\\", "\\\\");
		s = s.replaceAll("\"", "\\\"");
		return s;
	}

	/**
	 * <p>
	 * Given a null-terminated list of tokens, this methods takes the text of
	 * the non-whitespace tokens in that list and concatenates them using a
	 * single space as separator, finally forming a single string literal token
	 * from the resulting text. The exact rules are specified in C11
	 * 6.10.3.2(2):
	 * </p>
	 * 
	 * <blockquote>Each occurrence of white space between the argument’s
	 * preprocessing tokens becomes a single space character in the character
	 * string literal. White space before the first preprocessing token and
	 * after the last preprocessing token composing the argument is deleted.
	 * Otherwise, the original spelling of each preprocessing token in the
	 * argument is retained in the character string literal, except for special
	 * handling for producing the spelling of string literals and character
	 * constants: a \ character is inserted before each " and \ character of a
	 * character constant or string literal (including the delimiting "
	 * characters), except that it is implementation-defined whether a \
	 * character is inserted before the \ character beginning a universal
	 * character name. If the replacement that results is not a valid character
	 * string literal, the behavior is undefined. The character string literal
	 * corresponding to an empty argument is "".</blockquote>
	 * 
	 * Example:
	 * 
	 * <pre>
	 * #define f(X) # X
	 * f(this "is" not a "newline: \n") :
	 * 
	 * "this \"is\" not a \"newline: \\n\""
	 * </pre>
	 * 
	 * <p>
	 * This is used for function-like macros only. Object-like macros do not use
	 * stringification since the argument to the stringification operator "#" is
	 * a formal parameter.
	 * </p>
	 * 
	 * @param token
	 *            the first token in a null-terminated linked list of tokens
	 * @param tokenIndex
	 *            the index of the occurrence of the parameter in the macro body
	 *            token sequence
	 * @return the string literal token formed by concatenating the token text
	 *         as specified in C11 6.10.3.2 (2)
	 */
	private CivlcToken stringifyTokenSequence(CivlcToken token, int tokenIndex)
			throws PreprocessorException {
		StringBuffer concatBuffer = new StringBuffer();
		boolean first = true;
		List<CivlcToken> tokenList = new LinkedList<>();

		concatBuffer.append('"'); // open " of new string literal
		for (CivlcToken t = token; t != null; t = t.getNext()) {
			if (PreprocessorUtils.isWhiteSpace(t))
				continue;

			String tokenText = t.getText();
			int type = t.getType();

			if (type == PreprocessorLexer.STRING_LITERAL
					|| type == PreprocessorLexer.CHARACTER_CONSTANT)
				tokenText = escape(tokenText);
			if (first)
				first = false;
			else
				concatBuffer.append(" ");
			concatBuffer.append(tokenText);
			tokenList.add(t);
		}
		concatBuffer.append('"'); // close " of new string literal

		String text = concatBuffer.toString();
		Formation formation = tokenFactory.newStringification(
				(FunctionMacro) macro, tokenIndex, tokenList);
		CivlcToken newToken = tokenFactory.newCivlcToken(
				CivlcTokenConstant.STRING_LITERAL, text, formation);

		return newToken;
	}

	/**
	 * Determines whether the replacement token at given index immediately
	 * precedes or follows a "##" token in the replacement token sequence.
	 * 
	 * @param index
	 *            an index in the macro body token sequence for a replacement
	 *            token
	 * @return <code>true</code> iff the replacement token immediately preceding
	 *         or following this position is "##"
	 */
	private boolean isSpecial(int index) {
		if (index > 0 && macro.getReplacementUnit(index - 1).token
				.getType() == PreprocessorLexer.HASHHASH)
			return true;
		if (index < macro.getNumReplacements() - 1
				&& macro.getReplacementUnit(index + 1).token
						.getType() == PreprocessorLexer.HASHHASH)
			return true;
		return false;
	}

	/**
	 * Adds a token to an expansion list. Deals with white space properly.
	 * 
	 * @param elist
	 *            an expansion list. Non-<code>null</code>. May be empty. If not
	 *            empty, then only the last element may have a <code>null</code>
	 *            token field.
	 * @param token
	 *            token that should be added to the <code>elist</code>
	 * @return the entry that contains the newly added token; may be an existing
	 *         entry or a newly created one
	 */
	ExpandedToken add(ArrayList<ExpandedToken> elist, CivlcToken token) {
		ExpandedToken result;

		if (PreprocessorUtils.isWhiteSpace(token)) {
			if (elist.isEmpty()) {
				ArrayList<CivlcToken> prews = new ArrayList<CivlcToken>(),
						postws = new ArrayList<CivlcToken>();

				prews.add(token);
				result = new ExpandedToken(prews, null, postws);
				elist.add(result);
			} else {
				result = elist.get(elist.size() - 1);
				if (result.token == null)
					result.prews.add(token);
				else
					result.postws.add(token);
			}
		} else {
			if (elist.isEmpty()) {
				result = new ExpandedToken(token);
				elist.add(result);
			} else {
				result = elist.get(elist.size() - 1);
				if (result.token == null) {
					result.token = token;
				} else {
					result = new ExpandedToken(token);
					elist.add(result);
				}
			}
		}
		return result;
	}

	/**
	 * Creates a new white space token sequence by duplicating a white space
	 * token sequence from the macro definition body.
	 * 
	 * @param ru
	 *            a replacement unit in a macro definition
	 * @return new array of white space tokens formed by cloning the whitespace
	 *         immediately following the replacement token specified by ru in
	 *         the macro definition; the formation used will be a macro
	 *         expansion based on ru
	 */
	private ArrayList<CivlcToken> cloneWhitespace(ReplacementUnit ru) {
		int index = ru.index;
		Token[] wstokens = ru.whitespace;
		ArrayList<CivlcToken> result = new ArrayList<CivlcToken>(
				wstokens.length);
		Formation formation = tokenFactory.newMacroExpansion(origin, macro,
				index);

		for (int i = 0; i < wstokens.length; i++) {
			Token token = wstokens[i];

			result.add(tokenFactory.newCivlcToken(token, formation));
		}
		return result;
	}

	/**
	 * Adds a clone of the replacement unit to an expansion list.
	 * 
	 * @param elist
	 *            an expansion list
	 * @param ru
	 *            a replacement unit
	 * @return the entry containing the token added; may be an existing entry
	 *         (which must be the last one in the list) or a newly created one
	 */
	private ExpandedToken addClone(ArrayList<ExpandedToken> elist,
			ReplacementUnit ru) {
		CivlcToken newToken = tokenFactory.newCivlcToken(ru.token,
				tokenFactory.newMacroExpansion(origin, macro, ru.index));
		ArrayList<CivlcToken> whitespace = cloneWhitespace(ru);
		ExpandedToken result;

		if (elist.isEmpty()) {
			result = new ExpandedToken(newToken, whitespace);
			elist.add(result);
		} else {
			result = elist.get(elist.size() - 1);
			if (result.token == null) {
				result.token = newToken;
				result.postws.addAll(whitespace);
			} else {
				result = new ExpandedToken(newToken, whitespace);
				elist.add(result);
			}
		}
		return result;
	}

	/**
	 * <p>
	 * Creates a sequence of new {@link CivlcToken}s (with associated white
	 * space) by copying the macro definition replacement list, replacing formal
	 * parameters with actual arguments. The stringification operator '#' is
	 * also executed.
	 * </p>
	 * 
	 * <p>
	 * As specified in C11, an occurrence of a formal parameter in the macro
	 * replacement sequence that immediately follows a "#" or immediately
	 * follows or precedes a "##" is first replaced by the (unmodified) argument
	 * token list. All other occurrences of formal parameters in the replacement
	 * sequence are replaced by the expanded argument list (i.e., the argument
	 * is first macro expanded before being substituted). Moreover, if the
	 * parameter immediately precedes or follows a "##" and the corresponding
	 * argument list is empty (i.e., is all whitespace), then the formal is
	 * replaced by the special temporary "placemarker" token.
	 * </p>
	 * 
	 * @return sequence of tokens with pre- and post-whitespace obtained by
	 *         instantiating macro replacement list, substituting arguments for
	 *         formals, and executing stringification operators
	 * @throws PreprocessorException
	 *             if the stringification operator '#' is not followed
	 *             immediately (except for white space) by an occurrence of a
	 *             formal parameter
	 */
	private ArrayList<ExpandedToken> instantiateFunctionMacro()
			throws PreprocessorException {
		int numTokens = macro.getNumReplacements();
		ArrayList<ExpandedToken> result = new ArrayList<>(numTokens);

		for (int i = 0; i < numTokens; i++) {
			FunctionReplacementUnit ru = ((FunctionMacro) macro)
					.getReplacementUnit(i);
			Token token = ru.token;
			int type = token.getType();

			if (type == PreprocessorParser.HASH) {
				if (i == numTokens - 1)
					throw new PreprocessorException(
							"# cannot be last token in a function-like macro definition",
							token);
				i++;
				ru = ((FunctionMacro) macro).getReplacementUnit(i);
				if (ru.formalIndex < 0)
					throw new PreprocessorException(
							"# must be followed by macro parameter in function-like macro definition",
							ru.token);
				result.add(new ExpandedToken(
						stringifyTokenSequence(arguments[ru.formalIndex], i),
						cloneWhitespace(ru)));
			} else if (type == PreprocessorParser.HASHHASH) {
				addClone(result, ru).setConcat(true);
			} else {
				int formalIndex = ru.formalIndex;

				if (ru.formalIndex < 0) {
					addClone(result, ru);
				} else { // splice in argument clone...
					CivlcToken argument = isSpecial(i)
							? getSpecialArgument(formalIndex)
							: getExpandedArgument(formalIndex);

					while (argument != null) {
						CivlcToken newToken = tokenFactory
								.newCivlcToken(argument, tokenFactory
										.newMacroExpansion(argument, macro, i));

						add(result, newToken);
						argument = argument.getNext();
					}
				}
			}
		}
		return result;
	}

	/**
	 * Creates sequence of new {@link CivlcToken}s (with associated whitespace)
	 * by cloning the replacement sequence.
	 * 
	 * @return sequence of {@link ExpandedToken}s which are clones of the macro
	 *         replacement sequence; "##" tokens have their "concat" bits set
	 */
	private ArrayList<ExpandedToken> instantiateObjectMacro() {
		int numTokens = macro.getNumReplacements();
		ArrayList<ExpandedToken> result = new ArrayList<>(numTokens);

		for (int i = 0; i < numTokens; i++) {
			ReplacementUnit ru = macro.getReplacementUnit(i);
			Token token = ru.token;
			int type = token.getType();

			if (type == PreprocessorParser.HASHHASH) {
				addClone(result, ru).setConcat(true);
			} else {
				addClone(result, ru);
			}
		}
		return result;
	}

	/**
	 * Operates on a list of {@link ExpandedToken} by executing the
	 * concatenation operator "##". As specified in C11, the placemarker token
	 * acts as the identity for concatenation.
	 * 
	 * @param input
	 *            a list of expanded tokens
	 * @returns the result of executing all concatenation operators in the given
	 *          list
	 * @throws PreprocessorException
	 *             if the result of a concatenation does not spell a valid token
	 *             name
	 */
	private ArrayList<ExpandedToken> processConcatenations(
			ArrayList<ExpandedToken> input) throws PreprocessorException {
		ArrayList<ExpandedToken> output = new ArrayList<>(input.size());
		int numIn = input.size();

		for (int i = 0; i < numIn; i++) {
			ExpandedToken et = input.get(i);

			if (et.isConcat()) {
				assert i > 0;
				assert !output.isEmpty();

				LinkedList<CivlcToken> concatBuf = new LinkedList<>();
				ExpandedToken concatOutput = output.get(output.size() - 1);

				assert (concatOutput.token != null);
				concatBuf.add(concatOutput.token);
				do { // invariant: i, et point to a ## token
					i++;
					assert i < numIn;
					et = input.get(i);
					assert et.token != null;
					concatBuf.add(et.token);
					i++;
					if (i == numIn)
						break;
					et = input.get(i);
				} while (et.isConcat());
				// i points to token after last token in concat sequence
				i--;
				// i points to last token in concat sequence
				concatOutput.token = concatenate(concatBuf);
				concatOutput.postws = input.get(i).postws;
			} else {
				output.add(et);
			}
		}
		return output;
	}

	/**
	 * Removes any placemarkers from a list.
	 * 
	 * @param input
	 *            list of expanded tokens
	 * @return list obtained by removing any placemarker entries from given list
	 */
	private ArrayList<ExpandedToken> removePlacemarkers(
			ArrayList<ExpandedToken> input) {
		int n = input.size();
		ArrayList<ExpandedToken> output = new ArrayList<>(n);

		for (int i = 0; i < n; i++) {
			ExpandedToken et = input.get(i);

			if (et.token == null || et.token.getType() != placemarkerType)
				output.add(et);
		}
		return output;
	}

	/**
	 * Given an expansion list, extracts all the tokens (including white space)
	 * in order, to create a flat, null-terminated linked list of
	 * {@link CivlcToken}s, which is the form of the final output of this class.
	 * 
	 * @param input
	 *            list of expanded tokens
	 * @return flat list of {@link CivlcToken}s
	 */
	private Pair<CivlcToken, CivlcToken> expansionToTokenList(
			ArrayList<ExpandedToken> input) {
		CivlcToken firstToken = null, previousToken = null;

		for (ExpandedToken et : input) {
			for (CivlcToken token : et.prews) {
				if (previousToken == null)
					firstToken = token;
				else
					previousToken.setNext(token);
				previousToken = token;
			}
			if (et.token != null) {
				if (previousToken == null)
					firstToken = et.token;
				else
					previousToken.setNext(et.token);
				previousToken = et.token;
			}
			for (CivlcToken token : et.postws) {
				if (previousToken == null)
					firstToken = token;
				else
					previousToken.setNext(token);
				previousToken = token;
			}
		}
		return new Pair<CivlcToken, CivlcToken>(firstToken, previousToken);
	}

	// Public methods...

	/**
	 * Perform the macro expansion and return the result.
	 * 
	 * @return the first and last elements in a null-terminated linked list of
	 *         {@link CivlcToken} which results from performing the macro
	 *         expansion
	 * @throws PreprocessorException
	 *             if anything goes wrong in the expansion process
	 */
	public Pair<CivlcToken, CivlcToken> expand() throws PreprocessorException {
		boolean isFunction = macro instanceof FunctionMacro;
		ArrayList<ExpandedToken> expansion = isFunction
				? instantiateFunctionMacro() : instantiateObjectMacro();

		expansion = processConcatenations(expansion);
		if (isFunction)
			expansion = removePlacemarkers(expansion);
		return expansionToTokenList(expansion);
	}

}
