package edu.udel.cis.vsl.abc.transform.common;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTException;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.AttributeKey;
import edu.udel.cis.vsl.abc.ast.node.IF.NodePredicate;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.transform.IF.BaseTransformer;

/**
 * <p>
 * Prunes unreachable objects from an AST. Starting from the "main" function,
 * this transformer performs a search of the nodes that can be reached by
 * following children other than ordinary declarations and typedef declarations.
 * When an identifier is encountered, the definition or declaration of the
 * entity to which it refers is also searched. Hence only those
 * declarations/definitions that are actually used will be encountered in the
 * search.
 * </p>
 * 
 * <p>
 * Once the reachable nodes have been determined, the set of reachable nodes is
 * "closed" by marking all ancestors of reachable nodes reachable.
 * </p>
 * 
 * <p>
 * This transformer assumes the given AST is a closed program. It also assumes
 * that the standard analysis has been performed, so that identifiers have
 * entities associated to them.
 * </p>
 * 
 * <p>
 * The AST nodes are modified and re-used. If you want to keep the original AST
 * intact, you should clone it before performing this transformation.
 * </p>
 * 
 * <p>
 * The AST returned will be pruned, but will not have the standard analyses
 * encoded in it. If you want them, they should be invoked on the new AST.
 * </p>
 * 
 * @author siegel
 * 
 */
public class Pruner extends BaseTransformer {

	public final static String CODE = "prune";
	public final static String LONG_NAME = "Pruner";
	public final static String SHORT_DESCRIPTION = "removes unreachable objects from the AST";

	public enum Reachability {
		/**
		 * Indicates this node is unreachable and can therefore be pruned from
		 * the AST.
		 */
		UNREACHABLE,
		/**
		 * Indicates this node is reachable and must therefore be kept in the
		 * AST.
		 */
		REACHABLE
	};

	private AttributeKey reachedKey;

	private NodePredicate reachable;

	public Pruner(ASTFactory astFactory) {
		super(CODE, LONG_NAME, SHORT_DESCRIPTION, astFactory);
		reachedKey = nodeFactory.newAttribute("reached", Reachability.class);
		reachable = new NodePredicate() {

			@Override
			public boolean holds(ASTNode node) {
				return node.getAttribute(reachedKey) == Reachability.REACHABLE;
			}

		};
	}

	private void markAllUnreachable(ASTNode node) {
		if (node == null)
			return;
		else {
			Iterable<ASTNode> children = node.children();

			node.setAttribute(reachedKey, Reachability.UNREACHABLE);
			for (ASTNode child : children)
				markAllUnreachable(child);
		}
	}

	@Override
	public AST transform(AST ast) throws SyntaxException {
		SequenceNode<BlockItemNode> root = ast.getRootNode();
		Function main = (Function) root.getScope().getOrdinaryEntity(false,
				"main");

		assert this.astFactory == ast.getASTFactory();
		assert this.nodeFactory == astFactory.getNodeFactory();
		if (main == null)
			return ast;
		if (main.getDefinition() == null)
			throw new ASTException("Main function missing definition");
		else {
			ast.release();
			markAllUnreachable(root);
			new PrunerWorker(reachedKey, root);
			root.keepOnly(reachable);

			AST newAst = astFactory.newAST(root, ast.getSourceFiles(),
					ast.isWholeProgram());

			return newAst;
		}
	}
}
