package edu.udel.cis.vsl.abc.analysis.dataflow;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.abc.ast.value.IF.ValueFactory;
import edu.udel.cis.vsl.abc.ast.value.IF.ValueFactory.Answer;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.util.IF.Pair;

/**
 * The lattice is a map from variables, identified by entity, to singleton constant values or "top" (indicated
 * by the first element of the pair).
 * 
 * Really want to use a data flow framework that allows for a Map to be used, not just a set.
 * 
 * @author dwyer
 */
public class ConditionalConstantPropagation extends EdgeDataFlowFramework<Pair<Entity,Pair<Boolean,ConstantNode>>> {	
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
		
		Set<Pair<Entity, Pair<Boolean, ConstantNode>>> init = 
				new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();
		
		// Unprocessed nodes are assigned an empty set
		Set<Pair<Entity, Pair<Boolean, ConstantNode>>> bottom = 
				new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();

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
	
	private Set<Entity> collectRefs(ASTNode node) {
		Set<Entity> refs = new HashSet<Entity>();
		collectRefs(node, refs);
		return refs;
	}
	
	private void collectRefs(ASTNode node, Set<Entity> refs) {
		if (node instanceof IdentifierExpressionNode) {
			Entity idEnt = ((IdentifierExpressionNode) node).getIdentifier()
					.getEntity();
			refs.add(idEnt);

		} else if (node instanceof OperatorNode
				&& ((OperatorNode) node).getOperator() == Operator.SUBSCRIPT) {
			Entity idEnt = baseArray((OperatorNode) node).getIdentifier()
					.getEntity();
			refs.add(idEnt);

		} else if (node != null) {
			Iterable<ASTNode> children = node.children();
			for (ASTNode child : children) {
				collectRefs(child, refs);
			}
		}
	}
	
	/**
	 * 
	 * @param expr 
	 * @param constMap
	 * @return Value expression under constant map; null if value is undefined
	 */
	private Answer eval(ExpressionNode expr, Pair<Entity, ConstantNode> constMap) {
		Answer result;
		ValueFactory vf = expr.getOwner().getASTFactory().getNodeFactory().getValueFactory();
		ExpressionNode freshExpr = expr.copy();
		replaceIdWithConst(freshExpr, constMap);
		try {
			result = vf.isZero(vf.evaluate(freshExpr));
		} catch (SyntaxException se) {
			// If the expression was not made constant by the map then we can't tell the value
			result = Answer.MAYBE;
		}
		return result;
	}
	
	private void replaceNode(ASTNode current, ASTNode replacement) {
		ASTNode parent = current.parent();
		
		int indexOf = 0;
		for (;indexOf < parent.numChildren(); indexOf++) {
			if (parent.child(indexOf) == current) break;
		}
		
		// check that current was found
		assert indexOf < parent.numChildren() : "Node to replace was not found";
		
		parent.removeChild(indexOf);
		parent.setChild(indexOf, replacement);
	}

	/**
	 * Rewrite the expression replacing identifiers with constants given in the map.
	 * 
	 * @param expr
	 * @param map
	 */
	private void replaceIdWithConst(ExpressionNode expr, Pair<Entity,ConstantNode> idConst) {
		// this is a recursive walk that is the usual huge switch structure with recursion
		if (expr instanceof IdentifierExpressionNode) {
			Entity id = ((IdentifierExpressionNode)expr).getIdentifier().getEntity();
			if (id.equals(idConst.left)) {
				replaceNode(expr, idConst.right);
			}
		} else {
			Iterable<ASTNode> children = expr.children();
			for (ASTNode child : children) {
				if (child instanceof ExpressionNode) {
					ExpressionNode childExpr = (ExpressionNode)child;
					replaceIdWithConst(childExpr, idConst);
				}
			}
		}
	}
	
