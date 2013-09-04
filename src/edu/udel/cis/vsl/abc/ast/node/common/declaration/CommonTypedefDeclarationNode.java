package edu.udel.cis.vsl.abc.ast.node.common.declaration;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.entity.IF.Typedef;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonTypedefDeclarationNode extends CommonDeclarationNode
		implements TypedefDeclarationNode {

	public CommonTypedefDeclarationNode(Source source,
			IdentifierNode identifier, TypeNode type) {
		super(source, identifier, type);
	}

	@Override
	public Typedef getEntity() {
		return (Typedef) super.getEntity();
	}

	@Override
	public TypeNode getTypeNode() {
		return (TypeNode) child(1);
	}

	@Override
	public void setTypeNode(TypeNode type) {
		setChild(1, type);
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print("Typedef");
	}

	@Override
	public TypedefDeclarationNode copy() {
		return new CommonTypedefDeclarationNode(getSource(),
				duplicate(getIdentifier()), duplicate(getTypeNode()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public SequenceNode<VariableDeclarationNode> getScopeList() {
		return (SequenceNode<VariableDeclarationNode>) child(2);
	}

}
