package edu.udel.cis.vsl.abc.ast.node.IF.acsl;

import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;

/**
 * A <code>depends</code> clause specifies part of the dependence relation used
 * in partial order reduction (POR). It has the syntax <code>$depends e ;</code>
 * , where <code>e</code> is an expression of boolean type. For each process p,
 * the e can be evaluated in the context of p. If e evaluates to true, then p
 * must be included in an ample set containing a call to this function. The
 * expression <code>e</code> hence defines a predicate <code>d(s,p)</code>,
 * where s ranges over states, and p over procs.
 *
 * @see ContractNode
 * 
 * @author Manchun Zheng
 * 
 */
public interface DependsNode extends ContractNode {
	/**
	 * Gets the list of events specified by this depends clause
	 * 
	 * @return the list of events specified by this depends clause
	 */
	SequenceNode<DependsEventNode> getEventList();

	@Override
	DependsNode copy();
}
