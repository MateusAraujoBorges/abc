package edu.udel.cis.vsl.abc.front.fortran.astgen;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.label.SwitchLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.common.CommonSequenceNode;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.front.common.astgen.SimpleScope;
import edu.udel.cis.vsl.abc.front.fortran.ptree.FortranTree;
import edu.udel.cis.vsl.abc.token.IF.CivlcToken;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SourceFile;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;

public class FortranASTBuilderWorker {

	/* Instance Field */
	@SuppressWarnings("unused")
	private Configuration configuration;

	private String filePath;

	private FortranTree parseTree;

	private TokenFactory tokenFactory;

	// private TypeFactory typeFactory = Types.newTypeFactory();
	//
	// private ValueFactory valueFactory = Values.newValueFactory(configuration,
	// typeFactory);

	private NodeFactory nodeFactory;

	private ASTFactory astFactory;

	private SequenceNode<BlockItemNode> rootNode;

	private Stack<FortranTree> gotoLabelStack = new Stack<FortranTree>();

	/* Constructor */

	public FortranASTBuilderWorker(Configuration config, FortranTree parseTree,
			ASTFactory astFactory, String filePath) {
		this.configuration = config;
		this.parseTree = parseTree;
		this.filePath = filePath;
		this.astFactory = astFactory;
		this.nodeFactory = astFactory.getNodeFactory();
		this.tokenFactory = astFactory.getTokenFactory();
	}

	/* Private Functions */
	private CivlcToken findLToken(FortranTree node) {
		CivlcToken result = null;
		Token[] tempTokens = node.cTokens();
		int numChildren = node.numChildren();
		int childIndex = 0;

		if (tempTokens != null && tempTokens.length > 0) {
			return (CivlcToken) node.cTokens()[0];
		} else if (numChildren > 0) {
			while (childIndex < numChildren) {
				FortranTree tempNode = node.getChildByIndex(childIndex);

				if (tempNode != null) {
					result = findLToken(tempNode);
					if (result != null)
						return result;
				}
				childIndex++;
			}
		}
		return result;
	}

	private CivlcToken findRToken(FortranTree node) {
		CivlcToken result = null;
		Token[] tempTokens = node.cTokens();
		int childIndex = node.numChildren() - 1;

		if (tempTokens != null && tempTokens.length > 0) {
			return (CivlcToken) node.cTokens()[tempTokens.length - 1];
		} else if (childIndex >= 0) {
			while (childIndex >= 0) {
				FortranTree tempNode = node.getChildByIndex(childIndex);

				if (tempNode != null) {
					result = findRToken(tempNode);
					if (result != null)
						return result;
				}
				childIndex--;
			}
		}
		return result;
	}

	private Source generateSource(FortranTree... sourceNodes) {
		int numNodes = sourceNodes.length;
		int counter = 0;
		CivlcToken lToken = null, rToken = null;
		Formation dummyFormation = tokenFactory.newInclusion(new SourceFile(
				new File(this.filePath), 0));
		Source result = null;

		for (counter = 0; counter < numNodes; counter++) {
			Token tempToken = null;
			FortranTree tempNode = sourceNodes[counter];

			if (tempNode != null) {
				tempToken = findLToken(tempNode);
				if (tempToken != null) {
					lToken = (CivlcToken) tempToken;
					break;
				}
			}
		}
		for (counter = numNodes - 1; counter > -1; counter--) {
			Token tempToken = null;
			FortranTree tempNode = sourceNodes[counter];

			if (tempNode != null) {
				tempToken = findRToken(tempNode);
				if (tempToken != null) {
					rToken = (CivlcToken) tempToken;
					break;
				}
			}
		}
		if (lToken == null) {
			result = tokenFactory.newSource(tokenFactory.newCivlcToken(
					new CommonToken(0, ""), dummyFormation));
		} else if (rToken == null) {
			rToken = lToken;
			result = tokenFactory.newSource(lToken);
		} else {
			result = tokenFactory.newSource(lToken, rToken);
		}
		return result;
	}

