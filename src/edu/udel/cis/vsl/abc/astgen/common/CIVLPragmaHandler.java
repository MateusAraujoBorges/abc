package edu.udel.cis.vsl.abc.astgen.common;

import java.util.List;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.PragmaNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.astgen.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.astgen.IF.ASTBuilderWorker;
import edu.udel.cis.vsl.abc.astgen.IF.PragmaHandler;
import edu.udel.cis.vsl.abc.astgen.IF.SimpleScope;
import edu.udel.cis.vsl.abc.front.IF.parse.CParser;
import edu.udel.cis.vsl.abc.front.IF.parse.ParseException;
import edu.udel.cis.vsl.abc.front.IF.parse.Parse.RuleKind;
import edu.udel.cis.vsl.abc.front.IF.token.CTokenSource;
import edu.udel.cis.vsl.abc.front.IF.token.Source;
import edu.udel.cis.vsl.abc.front.IF.token.SyntaxException;
import edu.udel.cis.vsl.abc.front.c.ptree.CParseTree;

public class CIVLPragmaHandler implements PragmaHandler {

	private NodeFactory nodeFactory;

	private CParser parser;

	private CParseTree parseTree;

	ASTBuilderWorker worker;

	public CIVLPragmaHandler(ASTBuilder builder, CParser parser,
			CParseTree parseTree) {
		this.parser = parser;
		this.nodeFactory = builder.getASTFactory().getNodeFactory();
		this.parseTree = parseTree;
		this.worker = builder.getWorker(parseTree);
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
			throws SyntaxException {
		CTokenSource tokens = pragmaNode.newTokenSource();
		Source source = pragmaNode.getSource();

		try {
			CParseTree pragmaTree = parser.parse(RuleKind.BLOCK_ITEM, tokens,
					scope.getScopeSymbolStack());
			List<BlockItemNode> blockList = worker.translateBlockItem(
					pragmaTree.getRoot(), scope);

			return blockList.size() == 1 ? blockList.get(0) : nodeFactory
					.newCompoundStatementNode(source, blockList);
		} catch (ParseException e) {
			throw new SyntaxException(e.getMessage(), source);
		}
	}

	@Override
	public CParseTree getParseTree() {
		return parseTree;
	}

}
