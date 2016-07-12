package edu.udel.cis.vsl.abc.ast.node.common.statement;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.WithNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonWithNode extends CommonStatementNode implements WithNode {

	public CommonWithNode(Source source, ExpressionNode stateRef,
			StatementNode statement) {
		super(source, stateRef, statement);
	}

	@Override
	public ExpressionNode getStateReference() {
		return (ExpressionNode) child(0);
	}

	@Override
	public StatementNode getStatementNode() {
		return (StatementNode) child(1);
	}

	@Override
	public WithNode copy() {
		return new CommonWithNode(getSource(), duplicate(getStateReference()),
				duplicate(getStatementNode()));
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("WithNode");
	}

	@Override
	public StatementKind statementKind() {
		return StatementKind.WITH;
	}
}