	private FunctionTypeNode translateFunctionType(
			FortranTree programPrefixNode, FortranTree parameterNode,
			boolean isMain) {
		Source typeSource = generateSource(programPrefixNode);
		TypeNode returnType;
		SequenceNode<VariableDeclarationNode> formals = new CommonSequenceNode<VariableDeclarationNode>(
				typeSource, "IdentifierList",
				new ArrayList<VariableDeclarationNode>());
		boolean hasIdentifierList = true;

		assert parameterNode == null;
		returnType = nodeFactory.newVoidTypeNode(typeSource);
		if (isMain) {
			returnType = nodeFactory.newBasicTypeNode(typeSource,
					BasicTypeKind.INT);
		}
		return nodeFactory.newFunctionTypeNode(typeSource, returnType, formals,
				hasIdentifierList);
	}

	private IdentifierNode translateIdentifier(FortranTree identifierNode) {
		Source source;
		String name = (identifierNode.cTokens())[0].getText();

		source = generateSource(identifierNode);
		return nodeFactory.newIdentifierNode(source, name);
	}

	private TypeNode translateType(FortranTree typeSpecifierNode) {
		TypeNode type = null;
		int rule = typeSpecifierNode.rule();
		Source source = generateSource(typeSpecifierNode);

		switch (rule) {
		case 403:
			int specifierType = typeSpecifierNode.type();

			switch (specifierType) {
			case 400: /* Integer */
				type = nodeFactory.newBasicTypeNode(source, BasicTypeKind.INT);
				break;
			case 401: /* REAL */
				type = nodeFactory.newBasicTypeNode(source, BasicTypeKind.DOUBLE);
				break;
			case 402: /* DOUBLE PRECISION */
				type = nodeFactory.newBasicTypeNode(source,
						BasicTypeKind.DOUBLE);
				break;
			default:
				assert false;
			}
			break;
		default:
			assert false;
		}
		return type;
	}

	private InitializerNode translateInitializer(FortranTree initNode,
			SimpleScope scope) throws SyntaxException {
		Source source = this.generateSource(initNode);
		return translateExpression(source, initNode, scope);
	}

	private List<BlockItemNode> translateTypeDeclaration(
			FortranTree declarationNode, SimpleScope scope)
			throws SyntaxException {
		ArrayList<BlockItemNode> definitionList = new ArrayList<BlockItemNode>();
		FortranTree labelDefinition = declarationNode.getChildByIndex(0);
		FortranTree declarationSpecifier = declarationNode.getChildByIndex(1);
		FortranTree declarationEntityList = declarationNode.getChildByIndex(2);
		int numOfDeclarator = declarationEntityList.numChildren();
		Source source = this.generateSource(declarationNode);

		assert numOfDeclarator > 0;
		for (int i = 0; i < numOfDeclarator; i++) {
			FortranTree entityNode = declarationEntityList.getChildByIndex(i);
			FortranTree identifierNode = entityNode.getChildByIndex(0);
			IdentifierNode name = this.translateIdentifier(identifierNode);
			TypeNode type = this.translateType(declarationSpecifier
					.getChildByIndex(0));
			boolean isArray = false;
			boolean hasInit = false;

			if (1 < entityNode.numChildren()) {
				FortranTree temp = entityNode.getChildByIndex(1);
				int rule = temp.rule();

				switch (rule) {
				case 510:
					isArray = true;
					break;
				case 506:
					hasInit = true;
				default:
				}
			}
			if (isArray) {
				FortranTree arrayInfoNode = entityNode.getChildByIndex(1);
				TypeNode tempType = type;
				int arity = arrayInfoNode.numChildren();

				for (int j = arity - 1; j >= 0; j--) {
					FortranTree indexExprNode = arrayInfoNode
							.getChildByIndex(j).getChildByIndex(0);
					ExpressionNode extent = translateExpression(source,
							indexExprNode, scope);
					TypeNode arrayType = nodeFactory.newArrayTypeNode(source,
							tempType, extent);

					tempType = arrayType;
				}
				definitionList.add(nodeFactory.newVariableDeclarationNode(
						source, name, tempType));
			} else if (hasInit) {
				FortranTree initNode = entityNode.getChildByIndex(1)
						.getChildByIndex(0);
				InitializerNode initializer = translateInitializer(initNode,
						scope);
				VariableDeclarationNode declaration = nodeFactory
						.newVariableDeclarationNode(source, name, type);

				declaration.setInitializer(initializer);
				definitionList.add(declaration);
			} else {
				VariableDeclarationNode declaration = nodeFactory
						.newVariableDeclarationNode(source, name, type);
				definitionList.add(declaration);
			}
		}
		return definitionList;
	}

