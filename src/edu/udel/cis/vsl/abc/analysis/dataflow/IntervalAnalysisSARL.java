package edu.udel.cis.vsl.abc.analysis.dataflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import edu.udel.cis.vsl.abc.analysis.dataflow.IF.AbstractValue;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.abc.util.IF.Pair;

import edu.udel.cis.vsl.abc.analysis.dataflow.common.IntervalValue;

public class IntervalAnalysisSARL extends DataFlowFramework<Pair<Entity, IntervalValue>>{
	private static IntervalAnalysisSARL instance = null;

	Function currentFunction;

	ControlFlowAnalysis cfa;

	private EvaluationCommon evaluator;

	/**
	 * DFAs are singletons.  This allows them to be applied incrementally across a code base.
	 */
	protected IntervalAnalysisSARL() {
		evaluator = new EvaluationCommon();
	}

	public static IntervalAnalysisSARL getInstance() {
		if (instance == null) {
			instance = new IntervalAnalysisSARL();
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

		Set<Pair<Entity, IntervalValue>> init = new HashSet<Pair<Entity,IntervalValue>>();

		// Unprocessed nodes are assigned an empty set
		Set<Pair<Entity, IntervalValue>> bottom = new HashSet<Pair<Entity, IntervalValue>>();

		computeFixPoint(init, bottom);
	}

	/*
	 *  The next group of methods are stolen from reaching definitions, the OMP simplifier, and then expanded on significantly.
	 *  	They should be factored out into some utilities.
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
	
	private boolean isDefinition(final ASTNode s) {
		if (s instanceof VariableDeclarationNode) {
			VariableDeclarationNode vdn = (VariableDeclarationNode)s;
			return vdn.isDefinition() && vdn.getInitializer() != null;
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
		} else if (isDefinition(s)) {
			VariableDeclarationNode vdn = (VariableDeclarationNode)s;
			if ( vdn.isDefinition() && vdn.getInitializer() != null ) {
				return vdn.getEntity();
			}
		}
		return null;
	}
	
	private ExpressionNode getRHS(final ASTNode s) {
		if (isAssignment(s)) {
			OperatorNode opn = (OperatorNode) ((ExpressionStatementNode)s).getExpression();
			ExpressionNode rhs = null;
			if (opn.getNumberOfArguments() == 1) {
				rhs = ((OperatorNode)((ExpressionStatementNode)s).getExpression()).getArgument(0);
			} else if (opn.getNumberOfArguments() == 2) {
				// This might need refinement for, e.g., PLUSEQ, which has arg 0 on the LHS and RHS
				rhs = ((OperatorNode)((ExpressionStatementNode)s).getExpression()).getArgument(1);
			}
			return rhs;
		} else if (isDefinition(s)) {
			VariableDeclarationNode vdn = (VariableDeclarationNode)s;
			if ( vdn.isDefinition() && vdn.getInitializer() != null ) {
				return (ExpressionNode)vdn.getInitializer();
			}
		}
		return null;
	}

	@Override
	/*
	 * Generate constants that are assigned from for statements.
	 */
	protected Set<Pair<Entity, IntervalValue>> gen(final Set<Pair<Entity,IntervalValue>> set, final ASTNode n) {

		final Set<Pair<Entity,IntervalValue>> result = new HashSet<Pair<Entity,IntervalValue>>();
		final Entity lhsVar = getLHSVar(n);

//		if(lhsVar != null){
			if (isAssignment(n) || isDefinition(n)) {

				ExpressionNode rhs = getRHS(n);
				
				assert rhs != null : "not simple assignment!";

				Map<Entity, AbstractValue> map = new HashMap<Entity, AbstractValue>();
				for(Pair<Entity, IntervalValue> setElement : set){
					map.put(setElement.left, setElement.right);
				}
				
				
				IntervalValue interval = (IntervalValue) evaluator.evaluate(rhs, map, new IntervalValue());

				assert !interval.isEmpty() : "empty interval?";
				
				Pair<Entity, IntervalValue> inEntry = 
						new Pair<Entity, IntervalValue>(lhsVar, interval);
				result.add(inEntry);

			}else{

//				assert false : "not assignment or definition.";

//				Pair<Entity, IntervalValue> inEntry = 
//						new Pair<Entity, IntervalValue>(lhsVar, new IntervalValue());
//				result.add(inEntry);
//			}
		}

		return result;	
	}

	@Override
	protected
	/*
	 * MODIFIED
	 * Kill constants that are assigned into for statements.
	 */
	Set<Pair<Entity, IntervalValue>> kill( Set<Pair<Entity, IntervalValue>> set, final ASTNode n) {
		Set<Pair<Entity, IntervalValue>> result = new HashSet<Pair<Entity, IntervalValue>>();

		// Extremely simple interpretation of assignment.  No constant folding, no copy propagation, etc.
		if (isAssignment(n) || isDefinition(n)) {
			Entity lhsVar = getLHSVar(n);
			for (Pair<Entity, IntervalValue> inEntry : set) {
				if (inEntry.left.equals(lhsVar)) {
					result.add(inEntry);
				}
			}
		}

		return result;	
	}

	@Override
	protected Set<ASTNode> succs(ASTNode n) {
		return cfa.successors(n);
	}

	@Override
	protected Set<ASTNode> preds(ASTNode n) {
		return cfa.predecessors(n);
	}

	@Override
	protected ASTNode start() {
		ASTNode n = cfa.entry(currentFunction);
		assert n != null;
		return n;
	}

	@Override
	public String getAnalysisName() {
		return "Interval Analysis";
	}


	@Override
	protected Set<Pair<Entity, IntervalValue>> merge(Set<Pair<Entity, IntervalValue>> s1, Set<Pair<Entity, IntervalValue>> s2) {
		Set<Pair<Entity,IntervalValue>> result = new HashSet<Pair<Entity,IntervalValue>>();

		Set<Entity> idOverlap = new HashSet<Entity>();

		// Compute the set of overlapping identifiers in the incoming sets of CP entries
		for (Pair<Entity, IntervalValue> p1 : s1) {
			for (Pair<Entity, IntervalValue> p2 : s2) {
				if (p1.left.equals(p2.left)) {
					idOverlap.add(p1.left);
				}
			}
		}

		// For entries with common identifiers, merge their CP data
		for (Pair<Entity, IntervalValue> p1 : s1) {
			if (!idOverlap.contains(p1.left)) continue;

			for (Pair<Entity, IntervalValue> p2 : s2) {
				if (!idOverlap.contains(p2.left)) continue;

				if (p1.left.equals(p2.left)) {

					//Merge
					IntervalValue iv = new IntervalValue();
					iv = (IntervalValue) iv.union(p1.right,p2.right);

					Pair<Entity, IntervalValue> top = new Pair<Entity, IntervalValue>(p1.left, iv);					
					result.add(top);
				}
			}
		}

		// Add the disjoint CP entries to the merge
		// TBD: this seems wrong.  We want these entries to go to "top".  What's the cleanest way to do that with lambdas?
		result.addAll(s1.stream().filter(p -> !idOverlap.contains(p.left)).collect(Collectors.toSet()));
		result.addAll(s2.stream().filter(p -> !idOverlap.contains(p.left)).collect(Collectors.toSet()));

		return result;
	}

	@Override
	public String toString(Pair<Entity, IntervalValue> e) {
		String entry = e.left+"->"+ e.right;
		return "<"+entry+">";
	}

}
