package edu.udel.cis.vsl.abc.ast.node.common.expression;

import java.io.PrintStream;
import java.util.Arrays;

import edu.udel.cis.vsl.abc.ast.IF.DifferenceObject;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.PairNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ArrayLambdaNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

/**
 * A quantified expression consists of a quantifier, a variable bound by the
 * quantifier, an expression restricting the values of the quantified variable,
 * and a quantified expression. e.g. forall {int x | x > 1} x > 0;
 * 
 * @author zirkel
 * 
 */
public class CommonArrayLambdaNode extends CommonExpressionNode implements
		ArrayLambdaNode {

	/**
	 * @param source
	 *            The source code information for this expression.
	 * @param quantifier
	 *            The quantifier for this expression. One of {FORALL, EXISTS,
	 *            UNIFORM}.
	 * @param variableList
	 *            The list of bound variable declarations.
	 * @param restriction
	 *            Boolean-valued expression
	 * @param expression
	 *            the expression that is quantified
	 */
	public CommonArrayLambdaNode(
			Source source,
			TypeNode type,
			SequenceNode<PairNode<SequenceNode<VariableDeclarationNode>, ExpressionNode>> variableList,
			ExpressionNode restriction, ExpressionNode expression) {
		super(source, Arrays
				.asList(type, variableList, restriction, expression));
	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	@Override
	public ExpressionNode copy() {
		return new CommonArrayLambdaNode(this.getSource(), type().copy(),
				boundVariableList().copy(), restriction().copy(), expression()
						.copy());
	}

	@Override
	public TypeNode type() {
		return (TypeNode) this.child(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SequenceNode<PairNode<SequenceNode<VariableDeclarationNode>, ExpressionNode>> boundVariableList() {
		return (SequenceNode<PairNode<SequenceNode<VariableDeclarationNode>, ExpressionNode>>) this
				.child(1);
	}

	@Override
	public ExpressionNode restriction() {
		return (ExpressionNode) this.child(2);
	}

	@Override
	public ExpressionNode expression() {
		return (ExpressionNode) this.child(3);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("array_lambda");
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.ARRAY_LAMBDA;
	}

	@Override
	public boolean isSideEffectFree(boolean errorsAreSideEffects) {
		boolean result = expression().isSideEffectFree(errorsAreSideEffects);

		if (restriction() != null)
			result = result
					&& this.restriction()
							.isSideEffectFree(errorsAreSideEffects);
		return result;
	}

	@Override
	protected DifferenceObject diffWork(ASTNode that) {
		// if (that instanceof QuantifiedExpressionNode) {
		// QuantifiedExpressionNode thatQuan = (QuantifiedExpressionNode) that;
		//
		// if (this. == thatQuan.quantifier())
		// return null;
		// else
		// return new DifferenceObject(this, that, DiffKind.OTHER,
		// "different quantifier");
		// }
		return new DifferenceObject(this, that);
	}
}
