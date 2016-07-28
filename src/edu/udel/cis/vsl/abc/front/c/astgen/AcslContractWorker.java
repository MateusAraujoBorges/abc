package edu.udel.cis.vsl.abc.front.c.astgen;

import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ACCESS_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.AMPERSAND;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.AND;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ANYACT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ARROW;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ASSIGN;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.BEQUIV_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.BIMPLIES_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.BITOR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.BITXOR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.BOOLEAN;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.CALL_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.CAST;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.CHAR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.CHARACTER_CONSTANT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.COMMA;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.C_TYPE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.DIV;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.DOT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.DOTDOT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.DOUBLE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ELLIPSIS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EQUALS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EQUIV_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_BASE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_INTS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_PARENTHESIZED;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_PLUS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_SUB;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.FALSE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.FALSE_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.FLOAT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.FLOATING_CONSTANT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.FUNC_CALL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.GT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.GTE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.HASH;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.IDENTIFIER;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.IMPLIES_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.INDEX;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.INT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.INTEGER;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.INTEGER_CONSTANT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.LOGIC_TYPE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.LONG;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.LT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.LTE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.MOD;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.MPI_CONSTANT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.MPI_EXPRESSION;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.NEQ;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.NOT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.NOTHING;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.OPERATOR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.OR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.PLUS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.QMARK;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.QUANTIFIED;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.READ_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.REAL_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.REMOTE_ACCESS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.RESULT_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SELF;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SET_BINDERS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SHIFTLEFT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SHIFTRIGHT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SHORT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SIZEOF;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.STAR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.STRING_LITERAL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SUB;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.TERM_PARENTHESIZED;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.TILDE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.TRUE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.TRUE_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.TYPE_BUILTIN;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.TYPE_ID;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.VALID;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.VAR_ID;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.VAR_ID_BASE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.VAR_ID_SQUARE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.VAR_ID_STAR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.VOID;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.WRITE_ACSL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.XOR_ACSL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.PairNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AllocationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AssignsOrReadsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AssumesNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.BehaviorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CompletenessNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CompositeEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CompositeEventNode.EventOperator;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.EnsuresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.GuardsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPICollectiveBlockNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPICollectiveBlockNode.MPICollectiveKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIContractConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIContractConstantNode.MPIConstantKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIContractExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIContractExpressionNode.MPIContractExpressionKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MemoryEventNode.MemoryEventNodeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.RequiresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.WaitsforNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.CharacterConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode.ConstantKind;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.EnumerationConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.QuantifiedExpressionNode.Quantifier;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.StringLiteralNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardSignedIntegerType.SignedIntKind;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardUnsignedIntegerType.UnsignedIntKind;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.abc.front.c.parse.AcslParser;
import edu.udel.cis.vsl.abc.front.c.ptree.CParseTree;
import edu.udel.cis.vsl.abc.front.common.astgen.SimpleScope;
import edu.udel.cis.vsl.abc.token.IF.CharacterToken;
import edu.udel.cis.vsl.abc.token.IF.CivlcToken;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.StringToken;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;

/**
 * This is responsible for translating a CParseTree that represents an ACSL
 * contract specification for a function or a loop into an ordered list of
 * Contract nodes. It serves as a helper for {@link AcslContractHandler}. <br>
 * Precondition: all tokens are preprocessed with the regular program
 * components. <br>
 * Note: there are no separated lexer for the ACSL parser. All keywords are
 * recognized as IDENTIFIER or EXTENDED_IDENTIFIER and semantic predicates are
 * used to match different keywords, like <code>requires</code>,
 * <code>ensures</code>, <code>assumes</code> as IDENTIFIER and
 * <code>\valid</code>, <code>\result</code>, <code>\valid</code> as
 * EXTENDED_IDENTIFIER.
 * 
 * @author Manchun Zheng (zmanchun)
 *
 */
public class AcslContractWorker {

	/**
	 * the parse tree to be translated
	 */
	private CParseTree parseTree;

	/**
	 * the node factory to be used for creating AST nodes
	 */
	private NodeFactory nodeFactory;

	/**
	 * the token factory
	 */
	private TokenFactory tokenFactory;

	/**
	 * the type factory
	 */
	private TypeFactory typeFactory;

	/**
	 * the formation to be used for transforming ANTLR tokens into CTokens
	 */
	private Formation formation;

	/* ******************** Constants ******************* */
	private final String MPI_COMM_RANK = "\\mpi_comm_rank";
	private final String MPI_COMM_SIZE = "\\mpi_comm_size";

	/**
	 * creates a new instance of AcslContractWorker
	 * 
	 * @param factory
	 *            the node factory to be used
	 * @param tokenFactory
	 *            the token factory to be used
	 * @param parseTree
	 *            the parse tree to be translated
	 */
	public AcslContractWorker(NodeFactory factory, TokenFactory tokenFactory,
			CParseTree parseTree) {
		this.nodeFactory = factory;
		this.tokenFactory = tokenFactory;
		this.parseTree = parseTree;
		this.typeFactory = nodeFactory.typeFactory();
		formation = tokenFactory.newTransformFormation("ACSL", "contract");
	}

	/**
	 * translates the parse tree to a list of contract nodes. Currently, two
	 * kinds of contracts are supported, one is function contract, the other is
	 * loop annotation.
	 * 
	 * @param scope
	 *            the scope of the contract
	 * @return the list of contract nodes which is the result of translating the
	 *         parse tree
	 * @throws SyntaxException
	 *             if there are syntax errors during the translation
	 */
	public List<ContractNode> generateContractNodes(SimpleScope scope)
			throws SyntaxException {
		CommonTree contractTree = parseTree.getRoot();

		switch (contractTree.getType()) {
		case AcslParser.FUNC_CONTRACT:
			return translateFunctionContractBlock(
					(CommonTree) contractTree.getChild(0), scope);
		case AcslParser.LOOP_CONTRACT:
			return translateLoopContractBlock(
					(CommonTree) contractTree.getChild(0), scope);
		default:
			throw this.error("unknown kind of contract", contractTree);
		}
	}

