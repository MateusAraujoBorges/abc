package edu.udel.cis.vsl.abc.ast.node.common.type;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.front.IF.token.Source;

public class CommonVoidTypeNode extends CommonTypeNode {

	public CommonVoidTypeNode(Source source) {
		super(source, TypeNodeKind.VOID);
	}

	@Override
	protected void printBody(PrintStream out) {
		String qualifiers = qualifierString();

		out.print("VoidType");
		if (!qualifiers.isEmpty())
			out.print("[" + qualifiers + "]");
	}

	@Override
	public TypeNode copy() {
		CommonVoidTypeNode result = new CommonVoidTypeNode(getSource());

		copyData(result);
		return result;
	}

}
