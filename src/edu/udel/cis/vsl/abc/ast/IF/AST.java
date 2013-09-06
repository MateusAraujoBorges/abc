package edu.udel.cis.vsl.abc.ast.IF;

import java.io.PrintStream;
import java.util.Iterator;

import edu.udel.cis.vsl.abc.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;

/**
 * An abstract representation of a C "translation unit"---the thing that results
 * from translating a file (which may in turn include other files).
 * 
 * The clone method returns a new AST identical to this one, with all new nodes.
 * The tokens and attributed referenced by the nodes are not cloned; those
 * references are just copied.
 * 
 * @author siegel
 * 
 */
public interface AST {

	/** Returns the ASTFactory responsible for creating this translation unit. */
	ASTFactory getASTFactory();

	/** Returns the root node of the tree. */
	ASTNode getRootNode();

	/** The number of nodes in the tree. */
	int getNumberOfNodes();

	/**
	 * Returns the node with the given id number, The id must lie between 0 and
	 * n-1, inclusive, where n is the number of nodes.
	 */
	ASTNode getNode(int id);

	/** Pretty-prints the entire tree */
	void print(PrintStream out);

	/**
	 * Dissolves this AST. The nodes will be untouched, except they will become
	 * "free"--no longer owned by any AST. They can therefore be modified.
	 */
	void release();

	/**
	 * If this AST contains an entity with internal or external linkage and with
	 * the given name, it is returned by this method, else this method returns
	 * null. The entity will be either a Function, Variable, or Typedef.
	 * 
	 * @param name
	 *            name of the entity
	 * @return the entity
	 */
	OrdinaryEntity getInternalOrExternalEntity(String name);

	/**
	 * Returns an iterator over all entities with internal linkage belonging to
	 * this AST.
	 * 
	 * @return entities with internal linkage
	 */
	Iterator<OrdinaryEntity> getInternalEntities();

	/**
	 * Returns an iterator over all entities with external linkage belonging to
	 * this AST.
	 * 
	 * @return entities with external linkage
	 */
	Iterator<OrdinaryEntity> getExternalEntities();

	/**
	 * Adds the given entity to this AST.
	 * 
	 * @param entity
	 *            an Entity with internal or external linkage
	 */
	void add(OrdinaryEntity entity);
}