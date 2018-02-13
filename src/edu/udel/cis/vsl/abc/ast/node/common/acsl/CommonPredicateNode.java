package edu.udel.cis.vsl.abc.ast.node.common.acsl;

import java.io.PrintStream;

import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.PredicateNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.common.declaration.CommonFunctionDeclarationNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonPredicateNode extends CommonFunctionDeclarationNode
		implements
			PredicateNode {

	public CommonPredicateNode(Source source, TypeNode type,
			IdentifierNode name,
			SequenceNode<VariableDeclarationNode> parameters,
			ExpressionNode definition) {
		super(source, name, type, null);
		this.addChild(parameters);
		this.addChild(definition);
	}

	@Override
	public ContractKind contractKind() {
		return ContractKind.PREDICATE;
	}

	@Override
	public CommonPredicateNode copy() {
		return new CommonPredicateNode(getSource(), duplicate(getTypeNode()),
				duplicate(getPredicateName()), duplicate(getParameters()),
				duplicate(getBody()));
	}

	@Override
	public IdentifierNode getPredicateName() {
		return (IdentifierNode) this.child(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SequenceNode<VariableDeclarationNode> getParameters() {
		return ((SequenceNode<VariableDeclarationNode>) this.child(3));
	}

	@Override
	public ExpressionNode getBody() {
		return (ExpressionNode) this.child(4);
	}

	@Override
	protected void printBody(PrintStream out) {
		String params = "";
		SequenceNode<VariableDeclarationNode> paramsNode = getParameters();

		for (VariableDeclarationNode binder : paramsNode)
			params += binder.prettyRepresentation() + " ";
		out.print("predicate " + getPredicateName().prettyRepresentation()
				+ " (" + params + ") = " + getBody().prettyRepresentation());
	}
}
