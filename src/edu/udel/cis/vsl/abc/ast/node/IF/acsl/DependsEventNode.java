package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;

/**
 * This represents an event of the <code>depends</code> clause.
 * 
 * @author Manchun Zheng
 *
 */
public interface DependsEventNode extends ASTNode {
	public enum DependsEventKind {
		READ_WRITE, CALL, OPERATOR, ANYACT, NOACT
	}

	DependsEventKind getEventKind();

	@Override
	DependsEventNode copy();
}
