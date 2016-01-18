package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

public interface OperatorEventNode extends DependsEventNode {
	public enum EventOperator {
		UNION, DIFFERENCE, INTERSECT
	}

	DependsEventNode getLeft();

	DependsEventNode getRight();

	EventOperator eventOperator();

	@Override
	OperatorEventNode copy();
}
