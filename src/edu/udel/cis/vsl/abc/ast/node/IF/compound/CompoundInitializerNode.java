package edu.udel.cis.vsl.abc.ast.node.IF.compound;

import edu.udel.cis.vsl.abc.ast.node.IF.PairNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.CompoundLiteralNode;
import edu.udel.cis.vsl.abc.ast.type.IF.ObjectType;

/**
 * A compound initializer (written with curly braces in C) is used to initialize
 * an array, struct, or union. It is specified as a sequence of
 * designation-initializer pairs.
 * 
 * It is also used to represent a compound literal.
 * 
 * @see CompoundLiteralNode
 * 
 * @author siegel
 * 
 */
public interface CompoundInitializerNode extends InitializerNode,
		SequenceNode<PairNode<DesignationNode, InitializerNode>> {

	@Override
	CompoundInitializerNode copy();

	/**
	 * Returns the type of this initializer. The type is determined from the
	 * type of the expression of which this initializer is part, or the declared
	 * type of the variable which it is being used to initialize.
	 * 
	 * @return the type of this initializer
	 */
	ObjectType getType();

	void setType(ObjectType type);

	/**
	 * Returns the compound literal object obtained by analyzing the tree rooted
	 * at this compound initializer node. The compound literal object provides
	 * an abstract view of the literal which is very simple and easy to use.
	 * This method will return null before the analysis has been carried out.
	 * 
	 * @return the compound literal object or null
	 */
	CompoundLiteralObject getLiteralObject();

	void setLiteralObject(CompoundLiteralObject object);

}
