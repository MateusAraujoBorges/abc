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
		out.print(kind);
	}
}
