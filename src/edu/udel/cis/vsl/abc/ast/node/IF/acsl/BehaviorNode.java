package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;

/**
 * This represents a named behavior of the ACSL specification.
 * @author zmanchun
 *
 */
public interface BehaviorNode extends ContractNode {

	IdentifierNode getName();

	SequenceNode<ContractNode> getBody();

	@Override
	BehaviorNode copy();
}
