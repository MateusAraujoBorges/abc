package edu.udel.cis.vsl.abc.front.fortran.astgen;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.PragmaNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode.ExpressionKind;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FloatingConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.FunctionCallNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IdentifierExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.IntegerConstantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.OperatorNode.Operator;
import edu.udel.cis.vsl.abc.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.label.SwitchLabelNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpExecutableNode;
import edu.udel.cis.vsl.abc.ast.node.IF.omp.OmpForNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.BlockItemNode.BlockItemKind;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.ForLoopInitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.LabeledStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.StatementNode.StatementKind;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.SwitchNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.ArrayTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.FunctionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.StructureOrUnionTypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode.TypeNodeKind;
import edu.udel.cis.vsl.abc.ast.type.IF.StandardBasicType.BasicTypeKind;
import edu.udel.cis.vsl.abc.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.front.IF.Front;
import edu.udel.cis.vsl.abc.front.IF.ParseException;
import edu.udel.cis.vsl.abc.front.IF.Parser;
import edu.udel.cis.vsl.abc.front.IF.Preprocessor;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.front.common.astgen.LibraryASTFactory;
import edu.udel.cis.vsl.abc.front.common.astgen.PragmaFactory;
import edu.udel.cis.vsl.abc.front.common.astgen.PragmaHandler;
import edu.udel.cis.vsl.abc.front.common.astgen.SimpleScope;
import edu.udel.cis.vsl.abc.front.fortran.ptree.FortranTree;
import edu.udel.cis.vsl.abc.token.IF.CivlcToken;
import edu.udel.cis.vsl.abc.token.IF.CivlcTokenSequence;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SourceFile;
import edu.udel.cis.vsl.abc.token.IF.StringToken;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;

public class FortranASTBuilderWorker {

	/* Instance Field */
	private Configuration configuration;

	private String filePath;

	private FortranTree parseTree;

	private TokenFactory tokenFactory;

	private LibraryASTFactory libFactory;

	// private TypeFactory typeFactory = Types.newTypeFactory();
	//
	// private ValueFactory valueFactory = Values.newValueFactory(configuration,
	// typeFactory);

	private NodeFactory nodeFactory;

	private ASTFactory astFactory;

	private PragmaFactory pragmaFactory;

	private SequenceNode<BlockItemNode> rootNode;

	private ArrayList<BlockItemNode> programUnits;
	private ArrayList<BlockItemNode> commonBlocks;

	private boolean hasSTDIO = false;
	private boolean hasMATH = false;

	private Map<String, TypeNode> localMap = new HashMap<String, TypeNode>();
	private Map<String, VariableDeclarationNode> argsMap = new HashMap<String, VariableDeclarationNode>();

	private Map<String, StructureOrUnionTypeNode> commonBlockSet = new TreeMap<String, StructureOrUnionTypeNode>();
	private Map<String, String> comm_val_block = new TreeMap<String, String>();

	private Stack<IdentifierNode> programUnitTrackStack = new Stack<IdentifierNode>();

	private Map<String, PragmaHandler> pragmaMap = new HashMap<>();
	// private Map<String, VariableDeclarationNode> localMap2 = new
	// HashMap<String, VariableDeclarationNode>();

	private List<BlockItemNode> tempItems = null;

	private SimpleScope rootScope = null;

	/* Constructor */

	public FortranASTBuilderWorker(Configuration config, FortranTree parseTree,
			ASTFactory astFactory, String filePath,
			PragmaFactory pragmaFactory) {
		this.configuration = config;
		this.parseTree = parseTree;
		this.filePath = filePath;
		this.astFactory = astFactory;
		this.nodeFactory = astFactory.getNodeFactory();
		this.tokenFactory = astFactory.getTokenFactory();
		this.programUnits = new ArrayList<BlockItemNode>();
		this.commonBlocks = new ArrayList<BlockItemNode>();
		this.pragmaFactory = pragmaFactory;

		Preprocessor libPreproc = Front.newPreprocessor(Language.C,
				configuration, tokenFactory.newFileIndexer(), tokenFactory);
		Parser libParser = Front.newParser(Language.C);

		this.libFactory = new LibraryASTFactory(libPreproc, libParser,
				Front.newASTBuilder(Language.C, configuration, astFactory));
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
		Formation dummyFormation = tokenFactory
				.newInclusion(new SourceFile(new File(this.filePath), 0));
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
			result = tokenFactory.newSource(tokenFactory
					.newCivlcToken(new CommonToken(0, ""), dummyFormation));
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
			boolean isMain, Map<String, VariableDeclarationNode> argsMap) {
		FunctionTypeNode functionType = null;
		Source typeSource = generateSource(programPrefixNode);
		TypeNode returnType;
		List<VariableDeclarationNode> formalList = new LinkedList<VariableDeclarationNode>();
		SequenceNode<VariableDeclarationNode> formals = null;
		boolean hasIdentifierList = parameterNode != null;

		returnType = nodeFactory.newVoidTypeNode(typeSource);
		if (isMain) {
			returnType = nodeFactory.newBasicTypeNode(typeSource,
					BasicTypeKind.INT);
		} else {
			returnType = nodeFactory.newVoidTypeNode(typeSource);
		}
		if (hasIdentifierList) {
			int numOfFormals = parameterNode.numChildren();

			for (int i = 0; i < numOfFormals; i++) {
				/*
				 * A first letter of I, J, K, L, M, or N implies type integer
				 * and any other letter type real, unless an IMPLICIT statement
				 * is used to change the default implied type.
				 */
				IdentifierNode formalIdNode = translateIdentifier(
						parameterNode.getChildByIndex(i));
				String formalName = formalIdNode.name();
				String pattern = "^(I|J|K|L|M|N|i|j|k|l|m|n).*$";
				TypeNode defaultType = null;
				VariableDeclarationNode formal = null;

				if (formalName.matches(pattern)) {
					defaultType = nodeFactory.newBasicTypeNode(typeSource,
							BasicTypeKind.INT);
				} else {
					defaultType = nodeFactory.newBasicTypeNode(typeSource,
							BasicTypeKind.DOUBLE);
				}
				if (defaultType.kind() == TypeNodeKind.BASIC)
					defaultType = nodeFactory.newPointerTypeNode(typeSource,
							defaultType.copy());
				formal = nodeFactory.newVariableDeclarationNode(typeSource,
						formalIdNode, defaultType);
				argsMap.put(formalName, formal);
				formalList.add(formal);
			}
		}
		formals = nodeFactory.newSequenceNode(typeSource, "FormalParameterList",
				formalList);
		functionType = nodeFactory.newFunctionTypeNode(typeSource, returnType,
				formals, hasIdentifierList);
		return functionType;
	}

