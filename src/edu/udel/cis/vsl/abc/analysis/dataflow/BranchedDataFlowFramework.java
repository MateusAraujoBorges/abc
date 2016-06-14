package edu.udel.cis.vsl.abc.analysis.dataflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.label.SwitchLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.IfNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.util.IF.Pair;

/**
 * This class realizes an intra-procedural branched set-based Data Flow Framework. 
 * The analysis builds on {@link DataFlowFramework} by adding the ability to compute
 * data flow facts for outgoing edges of branch nodes.   These branches are represented
 * by pairs of nodes, i.e., the branch and a successor of the branch.
 * 
 * @param <E>
 *          The element type of the data flow lattice.
 *          
 * @author dwyer
 */
public abstract class BranchedDataFlowFramework<E> extends DataFlowFramework<E> {
	// Record outputs for edges in addition to nodes in base framework
	protected Map<Pair<ASTNode, ASTNode>, Set<E>> edgeOutMap = new HashMap<Pair<ASTNode, ASTNode>, Set<E>>();

	/**
	 * Clear analysis results
	 */
	@Override
	protected void clear() {
		super.clear();
		edgeOutMap = new HashMap<Pair<ASTNode, ASTNode>, Set<E>>();
	}

	@Override
	/**
	 * Compute the transfer function for all edges whose source is the 
	 * current CFG node. If that results in new values then update the 
	 * out set for the edge. 
	 * 
	 * @param n
	 * @return
	 */
	protected boolean compute(final ASTNode n) {
		boolean newValues = false;
		if (succs(n) != null) {
			for (ASTNode s : succs(n)) {
				Set<E> inSet = getInSet(n);
				inSet = update(inSet, n, s);
				final Set<E> outSet = getOutSet(n, s);

				if (!inSet.containsAll(outSet) || !outSet.containsAll(inSet)) {
					outSet.clear();
					outSet.addAll(inSet);
					inSet.clear();
					newValues |= true;
				}
			}
		}
		return newValues;
	}

	/**
	 * A branch has multiple successors
	 * 
	 * @param n the node in question
	 * @return an indication of whether it is a branch node
	 */
	protected boolean isBranch(ASTNode n) {
		return succs(n).size() > 1;
	}
	
	/**
	 * Access the branch condition for the branch edge.  This is purely an
	 * operation on {@link ASTNode}s, but it is in this class because it is
	 * essential for branched data flow analyses.
	 * 
	 * The returned expression does not share any structure with the existing
	 * AST.
	 * 
	 * @param n node at the source of the edge
	 * @param s successor at the destination of the edge
	 * @return expression encoding branch condition
	 */
	protected ExpressionNode branchCondition(ASTNode n, ASTNode s) {
		NodeFactory nf = n.getOwner().getASTFactory().getNodeFactory();
		Source source = n.getSource();
		
		if (isBranch(n)) {
			assert n instanceof ExpressionNode : "Expected expression node for branch condition";
		
			// Copy the source node since it will be used in building the branch condition
			ExpressionNode srcNode = (ExpressionNode)n.copy();
			
			if (succs(n).size() == 2) {
				// branch is an if-then
				IfNode ifn = (IfNode) n.parent();
				
				if (ifn.getTrueBranch().equals(s)) {
					// true branch has the original condition
					return srcNode;
				} else {
					// false branch requires wrapping with a negation
					OperatorNode notCond = nf.newOperatorNode(source, Operator.NOT, srcNode);
					return notCond;
				}				
		
			} else {
				// branch is a switch, which means that the condition is the expression that is compared to cases
				SwitchNode swn = (SwitchNode) n.parent();
				
				if (swn.getDefaultCase().equals(s)) {
					// default condition is the conjunction of the negation of all case label conditions
					ExpressionNode defaultCondition = nf.newBooleanConstantNode(source, true);
					for (Iterator<LabeledStatementNode> iter = swn.getCases(); iter.hasNext();) {
						LabeledStatementNode c = iter.next();
						if (c.equals(s)) {
							SwitchLabelNode sln = (SwitchLabelNode) c.getLabel();
							
							// Copy the case constant to assemble the switch edge condition
							ExpressionNode caseConst = sln.getExpression().copy();
							OperatorNode caseCompare = nf.newOperatorNode(source, Operator.NEQ, srcNode, caseConst);
							
							defaultCondition = nf.newOperatorNode(source, Operator.LAND, defaultCondition, caseCompare);	
						}
					}
					return defaultCondition;
				} else {
					// match the case label and return its condition
					for (Iterator<LabeledStatementNode> iter = swn.getCases(); iter.hasNext();) {
						LabeledStatementNode c = iter.next();
						if (c.equals(s)) {
							SwitchLabelNode sln = (SwitchLabelNode) c.getLabel();
							
							// Copy the case constant to assemble the switch edge condition
							ExpressionNode caseConst = sln.getExpression().copy();
							OperatorNode caseCompare = nf.newOperatorNode(source, Operator.EQUALS, srcNode, caseConst);
							return caseCompare;	
						}
					}
					assert false : "Expected a matching case label";
				}
				
			}
		} 
		
		// Return a trivial branch condition
		return nf.newBooleanConstantNode(source, true);	
	}
	
