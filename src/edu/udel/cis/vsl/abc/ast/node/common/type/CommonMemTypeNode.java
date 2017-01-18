package edu.udel.cis.vsl.abc.ast.node.common.type;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.type.MemTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonMemTypeNode extends CommonTypeNode implements MemTypeNode {

	public CommonMemTypeNode(Source source) {
		super(source, TypeNodeKind.MEMORY);
	}

	@Override
	public TypeNode copy() {
		return new CommonMemTypeNode(getSource());
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("$mem");
	}
}
