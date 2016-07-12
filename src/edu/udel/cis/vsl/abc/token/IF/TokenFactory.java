package edu.udel.cis.vsl.abc.token.IF;

import java.io.File;
import java.util.List;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

import edu.udel.cis.vsl.abc.token.IF.ExecutionCharacter.CharacterKind;

/**
 * A factory for producing all the objects under the control of the token
 * module. These includes instances of the following types (and their subtypes):
 * <ul>
 * <li>{@link CivlcToken}</li>
 * <li>{@link Formation}</li>
 * <li>{@link ExecutionCharacter}</li>
 * <li>{@link Source}</li>
 * <li>{@link SyntaxException}</li>
 * <li>{@link UnsourcedException}</li>
 * <li>{@link Macro}</li>
 * </ul>
 * 
 * @author siegel
 * 
 */
// TODO fix javadocs
public interface TokenFactory {

	// Formations (records of history of token creation)...

	/**
	 * Returns a standard macro expansion formation object. This is a formation
	 * that represents a token created through the process of macro expansion.
	 * 
	 * @param startToken
	 *            the token within the macro application expression that led to
	 *            the formation of the new token; this could be either the macro
	 *            name itself or a token from one of the arguments
	 * @param macro
	 *            the {@link Macro} object given an abstraction represnetation
	 *            of the macro
	 * @param index
	 *            the index of the replacement token (numbered from 0) in the
	 *            macro replacement list that led to the final token
	 * @return a new formation incorporating the specified values
	 */
	MacroExpansion newMacroExpansion(CivlcToken startToken, Macro macro,
			int index);

	/**
	 * Formation of a string literal token through the use of the preprocessor
	 * "#" operator during the application of a function-like macro.
	 * 
	 * @param macro
	 *            the function-like macro being applied
	 * @param index
	 *            the index of the replacement token (numbered from 0) in the
	 *            macro replacement list involved in the formation of the new
	 *            token; this replacement token will necessarily be a parameter
	 *            immediately following a "#" token
	 * @param argument
	 *            the sequence of non-whitespace tokens comprising the argument
	 *            in the macro invocation
	 * @return a new formation incorporating specified values
	 */
	Stringification newStringification(FunctionMacro macro, int index,
			List<CivlcToken> argument);

	/**
	 * A formation of a token by either (1) concatenating 0 or more tokens using
	 * the preprocessor "##" operator, or (2) concatenating 1 or more string
	 * literal tokens immediately after preprocessing.
	 * 
	 * @param tokens
	 *            list of tokens to concatenate; should not include whitespace
	 */
	Concatenation newConcatenation(List<CivlcToken> tokens);

	/**
	 * Inclusion record for original source file.
	 * 
	 * @param file
	 *            the file which was included
	 * @return a new inclusion record
	 */
	Inclusion newInclusion(SourceFile file);

	Inclusion newInclusion(SourceFile file, CivlcToken includeToken);

	/**
	 * Creates a new formation which represents some code added by the system
	 * itself, as opposed to code that emanated from an actual source file. The
	 * identifier should be a short string indicating what part of the system
	 * created the code. Examples: "The CIVL-MPI Transformer". The identifier
	 * will be used to form a "fake" {@link File}, which will be used to form a
	 * {@link SourceFile}, and that is what will be returned by the formation's
	 * {@link Formation#getLastFile()} method.
	 * 
	 * @param identifier
	 *            short string indicating what part of the system created this
	 *            code; used in messages
	 * @return a new system formation object
	 */
	Formation newSystemFormation(String identifier);

	Formation newTransformFormation(String transformerName, String method);

	// Basic token creation...

	CivlcToken newCivlcToken(Token token, Formation formation);

	CivlcToken newCivlcToken(int type, String text, Formation formation);

	CivlcToken newCivlcToken(CharStream input, int type, int channel, int start,
			int stop, Formation formation);

	// Characters and Strings...

	ExecutionCharacter executionCharacter(CharacterKind kind, int codePoint,
			char[] characters);

	CharacterToken characterToken(CivlcToken token) throws SyntaxException;

	StringToken newStringToken(CivlcToken token) throws SyntaxException;

	StringToken newStringToken(List<CivlcToken> tokens) throws SyntaxException;

	// Source objects...

	Source newSource(CivlcToken token);

	Source newSource(CivlcToken first, CivlcToken last);

	Source join(Source source, CivlcToken token);

	Source join(Source source1, Source source2);

	// Exceptions...

	SyntaxException newSyntaxException(String message, Source source);

	SyntaxException newSyntaxException(String message, CivlcToken token);

	SyntaxException newSyntaxException(UnsourcedException e, Source source);

	SyntaxException newSyntaxException(UnsourcedException e, CivlcToken token);

	UnsourcedException newUnsourcedException(String message);

	// Macros...

	ObjectMacro newObjectMacro(Tree definitionNode, SourceFile file);

	FunctionMacro newFunctionMacro(Tree definitionNode, SourceFile file);

	// TokenSources...

	CivlcTokenSequence getTokenSubsequence(CivlcTokenSource fullSource,
			CivlcToken startToken, CivlcToken stopToken);

	CivlcTokenSequence getEmptyTokenSubsequence(
			CivlcTokenSource originalSource);

	/**
	 * creates a CivlC Token Source based on a give list of tokens (not
	 * necessarily CivlC token). All given tokens will be transformed to CivlC
	 * tokens if they are not CivlC tokens, using the given formation.
	 * 
	 * @param tokens
	 *            the list of tokens
	 * @param formation
	 *            the formation to be used when transforming the given tokens
	 * @return a CivlC token source wrapping the given list of tokens
	 */
	CivlcTokenSource getCivlcTokenSourceByTokens(List<? extends Token> tokens,
			Formation formation);

	// FileIndexers....

	FileIndexer newFileIndexer();
}
