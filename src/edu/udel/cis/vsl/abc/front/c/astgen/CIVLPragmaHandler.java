package edu.udel.cis.vsl.abc.front.c.astgen;

import java.util.List;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.PragmaNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.front.IF.parse.Parse;
import edu.udel.cis.vsl.abc.front.IF.parse.ParseException;
import edu.udel.cis.vsl.abc.front.IF.parse.Parser;
import edu.udel.cis.vsl.abc.front.IF.parse.Parser.RuleKind;
import edu.udel.cis.vsl.abc.front.IF.ptree.ParseTree;
import edu.udel.cis.vsl.abc.front.common.astgen.PragmaHandler;
import edu.udel.cis.vsl.abc.front.common.astgen.SimpleScope;
import edu.udel.cis.vsl.abc.token.IF.CTokenSource;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class CIVLPragmaHandler extends PragmaHandler {

	private NodeFactory nodeFactory;

	private Parser parser;

	private ParseTree parseTree;

	CASTBuilderWorker worker;

	public CIVLPragmaHandler(CASTBuilder builder, ParseTree parseTree) {
		this.nodeFactory = builder.getASTFactory().getNodeFactory();
		this.parseTree = parseTree;
		this.worker = builder.getWorker(parseTree);
		this.parser = Parse.newParser(parseTree.getLanguage());
	}

	@Override
	public EntityKind getEntityKind() {
		return EntityKind.PRAGMA_HANDLER;
	}

	@Override
	public String getName() {
		return "CIVL";
	}

	@Override
	public ASTNode processPragmaNode(PragmaNode pragmaNode, SimpleScope scope)
			throws SyntaxException, ParseException {
		CTokenSource tokens = pragmaNode.newTokenSource();
		Source source = pragmaNode.getSource();
		ParseTree pragmaTree = parser.parse(RuleKind.BLOCK_ITEM, tokens,
				scope.getScopeSymbolStack());
		List<BlockItemNode> blockList = worker.translateBlockItem(
				pragmaTree.getRoot(), scope);

		return blockList.size() == 1 ? blockList.get(0) : nodeFactory
				.newCompoundStatementNode(source, blockList);
	}

	@Override
	public ParseTree getParseTree() {
		return parseTree;
	}

}