	private FunctionTypeNode translateFunctionCallType(Source source,
			List<ExpressionNode> argumentList) {
		FunctionTypeNode functionType = null;
		TypeNode returnType = nodeFactory.newVoidTypeNode(source);
		List<VariableDeclarationNode> formalList = new LinkedList<VariableDeclarationNode>();
		SequenceNode<VariableDeclarationNode> formals = null;
		boolean hasIdentifierList = false;

		for (int i = 0; i < argumentList.size(); i++) {
			ExpressionNode actual = argumentList.get(i);
			IdentifierNode formalIdNode = nodeFactory.newIdentifierNode(source,
					"_dummy_arg_" + i);
			TypeNode formalTypeNode = generateType(actual);

			if (actual
					.expressionKind() == ExpressionKind.IDENTIFIER_EXPRESSION) {
				// formalIdNode = ((IdentifierNode) actual.child(0)).copy();
			}
			formalList.add(nodeFactory.newVariableDeclarationNode(source,
					formalIdNode, formalTypeNode));
		}
		formals = nodeFactory.newSequenceNode(source, "FormalParameterList",
				formalList);
		functionType = nodeFactory.newFunctionTypeNode(source, returnType,
				formals, hasIdentifierList);
		return functionType;
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
			case 403 :
				int specifierType = typeSpecifierNode.type();

				switch (specifierType) {
					case 400 : /* Integer */
						type = nodeFactory.newBasicTypeNode(source,
								BasicTypeKind.INT);
						break;
					case 401 : /* REAL */
						type = nodeFactory.newBasicTypeNode(source,
								BasicTypeKind.DOUBLE);
						break;
					case 402 : /* DOUBLE PRECISION */
						type = nodeFactory.newBasicTypeNode(source,
								BasicTypeKind.DOUBLE);
						break;
					default :
						assert false;
				}
				break;
			default :
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
			FortranTree declarationNode, SimpleScope scope,
			Map<String, VariableDeclarationNode> argsMap)
			throws SyntaxException {
		ArrayList<BlockItemNode> definitionList = new ArrayList<BlockItemNode>();
		// FortranTree labelDefinition = declarationNode.getChildByIndex(0);
		FortranTree declarationSpecifier = declarationNode.getChildByIndex(1);
		FortranTree declarationEntityList = declarationNode.getChildByIndex(2);
		int numOfDeclarator = declarationEntityList.numChildren();
		Source source = this.generateSource(declarationNode);

		assert numOfDeclarator > 0;
		for (int i = 0; i < numOfDeclarator; i++) {
			FortranTree entityNode = declarationEntityList.getChildByIndex(i);
			FortranTree identifierNode = entityNode.getChildByIndex(0);
			IdentifierNode name = this.translateIdentifier(identifierNode);
			TypeNode type = this
					.translateType(declarationSpecifier.getChildByIndex(0));
			VariableDeclarationNode declaration = null;
			boolean isArray = false;
			boolean hasInit = false;

			if (1 < entityNode.numChildren()) {
				FortranTree temp = entityNode.getChildByIndex(1);
				int rule = temp.rule();

				switch (rule) {
					case 510 :
						isArray = true;
						break;
					case 506 :
						hasInit = true;
						break;
					default :
						assert false;
				}
			}
			if (isArray) {
				FortranTree arrayInfoNode = entityNode.getChildByIndex(1);
				int arity = arrayInfoNode.numChildren();

				for (int j = arity - 1; j >= 0; j--) {
					FortranTree arityInfo = arrayInfoNode.getChildByIndex(j);
					int numOfIndexes = arityInfo.numChildren();
					ExpressionNode startIndexExprNode = null; // 1 by default.

					if (numOfIndexes == 0) {
						// For endIndex is '*'
						startIndexExprNode = nodeFactory
								.newIntegerConstantNode(source, "1");
						type = nodeFactory.newArrayTypeNode(source, type, null,
								startIndexExprNode);
						((ArrayTypeNode) type)
								.setUnspecifiedVariableLength(true);
						continue;
					}

					ExpressionNode endIndexExprNode = null;
					Operator operator = Operator.MINUS;
					List<ExpressionNode> arguments = new ArrayList<ExpressionNode>();
					ExpressionNode intOneConstNode = nodeFactory
							.newIntegerConstantNode(source, "1");

					if (numOfIndexes == 1) {
						// For only single endIndex
						startIndexExprNode = nodeFactory
								.newIntegerConstantNode(source, "1");
						endIndexExprNode = translateExpression(source,
								arityInfo.getChildByIndex(0), scope);

					} else if (numOfIndexes == 2) {
						// For two indexes
						startIndexExprNode = translateExpression(source,
								arityInfo.getChildByIndex(0), scope);
						endIndexExprNode = translateExpression(source,
								arityInfo.getChildByIndex(1), scope);
					} else {
						System.out.println(numOfIndexes);
					}
					arguments.add(endIndexExprNode);
					arguments.add(startIndexExprNode);

					OperatorNode extentNode = nodeFactory
							.newOperatorNode(source, operator, arguments);

					operator = Operator.PLUS;
					arguments.clear();
					arguments.add(extentNode);
					arguments.add(intOneConstNode);
					extentNode = nodeFactory.newOperatorNode(source, operator,
							arguments);
					type = nodeFactory.newArrayTypeNode(source, type,
							extentNode, startIndexExprNode.copy());
				}
				declaration = nodeFactory.newVariableDeclarationNode(source,
						name, type);
				localMap.put(name.name(), type);
			} else if (hasInit) {
				FortranTree initNode = entityNode.getChildByIndex(1)
						.getChildByIndex(0);
				InitializerNode initializer = translateInitializer(initNode,
						scope);

				declaration = nodeFactory.newVariableDeclarationNode(source,
						name, type);
				declaration.setInitializer(initializer);
				localMap.put(name.name(), type);
			} else {
				declaration = nodeFactory.newVariableDeclarationNode(source,
						name, type);
				localMap.put(name.name(), type);
			}

			if (argsMap != null) {
				String formalName = name.name();
				VariableDeclarationNode argNode = argsMap.get(formalName);

				if (argNode != null) {
					TypeNode argType = type.copy();

					argType.remove();

					TypeNode tempNode = argType;

					if (tempNode.kind() == TypeNodeKind.BASIC)
						argType = nodeFactory.newPointerTypeNode(source,
								argType.copy());
					// Eliminate sub-scripts of the actual arg with array type
					while (tempNode.kind() == TypeNodeKind.ARRAY) {
						tempNode.removeChild(1);
						tempNode = (TypeNode) tempNode.child(0);
					}
					argNode.setTypeNode(argType);
				} else {
					definitionList.add(declaration);
				}
			} else {
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
		List<ExpressionNode> arguments = null;

		switch (rule) {
			case 613 : /* PartRef(ArrayUnit) */
				FortranTree id = exprNode.getChildByIndex(0);
				FortranTree indexUnitsNode = exprNode.getChildByIndex(1);
				int arity = indexUnitsNode.numChildren();
				IdentifierNode idNode = translateIdentifier(id);
				ExpressionNode idExprNode = nodeFactory
						.newIdentifierExpressionNode(source, idNode);
				ExpressionNode indexNode = null;
				ExpressionNode adjustedIndexNode = null;

				for (int i = 0; i < arity; i++) {
					FortranTree indexUnitNode = indexUnitsNode
							.getChildByIndex(i).getChildByIndex(0);
					TypeNode arrayType = localMap.get(idNode.name());
					ExpressionNode startIndexNode = null;

					for (int j = 1; j <= i; j++) {
						arrayType = (TypeNode) arrayType.child(0);
					}
					startIndexNode = (ExpressionNode) arrayType.child(2);
					operator = Operator.SUBSCRIPT;
					arguments = new LinkedList<ExpressionNode>();
					indexNode = translateExpression(source, indexUnitNode,
							scope);
					adjustedIndexNode = adjustIndex(source, indexNode,
							startIndexNode);
					arguments.add(idExprNode);
					arguments.add(adjustedIndexNode);
					idExprNode = nodeFactory.newOperatorNode(source, operator,
							arguments);

					String idName = idNode.name();

					if (idName != null && comm_val_block.containsKey(idName)) {
						IdentifierNode unionIdNode = nodeFactory
								.newIdentifierNode(source,
										comm_val_block.get(idName));
						IdentifierNode structIdNode = nodeFactory
								.newIdentifierNode(source,
										programUnitTrackStack.peek().name());
						IdentifierNode varIdNode = nodeFactory
								.newIdentifierNode(source, idName);

						ExpressionNode u_lhsArgExprNode = nodeFactory
								.newIdentifierExpressionNode(source,
										unionIdNode);
						ExpressionNode s_lhsArgExprNode = nodeFactory
								.newDotNode(source, u_lhsArgExprNode,
										structIdNode);
						ExpressionNode a_lhsArgExprNode = nodeFactory
								.newDotNode(source, s_lhsArgExprNode,
										varIdNode);
						ExpressionNode temp_indexNode = null;
						LinkedList<ExpressionNode> temp_args = new LinkedList<ExpressionNode>();

						temp_args.add(a_lhsArgExprNode);
						temp_indexNode = (ExpressionNode) idExprNode.child(1);
						temp_indexNode.remove();
						temp_args.add(temp_indexNode);
						idExprNode = nodeFactory.newOperatorNode(source,
								Operator.SUBSCRIPT, temp_args);
					}
				}
				return (OperatorNode) idExprNode;
			case 734 : /* Assign */
				ExpressionNode lhsArgExprNode = translateExpression(source,
						exprNode.getChildByIndex(1), scope);
				ExpressionNode rhsArgExprNode = translateExpression(source,
						exprNode.getChildByIndex(2), scope);
				String assignedIdName = "";

				operator = Operator.ASSIGN;
				arguments = new LinkedList<ExpressionNode>();
				if (lhsArgExprNode
						.expressionKind() == ExpressionKind.IDENTIFIER_EXPRESSION) {
					IdentifierNode assignedIdNode = (IdentifierNode) lhsArgExprNode
							.child(0);
					assignedIdName = assignedIdNode.name();

					if (!localMap.containsKey(assignedIdName)) {
						String pattern = "^(I|J|K|L|M|N|i|j|k|l|m|n).*$";
						TypeNode type = null;
						VariableDeclarationNode varDeclNode = null;

						if (assignedIdName.matches(pattern)) {
							type = nodeFactory.newBasicTypeNode(source,
									BasicTypeKind.INT);
						} else {
							type = nodeFactory.newBasicTypeNode(source,
									BasicTypeKind.DOUBLE);
						}
						varDeclNode = nodeFactory.newVariableDeclarationNode(
								source, assignedIdNode.copy(), type);
						localMap.put(assignedIdName, type);
						tempItems.add(0, varDeclNode);
					}
					if (assignedIdName != null
							&& comm_val_block.containsKey(assignedIdName)) {
						IdentifierNode unionIdNode = nodeFactory
								.newIdentifierNode(source,
										comm_val_block.get(assignedIdName));
						IdentifierNode structIdNode = nodeFactory
								.newIdentifierNode(source,
										programUnitTrackStack.peek().name());
						IdentifierNode varIdNode = nodeFactory
								.newIdentifierNode(source, assignedIdName);

						ExpressionNode u_lhsArgExprNode = nodeFactory
								.newIdentifierExpressionNode(source,
										unionIdNode);
						ExpressionNode s_lhsArgExprNode = nodeFactory
								.newDotNode(source, u_lhsArgExprNode,
										structIdNode);

						lhsArgExprNode = nodeFactory.newDotNode(source,
								s_lhsArgExprNode, varIdNode);
					}
				}
				arguments.add(lhsArgExprNode);
				arguments.add(rhsArgExprNode);
				return nodeFactory.newOperatorNode(source, operator, arguments);
			case 710 : /* Lv3 Expression */
				String opStr = exprNode.getChildByIndex(0).cTokens()[0]
						.getText();

				if (opStr.contains("EQ")) {
					operator = Operator.EQUALS;
					arguments = new LinkedList<ExpressionNode>();
					for (int i = 1; i < 3; i++) {
						ExpressionNode argument = translateExpression(source,
								exprNode.getChildByIndex(i), scope);
						arguments.add(argument);
					}
					return nodeFactory.newOperatorNode(source, operator,
							arguments);
				} else if (opStr.contains("NE")) {
					operator = Operator.NEQ;
					arguments = new LinkedList<ExpressionNode>();
					for (int i = 1; i < 3; i++) {
						ExpressionNode argument = translateExpression(source,
								exprNode.getChildByIndex(i), scope);

						arguments.add(argument);
					}
					return nodeFactory.newOperatorNode(source, operator,
							arguments);
				} else {
					System.out.println(opStr);
					assert false;
				}

				break;
			case 704 : /* MultOperand(s) */
				for (int i = 0; i < exprNode.numChildren(); i++) {
					String op_string = exprNode.getChildByIndex(0).cTokens()[0]
							.getText();
					ExpressionNode leftNode = null;
					ExpressionNode rightNode = null;

					operator = op_string.startsWith("*")
							? Operator.TIMES
							: Operator.DIV;
					arguments = new LinkedList<ExpressionNode>();
					leftNode = translateExpression(source,
							exprNode.getChildByIndex(1), scope);
					rightNode = translateExpression(source,
							exprNode.getChildByIndex(2), scope);
					arguments.add(leftNode);
					arguments.add(rightNode);
				}
				return nodeFactory.newOperatorNode(source, operator, arguments);
			case 705 : /* AddOperand(s) */
				if (exprNode.numChildren() == 2) {// signed
					String op_string = exprNode.getChildByIndex(0).cTokens()[0]
							.getText();
					ExpressionNode unaryNode = null;

					operator = op_string.startsWith("+")
							? Operator.UNARYPLUS
							: Operator.UNARYMINUS;
					unaryNode = translateExpression(source,
							exprNode.getChildByIndex(1), scope);
					return nodeFactory.newOperatorNode(source, operator,
							unaryNode);
				}
				for (int i = 0; i < exprNode.numChildren(); i++) {
					String op_string = exprNode.getChildByIndex(0).cTokens()[0]
							.getText();
					ExpressionNode leftNode = null;
					ExpressionNode rightNode = null;

					operator = op_string.startsWith("+")
							? Operator.PLUS
							: Operator.MINUS;
					arguments = new LinkedList<ExpressionNode>();
					leftNode = translateExpression(source,
							exprNode.getChildByIndex(1), scope);
					rightNode = translateExpression(source,
							exprNode.getChildByIndex(2), scope);
					arguments.add(leftNode);
					arguments.add(rightNode);
				}
				return nodeFactory.newOperatorNode(source, operator, arguments);
			default :
				System.out.println(rule);
				assert false;
		}
		result = nodeFactory.newOperatorNode(source, operator, arguments);
		return result;
	}

	private ExpressionNode adjustIndex(Source source, ExpressionNode indexNode,
			ExpressionNode startIndexNode) throws SyntaxException {
		Operator operator = Operator.MINUS;
		List<ExpressionNode> arguments = new ArrayList<ExpressionNode>();

		arguments.add(indexNode);
		arguments.add(startIndexNode.copy());
		return nodeFactory.newOperatorNode(source, operator, arguments);
	}

	private IntegerConstantNode translateIntegerConstant(Source source,
			FortranTree constNode) throws SyntaxException {
		String representation = constNode.getChildByIndex(0).cTokens()[0]
				.getText();

		return nodeFactory.newIntegerConstantNode(source, representation);
	}

	public ExpressionNode translateExpression(FortranTree exprNode,
			SimpleScope scope) throws SyntaxException {
		return translateExpression(generateSource(exprNode), exprNode, scope);
	}

	private ExpressionNode translateExpression(Source source,
			FortranTree exprNode, SimpleScope scope) throws SyntaxException {
		ExpressionNode result = null;
		int rule = exprNode.rule();

		switch (rule) {
			case 734 : /* Assign */
				return translateOperatorExpression(source, exprNode, scope);
			case 601 : /* Variable */
				int var_type = exprNode.getChildByIndex(0).rule();

				switch (var_type) {
					case 603 : /* Designator */
						FortranTree refNode = exprNode.getChildByIndex(0)
								.getChildByIndex(0).getChildByIndex(0);

						if (refNode.numChildren() < 2) {
							IdentifierExpressionNode idExprNode = nodeFactory
									.newIdentifierExpressionNode(source,
											translateIdentifier(refNode
													.getChildByIndex(0)));
							String varName = idExprNode.getIdentifier().name();

							if (argsMap.containsKey(varName))
								return nodeFactory.newOperatorNode(source,
										Operator.DEREFERENCE, idExprNode);

							return idExprNode;
						} else {
							return translateOperatorExpression(source, refNode,
									scope);
						}
					default :
						assert false;
				}
			case 701 : /* PrimaryExpr */
				FortranTree primExprNode = exprNode.getChildByIndex(0);
				int expr_type = primExprNode.rule();

				switch (expr_type) {
					case -3 : /* DesignatorOrFunctionRef */
						FortranTree refNode = primExprNode.getChildByIndex(0)
								.getChildByIndex(0);

						if (refNode.numChildren() < 2) {
							IdentifierNode idNode = translateIdentifier(
									refNode.getChildByIndex(0));
							String idName = idNode.name();

							if (idName != null
									&& comm_val_block.containsKey(idName)) {
								IdentifierNode unionIdNode = nodeFactory
										.newIdentifierNode(source,
												comm_val_block.get(idName));
								IdentifierNode structIdNode = nodeFactory
										.newIdentifierNode(source,
												programUnitTrackStack.peek()
														.name());
								IdentifierNode varIdNode = nodeFactory
										.newIdentifierNode(source, idName);

								ExpressionNode u_lhsArgExprNode = nodeFactory
										.newIdentifierExpressionNode(source,
												unionIdNode);
								ExpressionNode s_lhsArgExprNode = nodeFactory
										.newDotNode(source, u_lhsArgExprNode,
												structIdNode);

								return nodeFactory.newDotNode(source,
										s_lhsArgExprNode, varIdNode);
							}
							IdentifierExpressionNode idExprNode = nodeFactory
									.newIdentifierExpressionNode(source,
											translateIdentifier(refNode
													.getChildByIndex(0)));
							String varName = idExprNode.getIdentifier().name();

							if (argsMap.containsKey(varName))
								return nodeFactory.newOperatorNode(source,
										Operator.DEREFERENCE, idExprNode);

							return idExprNode;
						} else {
							FortranTree refIdNode = refNode.getChildByIndex(0);
							String IdStr = refIdNode.cTokens()[0].getText();

							if (IdStr.matches("^I?AND$")) {
								Operator operator = Operator.BITAND;
								List<ExpressionNode> arguments = new LinkedList<ExpressionNode>();

								for (int i = 0; i < 2; i++) {
									ExpressionNode argument = translateExpression(
											source,
											refNode.getChildByIndex(1)
													.getChildByIndex(i)
													.getChildByIndex(0),
											scope);

									arguments.add(argument);
								}
								return nodeFactory.newOperatorNode(source,
										operator, arguments);
							} else if (IdStr.matches("^I?OR$")) {
								Operator operator = Operator.BITOR;
								List<ExpressionNode> arguments = new LinkedList<ExpressionNode>();

								for (int i = 0; i < 2; i++) {
									ExpressionNode argument = translateExpression(
											source,
											refNode.getChildByIndex(1)
													.getChildByIndex(i)
													.getChildByIndex(0),
											scope);

									arguments.add(argument);
								}
								return nodeFactory.newOperatorNode(source,
										operator, arguments);
							} else if (IdStr.matches("^IEOR|XOR$")) {
								Operator operator = Operator.BITOR;
								List<ExpressionNode> arguments = new LinkedList<ExpressionNode>();

								for (int i = 0; i < 2; i++) {
									ExpressionNode argument = translateExpression(
											source,
											refNode.getChildByIndex(1)
													.getChildByIndex(i)
													.getChildByIndex(0),
											scope);

									arguments.add(argument);
								}
								return nodeFactory.newOperatorNode(source,
										operator, arguments);
							} else if (IdStr.matches("^MODULO$")) {
								Operator operator = Operator.MOD;
								List<ExpressionNode> arguments = new LinkedList<ExpressionNode>();

								for (int i = 0; i < 2; i++) {
									ExpressionNode argument = translateExpression(
											source,
											refNode.getChildByIndex(1)
													.getChildByIndex(i)
													.getChildByIndex(0),
											scope);

									arguments.add(argument);
								}
								return nodeFactory.newOperatorNode(source,
										operator, arguments);
							} else if (IdStr.matches("^SIN|COS|SQRT|LOG$")) {
								hasMATH = true;

								FortranTree argumentListTree = null;
								IdentifierNode idNode = nodeFactory
										.newIdentifierNode(source,
												IdStr.toLowerCase());
								ExpressionNode functionNode = nodeFactory
										.newIdentifierExpressionNode(source,
												idNode);
								boolean hasSubScripts = refNode
										.numChildren() > 1;
								int numArgs = 0;
								List<ExpressionNode> argumentList = new LinkedList<ExpressionNode>();

								if (hasSubScripts) {
									argumentListTree = refNode
											.getChildByIndex(1);
									numArgs = argumentListTree.numChildren();
									for (int i = 0; i < numArgs; i++) {
										FortranTree argumentTree = argumentListTree
												.getChildByIndex(i)
												.getChildByIndex(0);
										ExpressionNode argumentNode = translateExpression(
												source, argumentTree, scope);

										argumentList.add(argumentNode);
									}
								}
								return nodeFactory.newFunctionCallNode(source,
										functionNode, argumentList, null);
							} else {
								// Array Read
							}
							return translateOperatorExpression(source, refNode,
									scope);
						}
					case 306 : /* LiteralConst */
						FortranTree constNode = primExprNode.getChildByIndex(0);
						int const_type = constNode.rule();

						switch (const_type) {
							case 406 : /* IntLitConst */
								return translateIntegerConstant(source,
										constNode);
							case 417 : /* RealLitConst */
								return translateFloatingConstant(source,
										constNode);
							case 427 : /* CharLitConst */
								return translateCharLitConstant(source,
										constNode);
							default :
								System.out.println(const_type);
								assert false;
						}
					case 704 : /* MultOperand(s) */
						return translateOperatorExpression(source, primExprNode,
								scope);
					case 705 : /* AddOperand(s) */
						return translateOperatorExpression(source, primExprNode,
								scope);
					default :
						System.out.print(expr_type);
						assert false;
				}
			case 704 : /* MultOperand(s) */
				return translateOperatorExpression(source, exprNode, scope);
			case 705 : /* AddOperand(s) */
				return translateOperatorExpression(source, exprNode, scope);
			case 710 : /* Lv3 Expression */
				return translateOperatorExpression(source, exprNode, scope);
			default :
				System.out.println(rule);
				assert false;
		}
		return result;
	}

	private ExpressionNode translateCharLitConstant(Source source,
			FortranTree constNode) throws SyntaxException {
		FortranTree contentTree = constNode.getChildByIndex(0);
		CivlcToken cToken = contentTree.cTokens()[0];
		String content = cToken.getText().replace('\'', '\"');

		cToken.setText(content);

		StringToken strToken = tokenFactory.newStringToken(cToken);

		return nodeFactory.newStringLiteralNode(generateSource(contentTree),
				content, strToken.getStringLiteral());
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
			case 734 : /* AssignmentStatement */
				result = translateExpressionStatement(blockItemNode, scope);
				break;
			case 802 : /* IfConstruct */
				result = translateIfStatement(blockItemNode, scope);
				break;
			case 827 : /* DoStatement */
				result = translateDoStatement(blockItemNode.parent(), scope);
				break;
			case 845 : /* GotoStatement */
				result = translateGoto(blockItemNode);
				break;
			case 846 : /* ComputedGotoStatement */
				result = translateComputedGoto(blockItemNode, scope);
				break;
			case 1218 : /* CallStatement */
				result = translateCall(generateSource(blockItemNode),
						blockItemNode, scope);
				break;
			case 1236 : /* ReaturnStatement */
				// TODO: Return Statement for Fortran represents a exit for a
				// subprogram such as subroutines or functions.
				result = nodeFactory
						.newReturnNode(generateSource(blockItemNode), null);
				break;
			case 801 : /* Block */
				assert false;
				break;
			case -501 : /* Pragma Statement */ {
				ASTNode newNode = translatePragma(blockItemNode, scope);

				if (newNode == null)
					return null;
				else if (newNode instanceof StatementNode)
					return (StatementNode) newNode;
				else
					throw error("This pragma cannot be used as a statement",
							(FortranTree) newNode);
			}
			default :
				System.out.println(rule);
				assert false;
		}

		return result;

	}

	private StatementNode translateIfStatement(FortranTree blockItemNode,
			SimpleScope scope) throws SyntaxException {
		int numOfChildren = blockItemNode.numChildren();
		int blockIndex = numOfChildren - 2;
		ExpressionNode condition = null;
		StatementNode trueBranch = null, falseBranch = null;
		StatementNode result = null;
		SimpleScope ifScope = new SimpleScope(scope);

		while (blockIndex > 0) {
			assert blockIndex % 2 == 1;

			FortranTree blockNode = blockItemNode.getChildByIndex(blockIndex);
			FortranTree condNode = blockItemNode
					.getChildByIndex(blockIndex - 1);
			int rule = condNode.rule();

			if (rule == 803) {
				// IF
				Source blockSource = generateSource(blockNode);
				Source condSource = generateSource(condNode);

				condition = translateExpression(condSource,
						condNode.getChildByIndex(2), ifScope);
				trueBranch = translateBody(null, blockNode,
						new SimpleScope(ifScope), null);

				result = falseBranch == null
						? nodeFactory.newIfNode(blockSource, condition,
								trueBranch)
						: nodeFactory.newIfNode(blockSource, condition,
								trueBranch, falseBranch);
			} else if (rule == 804) {
				// ELSE IF
				Source blockSource = generateSource(blockNode);
				Source condSource = generateSource(condNode);

				condition = translateExpression(condSource,
						condNode.getChildByIndex(2), ifScope);
				trueBranch = translateBody(null, blockNode,
						new SimpleScope(ifScope), null);
				falseBranch = falseBranch == null
						? nodeFactory.newIfNode(blockSource, condition,
								trueBranch)
						: nodeFactory.newIfNode(blockSource, condition,
								trueBranch, falseBranch);
			} else { /* 805 */
				// ELSE
				falseBranch = translateBody(null, blockNode,
						new SimpleScope(ifScope), null);
			}
			blockIndex -= 2;
		}
		return result;
	}

	private StatementNode translateCall(Source source,
			FortranTree blockItemNode, SimpleScope scope)
			throws SyntaxException {
		FortranTree functionTree = blockItemNode.getChildByIndex(1);
		FortranTree functionIdNode = functionTree.getChildByIndex(0)
				.getChildByIndex(0);
		FortranTree argumentListTree = null;
		IdentifierNode idNode = translateIdentifier(
				functionIdNode.getChildByIndex(0));
		ExpressionNode functionNode = nodeFactory
				.newIdentifierExpressionNode(source, idNode);
		boolean hasActualArgs = blockItemNode.numChildren() > 2;
		boolean hasSubScripts = functionIdNode.numChildren() > 1;
		int numArgs = 0;
		List<ExpressionNode> parameterList = new LinkedList<ExpressionNode>();
		List<ExpressionNode> argumentList = new LinkedList<ExpressionNode>();

		if (hasActualArgs) {
			assert false;
		}
		if (hasSubScripts) {
			argumentListTree = functionIdNode.getChildByIndex(1);
			numArgs = argumentListTree.numChildren();
			for (int i = 0; i < numArgs; i++) {
				FortranTree argumentTree = argumentListTree.getChildByIndex(i)
						.getChildByIndex(0);
				ExpressionNode argumentNode = translateExpression(source,
						argumentTree, scope);

				parameterList.add(argumentNode);
				if (argumentNode instanceof IdentifierExpressionNode) {
					argumentNode = nodeFactory.newOperatorNode(source,
							Operator.ADDRESSOF, argumentNode);
				}
				argumentList.add(argumentNode);
			}
		}

		FunctionCallNode callNode = nodeFactory.newFunctionCallNode(source,
				functionNode, argumentList, null);
		FunctionTypeNode typeNode = translateFunctionCallType(source,
				parameterList);
		FunctionDeclarationNode declNode = nodeFactory
				.newFunctionDeclarationNode(source, idNode.copy(), typeNode,
						null);

		programUnits.add(0, declNode);
		return nodeFactory.newExpressionStatementNode(callNode);
	}

	private TypeNode generateType(ExpressionNode argumentNode) {
		TypeNode argType = null;
		ExpressionKind exprKind = argumentNode.expressionKind();

		if (exprKind == ExpressionKind.IDENTIFIER_EXPRESSION) {
			String key = ((IdentifierNode) argumentNode.child(0)).name();

			argType = localMap.get(key);
			if (argType == null) {
				String pattern = "^(I|J|K|L|M|N|i|j|k|l|m|n).*$";

				if (key.matches(pattern)) {
					argType = nodeFactory.newBasicTypeNode(
							argumentNode.getSource(), BasicTypeKind.INT);
				} else {
					argType = nodeFactory.newBasicTypeNode(
							argumentNode.getSource(), BasicTypeKind.DOUBLE);
				}
			} else {
				argType = argType.copy();
				argType.remove();
			}

			TypeNode tempNode = argType;

			if (tempNode.kind() == TypeNodeKind.BASIC)
				argType = nodeFactory.newPointerTypeNode(
						argumentNode.getSource(), argType.copy());
			// Eliminate sub-scripts of the actual arg with array type
			while (tempNode.kind() == TypeNodeKind.ARRAY) {
				tempNode.removeChild(1);
				tempNode = (TypeNode) tempNode.child(0);
			}
		} else if (exprKind == ExpressionKind.CONSTANT) {
			TypeKind typeKind = argumentNode.getInitialType().kind();

			if (typeKind == TypeKind.BASIC) {
				assert false;
			}
		} else {
			assert false;
		}
		return argType;
	}

	private StatementNode translateDoStatement(FortranTree blockItemNode,
			SimpleScope scope) throws SyntaxException {
		ForLoopInitializerNode initializerNode;
		ExpressionNode conditionNode;
		ExpressionNode incrementerNode = null;
		StatementNode bodyNode;
		// ExpressionNode invariantNode;
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
		IdentifierNode doVarIdNode = translateIdentifier(doVariableNode);
		String assignedIdName = doVarIdNode.name();

		if (!localMap.containsKey(assignedIdName)) {
			String pattern = "^(I|J|K|L|M|N|i|j|k|l|m|n).*$";
			TypeNode type = null;
			VariableDeclarationNode varDeclNode = null;

			if (assignedIdName.matches(pattern)) {
				type = nodeFactory.newBasicTypeNode(initSource,
						BasicTypeKind.INT);
			} else {
				type = nodeFactory.newBasicTypeNode(initSource,
						BasicTypeKind.DOUBLE);
			}
			varDeclNode = nodeFactory.newVariableDeclarationNode(initSource,
					doVarIdNode.copy(), type);
			localMap.put(assignedIdName, type);
			tempItems.add(0, varDeclNode);
		}
		initArgs.add(nodeFactory.newIdentifierExpressionNode(doVariableSource,
				doVarIdNode));
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
		bodyNode = translateBody(null, doBodyNode, scope, null);

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
			IntegerConstantNode caseNum = nodeFactory
					.newIntegerConstantNode(labelSource, "" + (i + 1));
			StatementNode gotoStatement = nodeFactory.newGotoNode(source,
					nodeFactory.newIdentifierNode(labelSource, labelStr));
			SwitchLabelNode labelDecl = nodeFactory.newCaseLabelDeclarationNode(
					labelSource, caseNum, gotoStatement);
			LabeledStatementNode tempItemNode = nodeFactory
					.newLabeledStatementNode(source, labelDecl, gotoStatement);
			items.add(tempItemNode);
		}
		return nodeFactory.newCompoundStatementNode(source, items);
	}

