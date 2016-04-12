package edu.udel.cis.vsl.abc.analysis.entity;

import java.util.Collection;
import java.util.Iterator;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.entity.IF.Label;
import edu.udel.cis.vsl.abc.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.abc.ast.entity.IF.ProgramEntity.LinkageKind;
import edu.udel.cis.vsl.abc.ast.entity.IF.Scope;
import edu.udel.cis.vsl.abc.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.abc.ast.entity.IF.Typedef;
import edu.udel.cis.vsl.abc.ast.entity.IF.Variable;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.compound.CompoundInitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.DeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.FunctionDefinitionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.InitializerNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.OrdinaryDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.ScopeParameterizedDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.TypedefDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.declaration.VariableDeclarationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.CompoundStatementNode;
import edu.udel.cis.vsl.abc.ast.node.IF.statement.GotoNode;
import edu.udel.cis.vsl.abc.ast.node.IF.type.TypeNode;
import edu.udel.cis.vsl.abc.ast.node.common.compound.CommonCompoundInitializerNode;
import edu.udel.cis.vsl.abc.ast.type.IF.DomainType;
import edu.udel.cis.vsl.abc.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.abc.ast.value.IF.Value;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.UnsourcedException;

/**
 * A tool to analyze declarations in an AST.
 * 
 * @author siegel
 * 
 */
public class DeclarationAnalyzer {

	// ***************************** Fields *******************************

	/**
	 * The entity analyzer controlling this declaration analyzer.
	 */
	private EntityAnalyzer entityAnalyzer;

	private AcslContractAnalyzer acslAnalyzer;

	/**
	 * Is the compilation mode CIVL-C?
	 */
	// private boolean civl;

	/**
	 * Typedefs which name types in this set will be ignored in file scope.
	 */
	private Collection<String> ignoredTypes = null;

	private Language language;

	// ************************** Constructors ****************************

	/**
	 * Creates new declaration analyzer with the given controlling entity
	 * analyzer.
	 * 
	 * @param entityAnalyzer
	 *            the entity analyzer in charge
	 */
	DeclarationAnalyzer(EntityAnalyzer entityAnalyzer) {
		this.entityAnalyzer = entityAnalyzer;
		this.language = entityAnalyzer.language;
		this.acslAnalyzer = new AcslContractAnalyzer(entityAnalyzer,
				entityAnalyzer.conversionFactory);
	}

	// ************************* Exported Methods *************************

	/**
	 * Sets the ignoredTypes to the given collection. Elements are not copied.
	 * 
	 * @param ignoredTypes
	 *            names of types for which typedefs will be ignored
	 */
	void setIgnoredTypes(Collection<String> ignoredTypes) {
		this.ignoredTypes = ignoredTypes;
	}

	/**
	 * Processes a typedef declaration.
	 * 
	 * @param node
	 *            a typedef declaration node that has not yet been processes
	 * @throws SyntaxException
	 */
	void processTypedefDeclaration(TypedefDeclarationNode node)
			throws SyntaxException {
		IdentifierNode identifier = node.getIdentifier();
		String name = identifier.name();
		Scope scope = node.getScope();
		TypeNode typeNode = node.getTypeNode();

		if (// scope.getScopeKind() == ScopeKind.FILE &&
		ignoredTypes != null && ignoredTypes.contains(name)) {
			OrdinaryEntity entity = scope.getLexicalOrdinaryEntity(false, name); // scope.getOrdinaryEntity(name);

			if (entity == null)
				throw error("Cannot find definition of system typedef", node);
			if (entity instanceof Typedef) {
				entityAnalyzer.typeAnalyzer.processTypeNode(typeNode);
				identifier.setEntity(entity);
				node.setEntity(entity);
				entity.addDeclaration(node);
			} else
				throw error("Expected system typedef, got " + entity, node);
		} else {
			Type type = entityAnalyzer.typeAnalyzer.processTypeNode(typeNode);
			OrdinaryEntity entity = scope.getOrdinaryEntity(false, name);
			Typedef typedef;

			if (entity != null) {
				Type oldType;

				if (!(entity instanceof Typedef))
					throw entityAnalyzer.error("Typedef name already used at "
							+ entity.getDefinition().getSource(), node);
				typedef = (Typedef) entity;
				oldType = typedef.getType();
				if (!type.equals(oldType))
					throw entityAnalyzer.error(
							"Redefiniton of typedef name with different type.  "
									+ "Original definition was at "
									+ typedef.getDefinition().getSource(),
							node);
			} else {
				typedef = entityAnalyzer.entityFactory.newTypedef(name, type);
				try {
					scope.add(typedef);
				} catch (UnsourcedException e) {
					throw entityAnalyzer.error(e, node);
				}
				typedef.setDefinition((TypedefDeclarationNode) node);
			}
			typedef.addDeclaration(node);
			node.setEntity(typedef);
			identifier.setEntity(typedef);
		}
	}

