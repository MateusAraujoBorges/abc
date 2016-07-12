package edu.udel.cis.vsl.abc.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.RemoteOnExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonRemoteExpressionNode extends CommonExpressionNode implements
		RemoteOnExpressionNode {

	public CommonRemoteExpressionNode(Source source,
			ExpressionNode processExpression, ExpressionNode foreignNode) {
		super(source, processExpression, foreignNode);

	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	@Override
	public ExpressionNode getProcessExpression() {
		return (ExpressionNode) child(0);
	}

	@Override
	public ExpressionNode getForeignExpressionNode() {
		return (ExpressionNode) child(1);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("RemoteOnExpressionNode");
	}

	@Override
	public RemoteOnExpressionNode copy() {
		return new CommonRemoteExpressionNode(getSource(),
				duplicate(getProcessExpression()),
				duplicate(getForeignExpressionNode()));
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.REMOTE_REFERENCE;
	}

	@Override
	public boolean isSideEffectFree(boolean errorsAreSideEffects) {
		return getProcessExpression().isSideEffectFree(errorsAreSideEffects);
	}

	@Override
	public void setProcessExpression(ExpressionNode arg) {
		setChild(0, arg);
	}

	@Override
	public void setForeignExpressionNode(ExpressionNode arg) {
		setChild(1, arg);
	}
}
