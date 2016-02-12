package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;

/**
 * This represents an event of the <code>depends</code> clause.
 * 
 * @author Manchun Zheng
 *
 */
public interface DependsEventNode extends ASTNode {
	public enum DependsEventNodeKind {
		READ_WRITE, CALL, COMPOSITE, ANYACT, NOACT
	}

	DependsEventNodeKind getEventKind();

	@Override
	DependsEventNode copy();
}