	/**
	 * translates the contract for a loop.
	 * 
	 * @param tree
	 *            the tree to be translated, which represented the contracts of
	 *            the loop
	 * @param scope
	 *            the current scope
	 * @return the list of contracts associated with a loop
	 * @throws SyntaxException
	 *             if there are syntax errors
	 */
	private List<ContractNode> translateLoopContractBlock(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int numChildren = tree.getChildCount();
		List<ContractNode> result = new ArrayList<>();

		assert tree.getType() == AcslParser.LOOP_CONTRACT_BLOCK;
		for (int i = 0; i < numChildren; i++) {
			CommonTree loopIterm = (CommonTree) tree.getChild(i);
			int loopItemKind = loopIterm.getType();

			switch (loopItemKind) {
			case AcslParser.LOOP_CLAUSE:
				result.add(this.translateLoopClause(
						(CommonTree) loopIterm.getChild(0), scope));
				break;
			case AcslParser.LOOP_BEHAVIOR:
			case AcslParser.LOOP_VARIANT:
			default:
				throw this.error("unknown kind of loop contract", loopIterm);
			}
		}
		return result;
	}

	/**
	 * translates a loop clause, which could be a loop invariant, an assigns
	 * clause, an allocate clause, or a free clause. Currently, only loop
	 * invariant is supported.
	 * 
	 * @param tree
	 *            the tree represented a loop contract clause
	 * @param scope
	 *            the current scope
	 * @return the contract node represented a loop clause
	 * @throws SyntaxException
	 *             if there are some syntax errors
	 */
	private ContractNode translateLoopClause(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		int loopClauseKind = tree.getType();
		Source source = this.newSource(tree);

		switch (loopClauseKind) {
		case AcslParser.LOOP_INVARIANT: {
			CommonTree exprTree = (CommonTree) tree.getChild(0);
			ExpressionNode expression = this.translateExpression(exprTree,
					scope);

			return this.nodeFactory.newInvariantNode(source, true, expression);
		}
		case AcslParser.LOOP_ASSIGNS:
		case AcslParser.LOOP_ALLOC:
		case AcslParser.LOOP_FREE:
		default:
			throw this.error("unkown kind of loop contract clause", tree);
		}
	}

	/**
	 * translates a contract block associated with a function
	 * 
	 * @param tree
	 *            the tree representing the contract block
	 * @param scope
	 *            the current scope, which is the scope of the function
	 *            parameter
	 * @return the list of contract nodes after translation
	 * @throws SyntaxException
	 *             if there are syntax errors
	 */
	private List<ContractNode> translateFunctionContractBlock(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int numChildren = tree.getChildCount();
		List<ContractNode> result = new ArrayList<>();

		assert tree.getType() == AcslParser.FUNC_CONTRACT_BLOCK;
		for (int i = 0; i < numChildren; i++) {
			CommonTree child = (CommonTree) tree.getChild(i);
			int childKind = child.getType();

			switch (childKind) {
			case AcslParser.CLAUSE_NORMAL:
				result.add(this.translateContractClause(
						(CommonTree) child.getChild(0), scope));
				break;
			case AcslParser.CLAUSE_BEHAVIOR:
				result.add(this.translateBehavior(
						(CommonTree) child.getChild(0), scope));
				break;
			case AcslParser.CLAUSE_COMPLETE:
				result.add(this.translateCompleteness(
						(CommonTree) child.getChild(0), scope));
				break;
			case AcslParser.MPI_COLLECTIVE:
				result.add(this.translateMPICollectiveBlock(
						this.parseTree.source(child), child, scope));
				break;
			default:
				throw this.error("Unknown contract kind", tree);
			}
		}
		return result;
	}

	/**
	 * translates the ACSL completeness clause for behavior, which could be
	 * COMPLETE or DISJOINT.
	 * 
	 * @param tree
	 *            the tree representing the completeness clause
	 * @param scope
	 *            the current scope
	 * @return the completeness node which is the result of translating the
	 *         given tree
	 * @throws SyntaxException
	 *             if there are some syntax errors
	 */
	private CompletenessNode translateCompleteness(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int kind = tree.getType();
		boolean isComplete = false;
		SequenceNode<IdentifierNode> idList = this
				.translateIdList((CommonTree) tree.getChild(2), scope);
		Source source = this.parseTree.source(tree);

		switch (kind) {
		case AcslParser.BEHAVIOR_COMPLETE:
			isComplete = true;
			break;
		case AcslParser.BEHAVIOR_DISJOINT:
			break;
		default:
			throw this.error("Unknown kind of completeness clause", tree);
		}
		return this.nodeFactory.newCompletenessNode(source, isComplete, idList);
	}

	/**
	 * translates a list of identifiers
	 * 
	 * @param tree
	 *            a tree that represents a list of identifiers
	 * @param scope
	 *            the current scope
	 * @return a sequence of identifier node
	 */
	private SequenceNode<IdentifierNode> translateIdList(CommonTree tree,
			SimpleScope scope) {
		int numChildren = tree.getChildCount();
		List<IdentifierNode> list = new ArrayList<>();
		Source source = this.parseTree.source(tree);

		for (int i = 0; i < numChildren; i++) {
			CommonTree idTree = (CommonTree) tree.getChild(i);

			list.add(this.translateIdentifier(idTree));
		}
		return this.nodeFactory.newSequenceNode(source, "ID list", list);
	}

	/**
	 * translates an ACSL behavior block
	 * 
	 * @param tree
	 *            the tree that represents a behavior block
	 * @param scope
	 *            the current scope
	 * @return the behavior node which is the result of translating the given
	 *         behavior block
	 * @throws SyntaxException
	 *             if there are any syntax errors.
	 */
	private BehaviorNode translateBehavior(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		CommonTree idTree = (CommonTree) tree.getChild(1);
		CommonTree bodyTree = (CommonTree) tree.getChild(2);
		IdentifierNode id = this.translateIdentifier(idTree);
		SequenceNode<ContractNode> body = this.translateBehaviorBody(bodyTree,
				scope);

		return this.nodeFactory.newBehaviorNode(this.parseTree.source(tree), id,
				body);
	}

