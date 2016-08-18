package edu.udel.cis.vsl.abc.ast.node.IF.expression;

/**
 * <code>$original (expr)</code>, which can only be used in
 * <code>$value_at</code> or <code>$on</code> expression, evaluating the given
 * expression <code>expr</code> in the original state.
 * 
 * @author Manchun Zheng
 *
 */
public interface OriginalExpressionNode extends ExpressionNode {
	ExpressionNode expression();
}
