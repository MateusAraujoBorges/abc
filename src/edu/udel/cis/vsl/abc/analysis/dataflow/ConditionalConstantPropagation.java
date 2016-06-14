package edu.udel.cis.vsl.abc.analysis.dataflow;

import java.util.HashSet;
import java.util.Set;

import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.abc.util.IF.Pair;

/**
 * The lattice is a map from variables, identified by entity, to singleton constant values or "top" (indicated
 * by the first element of the pair).
 * 
 * Really want to use a data flow framework that allows for a Map to be used, not just a set.
 * 
 * @author dwyer
 */
public class ConditionalConstantPropagation extends BranchedDataFlowFramework<Pair<Entity,Pair<Boolean,ConstantNode>>> {
	private static ConditionalConstantPropagation instance = null;

	Function currentFunction;
	
	ControlFlowAnalysis cfa;
	
	/**
	 * DFAs are singletons.  This allows them to be applied incrementally across a code base.
	 */
	protected ConditionalConstantPropagation() {}
	
	public static ConditionalConstantPropagation getInstance() {
		if (instance == null) {
			instance = new ConditionalConstantPropagation();
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
		
		Set<Pair<Entity, Pair<Boolean, ConstantNode>>> init = new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();
		
		// Unprocessed nodes are assigned an empty set
		Set<Pair<Entity, Pair<Boolean, ConstantNode>>> bottom = new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();

		computeFixPoint(init, bottom);
	}
	
	// The next three methods are stolen from reaching definitions.  They should be factored out into some utilities.
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
	
	private IdentifierExpressionNode baseArray(OperatorNode subscript) {
		assert subscript.getOperator() == OperatorNode.Operator.SUBSCRIPT : "Expected subscript expression";
		if (subscript.getArgument(0) instanceof IdentifierExpressionNode) {
			return (IdentifierExpressionNode) subscript.getArgument(0);
		}
		return baseArray((OperatorNode) subscript.getArgument(0));
	}

	private Entity getLHSVar(final ASTNode s) {
		if (isAssignment(s)) {
			ExpressionNode lhs = ((OperatorNode)((ExpressionStatementNode)s).getExpression()).getArgument(0);
			if (lhs instanceof IdentifierExpressionNode) {
				IdentifierNode id = ((IdentifierExpressionNode)lhs).getIdentifier();
				return id.getEntity();
			} else if (lhs instanceof OperatorNode) {
				OperatorNode opn = (OperatorNode)lhs;
				if (opn.getOperator() == Operator.SUBSCRIPT) {
					IdentifierExpressionNode idn = baseArray(opn);
					return idn.getIdentifier().getEntity();
				} else {
					assert false : "Unexpected operator node on LHS";
				}
			} else {
				assert false : "Unexpected LHS expression";
			}
		}
		return null;
	}
	
	private ExpressionNode getRHS(final ASTNode s) {
		if (isAssignment(s)) {
			ExpressionNode rhs = ((OperatorNode)((ExpressionStatementNode)s).getExpression()).getArgument(1);
			return rhs;
		}
		return null;
	}
	
	@Override
	protected
	/*
	 * Kill constants that are assigned into for statements.
	 * Kill constants that are inconsistent with branch conditions for edges.
	 */
	Set<Pair<Entity,Pair<Boolean,ConstantNode>>> kill(final Set<Pair<Entity,Pair<Boolean,ConstantNode>>> set, final ASTNode n, final ASTNode s) {
		Set<Pair<Entity,Pair<Boolean,ConstantNode>>> result = new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();

		if (isBranch(n)) {
			// TBD TBD
			result.addAll(set);
		} else {
			// Extremely simple interpretation of assignment.  No constant folding, no copy propagation, etc.
			if (isAssignment(n)) {
				Entity lhsVar = getLHSVar(n);
				for (Pair<Entity,Pair<Boolean,ConstantNode>> cpEntry : set) {
					if (cpEntry.left.equals(lhsVar)) {
						result.add(cpEntry);
					}			
				}
			}
		}
		return result;	
	}
	
	@Override
	protected
	/*
	 * Generate constants that are assigned from for statements.
	 */
	Set<Pair<Entity,Pair<Boolean,ConstantNode>>> gen(final Set<Pair<Entity,Pair<Boolean,ConstantNode>>> set, final ASTNode n, final ASTNode s) {
		Set<Pair<Entity,Pair<Boolean,ConstantNode>>> result = new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();

		// Extremely simple interpretation of assignment.  No constant folding, no copy propagation, etc.
		if (isAssignment(n)) {
			Entity lhsVar = getLHSVar(n);
			ExpressionNode rhs = getRHS(n);

			// The constant pair is "top" if the rhs is not a ConstantNode, otherwise we use the rhs
			Pair<Boolean, ConstantNode> constPair = new Pair<Boolean, ConstantNode>(!(rhs instanceof ConstantNode), (ConstantNode)rhs);
			Pair<Entity, Pair<Boolean,ConstantNode>> cpEntry = new Pair<Entity, Pair<Boolean,ConstantNode>>(lhsVar,constPair);
			result.add(cpEntry);
		}
		return result;	
	}

	@Override
	public String getAnalysisName() {
		return "Conditional Constant Propagation";
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
	protected Set<Pair<Entity, Pair<Boolean, ConstantNode>>> merge(Set<Pair<Entity, Pair<Boolean, ConstantNode>>> s1,
			Set<Pair<Entity, Pair<Boolean, ConstantNode>>> s2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Pair<Entity, Pair<Boolean, ConstantNode>> e) {
		Pair<Boolean, ConstantNode> p = e.right;
		String entry = e.left+"->"+((p.left.booleanValue()) ? "top" : (p.right.toString()));
		return "<"+entry+">";
	}


}