	/**
	 * translates the body of a behavior block.
	 * 
	 * @param tree
	 *            the tree that represents the body of a behavior block
	 * @param scope
	 *            the current scope
	 * @return a sequence of contract nodes which is the result of the
	 *         translation
	 * @throws SyntaxException
	 *             if there are any syntax errors
	 */
	private SequenceNode<ContractNode> translateBehaviorBody(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		Source source = this.parseTree.source(tree);
		int numChildren = tree.getChildCount();
		List<ContractNode> clauses = new ArrayList<>();

		for (int i = 0; i < numChildren; i++) {
			CommonTree clause = (CommonTree) tree.getChild(i);

			clauses.add(this.translateContractClause(clause, scope));
		}
		return this.nodeFactory.newSequenceNode(source, "behavior body",
				clauses);
	}

	/**
	 * translates a contract clause.
	 * 
	 * @param tree
	 *            the tree that representing a contract clause
	 * @param scope
	 *            the current scope
	 * @return the contract node which is the result of the translation
	 * @throws SyntaxException
	 *             if there are any syntax errors
	 */
	private ContractNode translateContractClause(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int kind = tree.getType();

		switch (kind) {
		case AcslParser.ALLOC:
			return this.translateAllocation(tree, scope, true);
		case AcslParser.FREES:
			return this.translateAllocation(tree, scope, false);
		case AcslParser.REQUIRES_ACSL:
			return this.translateRequires(tree, scope);
		case AcslParser.ENSURES_ACSL:
			return this.translateEnsures(tree, scope);
		case AcslParser.ASSIGNS_ACSL:
			return this.translateReadsOrAssigns(tree, scope, false);
		case AcslParser.ASSUMES_ACSL:
			return this.translateAssumes(tree, scope);
		case AcslParser.READS_ACSL:
			return this.translateReadsOrAssigns(tree, scope, true);
		case AcslParser.DEPENDSON:
			return this.translateDepends(tree, scope);
		case AcslParser.EXECUTES_WHEN:
			return this.translateGuards(tree, scope);
		case AcslParser.WAITSFOR:
			return this.translateWaitsfor(tree, scope);
		default:
			throw this.error("Unknown contract clause kind", tree);
		}
	}

	private AllocationNode translateAllocation(CommonTree tree,
			SimpleScope scope, boolean isAllocates) throws SyntaxException {
		SequenceNode<ExpressionNode> memoryList = this
				.translateArgumentList((CommonTree) tree.getChild(1), scope);

		return this.nodeFactory.newAllocationNode(this.newSource(tree),
				isAllocates, memoryList);
	}

	/**
	 * translates a guard clause, which has the syntax
	 * <code>executes_when expr</code>.
	 * 
	 * @param tree
	 *            the tree that represents the guard clause
	 * @param scope
	 *            the current scope
	 * @return the guard node that is the result of the translation
	 * @throws SyntaxException
	 *             if there are some syntax errors.
	 */
	private GuardsNode translateGuards(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		CommonTree expressionTree = (CommonTree) tree.getChild(1);

		return this.nodeFactory.newGuardNode(this.newSource(tree),
				this.translateExpression(expressionTree, scope));
	}

	private WaitsforNode translateWaitsfor(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		CommonTree expressionTree = (CommonTree) tree.getChild(1);
		SequenceNode<ExpressionNode> arguments = translateArgumentList(
				expressionTree, scope);

		return nodeFactory.newWaitsforNode(newSource(tree), arguments);
	}

	/**
	 * translates an assume clause, which has the syntax
	 * <code>assumes expr</code>.
	 * 
	 * @param tree
	 *            the tree that represents an assumes clause
	 * @param scope
	 *            the current scope
	 * @return the Assumes node
	 * @throws SyntaxException
	 *             if there are any syntax errors.
	 */
	private AssumesNode translateAssumes(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		CommonTree exprTree = (CommonTree) tree.getChild(1);
		ExpressionNode predicate = this.translateExpression(exprTree, scope);
		Source source = this.parseTree.source(tree);

		return this.nodeFactory.newAssumesNode(source, predicate);
	}

	private AssignsOrReadsNode translateReadsOrAssigns(CommonTree tree,
			SimpleScope scope, boolean isRead) throws SyntaxException {
		Source source = this.parseTree.source(tree);
		SequenceNode<ExpressionNode> memoryList = translateArgumentList(
				(CommonTree) tree.getChild(1), scope);

		if (isRead)
			return this.nodeFactory.newReadsNode(source, memoryList);
		else
			return this.nodeFactory.newAssignsNode(source, memoryList);
	}

	private DependsNode translateDepends(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		Source source = this.parseTree.source(tree);
		CommonTree argumentTree = (CommonTree) tree.getChild(1);
		SequenceNode<DependsEventNode> argumentList = this
				.translateDependsEventList(argumentTree, scope);

		return this.nodeFactory.newDependsNode(source, null, argumentList);
	}

	private SequenceNode<ExpressionNode> translateArgumentList(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int numChildren = tree.getChildCount();
		List<ExpressionNode> list = new ArrayList<>();

		for (int i = 0; i < numChildren; i++) {
			CommonTree arg = (CommonTree) tree.getChild(i);

			list.add(this.translateExpression(arg, scope));
		}
		return this.nodeFactory.newSequenceNode(this.parseTree.source(tree),
				"argument list", list);
	}

	private SequenceNode<DependsEventNode> translateDependsEventList(
			CommonTree tree, SimpleScope scope) throws SyntaxException {
		int numChildren = tree.getChildCount();
		List<DependsEventNode> events = new ArrayList<>();
		Source source = this.parseTree.source(tree);

		for (int i = 0; i < numChildren; i++) {
			CommonTree event = (CommonTree) tree.getChild(i);

			events.add(this.translateDependsEvent(event, scope));
		}
		return this.nodeFactory.newSequenceNode(source, "depends event list",
				events);
	}

	private DependsEventNode translateDependsEvent(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int kind = tree.getType();

		switch (kind) {
		case EVENT_PLUS:
			EventOperator operator = EventOperator.UNION;
			return translateOperatorEvent(tree, operator, scope);
		case EVENT_SUB:
			operator = EventOperator.DIFFERENCE;
			return translateOperatorEvent(tree, operator, scope);
		case EVENT_INTS:
			operator = EventOperator.INTERSECT;
			return translateOperatorEvent(tree, operator, scope);
		case EVENT_BASE:
			return translateDependsEventBase((CommonTree) tree.getChild(0),
					scope);
		default:
			throw this.error("unknown kind of operator for depends events",
					tree);
		}
	}

