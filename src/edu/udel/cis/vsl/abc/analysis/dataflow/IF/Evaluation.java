package edu.udel.cis.vsl.abc.analysis.dataflow.IF;

import java.util.Map;

import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;

public interface Evaluation<AbstractValue> {
	AbstractValue evaluate(ASTNode expr, Map<Entity, AbstractValue> map);

}
