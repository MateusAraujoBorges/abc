package edu.udel.cis.vsl.abc.front.common.token;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

import edu.udel.cis.vsl.abc.front.IF.token.CToken;
import edu.udel.cis.vsl.abc.front.IF.token.CTokenSequence;
import edu.udel.cis.vsl.abc.front.IF.token.CTokenSource;
import edu.udel.cis.vsl.abc.front.IF.token.CharacterToken;
import edu.udel.cis.vsl.abc.front.IF.token.Concatenation;
import edu.udel.cis.vsl.abc.front.IF.token.ExecutionCharacter;
import edu.udel.cis.vsl.abc.front.IF.token.Formation;
import edu.udel.cis.vsl.abc.front.IF.token.FunctionMacro;
import edu.udel.cis.vsl.abc.front.IF.token.Inclusion;
import edu.udel.cis.vsl.abc.front.IF.token.Macro;
import edu.udel.cis.vsl.abc.front.IF.token.MacroExpansion;
import edu.udel.cis.vsl.abc.front.IF.token.ObjectMacro;
import edu.udel.cis.vsl.abc.front.IF.token.Source;
import edu.udel.cis.vsl.abc.front.IF.token.SourceFile;
import edu.udel.cis.vsl.abc.front.IF.token.StringLiteral;
import edu.udel.cis.vsl.abc.front.IF.token.StringToken;
import edu.udel.cis.vsl.abc.front.IF.token.SyntaxException;
import edu.udel.cis.vsl.abc.front.IF.token.TokenFactory;
import edu.udel.cis.vsl.abc.front.IF.token.UnsourcedException;
import edu.udel.cis.vsl.abc.front.IF.token.ExecutionCharacter.CharacterKind;

public class CommonTokenFactory implements TokenFactory {

	private CommonCharacterFactory characterFactory;

	private CommonSourceFactory sourceFactory;

	private Map<String, SourceFile> transformerMap = new HashMap<>();

	public CommonTokenFactory() {
		characterFactory = new CommonCharacterFactory(this);
		sourceFactory = new CommonSourceFactory();
	}

	@Override
	public CToken newCToken(Token token, Formation formation) {
		return new CommonCToken(token, formation);
	}

	@Override
	public CToken newCToken(int type, String text, Formation formation) {
		return new CommonCToken(type, text, formation);
	}

	@Override
	public Concatenation newConcatenation(List<CToken> tokens) {
		return new CommonConcatenation(new ArrayList<CToken>(tokens));
	}

	@Override
	public Inclusion newInclusion(SourceFile file, CToken includeToken) {
		return new CommonInclusion(file, includeToken);
	}

	@Override
	public Inclusion newInclusion(SourceFile file) {
		return new CommonInclusion(file);
	}

	@Override
	public Formation newSystemFormation(String identifier) {
		return new SystemFormation(identifier, -1);
	}

	@Override
	public Formation newTransformFormation(String transformerName, String method) {
		SourceFile transformer = transformerMap.get(transformerName);

		if (transformer == null) {
			transformer = new SourceFile(new File(transformerName), -1);
			transformerMap.put(transformerName, transformer);
		}
		return new CommonTransformFormation(transformer, method);
	}

	@Override
	public ExecutionCharacter executionCharacter(CharacterKind kind,
			int codePoint, char[] characters) {
		return characterFactory.executionCharacter(kind, codePoint, characters);
	}

	@Override
	public CharacterToken characterToken(CToken token) throws SyntaxException {
		return characterFactory.characterToken(token);
	}

	/**
	 * 
	 * @param type
	 *            usually PreprocessorParser.STRING_LITERAL
	 * @return
	 * @throws SyntaxException
	 */
	@Override
	public StringToken newStringToken(CToken token) throws SyntaxException {
		StringLiteral data = characterFactory.stringLiteral(token);

		return new CommonStringToken(token, token.getFormation(), data);
	}

	/**
	 * Precondition: tokens has length at least 2.
	 */
	@Override
	public StringToken newStringToken(List<CToken> tokens)
			throws SyntaxException {
		int type = tokens.get(0).getType();
		CommonStringLiteral data = characterFactory.stringLiteral(tokens);
		Concatenation concatenation = newConcatenation(tokens);
		CommonStringToken result = new CommonStringToken(type, concatenation,
				data);

		return result;
	}

	@Override
	public Source newSource(CToken token) {
		return sourceFactory.newSource(token);
	}

	@Override
	public Source newSource(CToken first, CToken last) {
		return sourceFactory.newSource(first, last);
	}

	@Override
	public Source join(Source source, CToken token) {
		return sourceFactory.join(source, token);
	}

	@Override
	public Source join(Source source1, Source source2) {
		return sourceFactory.join(source1, source2);
	}

	@Override
	public SyntaxException newSyntaxException(String message, Source source) {
		return new SyntaxException(message, source);
	}

	@Override
	public SyntaxException newSyntaxException(UnsourcedException e,
			Source source) {
		return new SyntaxException(e, source);
	}

	@Override
	public UnsourcedException newUnsourcedException(String message) {
		return new UnsourcedException(message);
	}

	@Override
	public SyntaxException newSyntaxException(String message, CToken token) {
		return newSyntaxException(message, newSource(token));
	}

	@Override
	public SyntaxException newSyntaxException(UnsourcedException e, CToken token) {
		return newSyntaxException(e, newSource(token));
	}

	@Override
	public ObjectMacro newObjectMacro(Tree definitionNode, SourceFile file) {
		return new CommonObjectMacro(definitionNode, file);
	}

	@Override
	public FunctionMacro newFunctionMacro(Tree definitionNode, SourceFile file) {
		return new CommonFunctionMacro(definitionNode, file);
	}

	@Override
	public MacroExpansion newMacroExpansion(CToken startToken, Macro macro,
			int index) {
		return new CommonMacroExpansion(startToken, macro, index);
	}

	@Override
	public CTokenSequence getTokenSubsequence(CTokenSource fullSource,
			CToken startToken, CToken stopToken) {
		return new CTokenSubSequence(fullSource, startToken.getIndex(),
				stopToken.getIndex());
	}

	@Override
	public CTokenSequence getEmptyTokenSubsequence(CTokenSource originalSource) {
		return new CTokenSubSequence(originalSource, 0, -1);
	}

}
