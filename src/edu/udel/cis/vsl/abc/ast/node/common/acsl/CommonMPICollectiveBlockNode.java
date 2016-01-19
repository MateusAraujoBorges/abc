package edu.udel.cis.vsl.abc.ast.node.common.acsl;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPICollectiveBlockNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonMPICollectiveBlockNode extends CommonContractNode implements
		MPICollectiveBlockNode {
	private SequenceNode<ContractNode> body;

	public CommonMPICollectiveBlockNode(Source source, ExpressionNode mpiComm,
			ExpressionNode kind, SequenceNode<ContractNode> body) {
		super(source, mpiComm, kind, body);
		this.body = body;
	}

	@Override
	public ContractKind contractKind() {
		return ContractKind.MPI_COLLECTIVE;
	}

	@Override
	public ExpressionNode getMPIComm() {
		return (ExpressionNode) this.child(0);
	}

	@Override
	public ExpressionNode getCollectiveKind() {
		return (ExpressionNode) this.child(1);
	}

	@Override
	public SequenceNode<ContractNode> getBody() {
		return this.body;
	}

	@Override
	public MPICollectiveBlockNode copy() {
		return new CommonMPICollectiveBlockNode(this.getSource(),
				(ExpressionNode) child(0).copy(), (ExpressionNode) child(1)
						.copy(), this.body);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.println(this.prettyRepresentation());
	}

	@Override
	public StringBuffer prettyRepresentation() {
		StringBuffer out = new StringBuffer();
		out.append("\\mpi_collective(" + this.child(0).prettyRepresentation()
				+ ", " + this.child(1).prettyRepresentation() + ":\n"
				+ this.body.prettyRepresentation());
		return out;
	}

	@Override
	public String toString() {
		return this.prettyRepresentation().toString();
	}
}
