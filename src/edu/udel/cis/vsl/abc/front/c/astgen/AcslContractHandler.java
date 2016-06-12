package edu.udel.cis.vsl.abc.front.c.astgen;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.err.IF.ABCRuntimeException;
import edu.udel.cis.vsl.abc.front.c.parse.AcslParser;
import edu.udel.cis.vsl.abc.front.c.parse.CParser.RuleKind;
import edu.udel.cis.vsl.abc.front.c.preproc.AcslLexer;
import edu.udel.cis.vsl.abc.front.c.ptree.CParseTree;
import edu.udel.cis.vsl.abc.front.common.astgen.SimpleScope;
import edu.udel.cis.vsl.abc.front.common.parse.TreeUtils;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;

/**
 * This is responsible for translating a CParseTree that represents an ACSL
 * contract specification for a function or a loop into an ordered list of
 * Contract nodes.
 * 
 * @author Manchun Zheng (zmanchun)
 *
 */
public class AcslContractHandler {

	/**
	 * The kind of the contract: either for a function or for a loop.
	 * 
	 * @author Manchun Zheng
	 *
	 */
	public enum AcslContractKind {
		FUNCTION_CONTRACT, LOOP_CONTRACT
	}

	/**
	 * the node factor
	 */
	private NodeFactory nodeFactory;

	/**
	 * the token factory
	 */
	private TokenFactory tokenFactory;

	/**
	 * creates a new instance of ACSL contract handler
	 * 
	 * @param factory
	 *            the node factory to be used for creating new AST nodes
	 * @param tokenFactory
	 *            the token factory to be used
	 */
	public AcslContractHandler(NodeFactory factory, TokenFactory tokenFactory) {
		this.nodeFactory = factory;
		this.tokenFactory = tokenFactory;
	}

	/**
	 * translates a text representing an ACSL contract block into a list of
	 * contract nodes
	 * 
	 * @param startLine
	 *            the start line of the contract block in the original source
	 *            file
	 * @param text
	 *            the text representing the whole ACSL contract block, starting
	 *            with <code>/*@</code> and ending at <code>*\/</code>.
	 * @param scope
	 *            the current scope, which is the same as the scope of the loop
	 *            or the parameter scope of the function.
	 * @param formation
	 *            the formation to be used for transforming tokens into CToken.
	 * @param kind
	 *            the kind of the contract, whether it is for a loop or for a
	 *            function.
	 * @return a list of contract nodes which is the result of the translation.
	 * @throws SyntaxException
	 *             if there are any syntax errors
	 */
	public List<ContractNode> translateContracts(int startLine, String text,
			SimpleScope scope, Formation formation, AcslContractKind kind)
					throws SyntaxException {
		CParseTree contractTree = this.parse(startLine, text, formation, kind);
		AcslContractWorker worker = new AcslContractWorker(nodeFactory,
				tokenFactory, contractTree);

		return worker.generateASTNodes(scope);
	}

	/**
	 * parses a text which represents an ACSL contract block into a CParseTree.
	 * 
	 * @param startLine
	 *            the start line of the contract block in the original source
	 *            file
	 * @param text
	 *            the text representing the whole ACSL contract block, starting
	 *            with <code>/*@</code> and ending at <code>*\/</code>.
	 * @param formation
	 *            the formation to be used for transforming tokens into CToken.
	 * @param kind
	 *            the kind of the contract, whether it is for a loop or for a
	 *            function.
	 * @return the CParseTree after the parsing
	 * @throws SyntaxException
	 *             if there are any syntax errors.
	 */
	private CParseTree parse(int startLine, String text, Formation formation,
			AcslContractKind kind) throws SyntaxException {
		ANTLRStringStream input = new ANTLRStringStream(text);
		AcslLexer lexer = new AcslLexer(input);

		// TODO: need to filter out @s (where is this done?) and
		// preprocess the sub-expressions occurring in the contracts

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		AcslParser parser = new AcslParser(tokens);
		CommonTree tree;

		updateLineNumber(startLine - 1, tokens);
		try {
			switch (kind) {
			case FUNCTION_CONTRACT:
				tree = (CommonTree) parser.function_contract().getTree();
				break;
			case LOOP_CONTRACT:
				tree = (CommonTree) parser.loop_contract().getTree();
				break;
			default:
				throw new SyntaxException("unknown ACSL contract kind: " + kind,
						null);
			}
		} catch (RecognitionException e) {
			throw new ABCRuntimeException(e.getMessage());
		}
		TreeUtils.postProcessTree(tree);
		return new CParseTree(Language.CIVL_C, RuleKind.CONTRACT,
				this.tokenFactory.getCivlcTokenSourceByTokens(
						tokens.getTokens(), formation),
				tree);
	}

	/**
	 * updates the line number of each token using the given offset.
	 * 
	 * @param offset
	 *            the offset to be used for the update
	 * @param tokens
	 *            the list of tokens whose line numbers are to be updated
	 */
	private void updateLineNumber(int offset, CommonTokenStream tokens) {
		for (Token token : tokens.getTokens()) {
			token.setLine(token.getLine() + offset);
		}
	}
}