	private PragmaHandler getPragmaHandler(String code) {
		PragmaHandler result = pragmaMap.get(code);

		if (result == null) {
			result = pragmaFactory.newHandler(code, parseTree);
			pragmaMap.put(code, result);
		}
		return result;
	}

	private ASTNode translatePragma(FortranTree pragmaTree, SimpleScope scope)
			throws SyntaxException {
		Source source = generateSource(pragmaTree);
		FortranTree identifierTree = pragmaTree.getChildByIndex(0);
		IdentifierNode identifier = translateIdentifier(identifierTree);
		String code = identifier.name();
		FortranTree bodyTree = pragmaTree.getChildByIndex(1);
		FortranTree newlineTree = pragmaTree.getChildByIndex(2);
		CivlcToken newlineToken = (CivlcToken) newlineTree.cTokens()[0];
		CivlcTokenSequence producer = parseTree
				.getTokenSourceProducer(bodyTree);
		PragmaNode pragmaNode = nodeFactory.newPragmaNode(source, identifier,
				producer, newlineToken);
		PragmaHandler handler;
		ASTNode result = null;

		if (code.equals("CIVL")) {
			CivlcToken tokens[] = bodyTree.getChildByIndex(0).cTokens();

			if (tokens.length > 0 && tokens[0].equals("ACSL"))
				return null;
		}
		handler = getPragmaHandler(code);
		identifier.setEntity(handler);
		try {
			result = handler.processPragmaNode(pragmaNode, scope);
		} catch (ParseException e) {
			this.error(e.getMessage(), pragmaTree);
		}
		return result;
	}

