package edu.udel.cis.vsl.abc.front.c.astgen;

import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.AMPERSAND;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ANYACT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ARROW;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ASSIGN;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.AT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.BAR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.BITXOR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.CALL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.CAST;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.CHARACTER_CONSTANT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.COMMA;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.COMP;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.DIV;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.DOT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.DOTDOT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ELLIPSIS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EQ;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_BASE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_INTS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_PARENTHESIZED;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_PLUS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EVENT_SUB;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.EXISTS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.FALSE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.FLOATING_CONSTANT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.FORALL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.GT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.GTE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.ID;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.IMPLY;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.INDEX;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.INTEGER_CONSTANT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.LAND;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.LOR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.LT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.LTE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.MOD;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.NEQ;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.NOACT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.NOT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.NOTHING;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.OPERATOR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.PLUS;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.QUESTION;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.READ;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.RESULT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SHIFTLEFT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SHIFTRIGHT;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SIZEOF;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.STAR;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.STRING_LITERAL;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.SUB;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.TERM_PARENTHESIZED;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.TRUE;
import static edu.udel.cis.vsl.abc.front.c.parse.AcslParser.WRITE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
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
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPICollectiveBlockNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.RequiresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.CharacterConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.StringLiteralNode;
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

public class AcslContractWorker {

	private CParseTree parseTree;
	private NodeFactory nodeFactory;
	private TokenFactory tokenFactory;
	private Formation formation;

	public AcslContractWorker(NodeFactory factory, TokenFactory tokenFactory,
			CParseTree parseTree) {
		this.nodeFactory = factory;
		this.tokenFactory = tokenFactory;
		this.parseTree = parseTree;
		formation = tokenFactory.newTransformFormation("ACSL", "contract");
	}

	public List<ContractNode> generateASTNodes(SimpleScope scope)
			throws SyntaxException {
		CommonTree tree = parseTree.getRoot();
		int kind = tree.getType();

		assert kind == AcslParser.CONTRACT;
		return translateContractBlock((CommonTree) tree.getChild(0), scope);
	}

