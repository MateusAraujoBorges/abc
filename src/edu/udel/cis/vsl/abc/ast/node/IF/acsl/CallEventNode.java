package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;

public interface CallEventNode extends DependsEventNode {

	IdentifierNode getFunction();

	SequenceNode<ExpressionNode> arguments();

	@Override
	CallEventNode copy();
}
