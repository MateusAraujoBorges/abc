package edu.udel.cis.vsl.abc.analysis.gsa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.abc.analysis.dataflow.ControlFlowAnalysis;
import edu.udel.cis.vsl.abc.analysis.dataflow.DominatorAnalysis;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;

/**
 * Compute Gated Single Assignment (GSA) information for functions on-demand.
 * This is a relatively direct implementation of "Efficient Building and Placing of
 * Gating Functions", Peng Tu and David Padua, PLDI 1995; except for the Tarjan path
 * optimizations (maybe later if performance is an issue).
 * 
 * @author dwyer
 */
public class GatingPath  {
	private static GatingPath instance = null;
	
	// Record functions for which analysis results have already been computed
	protected Set<Function> analyzedFunctions = new HashSet<Function>();
	
	// Map associating GSA information with analyzed AST Nodes
	Map<ASTNode, ASTNode> idom = new HashMap<ASTNode, ASTNode>();
	
	Function currentFunction;
	
	ControlFlowAnalysis cfa;
	DominatorAnalysis dom;
	
	protected GatingPath() {}
	
	public static GatingPath getInstance() {
		if (instance == null) {
			instance = new GatingPath();
		}
		return instance;
	}
	
	public void clear() {
		instance = null;
		idom.clear();
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
		
		// compute them
	}
	
	


	public void printDominatorTree(Function f) {
		ASTNode entry = cfa.entry(f);
		printDomTree(entry, "");
	}
	
	private void printDomTree(ASTNode n, String indent) {
		System.out.println(indent+n);
		// print what makes sense
	}
	
}