	/*
	 * Function space definition
	 */
	
	/*
	 * Edge gen-kill functions
	 */
	protected Set<E> gen(Set<E> set, ASTNode n, ASTNode s) {
		return new HashSet<E>();
	}
	
	protected Set<E> kill(Set<E> set, ASTNode n, ASTNode s) {
		return new HashSet<E>();
	}

	/*
	 * Override this to define a non-gen-kill function space
	 */	
	protected Set<E> update(Set<E> inSet, ASTNode n, ASTNode s) {
		inSet.removeAll(kill(inSet, n, s));
		inSet.addAll(gen(inSet, n, s));
		return inSet;  
	}
	
	/*
	 * Returns the in-set of an {@link ASTNode}.  
	 * 
	 * @param s The {@link ASTNode}.
	 * @return The in-set of the given {@link ASTNode}.
	 */
	 public Set<E> getInSet(final ASTNode s) {
		assert s != null;
		Set<E> inSet = (s == start()) ? init : bottom;

		if (preds(s) != null) {
			for (final ASTNode pred : preds(s)) {
				inSet = merge(inSet, getOutSet(pred, s));
			}
		}

		return inSet;
	}
 
	/**
	 * Returns the out-set for the branch of {@link ASTNode} n leading
	 * to {@link ASTNode} s.
	 * 
	 * @param n the source of the branch
	 * @param s the destination of the branch
	 * @return the out-set of the branch
	 */
	public Set<E> getOutSet(final ASTNode n, final ASTNode s) {
			assert n != null;
			
			if (!isBranch(n)) {
				// If this is not a branch then just get the source out-set
				return getOutSet(n);
			}

			Pair<ASTNode, ASTNode> edge = new Pair<ASTNode, ASTNode>(n, s);
			Set<E> result = edgeOutMap.get(edge);
			if (result == null) {
				result = new HashSet<E>(bottom);
				edgeOutMap.put(edge, result);
			}
			return result;
	}

	/*
	 * Returns the {@link String} representation of all computed RD analysis results.
	 */
	@Override
	public String getResultString() {
		final StringBuilder sb = new StringBuilder("*** " + getAnalysisName()
		+ " ***\n");
		sb.append("*** InSet Map ***\n");
		sb.append(getResultString(true));
		sb.append("*** OutSet Map ***\n");
		sb.append(getResultString(false));
		return sb.toString();
	}

	private String getResultString(final boolean useInSet) {
		final StringBuilder sb = new StringBuilder();
		final ArrayList<String> list = new ArrayList<String>();
		for (final ASTNode n : this.outMap.keySet()) {
			for (final ASTNode s : succs(n)) {
			sb.append("("+n+","+s+") ==> ");
			final TreeSet<String> ts = new TreeSet<String>();
			if (useInSet) {
				for (final E e : getInSet(n)) {
					ts.add(toString(e));
				}
			} else {
				for (final E e : getOutSet(n,s)) {
					ts.add(toString(e));
				}
			}
			for (final String str : ts) {
				sb.append(str);
				sb.append("  #  ");
			}
			final String str = sb.toString();
			sb.setLength(0);
			list.add(str.substring(0, str.length() - 5) + "\n");
			}
		}
		Collections.sort(list);
		for (final String s : list) {
			sb.append(s);
		}
		return sb.toString();
	}
}
