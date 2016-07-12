package edu.udel.cis.vsl.abc.ast.node.IF.statement;

import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpNode;

public interface StatementNode extends BlockItemNode {

	// TODO add javadocs
	public enum StatementKind {
		ATOMIC, CHOOSE, CIVL_FOR, COMPOUND, EXPRESSION, IF, JUMP, LABELED, LOOP, NULL,
		/**
		 * indicates a OmpNode instance {@link OmpNode}
		 */
		OMP, PRAGMA,
		/**
		 * A CIVL-C run expression, A expression node with such a kind may be
		 * cast to {@link RunNode}.
		 */
		RUN, SWITCH, WHEN,
		/**
		 * A CIVL-C $with expression ( <code>$with/$call_with statement</code>);
		 * can be cast to {@link WithNode}
		 */
		WITH,
	}

	@Override
	StatementNode copy();

	/**
	 * Different statement nodes have different statement kind. For example, a
	 * while statement node has the statement kind WHILE, an if statement node
	 * has the kind IF, etc.
	 * 
	 * @return The statement kind defined as an enum element
	 */
	StatementKind statementKind();
}