	private CompositeEventNode translateOperatorEvent(CommonTree tree,
			EventOperator op, SimpleScope scope) throws SyntaxException {
		Source source = this.parseTree.source(tree);
		CommonTree leftTree = (CommonTree) tree.getChild(0),
				rightTree = (CommonTree) tree.getChild(1);
		DependsEventNode left = this.translateDependsEventBase(leftTree, scope),
				right = this.translateDependsEventBase(rightTree, scope);

		return this.nodeFactory.newOperatorEventNode(source, op, left, right);
	}

	private DependsEventNode translateDependsEventBase(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int kind = tree.getType();
		Source source = this.parseTree.source(tree);

		switch (kind) {
		case READ_ACSL: {
			SequenceNode<ExpressionNode> memList = this.translateArgumentList(
					(CommonTree) tree.getChild(1), scope);

			return nodeFactory.newMemoryEventNode(source,
					MemoryEventNodeKind.READ, memList);
		}
		case WRITE_ACSL: {
			SequenceNode<ExpressionNode> memList = this.translateArgumentList(
					(CommonTree) tree.getChild(1), scope);

			return nodeFactory.newMemoryEventNode(source,
					MemoryEventNodeKind.WRITE, memList);
		}
		case ACCESS_ACSL: {
			SequenceNode<ExpressionNode> memList = this.translateArgumentList(
					(CommonTree) tree.getChild(1), scope);

			return nodeFactory.newMemoryEventNode(source,
					MemoryEventNodeKind.REACH, memList);

		}
		case CALL_ACSL: {
			IdentifierNode function = this
					.translateIdentifier((CommonTree) tree.getChild(1));
			SequenceNode<ExpressionNode> args = this.translateArgumentList(
					(CommonTree) tree.getChild(1), scope);

			return nodeFactory.newCallEventNode(source,
					this.nodeFactory.newIdentifierExpressionNode(
							function.getSource(), function),
					args);
		}
		case NOTHING:
			return nodeFactory.newNoactNode(source);
		case ANYACT:
			return nodeFactory.newAnyactNode(source);
		case EVENT_PARENTHESIZED:
			return translateDependsEvent((CommonTree) tree.getChild(0), scope);
		default:
			throw this.error("unknown kind of nodes for depends events", tree);
		}
	}

	private RequiresNode translateRequires(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		CommonTree expressionTree = (CommonTree) tree.getChild(1);
		Source source = this.newSource(tree);
		ExpressionNode expression = this.translateExpression(expressionTree,
				scope);

		return nodeFactory.newRequiresNode(source, expression);
	}

	private EnsuresNode translateEnsures(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		Source source = this.newSource(tree);
		CommonTree expressionTree = (CommonTree) tree.getChild(1);
		ExpressionNode expression = this.translateExpression(expressionTree,
				scope);

		return nodeFactory.newEnsuresNode(source, expression);
	}

	/**
	 * Translates an expression.
	 * 
	 * @param expressionTree
	 *            any CommonTree node representing an expression
	 * @return an ExpressionNode
	 * @throws SyntaxException
	 */
	private ExpressionNode translateExpression(CommonTree expressionTree,
			SimpleScope scope) throws SyntaxException {
		Source source = this.newSource(expressionTree);
		int kind = expressionTree.getType();

		switch (kind) {
		case INTEGER_CONSTANT:
			return translateIntegerConstant(source, expressionTree);
		case FLOATING_CONSTANT:
			return translateFloatingConstant(source, expressionTree);
		case CHARACTER_CONSTANT:
			return translateCharacterConstant(source, expressionTree);
		case STRING_LITERAL:
			return translateStringLiteral(source, expressionTree);
		case IDENTIFIER: {
			IdentifierNode identifier = translateIdentifier(expressionTree);
			ExpressionNode enumerationConsant = translateEnumerationConstant(
					identifier, scope);

			return enumerationConsant != null ? enumerationConsant
					: nodeFactory.newIdentifierExpressionNode(source,
							identifier);
		}
		case TERM_PARENTHESIZED:
			return translateExpression((CommonTree) expressionTree.getChild(0),
					scope);
		case DOT:
		case ARROW:
			return translateDotOrArrow(source, expressionTree, scope);
		case OPERATOR:
			return translateOperatorExpression(source, expressionTree, scope);
		case TRUE:
		case TRUE_ACSL:
			return translateTrue(source);
		case FALSE:
		case FALSE_ACSL:
			return translateFalse(source);
		case RESULT_ACSL:
			return nodeFactory.newResultNode(source);
		case SELF:
			return nodeFactory.newSelfNode(source);
		case DOTDOT:
			return translateRegularRange(source, expressionTree, scope);
		case WRITE_ACSL:
			return translateWriteEvent(source, expressionTree, scope);
		case NOTHING:
			return this.nodeFactory.newNothingNode(source);
		case ELLIPSIS:
			return this.nodeFactory.newWildcardNode(source);
		case MPI_CONSTANT:
			return translateMPIConstantNode(expressionTree, source);
		case MPI_EXPRESSION:
			return translateMPIExpressionNode(expressionTree, source, scope);
		case VALID:
			return this.translateValidNode(expressionTree, source, scope);
		case SET_BINDERS:
			return this.translateSetBinders(expressionTree, source, scope);
		case REMOTE_ACCESS:
			return translateRemoteExpression(expressionTree, source, scope);
		case QUANTIFIED:
			return translateQuantifiedExpression(expressionTree, source, scope);
		case FUNC_CALL:
			return translateCall(source, expressionTree, scope);
		case SIZEOF:
			// return translateSizeOf(source, expressionTree, scope);
		case CAST:
			// return nodeFactory.newCastNode(
			// source,
			// translateTypeName((CommonTree) expressionTree.getChild(0),
			// scope),
			// translateExpression(
			// (CommonTree) expressionTree.getChild(1), scope));
		case AcslParser.OBJECT_OF:
			return translateObjectOf(source, expressionTree, scope);
		default:
			throw error("Unknown expression kind", expressionTree);
		} // end switch
	}

