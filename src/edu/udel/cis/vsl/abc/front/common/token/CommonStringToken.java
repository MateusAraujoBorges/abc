package edu.udel.cis.vsl.abc.front.common.token;

import edu.udel.cis.vsl.abc.front.IF.token.CToken;
import edu.udel.cis.vsl.abc.front.IF.token.Formation;
import edu.udel.cis.vsl.abc.front.IF.token.StringLiteral;
import edu.udel.cis.vsl.abc.front.IF.token.StringToken;

public class CommonStringToken extends CommonCToken implements StringToken {

	/**
	 * Eclipse made me do it.
	 */
	private static final long serialVersionUID = 6839260551000953066L;

	private StringLiteral literal;

	public CommonStringToken(int type, Formation formation,
			StringLiteral literal) {
		super(type, literal.toString(), formation);
		this.literal = literal;
	}

	public CommonStringToken(CToken token, Formation formation,
			StringLiteral data) {
		super(token, formation);
		this.literal = data;
	}

	@Override
	public StringLiteral getStringLiteral() {
		return literal;
	}

}
