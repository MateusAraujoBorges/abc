package edu.udel.cis.vsl.abc.analysis.entity;

import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.AssignsOrReadsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode.ContractKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsEventNode.DependsEventKind;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.DependsNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.EnsuresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.GuardNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ReadOrWriteEventNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.RequiresNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.UnsourcedException;

public class AcslContractAnalyzer {
	/**
	 * The entity analyzer controlling this declaration analyzer.
	 */
	private EntityAnalyzer entityAnalyzer;

	void processContractNode(ContractNode contract, Function result)
			throws SyntaxException {
		ContractKind contractKind = contract.contractKind();

		switch (contractKind) {
		case ASSIGNS_READS: {
			AssignsOrReadsNode assignsOrReads = (AssignsOrReadsNode) contract;
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
			if (assignsOrReads.isAssigns())
				result.addAssigns(assignsOrReads);
			else
				result.addReads(assignsOrReads);
			break;
		}
		case ASSUMES: {
			break;
		}
		case BEHAVIOR: {
			break;
		}
		case REQUIRES: {
			ExpressionNode expression = ((RequiresNode) contract)
					.getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			result.addPrecondition(expression);
			break;
		}
		case ENSURES: {
			ExpressionNode expression = ((EnsuresNode) contract)
					.getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			result.addPostcondition(expression);
			break;
		}
		case DEPENDS: {
			DependsNode depends = (DependsNode) contract;
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
			ExpressionNode expression = ((GuardNode) contract).getExpression();

			entityAnalyzer.expressionAnalyzer.processExpression(expression);
			result.addGuard(expression);
			break;
		}
		default:
			throw error("Unknown kind of contract clause", contract);
		}

	}

	private void processDependsEvent(DependsEventNode event)
			throws SyntaxException {
		// READ_WRITE, CALL, OPERATOR
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
		case CALL: {
			//CallEvent

		}
		case OPERATOR:
		default:
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
}
