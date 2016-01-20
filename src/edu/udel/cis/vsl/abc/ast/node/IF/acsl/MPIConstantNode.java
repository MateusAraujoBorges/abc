package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;

public interface MPIConstantNode extends ConstantNode {
	public enum MPIConstantKind {
		MPI_COMM_RANK, MPI_COMM_SIZE
	}

	MPIConstantKind getConstantKind();

	@Override
	MPIConstantNode copy();
}