	private OperatorNode translateOperatorExpression(Source source,
			FortranTree exprNode, SimpleScope scope) throws SyntaxException {
		int rule = exprNode.rule();
		OperatorNode result = null;
		Operator operator = null;
		List<ExpressionNode> arguments = new ArrayList<ExpressionNode>();

		switch (rule) {
		case 613: /* PartRef(ArrayUnit) */
			FortranTree idNode = exprNode.getChildByIndex(0);
			FortranTree indexUnitsNode = exprNode.getChildByIndex(1);
			int arity = indexUnitsNode.numChildren();
			ExpressionNode tempNode = nodeFactory.newIdentifierExpressionNode(
					source, translateIdentifier(idNode));
			ExpressionNode indexNode = null;
			ExpressionNode adjustedIndexNode = null;

			for (int i = 0; i < arity; i++) {
				FortranTree indexUnitNode = indexUnitsNode.getChildByIndex(i)
						.getChildByIndex(0);

				operator = Operator.SUBSCRIPT;
				arguments = new LinkedList<ExpressionNode>();
				indexNode = translateExpression(source, indexUnitNode, scope);
				adjustedIndexNode = adjustIndex(source, indexNode);
				arguments.add(tempNode);
				arguments.add(adjustedIndexNode);
				tempNode = nodeFactory.newOperatorNode(source, operator,
						arguments);
			}
			return (OperatorNode) tempNode;
		case 734: /* Assign */
			operator = Operator.ASSIGN;
			arguments = new LinkedList<ExpressionNode>();
			for (int i = 1; i < 3; i++) {
				ExpressionNode argument = translateExpression(source,
						exprNode.getChildByIndex(i), scope);
				arguments.add(argument);
			}
			return nodeFactory.newOperatorNode(source, operator, arguments);
		case 704: /* MultOperand(s) */
			for (int i = 0; i < exprNode.numChildren(); i++) {
				FortranTree operandNode = exprNode.getChildByIndex(i);
				String op_string = operandNode.getChildByIndex(0).cTokens()[0]
						.getText();
				ExpressionNode leftNode = null;
				ExpressionNode rightNode = null;

				operator = op_string.startsWith("*") ? Operator.TIMES
						: Operator.DIV;
				leftNode = translateExpression(source,
						operandNode.getChildByIndex(1), scope);
				rightNode = translateExpression(source,
						operandNode.getChildByIndex(2), scope);
				arguments.add(leftNode);
				arguments.add(rightNode);
			}
			return nodeFactory.newOperatorNode(source, operator, arguments);
		case 705: /* AddOperand(s) */
			for (int i = 0; i < exprNode.numChildren(); i++) {
				FortranTree operandNode = exprNode.getChildByIndex(i);
				String op_string = operandNode.getChildByIndex(0).cTokens()[0]
						.getText();
				ExpressionNode leftNode = null;
				ExpressionNode rightNode = null;

				operator = op_string.startsWith("+") ? Operator.PLUS
						: Operator.MINUS;
				leftNode = translateExpression(source,
						operandNode.getChildByIndex(1), scope);
				rightNode = translateExpression(source,
						operandNode.getChildByIndex(2), scope);
				arguments.add(leftNode);
				arguments.add(rightNode);
			}
			return nodeFactory.newOperatorNode(source, operator, arguments);
		default:
			System.out.println(rule);
			assert false;
		}
		result = nodeFactory.newOperatorNode(source, operator, arguments);
		return result;
	}

