package edu.udel.cis.vsl.abc.transform.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.abc.ast.IF.ASTException;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.entity.IF.Scope;
import edu.udel.cis.vsl.abc.ast.entity.IF.Variable;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.AttributeKey;
import edu.udel.cis.vsl.abc.ast.node.IF.ExternalDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.PragmaNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.StaticAssertionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.OrdinaryDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.AssumeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.type.IF.QualifiedObjectType;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.transform.common.Pruner.Reachability;

/**
 * Finds all reachable nodes in the AST and marks them REACHABLE. Assumes that
 * the AST has already had the standard analyses performed, so that every
 * identifier has been resolved to refer to some Entity.
 * 
 * @author siegel
 * 
 */
public class PrunerWorker {

	private ASTNode root;

	private AttributeKey reachedKey;

	List<ASTNode> reachableNodes = new LinkedList<ASTNode>();

	public PrunerWorker(AttributeKey reachedKey, ASTNode root)
			throws SyntaxException {
		this.reachedKey = reachedKey;
		this.root = root;
		search();
	}

	/**
	 * Marks a node and its descendants reachable.
	 * 
	 * If <code>node</code> is already marked reachable, returns immediately.
	 * Otherwise marks the node as reachable and recurses over the node's
	 * children. For each child, if the child is an identifier node, the entity
	 * to which that identifier refers is explored. In addition, if the child is
	 * anything other than an ordinary declaration (i.e., a function or variable
	 * declaration) or a typedef declaration, then <code>markReachable</code> is
	 * invoked recursively on the child.
	 * 
	 * @param node
	 */
	private void markReachable(ASTNode node) {
		if (node.getAttribute(reachedKey) == Reachability.REACHABLE)
			return;
		else {
			Iterable<ASTNode> children = node.children();

			node.setAttribute(reachedKey, Reachability.REACHABLE);
			reachableNodes.add(node);
			if (node instanceof FunctionTypeNode) {
				// special case: don't want to remove unused formal parameters
				SequenceNode<VariableDeclarationNode> formals = ((FunctionTypeNode) node)
						.getParameters();

				for (VariableDeclarationNode formal : formals)
					markReachable(formal);
			}
			for (ASTNode child : children) {
				if (child != null) {
					if (child instanceof IdentifierNode) {
						Entity entity = ((IdentifierNode) child).getEntity();

						if (entity == null)
							throw new ASTException("Identifier not resolved: "
									+ child.getSource().getSummary(false));
						explore(entity);
					}
					if (child instanceof OrdinaryDeclarationNode
							|| child instanceof TypedefDeclarationNode) {
						if (child instanceof VariableDeclarationNode) {
							InitializerNode init = ((VariableDeclarationNode) child)
									.getInitializer();

							// at some point improve this to keep the init
							// but not necessarily the variable...
							if (init != null && !init.isSideEffectFree(true))
								markReachable(child);
						}
						// do nothing: we want to see if we can reach these
					} else
						markReachable(child);
				}
			}
		}
	}

	/**
	 * Invokes {@link #markReachable(ASTNode)} on all declarations of the
	 * entity.
	 * 
	 * @param entity
	 *            an Entity occurring in the AST
	 */
	private void explore(Entity entity) {
		Iterator<DeclarationNode> declIter = entity.getDeclarations();

		while (declIter.hasNext())
			markReachable(declIter.next());
	}

	/**
	 * Searches AST, marking reachable nodes as REACHABLE.
	 * 
	 * @return list of reachable nodes
	 * @throws SyntaxException
	 */
	private void search() throws SyntaxException {
		Scope rootScope = root.getScope();
		Function main = (Function) rootScope.getOrdinaryEntity("main");
		Iterator<Variable> iter = rootScope.getVariables();
		Iterable<ASTNode> children = root.children();

		while (iter.hasNext()) {
			Variable variable = iter.next();
			Type type = variable.getType();

			if (type instanceof QualifiedObjectType) {
				QualifiedObjectType qotype = (QualifiedObjectType) type;

				if (qotype.isInputQualified() || qotype.isOutputQualified()) {
					VariableDeclarationNode decl = variable.getDefinition();

					if (decl == null)
						throw new ASTException(
								"No definition for input or output variable: "
										+ variable);
					markReachable(decl);
				}
			}
		}
		for (ASTNode child : children) {
			ExternalDefinitionNode externalDef = (ExternalDefinitionNode) child;

			if (externalDef instanceof PragmaNode
					|| externalDef instanceof StaticAssertionNode
					|| externalDef instanceof AssumeNode)
				markReachable(externalDef);
		}
		explore(main);
	}

	public List<ASTNode> getReachableNodes() {
		return reachableNodes;
	}

}
