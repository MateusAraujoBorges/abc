package edu.udel.cis.vsl.abc.analysis.dataflow;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;

public class PathExpression {
	PathExpression left = null;
	PathExpression right = null;
	ASTNode src = null;
	ASTNode dest = null;
	Kind kind = Kind.EMPTY;
	
	enum Kind {
		OR, // disjunction of two expressions
		CONCAT, // concatenation of two expressions
		KLEENE, // Kleene closure of one expression
		EDGE, // An edge in a path
		EMPTY, // empty expression
		IDENTITY; // the IDENTITY object for OR, i.e., x OR zero = x
	}
	
	/**
	 * Creates a single node path expression
	 * @param n the AST node
	 */
	static public PathExpression edge(ASTNode src, ASTNode dest) {
		PathExpression p = new PathExpression();
		p.kind = Kind.EDGE;
		p.src = src;
		p.dest = dest;
		return p;
	}
	
	/**
	 * Creates an empty path expression
	 */
	static public PathExpression empty() {
		PathExpression p = new PathExpression();
		p.kind = Kind.EMPTY;
		return p;
	}
	
	static public PathExpression identity() {
		PathExpression p = new PathExpression();
		p.kind = Kind.IDENTITY;
		return p;
	}
	
	static public PathExpression or(PathExpression p1, PathExpression p2) {
		if (p1.kind == Kind.IDENTITY) return p2;
		if (p2.kind == Kind.IDENTITY) return p1;
		PathExpression p = new PathExpression();
		p.kind = Kind.OR;
		p.left = p1;
		p.right = p2;
		return p;
	}
	
	static public PathExpression concat(PathExpression p1, PathExpression p2) {
		if (p1.kind == Kind.EMPTY) return p2;
		if (p2.kind == Kind.EMPTY) return p1;
		PathExpression p = new PathExpression();
		p.kind = Kind.CONCAT;
		p.left = p1;
		p.right = p2;
		return p;
	}
	
	static public PathExpression kleene(PathExpression p1) {
		if (p1.kind == Kind.EMPTY) return p1;
		PathExpression p = new PathExpression();
		p.kind = Kind.KLEENE;
		p.left = p1;
		return p;
	}
	
	/**
	 * Identify any suffixes in p that are rooted at edge e and transform them
	 * to Kleene closure.   Any suffixes that are not rooted at e have e concatenated
	 * onto them.
	 * 
	 * @param p the path expression being analyzed
	 * @param e the edge
	 * @return the resulting transformed path expression
	 */
	static public PathExpression closeSuffix(PathExpression p, PathExpression e) {
		switch(p.kind) {
		case IDENTITY:
		case EMPTY: 
			return e;
		case EDGE: 
			return concat(p,e);
		case OR: 
			return or(closeSuffix(p.left, e), 
				      closeSuffix(p.right, e));
		case CONCAT: 
			if (p.left.equals(e)) {
				return kleene(p);
			} else {
				return concat(p.left,closeSuffix(p.right, e));
			}
		case KLEENE: 
			return kleene(closeSuffix(p.left, e));
		}
		assert false : "Unexpected path expression kind";
		return null;
	}

	public String toString() {
		switch(kind) {
		case IDENTITY: return "ident";
		case EMPTY: return "_";
		case EDGE: return src+"->"+dest;
		case OR: return "("+left+"|"+right+")";
		case CONCAT: return "("+left+";"+right+")";
		case KLEENE: return "("+left+")*";
		}
		assert false : "Unexpected path expression kind";
		return null;
	}
	
	public boolean equals(PathExpression p) {
		if (this.kind != p.kind) return false;
		switch(kind) {
		case IDENTITY: 
		case EMPTY: return true;
		case EDGE: return this.src.equals(p.src) && p.src.equals(this.src);
		case OR:  
		case CONCAT:  
		case KLEENE: return this.left.equals(p.left) && p.right.equals(this.right);
		}
		return false;
	}
	
	public int hashCode() {
		return this.toString().hashCode();
	}
}
