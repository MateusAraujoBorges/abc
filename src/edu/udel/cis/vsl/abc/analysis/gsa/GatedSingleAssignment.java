package edu.udel.cis.vsl.abc.analysis.gsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;

import edu.udel.cis.vsl.abc.analysis.dataflow.ControlFlowAnalysis;
import edu.udel.cis.vsl.abc.analysis.dataflow.DominatorAnalysis;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;

/**
 * Compute Gated Single Assignment (GSA) information for functions on-demand.
 * This is a relatively direct implementation of "Efficient Building and Placing of
 * Gating Functions", Peng Tu and David Padua, PLDI 1995; except for the Tarjan path
 * optimizations (maybe later if performance is an issue).
 * 
 * @author dwyer
 */
public class GatedSingleAssignment  {
	private static GatedSingleAssignment instance = null;
	
	// Record functions for which analysis results have already been computed
	protected Set<Function> analyzedFunctions = new HashSet<Function>();
	
	Function currentFunction;
	
	ControlFlowAnalysis cfa;
	DominatorAnalysis dom;
	
	protected GatedSingleAssignment() {}
	
	public static GatedSingleAssignment getInstance() {
		if (instance == null) {
			instance = new GatedSingleAssignment();
		}
		return instance;
	}
	
	public void clear() {
		instance = null;
		dom.clear();
		cfa.clear();
	}

	public void analyze(Function f) {
		if (analyzedFunctions.contains(f)) return;
		analyzedFunctions.add(f);
		currentFunction = f;
		
		// Perform control flow analysis (if needed)
		cfa = ControlFlowAnalysis.getInstance();
		cfa.analyze(f);
			
		// Perform dominator analysis (if needed)
		dom = DominatorAnalysis.getInstance();
		dom.analyze(f);
		
		// Computer a depth-first ordering on the dominator tree
		List<ASTNode> rdfo = new ArrayList<ASTNode>();
		Set<ASTNode> seen = new HashSet<ASTNode>();
		dforder(cfa.entry(f), rdfo, seen);
		
		// reverse the order so that we process the dominator tree from bottom up
		Collections.reverse(rdfo);
		
		for (ASTNode u : rdfo) {
			// derive phase of Algorithm 4.2
			for (ASTNode v : dom.idomR(u)) {
				
			}
			
			List<ASTNode> children = new ArrayList<ASTNode>();
			children.addAll(dom.idomR(u));
			List<ASTNode> topoChildren = toporder(children);
			
			// merge phase of Algorithm 4.2
			for (ASTNode v : topoChildren) {
				
			}
			
		}
		
		
	}
	
	/**
	 * Traverse the dominator information for the current function 
	 * in a depth-first manner to compute a depth-first order on the nodes.
	 * 
	 * @param n the current node
	 * @param dfo the computed order
	 * @param seen used to control the depth-first traversal
	 */
	private void dforder(ASTNode n, List<ASTNode> dfo, Set<ASTNode> seen	) {
		if (seen.contains(n)) return;
		seen.add(n);
		dfo.add(n);
		for (ASTNode child : dom.idomR(n)) {
			dforder(child, dfo, seen);
		}
	}
	
	/**
	 * Compute the topological order, relative to control flow edges, of the 
	 * given set of ASTNodes.  Generally there will be very small lists passed
	 * to this method, so we will use a simple (inefficient) algorithm.
	 * Moreover, we assume that the CFG containing these nodes is reducible
	 * and this implies (based on the context that this method is called from)
	 * that that there are no cycles among these nodes.
	 * 
	 * @param s
	 * @return
	 */
	private List<ASTNode> toporder(List<ASTNode> l) {
		List<ASTNode> to = new ArrayList<ASTNode>();
		
		// If a node cannot be reached by any in the list then it can go first in the order
		nextInOrder : for (int i = 0; i<l.size(); i++) {
			for (int j = 0; j<l.size(); j++) {
				if (i==j) continue;
				
				Set<ASTNode> reachable = new HashSet<ASTNode>();
				getReachable(l.get(j), reachable);
				
				if (reachable.contains(l.get(i))) continue nextInOrder;
			}
			
			// Can only arrive here if the ith element is unreachable from any other
			to.add(l.get(i));
			l.remove(i);
		}
		return to;
	}
		
	/*
	 * Refactor the following to utility functions
	 */
	private boolean isAssignment(final ASTNode s) {
		if (s instanceof ExpressionStatementNode) {
			ExpressionNode e = ((ExpressionStatementNode)s).getExpression();
			if (e instanceof OperatorNode) {
				Operator op = ((OperatorNode)e).getOperator();
				if ( (op == Operator.ASSIGN) || 
						(op == Operator.POSTINCREMENT) || (op == Operator.POSTDECREMENT) || 
						(op == Operator.PREINCREMENT) || (op == Operator.PREDECREMENT) || 
						(op == Operator.BITANDEQ) || (op == Operator.BITOREQ) || (op == Operator.BITXOREQ) ||
						(op == Operator.DIVEQ) || (op == Operator.TIMESEQ) || (op == Operator.PLUSEQ) || 
						(op == Operator.MINUSEQ) || (op == Operator.MODEQ) ||
						(op == Operator.SHIFTLEFTEQ) || (op == Operator.SHIFTRIGHTEQ) ) {
					return true;
				}
			} 
		}
		return false;
	}
	
	private void getReachable(ASTNode s, Set<ASTNode> nodes) {
		if (!nodes.contains(s)) {
			nodes.add(s);
			if (cfa.successors(s) != null) {
				for (ASTNode succ : cfa.successors(s)) {
					getReachable(succ, nodes);
				}
			}
		}
	}
	
}
