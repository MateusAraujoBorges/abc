package edu.udel.cis.vsl.abc.analysis.entity;

import java.util.HashMap;
import java.util.Map;

import edu.udel.cis.vsl.abc.ast.conversion.IF.ConversionFactory;
import edu.udel.cis.vsl.abc.ast.entity.IF.BehaviorEntity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AllocationNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AssignsOrReadsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AssumesNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.BehaviorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CallEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CompletenessNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CompositeEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode.ContractKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsEventNode.DependsEventNodeKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.EnsuresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.GuardsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.InvariantNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPICollectiveBlockNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIContractExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIContractExpressionNode.MPIContractExpressionKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MemoryEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.RequiresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.WaitsforNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode.ExpressionKind;
import edu.udel.cis.vsl.abc.ast.type.IF.Type;
import edu.udel.cis.vsl.abc.ast.type.IF.Type.TypeKind;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.UnsourcedException;

public class AcslContractAnalyzerWorker {
	/**
	 * The entity analyzer controlling this declaration analyzer.
	 */
	private EntityAnalyzer entityAnalyzer;

	private Map<String, BehaviorEntity> definedBehaviors = new HashMap<>();

	private ConversionFactory conversionFactory;

	private ExpressionAnalyzer expressionAnalyzer;

	AcslContractAnalyzerWorker(EntityAnalyzer entityAnalyzer,
			ConversionFactory conversionFactory) {
		this.entityAnalyzer = entityAnalyzer;
		this.conversionFactory = conversionFactory;
		this.expressionAnalyzer = this.entityAnalyzer.expressionAnalyzer;
	}

	/**
	 * Do entity analysis on a whole contract block of a function.
	 * 
	 * @param contract
	 * @param result
	 * @throws SyntaxException
	 */
	void processContractNodes(SequenceNode<ContractNode> contract,
			Function result) throws SyntaxException {
		for (ContractNode contractClause : contract) {
			processContractNode(contractClause);
			result.addContract(contractClause);
		}
	}

	private boolean isLvalueOfMemorySet(ExpressionNode expr) {
		if (!expressionAnalyzer.isLvalue(expr)) {
			ExpressionKind kind = expr.expressionKind();

			if (kind != ExpressionKind.MPI_CONTRACT_EXPRESSION)
				return kind == ExpressionKind.OBJECT_OR_REGION_OF
						|| kind == ExpressionKind.NOTHING;
			else
				return ((MPIContractExpressionNode) expr)
						.MPIContractExpressionKind() == MPIContractExpressionKind.MPI_REGION;
		}
		return true;
	}

	// TODO: MPIContractExpression type checking!
	/**
	 * Recursively process entity analysis for a {@link ContractNode}.
	 * 
	 * @param contractClause
	 * @return
	 * @throws SyntaxException
	 */
	private void processContractNode(ContractNode contractClause)
			throws SyntaxException {
		ContractKind contractKind = contractClause.contractKind();

		switch (contractKind) {
			case ALLOCATES_OR_FREES : {
				AllocationNode allocation = (AllocationNode) contractClause;
				SequenceNode<ExpressionNode> memoryList = allocation
						.memoryList();

				for (ExpressionNode memory : memoryList) {
					expressionAnalyzer.processExpression(memory);
					if (!expressionAnalyzer.typeFactory
							.isPointerType(memory.getConvertedType()))
						throw this.error(
								"The expression "
										+ memory.prettyRepresentation()
										+ " doesn't have pointer type and thus "
										+ "can't be used as the operand of allocates/frees",
								allocation);
				}
				break;
			}
			case ASSIGNS_READS : {
				AssignsOrReadsNode assignsOrReads = (AssignsOrReadsNode) contractClause;
				// ExpressionNode condition = assignsOrReads.getCondition();
				SequenceNode<ExpressionNode> expressionList = assignsOrReads
						.getMemoryList();
				int numExpressions = expressionList.numChildren();

				// if (condition != null)
				// entityAnalyzer.expressionAnalyzer
				// .processExpression(condition);
				for (int i = 0; i < numExpressions; i++) {
					ExpressionNode expression = expressionList
							.getSequenceChild(i);

					expressionAnalyzer.processExpression(expression);
					if (!this.isLvalueOfMemorySet(expression)) {
						throw error(
								"The expression "
										+ expression.prettyRepresentation()
										+ " doesn't designate an object or a set of memory locations and thus "
										+ "can't be used as the left argument of assigns/reads",
								assignsOrReads);
					}
					// if (!this.isCompatibleWithMemoryType(expression
					// .getConvertedType()))
					// throw this
					// .error("the operand of assigns/reads doesn't have valid
					// memory type",
					// expression);
					expression.addConversion(this.conversionFactory
							.memoryConversion(expression.getConvertedType()));
				}
				break;
			}
			case DEPENDS : {
				DependsNode depends = (DependsNode) contractClause;
				// ExpressionNode condition = depends.getCondition();
				SequenceNode<DependsEventNode> eventList = depends
						.getEventList();

				for (DependsEventNode event : eventList) {
					processDependsEvent(event);
				}
				break;
				// int numEvents = eventList.numChildren();
				//
				// if (condition != null)
				// entityAnalyzer.expressionAnalyzer
				// .processExpression(condition);
				// for (int i = 0; i < numEvents; i++) {
				// ExpressionNode event = eventList.getSequenceChild(i);
				//
				// entityAnalyzer.expressionAnalyzer
				// .processExpression(event);
				// }
			}
			case GUARDS : {
				ExpressionNode expression = ((GuardsNode) contractClause)
						.getExpression();

				expressionAnalyzer.processExpression(expression);
				break;
			}
			/* *********************************************************** */
			/* ** Contracts stored in functions without categorization: ** */
			case ASSUMES : {
				AssumesNode assumesNode = (AssumesNode) contractClause;
				ExpressionNode expression = assumesNode.getPredicate();

				expressionAnalyzer.processExpression(expression);
				break;
			}
			case BEHAVIOR : {
				BehaviorNode behavior = (BehaviorNode) contractClause;
				String name = behavior.getName().name();
				SequenceNode<ContractNode> body = behavior.getBody();

				if (definedBehaviors.containsKey(name))
					throw this.error(
							"re-definition of behavior named as " + name
									+ ": the previous definition was at "
									+ definedBehaviors.get(name).getBehavior()
											.getSource()
											.getSummary(false, true),
							contractClause);
				else
					this.definedBehaviors.put(name, entityAnalyzer.entityFactory
							.newBehavior(name, behavior));
				for (ContractNode subClause : body) {
					processContractNode(subClause);
				}
				break;
			}
			case COMPLETENESS : {
				CompletenessNode completeNode = (CompletenessNode) contractClause;
				SequenceNode<IdentifierNode> idList = completeNode.getIDList();

				if (idList != null) {
					for (IdentifierNode id : idList) {
						BehaviorEntity behavior = this.definedBehaviors
								.get(id.name());

						if (behavior == null)
							throw this.error("undefined behavior " + id.name(),
									id);
						id.setEntity(behavior);
					}
				}
				break;
			}
			case REQUIRES : {
				ExpressionNode expression = ((RequiresNode) contractClause)
						.getExpression();

				expressionAnalyzer.processExpression(expression);
				break;
			}
			case ENSURES : {
				ExpressionNode expression = ((EnsuresNode) contractClause)
						.getExpression();

				expressionAnalyzer.processExpression(expression);
				break;
			}
			case PURE : {
				break;
			}
			case MPI_COLLECTIVE : {
				MPICollectiveBlockNode collective_block = (MPICollectiveBlockNode) contractClause;

				expressionAnalyzer
						.processExpression(collective_block.getMPIComm());
				for (ContractNode colClause : collective_block.getBody())
					processContractNode(colClause);
				break;
			}
			case WAITSFOR : {
				WaitsforNode waitsforNode = (WaitsforNode) contractClause;

				for (ExpressionNode arg : waitsforNode.getArguments())
					expressionAnalyzer.processExpression(arg);
				break;
			}
			default :
				throw error("Unknown kind of contract clause", contractClause);
		}
	}

