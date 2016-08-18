package edu.udel.cis.vsl.abc.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OriginalExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonOriginalExpressionNode extends CommonExpressionNode
		implements
			OriginalExpressionNode {

	public CommonOriginalExpressionNode(Source source, ExpressionNode expr) {
		super(source, expr);
	}

	@Override
	public ExpressionNode copy() {
		return new CommonOriginalExpressionNode(getSource(),
				duplicate(expression()));
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.ORIGINAL;
	}

	@Override
	public boolean isConstantExpression() {
		return expression().isConstantExpression();
	}

	@Override
	public boolean isSideEffectFree(boolean errorsAreSideEffects) {
		return expression().isSideEffectFree(errorsAreSideEffects);
	}

	@Override
	public ExpressionNode expression() {
		return (ExpressionNode) this.child(0);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("ORIGINAl");
	}

}
