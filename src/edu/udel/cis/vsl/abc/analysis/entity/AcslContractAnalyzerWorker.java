package edu.udel.cis.vsl.abc.analysis.entity;

import java.util.HashMap;
import java.util.Map;

import edu.udel.cis.vsl.abc.ast.entity.IF.BehaviorEntity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.IdentifierNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AssignsOrReadsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AssumesNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.BehaviorNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CallEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CompletenessNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.CompositeEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode.ContractKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsEventNode.DependsEventKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.EnsuresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.GuardNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPICollectiveBlockNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ReadOrWriteEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.RequiresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.err.IF.ABCRuntimeException;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.UnsourcedException;

public class AcslContractAnalyzerWorker {
	/**
	 * ContractType distinguishes the type of contracts. Currently there are two
	 * types of contracts: sequential (regular) and MPI.
	 * 
	 * @author ziqingluo
	 *
	 */
	private enum ContractType {
		SEQ, MPI, REDUCE
	};

	/**
	 * The entity analyzer controlling this declaration analyzer.
	 */
	private EntityAnalyzer entityAnalyzer;

	private Map<String, BehaviorEntity> definedBehaviors = new HashMap<>();

	AcslContractAnalyzerWorker(EntityAnalyzer entityAnalyzer) {
		this.entityAnalyzer = entityAnalyzer;
	}

	/**
	 * Do entity analysis on contract nodes. ContractNodes will be attached to
	 * {@link Function} with following categorization rules:
	 * <ol>
	 * <li>DEPENDES, GUARDS and READS are reduction contracts.</li>
	 * <li>All MPI contracts are in MPI_Collective blocks.</li>
	 * <li>For all contracts that are not in MPI_Collective blocks or not belong
	 * to reduction contracts are sequential contracts.</li>
	 * <li>Reduction contracts cannot appear in behavior blocks or
	 * MPI_Collective blocks (TODO: maybe currently, because the semantics is
	 * not clear)</li>
	 * </ol>
	 * 
	 * @param contract
	 * @param result
	 * @throws SyntaxException
	 */
	void processContractNodes(SequenceNode<ContractNode> contract,
			Function result) throws SyntaxException {
		ContractType contractType;

		for (ContractNode contractClause : contract) {
			contractType = processContractNode(contractClause);
			addContractToFunction(result, contractClause, contractType);
		}
	}

