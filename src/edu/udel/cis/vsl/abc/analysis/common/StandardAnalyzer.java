package edu.udel.cis.vsl.abc.analysis.common;

import edu.udel.cis.vsl.abc.analysis.IF.Analyzer;
import edu.udel.cis.vsl.abc.analysis.entity.EntityAnalyzer;
import edu.udel.cis.vsl.abc.ast.conversion.IF.ConversionFactory;
import edu.udel.cis.vsl.abc.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.unit.IF.TranslationUnit;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;

/**
 * Performs all standard analyses of a Translation Unit: determines and sets
 * Scopes, Types, and Entities.
 * 
 * @author siegel
 * 
 */
public class StandardAnalyzer implements Analyzer {

	private ScopeAnalyzer scopeAnalyzer;

	private EntityAnalyzer entityAnalyzer;

	public StandardAnalyzer(EntityFactory entityFactory,
			NodeFactory nodeFactory, TokenFactory tokenFactory,
			ConversionFactory conversionFactory) {
		scopeAnalyzer = new ScopeAnalyzer(entityFactory);
		entityAnalyzer = new EntityAnalyzer(entityFactory, nodeFactory,
				tokenFactory, conversionFactory);
	}

	@Override
	public void analyze(TranslationUnit unit) throws SyntaxException {
		scopeAnalyzer.analyze(unit);
		entityAnalyzer.analyze(unit);
	}

}