	Variable processVariableDeclaration(VariableDeclarationNode node)
			throws SyntaxException {
		return processVariableDeclaration(node, false);
	}

	/**
	 * Processes a function declaration. The declaration's type node is
	 * processed. If the {@link Function} entity does not already exist, it is
	 * created and added to the current scope. The declaration is added to the
	 * entity's list of declarations.
	 * 
	 * @param node
	 * @return
	 * @throws SyntaxException
	 */
	Function processFunctionDeclaration(FunctionDeclarationNode node)
			throws SyntaxException {
		Function result = (Function) processOrdinaryDeclaration(node, false);
		SequenceNode<ContractNode> contract = node.getContract();

		addDeclarationToFunction(result, node);
		if (node instanceof FunctionDefinitionNode) {
			CompoundStatementNode body = ((FunctionDefinitionNode) node)
					.getBody();

			node.setIsDefinition(true);
			entityAnalyzer.statementAnalyzer.processCompoundStatement(body);
			processGotos(body);
		}
		if (contract != null) {
			this.acslAnalyzer.processContractNodes(contract, result);
		}
		return result;
	}

	/**
	 * Processes a variable declaration node, creating the {@link Variable}
	 * entity if this is the definition, adding it to the appropriate scope,
	 * processing the type node, etc.
	 * 
	 * @param node
	 *            a variable declaration node
	 * @param isParameter
	 *            is this variable a formal parameter in a function declaration
	 *            or definition
	 * @return the Variable the Variable represented by this declaration (either
	 *         the existing one or a new one)
	 * @throws SyntaxException
	 */
	Variable processVariableDeclaration(VariableDeclarationNode node,
			boolean isParameter) throws SyntaxException {
		Variable result = (Variable) processOrdinaryDeclaration(node,
				isParameter); // result has type and linkage
		InitializerNode initializer = node.getInitializer();

		if (result != null) {
			ObjectType type;

			// problem: if the entity is new, then you do not need to form
			// composite
			// of declaration type and entity type

			addDeclarationToVariable(result, node);
			type = result.getType();
			if (initializer != null) {
				processInitializer(initializer, type);
				// if this is a compound initializer, the type
				// of the initializer refines the type of the variable
				if (initializer instanceof CompoundInitializerNode)
					result.setType(entityAnalyzer.typeFactory.compositeType(
							type,
							((CompoundInitializerNode) initializer).getType()));
			}
		}
		return result;
	}

	public void processInitializer(InitializerNode initializer,
			ObjectType currentType) throws SyntaxException {
		assert currentType != null;
		if (initializer instanceof ExpressionNode) {
			ExpressionNode rhs = (ExpressionNode) initializer;

			entityAnalyzer.expressionAnalyzer
					.processExpression((ExpressionNode) initializer);
			try {
				entityAnalyzer.expressionAnalyzer.processAssignment(currentType,
						rhs);
			} catch (UnsourcedException e) {
				throw error(e, initializer);
			}
		} else if (initializer instanceof CompoundInitializerNode) {
			if (currentType.kind() == TypeKind.DOMAIN)
				entityAnalyzer.expressionAnalyzer
						.processCartesianDomainInitializer(
								(CompoundInitializerNode) initializer,
								(DomainType) currentType);
			else
				entityAnalyzer.compoundLiteralAnalyzer
						.processCompoundInitializer(
								(CommonCompoundInitializerNode) initializer,
								currentType);
		}
	}

	public void processScopeParameterizedDeclaration(
			ScopeParameterizedDeclarationNode decl) throws SyntaxException {
		DeclarationNode baseDecl = decl.baseDeclaration();
		SequenceNode<VariableDeclarationNode> scopeList = decl.parameters();
		int numVars = scopeList.numChildren();

		for (int i = 0; i < numVars; i++) {
			VariableDeclarationNode varDecl = scopeList.getSequenceChild(i);

			processVariableDeclaration(varDecl, true);
		}
		if (baseDecl instanceof TypedefDeclarationNode)
			processTypedefDeclaration((TypedefDeclarationNode) baseDecl);
		else if (baseDecl instanceof FunctionDeclarationNode)
			processFunctionDeclaration((FunctionDeclarationNode) baseDecl);
		else
			throw error("Unexpected scoped declaration", decl);
		// Note: the declaration node for f will be the function declaration
		// node, not the scope parameterized declaration node.
	}