	// TODO: MPIContractExpression type checking!
	/**
	 * Recursively process entity analysis for a {@link ContractNode}, returns
	 * the {@link ContractType}. The process conforms the rule defined at
	 * {@link #processContractNodes(SequenceNode, Function)}.
	 * 
	 * @param contractClause
	 * @return
	 * @throws SyntaxException
	 */
	private ContractType processContractNode(ContractNode contractClause)
			throws SyntaxException {
		ContractKind contractKind = contractClause.contractKind();

		switch (contractKind) {
		case ASSIGNS_READS: {
			AssignsOrReadsNode assignsOrReads = (AssignsOrReadsNode) contractClause;
			// ExpressionNode condition = assignsOrReads.getCondition();
			SequenceNode<ExpressionNode> expressionList = assignsOrReads
					.getMemoryList();
			int numExpressions = expressionList.numChildren();

			// if (condition != null)
			// entityAnalyzer.expressionAnalyzer
			// .processExpression(condition);
			for (int i = 0; i < numExpressions; i++) {
				ExpressionNode expression = expressionList.getSequenceChild(i);

				entityAnalyzer.expressionAnalyzer.processExpression(expression);
			}
			// If the contract is not a sub-clause and is not an "assigns", put
			// it as "reads" then return; Else, it will be put into the function
			// later.
			if (assignsOrReads.isReads())
				return ContractType.REDUCE;
			else
				return ContractType.SEQ;
		}
		case DEPENDS: {
			DependsNode depends = (DependsNode) contractClause;
			// ExpressionNode condition = depends.getCondition();
			SequenceNode<DependsEventNode> eventList = depends.getEventList();

			for (DependsEventNode event : eventList) {
				processDependsEvent(event);
			}
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
			return ContractType.REDUCE;
		}
		case GUARDS: {
			ExpressionNode expression = ((GuardNode) contractClause)
					.getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			return ContractType.REDUCE;
		}
		/* *********************************************************** */
		/* ** Contracts stored in functions without categorization: ** */
		case ASSUMES: {
			AssumesNode assumesNode = (AssumesNode) contractClause;
			ExpressionNode expression = assumesNode.getPredicate();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			return ContractType.SEQ;
		}
		case BEHAVIOR: {
			BehaviorNode behavior = (BehaviorNode) contractClause;
			String name = behavior.getName().name();
			SequenceNode<ContractNode> body = behavior.getBody();

			if (definedBehaviors.containsKey(name))
				throw this.error("re-definition of behavior named as "
						+ name
						+ ": the previous definition was at "
						+ definedBehaviors.get(name).getBehavior().getSource()
								.getSummary(false), contractClause);
			else
				this.definedBehaviors.put(name, entityAnalyzer.entityFactory
						.newBehavior(name, behavior));
			for (ContractNode subClause : body) {
				processContractNode(subClause);
			}

			return ContractType.SEQ;
		}
		case COMPLETENESS: {
			CompletenessNode completeNode = (CompletenessNode) contractClause;
			SequenceNode<IdentifierNode> idList = completeNode.getIDList();

			if (idList != null) {
				for (IdentifierNode id : idList) {
					BehaviorEntity behavior = this.definedBehaviors.get(id
							.name());

					if (behavior == null)
						throw this.error("undefined behavior " + id.name(), id);
					id.setEntity(behavior);
				}
			}
			return ContractType.SEQ;
		}
		case REQUIRES: {
			ExpressionNode expression = ((RequiresNode) contractClause)
					.getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			return ContractType.SEQ;
		}
		case ENSURES: {
			ExpressionNode expression = ((EnsuresNode) contractClause)
					.getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			return ContractType.SEQ;
		}
		case MPI_COLLECTIVE: {
			MPICollectiveBlockNode collective_block = (MPICollectiveBlockNode) contractClause;

			entityAnalyzer.expressionAnalyzer
					.processExpression(collective_block.getMPIComm());
			for (ContractNode colClause : collective_block.getBody()) {
				ContractType nonRecType = processContractNode(colClause);

				// check if there is any reduction contracts:
				if (nonRecType == ContractType.REDUCE)
					throw error(
							"Reduction contract: "
									+ colClause.contractKind()
									+ " shall not appear in MPI_Collective blocks",
							colClause);
			}
			return ContractType.MPI;
		}
		default:
			throw error("Unknown kind of contract clause", contractClause);
		}
	}

	private void processDependsEvent(DependsEventNode event)
			throws SyntaxException {
		DependsEventKind kind = event.getEventKind();

		switch (kind) {
		case READ_WRITE: {
			ReadOrWriteEventNode rwEvent = (ReadOrWriteEventNode) event;
			SequenceNode<ExpressionNode> memoryList = rwEvent.getMemoryList();

			for (ExpressionNode memory : memoryList) {
				this.entityAnalyzer.expressionAnalyzer
						.processExpression(memory);
			}
			break;
		}
		case ANYACT:
		case NOACT:
			break;
		case CALL: {
			CallEventNode call = (CallEventNode) event;
			SequenceNode<ExpressionNode> arguments = call.arguments();

			this.entityAnalyzer.expressionAnalyzer.processIdentifierExpression(
					call.getFunction(), true, true);
			if (arguments != null) {
				for (ExpressionNode arg : arguments) {
					this.entityAnalyzer.expressionAnalyzer
							.processExpression(arg);
				}
			}
			break;
		}
		case COMPOSITE: {
			CompositeEventNode composite = (CompositeEventNode) event;

			this.processDependsEvent(composite.getLeft());
			this.processDependsEvent(composite.getRight());
			break;
		}
		default:
			throw error("Unknown kind of depends event", event);
		}

	}

	private void addContractToFunction(Function function,
			ContractNode contract, ContractType type) {
		switch (type) {
		case SEQ:
			function.addSeqContract(contract);
			break;
		case MPI:
			function.addMPIContract(contract);
			break;
		case REDUCE:
			function.addReductionContract(contract);
			break;
		default:
			throw new ABCRuntimeException("Unreachable location");
		}
	}

	private SyntaxException error(String message, ASTNode node) {
		return entityAnalyzer.error(message, node);
	}

	@SuppressWarnings("unused")
	private SyntaxException error(UnsourcedException e, ASTNode node) {
		return entityAnalyzer.error(e, node);
	}
}
