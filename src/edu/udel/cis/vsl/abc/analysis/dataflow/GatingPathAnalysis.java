package edu.udel.cis.vsl.abc.analysis.dataflow;

import java.util.HashSet;
import java.util.Set;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;

/**
 * Compute the gating path expression for nodes in the control flow graph.
 * A gating path is a representation of the branching that determines a control
 * flow path, i.e., the sequence of branches taken.  A gating path expression is
 * a representation of a set of gating paths as a path expression, i.e., a regular
 * expression defined over an alphabet of CFG edges.
 * 
 * We label edges in the CFG as follows:
 *   - for a branch, b, with successor, s, the pair (b,s) labels an edge
 *   - for all non-branching edges the symbol "empty" is used
 * The empty symbol is an identity element for conjunction in
 * regular expressions, e.g., e;empty == empty;e == e
 * 
 * @author dwyer
 */
public class GatingPathAnalysis extends BranchedDataFlowFramework<PathExpression> {
	private static GatingPathAnalysis instance = null;

	Function currentFunction;
	
	ControlFlowAnalysis cfa;
	
	/**
	 * DFAs are singletons.  This allows them to be applied incrementally across a code base.
	 */
	protected GatingPathAnalysis() {}
	
	public static GatingPathAnalysis getInstance() {
		if (instance == null) {
			instance = new GatingPathAnalysis();
		}
		return instance;
	}
	
	@Override
	public void clear() {
		super.clear();
		instance = null;
		cfa.clear();
	}
	
	@Override
	public void analyze(Function f) {
		if (analyzedFunctions.contains(f)) return;
		analyzedFunctions.add(f);
		currentFunction = f;
		
		// Perform control flow analysis (if needed)
		cfa = ControlFlowAnalysis.getInstance();
		cfa.analyze(f);
		
		HashSet<PathExpression> init = new HashSet<PathExpression>();
		init.add(PathExpression.empty());
		HashSet<PathExpression> bottom = new HashSet<PathExpression>();
		bottom.add(PathExpression.identity());
		computeFixPoint(init, bottom);
	}
	
	private PathExpression getPE(Set<PathExpression> set) {
		assert set.size() == 1 : "Expected singleton set";
		return (PathExpression) set.toArray()[0];
	}
	
	@Override
	/*
	 * If this is an assignment statement create a new RD pair for the definition.
	 * 
	 * @see edu.udel.cis.vsl.abc.analysis.dataflow.DataFlowFramework#gen(java.util.Set, edu.udel.cis.vsl.abc.ast.node.IF.ASTNode)
	 */
	protected Set<PathExpression> update(final Set<PathExpression> set, final ASTNode n, final ASTNode s) {
		Set<PathExpression> result = new HashSet<PathExpression>();
		if (isBranch(n)) {
			PathExpression edge = PathExpression.edge(n, s);
			result.add(PathExpression.closeSuffix(getPE(set), edge));
		} else {
			result.addAll(set);
		}
		System.out.println("For node "+n+":\n   input = "+set+"\n   output = "+result);
		return result;	
	}

	@Override
	public String getAnalysisName() {
		return "Gating Paths";
	}

	
	@Override
	/*
	 * This is a forward flow problem, so the successor direction for the analysis aligns with control flow.
	 * 
	 * @see edu.udel.cis.vsl.abc.analysis.dataflow.DataFlowFramework#succs(edu.udel.cis.vsl.abc.ast.node.IF.ASTNode)
	 */
	protected Set<ASTNode> succs(ASTNode s) {
		return cfa.successors(s);
	}

	@Override
	/*
	 * This is a forward flow problem, so the predecessor direction for the analysis opposes control flow.
	 * 
	 * @see edu.udel.cis.vsl.abc.analysis.dataflow.DataFlowFramework#preds(edu.udel.cis.vsl.abc.ast.node.IF.ASTNode)
	 */
	protected Set<ASTNode> preds(ASTNode s) {
		return cfa.predecessors(s);
	}

	@Override
	protected ASTNode start() {
		ASTNode n = cfa.entry(currentFunction);
		assert n != null;
		return n;
	}

	@Override
	/*
	 * Compute disjunction of arguments
	 * 
	 * @see edu.udel.cis.vsl.abc.analysis.dataflow.DataFlowFramework#merge(java.util.Set, java.util.Set)
	 */
	protected Set<PathExpression> merge(Set<PathExpression> s1, Set<PathExpression> s2) {
		Set<PathExpression> result = new HashSet<PathExpression>();
		PathExpression or = PathExpression.or(getPE(s1),getPE(s2));
		result.add(or);
		return result;
	}
	

	@Override
	public String toString(PathExpression e) {
		return e.toString();
	}

}
