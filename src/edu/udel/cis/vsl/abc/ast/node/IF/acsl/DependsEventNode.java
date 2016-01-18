package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;

public interface DependsEventNode extends ASTNode {
	public enum DependsEventKind {
		READ_WRITE, CALL, OPERATOR, ANYACT, NOACT
	}

	DependsEventKind getEventKind();

	@Override
	DependsEventNode copy();
}
