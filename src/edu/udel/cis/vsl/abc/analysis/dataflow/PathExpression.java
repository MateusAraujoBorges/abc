package edu.udel.cis.vsl.abc.analysis.dataflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dk.brics.automaton.Automaton;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.util.IF.Pair;

/*
 * Path expressions encode a regular language of edges in a flow graph.
 * All of the regular language operations are supported for path expressions.
 * In essence, this class provides an wrapper to an underlying regular language
 * abstraction that maps edges to symbols in a UTF16 alphabet and back again.
 */
public class PathExpression {
	Automaton auto;
	static Map<Pair<ASTNode,ASTNode>,Character> edgeCharMap = new HashMap<Pair<ASTNode,ASTNode>,Character>();
	static char charForMap = 'a';
	
	/**
	 * Path expressions accept the empty string initially.
	 */
	public PathExpression() {
		auto = Automaton.makeEmptyString();
	}
	
	public PathExpression(Pair<ASTNode, ASTNode> e) {
		Character c = edgeCharMap.get(e);
		if (c == null) {
			c = new Character(charForMap);
			charForMap++;
			edgeCharMap.put(e, c);
		}
		auto = Automaton.makeChar(c.charValue());
	}
	
	public PathExpression union(PathExpression p) {
		PathExpression result = new PathExpression();
		result.auto = this.auto.clone();
		result.auto.union(p.auto);
		return result;
	}
	
	static public PathExpression union(Set<PathExpression> set) {
		Set<Automaton> autoSet = new HashSet<Automaton>();
		for (PathExpression p : set) {
			autoSet.add(p.auto);
		}
		PathExpression result = new PathExpression();
		result.auto = Automaton.union(autoSet);
		return result;
	}
	
	public PathExpression concat(PathExpression p) {
		PathExpression result = new PathExpression();
		result.auto = this.auto.clone();
		result.auto = result.auto.concatenate(p.auto);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof PathExpression) {
			PathExpression p = (PathExpression)o;
			return this.auto.equals(p.auto);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.auto.hashCode();
	}
	
	public String toString() {
		String s = "## ";
		for (Pair<ASTNode,ASTNode> e : edgeCharMap.keySet()) {
			s += "("+e+"::"+edgeCharMap.get(e)+") ";
		}
		s += "##" + auto.toString();
		return s;
	}
	
}
