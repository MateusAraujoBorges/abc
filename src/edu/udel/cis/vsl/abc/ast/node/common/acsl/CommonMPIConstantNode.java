package edu.udel.cis.vsl.abc.ast.node.common.acsl;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIContractConstantNode;
import edu.udel.cis.vsl.abc.ast.node.common.expression.CommonConstantNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonMPIConstantNode extends CommonConstantNode implements
		MPIContractConstantNode {

	private MPIConstantKind kind;

	private ConstantKind constKind = null;

	public CommonMPIConstantNode(Source source, String name,
			MPIConstantKind kind, ConstantKind constKind) {
		super(source, name);
		this.kind = kind;
		this.constKind = constKind;
	}

	@Override
	public MPIConstantKind getConstantKind() {
		return kind;
	}

	@Override
	public MPIContractConstantNode copy() {
		return new CommonMPIConstantNode(this.getSource(), this
				.getStringRepresentation().toString(), kind, constKind);
	}

	@Override
	public ConstantKind constantKind() {
		return constKind;
	}

	@Override
	protected void printBody(PrintStream out) {
		out.println(this.prettyRepresentation());
	}

	@Override
	public String toString() {
		return this.prettyRepresentation().toString();
	}

	@Override
	public StringBuffer prettyRepresentation() {
		StringBuffer pretty = new StringBuffer();

		switch (kind) {
		case MPI_COMM_RANK:
			pretty.append("\\mpi_comm_rank");
			break;
		case MPI_COMM_SIZE:
			pretty.append("\\mpi_comm_size");
			break;
		default:
			pretty.append("\\unknown_mpi_constant");
		}
		return pretty;
	}
}