	private List<ContractNode> translateContractBlock(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int numChildren = tree.getChildCount();
		List<ContractNode> result = new ArrayList<>();

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

	private CompletenessNode translateCompleteness(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int kind = tree.getType();
		boolean isComplete = false;
		SequenceNode<IdentifierNode> idList = this.translateIdList(
				(CommonTree) tree.getChild(0), scope);
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

	private BehaviorNode translateBehavior(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		CommonTree idTree = (CommonTree) tree.getChild(0);
		CommonTree bodyTree = (CommonTree) tree.getChild(1);
		IdentifierNode id = this.translateIdentifier(idTree);
		SequenceNode<ContractNode> body = this.translateBehaviorBody(bodyTree,
				scope);

		return this.nodeFactory.newBehaviorNode(this.parseTree.source(tree),
				id, body);
	}

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

	private ContractNode translateContractClause(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int kind = tree.getType();

		switch (kind) {
		case AcslParser.REQUIRES:
			return this.translateRequires(tree, scope);
		case AcslParser.ENSURES:
			return this.translateEnsures(tree, scope);
		case AcslParser.ASSIGNS:
			return this.translateReadsOrAssigns(tree, scope, false);
		case AcslParser.ASSUMES:
			return this.translateAssumes(tree, scope);
		case AcslParser.READS:
			return this.translateReadsOrAssigns(tree, scope, true);
		case AcslParser.DEPENDS:
			return this.translateDepends(tree, scope);
		default:
			throw this.error("Unknown contract clause kind", tree);
		}
	}

	private AssumesNode translateAssumes(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		CommonTree exprTree = (CommonTree) tree.getChild(0);
		ExpressionNode predicate = this.translateExpression(exprTree, scope);
		Source source = this.parseTree.source(tree);

		return this.nodeFactory.newAssumesNode(source, predicate);
	}

	private AssignsOrReadsNode translateReadsOrAssigns(CommonTree tree,
			SimpleScope scope, boolean isRead) throws SyntaxException {
		Source source = this.parseTree.source(tree);
		SequenceNode<ExpressionNode> memoryList = translateArgumentList(
				(CommonTree) tree.getChild(0), scope);

		if (isRead)
			return this.nodeFactory.newReadsNode(source, memoryList);
		else
			return this.nodeFactory.newAssignsNode(source, memoryList);
	}

	private DependsNode translateDepends(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		Source source = this.parseTree.source(tree);
		CommonTree argumentTree = (CommonTree) tree.getChild(0);
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
		CommonTree leftTree = (CommonTree) tree.getChild(0), rightTree = (CommonTree) tree
				.getChild(1);
		DependsEventNode left = this.translateDependsEventBase(leftTree, scope), right = this
				.translateDependsEventBase(rightTree, scope);

		return this.nodeFactory.newOperatorEventNode(source, op, left, right);
	}

	private DependsEventNode translateDependsEventBase(CommonTree tree,
			SimpleScope scope) throws SyntaxException {
		int kind = tree.getType();
		Source source = this.parseTree.source(tree);

		switch (kind) {
		case READ: {
			SequenceNode<ExpressionNode> memList = this.translateArgumentList(
					(CommonTree) tree.getChild(0), scope);

			return nodeFactory.newReadOrWriteEventNode(source, true, memList);
		}
		case WRITE: {
			SequenceNode<ExpressionNode> memList = this.translateArgumentList(
					(CommonTree) tree.getChild(0), scope);

			return nodeFactory.newReadOrWriteEventNode(source, false, memList);
		}
		case CALL: {
			IdentifierNode function = this
					.translateIdentifier((CommonTree) tree.getChild(0));
			SequenceNode<ExpressionNode> args = this.translateArgumentList(
					(CommonTree) tree.getChild(1), scope);

			return nodeFactory.newCallEventNode(source, function, args);
		}
		case NOACT:
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
		CommonTree expressionTree = (CommonTree) tree.getChild(0);
		Source source = this.newSource(tree);
		ExpressionNode expression = this.translateExpression(expressionTree,
				scope);

		return nodeFactory.newRequiresNode(source, expression);
	}

	private EnsuresNode translateEnsures(CommonTree tree, SimpleScope scope)
			throws SyntaxException {
		Source source = this.newSource(tree);
		CommonTree expressionTree = (CommonTree) tree.getChild(0);
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
		case ID:
			return nodeFactory.newIdentifierExpressionNode(source,
					translateIdentifier(expressionTree));
		case TERM_PARENTHESIZED:
			return translateExpression((CommonTree) expressionTree.getChild(1),
					scope);
		case CALL:
			return translateCall(source, expressionTree, scope);
		case DOT:
		case ARROW:
			return translateDotOrArrow(source, expressionTree, scope);
		case OPERATOR:
			return translateOperatorExpression(source, expressionTree, scope);
		case TRUE:
			return translateTrue(source);
		case FALSE:
			return translateFalse(source);
		case RESULT:
			return nodeFactory.newResultNode(source);
		case DOTDOT:
			return translateRegularRange(source, expressionTree, scope);
		case WRITE:
			return translateWriteEvent(source, expressionTree, scope);
		case NOTHING:
			return this.nodeFactory.newNothingNode(source);
		case ELLIPSIS:
			return this.nodeFactory.newWildcardNode(source);
		case SIZEOF:
			// return translateSizeOf(source, expressionTree, scope);
		case FORALL:
			// return translateForall(source, expressionTree, scope);
		case EXISTS:
			// return translateExists(source, expressionTree, scope);
		case CAST:
			// return nodeFactory.newCastNode(
			// source,
			// translateTypeName((CommonTree) expressionTree.getChild(0),
			// scope),
			// translateExpression(
			// (CommonTree) expressionTree.getChild(1), scope));
		default:
			throw error("Unknown expression kind", expressionTree);
		} // end switch
	}

	// ////////////////////////////////////

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

		return nodeFactory.newStringLiteralNode(source,
				stringLiteral.getText(), token.getStringLiteral());
	}

	private ExpressionNode translateRegularRange(Source source,
			CommonTree expressionTree, SimpleScope scope)
			throws SyntaxException {
		{// regular range expression lo..hi or lo..hi#step
			ExpressionNode loNode = translateExpression(
					(CommonTree) expressionTree.getChild(0), scope);
			ExpressionNode hiNode = translateExpression(
					(CommonTree) expressionTree.getChild(1), scope);
			if (expressionTree.getChildCount() > 2) {
				CommonTree stepTree = (CommonTree) expressionTree.getChild(2);

				if (stepTree != null /* && stepTree.getType() != ABSENT */) {
					ExpressionNode stepNode = translateExpression(stepTree,
							scope);

					return nodeFactory.newRegularRangeNode(source, loNode,
							hiNode, stepNode);
				}
			}
			return nodeFactory.newRegularRangeNode(source, loNode, hiNode);
		}
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
		CommonTree functionTree = (CommonTree) callTree.getChild(1);
		CommonTree contextArgumentListTree = (CommonTree) callTree.getChild(2);
		CommonTree argumentListTree = (CommonTree) callTree.getChild(3);
		ExpressionNode functionNode = translateExpression(functionTree, scope);
		int numContextArgs = contextArgumentListTree.getChildCount();
		int numArgs = argumentListTree.getChildCount();
		List<ExpressionNode> contextArgumentList = new LinkedList<ExpressionNode>();
		List<ExpressionNode> argumentList = new LinkedList<ExpressionNode>();
		// SequenceNode<ExpressionNode> scopeList =
		// translateScopeListUse((CommonTree) callTree
		// .getChild(4));

		for (int i = 0; i < numContextArgs; i++) {
			CommonTree argumentTree = (CommonTree) contextArgumentListTree
					.getChild(i);
			ExpressionNode contextArgumentNode = translateExpression(
					argumentTree, scope);

			contextArgumentList.add(contextArgumentNode);
		}

		for (int i = 0; i < numArgs; i++) {
			CommonTree argumentTree = (CommonTree) argumentListTree.getChild(i);
			ExpressionNode argumentNode = translateExpression(argumentTree,
					scope);

			argumentList.add(argumentNode);
		}
		return nodeFactory.newFunctionCallNode(source, functionNode,
				contextArgumentList, argumentList, null);
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
		case COMP:
			operator = Operator.BITCOMPLEMENT;
			break;
		case BAR:
			operator = Operator.BITOR;
			break;
		case BITXOR:
			operator = Operator.BITXOR;
			break;
		case COMMA:
			operator = Operator.COMMA;
			break;
		case QUESTION:
			operator = Operator.CONDITIONAL;
			break;
		case STAR:
			operator = numArgs == 1 ? Operator.DEREFERENCE : Operator.TIMES;
			break;
		case DIV:
			operator = Operator.DIV;
			break;
		case EQ:
			operator = Operator.EQUALS;
			break;
		case GT:
			operator = Operator.GT;
			break;
		case GTE:
			operator = Operator.GTE;
			break;
		case LAND:
			operator = Operator.LAND;
			break;
		case LOR:
			operator = Operator.LOR;
			break;
		case IMPLY:
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
		case AT:
			operator = Operator.AT;
			break;
		default:
			throw error("Unknown operator :", operatorTree);
		}
		return nodeFactory.newOperatorNode(source, operator, arguments);
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
		CommonTree mpiComm = (CommonTree) colBlock.getChild(0);
		CommonTree kind = (CommonTree) colBlock.getChild(1);
		CommonTree body = (CommonTree) colBlock.getChild(2);
		List<ContractNode> bodyComponents = new LinkedList<>();
		SequenceNode<ContractNode> bodyNode;
		ExpressionNode mpiCommNode, kindNode;

		mpiCommNode = translateExpression(mpiComm, scope);
		kindNode = translateExpression(kind, scope);
		bodyComponents.addAll(translateContractBlock(body, scope));
		bodyNode = nodeFactory.newSequenceNode(source, "mpi_collective body",
				bodyComponents);
		return nodeFactory.newMPICollectiveBlockNode(source, mpiCommNode,
				kindNode, bodyNode);
	}

	private SyntaxException error(String message, CommonTree tree) {
		return new SyntaxException(message, newSource(tree));
	}

	private Source newSource(CommonTree tree) {
		return parseTree.source(tree);
	}

}