	// ************************* Private Methods **************************

	private SyntaxException error(String message, ASTNode node) {
		return entityAnalyzer.error(message, node);
	}

	private SyntaxException error(UnsourcedException e, ASTNode node) {
		return entityAnalyzer.error(e, node);
	}

	/**
	 * See C11 6.2.2 for the rules on determining linkage.
	 * 
	 * Note: "The declaration of an identifier for a function that has block
	 * scope shall have no explicit storage-class specifier other than extern."
	 * (C11 6.7.1(7))
	 * 
	 * @param node
	 * @return
	 * @throws SyntaxException
	 */
	private LinkageKind computeLinkage(OrdinaryDeclarationNode node)
			throws SyntaxException {
		boolean isFunction = node instanceof FunctionDeclarationNode;
		IdentifierNode identifier = node.getIdentifier();
		Scope scope = node.getScope();
		boolean isFileScope = scope.getScopeKind() == ScopeKind.FILE;
		String name;
		boolean hasNoStorageClass;

		if (identifier == null)
			return LinkageKind.NONE;
		name = identifier.name();
		if (isFileScope && node.hasStaticStorage()) {
			return LinkageKind.INTERNAL;
		}
		hasNoStorageClass = hasNoStorageClass(node);
		if (node.hasExternStorage() || (isFunction && hasNoStorageClass
				&& (isFileScope || !civl()))) {
			OrdinaryEntity previous = scope.getLexicalOrdinaryEntity(false,
					name);

			if (previous == null) {
				return LinkageKind.EXTERNAL;
			} else {
				LinkageKind previousLinkage = previous.getLinkage();

				if (previousLinkage == LinkageKind.INTERNAL
						|| previousLinkage == LinkageKind.EXTERNAL)
					return previousLinkage;
				else
					return LinkageKind.EXTERNAL;
			}
		}
		// if you are in C mode and this is a function, throw an
		// exception.
		if (isFunction && !civl())
			throw error(
					"C11 6.7.1(7) states: \"The declaration of an "
							+ " identifier for a function that has block scope shall "
							+ "have no explicit storage-class specifier other than extern.\"",
					node);
		if (isFileScope) {
			if (!isFunction && hasNoStorageClass)
				return LinkageKind.EXTERNAL;
		}
		return LinkageKind.NONE;
	}