	private boolean isCompatibleWithMemoryType(Type type) {
		if (type.kind() == TypeKind.MEMORY)
			return true;
		return expressionAnalyzer.typeFactory.isPointerType(type);
	}

	private void processDependsEvent(DependsEventNode event)
			throws SyntaxException {
		DependsEventNodeKind kind = event.getEventKind();

		switch (kind) {
			case MEMORY : {
				MemoryEventNode rwEvent = (MemoryEventNode) event;
				SequenceNode<ExpressionNode> memoryList = rwEvent
						.getMemoryList();

				for (ExpressionNode memory : memoryList) {
					this.expressionAnalyzer.processExpression(memory);
					if (!isCompatibleWithMemoryType(memory.getConvertedType()))
						throw this.error(
								"the operand of \\write/\\read/\\access doesn't have valid memory type",
								memory);
					// memory.addConversion(this.conversionFactory.memoryConversion(memory.getConvertedType()));
				}
				break;
			}
			case ANYACT :
			case NOACT :
				break;
			case CALL : {
				CallEventNode call = (CallEventNode) event;
				SequenceNode<ExpressionNode> arguments = call.arguments();

				this.expressionAnalyzer.processIdentifierExpression(
						call.getFunction(), true, true);

				if (arguments != null) {
					for (ExpressionNode arg : arguments) {
						this.expressionAnalyzer.processExpression(arg);
					}
				}
				break;
			}
			case COMPOSITE : {
				CompositeEventNode composite = (CompositeEventNode) event;

				this.processDependsEvent(composite.getLeft());
				this.processDependsEvent(composite.getRight());
				break;
			}
			default :
				throw error("Unknown kind of depends event", event);
		}

	}

	private SyntaxException error(String message, ASTNode node) {
		return entityAnalyzer.error(message, node);
	}

	@SuppressWarnings("unused")
	private SyntaxException error(UnsourcedException e, ASTNode node) {
		return entityAnalyzer.error(e, node);
	}

	void processLoopContractNodes(SequenceNode<ContractNode> loopContracts)
			throws SyntaxException {
		for (ContractNode clause : loopContracts) {
			switch (clause.contractKind()) {
				case INVARIANT :
					InvariantNode loopInvari = ((InvariantNode) clause);

					expressionAnalyzer
							.processExpression(loopInvari.getExpression());
					break;
				case ASSIGNS_READS :
					AssignsOrReadsNode assignsNode = (AssignsOrReadsNode) clause;

					if (assignsNode.isReads())
						throw error(
								"Unexpected loop contract clause: " + clause,
								clause);
					for (ExpressionNode mem : assignsNode.getMemoryList())
						expressionAnalyzer.processExpression(mem);
					break;
				default :
					throw error("Unknown kind of loop contracts: "
							+ clause.contractKind(), clause);
					// Check expression types
			}
		}
	}
}
