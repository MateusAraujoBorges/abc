package edu.udel.cis.vsl.abc.ast.node.IF.expression;

/**
 * An intermediate representation of a reference to the super set of a set of
 * memory locations. A memory block is always a super set of memory locations
 * reside in the block.
 * 
 * A memory block reference consists of a base address expression which
 * represents the base address of the memory block. The size of the memory block
 * is either decided by the size of the variable or the size specified in
 * allocation.
 * 
 * A memory block reference node will be printed as "$mem_block_ref(base_addr)",
 * though it is not a part of the CIVL-C language.
 * 
 * Example: <code>
 *   T a[n+1];
 *   
 *   $mem_block_ref(a) points to a super-set of a[1..n].
 * </code>
 * 
 * @author ziqing
 *
 */
public interface MemoryBlockReferenceNode extends ExpressionNode {
	/**
	 * A reference to a expression which represents a set of objects of type T.
	 * 
	 * @return
	 */
	ExpressionNode baseAddress();
}
