package edu.udel.cis.vsl.abc.ast.node.common.acsl;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.OperatorEventNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonOperatorEventNode extends CommonDependsEventNode implements
		OperatorEventNode {

	private EventOperator operator;

	public CommonOperatorEventNode(Source source, EventOperator op,
			DependsEventNode left, DependsEventNode right) {
		super(source, left, right);
		this.operator = op;
	}

	@Override
	public DependsEventKind getEventKind() {
		return DependsEventKind.OPERATOR;
	}

	@Override
	public OperatorEventNode copy() {
		return new CommonOperatorEventNode(getSource(), this.operator,
				duplicate(getLeft()), duplicate(getRight()));
	}

	@Override
	public DependsEventNode getLeft() {
		return (DependsEventNode) this.child(0);
	}

	@Override
	public DependsEventNode getRight() {
		return (DependsEventNode) this.child(1);
	}

	@Override
	public EventOperator eventOperator() {
		return this.operator;
	}

	@Override
	protected void printBody(PrintStream out) {
		out.println("OperatorEvent");
	}

}
