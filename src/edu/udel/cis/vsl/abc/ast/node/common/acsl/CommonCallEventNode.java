package edu.udel.cis.vsl.abc.ast.node.common.acsl;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CallEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonCallEventNode extends CommonDependsEventNode implements
		CallEventNode {

	public CommonCallEventNode(Source source, IdentifierNode function,
			SequenceNode<ExpressionNode> arguments) {
		super(source, function, arguments);
	}

	@Override
	public DependsEventKind getEventKind() {
		return DependsEventKind.CALL;
	}

	@Override
	public CallEventNode copy() {
		return new CommonCallEventNode(getSource(), duplicate(getFunction()),
				duplicate(arguments()));
	}

	@Override
	public void printBody(PrintStream out) {
		out.print("\\call");
	}

	@Override
	public IdentifierNode getFunction() {
		return (IdentifierNode) this.child(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SequenceNode<ExpressionNode> arguments() {
		return (SequenceNode<ExpressionNode>) this.child(1);
	}

}