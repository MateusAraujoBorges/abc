package edu.udel.cis.vsl.abc.transform.IF;

import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.front.IF.parse.CParser;
import edu.udel.cis.vsl.abc.front.IF.token.CToken;
import edu.udel.cis.vsl.abc.front.IF.token.Formation;
import edu.udel.cis.vsl.abc.front.IF.token.Source;
import edu.udel.cis.vsl.abc.front.IF.token.StringToken;
import edu.udel.cis.vsl.abc.front.IF.token.SyntaxException;
import edu.udel.cis.vsl.abc.front.IF.token.TokenFactory;

/**
 * A very basic partial implementation of {@link Transformer}. Implements the
 * book-keeping methods {@link #getCode()}, {@link #getShortDescription()},
 * {@link #toString()}, and provides similar fields. Most implementations of
 * {@link Transformer} can extend this class.
 * 
 * @author siegel
 * 
 */
public abstract class BaseTransformer implements Transformer {

	protected String code;

	protected String longName;

	protected String shortDescription;

	protected ASTFactory astFactory;

	protected NodeFactory nodeFactory;

	protected BaseTransformer(String code, String longName,
			String shortDescription, ASTFactory astFactory) {
		this.code = code;
		this.longName = longName;
		this.shortDescription = shortDescription;
		this.astFactory = astFactory;
		this.nodeFactory = astFactory.getNodeFactory();
	}

	/**
	 * gets the configuration associated with this translation task
	 * 
	 * @return the configuration associated with this translation task
	 */
	protected Configuration getConfiguration() {
		return this.nodeFactory.configuration();
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getShortDescription() {
		return shortDescription;
	}

	@Override
	public String toString() {
		return longName;
	}

	@Override
	public StringLiteralNode newStringLiteralNode(String method,
			String representation) throws SyntaxException {
		TokenFactory tokenFactory = astFactory.getTokenFactory();
		Formation formation = tokenFactory.newTransformFormation(longName,
				method);
		CToken token = tokenFactory.newCToken(CParser.STRING_LITERAL,
				representation, formation);
		StringToken stringToken = tokenFactory.newStringToken(token);
		Source source = tokenFactory.newSource(stringToken);
		StringLiteralNode result = nodeFactory.newStringLiteralNode(source,
				representation, stringToken.getStringLiteral());

		return result;
	}

}
