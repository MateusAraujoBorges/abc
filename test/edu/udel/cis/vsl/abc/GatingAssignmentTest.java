package edu.udel.cis.vsl.abc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udel.cis.vsl.abc.analysis.common.CallAnalyzer;
import edu.udel.cis.vsl.abc.analysis.dataflow.ControlFlowAnalysis;
import edu.udel.cis.vsl.abc.analysis.dataflow.DominatorAnalysis;
import edu.udel.cis.vsl.abc.analysis.gsa.GatedSingleAssignment;
import edu.udel.cis.vsl.abc.analysis.gsa.GatingExpression;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
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
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCException;
import edu.udel.cis.vsl.abc.front.IF.ParseException;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.main.FrontEnd;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

/**
 * Checks a number of simple C programs to make sure they pass on the control
 * flow graph construction analyzer.
 * 
 * @author dwyer
 * 
 */
@Ignore
public class GatingAssignmentTest {

	/**
	 * Turn on a lot of output for debugging? Set this to true only in your
	 * local copy. Be sure to set it back to false before committing!
	 */
	private static boolean debug = true;

	private static File root = new File(new File("examples"), "c");

	private static Configuration config = Configurations
			.newMinimalConfiguration();

	private static FrontEnd fe = new FrontEnd(config);

	private static ControlFlowAnalysis cfa;
	private static DominatorAnalysis dom;
	private static GatedSingleAssignment gsa;

	private AST ast;

	@Before
	public void setUp() throws Exception {
		cfa = ControlFlowAnalysis.getInstance();
		dom = DominatorAnalysis.getInstance();
		gsa = GatedSingleAssignment.getInstance();

	}

	@After
	public void tearDown() throws Exception {
		cfa.clear();
		dom.clear();
		gsa.clear();
	}

	private AST getAST(File file)
			throws ParseException, SyntaxException, PreprocessorException {
		AST ast = fe.compile(new File[] { file }, Language.C, new File[0],
				new File[0], new HashMap<String, String>());
		return ast;
	}

	@Test
	public void kicktires() throws ParseException, SyntaxException, PreprocessorException {
		File file = new File(root, "ifthen.c");
		ast = getAST(file);

		cfa.analyze(ast);

		if (debug) {
			cfa.printControlFlowGraph(ast);
		}

		Map<ASTNode, GatingExpression> geMap = new HashMap<ASTNode, GatingExpression>();

		for (Function f : CallAnalyzer.functions(ast)) {
			dom.analyze(f);
			gsa.analyze(f);
			
			System.out.println("Dominator Tree for "+f);
			dom.printDominatorTree(f);
			
			System.out.println("Subtree roots in the dominator tree:");
			for (ASTNode n1 : cfa.allNodes(f)) {
				for (ASTNode n2 : cfa.allNodes(f)) {
					System.out.println("Subtree root for "+n1+" below "+n2);
					System.out.println("   -> "+gsa.subroot(n1,n2));
				}
			}
			/*
			for (ASTNode n : cfa.allNodes(f)) {
				if (isBranch(n)) {
					for (ASTNode s : cfa.successors(n)) {
						geMap.put(s, new GatingExpression(n, s, branchCondition(n,s)));
					}
				} else {
					if (cfa.successors(n) != null)
						for (ASTNode s : cfa.successors(n)) {
							geMap.put(s, new GatingExpression(true));
						}
				}
			}
			
			for (ASTNode n : cfa.allNodes(f)) {
				if (isMerge(n)) {
					// merge
					GatingExpression current = new GatingExpression(false);
					for (ASTNode p : cfa.predecessors(n)) {
						current = current.or(current, geMap.get(p));
					}
					geMap.put(n, current);
				} else {
					if (cfa.predecessors(n) != null)
						for (ASTNode p : cfa.predecessors(n)) {
							if (geMap.get(p) != null) {
								GatingExpression sGExpr = geMap.get(p);
								geMap.put(n, sGExpr.concat(sGExpr, geMap.get(n)));
							}
						}
				}
			}
			*/
		}
		
		System.out.println("Gating Expressions for AST:");
		for (ASTNode n : geMap.keySet()) {
			System.out.println("-----------------");
			System.out.println("  node:"+n);
			System.out.println("  gexpr:"+geMap.get(n));
		}
		System.out.println("-----------------");


	}

	private boolean isBranch(ASTNode n) {
		return (cfa.successors(n) != null) && (cfa.successors(n).size() > 1);
	}
	
	private boolean isMerge(ASTNode n) {
		return (cfa.predecessors(n) != null) && (cfa.predecessors(n).size() > 1);
	}

	private boolean isChild(ASTNode n, ASTNode c) {
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

	private ExpressionNode branchCondition(ASTNode n, ASTNode s) {
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
