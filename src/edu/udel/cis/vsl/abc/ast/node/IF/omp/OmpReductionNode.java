package edu.udel.cis.vsl.abc.ast.node.IF.omp;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;

/**
 * This represents an OpenMP reduction clause. The syntax of a reduction clause
 * is as follows:
 * 
 * <pre>
 * reduction(reduction-identifier:list)
 * where reduction-identifier is either an identifier or
 * one of the following operators:
 * +, -, *, &, |, ^, && and ||.
 * </pre>
 * 
 * @author Manchun Zheng
 * 
 */
public interface OmpReductionNode extends ASTNode {

	/**
	 * The kind of this reduction clause, either
	 * <ul>
	 * <li>OPERATOR if the reduction-identifier is one of the following:+, -, *,
	 * &, |, ^, &&, ||, min and max;</li> or
	 * <li>FUNCTION if the reduction-identifier is an identifier except "min"
	 * and "max" (ignoring the letter case for these two strings).</li>
	 * </ul>
	 * 
	 * @author Manchun Zheng
	 * @author Wenhao Wu (wuwenhao@udel.edu)
	 * 
	 */
	public enum OmpReductionNodeKind {
		FUNCTION, OPERATOR
	}

	/**
	 * The kind of this reduction operator, all (SUM, MINUS, MULTIPLY, MAX,
	 * MINBAND, BOR, BXOR, LAND, LOR, UDEF) except for "UDEF" are OpenMP
	 * built-in operators according to OpenMP Standard ver.4.5
	 * (https://www.openmp.org/wp-content/uploads/openmp-4.5.pdf).
	 * 
	 * @author Wenhao Wu (wuwenhao@udel.edu)
	 *
	 */
	public enum OmpReductionOperator {
		SUM, MINUS, PROD, /* Arithmetical Symbol Operators */
		MAX, MIN, /* Arithmetical Identifier Operators */
		BAND, BOR, BXOR, /* Bit-wise Operators */
		LAND, LOR, /* Logical Operators */
		EQV, NEQ, /* Logical Operators for Fortran only */
		UDEF /* User-Defined Reduce Function Identifier Operators */
	}

	/**
	 * Returns the kind of this reduction clause.
	 * 
	 * @return the kind of this reduction clause.
	 */
	OmpReductionNodeKind ompReductionOperatorNodeKind();

	/**
	 * Returns the list of variables associated with this clause.
	 * 
	 * @return the list of variables associated with this clause.
	 */
	SequenceNode<IdentifierExpressionNode> variables();
}
