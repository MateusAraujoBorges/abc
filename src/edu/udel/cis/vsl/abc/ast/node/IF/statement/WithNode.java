package edu.udel.cis.vsl.abc.ast.node.IF.statement;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;

/**
 * Represents a CIVL-C <code>$with(p)</code> node. It has the form:
 * <code>$with(p) statement</code>.
 * 
 * The p in the parenthesis shall be an expression which represents a reference
 * to a collate state.
 * 
 * A <code>$with(p) statement</code> is a <code>$with(p)</code> keyword ahead of
 * a single {@link StatementNode} or a {@link CompoundStatementNode}.
 * 
 * A <code>$with(p) statement</code> means that the statement s will be executed
 * from a state referenced by p.
 * 
 * 
 * @author ziqingluo
 *
 */
public interface WithNode extends StatementNode {
	/**
	 * Returns the state reference expression attached with this WithNode.
	 * 
	 * A state reference expression represents reference (or pointer) to some
	 * object that can evaluate to a program state.
	 * 
	 * @return
	 */
	ExpressionNode getStateReference();

	/**
	 * If {@link #isCallWith()} returns true, the returned statement node must
	 * wraps a function call expression node. Else, it can be any kind of
	 * statement.
	 * 
	 * @return A {@link StatementNode} attached with the whole with expression.
	 */
	StatementNode getStatementNode();

	@Override
	WithNode copy();
}
