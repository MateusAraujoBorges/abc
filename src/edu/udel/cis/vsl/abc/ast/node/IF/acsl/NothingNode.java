package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;

/**
 * Constant <code>$nothing</code>, argument of <code>$assigns / $reads</code>
 * contract clauses.
 * 
 * @author Manchun Zheng
 *
 */
public interface NothingNode extends ConstantNode {
	@Override
	NothingNode copy();
}
