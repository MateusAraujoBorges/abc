package edu.udel.cis.vsl.abc.analysis.common;

import java.util.Iterator;

import edu.udel.cis.vsl.abc.analysis.IF.Analyzer;
import edu.udel.cis.vsl.abc.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.abc.ast.entity.IF.Scope;
import edu.udel.cis.vsl.abc.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.IfNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LoopNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

/**
 * Given an AST, determines and sets scope of every node.
 * 
 * @author siegel
 * 
 */
public class ScopeAnalyzer implements Analyzer {

	public static void setScopes(TranslationUnit ast, EntityFactory scopeFactory)
			throws SyntaxException {
		(new ScopeAnalyzer(scopeFactory)).analyze(ast);
	}

	private EntityFactory scopeFactory;

	private int currentId = 0;

	public ScopeAnalyzer(EntityFactory scopeFactory) {
		this.scopeFactory = scopeFactory;
	}

	@Override
	public void analyze(TranslationUnit unit) throws SyntaxException {
		ASTNode root = unit.getRootNode();

		processNode(root, null, null);
		setIds(root.getScope());
	}

	private void processNode(ASTNode node, Scope parentScope,
			Scope functionScope) throws SyntaxException {

		if (node.getScope() != null)
			return;
		if (parentScope == null) {
			parentScope = scopeFactory.newScope(ScopeKind.FILE, null, node);
		} else if (node instanceof FunctionDefinitionNode) {
			// children: identifier, type, contract (optional), body.
			// create function scope (1) for this node. labels go there.
			// create block scope (2) under 1. parameters go there.
			// if contract, create block scope (3) under 2. contract goes there.
			// body is processed under scope 2.
			FunctionDefinitionNode funcNode = (FunctionDefinitionNode) node;
			CompoundStatementNode body = funcNode.getBody();
			SequenceNode<ContractNode> contract = funcNode.getContract();
			FunctionTypeNode funcTypeNode = (FunctionTypeNode) funcNode
					.getTypeNode();
			SequenceNode<VariableDeclarationNode> paramsNode = funcTypeNode
					.getParameters();
			Scope parameterScope;

			parentScope = functionScope = scopeFactory.newScope(
					ScopeKind.FUNCTION, parentScope, node);
			parameterScope = scopeFactory.newScope(ScopeKind.BLOCK,
					functionScope, paramsNode);
			if (paramsNode != null)
				processChildren(paramsNode, parameterScope, functionScope);
			if (contract != null) {
				Scope contractScope = scopeFactory.newScope(ScopeKind.CONTRACT,
						parameterScope, node);

				processChildren(contract, contractScope, functionScope);
			}
			processChildren(funcTypeNode, functionScope, functionScope);
			// processNode because body will get new scope since
			// it will be a compound statement node...
			processNode(body, parameterScope, functionScope);
		} else if (node instanceof CompoundStatementNode) {
			parentScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					node);
		} else if (node instanceof SwitchNode) {
			ASTNode body = ((SwitchNode) node).getBody();
			Scope bodyScope;

			parentScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					node);
			bodyScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					body);
			processChildren(body, bodyScope, functionScope);
		} else if (node instanceof IfNode) {
			ASTNode trueBranch = ((IfNode) node).getTrueBranch();
			ASTNode falseBranch = ((IfNode) node).getFalseBranch();
			Scope trueBranchScope;

			parentScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					node);
			trueBranchScope = scopeFactory.newScope(ScopeKind.BLOCK,
					parentScope, trueBranch);
			processChildren(trueBranch, trueBranchScope, functionScope);
			if (falseBranch != null) {
				Scope falseBranchScope = scopeFactory.newScope(ScopeKind.BLOCK,
						parentScope, falseBranch);

				processChildren(falseBranch, falseBranchScope, functionScope);
			}
		} else if (node instanceof LoopNode) {
			ASTNode body = ((LoopNode) node).getBody();
			Scope bodyScope;

			parentScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					node);
			bodyScope = scopeFactory.newScope(ScopeKind.BLOCK, parentScope,
					body);
			processChildren(body, bodyScope, functionScope);
		} else if (node instanceof FunctionDeclarationNode) {
			// children: ident, type, contract.
			// type children: returnType, parameters
			// put ident type return type in current scope
			// create child scope prototypescope.
			// put parameters in prototype scope.
			// create child of prototypescope contract scope.
			// put contract in there.
			FunctionDeclarationNode declNode = (FunctionDeclarationNode) node;
			FunctionTypeNode typeNode = declNode.getTypeNode();
			ASTNode parameters = typeNode.getParameters();
			SequenceNode<ContractNode> contract = declNode.getContract();

			if (parameters != null || contract != null) {
				Scope prototypeScope = scopeFactory.newScope(
						ScopeKind.FUNCTION_PROTOTYPE, parentScope, parameters);

				if (parameters != null)
					processChildren(parameters, prototypeScope, functionScope);
				if (contract != null) {
					Scope contractScope = scopeFactory.newScope(
							ScopeKind.CONTRACT, prototypeScope, contract);

					processChildren(contract, contractScope, prototypeScope);
				}
			}
		} else if (node instanceof FunctionTypeNode) {
			ASTNode parameters = ((FunctionTypeNode) node).getParameters();

			if (parameters != null && parameters.getScope() == null) {
				Scope prototypeScope = scopeFactory.newScope(
						ScopeKind.FUNCTION_PROTOTYPE, parentScope, parameters);

				processChildren(parameters, prototypeScope, functionScope);
			}
		} else if (node instanceof OrdinaryLabelNode) {
			parentScope = functionScope;
		}
		processChildren(node, parentScope, functionScope);
	}

	private void setIds(Scope scope) {
		if (scope.getId() >= 0) {
			return;
		} else {
			Iterator<Scope> children = scope.getChildrenScopes();

			scope.setId(currentId);
			currentId++;
			while (children.hasNext())
				setIds(children.next());
		}
	}

	private void processChildren(ASTNode node, Scope parent, Scope functionScope)
			throws SyntaxException {
		Iterator<ASTNode> children = node.children();

		assert parent != null;
		node.setScope(parent);
		while (children.hasNext()) {
			ASTNode child = children.next();

			if (child != null)
				processNode(child, parent, functionScope);
		}
	}

	@Override
	public void clear(TranslationUnit unit) {
		clearNode(unit.getRootNode());
	}

	private void clearNode(ASTNode node) {
		if (node != null) {
			Iterator<ASTNode> children = node.children();

			node.setScope(null);
			while (children.hasNext())
				clearNode(children.next());
		}
	}

}
