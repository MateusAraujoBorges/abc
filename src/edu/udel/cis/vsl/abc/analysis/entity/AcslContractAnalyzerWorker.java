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
		SEQ, MPI
	};

	/**
	 * The entity analyzer controlling this declaration analyzer.
	 */
	private EntityAnalyzer entityAnalyzer;

	private Map<String, BehaviorEntity> definedBehaviors = new HashMap<>();

	AcslContractAnalyzerWorker(EntityAnalyzer entityAnalyzer) {
		this.entityAnalyzer = entityAnalyzer;
	}

	void processContractNodes(SequenceNode<ContractNode> contract,
			Function result) throws SyntaxException {
		for (ContractNode contractClause : contract)
			this.processContractNode(contractClause, result, false,
					ContractType.SEQ);
	}

	// TODO: MPIContractExpression type checking!
	private void processContractNode(ContractNode contractClause,
			Function result, boolean isSubclause, ContractType contractType)
			throws SyntaxException {
		ContractKind contractKind = contractClause.contractKind();

		switch (contractKind) {
		/* ********************************************************** */
		/* *** Contracts stored in functions with categorization: *** */
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
			if (!isSubclause)
				if (assignsOrReads.isReads())
					result.addReads(assignsOrReads);
				else
					this.addContractToFunction(result, assignsOrReads,
							contractType);
			break;
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
			result.addDepends(depends);
			break;
		}
		case GUARDS: {
			ExpressionNode expression = ((GuardNode) contractClause)
					.getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			result.addGuard(expression);
			break;
		}
		/* *********************************************************** */
		/* ** Contracts stored in functions without categorization: ** */
		case ASSUMES: {
			AssumesNode assumesNode = (AssumesNode) contractClause;
			ExpressionNode expression = assumesNode.getPredicate();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			if (!isSubclause)
				addContractToFunction(result, contractClause, contractType);
			break;
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
				this.processContractNode(subClause, result, true, contractType);
			}
			if (!isSubclause)
				addContractToFunction(result, contractClause, contractType);
			break;
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
			if (!isSubclause)
				addContractToFunction(result, contractClause, contractType);
			break;
		}
		case REQUIRES: {
			ExpressionNode expression = ((RequiresNode) contractClause)
					.getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			if (!isSubclause)
				addContractToFunction(result, contractClause, contractType);
			break;
		}
		case ENSURES: {
			ExpressionNode expression = ((EnsuresNode) contractClause)
					.getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			if (!isSubclause)
				addContractToFunction(result, contractClause, contractType);
			break;
		}
		case MPI_COLLECTIVE: {
			MPICollectiveBlockNode collective_block = (MPICollectiveBlockNode) contractClause;

			entityAnalyzer.expressionAnalyzer
					.processExpression(collective_block.getMPIComm());
			for (ContractNode colClause : collective_block.getBody())
				processContractNode(colClause, result, true, ContractType.MPI);
			if (!isSubclause)
				addContractToFunction(result, contractClause, contractType);
			break;
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