	@Override
	protected
	/*
	 * Kill constants that are assigned into for statements.
	 * Kill constants that are inconsistent with branch conditions for edges.
	 */
	Set<Pair<Entity,Pair<Boolean,ConstantNode>>> kill(
			final Set<Pair<Entity,Pair<Boolean,ConstantNode>>> set, final ASTNode n, final ASTNode s) {
		Set<Pair<Entity,Pair<Boolean,ConstantNode>>> result = 
				new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();

		if (isBranch(n)) {
			ExpressionNode cond = branchCondition(n,s);
			Set<Entity> refs = collectRefs(cond);
			
			/*
			 * Collect the <entry, constant> pairs involving referenced identifiers
			 * that have a non-top value.  For each such pair, if it leads to the 
			 * definite falsification of the branch condition, then kill it.
			 */
			for (Pair<Entity,Pair<Boolean,ConstantNode>> cpEntry : set) {
				if (refs.contains(cpEntry.left)) {
					if (!cpEntry.right.left) {
						Pair<Entity, ConstantNode> constMap = 
								new Pair<Entity, ConstantNode>(cpEntry.left, cpEntry.right.right);
						if (eval(cond, constMap) == Answer.YES) {
							// the branch condition is definitely false
							result.add(cpEntry);
						}
					}
				}			
			}
				
			return result;
		} else {
			// Extremely simple interpretation of assignment.  No constant folding, no copy propagation, etc.
			if (isAssignment(n) || isDefinition(n)) {
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
	Set<Pair<Entity,Pair<Boolean,ConstantNode>>> gen(
			final Set<Pair<Entity,Pair<Boolean,ConstantNode>>> set, final ASTNode n, final ASTNode s) {
		Set<Pair<Entity,Pair<Boolean,ConstantNode>>> result = 
				new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();

		// Extremely simple interpretation of assignment.  No constant folding, no copy propagation, etc.
		if (isAssignment(n) || isDefinition(n)) {
			Entity lhsVar = getLHSVar(n);
			ExpressionNode rhs = getRHS(n);

			// The constant pair is "top" if the rhs is not a ConstantNode, otherwise we use the rhs
			Pair<Boolean, ConstantNode> constPair = null;
			if (rhs instanceof ConstantNode) {
				constPair = new Pair<Boolean, ConstantNode>(false, (ConstantNode)rhs);
			} else {
				constPair = new Pair<Boolean, ConstantNode>(true, null);
			}
			Pair<Entity, Pair<Boolean,ConstantNode>> cpEntry = 
					new Pair<Entity, Pair<Boolean,ConstantNode>>(lhsVar,constPair);
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
		Set<Pair<Entity,Pair<Boolean,ConstantNode>>> result = 
				new HashSet<Pair<Entity,Pair<Boolean,ConstantNode>>>();

		Set<Entity> idOverlap = new HashSet<Entity>();
		
		// Computer the set of overlapping identifiers in the incoming sets of CP entries
		for (Pair<Entity,Pair<Boolean,ConstantNode>> p1 : s1) {
			for (Pair<Entity,Pair<Boolean,ConstantNode>> p2 : s2) {
				if (p1.left.equals(p2.left)) {
					idOverlap.add(p1.left);
				}
			}
		}
		
		// For entries with common identifiers, merge their CP data
		for (Pair<Entity,Pair<Boolean,ConstantNode>> p1 : s1) {
			if (!idOverlap.contains(p1.left)) continue;
			
			for (Pair<Entity,Pair<Boolean,ConstantNode>> p2 : s2) {
				if (!idOverlap.contains(p2.left)) continue;

				if (p1.left.equals(p2.left)) {

					// always generate the same top CP value
					Pair<Boolean, ConstantNode> topCP = new Pair<Boolean, ConstantNode>(new Boolean(true), null);
					Pair<Entity, Pair<Boolean, ConstantNode>> top = new Pair<Entity, Pair<Boolean, ConstantNode>>(p1.left, topCP);
					if (!p1.right.left.booleanValue() && !p2.right.left.booleanValue() && 
							p1.right.right.getConstantValue().equals(p2.right.right.getConstantValue())) {
						result.add(p1);
					} else {
						result.add(top);
					}
				}
			}
		}	
		
		// Add the disjoint CP entries to the merge
		result.addAll(s1.stream().filter(p -> !idOverlap.contains(p.left)).collect(Collectors.toSet()));
		result.addAll(s2.stream().filter(p -> !idOverlap.contains(p.left)).collect(Collectors.toSet()));
				
		return result;
	}

	@Override
	public String toString(Pair<Entity, Pair<Boolean, ConstantNode>> e) {
		Pair<Boolean, ConstantNode> p = e.right;
		String entry = e.left.getName()+"->"+((p.left.booleanValue()) ? "top" : (p.right.getConstantValue()));
		return "<"+entry+">";
	}


}
