package edu.udel.cis.vsl.abc.front.common.astgen;

import edu.udel.cis.vsl.abc.front.IF.astgen.ASTBuilder;
import edu.udel.cis.vsl.abc.front.IF.astgen.PragmaFactory;
import edu.udel.cis.vsl.abc.front.IF.astgen.PragmaHandler;
import edu.udel.cis.vsl.abc.front.IF.ptree.ParseTree;
import edu.udel.cis.vsl.abc.front.c.astgen.OMPPragmaHandler;

public class CommonPragmaFactory implements PragmaFactory {

	private ASTBuilder astBuilder;

	public CommonPragmaFactory(ASTBuilder astBuilder) {
		this.astBuilder = astBuilder;
	}

	@Override
	public PragmaHandler newHandler(String code, ParseTree parseTree) {
		switch (code) {
		case "CIVL":
			return new CIVLPragmaHandler(astBuilder, parseTree);
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