	private ExpressionNode adjustIndex(Source source, ExpressionNode indexNode) throws SyntaxException {
		ExpressionNode intOneNode = nodeFactory.newIntegerConstantNode(source, "1");
		Operator operator = Operator.MINUS;
		List<ExpressionNode> arguments = new ArrayList<ExpressionNode>();
		
		arguments.add(indexNode);
		arguments.add(intOneNode.copy());
		return nodeFactory.newOperatorNode(source, operator, arguments);
	}

	private IntegerConstantNode translateIntegerConstant(Source source,
			FortranTree constNode) throws SyntaxException {
		String representation = constNode.getChildByIndex(0).cTokens()[0]
				.getText();

		return nodeFactory.newIntegerConstantNode(source, representation);
	}

	private ExpressionNode translateExpression(Source source,
			FortranTree exprNode, SimpleScope scope) throws SyntaxException {
		ExpressionNode result = null;
		int rule = exprNode.rule();

		switch (rule) {
		case 734: /* Assign */
			return translateOperatorExpression(source, exprNode, scope);
		case 601: /* Variable */
			int var_type = exprNode.getChildByIndex(0).rule();

			switch (var_type) {
			case 603: /* Designator */
				FortranTree refNode = exprNode.getChildByIndex(0)
						.getChildByIndex(0).getChildByIndex(0);

				if (refNode.numChildren() < 2) {
					return nodeFactory.newIdentifierExpressionNode(source,
							translateIdentifier(refNode.getChildByIndex(0)));
				} else {
					return translateOperatorExpression(source, refNode, scope);
				}
			default:
				assert false;
			}
		case 701: /* PrimaryExpr */
			FortranTree primExprNode = exprNode.getChildByIndex(0);
			int expr_type = primExprNode.rule();

			switch (expr_type) {
			case -3: /* DesignatorOrFunctionRef */
				FortranTree refNode = exprNode.getChildByIndex(0)
						.getChildByIndex(0).getChildByIndex(0);

				if (refNode.numChildren() < 2) {
					return nodeFactory.newIdentifierExpressionNode(source,
							translateIdentifier(refNode.getChildByIndex(0)));
				} else {
					return translateOperatorExpression(source, refNode, scope);
				}
			case 306: /* LiteralConst */
				FortranTree constNode = primExprNode.getChildByIndex(0);
				int const_type = constNode.rule();

				switch (const_type) {
				case 406: /* IntLitConst */
					return translateIntegerConstant(source, constNode);
				case 417: /* RealLitConst */
					return translateFloatingConstant(source, constNode);
				default:
					assert false;
				}
			case 704: /* MultOperand(s) */
				return translateOperatorExpression(source, primExprNode, scope);
			case 705: /* AddOperand(s) */
				return translateOperatorExpression(source, primExprNode, scope);
			default:
				System.out.print(expr_type);
				assert false;
			}
		case 704: /* MultOperand(s) */
			return translateOperatorExpression(source, exprNode, scope);
		case 705: /* AddOperand(s) */
			return translateOperatorExpression(source, exprNode, scope);
		default:
			System.out.println(rule);
			assert false;
		}
		return result;
	}

	private FloatingConstantNode translateFloatingConstant(Source source,
			FortranTree constNode) throws SyntaxException {
		String representation = constNode.getChildByIndex(0).cTokens()[0]
				.getText();
		if (representation.contains("d")) {
			int dIndex = representation.indexOf("d");
			String numberStr = representation.substring(0, dIndex);
			String powTenStr = representation.substring(dIndex + 1);
			double number = Double.valueOf(numberStr);
			double powTen = Double.valueOf(powTenStr);
			double result = number * Math.pow(10.0, powTen);
			representation = "" + result;
		} else if (representation.contains("D")) {
			int dIndex = representation.indexOf("D");
			String numberStr = representation.substring(0, dIndex);
			String powTenStr = representation.substring(dIndex + 1);
			double number = Double.valueOf(numberStr);
			double powTen = Double.valueOf(powTenStr);
			double result = number * Math.pow(10.0, powTen);
			representation = "" + result;
		}

		return nodeFactory.newFloatingConstantNode(source, representation);
	}

