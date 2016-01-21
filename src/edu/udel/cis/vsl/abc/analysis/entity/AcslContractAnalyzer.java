package edu.udel.cis.vsl.abc.analysis.entity;

import edu.udel.cis.vsl.abc.ast.entity.IF.Function;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.ast.node.IF.acsl.ContractNode;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class AcslContractAnalyzer {
	/**
	 * The entity analyzer controlling this declaration analyzer.
	 */
	private EntityAnalyzer entityAnalyzer;

	AcslContractAnalyzer(EntityAnalyzer entityAnalyzer) {
		this.entityAnalyzer = entityAnalyzer;
	}

	void processContractNodes(SequenceNode<ContractNode> contract,
			Function result) throws SyntaxException {
		AcslContractAnalyzerWorker worker = new AcslContractAnalyzerWorker(
				this.entityAnalyzer);

		worker.processContractNodes(contract, result);
	}
}
