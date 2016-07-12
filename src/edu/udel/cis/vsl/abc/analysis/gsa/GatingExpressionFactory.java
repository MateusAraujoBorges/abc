package edu.udel.cis.vsl.abc.analysis.gsa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.abc.analysis.dataflow.ControlFlowAnalysis;
import edu.udel.cis.vsl.abc.analysis.dataflow.DominatorAnalysis;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.util.IF.Pair;

/**
 * Gating expressions are constructed exclusively through a factory.
 * The factory manages control flow, conditional, and other information
 * that is required to compute gating expressions for an edge.  The
 * factory also interns expressions for reuse.
 * 
 * @author dwyer
 */
public class GatingExpressionFactory  {
	ControlFlowUtilities cfu;
	Map<ASTNode, Map<ASTNode, GatingExpression>> edgeGExprMap;	
	
	public GatingExpressionFactory(ControlFlowAnalysis cfa) {
		this.cfu = new ControlFlowUtilities(cfa);
		this.edgeGExprMap = new HashMap<ASTNode, Map<ASTNode, GatingExpression>>();
	}
	
	public GatingExpression makeGatingExpression(ASTNode src, ASTNode dest) {
		Map<ASTNode, GatingExpression> destMap = edgeGExprMap.get(src);
		if (destMap == null) {
			destMap = new HashMap<ASTNode, GatingExpression>();
			edgeGExprMap.put(src, destMap);
		}
		
		GatingExpression result = destMap.get(dest);
		if (result == null) {	
			if (cfu.isBranch(src)) {
				result = new GatingExpression(src, dest, cfu.branchCondition(src, dest));
			} else {
				result = new GatingExpression(true);
			}
		}
		
		return result;
	}
		
}