	/**
	 * <p>
	 * Processes an ordinary declaration, i.e., one which declares a variable or
	 * function (and not a structure/union member, enumerator, or typedef), and
	 * returns the corresponding {@link Entity}. Does all of the following:
	 * </p>
	 * 
	 * <ul>
	 * <li>processes the declaration node's type node</li>
	 * <li>if the declaration node's identifier is <code>null</code>, returns
	 * <code>null</code></li>
	 * <li>if the declared {@link Entity} has not yet been encountered, an
	 * {@link Entity} object is created and added to the appropriate scope. The
	 * new entity will have type and linkage as specified by the declaration.
	 * </li>
	 * <li>if on the other hand the entity already exists, the declaration's
	 * linkage is checked for consistency with that of the existing entity, the
	 * entity's type is updated to be the composite type of its current type and
	 * the type of the declaration</li>
	 * <li>in either case, the declaration node and its identifier node will
	 * have the entity field set, and this method returns the entity (old or
	 * new).</li>
	 * <li>if this declares a function called "main", the AST to which the
	 * declaration node belong has its "main function" field set to the function
	 * entity.</li>
	 * </ul>
	 * 
	 * <p>
	 * Note: This method does not do everything needed to process an ordinary
	 * declaration. It just does the stuff that is common to both an object and
	 * function declaration.
	 * </p>
	 * 
	 * <p>
	 * Note that an entity can belong to multiple scopes! It is added to every
	 * scope in which it is declared. An {@link Entity} with no linkage can
	 * belong to only one scope. An {@link Entity} with internal or external
	 * linkage can belong to multiple scopes.
	 * </p>
	 * 
	 * 
	 * @param node
	 *            the declaration node
	 * @param isParameter
	 *            is the declaration the declaration of a function parameter?
	 * @throws SyntaxException
	 *             if the type or linkage specified by the declaration node is
	 *             not compatible with that of earlier declarations
	 */
	private OrdinaryEntity processOrdinaryDeclaration(
			OrdinaryDeclarationNode node, boolean isParameter)
					throws SyntaxException {
		AST ast = node.getOwner();
		IdentifierNode identifier = node.getIdentifier();
		TypeNode typeNode = node.getTypeNode();
		Type type = entityAnalyzer.typeAnalyzer.processTypeNode(typeNode,
				isParameter);
		Scope scope;
		boolean isFunction;
		LinkageKind linkage;
		String name;
		OrdinaryEntity entity;
		boolean isNew = false;

		if (identifier == null)
			return null;
		// the scope to which this entity belongs is the scope in which
		// the Identifier in the declaration occurs:
		scope = identifier.getScope();
		isFunction = node instanceof FunctionDeclarationNode;
		linkage = computeLinkage(node);
		name = identifier.name();
		entity = scope.getOrdinaryEntity(false, name);
		// CIVL, but not C, allows multiple function declarations in block
		// scope with no linkage and same identifier, all signifying
		// the same function
		if (linkage == LinkageKind.NONE) {
			if (entity != null) {
				if (!(civl() && isFunction
						&& scope.getScopeKind() == ScopeKind.BLOCK))
					throw error(
							"Redeclaration of identifier with no linkage. "
									+ "Original declaration was at "
									+ entity.getDeclaration(0).getSource(),
							identifier);
			} else {
				entity = isFunction
						? entityAnalyzer.entityFactory.newFunction(name,
								linkage, type)
						: entityAnalyzer.entityFactory.newVariable(name,
								linkage, type);
				isNew = true;
				try {
					scope.add(entity);
				} catch (UnsourcedException e) {
					throw error(e, identifier);
				}
			}
		} else { // declaration node's linkage is EXTERNAL or INTERNAL
			if (entity != null) { // entity found in current scope
				if (entity.getLinkage() != linkage)
					throw error(
							"Disagreement on internal/external linkage between two declarations",
							node);
			} else { // entity not in current scope, look elsewhere...
				entity = ast.getInternalOrExternalEntity(name); // find the
																// entity
				if (entity != null) { // entity found in different scope
					if (entity.getLinkage() != linkage)
						throw error(
								"Disagreement on internal/external linkage between two declarations",
								node);
				} else { // entity does not yet exist
					entity = isFunction
							? entityAnalyzer.entityFactory.newFunction(name,
									linkage, type)
							: entityAnalyzer.entityFactory.newVariable(name,
									linkage, type);
					isNew = true;
					ast.add(entity);
				}
				try {
					scope.add(entity);
				} catch (UnsourcedException e) {
					throw error(e, identifier);
				}
			}
		}
		node.setEntity(entity);
		identifier.setEntity(entity);
		if (isFunction && name.equals("main")) {
			if (scope.getParentScope() == null) {
				ast.setMain((Function) entity);
			}
		}
		if (!isNew)
			addTypeToVariableOrFunction(typeNode, entity);
		return entity;
	}

	private void addTypeToVariableOrFunction(TypeNode typeNode,
			OrdinaryEntity entity) throws SyntaxException {
		if (typeNode != null) {
			Type type = typeNode.getType();
			Type oldType = entity.getType();

			if (type == null)
				throw error("Internal error: type not processed", typeNode);
			if (oldType == null)
				entity.setType(type);
			else {
				if (!oldType.compatibleWith(type))
					throw error(
							"Redeclaration of entity with incompatible type: "
									+ entity.getName() + "\nOriginal type: "
									+ oldType + "\nNew type: " + type,
							typeNode);
				entity.setType(entityAnalyzer.typeFactory.compositeType(oldType,
						type));
			}
		}
	}