	private ExpressionNode translateObjectOf(Source source, CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		CommonTree operandTree = (CommonTree) tree.getChild(2);
		ExpressionNode operand = this.translateExpression(operandTree, scope);

		return nodeFactory.newObjectofNode(source, operand);
	}

	/**
	 * translate a quantified expression. e.g. \\forall | \\exists type_name
	 * identifier; predicate
	 * 
	 * @param expressionTree
	 * @param source
	 * @param scope
	 * @return
	 * @throws SyntaxException
	 */
	private ExpressionNode translateQuantifiedExpression(
			CommonTree expressionTree, Source source, SimpleScope scope)
			throws SyntaxException {
		CommonTree quantifierTree = (CommonTree) expressionTree.getChild(0);
		CommonTree bindersTree = (CommonTree) expressionTree.getChild(1);
		CommonTree restrictTree = (CommonTree) expressionTree.getChild(2);
		CommonTree predTree = (CommonTree) expressionTree.getChild(3);
		ExpressionNode restrict, pred;
		SequenceNode<VariableDeclarationNode> binders;
		Quantifier quantifier;
		// If the quantified expression has more than one binder, it will be
		// translated into several quantifiedExpressions each of which has exact
		// one binder:
		// boolean firstQuantifiedExpr = true;
		// ExpressionNode result = null;
		List<PairNode<SequenceNode<VariableDeclarationNode>, ExpressionNode>> boundVariableList = new LinkedList<>();

		if (quantifierTree.getType() == AcslParser.FORALL_ACSL)
			quantifier = Quantifier.FORALL;
		else if (quantifierTree.getType() == AcslParser.EXISTS_ACSL)
			quantifier = Quantifier.EXISTS;
		else
			throw error("Unexpexted quantifier " + quantifierTree.getType(),
					quantifierTree);
		binders = translateBinders(bindersTree, source, scope);
		boundVariableList.add(nodeFactory.newPairNode(source, binders, null));
		restrict = translateExpression(restrictTree, scope);
		pred = translateExpression(predTree, scope);
		// for (VariableDeclarationNode binder : binders) {
		// QuantifiedExpressionNode quantifiedExprNode = nodeFactory
		// .newQuantifiedExpressionNode(source, quantifier,
		// binder.copy(), restrict, pred);
		//
		// if (firstQuantifiedExpr) {
		// result = quantifiedExprNode;
		// firstQuantifiedExpr = false;
		// } else
		// result = nodeFactory.newOperatorNode(source, Operator.LAND,
		// result, quantifiedExprNode);
		// }
		// if (result == null) {
		// // no binder, just return the predicate:
		// return pred;
		// }
		return nodeFactory.newQuantifiedExpressionNode(source, quantifier,
				nodeFactory.newSequenceNode(source,
						"bound variable declaration list", boundVariableList),
				restrict, pred);
	}

	/**
	 * <pre>
	 * LCURLY term BAR binders (SEMICOL term)? RCURLY 
	 *   ->^(SET_BINDERS term binders term?)
	 * </pre>
	 * 
	 * @param tree
	 * @param source
	 * @param scope
	 * @return
	 * @throws SyntaxException
	 */
	private ExpressionNode translateSetBinders(CommonTree tree, Source source,
			SimpleScope scope) throws SyntaxException {
		SimpleScope newScope = new SimpleScope(scope);
		CommonTree termTree = (CommonTree) tree.getChild(0);
		CommonTree bindersTree = (CommonTree) tree.getChild(1);
		CommonTree predicateTree = tree.getChildCount() > 2
				? (CommonTree) tree.getChild(2) : null;
		ExpressionNode term = this.translateExpression(termTree, newScope),
				predicate = null;
		SequenceNode<VariableDeclarationNode> binders = this
				.translateBinders(bindersTree, source, newScope);

		if (predicateTree != null)
			predicate = this.translateExpression(predicateTree, newScope);
		return this.nodeFactory.newMemorySetNode(source, term, binders,
				predicate);
	}

	private ExpressionNode translateRemoteExpression(CommonTree tree,
			Source source, SimpleScope scope) throws SyntaxException {
		SimpleScope newScope = new SimpleScope(scope);
		CommonTree procTree = (CommonTree) tree.getChild(1);
		CommonTree exprTree = (CommonTree) tree.getChild(2);
		ExpressionNode exprArg, procArg;

		exprArg = translateExpression(exprTree, newScope);
		procArg = translateExpression(procTree, newScope);
		return nodeFactory.newRemoteOnExpressionNode(source, procArg, exprArg);
	}

	private SequenceNode<VariableDeclarationNode> translateBinders(
			CommonTree tree, Source source, SimpleScope scope)
			throws SyntaxException {
		int count = tree.getChildCount();
		List<VariableDeclarationNode> vars = new LinkedList<>();

		for (int i = 0; i < count; i++) {
			CommonTree binder = (CommonTree) tree.getChild(i);

			vars.addAll(this.translateBinder(binder, scope));
		}
		return this.nodeFactory.newSequenceNode(source, "Binder List", vars);
	}

	private List<VariableDeclarationNode> translateBinder(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		CommonTree typeTree = (CommonTree) tree.getChild(0);
		int numChild = tree.getChildCount();
		TypeNode type = this.translateTypeExpr(typeTree, scope);
		List<VariableDeclarationNode> result = new LinkedList<>();

		for (int i = 1; i < numChild; i++) {
			CommonTree varIdent = (CommonTree) tree.getChild(i);

			result.add(
					this.translateVariableIdent(varIdent, scope, type.copy()));
		}
		return result;
	}

	private TypeNode translateTypeExpr(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		int kind = tree.getType();

		switch (kind) {
		case LOGIC_TYPE:
			return this.translateLogicType((CommonTree) tree.getChild(0),
					scope);
		case C_TYPE:
			return this.translateCType((CommonTree) tree.getChild(0), scope);
		default:
			throw this.error("unkown kind of tyep expression", tree);
		}
	}

