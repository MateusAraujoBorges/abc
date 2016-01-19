package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;

/**
 * A depends event which specifies reading or writing a list of memory units. It
 * has the syntax
 * 
 * <pre>
 * \read(m0, m1, ...)
 * </pre>
 * 
 * or
 * 
 * <pre>
 * \write(m0, m1, ...)
 * </pre>
 * 
 * @author Manchun Zheng
 *
 */
public interface ReadOrWriteEventNode extends DependsEventNode {
	boolean isRead();

	boolean isWrite();

	SequenceNode<ExpressionNode> getMemoryList();

	@Override
	ReadOrWriteEventNode copy();
}
