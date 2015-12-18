package edu.udel.cis.vsl.abc.astgen.c;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.astgen.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.astgen.IF.ASTBuilderWorker;
import edu.udel.cis.vsl.abc.astgen.IF.PragmaFactory;
import edu.udel.cis.vsl.abc.astgen.common.CommonPragmaFactory;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.front.IF.parse.CParser;
import edu.udel.cis.vsl.abc.front.IF.ptree.ParseTree;
import edu.udel.cis.vsl.abc.front.IF.token.SyntaxException;
import edu.udel.cis.vsl.abc.front.c.ptree.CParseTree;

public class CASTBuilder implements ASTBuilder {

	private ASTFactory astFactory;

	private PragmaFactory pragmaFactory;

	private Configuration config;

	public CASTBuilder(ASTFactory astFactory, CParser parser) {
		this.astFactory = astFactory;
		pragmaFactory = new CommonPragmaFactory(this, parser);
	}

	@Override
	public AST getTranslationUnit(Configuration config, ParseTree tree)
			throws SyntaxException {
		this.config = config;

		ASTBuilderWorker worker = getWorker(tree);
		SequenceNode<BlockItemNode> rootNode = worker.translateRoot();
		AST ast = astFactory.newAST(rootNode,
				((CParseTree) tree).getSourceFiles());

		return ast;
	}

	@Override
	public ASTBuilderWorker getWorker(ParseTree tree) {
		return new CASTBuilderWorker(config, (CParseTree) tree, astFactory,
				pragmaFactory);
	}

	@Override
	public ASTFactory getASTFactory() {
		return astFactory;
	}

	@Override
	public PragmaFactory getPragmaFactory() {
		return pragmaFactory;
	}
}
