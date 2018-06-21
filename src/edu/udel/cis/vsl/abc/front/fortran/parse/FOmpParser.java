package edu.udel.cis.vsl.abc.front.fortran.parse;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;

import edu.udel.cis.vsl.abc.front.common.parse.OmpPragmaParser;
import edu.udel.cis.vsl.abc.front.fortran.ptree.FortranTree;
import edu.udel.cis.vsl.abc.token.IF.Source;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

public class FOmpParser implements OmpPragmaParser {
	public static final int AMPERSAND = OmpParserF08.T_AMPERSAND;
	public static final int ATOMIC = OmpParserF08.T_OMPATOMIC;
	public static final int BARRIER = OmpParserF08.T_BARRIER;
	public static final int BITOR = OmpParserF08.T_BITOR;
	public static final int BITXOR = OmpParserF08.T_BITXOR;
	public static final int CAPTURE = OmpParserF08.T_CAPTURE;
	public static final int COLLAPSE = OmpParserF08.T_COLLAPSE;
	public static final int COPYIN = OmpParserF08.T_COPYIN;
	public static final int COPYPRIVATE = OmpParserF08.T_COPYPRIVATE;
	public static final int CRITICAL = OmpParserF08.T_CRITICAL;
	public static final int DATA_CLAUSE = OmpParserF08.T_DATA_CLAUSE;
	public static final int DEFAULT = OmpParserF08.T_DEFAULT;
	public static final int DYNAMIC = OmpParserF08.T_DYNAMIC;
	public static final int EQ = OmpParserF08.T_EQ;
	public static final int EQV = OmpParserF08.T_EQV;
	public static final int FLUSH = OmpParserF08.T_FLUSH;
	public static final int FOR = OmpParserF08.T_DO;
	public static final int FST_PRIVATE = OmpParserF08.T_FST_PRIVATE;
	public static final int GUIDED = OmpParserF08.T_GUIDED;
	public static final int IDENTIFIER = OmpParserF08.T_IDENT;
	public static final int IF = OmpParserF08.T_IF;
	public static final int LAND = OmpParserF08.T_AND;
	public static final int LOR = OmpParserF08.T_OR;
	public static final int LST_PRIVATE = OmpParserF08.T_LST_PRIVATE;
	public static final int MASTER = OmpParserF08.T_MASTER;
	public static final int NONE = OmpParserF08.T_NONE;
	public static final int NE = OmpParserF08.T_NE;
	public static final int NEQV = OmpParserF08.T_NEQV;
	public static final int NOWAIT = OmpParserF08.T_NOWAIT;
	public static final int NUM_THREADS = OmpParserF08.T_NUM_THREADS;
	public static final int ORDERED = OmpParserF08.T_ORDERED;
	public static final int PARALLEL = OmpParserF08.T_PARALLEL;
	public static final int PARALLEL_FOR = OmpParserF08.T_PARALLEL_FOR;
	public static final int PARALLEL_SECTIONS = OmpParserF08.T_PARALLEL_SECTIONS;
	public static final int PLUS = OmpParserF08.T_PLUS;
	public static final int PRIVATE = OmpParserF08.T_PRIVATE;
	public static final int READ = OmpParserF08.T_READ;
	public static final int REDUCTION = OmpParserF08.T_REDUCTION;
	public static final int RUNTIME = OmpParserF08.T_RUNTIME;
	public static final int SCHEDULE = OmpParserF08.T_SCHEDULE;
	public static final int SECTION = OmpParserF08.T_SECTION;
	public static final int SECTIONS = OmpParserF08.T_SECTIONS;
	public static final int SEQ_CST = OmpParserF08.T_SEQ_CST;
	public static final int SHARED = OmpParserF08.T_SHARED;
	public static final int SINGLE = OmpParserF08.T_SINGLE;
	public static final int STAR = OmpParserF08.T_ASTERISK;
	public static final int STATIC = OmpParserF08.T_STATIC;
	public static final int SUB = OmpParserF08.T_MINUS;
	public static final int THD_PRIVATE = OmpParserF08.T_THD_PRIVATE;
	public static final int UNIQUE_FOR = OmpParserF08.T_UNIQUE_FOR;
	public static final int UNIQUE_PARALLEL = OmpParserF08.T_UNIQUE_PARALLEL;
	public static final int UPDATE = OmpParserF08.T_UPDATE;
	public static final int WRITE = OmpParserF08.T_WRITE;
	public static final int END = OmpParserF08.T_END;

	@Override
	public CommonTree parse(Source source, TokenStream tokens)
			throws SyntaxException {
		OmpParserF08 parser = new OmpParserF08(tokens);

		try {
			return (CommonTree) parser.openmp_construct().getTree();
		} catch (RecognitionException e) {
			throw new SyntaxException(e.getMessage(), null);
		}
	}

	// TODO: Transformation from CommonTree to FortranTree for
	// parsing the involved expression.
	public FortranTree parseFortran(Source source, TokenStream tokens)
			throws SyntaxException {
		OmpParserF08 parser = new OmpParserF08(tokens);

		try {
			CommonTree tempTree = (CommonTree) parser.openmp_construct()
					.getTree();

			return commonTree2FortranTree(tempTree);
		} catch (RecognitionException e) {
			throw new SyntaxException(e.getMessage(), null);
		}
	}

	private FortranTree commonTree2FortranTree(CommonTree tree) {
		// TODO Auto-generated method stub
		return null;
	}

}
