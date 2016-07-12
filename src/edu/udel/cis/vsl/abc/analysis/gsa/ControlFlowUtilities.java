package edu.udel.cis.vsl.abc.analysis.gsa;

import java.util.Iterator;

import edu.udel.cis.vsl.abc.analysis.dataflow.ControlFlowAnalysis;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.label.SwitchLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.IfNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class ControlFlowUtilities {
	ControlFlowAnalysis cfa;
	
	public ControlFlowUtilities(ControlFlowAnalysis cfa) {
		this.cfa = cfa;
	}
	
	boolean isBranch(ASTNode n) {
		return (cfa.successors(n) != null) && (cfa.successors(n).size() > 1);
	}
	
	boolean isMerge(ASTNode n) {
		return (cfa.predecessors(n) != null) && (cfa.predecessors(n).size() > 1);
	}

	boolean isChild(ASTNode n, ASTNode c) {
		if (n.equals(c)) {
			return true;
		} else {
			Iterable<ASTNode> children = n.children();
			for (ASTNode child : children) {
				if (isChild(child, c)) {
					return true;
				}
			}
			return false;
		}
	}

	ExpressionNode branchCondition(ASTNode n, ASTNode s) {
		NodeFactory nf = n.getOwner().getASTFactory().getNodeFactory();
		Source source = n.getSource();

		if (isBranch(n)) {
			assert n instanceof ExpressionNode : "Expected expression node for branch condition";

		// Copy the source node since it will be used in building the branch condition
		ExpressionNode srcNode = (ExpressionNode)n.copy();

		if (cfa.successors(n).size() == 2) {
			if (n.parent() instanceof IfNode) {
				IfNode ifn = (IfNode)n.parent();

				// if the successor is in the true branch somewhere
				if (isChild(ifn.getTrueBranch(),s)) {
					// true branch has the original condition
					return srcNode;
				} else {
					// false branch requires wrapping with a negation
					OperatorNode notCond = nf.newOperatorNode(source, Operator.NOT, srcNode);
					return notCond;
				}		
			} else if (n.parent() instanceof LoopNode) {
				LoopNode ln = (LoopNode)n.parent();

				// if the successor is in the body
				if (isChild(ln.getBody(),s)) {
					// true branch has the original condition
					return srcNode;
				} else {
					// false branch requires wrapping with a negation
					OperatorNode notCond = nf.newOperatorNode(source, Operator.NOT, srcNode);
					return notCond;
				}	
			} else {
				assert false : "Unexpected branching node";
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
}
