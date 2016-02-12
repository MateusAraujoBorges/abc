package edu.udel.cis.vsl.abc.ast.node.common.acsl;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ReadOrWriteEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonReadOrWriteEventNode extends CommonDependsEventNode
		implements ReadOrWriteEventNode {

	private boolean isRead = true;// if false, then this is a write event

	public CommonReadOrWriteEventNode(Source source, boolean isRead,
			SequenceNode<ExpressionNode> memoryList) {
		super(source, memoryList);
		this.isRead = isRead;
	}

	@Override
	public DependsEventNodeKind getEventKind() {
		return DependsEventNodeKind.READ_WRITE;
	}

	@Override
	public ReadOrWriteEventNode copy() {
		return new CommonReadOrWriteEventNode(this.getSource(), this.isRead,
				duplicate(getMemoryList()));
	}

	@Override
	public boolean isRead() {
		return this.isRead;
	}

	@Override
	public boolean isWrite() {
		return !this.isRead;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SequenceNode<ExpressionNode> getMemoryList() {
		return (SequenceNode<ExpressionNode>) this.child(0);
	}

	@Override
	protected void printBody(PrintStream out) {
		if (this.isRead)
			out.println("Rread");
		else
			out.println("Write");
	}

}
