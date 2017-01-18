package edu.udel.cis.vsl.abc.ast.node.common.expression;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.MemoryBlockReferenceNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonMemoryBlockNode extends CommonExpressionNode
		implements
			MemoryBlockReferenceNode {

	public CommonMemoryBlockNode(Source source, ExpressionNode baseAddr) {
		super(source, baseAddr);
	}

	@Override
	public CommonMemoryBlockNode copy() {
		return new CommonMemoryBlockNode(getSource(), duplicate(baseAddress()));
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.MEMORY_BLOCK;
	}

	@Override
	public boolean isConstantExpression() {
		return baseAddress().isConstantExpression();
	}

	@Override
	public boolean isSideEffectFree(boolean errorsAreSideEffects) {
		return baseAddress().isSideEffectFree(errorsAreSideEffects);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("MEMORY_BLOCK");
	}

	@Override
	public ExpressionNode baseAddress() {
		return (ExpressionNode) child(0);
	}
}