	private TypeNode translateCType(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		int kind = tree.getType();
		Source source = this.newSource(tree);

		switch (kind) {
		case CHAR:
			return this.nodeFactory.newBasicTypeNode(source,
					BasicTypeKind.CHAR);
		case DOUBLE:
			return this.nodeFactory.newBasicTypeNode(source,
					BasicTypeKind.DOUBLE);
		case FLOAT:
			return this.nodeFactory.newBasicTypeNode(source,
					BasicTypeKind.FLOAT);
		case INT:
			return this.nodeFactory.newBasicTypeNode(source, BasicTypeKind.INT);
		case LONG:
			return this.nodeFactory.newBasicTypeNode(source,
					BasicTypeKind.LONG);
		case SHORT:
			return this.nodeFactory.newBasicTypeNode(source,
					BasicTypeKind.SHORT);
		case VOID:
			return this.nodeFactory.newVoidTypeNode(source);
		default:
			throw this.error("unknown C type", tree);
		}
	}

	private TypeNode translateLogicType(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		int kind = tree.getType();
		Source source = this.newSource(tree);

		switch (kind) {
		case TYPE_BUILTIN: {
			int typeKind = tree.getChild(0).getType();

			switch (typeKind) {
			case BOOLEAN:
				return this.nodeFactory.newBasicTypeNode(source,
						BasicTypeKind.BOOL);
			case INTEGER:
				return this.nodeFactory.newBasicTypeNode(source,
						BasicTypeKind.INT);
			case REAL_ACSL:
				return this.nodeFactory.newBasicTypeNode(source,
						BasicTypeKind.REAL);
			default:
				throw this.error("unknown built-in logic type", tree);
			}
		}
		case TYPE_ID:
			return this.nodeFactory.newTypedefNameNode(
					this.translateIdentifier((CommonTree) tree.getChild(0)),
					null);
		default:
			throw this.error("unknown kind of logic type", tree);
		}
	}

	private VariableDeclarationNode translateVariableIdent(CommonTree tree,
			SimpleScope scope, TypeNode baseType) throws SyntaxException {
		int kind = tree.getType();
		Source source = this.newSource(tree);

		switch (kind) {
		case VAR_ID_STAR: {
			VariableDeclarationNode baseVar = this.translateVariableIdentBase(
					(CommonTree) tree.getChild(0), source, scope, baseType);
			TypeNode baseVarType, type;

			baseVarType = baseVar.getTypeNode();
			baseVarType.remove();
			type = this.nodeFactory.newPointerTypeNode(source, baseVarType);
			baseVar.setTypeNode(type);
			return baseVar;
		}
		case VAR_ID_SQUARE: {
			VariableDeclarationNode baseVar = this.translateVariableIdentBase(
					(CommonTree) tree.getChild(0), source, scope, baseType);
			TypeNode baseVarType, type;

			baseVarType = baseVar.getTypeNode();
			baseVarType.remove();
			type = this.nodeFactory.newArrayTypeNode(source, baseVarType, null);
			baseVar.setTypeNode(type);
			return baseVar;
		}
		case VAR_ID:
			return this.translateVariableIdentBase(
					(CommonTree) tree.getChild(0), source, scope, baseType);
		default:
			throw this.error("unknown kind of variable identity", tree);
		}
	}

	private VariableDeclarationNode translateVariableIdentBase(CommonTree tree,
			Source source, SimpleScope scope, TypeNode baseType)
			throws SyntaxException {
		int kind = tree.getType();

		switch (kind) {
		case IDENTIFIER: {
			IdentifierNode identifier = this.translateIdentifier(tree);

			return this.nodeFactory.newVariableDeclarationNode(
					identifier.getSource(), identifier, baseType);
		}
		case VAR_ID_BASE:
			return this.translateVariableIdent((CommonTree) tree.getChild(0),
					scope, baseType);
		default:
			throw this.error("unknown kind of variable identity base", tree);
		}
	}

	// ////////////////////////////////////
	private ExpressionNode translateValidNode(CommonTree tree, Source source,
			SimpleScope scope) throws SyntaxException {
		CommonTree pointer = (CommonTree) tree.getChild(1);
		ExpressionNode ptrNode;
		ExpressionNode rangeNode;

		ptrNode = translateExpression(pointer, scope);
		if (tree.getChildCount() > 2) {
			CommonTree range;

			range = (CommonTree) tree.getChild(2);
			rangeNode = translateExpression(range, scope);
			return nodeFactory.newOperatorNode(source, Operator.VALID, ptrNode,
					rangeNode);
		} else
			return nodeFactory.newOperatorNode(source, Operator.VALID, ptrNode);
	}

	private ExpressionNode translateWriteEvent(Source source,
			CommonTree expressionTree, SimpleScope scope) {
		// TODO Auto-generated method stub
		return null;
	}

	private IntegerConstantNode translateIntegerConstant(Source source,
			CommonTree integerConstant) throws SyntaxException {
		return nodeFactory.newIntegerConstantNode(source,
				integerConstant.getText());
	}

	private FloatingConstantNode translateFloatingConstant(Source source,
			CommonTree floatingConstant) throws SyntaxException {
		return nodeFactory.newFloatingConstantNode(source,
				floatingConstant.getText());
	}

	private CharacterConstantNode translateCharacterConstant(Source source,
			CommonTree characterConstant) throws SyntaxException {
		CharacterToken token = (CharacterToken) characterConstant.getToken();

		return nodeFactory.newCharacterConstantNode(source,
				characterConstant.getText(), token.getExecutionCharacter());
	}

	private ConstantNode translateTrue(Source source) {
		return nodeFactory.newBooleanConstantNode(source, true);
	}

	private ConstantNode translateFalse(Source source) {
		return nodeFactory.newBooleanConstantNode(source, false);
	}

	private StringLiteralNode translateStringLiteral(Source source,
			CommonTree stringLiteral) throws SyntaxException {
		StringToken token = (StringToken) stringLiteral.getToken();

		return nodeFactory.newStringLiteralNode(source, stringLiteral.getText(),
				token.getStringLiteral());
	}