	private List<BlockItemNode> translateBlockItem(FortranTree blockItemNode,
			SimpleScope scope, Map<String, VariableDeclarationNode> argsMap)
			throws SyntaxException {
		List<BlockItemNode> result = null;
		int rule = blockItemNode.rule();

		switch (rule) {
			case 212 : /* Other Specification Statement */
				result = this.translateSpecificationStatement(blockItemNode,
						scope);
				break;
			case 501 : /* Type Declaration Statement */
				result = this.translateTypeDeclaration(blockItemNode, scope,
						argsMap);
				break;
			case 538 : /* Parameter Statement */
				result = this.translateParameterStatement(blockItemNode, scope);
				break;
			case 734 : /* Assignment Statement */
				result = new ArrayList<BlockItemNode>();
				result.add((BlockItemNode) this
						.translateStatement(blockItemNode, scope));
				break;
			case 827 : /* Do Statement */
				result = new ArrayList<BlockItemNode>();
				result.add((BlockItemNode) this
						.translateStatement(blockItemNode, scope));
				break;
			case 845 : /* Goto Statement */
				result = new ArrayList<BlockItemNode>();
				result.add((BlockItemNode) this
						.translateStatement(blockItemNode, scope));
				break;
			case 846 : /* ComputedGoto Statement */
				result = new ArrayList<BlockItemNode>();
				result.add((BlockItemNode) this
						.translateStatement(blockItemNode, scope));
				break;
			case 848 : /* Continue Statement */
				result = new ArrayList<BlockItemNode>();
				result.add((BlockItemNode) this
						.translateIdentifierLabeledStatement(blockItemNode,
								scope));
				break;
			case 912 : /* Print Statement */
				result = new ArrayList<BlockItemNode>();
				result.add((BlockItemNode) this
						.translatePrintStatement(blockItemNode, scope));
				break;
			case 1218 : /* Call Statement */
				result = new ArrayList<BlockItemNode>();
				result.add((BlockItemNode) this
						.translateStatement(blockItemNode, scope));
				break;
			case 1236 : /* Return Statement */
				result = new ArrayList<BlockItemNode>();
				result.add((BlockItemNode) this
						.translateStatement(blockItemNode, scope));
				break;
			default :
				System.out.println(rule);
				assert false;
		}
		return result;
	}

