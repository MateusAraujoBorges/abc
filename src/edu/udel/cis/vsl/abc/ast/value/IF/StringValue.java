package edu.udel.cis.vsl.abc.ast.value.IF;

import edu.udel.cis.vsl.abc.ast.type.IF.ArrayType;
import edu.udel.cis.vsl.abc.front.IF.token.StringLiteral;

public interface StringValue extends Value {

	StringLiteral getLiteral();

	@Override
	ArrayType getType();

}