	private StatementNode translateExpressionStatement(FortranTree exprNode,
			SimpleScope scope) throws SyntaxException {
		Source statementSource = generateSource(exprNode);
		ExpressionNode expressionNode = translateExpression(statementSource,
				exprNode, scope);

		if (expressionNode == null)
			return nodeFactory.newNullStatementNode(statementSource);
		else
			return nodeFactory.newExpressionStatementNode(expressionNode);
	}

	private StatementNode translateStatement(FortranTree blockItemNode,
			SimpleScope scope) throws SyntaxException {
		int rule = blockItemNode.rule();
		StatementNode result = null;

		switch (rule) {
		case 734: /* AssignmentStatement */
			result = translateExpressionStatement(blockItemNode, scope);
			break;
		case 827: /* DoStatement */
			result = translateDoStatement(blockItemNode.parent(), scope);
			break;
		case 845: /* GotoStatement */
			result = translateGoto(blockItemNode);
			break;
		case 846: /* ComputedGotoStatement */
			result = translateComputedGoto(blockItemNode, scope);
			break;
		case 1236: /* ReaturnStatement */
			result = nodeFactory.newReturnNode(generateSource(blockItemNode),
					null);
			break;
		case 801: /* Block */
			break;
		default:
			System.out.println(">>>" + rule);
			assert false;
		}

		return result;

	}

	private StatementNode translateDoStatement(FortranTree blockItemNode,
			SimpleScope scope) throws SyntaxException {
		ForLoopInitializerNode initializerNode;
		ExpressionNode conditionNode;
		ExpressionNode incrementerNode = null;
		StatementNode bodyNode;
		ExpressionNode invariantNode;
		Operator initOperator = Operator.ASSIGN;
		Operator condOperator = Operator.LTE;
		Operator stepOperator = Operator.PLUSEQ;
		FortranTree doControlNode = blockItemNode.getChildByIndex(0)
				.getChildByIndex(4);
		FortranTree doBodyNode = blockItemNode.getChildByIndex(1);
		FortranTree doVariableNode = doControlNode.getChildByIndex(1)
				.getChildByIndex(0);
		Source doVariableSource = generateSource(doVariableNode);
		FortranTree initExprNode = doControlNode.getChildByIndex(2);
		Source initSource = generateSource(initExprNode);
		List<ExpressionNode> initArgs = new LinkedList<ExpressionNode>();
		FortranTree condExprNode = doControlNode.getChildByIndex(3);
		Source condSource = generateSource(condExprNode);
		List<ExpressionNode> condArgs = new LinkedList<ExpressionNode>();
		Source stepSource = generateSource(doVariableNode);// TODO: Fix no step
															// defined
		List<ExpressionNode> stepArgs = new LinkedList<ExpressionNode>();

		initArgs.add(nodeFactory.newIdentifierExpressionNode(doVariableSource,
				translateIdentifier(doVariableNode)));
		initArgs.add(translateExpression(initSource, initExprNode, scope));
		condArgs.add(nodeFactory.newIdentifierExpressionNode(doVariableSource,
				translateIdentifier(doVariableNode)));
		condArgs.add(translateExpression(condSource, condExprNode, scope));
		stepArgs.add(nodeFactory.newIdentifierExpressionNode(doVariableSource,
				translateIdentifier(doVariableNode)));
		if (doControlNode.numChildren() > 4) {
			FortranTree stepExprNode = doControlNode.getChildByIndex(4);

			stepArgs.add(translateExpression(stepSource, stepExprNode, scope));
		} else {
			stepArgs.add(nodeFactory.newIntegerConstantNode(stepSource, "1"));
		}
		initializerNode = nodeFactory.newOperatorNode(initSource, initOperator,
				initArgs);
		conditionNode = nodeFactory.newOperatorNode(condSource, condOperator,
				condArgs);
		incrementerNode = nodeFactory.newOperatorNode(stepSource, stepOperator,
				stepArgs);
		bodyNode = translateBody(null, doBodyNode, scope);

		return nodeFactory.newForLoopNode(
				generateSource(blockItemNode.parent()), initializerNode,
				conditionNode, incrementerNode, bodyNode, null);
	}

