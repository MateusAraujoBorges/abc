package edu.udel.cis.vsl.abc.front.c.ptree;

import java.util.Collection;

import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.front.IF.parse.Parse.RuleKind;
import edu.udel.cis.vsl.abc.front.IF.token.CToken;
import edu.udel.cis.vsl.abc.front.IF.token.CTokenSequence;
import edu.udel.cis.vsl.abc.front.IF.token.CTokenSource;
import edu.udel.cis.vsl.abc.front.IF.token.Source;
import edu.udel.cis.vsl.abc.front.IF.token.SourceFile;
import edu.udel.cis.vsl.abc.front.IF.token.SyntaxException;
import edu.udel.cis.vsl.abc.front.IF.token.TokenFactory;
import edu.udel.cis.vsl.abc.front.common.ptree.CommonParseTree;

public class ImplCParseTree extends CommonParseTree implements CParseTree {

	private CTokenSource tokenSource;

	private TokenFactory tokenFactory;

	private CommonTree root;

	private RuleKind kind;

	public ImplCParseTree(Language language, RuleKind kind,
			CTokenSource tokenSource, CommonTree root) {
		super(language);
		this.tokenSource = tokenSource;
		this.tokenFactory = tokenSource.getTokenFactory();
		this.root = root;
		this.kind = kind;
	}

	@Override
	public CommonTree getRoot() {
		return root;
	}

	@Override
	public Source source(CommonTree tree) {
		CToken firstToken = null, lastToken = null;
		int start = tree.getTokenStartIndex();
		int stop = tree.getTokenStopIndex();

		if (start >= 0)
			firstToken = tokenSource.getToken(start);
		if (stop >= 0)
			lastToken = tokenSource.getToken(stop);
		if (firstToken == null)
			if (lastToken == null)
				throw new IllegalArgumentException(
						"No tokens associated to tree node " + tree);
			else
				firstToken = lastToken;
		else if (lastToken == null)
			lastToken = firstToken;
		return tokenFactory.newSource(firstToken, lastToken);
	}

	@Override
	public SyntaxException newSyntaxException(String message, CommonTree tree) {
		return tokenFactory.newSyntaxException(message, source(tree));
	}

	@Override
	public CTokenSequence getTokenSourceProducer(CommonTree tokenListNode) {
		int numChildren = tokenListNode.getChildCount();

		if (numChildren == 0) {
			return tokenFactory.getEmptyTokenSubsequence(tokenSource);
		} else {
			CToken startToken = (CToken) ((CommonTree) tokenListNode
					.getChild(0)).getToken();
			CToken stopToken = (CToken) ((CommonTree) tokenListNode
					.getChild(numChildren - 1)).getToken();

			return tokenFactory.getTokenSubsequence(tokenSource, startToken,
					stopToken);
		}
	}

	@Override
	public RuleKind getKind() {
		return kind;
	}

	@Override
	public Collection<SourceFile> getSourceFiles() {
		return tokenSource.getSourceFiles();
	}

	@Override
	public String toString() {
		return tokenSource.toString();
	}

}
