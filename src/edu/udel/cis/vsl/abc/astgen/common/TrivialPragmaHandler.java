package edu.udel.cis.vsl.abc.astgen.common;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.PragmaNode;
import edu.udel.cis.vsl.abc.astgen.IF.PragmaHandler;
import edu.udel.cis.vsl.abc.astgen.IF.SimpleScope;
import edu.udel.cis.vsl.abc.front.c.ptree.CParseTree;

public class TrivialPragmaHandler implements PragmaHandler {

	private String name;

	private CParseTree parseTree;

	public TrivialPragmaHandler(String name, CParseTree parseTree) {
		this.name = name;
		this.parseTree = parseTree;
	}

	@Override
	public EntityKind getEntityKind() {
		return EntityKind.PRAGMA_HANDLER;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ASTNode processPragmaNode(PragmaNode pragmaNode, SimpleScope scope) {
		return pragmaNode;
	}

	@Override
	public CParseTree getParseTree() {
		return parseTree;
	}

}