	private List<BlockItemNode> translateSpecificationStatement(
			FortranTree blockItemNode, SimpleScope scope) {
		List<BlockItemNode> result = null;
		FortranTree commonBlockTreeNode = blockItemNode.getChildByIndex(0);
		int rule = commonBlockTreeNode.rule();

		switch (rule) {
			case 557 : /* Common Statement */
				result = this.translateCommonStatement(commonBlockTreeNode,
						new SimpleScope(rootScope, false));
				break;
			default :
				System.out.println(rule);
				assert false;

		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<BlockItemNode> translateCommonStatement(
			FortranTree blockItemNode, SimpleScope simpleScope) {
		FortranTree idTreeNode = blockItemNode.getChildByIndex(1)
				.getChildByIndex(0);
		FortranTree fieldsTreeNode = blockItemNode.getChildByIndex(2);
		IdentifierNode unionIdNode = this.translateIdentifier(idTreeNode);
		IdentifierNode unionTypeIdNode = nodeFactory.newIdentifierNode(
				generateSource(idTreeNode), "u_" + unionIdNode.name());
		String blockName = unionIdNode.name();
		FieldDeclarationNode localCommonBlockNode;

		if (commonBlockSet.containsKey(blockName)) {
			StructureOrUnionTypeNode unionTypeNode = commonBlockSet
					.get(unionIdNode.name());

			localCommonBlockNode = generateLocalCommonBlockNode(fieldsTreeNode,
					simpleScope, blockName);
			for (ASTNode node : unionTypeNode.children()) {
				if (node instanceof SequenceNode)
					((SequenceNode<FieldDeclarationNode>) unionTypeNode
							.child(1)).addSequenceChild(localCommonBlockNode);
			}

		} else {
			Source unionSource = generateSource(blockItemNode);
			List<FieldDeclarationNode> unionFieldDecls = new LinkedList<FieldDeclarationNode>();
			SequenceNode<FieldDeclarationNode> unionDeclList;
			StructureOrUnionTypeNode unionTypeNode;

			localCommonBlockNode = generateLocalCommonBlockNode(fieldsTreeNode,
					simpleScope, blockName);
			unionFieldDecls.add(localCommonBlockNode);
			unionDeclList = nodeFactory.newSequenceNode(unionSource,
					"FieldDeclarations", unionFieldDecls);
			unionTypeNode = nodeFactory.newStructOrUnionTypeNode(unionSource,
					false, unionTypeIdNode, unionDeclList);
			commonBlocks
					.add((BlockItemNode) nodeFactory.newVariableDeclarationNode(
							unionSource, unionIdNode.copy(), unionTypeNode));
			commonBlockSet.put(unionIdNode.name(), unionTypeNode);
		}
		return new ArrayList<BlockItemNode>();
	}

	private FieldDeclarationNode generateLocalCommonBlockNode(
			FortranTree fieldsTreeNode, SimpleScope simpleScope,
			String blockName) {
		Source structSource = generateSource(fieldsTreeNode);
		IdentifierNode programUnitIdNode = programUnitTrackStack.peek();
		String structIdStr = programUnitIdNode.name();
		IdentifierNode structIdNode = nodeFactory
				.newIdentifierNode(programUnitIdNode.getSource(), structIdStr);
		IdentifierNode structTypeIdNode = nodeFactory.newIdentifierNode(
				programUnitIdNode.getSource(), "s_" + structIdStr);
		List<FieldDeclarationNode> structFieldDecls = new LinkedList<FieldDeclarationNode>();

		try {
			for (FortranTree commBlockObjTreeNode : fieldsTreeNode.children()) {
				FortranTree idTreeNode = commBlockObjTreeNode
						.getChildByIndex(0);
				IdentifierNode fieldIdNode = this
						.translateIdentifier(idTreeNode);
				//
				Source varSource = generateSource(idTreeNode);
				TypeNode type = nodeFactory.newBasicTypeNode(varSource,
						BasicTypeKind.INT);
				boolean isArray = 1 < commBlockObjTreeNode.numChildren();

				comm_val_block.put(fieldIdNode.name(), blockName);
				if (isArray) {
					FortranTree arrayInfoNode = commBlockObjTreeNode
							.getChildByIndex(1);
					int arity = arrayInfoNode.numChildren();

					for (int j = arity - 1; j >= 0; j--) {
						FortranTree arityInfo = arrayInfoNode
								.getChildByIndex(j);
						int numOfIndexes = arityInfo.numChildren();
						ExpressionNode startIndexExprNode = null; // 1 by
																	// default.

						if (numOfIndexes == 0) {
							// For endIndex is '*'
							startIndexExprNode = nodeFactory
									.newIntegerConstantNode(varSource, "1");
							type = nodeFactory.newArrayTypeNode(varSource, type,
									null, startIndexExprNode);
							((ArrayTypeNode) type)
									.setUnspecifiedVariableLength(true);
							continue;
						}

						ExpressionNode endIndexExprNode = null;
						Operator operator = Operator.MINUS;
						List<ExpressionNode> arguments = new ArrayList<ExpressionNode>();
						ExpressionNode intOneConstNode = nodeFactory
								.newIntegerConstantNode(varSource, "1");

						if (numOfIndexes == 1) {
							// For only single endIndex
							startIndexExprNode = nodeFactory
									.newIntegerConstantNode(varSource, "1");
							endIndexExprNode = translateExpression(varSource,
									arityInfo.getChildByIndex(0), simpleScope);

						} else if (numOfIndexes == 2) {
							// For two indexes
							startIndexExprNode = translateExpression(varSource,
									arityInfo.getChildByIndex(0), simpleScope);
							endIndexExprNode = translateExpression(varSource,
									arityInfo.getChildByIndex(1), simpleScope);
						} else {
							System.out.println(numOfIndexes);
						}
						arguments.add(endIndexExprNode);
						arguments.add(startIndexExprNode);

						OperatorNode extentNode = nodeFactory.newOperatorNode(
								varSource, operator, arguments);

						operator = Operator.PLUS;
						arguments.clear();
						arguments.add(extentNode);
						arguments.add(intOneConstNode);
						extentNode = nodeFactory.newOperatorNode(varSource,
								operator, arguments);
						type = nodeFactory.newArrayTypeNode(varSource, type,
								extentNode, startIndexExprNode.copy());
					}
				}
				//
				structFieldDecls.add(nodeFactory
						.newFieldDeclarationNode(varSource, fieldIdNode, type));
				localMap.put(fieldIdNode.name(), type);
			}
		} catch (SyntaxException e) {
			e.printStackTrace();
		}
		SequenceNode<FieldDeclarationNode> structDeclList = nodeFactory
				.newSequenceNode(structSource, "FieldDeclarations",
						structFieldDecls);
		StructureOrUnionTypeNode structTypeNode = nodeFactory
				.newStructOrUnionTypeNode(structSource, true, structTypeIdNode,
						structDeclList);
		return nodeFactory.newFieldDeclarationNode(structSource,
				structIdNode.copy(), structTypeNode);
	}

	private BlockItemNode translatePrintStatement(FortranTree blockItemNode,
			SimpleScope scope) throws SyntaxException {
		Source source = generateSource(blockItemNode);
		FortranTree printStatementTree = blockItemNode;
		FortranTree outputListTree = printStatementTree.getChildByIndex(3);
		IdentifierNode printfIdNode = nodeFactory.newIdentifierNode(source,
				"printf");
		ExpressionNode functionNode = nodeFactory
				.newIdentifierExpressionNode(source, printfIdNode);
		ExpressionNode formatNode = null;
		int numOfOutputItem = outputListTree.numChildren();
		List<ExpressionNode> argumentList = new LinkedList<ExpressionNode>();
		FunctionCallNode callNode = null;
		// TODO: All variables use "%s" (CIVL doesn't analysis the format.)
		String formatStr = "\"";
		CivlcToken cToken = null;
		StringToken strToken = null;

		hasSTDIO = true;
		for (int i = 0; i < numOfOutputItem; i++) {
			FortranTree outputItemTree = outputListTree.getChildByIndex(i)
					.getChildByIndex(0);
			ExpressionNode outputExprNode = translateExpression(source,
					outputItemTree, scope);

			argumentList.add(outputExprNode);
			formatStr += "%s";
		}
		formatStr += "\\n\"";
		cToken = tokenFactory.newCivlcToken(0, formatStr,
				blockItemNode.getChildByIndex(1).cTokens()[0].getFormation());
		strToken = tokenFactory.newStringToken(cToken);
		formatNode = nodeFactory.newStringLiteralNode(source, formatStr,
				strToken.getStringLiteral());
		argumentList.add(0, formatNode);
		callNode = nodeFactory.newFunctionCallNode(source, functionNode,
				argumentList, null);
		return nodeFactory.newExpressionStatementNode(callNode);
	}

	private List<BlockItemNode> translateParameterStatement(
			FortranTree parameterStmtNode, SimpleScope scope)
			throws SyntaxException {
		ArrayList<BlockItemNode> paramterItems = new ArrayList<BlockItemNode>();
		// FortranTree labelDefinition = parameterStmtNode.getChildByIndex(0);
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
			paramterItems.add(
					nodeFactory.newExpressionStatementNode(expressionNode));
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

		return nodeFactory.newLabeledStatementNode(source, labelDecl,
				statement);
	}

	private CompoundStatementNode translateBody(
			FortranTree specificationPartNode, FortranTree executionPartNode,
			SimpleScope scope, Map<String, VariableDeclarationNode> argsMap)
			throws SyntaxException {
		CompoundStatementNode result;
		SimpleScope newScope = new SimpleScope(scope);
		Source source = null;
		List<BlockItemNode> items = new LinkedList<BlockItemNode>();

		if (specificationPartNode != null) {
			int numOfSpecification = specificationPartNode.numChildren();

			tempItems = items;
			source = generateSource(specificationPartNode);
			for (int i = 0; i < numOfSpecification; i++) {
				FortranTree specificationNode = specificationPartNode
						.getChildByIndex(i);
				int rule = specificationNode.rule();
				switch (rule) {
					case 207 : /* DeclarationConstruct */
						FortranTree declarationNode = specificationNode
								.getChildByIndex(0);
						List<BlockItemNode> blockItemNodes = this
								.translateBlockItem(declarationNode, newScope,
										argsMap);

						items.addAll(blockItemNodes);
						break;
					default :
						assert false;
				}
			}
		}

		OmpExecutableNode ompStatementNode = null;

		if (executionPartNode != null) {
			int numOfExecution = executionPartNode.numChildren();

			if (specificationPartNode != null) {
				source = generateSource(specificationPartNode,
						executionPartNode);
			} else {
				source = generateSource(executionPartNode);
			}
			for (int i = 0; i < numOfExecution; i++) {
				FortranTree execConstNode = executionPartNode
						.getChildByIndex(i);
				int rule = execConstNode.rule();
				StatementNode tempASTNode = null;

				switch (rule) {
					case 213 : /* ExecutableConstruct */
						FortranTree stmtTypeNode = execConstNode
								.getChildByIndex(0);

						rule = stmtTypeNode.rule();
						switch (rule) {
							case 214 : /* Assignment */
								FortranTree stmtNode = stmtTypeNode
										.getChildByIndex(0);
								List<BlockItemNode> blockItemNodes = this
										.translateBlockItem(stmtNode, newScope,
												null);
								if (execConstNode.getChildByIndex(0)
										.getChildByIndex(0).rule() == 848) {
									i++;
								}
								if (blockItemNodes.size() == 1 && blockItemNodes
										.get(0)
										.blockItemKind() == BlockItemKind.STATEMENT)
									tempASTNode = (StatementNode) blockItemNodes
											.get(0);
								else
									items.addAll(blockItemNodes);
								break;
							case 802 : /* If Construct */
								tempASTNode = translateStatement(stmtTypeNode,
										scope);
								break;
							case 825 : /* Do Construct */
								tempASTNode = translateStatement(
										stmtTypeNode.getChildByIndex(0), scope);
								break;
							case -501 : /* Fortran Pragma Stmt */
								tempASTNode = translateStatement(stmtTypeNode,
										scope);
								break;
							default :
								System.out.println(
										"ASTBuilderWorker: Unkown Rule: " + rule
												+ "for 'translateBody()'");
								assert false;
						}
						break;
					default :
						System.out.println("ASTBuilderWorker: Unkown Rule: "
								+ rule + "for 'translateBody()'");
						assert false;
				}
				if (tempASTNode != null) {
					if (ompStatementNode != null) {
						ompStatementNode.setStatementNode(tempASTNode);
						ompStatementNode = null;
					} else {
						items.add(tempASTNode);
						if (tempASTNode.statementKind() == StatementKind.OMP) {
							ompStatementNode = (OmpExecutableNode) tempASTNode;
							if (ompStatementNode.isComplete())
								ompStatementNode = null;
						}
					}
				}
			}
		}

		int numItems = items.size();
		boolean changed = false;

		for (int i = 0; i < numItems; i++) {
			ASTNode child = items.get(i);

			if (child != null && child instanceof OmpExecutableNode) {
				OmpExecutableNode ompStmt = (OmpExecutableNode) child;

				if (!ompStmt.isComplete()) {
					changed = true;
					if (ompStmt instanceof OmpForNode) {
						OmpForNode ompForNode = (OmpForNode) ompStmt;
						int collapse = ompForNode.collapse();

						if (collapse == 1) {
							StatementNode forStatement = (StatementNode) items
									.get(i + 1);

							items.set(i + 1, null);
							ompForNode.setStatementNode(forStatement);
						} else {
							List<BlockItemNode> forList = new ArrayList<>(
									collapse);
							CompoundStatementNode compoundStatement;

							source = items.get(i + 1).getSource();

							for (int k = 1; k <= collapse; k++) {
								StatementNode forStatement = (StatementNode) items
										.get(i + k);

								items.set(i + k, null);
								forList.add(forStatement);
							}
							compoundStatement = nodeFactory
									.newCompoundStatementNode(source, forList);
							ompForNode.setStatementNode(compoundStatement);
						}
						items.set(i, ompForNode);
					} else {
						StatementNode statementNode = (StatementNode) items
								.get(i + 1);

						items.set(i + 1, null);
						ompStmt.setStatementNode(statementNode);
						items.set(i, ompStmt);
					}
				}
			}
		}
		if (changed) {
			List<BlockItemNode> newItems = new LinkedList<>();
			for (int i = 0; i < numItems; i++) {
				BlockItemNode item = items.get(i);
				if (item != null)
					newItems.add(item);
			}
			items = newItems;
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
		FortranTree argsNode = null;
		FortranTree specificationPartNode = programUnitNode.getChildByIndex(1);
		FortranTree executionPartNode = numChildren > 3
				? programUnitNode.getChildByIndex(2)
				: null;
		/*
		 * FortranTree endProgramStatementNode = programUnitNode
		 * .getChildByIndex(numChildren - 1);
		 */
		Map<String, VariableDeclarationNode> argsMap = new HashMap<String, VariableDeclarationNode>();
		Source source;
		IdentifierNode name;
		FunctionTypeNode type;
		CompoundStatementNode body;
		BlockItemNode result = null;

		source = generateSource(programUnitNode);
		name = this.translateIdentifier(identifierNode);
		programUnitTrackStack.push(name);
		if (unitType == 0)
			name = nodeFactory.newIdentifierNode(source, "main");
		if (programStatementNode.numChildren() > 3) {
			argsNode = programStatementNode.getChildByIndex(3);
		}
		type = this.translateFunctionType(null, argsNode, true, argsMap);
		body = translateBody(specificationPartNode, executionPartNode, newScope,
				argsMap);
		result = nodeFactory.newFunctionDefinitionNode(source, name, type, null,
				body);
		localMap.clear();
		return result;
	}

	private BlockItemNode translateSubroutine(FortranTree subroutineTree,
			SimpleScope scope, int unitType) throws SyntaxException {
		int numChildren = subroutineTree.numChildren();
		SimpleScope newScope = new SimpleScope(scope, true);
		FortranTree subroutineStatementTree = subroutineTree.getChildByIndex(0);
		boolean hasArgs = subroutineStatementTree.numChildren() > 3;
		FortranTree idTree = subroutineStatementTree.getChildByIndex(2);
		FortranTree argsNode = hasArgs
				? subroutineStatementTree.getChildByIndex(3)
				: null;
		FortranTree specificationPartNode = subroutineTree.getChildByIndex(1);
		FortranTree executionPartNode = numChildren > 3
				? subroutineTree.getChildByIndex(2)
				: null;
		/*
		 * FortranTree endSubroutineStatementNode = subroutineTree
		 * .getChildByIndex(numChildren - 1);
		 */
		Source source;
		IdentifierNode name;
		FunctionTypeNode functionType;
		CompoundStatementNode body;
		BlockItemNode result = null;

		argsMap = new HashMap<String, VariableDeclarationNode>();
		source = generateSource(subroutineTree);
		name = this.translateIdentifier(idTree);
		programUnitTrackStack.push(name);
		functionType = this.translateFunctionType(null, argsNode, false,
				argsMap);
		body = translateBody(specificationPartNode, executionPartNode, newScope,
				argsMap);
		result = nodeFactory.newFunctionDefinitionNode(source, name,
				functionType, null, body);
		localMap.clear();
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
			case 1101 : /* MainProgramUnit */
				items.add((BlockItemNode) translateMainProgramUnit(
						programUnitNode, scope, 0));
				break;
			case 1231 : /* Subroutine */
				items.add((BlockItemNode) translateSubroutine(programUnitNode,
						scope, 1));
				break;
			case 1223 : /* Function */
				items.add((BlockItemNode) translateMainProgramUnit(
						programUnitNode, scope, 2));
				break;
			default :
				assert false;
		}
		programUnitTrackStack.pop();
		return items;
	}

	/* Public Function */

	public SequenceNode<BlockItemNode> generateRoot() throws SyntaxException {
		int numOfProgramUnit = parseTree.numChildren();
		this.rootScope = new SimpleScope(null);
		Source source;

		assert numOfProgramUnit >= 0;
		for (int i = 0; i < numOfProgramUnit; i++) {
			programUnits.addAll(this.translateProgramUnit(
					parseTree.getChildByIndex(i), rootScope));
		}
		source = generateSource(parseTree);
		return nodeFactory.newTranslationUnitNode(source, programUnits);
	}

	public AST generateAST() {
		AST ast = null;
		Set<SourceFile> sourceFiles = new LinkedHashSet<>();

		sourceFiles.add(new SourceFile(new File(filePath), 0));
		try {
			rootNode = this.generateRoot();
			rootNode.insertChildren(0, commonBlocks);
			ast = addLib(rootNode, sourceFiles);
		} catch (SyntaxException | PreprocessorException | ParseException e) {
			e.printStackTrace();
		}
		// TODO:
		// ast.print(System.out);
		// ast.prettyPrint(System.out, true);
		return ast;
	}

	private AST addLib(SequenceNode<BlockItemNode> oldRootNode,
			Set<SourceFile> sourceFiles)
			throws PreprocessorException, ParseException, SyntaxException {
		if (hasSTDIO) {
			AST stdioAST = libFactory.getASTofLibrary(LibraryASTFactory.STDIO);
			SequenceNode<BlockItemNode> stdioNodes = stdioAST.getRootNode();
			List<BlockItemNode> nodes = new ArrayList<BlockItemNode>();

			stdioAST.release();
			for (BlockItemNode node : stdioNodes) {
				node.remove();
				nodes.add(node);
			}
			oldRootNode.insertChildren(0, nodes);
			sourceFiles.addAll(stdioAST.getSourceFiles());
		}
		if (hasMATH) {
			AST mathAST = libFactory.getASTofLibrary(LibraryASTFactory.MATH);
			SequenceNode<BlockItemNode> mathNodes = mathAST.getRootNode();
			List<BlockItemNode> nodes = new ArrayList<BlockItemNode>();

			mathAST.release();
			for (BlockItemNode node : mathNodes) {
				node.remove();
				nodes.add(node);
			}
			oldRootNode.insertChildren(0, nodes);
			sourceFiles.addAll(mathAST.getSourceFiles());
		}
		return astFactory.newAST(oldRootNode, sourceFiles, false);
	}

	// ******** Util Func ********

	private SyntaxException error(String message, FortranTree tree) {
		return new SyntaxException(message, generateSource(tree));
	}
}
