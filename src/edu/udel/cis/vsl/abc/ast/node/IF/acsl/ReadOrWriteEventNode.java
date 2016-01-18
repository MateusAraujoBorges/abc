package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;

public interface ReadOrWriteEventNode extends DependsEventNode {
	boolean isRead();

	boolean isWrite();

	SequenceNode<ExpressionNode> getMemoryList();

	@Override
	ReadOrWriteEventNode copy();
}
