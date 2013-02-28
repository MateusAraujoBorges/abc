package edu.udel.cis.vsl.abc.ast.value.IF;

import edu.udel.cis.vsl.abc.ast.entity.IF.Field;
import edu.udel.cis.vsl.abc.ast.type.IF.StructureOrUnionType;

public interface UnionValue extends CompoundValue {

	/**
	 * Returns the field of the union to which this value belongs, since a union
	 * can only hold the value of one of its fieldsl
	 * 
	 * @return
	 */
	Field getField();
	
	Value getMemberValue();

	@Override
	StructureOrUnionType getType();

}
