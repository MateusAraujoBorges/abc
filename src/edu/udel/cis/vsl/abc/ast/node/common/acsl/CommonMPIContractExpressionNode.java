package edu.udel.cis.vsl.abc.ast.node.common.acsl;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIContractExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.common.expression.CommonExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonMPIContractExpressionNode extends CommonExpressionNode
		implements MPIContractExpressionNode {

	private MPIContractExpressionKind kind;

	private ExpressionNode[] arguments;

	private int numArgs = -1;

	private String exprName;

	public CommonMPIContractExpressionNode(Source source,
			List<ExpressionNode> arguments, MPIContractExpressionKind kind,
			String exprName) {
		super(source, arguments);
		this.kind = kind;
		this.exprName = exprName;
		this.arguments = new ExpressionNode[arguments.size()];
		arguments.toArray(this.arguments);
	}

	@Override
	public ExpressionNode copy() {
		List<ExpressionNode> argCopy = new LinkedList<>();
		int numArgs = arguments.length;

		for (int i = 0; i < numArgs; i++)
			argCopy.add(duplicate(arguments[i]));
		return new CommonMPIContractExpressionNode(this.getSource(), argCopy,
				kind, exprName);
	}

	@Override
	public MPIContractExpressionKind MPIContractExpressionKind() {
		return kind;
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.MPI_CONTRACT_EXPRESSION;
	}

	@Override
	public boolean isConstantExpression() {
		return false;
	}

	@Override
	public boolean isSideEffectFree(boolean errorsAreSideEffects) {
		return true;
	}

	@Override
	public int numArguments() {
		if (numArgs > 0)
			return numArgs;
		else {
			switch (kind) {
			case MPI_AGREE:
				numArgs = 1;
				break;
			case MPI_EMPTY_IN:
				numArgs = 1;
				break;
			case MPI_EMPTY_OUT:
				numArgs = 1;
				break;
			case MPI_EQUALS:
				numArgs = 4;
				break;
			case MPI_EXTENT:
				numArgs = 1;
				break;
			case MPI_VALID:
				numArgs = 3;
				break;
			case MPI_OFFSET:
				numArgs = 3;
				break;
			case MPI_REGION:
				numArgs = 3;
				break;
			default:
				numArgs = -1;
			}
		}
		return numArgs;
	}

	@Override
	public ExpressionNode getArgument(int index) {
		return arguments[index];
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print(kind);
	}
}
