package edu.udel.cis.vsl.abc.analysis.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.udel.cis.vsl.abc.analysis.IF.Analyzer;
import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.AtomicNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ChooseStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CivlForNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.DeclarationListNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ExpressionStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.JumpNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.NullStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ReturnNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.WhenNode;
import edu.udel.cis.vsl.abc.ast.type.IF.FunctionType;
import edu.udel.cis.vsl.abc.ast.type.IF.PointerType;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardSignedIntegerType;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardSignedIntegerType.SignedIntKind;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

/**
 * Given an AST determines successor/predecessor relationships among statements.
 * 
 * ? The analyzer is supposed to work on an entire AST, but we want to target specific ASTNodes 
 * ? should we use this approach?
 * 
 * We could follow the model of the CallAnalyzer and extend the AST with pred/succ
 * functions that return sets of statements where "null" means that no information
 * has been computed.   With this approach multiple calls to compute ControlFlow
 * for parts of the AST can be made and the results will persist for the life of the
 * AST.
 * 
 * There should be no reason why this code does not operate on a whole program.   We
 * just need to visit the substructure down to the level of the method body, then 
 * switch over to the control flow collector routines.
 * 
 * @author dwyer
 * 
 */
public class ControlFlowAnalyzer implements Analyzer {
	Map<StatementNode, Set<StatementNode>> successors = new HashMap<StatementNode, Set<StatementNode>>();
	Map<StatementNode, Set<StatementNode>> predecessors = new HashMap<StatementNode, Set<StatementNode>>();

	AST currentAST;

	/*
	 * Dispatch to statement-specific control flow edge building methods
	 */
	private void collectStatement(ASTNode node) {
		if (node instanceof ChooseStatementNode) {
		} else if (node instanceof CompoundStatementNode) {
		} else if (node instanceof DeclarationListNode) {
		} else if (node instanceof ExpressionStatementNode) {
		} else if (node instanceof ForLoopNode) {
		} else if (node instanceof LoopNode) {
		} else if (node instanceof ReturnNode) {
		} else if (node instanceof SwitchNode) {
		} else if (node instanceof WhenNode) {
		} else if (node instanceof AtomicNode) {
		} else if (node instanceof JumpNode) {
		} else if (node instanceof NullStatementNode) {
		} else if (node instanceof CivlForNode) {

		}
		for (ASTNode child : node.children()) {
			if (child != null)
				collectStatement(child);
		}
	}

	// add functions to build edges for each type of statement


	@Override
	public void clear(AST unit) {
		successors.clear();
		predecessors.clear();
		clearNode(unit.getRootNode());
	}

	private void clearNode(ASTNode node) {
		if (node != null) {
			if (node instanceof FunctionDefinitionNode) {
				Function f = ((FunctionDefinitionNode) node).getEntity();
				if (f != null) {
					Set<Function> callers = f.getCallers();
					if (callers != null)
						callers.clear();
					Set<Function> callees = f.getCallees();
					if (callees != null)
						callees.clear();
				}
			}
			for (ASTNode child : node.children()) {
				clearNode(child);
			}
		}
	}

	@Override
	public void analyze(AST unit) throws SyntaxException {
		collectStatement(unit.getRootNode());
	}

	static private Set<Function> seen;

	static public void printControlFlowGraph(AST unit) {
		System.out.println(unit.getMain().getName());
		seen = new HashSet<Function>();
		seen.add(unit.getMain());
		printControlFlowGraphNode(unit.getMain(), " ");
	}

	static private void printControlFlowGraphNode(Function f, String pre) {
		for (Function callee : f.getCallees()) {
			System.out.print(pre + callee.getName() + " [");
			for (Function caller : callee.getCallers()) {
				System.out.print(" " + caller.getName());
			}
			System.out.println(" ]");
			if (!seen.contains(callee)) {
				seen.add(callee);
				printControlFlowGraphNode(callee, pre + " ");
				seen.remove(callee);
			} else {
				System.out.println(pre + " <recursion>");
			}
		}
	}

}
