package edu.udel.cis.vsl.abc.transform.common;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.entity.IF.ProgramEntity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Scope;
import edu.udel.cis.vsl.abc.ast.entity.IF.TaggedEntity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Variable;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.AttributeKey;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.abc.ast.type.IF.QualifiedObjectType;
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

	private AST ast;

	/**
	 * Creates new instance and performs the reachability analysis on the
	 * <code>root</code> node. After returning, all reachable nodes in the AST
	 * will be marked with the <code>reachedKey</code> attribute set to
	 * {@link #REACHABLE}.
	 * 
	 * @param reachedKey
	 *            the attribute key use
	 * @param root
	 *            root node of AST
	 * @throws SyntaxException
	 */
	public PrunerWorker(AttributeKey reachedKey, ASTNode root) {
		// throws SyntaxException {
		this.reachedKey = reachedKey;
		this.root = root;
		this.ast = root.getOwner();
		search();
	}

	/**
	 * Determines whether a declaration occurring in a sequence of block items
	 * nodes should be kept. In general, such a declaration will not be kept
	 * unless the thing that it declares is used in something reachable.
	 * However, there are some exceptions: an input/output variable declaration
	 * will be kept in all cases, and a variable declaration with an initializer
	 * with side effects will be kept.
	 * 
	 * @param node
	 *            a declaration node occurring as a child of a sequence of
	 *            {@link BlockItemNode}.
	 * @return <code>true</code> iff <code>node</code> should be kept
	 */
	private boolean keepSequenceDecl(DeclarationNode node) {
		if (!(node instanceof VariableDeclarationNode))
			return false;

		ObjectType type = ((Variable) node.getEntity()).getType();

		if (type instanceof QualifiedObjectType) {
			QualifiedObjectType qualifiedType = (QualifiedObjectType) type;

			if (qualifiedType.isInputQualified()
					|| qualifiedType.isOutputQualified())
				return true;
		}

		InitializerNode initializer = ((VariableDeclarationNode) node)
				.getInitializer();

		if (initializer != null && !initializer.isSideEffectFree(true))
			return true;
		return false;
	}

	/**
	 * Marks given node as reachable (if not already marked). Explores children
	 * of this node, and ancestors of this node.
	 * 
	 * @param node
	 *            the AST node to mark as reachable and explore
	 */
	private void markReachable(ASTNode node) {
		if (node.getAttribute(reachedKey) == Reachability.REACHABLE)
			return;
		node.setAttribute(reachedKey, Reachability.REACHABLE);

		if (node instanceof IdentifierNode) {
			Entity entity = ((IdentifierNode) node).getEntity();

			if (entity != null && entity instanceof ProgramEntity)
				explore((ProgramEntity) entity);
		}
		if (node.parent() != null)
			markReachable(node.parent());

		boolean isCompound = node instanceof CompoundStatementNode
				|| node.parent() == null;
		// any sequence of block item node

		for (ASTNode child : node.children()) {
			if (child == null)
				continue;
			if (isCompound && child instanceof DeclarationNode
					&& !keepSequenceDecl((DeclarationNode) child))
				continue;
			markReachable(child);
		}
	}

	/**
	 * Marks reachable all necessary declarations and/or definition of an
	 * entity.
	 * 
	 * @param entity
	 *            any non-<code>null</code> program entity
	 */
	private void explore(ProgramEntity entity) {
		if (entity instanceof TaggedEntity) {
			ASTNode defn = entity.getDefinition();

			if (defn != null)
				markReachable(defn);
			// need the first decl in case there other uses in inner scopes
			// before the definition; see C11 6.7.2.3. Without the first decl,
			// those inner uses will declare new types instead of referring
			// to the existing one...

			DeclarationNode decl = entity.getFirstDeclaration();

			if (decl != null && decl != defn) {
				markReachable(decl);
			}
		} else {
			// if a decl is equivalent to any previous decl for this entity
			// don't mark it reachable. Correctness of below requires that
			// getDeclarations are returned in program order.
			// also, system types are weird and have decls from every
			// AST ever seen, so ignore them...
			for (DeclarationNode current : entity.getDeclarations()) {
				if (current.getOwner() != ast)
					continue;
				for (DeclarationNode previous : entity.getDeclarations()) {
					if (previous.getOwner() != ast)
						continue;
					if (previous == current) {
						markReachable(current);
						break;
					}
					if (current.equiv(previous))
						break;
				}
			}
		}
	}

	/**
	 * Searches AST, marking reachable nodes as REACHABLE.
	 * 
	 * @throws SyntaxException
	 */
	private void search() {
		Scope rootScope = root.getScope();
		Function main = (Function) rootScope.getOrdinaryEntity(false, "main");

		explore(main);
	}
}
