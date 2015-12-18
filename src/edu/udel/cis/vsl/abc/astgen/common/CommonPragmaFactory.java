package edu.udel.cis.vsl.abc.astgen.common;

import edu.udel.cis.vsl.abc.astgen.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.astgen.IF.PragmaFactory;
import edu.udel.cis.vsl.abc.astgen.IF.PragmaHandler;
import edu.udel.cis.vsl.abc.astgen.c.OMPPragmaHandler;
import edu.udel.cis.vsl.abc.front.IF.parse.CParser;
import edu.udel.cis.vsl.abc.front.c.ptree.CParseTree;

public class CommonPragmaFactory implements PragmaFactory {

	private ASTBuilder astBuilder;

	private CParser parser;

	public CommonPragmaFactory(ASTBuilder astBuilder, CParser parser) {
		this.astBuilder = astBuilder;
		this.parser = parser;
	}

	@Override
	public PragmaHandler newHandler(String code, CParseTree parseTree) {
		switch (code) {
		case "CIVL":
			return new CIVLPragmaHandler(astBuilder, parser, parseTree);
		case "omp":
			return new OMPPragmaHandler(astBuilder, parseTree);
		default:
			return new TrivialPragmaHandler(code, parseTree);
		}
	}

	@Override
	public ASTBuilder getASTBuilder() {
		return astBuilder;
	}

}
