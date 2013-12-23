package edu.udel.cis.vsl.abc.ast.node.common.statement;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.AssertNode;
import edu.udel.cis.vsl.abc.ast.node.common.CommonASTNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonAssertNode extends CommonASTNode implements AssertNode {

	public CommonAssertNode(Source source, ExpressionNode expression) {
		super(source, expression);
	}

	@Override
	public ExpressionNode getExpression() {
		return (ExpressionNode) child(0);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("Assert");
	}

	@Override
	public AssertNode copy() {
		return new CommonAssertNode(getSource(), duplicate(getExpression()));
	}

	@Override
	public StatementKind statementKind() {
		return StatementKind.ASSERT;
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.STATEMENT;
	}

}