	/**
	 * <p>
	 * Adds a declaration of a variable to that variable.
	 * </p>
	 * 
	 * <p>
	 * Preconditions: the {@link TypeNode} of <code>declaration</code> has
	 * already been processed and its {@link Type} set. If the declaration has a
	 * non-<code>null</code> {@link InitializerNode}, the initializer node has
	 * been processed. The linkage of <code>variable</code> has been set
	 * appropriately.
	 * </p>
	 * 
	 * <p>
	 * Each variable maintains a list of all of its declarations. This method
	 * will do the following: (1) if the initializer is present, checks that
	 * there is no previous initializer for <code>variable</code>, and then make
	 * this declaration the definition. (2) if the initializer is not present
	 * but the linkage of <code>variable</code> is {@link LinkageKind#NONE}, the
	 * declaration is also the definition and the definition of the variable is
	 * set to the declaration as well. (3) the alignment specifiers of the
	 * declaration are processed and added to the variable's lists.
	 * </p>
	 * 
	 * @param variable
	 *            a non-<code>null</code> {@link Variable}
	 * @param declaration
	 *            a declaration of <code>variable</code>
	 * @throws SyntaxException
	 *             if the type of <code>declaration</code> is incompatible with
	 *             that of <code>variable</code>
	 */
	private void addDeclarationToVariable(Variable variable,
			VariableDeclarationNode declaration) throws SyntaxException {
		InitializerNode initializer = declaration.getInitializer();
		SequenceNode<TypeNode> typeAlignmentSpecifiers = declaration
				.typeAlignmentSpecifiers();
		SequenceNode<ExpressionNode> constantAlignmentSpecifiers = declaration
				.constantAlignmentSpecifiers();

		if (initializer != null) {
			InitializerNode oldInitializer = variable.getInitializer();

			if (oldInitializer != null)
				throw error("Re-initialization of variable "
						+ variable.getName() + ". First was at "
						+ oldInitializer.getSource() + ".", initializer);
			variable.setInitializer(initializer);
			variable.setDefinition(declaration);
			declaration.setIsDefinition(true);
		} else if (variable.getLinkage() == LinkageKind.NONE) {
			variable.setDefinition(declaration);
			declaration.setIsDefinition(true);
		}
		if (typeAlignmentSpecifiers != null) {
			for (TypeNode child : typeAlignmentSpecifiers)
				variable.addTypeAlignment(
						entityAnalyzer.typeAnalyzer.processTypeNode(child));
		}
		if (constantAlignmentSpecifiers != null) {
			for (ExpressionNode expression : constantAlignmentSpecifiers) {
				Value constant = entityAnalyzer.valueOf(expression);

				if (constant == null)
					throw error("Value for enumerator must be constant",
							expression);
				variable.addConstantAlignment(constant);
			}
		}
		// TODO: set storage duration. See C11 Sec. 6.2.4.
		variable.addDeclaration(declaration);
	}

	private void addDeclarationToFunction(Function function,
			FunctionDeclarationNode declaration) throws SyntaxException {
		OrdinaryDeclarationNode previousDeclaration;
		Iterator<DeclarationNode> declarationIter = function.getDeclarations()
				.iterator();

		if (declarationIter.hasNext())
			previousDeclaration = (OrdinaryDeclarationNode) declarationIter
					.next();
		else
			previousDeclaration = null;
		if (previousDeclaration == null) {
			if (declaration.hasInlineFunctionSpecifier())
				function.setIsInlined(true);
			if (declaration.hasNoreturnFunctionSpecifier())
				function.setDoesNotReturn(true);
			if (declaration.hasAtomicFunctionSpecifier())
				function.setAtomic(true);
			if (declaration.hasSystemFunctionSpecifier())
				function.setSystemFunction(true);
		} else {
			if (declaration.hasNoreturnFunctionSpecifier() != function
					.doesNotReturn())
				throw error(
						"Disagreement on Noreturn function specifier at function declaration.\n"
								+ "  Previous declaration was at "
								+ previousDeclaration.getSource(),
						declaration);
		}
		if (declaration instanceof FunctionDefinitionNode) {
			FunctionDefinitionNode previousDefinition = function
					.getDefinition();

			if (previousDefinition != null)
				throw error(
						"Redefinition of function.  Previous definition was at "
								+ previousDefinition.getSource(),
						declaration);
			function.setDefinition(declaration);
		}
		function.addDeclaration(declaration);
	}

	private boolean hasNoStorageClass(OrdinaryDeclarationNode node) {
		if (node.hasExternStorage() || node.hasStaticStorage())
			return false;
		if (node instanceof VariableDeclarationNode)
			return !(((VariableDeclarationNode) node).hasRegisterStorage()
					|| ((VariableDeclarationNode) node).hasAutoStorage());
		return true;
	}

	private void processGotos(ASTNode node) throws SyntaxException {
		Iterable<ASTNode> childIter = node.children();

		if (node instanceof GotoNode) {
			IdentifierNode identifier = ((GotoNode) node).getLabel();
			String name = identifier.name();
			Scope scope = node.getScope();
			Label label = scope.getLexicalLabel(name);

			if (label == null)
				throw error("Goto statement refers to non-existent label",
						identifier);
			identifier.setEntity(label);
		}
		for (ASTNode child : childIter) {
			if (child != null)
				processGotos(child);
		}
	}

	private boolean civl() {
		return language == Language.CIVL_C;
	}

}
