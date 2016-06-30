package edu.udel.cis.vsl.abc;

import org.junit.Test;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.IF.ASTs;
import edu.udel.cis.vsl.abc.ast.node.IF.NodeFactory;
import edu.udel.cis.vsl.abc.ast.node.IF.Nodes;
import edu.udel.cis.vsl.abc.ast.type.IF.TypeFactory;
import edu.udel.cis.vsl.abc.ast.type.IF.Types;
import edu.udel.cis.vsl.abc.ast.value.IF.ValueFactory;
import edu.udel.cis.vsl.abc.ast.value.IF.Values;
import edu.udel.cis.vsl.abc.config.IF.Configuration;
import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;
import edu.udel.cis.vsl.abc.front.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.front.IF.Front;
import edu.udel.cis.vsl.abc.front.IF.ParseException;
import edu.udel.cis.vsl.abc.front.IF.Parser;
import edu.udel.cis.vsl.abc.front.IF.Preprocessor;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.front.common.astgen.LibraryASTFactory;
import edu.udel.cis.vsl.abc.token.IF.FileIndexer;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.token.IF.TokenFactory;
import edu.udel.cis.vsl.abc.token.IF.Tokens;

public class LibraryASTTest {

	private boolean debug = false;

	@Test
	public void stdio()
			throws PreprocessorException, ParseException, SyntaxException {
		Configuration configuration = Configurations.newMinimalConfiguration();
		TokenFactory tokenFactory = Tokens.newTokenFactory();
		FileIndexer indexer = tokenFactory.newFileIndexer();
		Language language = Language.C;
		Preprocessor preprocessor = Front.newPreprocessor(language,
				configuration, indexer, tokenFactory);
		Parser parser = Front.newParser(language);
		TypeFactory typeFactory = Types.newTypeFactory();
		ValueFactory valueFactory = Values.newValueFactory(configuration,
				typeFactory);
		NodeFactory nodeFactory = Nodes.newNodeFactory(configuration,
				typeFactory, valueFactory);
		ASTFactory astFactory = ASTs.newASTFactory(nodeFactory, tokenFactory,
				typeFactory);
		ASTBuilder astBuilder = Front.newASTBuilder(language, configuration,
				astFactory);
		LibraryASTFactory libFac = new LibraryASTFactory(preprocessor, parser,
				astBuilder);

		AST ast = libFac.getASTofLibrary(LibraryASTFactory.STDLIB);

		if (debug)
			ast.prettyPrint(System.out, false);
	}
}