	private StatementNode translateGoto(FortranTree blockItemNode) {
		Source statementSource = generateSource(blockItemNode);
		int labelIndex = blockItemNode.numChildren() - 1;
		FortranTree labelNode = blockItemNode.getChildByIndex(labelIndex);

		return nodeFactory.newGotoNode(statementSource,
				translateIdentifier(labelNode));
	}

	private StatementNode translateComputedGoto(FortranTree blockItemNode,
			SimpleScope scope) throws SyntaxException {
		Source source = generateSource(blockItemNode);
		FortranTree exprNode = blockItemNode.getChildByIndex(2);
		FortranTree labelListNode = blockItemNode.getChildByIndex(1);

		ExpressionNode expressionNode = translateExpression(source, exprNode,
				scope);
		StatementNode statementNode = translateGotoLabelList(source,
				labelListNode, scope);
		SwitchNode switchNode = nodeFactory.newSwitchNode(source,
				expressionNode, statementNode);
		return switchNode;
	}

	private CompoundStatementNode translateGotoLabelList(Source source,
			FortranTree labelListNode, SimpleScope scope)
			throws SyntaxException {
		int numOfLabel = labelListNode.numChildren();
		List<BlockItemNode> items = new LinkedList<BlockItemNode>();

		for (int i = 0; i < numOfLabel; i++) {
			FortranTree labelNode = labelListNode.getChildByIndex(i);
			Source labelSource = generateSource(labelNode);
			String labelStr = labelNode.cTokens()[0].getText();
			IntegerConstantNode caseNum = nodeFactory.newIntegerConstantNode(
					labelSource, "" + (i + 1));
			StatementNode gotoStatement = nodeFactory.newGotoNode(source,
					nodeFactory.newIdentifierNode(labelSource, labelStr));
			SwitchLabelNode labelDecl = nodeFactory
					.newCaseLabelDeclarationNode(labelSource, caseNum,
							gotoStatement);
			LabeledStatementNode tempItemNode = nodeFactory
					.newLabeledStatementNode(source, labelDecl, gotoStatement);
			items.add(tempItemNode);
		}
		return nodeFactory.newCompoundStatementNode(source, items);
	}

	private List<BlockItemNode> translateBlockItem(FortranTree blockItemNode,
			SimpleScope scope) throws SyntaxException {
		List<BlockItemNode> result = null;
		int rule = blockItemNode.rule();

		switch (rule) {
		case 501: /* TypeDeclarationStatement */
			result = this.translateTypeDeclaration(blockItemNode, scope);
			break;
		case 538: /* ParameterStatement */
			result = this.translateParameterStatement(blockItemNode, scope);
			break;
		case 734: /* AssignmentStatement */
			result = new ArrayList<BlockItemNode>();
			result.add((BlockItemNode) this.translateStatement(blockItemNode,
					scope));
			break;
		case 827: /* DoStatement */
			result = new ArrayList<BlockItemNode>();
			result.add((BlockItemNode) this.translateStatement(blockItemNode,
					scope));
			break;
		case 845: /* GotoStatement */
			result = new ArrayList<BlockItemNode>();
			result.add((BlockItemNode) this.translateStatement(blockItemNode,
					scope));
			break;
		case 846: /* ComputedGotoStatement */
			result = new ArrayList<BlockItemNode>();
			result.add((BlockItemNode) this.translateStatement(blockItemNode,
					scope));
			break;
		case 848: /* ContinueStatement */
			result = new ArrayList<BlockItemNode>();
			result.add((BlockItemNode) this
					.translateIdentifierLabeledStatement(blockItemNode, scope));
			break;
		case 912: /* PrintStatement */
			//TODO: Print statement
			break;
		case 1236: /* ReturnStatement */
			result = new ArrayList<BlockItemNode>();
			result.add((BlockItemNode) this.translateStatement(blockItemNode,
					scope));
			break;
		default:
			System.out.println(rule);
			assert false;
		}
		return result;
	}