	private ExpressionNode translateRegularRange(Source source,
			CommonTree expressionTree, SimpleScope scope)
			throws SyntaxException {
		// regular range expression lo..hi or lo..hi#step
		ExpressionNode loNode = translateExpression(
				(CommonTree) expressionTree.getChild(0), scope);
		ExpressionNode hiNode = translateExpression(
				(CommonTree) expressionTree.getChild(1), scope);
		if (expressionTree.getChildCount() > 2) {
			CommonTree stepTree = (CommonTree) expressionTree.getChild(2);

			if (stepTree != null /* && stepTree.getType() != ABSENT */) {
				ExpressionNode stepNode = translateExpression(stepTree, scope);

				return nodeFactory.newRegularRangeNode(source, loNode, hiNode,
						stepNode);
			}
		}
		return nodeFactory.newRegularRangeNode(source, loNode, hiNode);

	}

	/**
	 * Translates a function call expression.
	 * 
	 * @param callTree
	 *            CommonTree node of type CALL, representing a function call
	 * @return a FunctionCallNode corresponding to the ANTLR tree
	 * @throws SyntaxException
	 */
	private FunctionCallNode translateCall(Source source, CommonTree callTree,
			SimpleScope scope) throws SyntaxException {
		CommonTree functionTree = (CommonTree) callTree.getChild(0);
		// CommonTree contextArgumentListTree = (CommonTree)
		// callTree.getChild(2);
		CommonTree argumentListTree = (CommonTree) callTree.getChild(1);
		ExpressionNode functionNode = translateExpression(functionTree, scope);
		// int numContextArgs = contextArgumentListTree.getChildCount();
		int numArgs = argumentListTree.getChildCount();
		// List<ExpressionNode> contextArgumentList = new
		// LinkedList<ExpressionNode>();
		List<ExpressionNode> argumentList = new LinkedList<ExpressionNode>();

		for (int i = 0; i < numArgs; i++) {
			CommonTree argumentTree = (CommonTree) argumentListTree.getChild(i);
			ExpressionNode argumentNode = translateExpression(argumentTree,
					scope);

			argumentList.add(argumentNode);
		}
		return nodeFactory.newFunctionCallNode(source, functionNode,
				new LinkedList<ExpressionNode>(), argumentList, null);
	}

	/**
	 * 
	 * @param expressionTree
	 * @return
	 * @throws SyntaxException
	 */
	private ExpressionNode translateDotOrArrow(Source source,
			CommonTree expressionTree, SimpleScope scope)
			throws SyntaxException {
		int kind = expressionTree.getType();
		CommonTree argumentNode = (CommonTree) expressionTree.getChild(0);
		CommonTree fieldNode = (CommonTree) expressionTree.getChild(1);
		ExpressionNode argument = translateExpression(argumentNode, scope);
		IdentifierNode fieldName = translateIdentifier(fieldNode);

		if (kind == DOT)
			return nodeFactory.newDotNode(source, argument, fieldName);
		else
			return nodeFactory.newArrowNode(source, argument, fieldName);
	}

	/**
	 * 
	 * @param expressionTree
	 * @return
	 * @throws SyntaxException
	 */
	private OperatorNode translateOperatorExpression(Source source,
			CommonTree expressionTree, SimpleScope scope)
			throws SyntaxException {
		CommonTree operatorTree = (CommonTree) expressionTree.getChild(0);
		int operatorKind = operatorTree.getType();
		CommonTree argumentList = (CommonTree) expressionTree.getChild(1);
		int numArgs = argumentList.getChildCount();
		List<ExpressionNode> arguments = new LinkedList<ExpressionNode>();
		Operator operator;

		for (int i = 0; i < numArgs; i++) {
			ExpressionNode argument = translateExpression(
					(CommonTree) argumentList.getChild(i), scope);

			arguments.add(argument);
		}
		switch (operatorKind) {
		case AMPERSAND:
			operator = numArgs == 1 ? Operator.ADDRESSOF : Operator.BITAND;
			break;
		case ASSIGN:
			operator = Operator.ASSIGN;
			break;
		case TILDE:
			operator = Operator.BITCOMPLEMENT;
			break;
		case BITOR:
			operator = Operator.BITOR;
			break;
		case BITXOR:
			operator = Operator.BITXOR;
			break;
		case COMMA:
			operator = Operator.COMMA;
			break;
		case QMARK:
			operator = Operator.CONDITIONAL;
			break;
		case STAR:
			operator = numArgs == 1 ? Operator.DEREFERENCE : Operator.TIMES;
			break;
		case DIV:
			operator = Operator.DIV;
			break;
		case EQUALS:
			operator = Operator.EQUALS;
			break;
		case GT:
			operator = Operator.GT;
			break;
		case GTE:
			operator = Operator.GTE;
			break;
		case HASH:
			operator = Operator.HASH;
			break;
		case AND:
			operator = Operator.LAND;
			break;
		case OR:
			operator = Operator.LOR;
			break;
		case IMPLIES_ACSL:
			operator = Operator.IMPLIES;
			break;
		case LT:
			operator = Operator.LT;
			break;
		case LTE:
			operator = Operator.LTE;
			break;
		case SUB:
			operator = numArgs == 1 ? Operator.UNARYMINUS : Operator.MINUS;
			break;
		case MOD:
			operator = Operator.MOD;
			break;
		case NEQ:
			operator = Operator.NEQ;
			break;
		case NOT:
			operator = Operator.NOT;
			break;
		case PLUS:
			operator = numArgs == 1 ? Operator.UNARYPLUS : Operator.PLUS;
			break;
		case SHIFTLEFT:
			operator = Operator.SHIFTLEFT;
			break;
		case SHIFTRIGHT:
			operator = Operator.SHIFTRIGHT;
			break;
		case INDEX:
			operator = Operator.SUBSCRIPT;
			break;
		case XOR_ACSL:
			operator = Operator.LXOR;
			break;
		case BEQUIV_ACSL:
			operator = Operator.BITEQUIV;
			break;
		case BIMPLIES_ACSL:
			operator = Operator.BITIMPLIES;
			break;
		case EQUIV_ACSL:
			operator = Operator.LEQ;
			break;
		default:
			throw error("Unknown operator : " + operatorTree.getText(),
					operatorTree);
		}
		return nodeFactory.newOperatorNode(source, operator, arguments);
	}

