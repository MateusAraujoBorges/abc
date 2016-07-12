package edu.udel.cis.vsl.abc.token.IF;

import org.antlr.runtime.Token;

/**
 * A FunctionMacro represents a C preprocessor function-like macro, which has
 * the from <code>#define f(X1,X2,...) ...</code>. The name of the macro is
 * <code>f</code>, the <code>X1</code>,<code>X2</code>, etc., are the formal
 * parameters, and the <code>...</code> is a sequence of replacement tokens. The
 * replacement tokens may include the formal parameters.
 * 
 * @author siegel
 * 
 */
public interface FunctionMacro extends Macro {

	class FunctionReplacementUnit extends ReplacementUnit {

		public FunctionReplacementUnit(int index, Token token,
				Token[] whitespace) {
			super(index, token, whitespace);
		}

		/**
		 * If the replacement token is an occurrence of a formal parameter, this
		 * is the formal index; otherwise -1
		 */
		public int formalIndex;
	}

	/**
	 * Returns the number of formal parameters
	 * 
	 * @return the number of formal parameters
	 */
	int getNumFormals();

	/**
	 * Gets the index-th formal parameter
	 * 
	 * @param index
	 *            an integer in the range [0,numFormals-1]
	 * @return the index-th formal parameter token
	 */
	Token getFormal(int index);

	@Override
	FunctionReplacementUnit getReplacementUnit(int index);
}