	private List<BlockItemNode> translateParameterStatement(
			FortranTree parameterStmtNode, SimpleScope scope)
			throws SyntaxException {
		ArrayList<BlockItemNode> paramterItems = new ArrayList<BlockItemNode>();
		FortranTree labelDefinition = parameterStmtNode.getChildByIndex(0);
		FortranTree constList = parameterStmtNode.getChildByIndex(2);
		int numOfConst = constList.numChildren();
		Source source = this.generateSource(parameterStmtNode);

		assert numOfConst > 0;
		for (int i = 0; i < numOfConst; i++) {
			FortranTree namedConstDefNode = constList.getChildByIndex(i);
			FortranTree identifierNode = namedConstDefNode.getChildByIndex(0);
			FortranTree exprNode = namedConstDefNode.getChildByIndex(1);
			ExpressionNode constValExpr = translateExpression(source, exprNode,
					scope);
			OperatorNode expressionNode = null;
			Operator operator = Operator.ASSIGN;
			List<ExpressionNode> arguments = new ArrayList<ExpressionNode>();

			arguments.add(nodeFactory.newIdentifierExpressionNode(source,
					translateIdentifier(identifierNode)));
			arguments.add(constValExpr);
			expressionNode = nodeFactory.newOperatorNode(source, operator,
					arguments);
			paramterItems.add(nodeFactory
					.newExpressionStatementNode(expressionNode));
		}
		return paramterItems;
	}

	private LabeledStatementNode translateIdentifierLabeledStatement(
			FortranTree blockItemNode, SimpleScope scope)
			throws SyntaxException {
		Source source = generateSource(blockItemNode);
		IdentifierNode labelName = null;
		StatementNode statement = null;
		FortranTree ancesterNode = blockItemNode.parent().parent();
		int nextIndex = (ancesterNode.childIndex()) + 1;
		FortranTree labeledAncesterNode = ancesterNode.parent()
				.getChildByIndex(nextIndex);
		FortranTree labeledStatementNode = labeledAncesterNode
				.getChildByIndex(0);
		FortranTree labelIdNode = blockItemNode.getChildByIndex(0);
		int rule = labeledStatementNode.rule();

		labelName = translateIdentifier(labelIdNode);
		if (rule == 214) {
			statement = translateStatement(
					labeledStatementNode.getChildByIndex(0), scope);
		} else if (rule == 825) {
			statement = translateDoStatement(labeledStatementNode, scope);
		} else {
			System.out.print(rule);
			assert false;
		}

		OrdinaryLabelNode labelDecl = nodeFactory
				.newStandardLabelDeclarationNode(labelName.getSource(),
						labelName, statement);

		return nodeFactory
				.newLabeledStatementNode(source, labelDecl, statement);
	}

	private CompoundStatementNode translateBody(
			FortranTree specificationPartNode, FortranTree executionPartNode,
			SimpleScope scope) throws SyntaxException {
		CompoundStatementNode result;
		SimpleScope newScope = new SimpleScope(scope);
		Source source = null;
		List<BlockItemNode> items = new LinkedList<BlockItemNode>();
		if (specificationPartNode != null) {
			int numOfSpecification = specificationPartNode.numChildren();

			source = generateSource(specificationPartNode);
			for (int i = 0; i < numOfSpecification; i++) {
				FortranTree specificationNode = specificationPartNode
						.getChildByIndex(i);
				int rule = specificationNode.rule();
				switch (rule) {
				case 207: /* DeclarationConstruct */
					FortranTree declarationNode = specificationNode
							.getChildByIndex(0);
					List<BlockItemNode> blockItemNodes = this
							.translateBlockItem(declarationNode, newScope);
					items.addAll(blockItemNodes);
					break;
				default:
					assert false;
				}
			}
		}
		if (executionPartNode != null) {
			int numOfExecution = executionPartNode.numChildren();

			if (specificationPartNode != null) {
				source = generateSource(specificationPartNode,
						executionPartNode);
			} else {
				source = generateSource(executionPartNode);
			}
			for (int i = 0; i < numOfExecution; i++) {
				FortranTree executionNode = executionPartNode
						.getChildByIndex(i);
				int rule = executionNode.rule();
				switch (rule) {
				case 213: /* ExecutableConstruct */
					FortranTree stmtNode = executionNode.getChildByIndex(0)
							.getChildByIndex(0);
					List<BlockItemNode> blockItemNodes = this
							.translateBlockItem(stmtNode, newScope);
					items.addAll(blockItemNodes);
					if (executionNode.getChildByIndex(0).getChildByIndex(0)
							.rule() == 848) {
						i++;
					}
					break;
				default:
					assert false;
				}
			}
		}
		result = nodeFactory.newCompoundStatementNode(source, items);
		return result;
	}

