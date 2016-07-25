package edu.udel.cis.vsl.abc.front.fortran.astgen;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.front.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.front.IF.ParseTree;
import edu.udel.cis.vsl.abc.front.common.astgen.PragmaFactory;
import edu.udel.cis.vsl.abc.front.fortran.ptree.FortranTree;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class FortranASTBuilder implements ASTBuilder {

	private ASTFactory astFactory;

	private Configuration config;

	public FortranASTBuilder(Configuration configuration,
			ASTFactory astFactory) {
		this.config = configuration;
		this.astFactory = astFactory;
	}

	@Override
	public AST getTranslationUnit(ParseTree tree) throws SyntaxException {
		FortranTree fTree = (FortranTree) tree;
		String filePath = "";
		FortranASTBuilderWorker worker = new FortranASTBuilderWorker(config,
				fTree, astFactory, filePath);

		return worker.generateAST();
	}

	@Override
	public ASTFactory getASTFactory() {
		return this.astFactory;
	}

	@Override
	public PragmaFactory getPragmaFactory() {
		return null; // No progma for Fortran
	}
}
