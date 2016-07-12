package edu.udel.cis.vsl.abc.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.UpdateNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonUpdateNode extends CommonExpressionNode implements
		UpdateNode {

	public CommonUpdateNode(Source source, ExpressionNode collator,
			FunctionCallNode call) {
		super(source, collator, call);
	}

	@Override
	public ExpressionNode copy() {
		return new CommonUpdateNode(getSource(),
				(ExpressionNode) duplicate(child(0)),
				(FunctionCallNode) duplicate(child(1)));
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.UPDATE;
	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	@Override
	public boolean isSideEffectFree(boolean errorsAreSideEffects) {
		FunctionCallNode call = (FunctionCallNode) child(1);
		boolean result = true;

		for (ExpressionNode param : call.getArguments())
			result &= param.isSideEffectFree(errorsAreSideEffects);
		return result;
	}

	@Override
	public FunctionCallNode getFunctionCall() {
		return (FunctionCallNode) child(1);
	}

	@Override
	public ExpressionNode getCollator() {
		return (ExpressionNode) child(0);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("UpdateNode");
	}
}