	private BlockItemNode translateMainProgramUnit(FortranTree programUnitNode,
			SimpleScope scope, int unitType) throws SyntaxException {
		int numChildren = programUnitNode.numChildren();
		SimpleScope newScope = new SimpleScope(scope, true);
		FortranTree programStatementNode = programUnitNode.getChildByIndex(0);
		FortranTree identifierNode = programStatementNode.getChildByIndex(2);
		FortranTree specificationPartNode = programUnitNode.getChildByIndex(1);
		FortranTree executionPartNode = numChildren > 3 ? programUnitNode
				.getChildByIndex(2) : null;
		FortranTree endProgramStatementNode = programUnitNode
				.getChildByIndex(numChildren - 1);
		Source source;
		IdentifierNode name;
		FunctionTypeNode type;
		CompoundStatementNode body;
		BlockItemNode result = null;

		source = generateSource(programUnitNode);
		name = this.translateIdentifier(identifierNode);
		if (unitType == 0)
			name = nodeFactory.newIdentifierNode(source, "main");
		type = this.translateFunctionType(null, null, true);
		body = translateBody(specificationPartNode, executionPartNode, newScope);
		result = nodeFactory.newFunctionDefinitionNode(source, name, type,
				null, body);
		return result;
	}

	/**
	 * [R202-F08] ProgramUnits
	 * 
	 * @param programUnitNode
	 * @param scope
	 * @return
	 * @throws SyntaxException
	 */
	private List<BlockItemNode> translateProgramUnit(
			FortranTree programUnitNode, SimpleScope scope)
			throws SyntaxException {
		int rule = programUnitNode.rule();
		List<BlockItemNode> items = new LinkedList<BlockItemNode>();

		switch (rule) {
		case 1101: /* MainProgramUnit */
			items.add((BlockItemNode) translateMainProgramUnit(programUnitNode,
					scope, 0));
			break;
		case 1231: /* Subroutine */
			items.add((BlockItemNode) translateMainProgramUnit(programUnitNode,
					scope, 1));
			break;
		default:
			assert false;
		}
		return items;
	}

	/* Public Function */

	public SequenceNode<BlockItemNode> generateRoot() throws SyntaxException {
		int numOfProgramUnit = parseTree.numChildren();
		ArrayList<BlockItemNode> programUnits = new ArrayList<BlockItemNode>();
		SimpleScope scope = new SimpleScope(null);
		Source source;

		assert numOfProgramUnit >= 0;
		for (int i = 0; i < numOfProgramUnit; i++) {
			programUnits.addAll(this.translateProgramUnit(
					parseTree.getChildByIndex(i), scope));
		}
		source = generateSource(parseTree);
		return nodeFactory.newTranslationUnitNode(source, programUnits);
	}

	public AST generateAST() throws SyntaxException {
		rootNode = this.generateRoot();
		Set<SourceFile> sourceFiles = new LinkedHashSet<>();
		sourceFiles.add(new SourceFile(new File(filePath), 0));
		AST ast = astFactory.newAST(rootNode, sourceFiles, false);
		return ast;
	}
}