	/**
	 * tries to translate the given identifier node into an enumeration node
	 * according to the scope. If the identifer's name has NOT been declared as
	 * an enumeration constant in the scope, then return null.
	 * 
	 * @param identifier
	 *            the identifier node to be translated
	 * @param scope
	 *            the current scope
	 * @return an enumeration constant node if the identifer's name has been
	 *         declared as an enumeration in the scope otherwise return null.
	 */
	private EnumerationConstantNode translateEnumerationConstant(
			IdentifierNode identifier, SimpleScope scope) {
		String name = identifier.name();

		if (scope.isEnumerationConstant(name))
			return this.nodeFactory.newEnumerationConstantNode(identifier);
		return null;
	}

	private IdentifierNode translateIdentifier(CommonTree identifier) {
		Token idToken = identifier.getToken();
		CivlcToken token;
		Source source;

		if (idToken instanceof CivlcToken)
			token = (CivlcToken) idToken;
		else {
			token = tokenFactory.newCivlcToken(idToken, formation);
		}
		source = tokenFactory.newSource(token);
		return nodeFactory.newIdentifierNode(source, token.getText());
	}

	// ////////////////////////////////////

	private MPICollectiveBlockNode translateMPICollectiveBlock(Source source,
			CommonTree colBlock, SimpleScope scope) throws SyntaxException {
		CommonTree mpiComm = (CommonTree) colBlock.getChild(1);
		CommonTree kind = (CommonTree) colBlock.getChild(2);
		CommonTree body = (CommonTree) colBlock.getChild(3);
		List<ContractNode> bodyComponents = new LinkedList<>();
		SequenceNode<ContractNode> bodyNode;
		ExpressionNode mpiCommNode;
		MPICollectiveKind colKind;

		mpiCommNode = translateExpression(mpiComm, scope);
		// The mpi collective kind can only be :COL, P2P or BOTH
		switch (kind.getType()) {
		case AcslParser.COL:
			colKind = MPICollectiveKind.COL;
			break;
		case AcslParser.P2P:
			colKind = MPICollectiveKind.P2P;
			break;
		case AcslParser.BOTH:
			colKind = MPICollectiveKind.BOTH;
			break;
		default:
			throw error("Unknown MPI collective kind", kind);
		}
		bodyComponents.addAll(translateFunctionContractBlock(body, scope));
		bodyNode = nodeFactory.newSequenceNode(source, "mpi_collective body",
				bodyComponents);
		return nodeFactory.newMPICollectiveBlockNode(source, mpiCommNode,
				colKind, bodyNode);
	}

	private MPIContractConstantNode translateMPIConstantNode(CommonTree tree,
			Source source) throws SyntaxException {
		CommonTree constantTree = (CommonTree) tree.getChild(0);
		MPIContractConstantNode result;

		switch (constantTree.getType()) {
		case AcslParser.MPI_COMM_RANK:
			result = nodeFactory.newMPIConstantNode(source, MPI_COMM_RANK,
					MPIConstantKind.MPI_COMM_RANK, ConstantKind.INT);
			break;
		case AcslParser.MPI_COMM_SIZE:
			result = nodeFactory.newMPIConstantNode(source, MPI_COMM_SIZE,
					MPIConstantKind.MPI_COMM_SIZE, ConstantKind.INT);
			break;
		default:
			throw error("Unknown MPI Constant", tree);
		}
		result.setInitialType(typeFactory.signedIntegerType(SignedIntKind.INT));
		return result;
	}

	private MPIContractExpressionNode translateMPIExpressionNode(
			CommonTree expressionTree, Source source, SimpleScope scope)
			throws SyntaxException {
		CommonTree expression = (CommonTree) expressionTree.getChild(0);
		int kind = expression.getType();
		List<ExpressionNode> args = new LinkedList<>();
		String exprName = expression.getText();
		MPIContractExpressionKind mpiExprKind;
		MPIContractExpressionNode result;
		Type initialType;
		int numChildren = expression.getChildCount();

		switch (kind) {
		case AcslParser.MPI_AGREE:
			mpiExprKind = MPIContractExpressionKind.MPI_AGREE;
			initialType = typeFactory.unsignedIntegerType(UnsignedIntKind.BOOL);
			break;
		case AcslParser.MPI_EMPTY_IN:
			mpiExprKind = MPIContractExpressionKind.MPI_EMPTY_IN;
			initialType = typeFactory.unsignedIntegerType(UnsignedIntKind.BOOL);
			break;
		case AcslParser.MPI_EMPTY_OUT:
			mpiExprKind = MPIContractExpressionKind.MPI_EMPTY_OUT;
			initialType = typeFactory.unsignedIntegerType(UnsignedIntKind.BOOL);
			break;
		case AcslParser.MPI_EQUALS:
			mpiExprKind = MPIContractExpressionKind.MPI_EQUALS;
			initialType = typeFactory.unsignedIntegerType(UnsignedIntKind.BOOL);
			break;
		case AcslParser.MPI_EXTENT:
			mpiExprKind = MPIContractExpressionKind.MPI_EXTENT;
			initialType = typeFactory
					.unsignedIntegerType(UnsignedIntKind.UNSIGNED);
			break;
		case AcslParser.MPI_REGION:
			mpiExprKind = MPIContractExpressionKind.MPI_REGION;
			initialType = typeFactory.pointerType(typeFactory.voidType());
			break;
		case AcslParser.MPI_OFFSET:
			mpiExprKind = MPIContractExpressionKind.MPI_OFFSET;
			initialType = typeFactory.pointerType(typeFactory.voidType());
			break;
		case AcslParser.MPI_VALID:
			mpiExprKind = MPIContractExpressionKind.MPI_VALID;
			initialType = typeFactory.unsignedIntegerType(UnsignedIntKind.BOOL);
			break;
		default:
			throw error("Unknown MPI expression " + exprName, expressionTree);
		}
		for (int i = 1; i < numChildren; i++) {
			ExpressionNode child = translateExpression(
					(CommonTree) expression.getChild(i), scope);

			args.add(child);
		}
		result = nodeFactory.newMPIExpressionNode(source, args, mpiExprKind,
				exprName);
		result.setInitialType(initialType);
		return result;
	}

	private SyntaxException error(String message, CommonTree tree) {
		return new SyntaxException(message, newSource(tree));
	}

	private Source newSource(CommonTree tree) {
		return parseTree.source(tree);
	}

}
