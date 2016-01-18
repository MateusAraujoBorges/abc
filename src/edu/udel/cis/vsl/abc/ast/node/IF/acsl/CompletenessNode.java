package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;

public interface CompletenessNode extends ContractNode {

	SequenceNode<IdentifierNode> getIDList();

	@Override
	CompletenessNode copy();
}
