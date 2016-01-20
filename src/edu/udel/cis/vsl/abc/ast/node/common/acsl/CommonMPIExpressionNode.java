package edu.udel.cis.vsl.abc.ast.node.common.acsl;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.abc.ast.node.IF.acsl.MPIExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.IF.expression.ExpressionNode;
import edu.udel.cis.vsl.abc.ast.node.common.expression.CommonExpressionNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonMPIExpressionNode extends CommonExpressionNode implements
		MPIExpressionNode {

	private MPIExpressionKind kind;

	private ExpressionNode[] arguments;

	private int numArgs = -1;

	private String exprName;

	public CommonMPIExpressionNode(Source source,
			List<ExpressionNode> arguments, MPIExpressionKind kind,
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

		argCopy.addAll(Arrays.asList(arguments));
		return new CommonMPIExpressionNode(this.getSource(), argCopy, kind,
				exprName);
	}

	@Override
	public ExpressionKind expressionKind() {
		return ExpressionKind.FUNCTION_CALL;
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
			case MPI_EMPTY_IN:
				numArgs = 1;
				break;
			case MPI_EMPTY_OUT:
				numArgs = 1;
				break;
			case MPI_SIZE:
				numArgs = 2;
				break;
			case MPI_REGION:
				numArgs = 3;
				break;
			case MPI_EQUALS:
				numArgs = 4;
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
		out.println(this.prettyRepresentation());
	}

	@Override
	public String toString() {
		return this.prettyRepresentation().toString();
	}

	@Override
	public StringBuffer prettyRepresentation() {
		StringBuffer pretty = new StringBuffer();
		int numArgs = this.numArguments();

		pretty.append(exprName + "(");
		pretty.append(arguments[0].prettyRepresentation());
		for (int i = 1; i < numArgs; i++)
			pretty.append(" , " + arguments[i].prettyRepresentation());
		pretty.append(")");
		return pretty;
	}
}
