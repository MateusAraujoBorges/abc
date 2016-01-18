// $ANTLR 3.5.2 AcslParser.g 2016-01-13 20:15:48

package edu.udel.cis.vsl.abc.front.c.parse;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class AcslParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALLOC", "AMPERSAND", "ANYACT", 
		"ARROW", "ASSIGN", "ASSIGNS", "ASSUMES", "AT", "BAR", "BEHAVIOR", "BEHAVIORS", 
		"BITXOR", "BOOLEAN", "BinaryExponentPart", "CALL", "CHAR", "COLON", "COMMA", 
		"COMP", "COMPLETE", "DECREASES", "DEPENDS", "DISJOINT", "DIVIDE", "DOT", 
		"DOTDOT", "DOUBLE", "DecimalConstant", "DecimalFloatingConstant", "Digit", 
		"ELLIPSIS", "EMPTY", "ENSURES", "EQ", "EQUIV", "EXISTS", "EscapeSequence", 
		"ExponentPart", "FALSE", "FLOAT", "FLOATING_CONSTANT", "FOR", "FORALL", 
		"FREES", "FloatingSuffix", "FractionalConstant", "GT", "GTE", "GUARDS", 
		"HASH", "HexFractionalConstant", "HexPrefix", "HexQuad", "HexadecimalConstant", 
		"HexadecimalDigit", "HexadecimalFloatingConstant", "ID", "IMPLY", "INT", 
		"INTEGER", "INTEGER_CONSTANT", "INTER", "IdentifierNonDigit", "IntegerSuffix", 
		"LAND", "LCOMMENT", "LCURLY", "LET", "LONG", "LOR", "LPAREN", "LSQUARE", 
		"LT", "LTE", "LongLongSuffix", "LongSuffix", "MOD", "NEQ", "NEWLINE", 
		"NOACT", "NOT", "NOTHING", "NewLine", "NonDigit", "NonZeroDigit", "OLD", 
		"OctalConstant", "OctalDigit", "OctalEscape", "PLUS", "PP_NUMBER", "QUESTION", 
		"RCOMMENT", "RCURLY", "READ", "READS", "REAL", "REQUIRES", "RESULT", "RPAREN", 
		"RSQUARE", "SChar", "SEMICOL", "SHIFTLEFT", "SHIFTRIGHT", "SHORT", "SIZEOF", 
		"STAR", "STRING_LITERAL", "SUB", "TERMINATES", "TRUE", "UNION", "UniversalCharacterName", 
		"UnsignedSuffix", "VOID", "WITH", "WRITE", "WS", "XOR", "Zero", "ARGUMENT_LIST", 
		"BEHAVIOR_BODY", "BEHAVIOR_COMPLETE", "BEHAVIOR_DISJOINT", "BINDER", "BINDER_LIST", 
		"CAST", "CHARACTER_CONSTANT", "CLAUSE_BEHAVIOR", "CLAUSE_COMPLETE", "CLAUSE_NORMAL", 
		"CONTRACT", "DIV", "EVENT_BASE", "EVENT_INTS", "EVENT_LIST", "EVENT_PARENTHESIZED", 
		"EVENT_PLUS", "EVENT_SUB", "FUNC_CALL", "ID_LIST", "INDEX", "INTGER", 
		"LSHIFT", "MINUS", "OPERATOR", "RSHIFT", "SET_BINDERS", "SET_SIMPLE", 
		"SIZEOF_EXPR", "SIZEOF_TYPE", "TEMINATES", "TERM_PARENTHESIZED", "TYPE", 
		"TYPE_BUILTIN", "TYPE_ID"
	};
	public static final int EOF=-1;
	public static final int ALLOC=4;
	public static final int AMPERSAND=5;
	public static final int ANYACT=6;
	public static final int ARROW=7;
	public static final int ASSIGN=8;
	public static final int ASSIGNS=9;
	public static final int ASSUMES=10;
	public static final int AT=11;
	public static final int BAR=12;
	public static final int BEHAVIOR=13;
	public static final int BEHAVIORS=14;
	public static final int BITXOR=15;
	public static final int BOOLEAN=16;
	public static final int BinaryExponentPart=17;
	public static final int CALL=18;
	public static final int CHAR=19;
	public static final int COLON=20;
	public static final int COMMA=21;
	public static final int COMP=22;
	public static final int COMPLETE=23;
	public static final int DECREASES=24;
	public static final int DEPENDS=25;
	public static final int DISJOINT=26;
	public static final int DIVIDE=27;
	public static final int DOT=28;
	public static final int DOTDOT=29;
	public static final int DOUBLE=30;
	public static final int DecimalConstant=31;
	public static final int DecimalFloatingConstant=32;
	public static final int Digit=33;
	public static final int ELLIPSIS=34;
	public static final int EMPTY=35;
	public static final int ENSURES=36;
	public static final int EQ=37;
	public static final int EQUIV=38;
	public static final int EXISTS=39;
	public static final int EscapeSequence=40;
	public static final int ExponentPart=41;
	public static final int FALSE=42;
	public static final int FLOAT=43;
	public static final int FLOATING_CONSTANT=44;
	public static final int FOR=45;
	public static final int FORALL=46;
	public static final int FREES=47;
	public static final int FloatingSuffix=48;
	public static final int FractionalConstant=49;
	public static final int GT=50;
	public static final int GTE=51;
	public static final int GUARDS=52;
	public static final int HASH=53;
	public static final int HexFractionalConstant=54;
	public static final int HexPrefix=55;
	public static final int HexQuad=56;
	public static final int HexadecimalConstant=57;
	public static final int HexadecimalDigit=58;
	public static final int HexadecimalFloatingConstant=59;
	public static final int ID=60;
	public static final int IMPLY=61;
	public static final int INT=62;
	public static final int INTEGER=63;
	public static final int INTEGER_CONSTANT=64;
	public static final int INTER=65;
	public static final int IdentifierNonDigit=66;
	public static final int IntegerSuffix=67;
	public static final int LAND=68;
	public static final int LCOMMENT=69;
	public static final int LCURLY=70;
	public static final int LET=71;
	public static final int LONG=72;
	public static final int LOR=73;
	public static final int LPAREN=74;
	public static final int LSQUARE=75;
	public static final int LT=76;
	public static final int LTE=77;
	public static final int LongLongSuffix=78;
	public static final int LongSuffix=79;
	public static final int MOD=80;
	public static final int NEQ=81;
	public static final int NEWLINE=82;
	public static final int NOACT=83;
	public static final int NOT=84;
	public static final int NOTHING=85;
	public static final int NewLine=86;
	public static final int NonDigit=87;
	public static final int NonZeroDigit=88;
	public static final int OLD=89;
	public static final int OctalConstant=90;
	public static final int OctalDigit=91;
	public static final int OctalEscape=92;
	public static final int PLUS=93;
	public static final int PP_NUMBER=94;
	public static final int QUESTION=95;
	public static final int RCOMMENT=96;
	public static final int RCURLY=97;
	public static final int READ=98;
	public static final int READS=99;
	public static final int REAL=100;
	public static final int REQUIRES=101;
	public static final int RESULT=102;
	public static final int RPAREN=103;
	public static final int RSQUARE=104;
	public static final int SChar=105;
	public static final int SEMICOL=106;
	public static final int SHIFTLEFT=107;
	public static final int SHIFTRIGHT=108;
	public static final int SHORT=109;
	public static final int SIZEOF=110;
	public static final int STAR=111;
	public static final int STRING_LITERAL=112;
	public static final int SUB=113;
	public static final int TERMINATES=114;
	public static final int TRUE=115;
	public static final int UNION=116;
	public static final int UniversalCharacterName=117;
	public static final int UnsignedSuffix=118;
	public static final int VOID=119;
	public static final int WITH=120;
	public static final int WRITE=121;
	public static final int WS=122;
	public static final int XOR=123;
	public static final int Zero=124;
	public static final int ARGUMENT_LIST=125;
	public static final int BEHAVIOR_BODY=126;
	public static final int BEHAVIOR_COMPLETE=127;
	public static final int BEHAVIOR_DISJOINT=128;
	public static final int BINDER=129;
	public static final int BINDER_LIST=130;
	public static final int CAST=131;
	public static final int CHARACTER_CONSTANT=132;
	public static final int CLAUSE_BEHAVIOR=133;
	public static final int CLAUSE_COMPLETE=134;
	public static final int CLAUSE_NORMAL=135;
	public static final int CONTRACT=136;
	public static final int DIV=137;
	public static final int EVENT_BASE=138;
	public static final int EVENT_INTS=139;
	public static final int EVENT_LIST=140;
	public static final int EVENT_PARENTHESIZED=141;
	public static final int EVENT_PLUS=142;
	public static final int EVENT_SUB=143;
	public static final int FUNC_CALL=144;
	public static final int ID_LIST=145;
	public static final int INDEX=146;
	public static final int INTGER=147;
	public static final int LSHIFT=148;
	public static final int MINUS=149;
	public static final int OPERATOR=150;
	public static final int RSHIFT=151;
	public static final int SET_BINDERS=152;
	public static final int SET_SIMPLE=153;
	public static final int SIZEOF_EXPR=154;
	public static final int SIZEOF_TYPE=155;
	public static final int TEMINATES=156;
	public static final int TERM_PARENTHESIZED=157;
	public static final int TYPE=158;
	public static final int TYPE_BUILTIN=159;
	public static final int TYPE_ID=160;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public AcslParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public AcslParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return AcslParser.tokenNames; }
	@Override public String getGrammarFileName() { return "AcslParser.g"; }


	public static class function_contract_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function_contract"
	// AcslParser.g:58:1: function_contract : LCOMMENT (f+= function_clause )+ (b+= named_behavior_block )* (c+= completeness_clause_block )* RCOMMENT -> ^( CONTRACT ( $f)+ ( $b)* ( $c)* ) ;
	public final AcslParser.function_contract_return function_contract() throws RecognitionException {
		AcslParser.function_contract_return retval = new AcslParser.function_contract_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LCOMMENT1=null;
		Token RCOMMENT2=null;
		List<Object> list_f=null;
		List<Object> list_b=null;
		List<Object> list_c=null;
		RuleReturnScope f = null;
		RuleReturnScope b = null;
		RuleReturnScope c = null;
		Object LCOMMENT1_tree=null;
		Object RCOMMENT2_tree=null;
		RewriteRuleTokenStream stream_LCOMMENT=new RewriteRuleTokenStream(adaptor,"token LCOMMENT");
		RewriteRuleTokenStream stream_RCOMMENT=new RewriteRuleTokenStream(adaptor,"token RCOMMENT");
		RewriteRuleSubtreeStream stream_function_clause=new RewriteRuleSubtreeStream(adaptor,"rule function_clause");
		RewriteRuleSubtreeStream stream_named_behavior_block=new RewriteRuleSubtreeStream(adaptor,"rule named_behavior_block");
		RewriteRuleSubtreeStream stream_completeness_clause_block=new RewriteRuleSubtreeStream(adaptor,"rule completeness_clause_block");

		try {
			// AcslParser.g:59:5: ( LCOMMENT (f+= function_clause )+ (b+= named_behavior_block )* (c+= completeness_clause_block )* RCOMMENT -> ^( CONTRACT ( $f)+ ( $b)* ( $c)* ) )
			// AcslParser.g:59:7: LCOMMENT (f+= function_clause )+ (b+= named_behavior_block )* (c+= completeness_clause_block )* RCOMMENT
			{
			LCOMMENT1=(Token)match(input,LCOMMENT,FOLLOW_LCOMMENT_in_function_contract267); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_LCOMMENT.add(LCOMMENT1);

			// AcslParser.g:59:16: (f+= function_clause )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==ALLOC||LA1_0==ASSIGNS||LA1_0==DEPENDS||LA1_0==ENSURES||LA1_0==FREES||LA1_0==GUARDS||LA1_0==READS||LA1_0==REQUIRES||LA1_0==TEMINATES) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// AcslParser.g:59:17: f+= function_clause
					{
					pushFollow(FOLLOW_function_clause_in_function_contract272);
					f=function_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_function_clause.add(f.getTree());
					if (list_f==null) list_f=new ArrayList<Object>();
					list_f.add(f.getTree());
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			// AcslParser.g:60:9: (b+= named_behavior_block )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==BEHAVIOR) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// AcslParser.g:60:10: b+= named_behavior_block
					{
					pushFollow(FOLLOW_named_behavior_block_in_function_contract287);
					b=named_behavior_block();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_named_behavior_block.add(b.getTree());
					if (list_b==null) list_b=new ArrayList<Object>();
					list_b.add(b.getTree());
					}
					break;

				default :
					break loop2;
				}
			}

			// AcslParser.g:60:36: (c+= completeness_clause_block )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==COMPLETE||LA3_0==DISJOINT) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// AcslParser.g:60:37: c+= completeness_clause_block
					{
					pushFollow(FOLLOW_completeness_clause_block_in_function_contract294);
					c=completeness_clause_block();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_completeness_clause_block.add(c.getTree());
					if (list_c==null) list_c=new ArrayList<Object>();
					list_c.add(c.getTree());
					}
					break;

				default :
					break loop3;
				}
			}

			RCOMMENT2=(Token)match(input,RCOMMENT,FOLLOW_RCOMMENT_in_function_contract298); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_RCOMMENT.add(RCOMMENT2);

			// AST REWRITE
			// elements: c, f, b
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: b, c, f
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"token b",list_b);
			RewriteRuleSubtreeStream stream_c=new RewriteRuleSubtreeStream(adaptor,"token c",list_c);
			RewriteRuleSubtreeStream stream_f=new RewriteRuleSubtreeStream(adaptor,"token f",list_f);
			root_0 = (Object)adaptor.nil();
			// 61:9: -> ^( CONTRACT ( $f)+ ( $b)* ( $c)* )
			{
				// AcslParser.g:61:12: ^( CONTRACT ( $f)+ ( $b)* ( $c)* )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CONTRACT, "CONTRACT"), root_1);
				if ( !(stream_f.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_f.hasNext() ) {
					adaptor.addChild(root_1, stream_f.nextTree());
				}
				stream_f.reset();

				// AcslParser.g:61:28: ( $b)*
				while ( stream_b.hasNext() ) {
					adaptor.addChild(root_1, stream_b.nextTree());
				}
				stream_b.reset();

				// AcslParser.g:61:32: ( $c)*
				while ( stream_c.hasNext() ) {
					adaptor.addChild(root_1, stream_c.nextTree());
				}
				stream_c.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "function_contract"


	public static class function_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "function_clause"
	// AcslParser.g:64:1: function_clause : ( requires_clause SEMICOL -> ^( CLAUSE_NORMAL requires_clause ) | terminates_clause SEMICOL -> ^( CLAUSE_NORMAL terminates_clause ) | simple_clause SEMICOL -> ^( CLAUSE_NORMAL simple_clause ) );
	public final AcslParser.function_clause_return function_clause() throws RecognitionException {
		AcslParser.function_clause_return retval = new AcslParser.function_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMICOL4=null;
		Token SEMICOL6=null;
		Token SEMICOL8=null;
		ParserRuleReturnScope requires_clause3 =null;
		ParserRuleReturnScope terminates_clause5 =null;
		ParserRuleReturnScope simple_clause7 =null;

		Object SEMICOL4_tree=null;
		Object SEMICOL6_tree=null;
		Object SEMICOL8_tree=null;
		RewriteRuleTokenStream stream_SEMICOL=new RewriteRuleTokenStream(adaptor,"token SEMICOL");
		RewriteRuleSubtreeStream stream_simple_clause=new RewriteRuleSubtreeStream(adaptor,"rule simple_clause");
		RewriteRuleSubtreeStream stream_requires_clause=new RewriteRuleSubtreeStream(adaptor,"rule requires_clause");
		RewriteRuleSubtreeStream stream_terminates_clause=new RewriteRuleSubtreeStream(adaptor,"rule terminates_clause");

		try {
			// AcslParser.g:65:5: ( requires_clause SEMICOL -> ^( CLAUSE_NORMAL requires_clause ) | terminates_clause SEMICOL -> ^( CLAUSE_NORMAL terminates_clause ) | simple_clause SEMICOL -> ^( CLAUSE_NORMAL simple_clause ) )
			int alt4=3;
			switch ( input.LA(1) ) {
			case REQUIRES:
				{
				alt4=1;
				}
				break;
			case TEMINATES:
				{
				alt4=2;
				}
				break;
			case ALLOC:
			case ASSIGNS:
			case DEPENDS:
			case ENSURES:
			case FREES:
			case GUARDS:
			case READS:
				{
				alt4=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// AcslParser.g:65:7: requires_clause SEMICOL
					{
					pushFollow(FOLLOW_requires_clause_in_function_clause341);
					requires_clause3=requires_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_requires_clause.add(requires_clause3.getTree());
					SEMICOL4=(Token)match(input,SEMICOL,FOLLOW_SEMICOL_in_function_clause343); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SEMICOL.add(SEMICOL4);

					// AST REWRITE
					// elements: requires_clause
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 65:30: -> ^( CLAUSE_NORMAL requires_clause )
					{
						// AcslParser.g:65:33: ^( CLAUSE_NORMAL requires_clause )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CLAUSE_NORMAL, "CLAUSE_NORMAL"), root_1);
						adaptor.addChild(root_1, stream_requires_clause.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:66:7: terminates_clause SEMICOL
					{
					pushFollow(FOLLOW_terminates_clause_in_function_clause358);
					terminates_clause5=terminates_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_terminates_clause.add(terminates_clause5.getTree());
					SEMICOL6=(Token)match(input,SEMICOL,FOLLOW_SEMICOL_in_function_clause360); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SEMICOL.add(SEMICOL6);

					// AST REWRITE
					// elements: terminates_clause
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 66:32: -> ^( CLAUSE_NORMAL terminates_clause )
					{
						// AcslParser.g:66:35: ^( CLAUSE_NORMAL terminates_clause )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CLAUSE_NORMAL, "CLAUSE_NORMAL"), root_1);
						adaptor.addChild(root_1, stream_terminates_clause.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// AcslParser.g:67:7: simple_clause SEMICOL
					{
					pushFollow(FOLLOW_simple_clause_in_function_clause375);
					simple_clause7=simple_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_simple_clause.add(simple_clause7.getTree());
					SEMICOL8=(Token)match(input,SEMICOL,FOLLOW_SEMICOL_in_function_clause377); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SEMICOL.add(SEMICOL8);

					// AST REWRITE
					// elements: simple_clause
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 67:29: -> ^( CLAUSE_NORMAL simple_clause )
					{
						// AcslParser.g:67:32: ^( CLAUSE_NORMAL simple_clause )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CLAUSE_NORMAL, "CLAUSE_NORMAL"), root_1);
						adaptor.addChild(root_1, stream_simple_clause.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "function_clause"


	public static class named_behavior_block_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "named_behavior_block"
	// AcslParser.g:70:1: named_behavior_block : named_behavior -> ^( CLAUSE_BEHAVIOR named_behavior ) ;
	public final AcslParser.named_behavior_block_return named_behavior_block() throws RecognitionException {
		AcslParser.named_behavior_block_return retval = new AcslParser.named_behavior_block_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope named_behavior9 =null;

		RewriteRuleSubtreeStream stream_named_behavior=new RewriteRuleSubtreeStream(adaptor,"rule named_behavior");

		try {
			// AcslParser.g:71:5: ( named_behavior -> ^( CLAUSE_BEHAVIOR named_behavior ) )
			// AcslParser.g:71:7: named_behavior
			{
			pushFollow(FOLLOW_named_behavior_in_named_behavior_block402);
			named_behavior9=named_behavior();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_named_behavior.add(named_behavior9.getTree());
			// AST REWRITE
			// elements: named_behavior
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 71:22: -> ^( CLAUSE_BEHAVIOR named_behavior )
			{
				// AcslParser.g:71:25: ^( CLAUSE_BEHAVIOR named_behavior )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CLAUSE_BEHAVIOR, "CLAUSE_BEHAVIOR"), root_1);
				adaptor.addChild(root_1, stream_named_behavior.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "named_behavior_block"


	public static class completeness_clause_block_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "completeness_clause_block"
	// AcslParser.g:74:1: completeness_clause_block : completeness_clause SEMICOL -> ^( CLAUSE_COMPLETE completeness_clause ) ;
	public final AcslParser.completeness_clause_block_return completeness_clause_block() throws RecognitionException {
		AcslParser.completeness_clause_block_return retval = new AcslParser.completeness_clause_block_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMICOL11=null;
		ParserRuleReturnScope completeness_clause10 =null;

		Object SEMICOL11_tree=null;
		RewriteRuleTokenStream stream_SEMICOL=new RewriteRuleTokenStream(adaptor,"token SEMICOL");
		RewriteRuleSubtreeStream stream_completeness_clause=new RewriteRuleSubtreeStream(adaptor,"rule completeness_clause");

		try {
			// AcslParser.g:75:5: ( completeness_clause SEMICOL -> ^( CLAUSE_COMPLETE completeness_clause ) )
			// AcslParser.g:75:7: completeness_clause SEMICOL
			{
			pushFollow(FOLLOW_completeness_clause_in_completeness_clause_block427);
			completeness_clause10=completeness_clause();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_completeness_clause.add(completeness_clause10.getTree());
			SEMICOL11=(Token)match(input,SEMICOL,FOLLOW_SEMICOL_in_completeness_clause_block429); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_SEMICOL.add(SEMICOL11);

			// AST REWRITE
			// elements: completeness_clause
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 75:35: -> ^( CLAUSE_COMPLETE completeness_clause )
			{
				// AcslParser.g:75:38: ^( CLAUSE_COMPLETE completeness_clause )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CLAUSE_COMPLETE, "CLAUSE_COMPLETE"), root_1);
				adaptor.addChild(root_1, stream_completeness_clause.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "completeness_clause_block"


	public static class requires_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "requires_clause"
	// AcslParser.g:78:1: requires_clause : REQUIRES term -> ^( REQUIRES term ) ;
	public final AcslParser.requires_clause_return requires_clause() throws RecognitionException {
		AcslParser.requires_clause_return retval = new AcslParser.requires_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token REQUIRES12=null;
		ParserRuleReturnScope term13 =null;

		Object REQUIRES12_tree=null;
		RewriteRuleTokenStream stream_REQUIRES=new RewriteRuleTokenStream(adaptor,"token REQUIRES");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			// AcslParser.g:79:5: ( REQUIRES term -> ^( REQUIRES term ) )
			// AcslParser.g:79:7: REQUIRES term
			{
			REQUIRES12=(Token)match(input,REQUIRES,FOLLOW_REQUIRES_in_requires_clause454); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_REQUIRES.add(REQUIRES12);

			pushFollow(FOLLOW_term_in_requires_clause456);
			term13=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_term.add(term13.getTree());
			// AST REWRITE
			// elements: REQUIRES, term
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 79:21: -> ^( REQUIRES term )
			{
				// AcslParser.g:79:24: ^( REQUIRES term )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_REQUIRES.nextNode(), root_1);
				adaptor.addChild(root_1, stream_term.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "requires_clause"


	public static class terminates_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "terminates_clause"
	// AcslParser.g:82:1: terminates_clause : TEMINATES term -> ^( TERMINATES term ) ;
	public final AcslParser.terminates_clause_return terminates_clause() throws RecognitionException {
		AcslParser.terminates_clause_return retval = new AcslParser.terminates_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token TEMINATES14=null;
		ParserRuleReturnScope term15 =null;

		Object TEMINATES14_tree=null;
		RewriteRuleTokenStream stream_TEMINATES=new RewriteRuleTokenStream(adaptor,"token TEMINATES");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			// AcslParser.g:83:5: ( TEMINATES term -> ^( TERMINATES term ) )
			// AcslParser.g:83:7: TEMINATES term
			{
			TEMINATES14=(Token)match(input,TEMINATES,FOLLOW_TEMINATES_in_terminates_clause481); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_TEMINATES.add(TEMINATES14);

			pushFollow(FOLLOW_term_in_terminates_clause483);
			term15=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_term.add(term15.getTree());
			// AST REWRITE
			// elements: term
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 83:22: -> ^( TERMINATES term )
			{
				// AcslParser.g:83:25: ^( TERMINATES term )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TERMINATES, "TERMINATES"), root_1);
				adaptor.addChild(root_1, stream_term.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "terminates_clause"


	public static class rel_op_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "rel_op"
	// AcslParser.g:86:1: rel_op : ( EQ | NEQ | LTE | GTE | LT | GT );
	public final AcslParser.rel_op_return rel_op() throws RecognitionException {
		AcslParser.rel_op_return retval = new AcslParser.rel_op_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set16=null;

		Object set16_tree=null;

		try {
			// AcslParser.g:87:5: ( EQ | NEQ | LTE | GTE | LT | GT )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set16=input.LT(1);
			if ( input.LA(1)==EQ||(input.LA(1) >= GT && input.LA(1) <= GTE)||(input.LA(1) >= LT && input.LA(1) <= LTE)||input.LA(1)==NEQ ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set16));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "rel_op"


	public static class binders_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "binders"
	// AcslParser.g:90:1: binders : binder ( COMMA binder )* -> ^( BINDER_LIST ( binder )+ ) ;
	public final AcslParser.binders_return binders() throws RecognitionException {
		AcslParser.binders_return retval = new AcslParser.binders_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA18=null;
		ParserRuleReturnScope binder17 =null;
		ParserRuleReturnScope binder19 =null;

		Object COMMA18_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_binder=new RewriteRuleSubtreeStream(adaptor,"rule binder");

		try {
			// AcslParser.g:91:5: ( binder ( COMMA binder )* -> ^( BINDER_LIST ( binder )+ ) )
			// AcslParser.g:91:7: binder ( COMMA binder )*
			{
			pushFollow(FOLLOW_binder_in_binders545);
			binder17=binder();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_binder.add(binder17.getTree());
			// AcslParser.g:91:14: ( COMMA binder )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==COMMA) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// AcslParser.g:91:15: COMMA binder
					{
					COMMA18=(Token)match(input,COMMA,FOLLOW_COMMA_in_binders548); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA18);

					pushFollow(FOLLOW_binder_in_binders550);
					binder19=binder();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_binder.add(binder19.getTree());
					}
					break;

				default :
					break loop5;
				}
			}

			// AST REWRITE
			// elements: binder
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 92:9: -> ^( BINDER_LIST ( binder )+ )
			{
				// AcslParser.g:92:11: ^( BINDER_LIST ( binder )+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BINDER_LIST, "BINDER_LIST"), root_1);
				if ( !(stream_binder.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_binder.hasNext() ) {
					adaptor.addChild(root_1, stream_binder.nextTree());
				}
				stream_binder.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "binders"


	public static class binder_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "binder"
	// AcslParser.g:95:1: binder : type_expr variable_ident ( COMMA variable_ident )* -> ^( BINDER type_expr ( variable_ident )+ ) ;
	public final AcslParser.binder_return binder() throws RecognitionException {
		AcslParser.binder_return retval = new AcslParser.binder_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA22=null;
		ParserRuleReturnScope type_expr20 =null;
		ParserRuleReturnScope variable_ident21 =null;
		ParserRuleReturnScope variable_ident23 =null;

		Object COMMA22_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_type_expr=new RewriteRuleSubtreeStream(adaptor,"rule type_expr");
		RewriteRuleSubtreeStream stream_variable_ident=new RewriteRuleSubtreeStream(adaptor,"rule variable_ident");

		try {
			// AcslParser.g:96:5: ( type_expr variable_ident ( COMMA variable_ident )* -> ^( BINDER type_expr ( variable_ident )+ ) )
			// AcslParser.g:96:7: type_expr variable_ident ( COMMA variable_ident )*
			{
			pushFollow(FOLLOW_type_expr_in_binder585);
			type_expr20=type_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_type_expr.add(type_expr20.getTree());
			pushFollow(FOLLOW_variable_ident_in_binder587);
			variable_ident21=variable_ident();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_variable_ident.add(variable_ident21.getTree());
			// AcslParser.g:96:32: ( COMMA variable_ident )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==COMMA) ) {
					int LA6_1 = input.LA(2);
					if ( (LA6_1==ID) ) {
						int LA6_3 = input.LA(3);
						if ( (LA6_3==EOF||LA6_3==COMMA||LA6_3==LSQUARE||LA6_3==RCURLY||LA6_3==SEMICOL) ) {
							alt6=1;
						}

					}
					else if ( (LA6_1==LPAREN||LA6_1==STAR) ) {
						alt6=1;
					}

				}

				switch (alt6) {
				case 1 :
					// AcslParser.g:96:33: COMMA variable_ident
					{
					COMMA22=(Token)match(input,COMMA,FOLLOW_COMMA_in_binder590); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA22);

					pushFollow(FOLLOW_variable_ident_in_binder592);
					variable_ident23=variable_ident();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_variable_ident.add(variable_ident23.getTree());
					}
					break;

				default :
					break loop6;
				}
			}

			// AST REWRITE
			// elements: type_expr, variable_ident
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 97:9: -> ^( BINDER type_expr ( variable_ident )+ )
			{
				// AcslParser.g:97:11: ^( BINDER type_expr ( variable_ident )+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BINDER, "BINDER"), root_1);
				adaptor.addChild(root_1, stream_type_expr.nextTree());
				if ( !(stream_variable_ident.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_variable_ident.hasNext() ) {
					adaptor.addChild(root_1, stream_variable_ident.nextTree());
				}
				stream_variable_ident.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "binder"


	public static class type_expr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "type_expr"
	// AcslParser.g:100:1: type_expr : ( logic_type_expr -> ^( TYPE logic_type_expr ) | c_type -> ^( TYPE c_type ) );
	public final AcslParser.type_expr_return type_expr() throws RecognitionException {
		AcslParser.type_expr_return retval = new AcslParser.type_expr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope logic_type_expr24 =null;
		ParserRuleReturnScope c_type25 =null;

		RewriteRuleSubtreeStream stream_c_type=new RewriteRuleSubtreeStream(adaptor,"rule c_type");
		RewriteRuleSubtreeStream stream_logic_type_expr=new RewriteRuleSubtreeStream(adaptor,"rule logic_type_expr");

		try {
			// AcslParser.g:101:5: ( logic_type_expr -> ^( TYPE logic_type_expr ) | c_type -> ^( TYPE c_type ) )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==BOOLEAN||LA7_0==ID||LA7_0==REAL||LA7_0==INTGER) ) {
				alt7=1;
			}
			else if ( (LA7_0==CHAR||LA7_0==DOUBLE||LA7_0==FLOAT||LA7_0==INT||LA7_0==LONG||LA7_0==SHORT||LA7_0==VOID) ) {
				alt7=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// AcslParser.g:101:7: logic_type_expr
					{
					pushFollow(FOLLOW_logic_type_expr_in_type_expr629);
					logic_type_expr24=logic_type_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_logic_type_expr.add(logic_type_expr24.getTree());
					// AST REWRITE
					// elements: logic_type_expr
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 101:23: -> ^( TYPE logic_type_expr )
					{
						// AcslParser.g:101:25: ^( TYPE logic_type_expr )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE, "TYPE"), root_1);
						adaptor.addChild(root_1, stream_logic_type_expr.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:102:7: c_type
					{
					pushFollow(FOLLOW_c_type_in_type_expr644);
					c_type25=c_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_c_type.add(c_type25.getTree());
					// AST REWRITE
					// elements: c_type
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 102:14: -> ^( TYPE c_type )
					{
						// AcslParser.g:102:16: ^( TYPE c_type )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE, "TYPE"), root_1);
						adaptor.addChild(root_1, stream_c_type.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type_expr"


	public static class logic_type_expr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logic_type_expr"
	// AcslParser.g:105:1: logic_type_expr : ( built_in_logic_type -> ^( TYPE_BUILTIN built_in_logic_type ) | ID -> ^( TYPE_ID ID ) );
	public final AcslParser.logic_type_expr_return logic_type_expr() throws RecognitionException {
		AcslParser.logic_type_expr_return retval = new AcslParser.logic_type_expr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID27=null;
		ParserRuleReturnScope built_in_logic_type26 =null;

		Object ID27_tree=null;
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleSubtreeStream stream_built_in_logic_type=new RewriteRuleSubtreeStream(adaptor,"rule built_in_logic_type");

		try {
			// AcslParser.g:106:5: ( built_in_logic_type -> ^( TYPE_BUILTIN built_in_logic_type ) | ID -> ^( TYPE_ID ID ) )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==BOOLEAN||LA8_0==REAL||LA8_0==INTGER) ) {
				alt8=1;
			}
			else if ( (LA8_0==ID) ) {
				alt8=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// AcslParser.g:106:7: built_in_logic_type
					{
					pushFollow(FOLLOW_built_in_logic_type_in_logic_type_expr668);
					built_in_logic_type26=built_in_logic_type();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_built_in_logic_type.add(built_in_logic_type26.getTree());
					// AST REWRITE
					// elements: built_in_logic_type
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 106:27: -> ^( TYPE_BUILTIN built_in_logic_type )
					{
						// AcslParser.g:106:29: ^( TYPE_BUILTIN built_in_logic_type )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE_BUILTIN, "TYPE_BUILTIN"), root_1);
						adaptor.addChild(root_1, stream_built_in_logic_type.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:107:7: ID
					{
					ID27=(Token)match(input,ID,FOLLOW_ID_in_logic_type_expr683); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(ID27);

					// AST REWRITE
					// elements: ID
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 107:10: -> ^( TYPE_ID ID )
					{
						// AcslParser.g:107:12: ^( TYPE_ID ID )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TYPE_ID, "TYPE_ID"), root_1);
						adaptor.addChild(root_1, stream_ID.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logic_type_expr"


	public static class c_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "c_type"
	// AcslParser.g:110:1: c_type : ( CHAR | DOUBLE | FLOAT | INT | LONG | SHORT | VOID );
	public final AcslParser.c_type_return c_type() throws RecognitionException {
		AcslParser.c_type_return retval = new AcslParser.c_type_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set28=null;

		Object set28_tree=null;

		try {
			// AcslParser.g:111:5: ( CHAR | DOUBLE | FLOAT | INT | LONG | SHORT | VOID )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set28=input.LT(1);
			if ( input.LA(1)==CHAR||input.LA(1)==DOUBLE||input.LA(1)==FLOAT||input.LA(1)==INT||input.LA(1)==LONG||input.LA(1)==SHORT||input.LA(1)==VOID ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set28));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "c_type"


	public static class built_in_logic_type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "built_in_logic_type"
	// AcslParser.g:114:1: built_in_logic_type : ( BOOLEAN | INTGER | REAL );
	public final AcslParser.built_in_logic_type_return built_in_logic_type() throws RecognitionException {
		AcslParser.built_in_logic_type_return retval = new AcslParser.built_in_logic_type_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set29=null;

		Object set29_tree=null;

		try {
			// AcslParser.g:115:5: ( BOOLEAN | INTGER | REAL )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set29=input.LT(1);
			if ( input.LA(1)==BOOLEAN||input.LA(1)==REAL||input.LA(1)==INTGER ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set29));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "built_in_logic_type"


	public static class variable_ident_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "variable_ident"
	// AcslParser.g:118:1: variable_ident : ( STAR variable_ident_base | variable_ident_base LSQUARE RSQUARE | variable_ident_base );
	public final AcslParser.variable_ident_return variable_ident() throws RecognitionException {
		AcslParser.variable_ident_return retval = new AcslParser.variable_ident_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STAR30=null;
		Token LSQUARE33=null;
		Token RSQUARE34=null;
		ParserRuleReturnScope variable_ident_base31 =null;
		ParserRuleReturnScope variable_ident_base32 =null;
		ParserRuleReturnScope variable_ident_base35 =null;

		Object STAR30_tree=null;
		Object LSQUARE33_tree=null;
		Object RSQUARE34_tree=null;

		try {
			// AcslParser.g:119:5: ( STAR variable_ident_base | variable_ident_base LSQUARE RSQUARE | variable_ident_base )
			int alt9=3;
			switch ( input.LA(1) ) {
			case STAR:
				{
				alt9=1;
				}
				break;
			case ID:
				{
				int LA9_2 = input.LA(2);
				if ( (synpred24_AcslParser()) ) {
					alt9=2;
				}
				else if ( (true) ) {
					alt9=3;
				}

				}
				break;
			case LPAREN:
				{
				int LA9_3 = input.LA(2);
				if ( (synpred24_AcslParser()) ) {
					alt9=2;
				}
				else if ( (true) ) {
					alt9=3;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// AcslParser.g:119:7: STAR variable_ident_base
					{
					root_0 = (Object)adaptor.nil();


					STAR30=(Token)match(input,STAR,FOLLOW_STAR_in_variable_ident773); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STAR30_tree = (Object)adaptor.create(STAR30);
					adaptor.addChild(root_0, STAR30_tree);
					}

					pushFollow(FOLLOW_variable_ident_base_in_variable_ident775);
					variable_ident_base31=variable_ident_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, variable_ident_base31.getTree());

					}
					break;
				case 2 :
					// AcslParser.g:120:7: variable_ident_base LSQUARE RSQUARE
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_variable_ident_base_in_variable_ident783);
					variable_ident_base32=variable_ident_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, variable_ident_base32.getTree());

					LSQUARE33=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_variable_ident785); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LSQUARE33_tree = (Object)adaptor.create(LSQUARE33);
					adaptor.addChild(root_0, LSQUARE33_tree);
					}

					RSQUARE34=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_variable_ident787); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RSQUARE34_tree = (Object)adaptor.create(RSQUARE34);
					adaptor.addChild(root_0, RSQUARE34_tree);
					}

					}
					break;
				case 3 :
					// AcslParser.g:121:7: variable_ident_base
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_variable_ident_base_in_variable_ident795);
					variable_ident_base35=variable_ident_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, variable_ident_base35.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "variable_ident"


	public static class variable_ident_base_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "variable_ident_base"
	// AcslParser.g:124:1: variable_ident_base : ( ID | LPAREN variable_ident RPAREN );
	public final AcslParser.variable_ident_base_return variable_ident_base() throws RecognitionException {
		AcslParser.variable_ident_base_return retval = new AcslParser.variable_ident_base_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID36=null;
		Token LPAREN37=null;
		Token RPAREN39=null;
		ParserRuleReturnScope variable_ident38 =null;

		Object ID36_tree=null;
		Object LPAREN37_tree=null;
		Object RPAREN39_tree=null;

		try {
			// AcslParser.g:125:5: ( ID | LPAREN variable_ident RPAREN )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==ID) ) {
				alt10=1;
			}
			else if ( (LA10_0==LPAREN) ) {
				alt10=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// AcslParser.g:125:7: ID
					{
					root_0 = (Object)adaptor.nil();


					ID36=(Token)match(input,ID,FOLLOW_ID_in_variable_ident_base812); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID36_tree = (Object)adaptor.create(ID36);
					adaptor.addChild(root_0, ID36_tree);
					}

					}
					break;
				case 2 :
					// AcslParser.g:126:7: LPAREN variable_ident RPAREN
					{
					root_0 = (Object)adaptor.nil();


					LPAREN37=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_variable_ident_base820); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LPAREN37_tree = (Object)adaptor.create(LPAREN37);
					adaptor.addChild(root_0, LPAREN37_tree);
					}

					pushFollow(FOLLOW_variable_ident_in_variable_ident_base822);
					variable_ident38=variable_ident();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, variable_ident38.getTree());

					RPAREN39=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_variable_ident_base824); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RPAREN39_tree = (Object)adaptor.create(RPAREN39);
					adaptor.addChild(root_0, RPAREN39_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "variable_ident_base"


	public static class guards_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "guards_clause"
	// AcslParser.g:129:1: guards_clause : GUARDS term -> ^( GUARDS term ) ;
	public final AcslParser.guards_clause_return guards_clause() throws RecognitionException {
		AcslParser.guards_clause_return retval = new AcslParser.guards_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token GUARDS40=null;
		ParserRuleReturnScope term41 =null;

		Object GUARDS40_tree=null;
		RewriteRuleTokenStream stream_GUARDS=new RewriteRuleTokenStream(adaptor,"token GUARDS");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			// AcslParser.g:130:5: ( GUARDS term -> ^( GUARDS term ) )
			// AcslParser.g:130:7: GUARDS term
			{
			GUARDS40=(Token)match(input,GUARDS,FOLLOW_GUARDS_in_guards_clause841); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_GUARDS.add(GUARDS40);

			pushFollow(FOLLOW_term_in_guards_clause843);
			term41=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_term.add(term41.getTree());
			// AST REWRITE
			// elements: term, GUARDS
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 130:19: -> ^( GUARDS term )
			{
				// AcslParser.g:130:21: ^( GUARDS term )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_GUARDS.nextNode(), root_1);
				adaptor.addChild(root_1, stream_term.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "guards_clause"


	public static class simple_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "simple_clause"
	// AcslParser.g:133:1: simple_clause : ( assigns_clause | ensures_clause | allocation_clause | reads_clause | depends_clause | guards_clause );
	public final AcslParser.simple_clause_return simple_clause() throws RecognitionException {
		AcslParser.simple_clause_return retval = new AcslParser.simple_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope assigns_clause42 =null;
		ParserRuleReturnScope ensures_clause43 =null;
		ParserRuleReturnScope allocation_clause44 =null;
		ParserRuleReturnScope reads_clause45 =null;
		ParserRuleReturnScope depends_clause46 =null;
		ParserRuleReturnScope guards_clause47 =null;


		try {
			// AcslParser.g:134:5: ( assigns_clause | ensures_clause | allocation_clause | reads_clause | depends_clause | guards_clause )
			int alt11=6;
			switch ( input.LA(1) ) {
			case ASSIGNS:
				{
				alt11=1;
				}
				break;
			case ENSURES:
				{
				alt11=2;
				}
				break;
			case ALLOC:
			case FREES:
				{
				alt11=3;
				}
				break;
			case READS:
				{
				alt11=4;
				}
				break;
			case DEPENDS:
				{
				alt11=5;
				}
				break;
			case GUARDS:
				{
				alt11=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// AcslParser.g:134:7: assigns_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_assigns_clause_in_simple_clause867);
					assigns_clause42=assigns_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, assigns_clause42.getTree());

					}
					break;
				case 2 :
					// AcslParser.g:135:7: ensures_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_ensures_clause_in_simple_clause875);
					ensures_clause43=ensures_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, ensures_clause43.getTree());

					}
					break;
				case 3 :
					// AcslParser.g:136:7: allocation_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_allocation_clause_in_simple_clause884);
					allocation_clause44=allocation_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, allocation_clause44.getTree());

					}
					break;
				case 4 :
					// AcslParser.g:137:7: reads_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_reads_clause_in_simple_clause892);
					reads_clause45=reads_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, reads_clause45.getTree());

					}
					break;
				case 5 :
					// AcslParser.g:138:7: depends_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_depends_clause_in_simple_clause900);
					depends_clause46=depends_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, depends_clause46.getTree());

					}
					break;
				case 6 :
					// AcslParser.g:139:7: guards_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_guards_clause_in_simple_clause908);
					guards_clause47=guards_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, guards_clause47.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "simple_clause"


	public static class assigns_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assigns_clause"
	// AcslParser.g:142:1: assigns_clause : ASSIGNS argumentExpressionList -> ^( ASSIGNS argumentExpressionList ) ;
	public final AcslParser.assigns_clause_return assigns_clause() throws RecognitionException {
		AcslParser.assigns_clause_return retval = new AcslParser.assigns_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ASSIGNS48=null;
		ParserRuleReturnScope argumentExpressionList49 =null;

		Object ASSIGNS48_tree=null;
		RewriteRuleTokenStream stream_ASSIGNS=new RewriteRuleTokenStream(adaptor,"token ASSIGNS");
		RewriteRuleSubtreeStream stream_argumentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule argumentExpressionList");

		try {
			// AcslParser.g:143:5: ( ASSIGNS argumentExpressionList -> ^( ASSIGNS argumentExpressionList ) )
			// AcslParser.g:143:7: ASSIGNS argumentExpressionList
			{
			ASSIGNS48=(Token)match(input,ASSIGNS,FOLLOW_ASSIGNS_in_assigns_clause925); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ASSIGNS.add(ASSIGNS48);

			pushFollow(FOLLOW_argumentExpressionList_in_assigns_clause927);
			argumentExpressionList49=argumentExpressionList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList49.getTree());
			// AST REWRITE
			// elements: argumentExpressionList, ASSIGNS
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 143:38: -> ^( ASSIGNS argumentExpressionList )
			{
				// AcslParser.g:143:40: ^( ASSIGNS argumentExpressionList )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_ASSIGNS.nextNode(), root_1);
				adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assigns_clause"


	public static class ensures_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "ensures_clause"
	// AcslParser.g:146:1: ensures_clause : ENSURES term -> ^( ENSURES term ) ;
	public final AcslParser.ensures_clause_return ensures_clause() throws RecognitionException {
		AcslParser.ensures_clause_return retval = new AcslParser.ensures_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ENSURES50=null;
		ParserRuleReturnScope term51 =null;

		Object ENSURES50_tree=null;
		RewriteRuleTokenStream stream_ENSURES=new RewriteRuleTokenStream(adaptor,"token ENSURES");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			// AcslParser.g:147:5: ( ENSURES term -> ^( ENSURES term ) )
			// AcslParser.g:147:7: ENSURES term
			{
			ENSURES50=(Token)match(input,ENSURES,FOLLOW_ENSURES_in_ensures_clause951); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ENSURES.add(ENSURES50);

			pushFollow(FOLLOW_term_in_ensures_clause953);
			term51=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_term.add(term51.getTree());
			// AST REWRITE
			// elements: term, ENSURES
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 147:20: -> ^( ENSURES term )
			{
				// AcslParser.g:147:22: ^( ENSURES term )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_ENSURES.nextNode(), root_1);
				adaptor.addChild(root_1, stream_term.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ensures_clause"


	public static class allocation_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "allocation_clause"
	// AcslParser.g:150:1: allocation_clause : ( ALLOC argumentExpressionList ( COMMA term )? -> ^( ALLOC argumentExpressionList ( term )? ) | FREES argumentExpressionList -> ^( FREES argumentExpressionList ) );
	public final AcslParser.allocation_clause_return allocation_clause() throws RecognitionException {
		AcslParser.allocation_clause_return retval = new AcslParser.allocation_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ALLOC52=null;
		Token COMMA54=null;
		Token FREES56=null;
		ParserRuleReturnScope argumentExpressionList53 =null;
		ParserRuleReturnScope term55 =null;
		ParserRuleReturnScope argumentExpressionList57 =null;

		Object ALLOC52_tree=null;
		Object COMMA54_tree=null;
		Object FREES56_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_ALLOC=new RewriteRuleTokenStream(adaptor,"token ALLOC");
		RewriteRuleTokenStream stream_FREES=new RewriteRuleTokenStream(adaptor,"token FREES");
		RewriteRuleSubtreeStream stream_argumentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule argumentExpressionList");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			// AcslParser.g:151:5: ( ALLOC argumentExpressionList ( COMMA term )? -> ^( ALLOC argumentExpressionList ( term )? ) | FREES argumentExpressionList -> ^( FREES argumentExpressionList ) )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==ALLOC) ) {
				alt13=1;
			}
			else if ( (LA13_0==FREES) ) {
				alt13=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// AcslParser.g:151:7: ALLOC argumentExpressionList ( COMMA term )?
					{
					ALLOC52=(Token)match(input,ALLOC,FOLLOW_ALLOC_in_allocation_clause977); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ALLOC.add(ALLOC52);

					pushFollow(FOLLOW_argumentExpressionList_in_allocation_clause979);
					argumentExpressionList53=argumentExpressionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList53.getTree());
					// AcslParser.g:151:36: ( COMMA term )?
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==COMMA) ) {
						alt12=1;
					}
					switch (alt12) {
						case 1 :
							// AcslParser.g:151:37: COMMA term
							{
							COMMA54=(Token)match(input,COMMA,FOLLOW_COMMA_in_allocation_clause982); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA54);

							pushFollow(FOLLOW_term_in_allocation_clause984);
							term55=term();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_term.add(term55.getTree());
							}
							break;

					}

					// AST REWRITE
					// elements: argumentExpressionList, term, ALLOC
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 151:50: -> ^( ALLOC argumentExpressionList ( term )? )
					{
						// AcslParser.g:151:52: ^( ALLOC argumentExpressionList ( term )? )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_ALLOC.nextNode(), root_1);
						adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
						// AcslParser.g:151:83: ( term )?
						if ( stream_term.hasNext() ) {
							adaptor.addChild(root_1, stream_term.nextTree());
						}
						stream_term.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:152:7: FREES argumentExpressionList
					{
					FREES56=(Token)match(input,FREES,FOLLOW_FREES_in_allocation_clause1004); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_FREES.add(FREES56);

					pushFollow(FOLLOW_argumentExpressionList_in_allocation_clause1006);
					argumentExpressionList57=argumentExpressionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList57.getTree());
					// AST REWRITE
					// elements: FREES, argumentExpressionList
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 152:36: -> ^( FREES argumentExpressionList )
					{
						// AcslParser.g:152:38: ^( FREES argumentExpressionList )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_FREES.nextNode(), root_1);
						adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "allocation_clause"


	public static class reads_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "reads_clause"
	// AcslParser.g:155:1: reads_clause : READS argumentExpressionList -> ^( READS argumentExpressionList ) ;
	public final AcslParser.reads_clause_return reads_clause() throws RecognitionException {
		AcslParser.reads_clause_return retval = new AcslParser.reads_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token READS58=null;
		ParserRuleReturnScope argumentExpressionList59 =null;

		Object READS58_tree=null;
		RewriteRuleTokenStream stream_READS=new RewriteRuleTokenStream(adaptor,"token READS");
		RewriteRuleSubtreeStream stream_argumentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule argumentExpressionList");

		try {
			// AcslParser.g:156:5: ( READS argumentExpressionList -> ^( READS argumentExpressionList ) )
			// AcslParser.g:156:7: READS argumentExpressionList
			{
			READS58=(Token)match(input,READS,FOLLOW_READS_in_reads_clause1030); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_READS.add(READS58);

			pushFollow(FOLLOW_argumentExpressionList_in_reads_clause1032);
			argumentExpressionList59=argumentExpressionList();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList59.getTree());
			// AST REWRITE
			// elements: argumentExpressionList, READS
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 156:36: -> ^( READS argumentExpressionList )
			{
				// AcslParser.g:156:38: ^( READS argumentExpressionList )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_READS.nextNode(), root_1);
				adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "reads_clause"


	public static class depends_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "depends_clause"
	// AcslParser.g:159:1: depends_clause : DEPENDS event_list -> ^( DEPENDS event_list ) ;
	public final AcslParser.depends_clause_return depends_clause() throws RecognitionException {
		AcslParser.depends_clause_return retval = new AcslParser.depends_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token DEPENDS60=null;
		ParserRuleReturnScope event_list61 =null;

		Object DEPENDS60_tree=null;
		RewriteRuleTokenStream stream_DEPENDS=new RewriteRuleTokenStream(adaptor,"token DEPENDS");
		RewriteRuleSubtreeStream stream_event_list=new RewriteRuleSubtreeStream(adaptor,"rule event_list");

		try {
			// AcslParser.g:160:5: ( DEPENDS event_list -> ^( DEPENDS event_list ) )
			// AcslParser.g:160:7: DEPENDS event_list
			{
			DEPENDS60=(Token)match(input,DEPENDS,FOLLOW_DEPENDS_in_depends_clause1056); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_DEPENDS.add(DEPENDS60);

			pushFollow(FOLLOW_event_list_in_depends_clause1058);
			event_list61=event_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_event_list.add(event_list61.getTree());
			// AST REWRITE
			// elements: event_list, DEPENDS
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 160:26: -> ^( DEPENDS event_list )
			{
				// AcslParser.g:160:28: ^( DEPENDS event_list )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_DEPENDS.nextNode(), root_1);
				adaptor.addChild(root_1, stream_event_list.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "depends_clause"


	public static class event_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "event_list"
	// AcslParser.g:163:1: event_list : event ( COMMA event )* -> ^( EVENT_LIST ( event )+ ) ;
	public final AcslParser.event_list_return event_list() throws RecognitionException {
		AcslParser.event_list_return retval = new AcslParser.event_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA63=null;
		ParserRuleReturnScope event62 =null;
		ParserRuleReturnScope event64 =null;

		Object COMMA63_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_event=new RewriteRuleSubtreeStream(adaptor,"rule event");

		try {
			// AcslParser.g:164:5: ( event ( COMMA event )* -> ^( EVENT_LIST ( event )+ ) )
			// AcslParser.g:164:7: event ( COMMA event )*
			{
			pushFollow(FOLLOW_event_in_event_list1082);
			event62=event();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_event.add(event62.getTree());
			// AcslParser.g:164:13: ( COMMA event )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==COMMA) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// AcslParser.g:164:14: COMMA event
					{
					COMMA63=(Token)match(input,COMMA,FOLLOW_COMMA_in_event_list1085); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMMA.add(COMMA63);

					pushFollow(FOLLOW_event_in_event_list1087);
					event64=event();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event.add(event64.getTree());
					}
					break;

				default :
					break loop14;
				}
			}

			// AST REWRITE
			// elements: event
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 164:28: -> ^( EVENT_LIST ( event )+ )
			{
				// AcslParser.g:164:31: ^( EVENT_LIST ( event )+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EVENT_LIST, "EVENT_LIST"), root_1);
				if ( !(stream_event.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_event.hasNext() ) {
					adaptor.addChild(root_1, stream_event.nextTree());
				}
				stream_event.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "event_list"


	public static class event_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "event"
	// AcslParser.g:167:1: event : ( event_base PLUS event_base -> ^( EVENT_PLUS event_base event_base ) | event_base SUB event_base -> ^( EVENT_SUB event_base event_base ) | event_base AMPERSAND event_base -> ^( EVENT_INTS event_base event_base ) | event_base -> ^( EVENT_BASE event_base ) );
	public final AcslParser.event_return event() throws RecognitionException {
		AcslParser.event_return retval = new AcslParser.event_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PLUS66=null;
		Token SUB69=null;
		Token AMPERSAND72=null;
		ParserRuleReturnScope event_base65 =null;
		ParserRuleReturnScope event_base67 =null;
		ParserRuleReturnScope event_base68 =null;
		ParserRuleReturnScope event_base70 =null;
		ParserRuleReturnScope event_base71 =null;
		ParserRuleReturnScope event_base73 =null;
		ParserRuleReturnScope event_base74 =null;

		Object PLUS66_tree=null;
		Object SUB69_tree=null;
		Object AMPERSAND72_tree=null;
		RewriteRuleTokenStream stream_AMPERSAND=new RewriteRuleTokenStream(adaptor,"token AMPERSAND");
		RewriteRuleTokenStream stream_SUB=new RewriteRuleTokenStream(adaptor,"token SUB");
		RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
		RewriteRuleSubtreeStream stream_event_base=new RewriteRuleSubtreeStream(adaptor,"rule event_base");

		try {
			// AcslParser.g:168:5: ( event_base PLUS event_base -> ^( EVENT_PLUS event_base event_base ) | event_base SUB event_base -> ^( EVENT_SUB event_base event_base ) | event_base AMPERSAND event_base -> ^( EVENT_INTS event_base event_base ) | event_base -> ^( EVENT_BASE event_base ) )
			int alt15=4;
			switch ( input.LA(1) ) {
			case READ:
				{
				int LA15_1 = input.LA(2);
				if ( (synpred34_AcslParser()) ) {
					alt15=1;
				}
				else if ( (synpred35_AcslParser()) ) {
					alt15=2;
				}
				else if ( (synpred36_AcslParser()) ) {
					alt15=3;
				}
				else if ( (true) ) {
					alt15=4;
				}

				}
				break;
			case WRITE:
				{
				int LA15_2 = input.LA(2);
				if ( (synpred34_AcslParser()) ) {
					alt15=1;
				}
				else if ( (synpred35_AcslParser()) ) {
					alt15=2;
				}
				else if ( (synpred36_AcslParser()) ) {
					alt15=3;
				}
				else if ( (true) ) {
					alt15=4;
				}

				}
				break;
			case CALL:
				{
				int LA15_3 = input.LA(2);
				if ( (synpred34_AcslParser()) ) {
					alt15=1;
				}
				else if ( (synpred35_AcslParser()) ) {
					alt15=2;
				}
				else if ( (synpred36_AcslParser()) ) {
					alt15=3;
				}
				else if ( (true) ) {
					alt15=4;
				}

				}
				break;
			case NOACT:
				{
				int LA15_4 = input.LA(2);
				if ( (synpred34_AcslParser()) ) {
					alt15=1;
				}
				else if ( (synpred35_AcslParser()) ) {
					alt15=2;
				}
				else if ( (synpred36_AcslParser()) ) {
					alt15=3;
				}
				else if ( (true) ) {
					alt15=4;
				}

				}
				break;
			case ANYACT:
				{
				int LA15_5 = input.LA(2);
				if ( (synpred34_AcslParser()) ) {
					alt15=1;
				}
				else if ( (synpred35_AcslParser()) ) {
					alt15=2;
				}
				else if ( (synpred36_AcslParser()) ) {
					alt15=3;
				}
				else if ( (true) ) {
					alt15=4;
				}

				}
				break;
			case LPAREN:
				{
				int LA15_6 = input.LA(2);
				if ( (synpred34_AcslParser()) ) {
					alt15=1;
				}
				else if ( (synpred35_AcslParser()) ) {
					alt15=2;
				}
				else if ( (synpred36_AcslParser()) ) {
					alt15=3;
				}
				else if ( (true) ) {
					alt15=4;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// AcslParser.g:168:7: event_base PLUS event_base
					{
					pushFollow(FOLLOW_event_base_in_event1115);
					event_base65=event_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event_base.add(event_base65.getTree());
					PLUS66=(Token)match(input,PLUS,FOLLOW_PLUS_in_event1117); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_PLUS.add(PLUS66);

					pushFollow(FOLLOW_event_base_in_event1119);
					event_base67=event_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event_base.add(event_base67.getTree());
					// AST REWRITE
					// elements: event_base, event_base
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 169:9: -> ^( EVENT_PLUS event_base event_base )
					{
						// AcslParser.g:169:12: ^( EVENT_PLUS event_base event_base )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EVENT_PLUS, "EVENT_PLUS"), root_1);
						adaptor.addChild(root_1, stream_event_base.nextTree());
						adaptor.addChild(root_1, stream_event_base.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:170:7: event_base SUB event_base
					{
					pushFollow(FOLLOW_event_base_in_event1145);
					event_base68=event_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event_base.add(event_base68.getTree());
					SUB69=(Token)match(input,SUB,FOLLOW_SUB_in_event1147); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SUB.add(SUB69);

					pushFollow(FOLLOW_event_base_in_event1149);
					event_base70=event_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event_base.add(event_base70.getTree());
					// AST REWRITE
					// elements: event_base, event_base
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 171:9: -> ^( EVENT_SUB event_base event_base )
					{
						// AcslParser.g:171:12: ^( EVENT_SUB event_base event_base )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EVENT_SUB, "EVENT_SUB"), root_1);
						adaptor.addChild(root_1, stream_event_base.nextTree());
						adaptor.addChild(root_1, stream_event_base.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// AcslParser.g:172:7: event_base AMPERSAND event_base
					{
					pushFollow(FOLLOW_event_base_in_event1175);
					event_base71=event_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event_base.add(event_base71.getTree());
					AMPERSAND72=(Token)match(input,AMPERSAND,FOLLOW_AMPERSAND_in_event1177); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_AMPERSAND.add(AMPERSAND72);

					pushFollow(FOLLOW_event_base_in_event1179);
					event_base73=event_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event_base.add(event_base73.getTree());
					// AST REWRITE
					// elements: event_base, event_base
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 173:9: -> ^( EVENT_INTS event_base event_base )
					{
						// AcslParser.g:173:12: ^( EVENT_INTS event_base event_base )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EVENT_INTS, "EVENT_INTS"), root_1);
						adaptor.addChild(root_1, stream_event_base.nextTree());
						adaptor.addChild(root_1, stream_event_base.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// AcslParser.g:174:7: event_base
					{
					pushFollow(FOLLOW_event_base_in_event1205);
					event_base74=event_base();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event_base.add(event_base74.getTree());
					// AST REWRITE
					// elements: event_base
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 175:9: -> ^( EVENT_BASE event_base )
					{
						// AcslParser.g:175:12: ^( EVENT_BASE event_base )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EVENT_BASE, "EVENT_BASE"), root_1);
						adaptor.addChild(root_1, stream_event_base.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "event"


	public static class event_base_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "event_base"
	// AcslParser.g:178:1: event_base : ( READ LPAREN argumentExpressionList RPAREN -> ^( READ argumentExpressionList ) | WRITE LPAREN argumentExpressionList RPAREN -> ^( WRITE argumentExpressionList ) | CALL LPAREN ID ( COMMA argumentExpressionList )? RPAREN -> ^( CALL ID ( argumentExpressionList )? ) | NOACT -> ^( NOACT ) | ANYACT -> ^( ANYACT ) | LPAREN event RPAREN -> ^( EVENT_PARENTHESIZED ) );
	public final AcslParser.event_base_return event_base() throws RecognitionException {
		AcslParser.event_base_return retval = new AcslParser.event_base_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token READ75=null;
		Token LPAREN76=null;
		Token RPAREN78=null;
		Token WRITE79=null;
		Token LPAREN80=null;
		Token RPAREN82=null;
		Token CALL83=null;
		Token LPAREN84=null;
		Token ID85=null;
		Token COMMA86=null;
		Token RPAREN88=null;
		Token NOACT89=null;
		Token ANYACT90=null;
		Token LPAREN91=null;
		Token RPAREN93=null;
		ParserRuleReturnScope argumentExpressionList77 =null;
		ParserRuleReturnScope argumentExpressionList81 =null;
		ParserRuleReturnScope argumentExpressionList87 =null;
		ParserRuleReturnScope event92 =null;

		Object READ75_tree=null;
		Object LPAREN76_tree=null;
		Object RPAREN78_tree=null;
		Object WRITE79_tree=null;
		Object LPAREN80_tree=null;
		Object RPAREN82_tree=null;
		Object CALL83_tree=null;
		Object LPAREN84_tree=null;
		Object ID85_tree=null;
		Object COMMA86_tree=null;
		Object RPAREN88_tree=null;
		Object NOACT89_tree=null;
		Object ANYACT90_tree=null;
		Object LPAREN91_tree=null;
		Object RPAREN93_tree=null;
		RewriteRuleTokenStream stream_READ=new RewriteRuleTokenStream(adaptor,"token READ");
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_CALL=new RewriteRuleTokenStream(adaptor,"token CALL");
		RewriteRuleTokenStream stream_NOACT=new RewriteRuleTokenStream(adaptor,"token NOACT");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleTokenStream stream_ANYACT=new RewriteRuleTokenStream(adaptor,"token ANYACT");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleTokenStream stream_WRITE=new RewriteRuleTokenStream(adaptor,"token WRITE");
		RewriteRuleSubtreeStream stream_argumentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule argumentExpressionList");
		RewriteRuleSubtreeStream stream_event=new RewriteRuleSubtreeStream(adaptor,"rule event");

		try {
			// AcslParser.g:179:5: ( READ LPAREN argumentExpressionList RPAREN -> ^( READ argumentExpressionList ) | WRITE LPAREN argumentExpressionList RPAREN -> ^( WRITE argumentExpressionList ) | CALL LPAREN ID ( COMMA argumentExpressionList )? RPAREN -> ^( CALL ID ( argumentExpressionList )? ) | NOACT -> ^( NOACT ) | ANYACT -> ^( ANYACT ) | LPAREN event RPAREN -> ^( EVENT_PARENTHESIZED ) )
			int alt17=6;
			switch ( input.LA(1) ) {
			case READ:
				{
				alt17=1;
				}
				break;
			case WRITE:
				{
				alt17=2;
				}
				break;
			case CALL:
				{
				alt17=3;
				}
				break;
			case NOACT:
				{
				alt17=4;
				}
				break;
			case ANYACT:
				{
				alt17=5;
				}
				break;
			case LPAREN:
				{
				alt17=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// AcslParser.g:179:7: READ LPAREN argumentExpressionList RPAREN
					{
					READ75=(Token)match(input,READ,FOLLOW_READ_in_event_base1238); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_READ.add(READ75);

					LPAREN76=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_event_base1240); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN76);

					pushFollow(FOLLOW_argumentExpressionList_in_event_base1242);
					argumentExpressionList77=argumentExpressionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList77.getTree());
					RPAREN78=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_event_base1244); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN78);

					// AST REWRITE
					// elements: argumentExpressionList, READ
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 180:9: -> ^( READ argumentExpressionList )
					{
						// AcslParser.g:180:12: ^( READ argumentExpressionList )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_READ.nextNode(), root_1);
						adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:181:7: WRITE LPAREN argumentExpressionList RPAREN
					{
					WRITE79=(Token)match(input,WRITE,FOLLOW_WRITE_in_event_base1268); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_WRITE.add(WRITE79);

					LPAREN80=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_event_base1270); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN80);

					pushFollow(FOLLOW_argumentExpressionList_in_event_base1272);
					argumentExpressionList81=argumentExpressionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList81.getTree());
					RPAREN82=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_event_base1274); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN82);

					// AST REWRITE
					// elements: argumentExpressionList, WRITE
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 182:9: -> ^( WRITE argumentExpressionList )
					{
						// AcslParser.g:182:12: ^( WRITE argumentExpressionList )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_WRITE.nextNode(), root_1);
						adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// AcslParser.g:183:7: CALL LPAREN ID ( COMMA argumentExpressionList )? RPAREN
					{
					CALL83=(Token)match(input,CALL,FOLLOW_CALL_in_event_base1298); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_CALL.add(CALL83);

					LPAREN84=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_event_base1300); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN84);

					ID85=(Token)match(input,ID,FOLLOW_ID_in_event_base1302); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(ID85);

					// AcslParser.g:183:22: ( COMMA argumentExpressionList )?
					int alt16=2;
					int LA16_0 = input.LA(1);
					if ( (LA16_0==COMMA) ) {
						alt16=1;
					}
					switch (alt16) {
						case 1 :
							// AcslParser.g:183:23: COMMA argumentExpressionList
							{
							COMMA86=(Token)match(input,COMMA,FOLLOW_COMMA_in_event_base1305); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA86);

							pushFollow(FOLLOW_argumentExpressionList_in_event_base1307);
							argumentExpressionList87=argumentExpressionList();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList87.getTree());
							}
							break;

					}

					RPAREN88=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_event_base1311); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN88);

					// AST REWRITE
					// elements: ID, CALL, argumentExpressionList
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 184:9: -> ^( CALL ID ( argumentExpressionList )? )
					{
						// AcslParser.g:184:12: ^( CALL ID ( argumentExpressionList )? )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_CALL.nextNode(), root_1);
						adaptor.addChild(root_1, stream_ID.nextNode());
						// AcslParser.g:184:22: ( argumentExpressionList )?
						if ( stream_argumentExpressionList.hasNext() ) {
							adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
						}
						stream_argumentExpressionList.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// AcslParser.g:185:7: NOACT
					{
					NOACT89=(Token)match(input,NOACT,FOLLOW_NOACT_in_event_base1338); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_NOACT.add(NOACT89);

					// AST REWRITE
					// elements: NOACT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 186:9: -> ^( NOACT )
					{
						// AcslParser.g:186:12: ^( NOACT )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_NOACT.nextNode(), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// AcslParser.g:187:7: ANYACT
					{
					ANYACT90=(Token)match(input,ANYACT,FOLLOW_ANYACT_in_event_base1360); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ANYACT.add(ANYACT90);

					// AST REWRITE
					// elements: ANYACT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 188:9: -> ^( ANYACT )
					{
						// AcslParser.g:188:12: ^( ANYACT )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_ANYACT.nextNode(), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// AcslParser.g:189:7: LPAREN event RPAREN
					{
					LPAREN91=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_event_base1382); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN91);

					pushFollow(FOLLOW_event_in_event_base1384);
					event92=event();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_event.add(event92.getTree());
					RPAREN93=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_event_base1386); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN93);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 190:9: -> ^( EVENT_PARENTHESIZED )
					{
						// AcslParser.g:190:12: ^( EVENT_PARENTHESIZED )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EVENT_PARENTHESIZED, "EVENT_PARENTHESIZED"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "event_base"


	public static class named_behavior_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "named_behavior"
	// AcslParser.g:194:1: named_behavior : BEHAVIOR ID COLON behavior_body -> ^( BEHAVIOR ID behavior_body ) ;
	public final AcslParser.named_behavior_return named_behavior() throws RecognitionException {
		AcslParser.named_behavior_return retval = new AcslParser.named_behavior_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token BEHAVIOR94=null;
		Token ID95=null;
		Token COLON96=null;
		ParserRuleReturnScope behavior_body97 =null;

		Object BEHAVIOR94_tree=null;
		Object ID95_tree=null;
		Object COLON96_tree=null;
		RewriteRuleTokenStream stream_BEHAVIOR=new RewriteRuleTokenStream(adaptor,"token BEHAVIOR");
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleSubtreeStream stream_behavior_body=new RewriteRuleSubtreeStream(adaptor,"rule behavior_body");

		try {
			// AcslParser.g:195:5: ( BEHAVIOR ID COLON behavior_body -> ^( BEHAVIOR ID behavior_body ) )
			// AcslParser.g:195:7: BEHAVIOR ID COLON behavior_body
			{
			BEHAVIOR94=(Token)match(input,BEHAVIOR,FOLLOW_BEHAVIOR_in_named_behavior1419); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_BEHAVIOR.add(BEHAVIOR94);

			ID95=(Token)match(input,ID,FOLLOW_ID_in_named_behavior1421); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ID.add(ID95);

			COLON96=(Token)match(input,COLON,FOLLOW_COLON_in_named_behavior1423); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_COLON.add(COLON96);

			pushFollow(FOLLOW_behavior_body_in_named_behavior1425);
			behavior_body97=behavior_body();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_behavior_body.add(behavior_body97.getTree());
			// AST REWRITE
			// elements: ID, BEHAVIOR, behavior_body
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 195:39: -> ^( BEHAVIOR ID behavior_body )
			{
				// AcslParser.g:195:41: ^( BEHAVIOR ID behavior_body )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_BEHAVIOR.nextNode(), root_1);
				adaptor.addChild(root_1, stream_ID.nextNode());
				adaptor.addChild(root_1, stream_behavior_body.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "named_behavior"


	public static class behavior_body_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "behavior_body"
	// AcslParser.g:198:1: behavior_body : (b+= behavior_clause SEMICOL )+ -> ^( BEHAVIOR_BODY ( $b)+ ) ;
	public final AcslParser.behavior_body_return behavior_body() throws RecognitionException {
		AcslParser.behavior_body_return retval = new AcslParser.behavior_body_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMICOL98=null;
		List<Object> list_b=null;
		RuleReturnScope b = null;
		Object SEMICOL98_tree=null;
		RewriteRuleTokenStream stream_SEMICOL=new RewriteRuleTokenStream(adaptor,"token SEMICOL");
		RewriteRuleSubtreeStream stream_behavior_clause=new RewriteRuleSubtreeStream(adaptor,"rule behavior_clause");

		try {
			// AcslParser.g:199:5: ( (b+= behavior_clause SEMICOL )+ -> ^( BEHAVIOR_BODY ( $b)+ ) )
			// AcslParser.g:199:7: (b+= behavior_clause SEMICOL )+
			{
			// AcslParser.g:199:7: (b+= behavior_clause SEMICOL )+
			int cnt18=0;
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==ALLOC||(LA18_0 >= ASSIGNS && LA18_0 <= ASSUMES)||LA18_0==DEPENDS||LA18_0==ENSURES||LA18_0==FREES||LA18_0==GUARDS||LA18_0==READS||LA18_0==REQUIRES) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// AcslParser.g:199:8: b+= behavior_clause SEMICOL
					{
					pushFollow(FOLLOW_behavior_clause_in_behavior_body1454);
					b=behavior_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_behavior_clause.add(b.getTree());
					if (list_b==null) list_b=new ArrayList<Object>();
					list_b.add(b.getTree());
					SEMICOL98=(Token)match(input,SEMICOL,FOLLOW_SEMICOL_in_behavior_body1456); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SEMICOL.add(SEMICOL98);

					}
					break;

				default :
					if ( cnt18 >= 1 ) break loop18;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(18, input);
					throw eee;
				}
				cnt18++;
			}

			// AST REWRITE
			// elements: b
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: b
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
			RewriteRuleSubtreeStream stream_b=new RewriteRuleSubtreeStream(adaptor,"token b",list_b);
			root_0 = (Object)adaptor.nil();
			// 199:37: -> ^( BEHAVIOR_BODY ( $b)+ )
			{
				// AcslParser.g:199:40: ^( BEHAVIOR_BODY ( $b)+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BEHAVIOR_BODY, "BEHAVIOR_BODY"), root_1);
				if ( !(stream_b.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_b.hasNext() ) {
					adaptor.addChild(root_1, stream_b.nextTree());
				}
				stream_b.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "behavior_body"


	public static class behavior_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "behavior_clause"
	// AcslParser.g:202:1: behavior_clause : ( assumes_clause | requires_clause | simple_clause );
	public final AcslParser.behavior_clause_return behavior_clause() throws RecognitionException {
		AcslParser.behavior_clause_return retval = new AcslParser.behavior_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope assumes_clause99 =null;
		ParserRuleReturnScope requires_clause100 =null;
		ParserRuleReturnScope simple_clause101 =null;


		try {
			// AcslParser.g:203:5: ( assumes_clause | requires_clause | simple_clause )
			int alt19=3;
			switch ( input.LA(1) ) {
			case ASSUMES:
				{
				alt19=1;
				}
				break;
			case REQUIRES:
				{
				alt19=2;
				}
				break;
			case ALLOC:
			case ASSIGNS:
			case DEPENDS:
			case ENSURES:
			case FREES:
			case GUARDS:
			case READS:
				{
				alt19=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// AcslParser.g:203:7: assumes_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_assumes_clause_in_behavior_clause1485);
					assumes_clause99=assumes_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, assumes_clause99.getTree());

					}
					break;
				case 2 :
					// AcslParser.g:204:7: requires_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_requires_clause_in_behavior_clause1494);
					requires_clause100=requires_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, requires_clause100.getTree());

					}
					break;
				case 3 :
					// AcslParser.g:205:7: simple_clause
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_simple_clause_in_behavior_clause1502);
					simple_clause101=simple_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_clause101.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "behavior_clause"


	public static class assumes_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assumes_clause"
	// AcslParser.g:208:1: assumes_clause : ASSUMES term -> ^( ASSUMES term ) ;
	public final AcslParser.assumes_clause_return assumes_clause() throws RecognitionException {
		AcslParser.assumes_clause_return retval = new AcslParser.assumes_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ASSUMES102=null;
		ParserRuleReturnScope term103 =null;

		Object ASSUMES102_tree=null;
		RewriteRuleTokenStream stream_ASSUMES=new RewriteRuleTokenStream(adaptor,"token ASSUMES");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			// AcslParser.g:209:5: ( ASSUMES term -> ^( ASSUMES term ) )
			// AcslParser.g:209:7: ASSUMES term
			{
			ASSUMES102=(Token)match(input,ASSUMES,FOLLOW_ASSUMES_in_assumes_clause1519); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_ASSUMES.add(ASSUMES102);

			pushFollow(FOLLOW_term_in_assumes_clause1521);
			term103=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_term.add(term103.getTree());
			// AST REWRITE
			// elements: term, ASSUMES
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 209:20: -> ^( ASSUMES term )
			{
				// AcslParser.g:209:22: ^( ASSUMES term )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_ASSUMES.nextNode(), root_1);
				adaptor.addChild(root_1, stream_term.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assumes_clause"


	public static class completeness_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "completeness_clause"
	// AcslParser.g:212:1: completeness_clause : ( COMPLETE BEHAVIORS id_list -> ^( BEHAVIOR_COMPLETE id_list ) | DISJOINT BEHAVIORS id_list -> ^( BEHAVIOR_DISJOINT id_list ) );
	public final AcslParser.completeness_clause_return completeness_clause() throws RecognitionException {
		AcslParser.completeness_clause_return retval = new AcslParser.completeness_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMPLETE104=null;
		Token BEHAVIORS105=null;
		Token DISJOINT107=null;
		Token BEHAVIORS108=null;
		ParserRuleReturnScope id_list106 =null;
		ParserRuleReturnScope id_list109 =null;

		Object COMPLETE104_tree=null;
		Object BEHAVIORS105_tree=null;
		Object DISJOINT107_tree=null;
		Object BEHAVIORS108_tree=null;
		RewriteRuleTokenStream stream_COMPLETE=new RewriteRuleTokenStream(adaptor,"token COMPLETE");
		RewriteRuleTokenStream stream_DISJOINT=new RewriteRuleTokenStream(adaptor,"token DISJOINT");
		RewriteRuleTokenStream stream_BEHAVIORS=new RewriteRuleTokenStream(adaptor,"token BEHAVIORS");
		RewriteRuleSubtreeStream stream_id_list=new RewriteRuleSubtreeStream(adaptor,"rule id_list");

		try {
			// AcslParser.g:213:5: ( COMPLETE BEHAVIORS id_list -> ^( BEHAVIOR_COMPLETE id_list ) | DISJOINT BEHAVIORS id_list -> ^( BEHAVIOR_DISJOINT id_list ) )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==COMPLETE) ) {
				alt20=1;
			}
			else if ( (LA20_0==DISJOINT) ) {
				alt20=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}

			switch (alt20) {
				case 1 :
					// AcslParser.g:213:7: COMPLETE BEHAVIORS id_list
					{
					COMPLETE104=(Token)match(input,COMPLETE,FOLLOW_COMPLETE_in_completeness_clause1545); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COMPLETE.add(COMPLETE104);

					BEHAVIORS105=(Token)match(input,BEHAVIORS,FOLLOW_BEHAVIORS_in_completeness_clause1547); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_BEHAVIORS.add(BEHAVIORS105);

					pushFollow(FOLLOW_id_list_in_completeness_clause1549);
					id_list106=id_list();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_id_list.add(id_list106.getTree());
					// AST REWRITE
					// elements: id_list
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 213:34: -> ^( BEHAVIOR_COMPLETE id_list )
					{
						// AcslParser.g:213:36: ^( BEHAVIOR_COMPLETE id_list )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BEHAVIOR_COMPLETE, "BEHAVIOR_COMPLETE"), root_1);
						adaptor.addChild(root_1, stream_id_list.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:214:7: DISJOINT BEHAVIORS id_list
					{
					DISJOINT107=(Token)match(input,DISJOINT,FOLLOW_DISJOINT_in_completeness_clause1564); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DISJOINT.add(DISJOINT107);

					BEHAVIORS108=(Token)match(input,BEHAVIORS,FOLLOW_BEHAVIORS_in_completeness_clause1566); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_BEHAVIORS.add(BEHAVIORS108);

					pushFollow(FOLLOW_id_list_in_completeness_clause1568);
					id_list109=id_list();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_id_list.add(id_list109.getTree());
					// AST REWRITE
					// elements: id_list
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 214:34: -> ^( BEHAVIOR_DISJOINT id_list )
					{
						// AcslParser.g:214:36: ^( BEHAVIOR_DISJOINT id_list )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(BEHAVIOR_DISJOINT, "BEHAVIOR_DISJOINT"), root_1);
						adaptor.addChild(root_1, stream_id_list.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "completeness_clause"


	public static class id_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "id_list"
	// AcslParser.g:217:1: id_list : (| ID ( COMMA ID )* -> ^( ID_LIST ( ID )+ ) );
	public final AcslParser.id_list_return id_list() throws RecognitionException {
		AcslParser.id_list_return retval = new AcslParser.id_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID110=null;
		Token COMMA111=null;
		Token ID112=null;

		Object ID110_tree=null;
		Object COMMA111_tree=null;
		Object ID112_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

		try {
			// AcslParser.g:218:5: (| ID ( COMMA ID )* -> ^( ID_LIST ( ID )+ ) )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==EOF||LA22_0==SEMICOL) ) {
				alt22=1;
			}
			else if ( (LA22_0==ID) ) {
				alt22=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// AcslParser.g:219:5: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;
				case 2 :
					// AcslParser.g:219:7: ID ( COMMA ID )*
					{
					ID110=(Token)match(input,ID,FOLLOW_ID_in_id_list1598); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(ID110);

					// AcslParser.g:219:10: ( COMMA ID )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( (LA21_0==COMMA) ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// AcslParser.g:219:11: COMMA ID
							{
							COMMA111=(Token)match(input,COMMA,FOLLOW_COMMA_in_id_list1601); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA111);

							ID112=(Token)match(input,ID,FOLLOW_ID_in_id_list1603); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_ID.add(ID112);

							}
							break;

						default :
							break loop21;
						}
					}

					// AST REWRITE
					// elements: ID
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 219:22: -> ^( ID_LIST ( ID )+ )
					{
						// AcslParser.g:219:24: ^( ID_LIST ( ID )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ID_LIST, "ID_LIST"), root_1);
						if ( !(stream_ID.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_ID.hasNext() ) {
							adaptor.addChild(root_1, stream_ID.nextNode());
						}
						stream_ID.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "id_list"


	public static class primaryExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "primaryExpression"
	// AcslParser.g:223:1: primaryExpression : ( constant | ID | STRING_LITERAL | LCURLY term BAR binders ( SEMICOL term )? RCURLY -> ^( SET_BINDERS term binders ( term )? ) | LCURLY term RCURLY -> ^( SET_SIMPLE term ) | LPAREN term RPAREN -> ^( TERM_PARENTHESIZED term ) );
	public final AcslParser.primaryExpression_return primaryExpression() throws RecognitionException {
		AcslParser.primaryExpression_return retval = new AcslParser.primaryExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ID114=null;
		Token STRING_LITERAL115=null;
		Token LCURLY116=null;
		Token BAR118=null;
		Token SEMICOL120=null;
		Token RCURLY122=null;
		Token LCURLY123=null;
		Token RCURLY125=null;
		Token LPAREN126=null;
		Token RPAREN128=null;
		ParserRuleReturnScope constant113 =null;
		ParserRuleReturnScope term117 =null;
		ParserRuleReturnScope binders119 =null;
		ParserRuleReturnScope term121 =null;
		ParserRuleReturnScope term124 =null;
		ParserRuleReturnScope term127 =null;

		Object ID114_tree=null;
		Object STRING_LITERAL115_tree=null;
		Object LCURLY116_tree=null;
		Object BAR118_tree=null;
		Object SEMICOL120_tree=null;
		Object RCURLY122_tree=null;
		Object LCURLY123_tree=null;
		Object RCURLY125_tree=null;
		Object LPAREN126_tree=null;
		Object RPAREN128_tree=null;
		RewriteRuleTokenStream stream_SEMICOL=new RewriteRuleTokenStream(adaptor,"token SEMICOL");
		RewriteRuleTokenStream stream_BAR=new RewriteRuleTokenStream(adaptor,"token BAR");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
		RewriteRuleSubtreeStream stream_binders=new RewriteRuleSubtreeStream(adaptor,"rule binders");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			// AcslParser.g:224:2: ( constant | ID | STRING_LITERAL | LCURLY term BAR binders ( SEMICOL term )? RCURLY -> ^( SET_BINDERS term binders ( term )? ) | LCURLY term RCURLY -> ^( SET_SIMPLE term ) | LPAREN term RPAREN -> ^( TERM_PARENTHESIZED term ) )
			int alt24=6;
			switch ( input.LA(1) ) {
			case ELLIPSIS:
			case FALSE:
			case FLOATING_CONSTANT:
			case INTEGER_CONSTANT:
			case NOTHING:
			case RESULT:
			case TRUE:
			case CHARACTER_CONSTANT:
				{
				alt24=1;
				}
				break;
			case ID:
				{
				alt24=2;
				}
				break;
			case STRING_LITERAL:
				{
				alt24=3;
				}
				break;
			case LCURLY:
				{
				int LA24_4 = input.LA(2);
				if ( (synpred53_AcslParser()) ) {
					alt24=4;
				}
				else if ( (synpred54_AcslParser()) ) {
					alt24=5;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 24, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case LPAREN:
				{
				alt24=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}
			switch (alt24) {
				case 1 :
					// AcslParser.g:224:4: constant
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_constant_in_primaryExpression1629);
					constant113=constant();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, constant113.getTree());

					}
					break;
				case 2 :
					// AcslParser.g:225:7: ID
					{
					root_0 = (Object)adaptor.nil();


					ID114=(Token)match(input,ID,FOLLOW_ID_in_primaryExpression1637); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ID114_tree = (Object)adaptor.create(ID114);
					adaptor.addChild(root_0, ID114_tree);
					}

					}
					break;
				case 3 :
					// AcslParser.g:226:4: STRING_LITERAL
					{
					root_0 = (Object)adaptor.nil();


					STRING_LITERAL115=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_primaryExpression1642); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STRING_LITERAL115_tree = (Object)adaptor.create(STRING_LITERAL115);
					adaptor.addChild(root_0, STRING_LITERAL115_tree);
					}

					}
					break;
				case 4 :
					// AcslParser.g:227:7: LCURLY term BAR binders ( SEMICOL term )? RCURLY
					{
					LCURLY116=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_primaryExpression1650); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY116);

					pushFollow(FOLLOW_term_in_primaryExpression1652);
					term117=term();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_term.add(term117.getTree());
					BAR118=(Token)match(input,BAR,FOLLOW_BAR_in_primaryExpression1654); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_BAR.add(BAR118);

					pushFollow(FOLLOW_binders_in_primaryExpression1656);
					binders119=binders();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_binders.add(binders119.getTree());
					// AcslParser.g:227:31: ( SEMICOL term )?
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==SEMICOL) ) {
						alt23=1;
					}
					switch (alt23) {
						case 1 :
							// AcslParser.g:227:32: SEMICOL term
							{
							SEMICOL120=(Token)match(input,SEMICOL,FOLLOW_SEMICOL_in_primaryExpression1659); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_SEMICOL.add(SEMICOL120);

							pushFollow(FOLLOW_term_in_primaryExpression1661);
							term121=term();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_term.add(term121.getTree());
							}
							break;

					}

					RCURLY122=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_primaryExpression1665); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY122);

					// AST REWRITE
					// elements: binders, term, term
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 228:9: -> ^( SET_BINDERS term binders ( term )? )
					{
						// AcslParser.g:228:11: ^( SET_BINDERS term binders ( term )? )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET_BINDERS, "SET_BINDERS"), root_1);
						adaptor.addChild(root_1, stream_term.nextTree());
						adaptor.addChild(root_1, stream_binders.nextTree());
						// AcslParser.g:228:38: ( term )?
						if ( stream_term.hasNext() ) {
							adaptor.addChild(root_1, stream_term.nextTree());
						}
						stream_term.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// AcslParser.g:229:7: LCURLY term RCURLY
					{
					LCURLY123=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_primaryExpression1693); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY123);

					pushFollow(FOLLOW_term_in_primaryExpression1695);
					term124=term();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_term.add(term124.getTree());
					RCURLY125=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_primaryExpression1697); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY125);

					// AST REWRITE
					// elements: term
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 230:9: -> ^( SET_SIMPLE term )
					{
						// AcslParser.g:230:11: ^( SET_SIMPLE term )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SET_SIMPLE, "SET_SIMPLE"), root_1);
						adaptor.addChild(root_1, stream_term.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// AcslParser.g:231:4: LPAREN term RPAREN
					{
					LPAREN126=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_primaryExpression1717); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN126);

					pushFollow(FOLLOW_term_in_primaryExpression1719);
					term127=term();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_term.add(term127.getTree());
					RPAREN128=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_primaryExpression1721); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN128);

					// AST REWRITE
					// elements: term
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 232:4: -> ^( TERM_PARENTHESIZED term )
					{
						// AcslParser.g:232:7: ^( TERM_PARENTHESIZED term )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TERM_PARENTHESIZED, "TERM_PARENTHESIZED"), root_1);
						adaptor.addChild(root_1, stream_term.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "primaryExpression"


	public static class postfixExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "postfixExpression"
	// AcslParser.g:236:1: postfixExpression : ( primaryExpression -> primaryExpression ) (l= LSQUARE term RSQUARE -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression term ) RSQUARE ) | LPAREN argumentExpressionList RPAREN -> ^( FUNC_CALL $postfixExpression argumentExpressionList ) | DOT ID -> ^( DOT $postfixExpression ID ) | ARROW ID -> ^( ARROW $postfixExpression ID ) )* ;
	public final AcslParser.postfixExpression_return postfixExpression() throws RecognitionException {
		AcslParser.postfixExpression_return retval = new AcslParser.postfixExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token l=null;
		Token RSQUARE131=null;
		Token LPAREN132=null;
		Token RPAREN134=null;
		Token DOT135=null;
		Token ID136=null;
		Token ARROW137=null;
		Token ID138=null;
		ParserRuleReturnScope primaryExpression129 =null;
		ParserRuleReturnScope term130 =null;
		ParserRuleReturnScope argumentExpressionList133 =null;

		Object l_tree=null;
		Object RSQUARE131_tree=null;
		Object LPAREN132_tree=null;
		Object RPAREN134_tree=null;
		Object DOT135_tree=null;
		Object ID136_tree=null;
		Object ARROW137_tree=null;
		Object ID138_tree=null;
		RewriteRuleTokenStream stream_LSQUARE=new RewriteRuleTokenStream(adaptor,"token LSQUARE");
		RewriteRuleTokenStream stream_RSQUARE=new RewriteRuleTokenStream(adaptor,"token RSQUARE");
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
		RewriteRuleSubtreeStream stream_primaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule primaryExpression");
		RewriteRuleSubtreeStream stream_argumentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule argumentExpressionList");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

		try {
			// AcslParser.g:237:2: ( ( primaryExpression -> primaryExpression ) (l= LSQUARE term RSQUARE -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression term ) RSQUARE ) | LPAREN argumentExpressionList RPAREN -> ^( FUNC_CALL $postfixExpression argumentExpressionList ) | DOT ID -> ^( DOT $postfixExpression ID ) | ARROW ID -> ^( ARROW $postfixExpression ID ) )* )
			// AcslParser.g:237:4: ( primaryExpression -> primaryExpression ) (l= LSQUARE term RSQUARE -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression term ) RSQUARE ) | LPAREN argumentExpressionList RPAREN -> ^( FUNC_CALL $postfixExpression argumentExpressionList ) | DOT ID -> ^( DOT $postfixExpression ID ) | ARROW ID -> ^( ARROW $postfixExpression ID ) )*
			{
			// AcslParser.g:237:4: ( primaryExpression -> primaryExpression )
			// AcslParser.g:237:5: primaryExpression
			{
			pushFollow(FOLLOW_primaryExpression_in_postfixExpression1747);
			primaryExpression129=primaryExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_primaryExpression.add(primaryExpression129.getTree());
			// AST REWRITE
			// elements: primaryExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 237:23: -> primaryExpression
			{
				adaptor.addChild(root_0, stream_primaryExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:239:4: (l= LSQUARE term RSQUARE -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression term ) RSQUARE ) | LPAREN argumentExpressionList RPAREN -> ^( FUNC_CALL $postfixExpression argumentExpressionList ) | DOT ID -> ^( DOT $postfixExpression ID ) | ARROW ID -> ^( ARROW $postfixExpression ID ) )*
			loop25:
			while (true) {
				int alt25=5;
				switch ( input.LA(1) ) {
				case LSQUARE:
					{
					alt25=1;
					}
					break;
				case LPAREN:
					{
					alt25=2;
					}
					break;
				case DOT:
					{
					alt25=3;
					}
					break;
				case ARROW:
					{
					alt25=4;
					}
					break;
				}
				switch (alt25) {
				case 1 :
					// AcslParser.g:239:6: l= LSQUARE term RSQUARE
					{
					l=(Token)match(input,LSQUARE,FOLLOW_LSQUARE_in_postfixExpression1764); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LSQUARE.add(l);

					pushFollow(FOLLOW_term_in_postfixExpression1766);
					term130=term();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_term.add(term130.getTree());
					RSQUARE131=(Token)match(input,RSQUARE,FOLLOW_RSQUARE_in_postfixExpression1768); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RSQUARE.add(RSQUARE131);

					// AST REWRITE
					// elements: RSQUARE, term, postfixExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 240:6: -> ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression term ) RSQUARE )
					{
						// AcslParser.g:240:9: ^( OPERATOR INDEX[$l] ^( ARGUMENT_LIST $postfixExpression term ) RSQUARE )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, (Object)adaptor.create(INDEX, l));
						// AcslParser.g:242:13: ^( ARGUMENT_LIST $postfixExpression term )
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_term.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_1, stream_RSQUARE.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:245:6: LPAREN argumentExpressionList RPAREN
					{
					LPAREN132=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_postfixExpression1842); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN132);

					pushFollow(FOLLOW_argumentExpressionList_in_postfixExpression1844);
					argumentExpressionList133=argumentExpressionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList133.getTree());
					RPAREN134=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_postfixExpression1846); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN134);

					// AST REWRITE
					// elements: postfixExpression, argumentExpressionList
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 246:6: -> ^( FUNC_CALL $postfixExpression argumentExpressionList )
					{
						// AcslParser.g:246:9: ^( FUNC_CALL $postfixExpression argumentExpressionList )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNC_CALL, "FUNC_CALL"), root_1);
						adaptor.addChild(root_1, stream_retval.nextTree());
						adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// AcslParser.g:248:6: DOT ID
					{
					DOT135=(Token)match(input,DOT,FOLLOW_DOT_in_postfixExpression1877); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DOT.add(DOT135);

					ID136=(Token)match(input,ID,FOLLOW_ID_in_postfixExpression1879); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(ID136);

					// AST REWRITE
					// elements: ID, DOT, postfixExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 249:6: -> ^( DOT $postfixExpression ID )
					{
						// AcslParser.g:249:9: ^( DOT $postfixExpression ID )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_DOT.nextNode(), root_1);
						adaptor.addChild(root_1, stream_retval.nextTree());
						adaptor.addChild(root_1, stream_ID.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// AcslParser.g:250:6: ARROW ID
					{
					ARROW137=(Token)match(input,ARROW,FOLLOW_ARROW_in_postfixExpression1902); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ARROW.add(ARROW137);

					ID138=(Token)match(input,ID,FOLLOW_ID_in_postfixExpression1904); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ID.add(ID138);

					// AST REWRITE
					// elements: postfixExpression, ARROW, ID
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 251:6: -> ^( ARROW $postfixExpression ID )
					{
						// AcslParser.g:251:9: ^( ARROW $postfixExpression ID )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_ARROW.nextNode(), root_1);
						adaptor.addChild(root_1, stream_retval.nextTree());
						adaptor.addChild(root_1, stream_ID.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop25;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "postfixExpression"


	public static class argumentExpressionList_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "argumentExpressionList"
	// AcslParser.g:256:1: argumentExpressionList : ( -> ^( ARGUMENT_LIST ) | assignmentExpression ( COMMA assignmentExpression )* -> ^( ARGUMENT_LIST ( assignmentExpression )+ ) );
	public final AcslParser.argumentExpressionList_return argumentExpressionList() throws RecognitionException {
		AcslParser.argumentExpressionList_return retval = new AcslParser.argumentExpressionList_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA140=null;
		ParserRuleReturnScope assignmentExpression139 =null;
		ParserRuleReturnScope assignmentExpression141 =null;

		Object COMMA140_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");

		try {
			// AcslParser.g:257:2: ( -> ^( ARGUMENT_LIST ) | assignmentExpression ( COMMA assignmentExpression )* -> ^( ARGUMENT_LIST ( assignmentExpression )+ ) )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==EOF||LA27_0==COMMA||LA27_0==RPAREN||LA27_0==SEMICOL) ) {
				alt27=1;
			}
			else if ( (LA27_0==AMPERSAND||LA27_0==COMP||LA27_0==ELLIPSIS||LA27_0==EXISTS||LA27_0==FALSE||LA27_0==FLOATING_CONSTANT||LA27_0==FORALL||LA27_0==ID||(LA27_0 >= INTEGER_CONSTANT && LA27_0 <= INTER)||LA27_0==LCURLY||LA27_0==LPAREN||(LA27_0 >= NOT && LA27_0 <= NOTHING)||LA27_0==PLUS||LA27_0==RESULT||(LA27_0 >= SIZEOF && LA27_0 <= STRING_LITERAL)||(LA27_0 >= TRUE && LA27_0 <= UNION)||LA27_0==CHARACTER_CONSTANT||LA27_0==MINUS) ) {
				alt27=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// AcslParser.g:257:4: 
					{
					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 257:4: -> ^( ARGUMENT_LIST )
					{
						// AcslParser.g:257:7: ^( ARGUMENT_LIST )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:258:4: assignmentExpression ( COMMA assignmentExpression )*
					{
					pushFollow(FOLLOW_assignmentExpression_in_argumentExpressionList1948);
					assignmentExpression139=assignmentExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression139.getTree());
					// AcslParser.g:258:25: ( COMMA assignmentExpression )*
					loop26:
					while (true) {
						int alt26=2;
						int LA26_0 = input.LA(1);
						if ( (LA26_0==COMMA) ) {
							int LA26_3 = input.LA(2);
							if ( (synpred60_AcslParser()) ) {
								alt26=1;
							}

						}

						switch (alt26) {
						case 1 :
							// AcslParser.g:258:26: COMMA assignmentExpression
							{
							COMMA140=(Token)match(input,COMMA,FOLLOW_COMMA_in_argumentExpressionList1951); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_COMMA.add(COMMA140);

							pushFollow(FOLLOW_assignmentExpression_in_argumentExpressionList1953);
							assignmentExpression141=assignmentExpression();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression141.getTree());
							}
							break;

						default :
							break loop26;
						}
					}

					// AST REWRITE
					// elements: assignmentExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 259:4: -> ^( ARGUMENT_LIST ( assignmentExpression )+ )
					{
						// AcslParser.g:259:7: ^( ARGUMENT_LIST ( assignmentExpression )+ )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_1);
						if ( !(stream_assignmentExpression.hasNext()) ) {
							throw new RewriteEarlyExitException();
						}
						while ( stream_assignmentExpression.hasNext() ) {
							adaptor.addChild(root_1, stream_assignmentExpression.nextTree());
						}
						stream_assignmentExpression.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "argumentExpressionList"


	public static class unaryExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "unaryExpression"
	// AcslParser.g:263:1: unaryExpression : ( postfixExpression | unary_op castExpression -> ^( OPERATOR unary_op ^( ARGUMENT_LIST castExpression ) ) | ( SIZEOF LPAREN type_expr )=> SIZEOF LPAREN type_expr RPAREN -> ^( SIZEOF_TYPE type_expr ) | SIZEOF unaryExpression -> ^( SIZEOF_EXPR unaryExpression ) | UNION LPAREN argumentExpressionList RPAREN -> ^( UNION argumentExpressionList ) | INTER LPAREN argumentExpressionList RPAREN -> ^( INTER argumentExpressionList ) );
	public final AcslParser.unaryExpression_return unaryExpression() throws RecognitionException {
		AcslParser.unaryExpression_return retval = new AcslParser.unaryExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SIZEOF145=null;
		Token LPAREN146=null;
		Token RPAREN148=null;
		Token SIZEOF149=null;
		Token UNION151=null;
		Token LPAREN152=null;
		Token RPAREN154=null;
		Token INTER155=null;
		Token LPAREN156=null;
		Token RPAREN158=null;
		ParserRuleReturnScope postfixExpression142 =null;
		ParserRuleReturnScope unary_op143 =null;
		ParserRuleReturnScope castExpression144 =null;
		ParserRuleReturnScope type_expr147 =null;
		ParserRuleReturnScope unaryExpression150 =null;
		ParserRuleReturnScope argumentExpressionList153 =null;
		ParserRuleReturnScope argumentExpressionList157 =null;

		Object SIZEOF145_tree=null;
		Object LPAREN146_tree=null;
		Object RPAREN148_tree=null;
		Object SIZEOF149_tree=null;
		Object UNION151_tree=null;
		Object LPAREN152_tree=null;
		Object RPAREN154_tree=null;
		Object INTER155_tree=null;
		Object LPAREN156_tree=null;
		Object RPAREN158_tree=null;
		RewriteRuleTokenStream stream_INTER=new RewriteRuleTokenStream(adaptor,"token INTER");
		RewriteRuleTokenStream stream_SIZEOF=new RewriteRuleTokenStream(adaptor,"token SIZEOF");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_UNION=new RewriteRuleTokenStream(adaptor,"token UNION");
		RewriteRuleSubtreeStream stream_castExpression=new RewriteRuleSubtreeStream(adaptor,"rule castExpression");
		RewriteRuleSubtreeStream stream_type_expr=new RewriteRuleSubtreeStream(adaptor,"rule type_expr");
		RewriteRuleSubtreeStream stream_argumentExpressionList=new RewriteRuleSubtreeStream(adaptor,"rule argumentExpressionList");
		RewriteRuleSubtreeStream stream_unary_op=new RewriteRuleSubtreeStream(adaptor,"rule unary_op");
		RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");

		try {
			// AcslParser.g:264:2: ( postfixExpression | unary_op castExpression -> ^( OPERATOR unary_op ^( ARGUMENT_LIST castExpression ) ) | ( SIZEOF LPAREN type_expr )=> SIZEOF LPAREN type_expr RPAREN -> ^( SIZEOF_TYPE type_expr ) | SIZEOF unaryExpression -> ^( SIZEOF_EXPR unaryExpression ) | UNION LPAREN argumentExpressionList RPAREN -> ^( UNION argumentExpressionList ) | INTER LPAREN argumentExpressionList RPAREN -> ^( INTER argumentExpressionList ) )
			int alt28=6;
			switch ( input.LA(1) ) {
			case ELLIPSIS:
			case FALSE:
			case FLOATING_CONSTANT:
			case ID:
			case INTEGER_CONSTANT:
			case LCURLY:
			case LPAREN:
			case NOTHING:
			case RESULT:
			case STRING_LITERAL:
			case TRUE:
			case CHARACTER_CONSTANT:
				{
				alt28=1;
				}
				break;
			case AMPERSAND:
			case COMP:
			case NOT:
			case PLUS:
			case STAR:
			case MINUS:
				{
				alt28=2;
				}
				break;
			case SIZEOF:
				{
				int LA28_3 = input.LA(2);
				if ( (LA28_3==LPAREN) ) {
					int LA28_6 = input.LA(3);
					if ( (LA28_6==BOOLEAN||LA28_6==REAL||LA28_6==INTGER) && (synpred63_AcslParser())) {
						alt28=3;
					}
					else if ( (LA28_6==ID) ) {
						int LA28_9 = input.LA(4);
						if ( (LA28_9==RPAREN) ) {
							int LA28_11 = input.LA(5);
							if ( (synpred63_AcslParser()) ) {
								alt28=3;
							}
							else if ( (synpred64_AcslParser()) ) {
								alt28=4;
							}

							else {
								if (state.backtracking>0) {state.failed=true; return retval;}
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 28, 11, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}
						else if ( (LA28_9==AMPERSAND||(LA28_9 >= ARROW && LA28_9 <= ASSIGN)||LA28_9==BAR||LA28_9==BITXOR||(LA28_9 >= DOT && LA28_9 <= DOTDOT)||LA28_9==EQ||(LA28_9 >= GT && LA28_9 <= GTE)||LA28_9==IMPLY||LA28_9==LAND||(LA28_9 >= LOR && LA28_9 <= LTE)||(LA28_9 >= MOD && LA28_9 <= NEQ)||LA28_9==PLUS||LA28_9==QUESTION||(LA28_9 >= SHIFTLEFT && LA28_9 <= SHIFTRIGHT)||LA28_9==STAR||LA28_9==SUB||LA28_9==DIV) ) {
							alt28=4;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 28, 9, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA28_6==CHAR||LA28_6==DOUBLE||LA28_6==FLOAT||LA28_6==INT||LA28_6==LONG||LA28_6==SHORT||LA28_6==VOID) && (synpred63_AcslParser())) {
						alt28=3;
					}
					else if ( (LA28_6==AMPERSAND||LA28_6==COMP||LA28_6==ELLIPSIS||LA28_6==EXISTS||LA28_6==FALSE||LA28_6==FLOATING_CONSTANT||LA28_6==FORALL||(LA28_6 >= INTEGER_CONSTANT && LA28_6 <= INTER)||LA28_6==LCURLY||LA28_6==LPAREN||(LA28_6 >= NOT && LA28_6 <= NOTHING)||LA28_6==PLUS||LA28_6==RESULT||(LA28_6 >= SIZEOF && LA28_6 <= STRING_LITERAL)||(LA28_6 >= TRUE && LA28_6 <= UNION)||LA28_6==CHARACTER_CONSTANT||LA28_6==MINUS) ) {
						alt28=4;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 28, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA28_3==AMPERSAND||LA28_3==COMP||LA28_3==ELLIPSIS||LA28_3==FALSE||LA28_3==FLOATING_CONSTANT||LA28_3==ID||(LA28_3 >= INTEGER_CONSTANT && LA28_3 <= INTER)||LA28_3==LCURLY||(LA28_3 >= NOT && LA28_3 <= NOTHING)||LA28_3==PLUS||LA28_3==RESULT||(LA28_3 >= SIZEOF && LA28_3 <= STRING_LITERAL)||(LA28_3 >= TRUE && LA28_3 <= UNION)||LA28_3==CHARACTER_CONSTANT||LA28_3==MINUS) ) {
					alt28=4;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 28, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case UNION:
				{
				alt28=5;
				}
				break;
			case INTER:
				{
				alt28=6;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}
			switch (alt28) {
				case 1 :
					// AcslParser.g:264:4: postfixExpression
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_postfixExpression_in_unaryExpression1981);
					postfixExpression142=postfixExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, postfixExpression142.getTree());

					}
					break;
				case 2 :
					// AcslParser.g:265:4: unary_op castExpression
					{
					pushFollow(FOLLOW_unary_op_in_unaryExpression1986);
					unary_op143=unary_op();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_unary_op.add(unary_op143.getTree());
					pushFollow(FOLLOW_castExpression_in_unaryExpression1988);
					castExpression144=castExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_castExpression.add(castExpression144.getTree());
					// AST REWRITE
					// elements: unary_op, castExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 266:4: -> ^( OPERATOR unary_op ^( ARGUMENT_LIST castExpression ) )
					{
						// AcslParser.g:266:7: ^( OPERATOR unary_op ^( ARGUMENT_LIST castExpression ) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_unary_op.nextTree());
						// AcslParser.g:266:27: ^( ARGUMENT_LIST castExpression )
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_castExpression.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// AcslParser.g:267:4: ( SIZEOF LPAREN type_expr )=> SIZEOF LPAREN type_expr RPAREN
					{
					SIZEOF145=(Token)match(input,SIZEOF,FOLLOW_SIZEOF_in_unaryExpression2019); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SIZEOF.add(SIZEOF145);

					LPAREN146=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_unaryExpression2021); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN146);

					pushFollow(FOLLOW_type_expr_in_unaryExpression2023);
					type_expr147=type_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_type_expr.add(type_expr147.getTree());
					RPAREN148=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_unaryExpression2025); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN148);

					// AST REWRITE
					// elements: type_expr
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 268:4: -> ^( SIZEOF_TYPE type_expr )
					{
						// AcslParser.g:268:7: ^( SIZEOF_TYPE type_expr )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SIZEOF_TYPE, "SIZEOF_TYPE"), root_1);
						adaptor.addChild(root_1, stream_type_expr.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 4 :
					// AcslParser.g:269:4: SIZEOF unaryExpression
					{
					SIZEOF149=(Token)match(input,SIZEOF,FOLLOW_SIZEOF_in_unaryExpression2041); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SIZEOF.add(SIZEOF149);

					pushFollow(FOLLOW_unaryExpression_in_unaryExpression2043);
					unaryExpression150=unaryExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression150.getTree());
					// AST REWRITE
					// elements: unaryExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 270:4: -> ^( SIZEOF_EXPR unaryExpression )
					{
						// AcslParser.g:270:7: ^( SIZEOF_EXPR unaryExpression )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SIZEOF_EXPR, "SIZEOF_EXPR"), root_1);
						adaptor.addChild(root_1, stream_unaryExpression.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 5 :
					// AcslParser.g:272:7: UNION LPAREN argumentExpressionList RPAREN
					{
					UNION151=(Token)match(input,UNION,FOLLOW_UNION_in_unaryExpression2064); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_UNION.add(UNION151);

					LPAREN152=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_unaryExpression2066); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN152);

					pushFollow(FOLLOW_argumentExpressionList_in_unaryExpression2068);
					argumentExpressionList153=argumentExpressionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList153.getTree());
					RPAREN154=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_unaryExpression2070); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN154);

					// AST REWRITE
					// elements: UNION, argumentExpressionList
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 273:9: -> ^( UNION argumentExpressionList )
					{
						// AcslParser.g:273:12: ^( UNION argumentExpressionList )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_UNION.nextNode(), root_1);
						adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 6 :
					// AcslParser.g:274:7: INTER LPAREN argumentExpressionList RPAREN
					{
					INTER155=(Token)match(input,INTER,FOLLOW_INTER_in_unaryExpression2094); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_INTER.add(INTER155);

					LPAREN156=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_unaryExpression2096); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN156);

					pushFollow(FOLLOW_argumentExpressionList_in_unaryExpression2098);
					argumentExpressionList157=argumentExpressionList();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_argumentExpressionList.add(argumentExpressionList157.getTree());
					RPAREN158=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_unaryExpression2100); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN158);

					// AST REWRITE
					// elements: INTER, argumentExpressionList
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 275:9: -> ^( INTER argumentExpressionList )
					{
						// AcslParser.g:275:12: ^( INTER argumentExpressionList )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_INTER.nextNode(), root_1);
						adaptor.addChild(root_1, stream_argumentExpressionList.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "unaryExpression"


	public static class castExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "castExpression"
	// AcslParser.g:290:1: castExpression : ( ( LPAREN type_expr RPAREN )=>l= LPAREN type_expr RPAREN castExpression -> ^( CAST type_expr castExpression ) | unaryExpression );
	public final AcslParser.castExpression_return castExpression() throws RecognitionException {
		AcslParser.castExpression_return retval = new AcslParser.castExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token l=null;
		Token RPAREN160=null;
		ParserRuleReturnScope type_expr159 =null;
		ParserRuleReturnScope castExpression161 =null;
		ParserRuleReturnScope unaryExpression162 =null;

		Object l_tree=null;
		Object RPAREN160_tree=null;
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleSubtreeStream stream_castExpression=new RewriteRuleSubtreeStream(adaptor,"rule castExpression");
		RewriteRuleSubtreeStream stream_type_expr=new RewriteRuleSubtreeStream(adaptor,"rule type_expr");

		try {
			// AcslParser.g:291:2: ( ( LPAREN type_expr RPAREN )=>l= LPAREN type_expr RPAREN castExpression -> ^( CAST type_expr castExpression ) | unaryExpression )
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==LPAREN) ) {
				int LA29_1 = input.LA(2);
				if ( (synpred66_AcslParser()) ) {
					alt29=1;
				}
				else if ( (true) ) {
					alt29=2;
				}

			}
			else if ( (LA29_0==AMPERSAND||LA29_0==COMP||LA29_0==ELLIPSIS||LA29_0==FALSE||LA29_0==FLOATING_CONSTANT||LA29_0==ID||(LA29_0 >= INTEGER_CONSTANT && LA29_0 <= INTER)||LA29_0==LCURLY||(LA29_0 >= NOT && LA29_0 <= NOTHING)||LA29_0==PLUS||LA29_0==RESULT||(LA29_0 >= SIZEOF && LA29_0 <= STRING_LITERAL)||(LA29_0 >= TRUE && LA29_0 <= UNION)||LA29_0==CHARACTER_CONSTANT||LA29_0==MINUS) ) {
				alt29=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}

			switch (alt29) {
				case 1 :
					// AcslParser.g:291:4: ( LPAREN type_expr RPAREN )=>l= LPAREN type_expr RPAREN castExpression
					{
					l=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_castExpression2161); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(l);

					pushFollow(FOLLOW_type_expr_in_castExpression2163);
					type_expr159=type_expr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_type_expr.add(type_expr159.getTree());
					RPAREN160=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_castExpression2165); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN160);

					pushFollow(FOLLOW_castExpression_in_castExpression2167);
					castExpression161=castExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_castExpression.add(castExpression161.getTree());
					// AST REWRITE
					// elements: type_expr, castExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 292:4: -> ^( CAST type_expr castExpression )
					{
						// AcslParser.g:292:7: ^( CAST type_expr castExpression )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CAST, "CAST"), root_1);
						adaptor.addChild(root_1, stream_type_expr.nextTree());
						adaptor.addChild(root_1, stream_castExpression.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:293:4: unaryExpression
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_unaryExpression_in_castExpression2185);
					unaryExpression162=unaryExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression162.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "castExpression"


	public static class multiplicativeExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "multiplicativeExpression"
	// AcslParser.g:297:1: multiplicativeExpression : ( castExpression -> castExpression ) ( STAR y= castExpression -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | DIV y= castExpression -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | MOD y= castExpression -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) ) )* ;
	public final AcslParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
		AcslParser.multiplicativeExpression_return retval = new AcslParser.multiplicativeExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STAR164=null;
		Token DIV165=null;
		Token MOD166=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope castExpression163 =null;

		Object STAR164_tree=null;
		Object DIV165_tree=null;
		Object MOD166_tree=null;
		RewriteRuleTokenStream stream_DIV=new RewriteRuleTokenStream(adaptor,"token DIV");
		RewriteRuleTokenStream stream_MOD=new RewriteRuleTokenStream(adaptor,"token MOD");
		RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
		RewriteRuleSubtreeStream stream_castExpression=new RewriteRuleSubtreeStream(adaptor,"rule castExpression");

		try {
			// AcslParser.g:298:2: ( ( castExpression -> castExpression ) ( STAR y= castExpression -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | DIV y= castExpression -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | MOD y= castExpression -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) ) )* )
			// AcslParser.g:298:4: ( castExpression -> castExpression ) ( STAR y= castExpression -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | DIV y= castExpression -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | MOD y= castExpression -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) ) )*
			{
			// AcslParser.g:298:4: ( castExpression -> castExpression )
			// AcslParser.g:298:5: castExpression
			{
			pushFollow(FOLLOW_castExpression_in_multiplicativeExpression2199);
			castExpression163=castExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_castExpression.add(castExpression163.getTree());
			// AST REWRITE
			// elements: castExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 298:20: -> castExpression
			{
				adaptor.addChild(root_0, stream_castExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:299:2: ( STAR y= castExpression -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | DIV y= castExpression -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) ) | MOD y= castExpression -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) ) )*
			loop30:
			while (true) {
				int alt30=4;
				switch ( input.LA(1) ) {
				case STAR:
					{
					alt30=1;
					}
					break;
				case DIV:
					{
					alt30=2;
					}
					break;
				case MOD:
					{
					alt30=3;
					}
					break;
				}
				switch (alt30) {
				case 1 :
					// AcslParser.g:299:4: STAR y= castExpression
					{
					STAR164=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicativeExpression2209); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_STAR.add(STAR164);

					pushFollow(FOLLOW_castExpression_in_multiplicativeExpression2213);
					y=castExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_castExpression.add(y.getTree());
					// AST REWRITE
					// elements: STAR, multiplicativeExpression, y
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 300:4: -> ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) )
					{
						// AcslParser.g:300:7: ^( OPERATOR STAR ^( ARGUMENT_LIST $multiplicativeExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_STAR.nextNode());
						// AcslParser.g:300:23: ^( ARGUMENT_LIST $multiplicativeExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:301:4: DIV y= castExpression
					{
					DIV165=(Token)match(input,DIV,FOLLOW_DIV_in_multiplicativeExpression2239); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DIV.add(DIV165);

					pushFollow(FOLLOW_castExpression_in_multiplicativeExpression2243);
					y=castExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_castExpression.add(y.getTree());
					// AST REWRITE
					// elements: multiplicativeExpression, DIV, y
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 302:4: -> ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) )
					{
						// AcslParser.g:302:7: ^( OPERATOR DIV ^( ARGUMENT_LIST $multiplicativeExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_DIV.nextNode());
						// AcslParser.g:302:22: ^( ARGUMENT_LIST $multiplicativeExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 3 :
					// AcslParser.g:303:7: MOD y= castExpression
					{
					MOD166=(Token)match(input,MOD,FOLLOW_MOD_in_multiplicativeExpression2272); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_MOD.add(MOD166);

					pushFollow(FOLLOW_castExpression_in_multiplicativeExpression2276);
					y=castExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_castExpression.add(y.getTree());
					// AST REWRITE
					// elements: y, multiplicativeExpression, MOD
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 304:4: -> ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) )
					{
						// AcslParser.g:304:7: ^( OPERATOR MOD ^( ARGUMENT_LIST $multiplicativeExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_MOD.nextNode());
						// AcslParser.g:304:22: ^( ARGUMENT_LIST $multiplicativeExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop30;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multiplicativeExpression"


	public static class additiveExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "additiveExpression"
	// AcslParser.g:309:1: additiveExpression : ( multiplicativeExpression -> multiplicativeExpression ) ( PLUS y= multiplicativeExpression -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) ) | SUB y= multiplicativeExpression -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) ) )* ;
	public final AcslParser.additiveExpression_return additiveExpression() throws RecognitionException {
		AcslParser.additiveExpression_return retval = new AcslParser.additiveExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PLUS168=null;
		Token SUB169=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope multiplicativeExpression167 =null;

		Object PLUS168_tree=null;
		Object SUB169_tree=null;
		RewriteRuleTokenStream stream_SUB=new RewriteRuleTokenStream(adaptor,"token SUB");
		RewriteRuleTokenStream stream_PLUS=new RewriteRuleTokenStream(adaptor,"token PLUS");
		RewriteRuleSubtreeStream stream_multiplicativeExpression=new RewriteRuleSubtreeStream(adaptor,"rule multiplicativeExpression");

		try {
			// AcslParser.g:310:2: ( ( multiplicativeExpression -> multiplicativeExpression ) ( PLUS y= multiplicativeExpression -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) ) | SUB y= multiplicativeExpression -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) ) )* )
			// AcslParser.g:310:4: ( multiplicativeExpression -> multiplicativeExpression ) ( PLUS y= multiplicativeExpression -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) ) | SUB y= multiplicativeExpression -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) ) )*
			{
			// AcslParser.g:310:4: ( multiplicativeExpression -> multiplicativeExpression )
			// AcslParser.g:310:5: multiplicativeExpression
			{
			pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression2318);
			multiplicativeExpression167=multiplicativeExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_multiplicativeExpression.add(multiplicativeExpression167.getTree());
			// AST REWRITE
			// elements: multiplicativeExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 310:30: -> multiplicativeExpression
			{
				adaptor.addChild(root_0, stream_multiplicativeExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:311:9: ( PLUS y= multiplicativeExpression -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) ) | SUB y= multiplicativeExpression -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) ) )*
			loop31:
			while (true) {
				int alt31=3;
				int LA31_0 = input.LA(1);
				if ( (LA31_0==PLUS) ) {
					alt31=1;
				}
				else if ( (LA31_0==SUB) ) {
					alt31=2;
				}

				switch (alt31) {
				case 1 :
					// AcslParser.g:311:11: PLUS y= multiplicativeExpression
					{
					PLUS168=(Token)match(input,PLUS,FOLLOW_PLUS_in_additiveExpression2335); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_PLUS.add(PLUS168);

					pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression2339);
					y=multiplicativeExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_multiplicativeExpression.add(y.getTree());
					// AST REWRITE
					// elements: y, additiveExpression, PLUS
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 312:11: -> ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) )
					{
						// AcslParser.g:312:14: ^( OPERATOR PLUS ^( ARGUMENT_LIST $additiveExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_PLUS.nextNode());
						// AcslParser.g:312:30: ^( ARGUMENT_LIST $additiveExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:313:11: SUB y= multiplicativeExpression
					{
					SUB169=(Token)match(input,SUB,FOLLOW_SUB_in_additiveExpression2379); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SUB.add(SUB169);

					pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression2383);
					y=multiplicativeExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_multiplicativeExpression.add(y.getTree());
					// AST REWRITE
					// elements: additiveExpression, y, SUB
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 314:11: -> ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) )
					{
						// AcslParser.g:314:14: ^( OPERATOR SUB ^( ARGUMENT_LIST $additiveExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_SUB.nextNode());
						// AcslParser.g:314:29: ^( ARGUMENT_LIST $additiveExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop31;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "additiveExpression"


	public static class rangeExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "rangeExpression"
	// AcslParser.g:320:1: rangeExpression : ( additiveExpression -> additiveExpression ) ( DOTDOT y= additiveExpression ( -> ^( DOTDOT $rangeExpression $y) | HASH z= additiveExpression -> ^( DOTDOT $rangeExpression $y $z) ) )? ;
	public final AcslParser.rangeExpression_return rangeExpression() throws RecognitionException {
		AcslParser.rangeExpression_return retval = new AcslParser.rangeExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token DOTDOT171=null;
		Token HASH172=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope z =null;
		ParserRuleReturnScope additiveExpression170 =null;

		Object DOTDOT171_tree=null;
		Object HASH172_tree=null;
		RewriteRuleTokenStream stream_DOTDOT=new RewriteRuleTokenStream(adaptor,"token DOTDOT");
		RewriteRuleTokenStream stream_HASH=new RewriteRuleTokenStream(adaptor,"token HASH");
		RewriteRuleSubtreeStream stream_additiveExpression=new RewriteRuleSubtreeStream(adaptor,"rule additiveExpression");

		try {
			// AcslParser.g:321:2: ( ( additiveExpression -> additiveExpression ) ( DOTDOT y= additiveExpression ( -> ^( DOTDOT $rangeExpression $y) | HASH z= additiveExpression -> ^( DOTDOT $rangeExpression $y $z) ) )? )
			// AcslParser.g:321:4: ( additiveExpression -> additiveExpression ) ( DOTDOT y= additiveExpression ( -> ^( DOTDOT $rangeExpression $y) | HASH z= additiveExpression -> ^( DOTDOT $rangeExpression $y $z) ) )?
			{
			// AcslParser.g:321:4: ( additiveExpression -> additiveExpression )
			// AcslParser.g:321:5: additiveExpression
			{
			pushFollow(FOLLOW_additiveExpression_in_rangeExpression2437);
			additiveExpression170=additiveExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_additiveExpression.add(additiveExpression170.getTree());
			// AST REWRITE
			// elements: additiveExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 321:24: -> additiveExpression
			{
				adaptor.addChild(root_0, stream_additiveExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:322:7: ( DOTDOT y= additiveExpression ( -> ^( DOTDOT $rangeExpression $y) | HASH z= additiveExpression -> ^( DOTDOT $rangeExpression $y $z) ) )?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==DOTDOT) ) {
				alt33=1;
			}
			switch (alt33) {
				case 1 :
					// AcslParser.g:322:9: DOTDOT y= additiveExpression ( -> ^( DOTDOT $rangeExpression $y) | HASH z= additiveExpression -> ^( DOTDOT $rangeExpression $y $z) )
					{
					DOTDOT171=(Token)match(input,DOTDOT,FOLLOW_DOTDOT_in_rangeExpression2452); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_DOTDOT.add(DOTDOT171);

					pushFollow(FOLLOW_additiveExpression_in_rangeExpression2456);
					y=additiveExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_additiveExpression.add(y.getTree());
					// AcslParser.g:323:9: ( -> ^( DOTDOT $rangeExpression $y) | HASH z= additiveExpression -> ^( DOTDOT $rangeExpression $y $z) )
					int alt32=2;
					int LA32_0 = input.LA(1);
					if ( (LA32_0==EOF||LA32_0==AMPERSAND||LA32_0==BAR||LA32_0==BITXOR||(LA32_0 >= COLON && LA32_0 <= COMMA)||LA32_0==EQ||(LA32_0 >= GT && LA32_0 <= GTE)||LA32_0==IMPLY||LA32_0==LAND||LA32_0==LOR||(LA32_0 >= LT && LA32_0 <= LTE)||LA32_0==NEQ||LA32_0==QUESTION||LA32_0==RCURLY||(LA32_0 >= RPAREN && LA32_0 <= RSQUARE)||(LA32_0 >= SEMICOL && LA32_0 <= SHIFTRIGHT)) ) {
						alt32=1;
					}
					else if ( (LA32_0==HASH) ) {
						alt32=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 32, 0, input);
						throw nvae;
					}

					switch (alt32) {
						case 1 :
							// AcslParser.g:323:11: 
							{
							// AST REWRITE
							// elements: y, rangeExpression, DOTDOT
							// token labels: 
							// rule labels: y, retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (Object)adaptor.nil();
							// 323:11: -> ^( DOTDOT $rangeExpression $y)
							{
								// AcslParser.g:323:14: ^( DOTDOT $rangeExpression $y)
								{
								Object root_1 = (Object)adaptor.nil();
								root_1 = (Object)adaptor.becomeRoot(stream_DOTDOT.nextNode(), root_1);
								adaptor.addChild(root_1, stream_retval.nextTree());
								adaptor.addChild(root_1, stream_y.nextTree());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;
							}

							}
							break;
						case 2 :
							// AcslParser.g:324:11: HASH z= additiveExpression
							{
							HASH172=(Token)match(input,HASH,FOLLOW_HASH_in_rangeExpression2490); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_HASH.add(HASH172);

							pushFollow(FOLLOW_additiveExpression_in_rangeExpression2494);
							z=additiveExpression();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_additiveExpression.add(z.getTree());
							// AST REWRITE
							// elements: DOTDOT, y, rangeExpression, z
							// token labels: 
							// rule labels: y, z, retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
							RewriteRuleSubtreeStream stream_z=new RewriteRuleSubtreeStream(adaptor,"rule z",z!=null?z.getTree():null);
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (Object)adaptor.nil();
							// 325:11: -> ^( DOTDOT $rangeExpression $y $z)
							{
								// AcslParser.g:325:14: ^( DOTDOT $rangeExpression $y $z)
								{
								Object root_1 = (Object)adaptor.nil();
								root_1 = (Object)adaptor.becomeRoot(stream_DOTDOT.nextNode(), root_1);
								adaptor.addChild(root_1, stream_retval.nextTree());
								adaptor.addChild(root_1, stream_y.nextTree());
								adaptor.addChild(root_1, stream_z.nextTree());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;
							}

							}
							break;

					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "rangeExpression"


	public static class shiftExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "shiftExpression"
	// AcslParser.g:331:1: shiftExpression : ( rangeExpression -> rangeExpression ) ( SHIFTLEFT y= rangeExpression -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) ) | SHIFTRIGHT y= rangeExpression -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) ) )* ;
	public final AcslParser.shiftExpression_return shiftExpression() throws RecognitionException {
		AcslParser.shiftExpression_return retval = new AcslParser.shiftExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SHIFTLEFT174=null;
		Token SHIFTRIGHT175=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope rangeExpression173 =null;

		Object SHIFTLEFT174_tree=null;
		Object SHIFTRIGHT175_tree=null;
		RewriteRuleTokenStream stream_SHIFTLEFT=new RewriteRuleTokenStream(adaptor,"token SHIFTLEFT");
		RewriteRuleTokenStream stream_SHIFTRIGHT=new RewriteRuleTokenStream(adaptor,"token SHIFTRIGHT");
		RewriteRuleSubtreeStream stream_rangeExpression=new RewriteRuleSubtreeStream(adaptor,"rule rangeExpression");

		try {
			// AcslParser.g:332:2: ( ( rangeExpression -> rangeExpression ) ( SHIFTLEFT y= rangeExpression -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) ) | SHIFTRIGHT y= rangeExpression -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) ) )* )
			// AcslParser.g:332:4: ( rangeExpression -> rangeExpression ) ( SHIFTLEFT y= rangeExpression -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) ) | SHIFTRIGHT y= rangeExpression -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) ) )*
			{
			// AcslParser.g:332:4: ( rangeExpression -> rangeExpression )
			// AcslParser.g:332:5: rangeExpression
			{
			pushFollow(FOLLOW_rangeExpression_in_shiftExpression2555);
			rangeExpression173=rangeExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_rangeExpression.add(rangeExpression173.getTree());
			// AST REWRITE
			// elements: rangeExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 332:21: -> rangeExpression
			{
				adaptor.addChild(root_0, stream_rangeExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:333:9: ( SHIFTLEFT y= rangeExpression -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) ) | SHIFTRIGHT y= rangeExpression -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) ) )*
			loop34:
			while (true) {
				int alt34=3;
				int LA34_0 = input.LA(1);
				if ( (LA34_0==SHIFTLEFT) ) {
					alt34=1;
				}
				else if ( (LA34_0==SHIFTRIGHT) ) {
					alt34=2;
				}

				switch (alt34) {
				case 1 :
					// AcslParser.g:333:11: SHIFTLEFT y= rangeExpression
					{
					SHIFTLEFT174=(Token)match(input,SHIFTLEFT,FOLLOW_SHIFTLEFT_in_shiftExpression2572); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SHIFTLEFT.add(SHIFTLEFT174);

					pushFollow(FOLLOW_rangeExpression_in_shiftExpression2576);
					y=rangeExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_rangeExpression.add(y.getTree());
					// AST REWRITE
					// elements: shiftExpression, SHIFTLEFT, y
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 334:11: -> ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) )
					{
						// AcslParser.g:334:14: ^( OPERATOR SHIFTLEFT ^( ARGUMENT_LIST $shiftExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_SHIFTLEFT.nextNode());
						// AcslParser.g:334:35: ^( ARGUMENT_LIST $shiftExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:335:11: SHIFTRIGHT y= rangeExpression
					{
					SHIFTRIGHT175=(Token)match(input,SHIFTRIGHT,FOLLOW_SHIFTRIGHT_in_shiftExpression2616); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_SHIFTRIGHT.add(SHIFTRIGHT175);

					pushFollow(FOLLOW_rangeExpression_in_shiftExpression2620);
					y=rangeExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_rangeExpression.add(y.getTree());
					// AST REWRITE
					// elements: SHIFTRIGHT, y, shiftExpression
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 336:11: -> ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) )
					{
						// AcslParser.g:336:14: ^( OPERATOR SHIFTRIGHT ^( ARGUMENT_LIST $shiftExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_SHIFTRIGHT.nextNode());
						// AcslParser.g:336:36: ^( ARGUMENT_LIST $shiftExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop34;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "shiftExpression"


	public static class relationalExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "relationalExpression"
	// AcslParser.g:341:1: relationalExpression : ( shiftExpression -> shiftExpression ) ( relationalOperator y= shiftExpression -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) ) )* ;
	public final AcslParser.relationalExpression_return relationalExpression() throws RecognitionException {
		AcslParser.relationalExpression_return retval = new AcslParser.relationalExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope y =null;
		ParserRuleReturnScope shiftExpression176 =null;
		ParserRuleReturnScope relationalOperator177 =null;

		RewriteRuleSubtreeStream stream_shiftExpression=new RewriteRuleSubtreeStream(adaptor,"rule shiftExpression");
		RewriteRuleSubtreeStream stream_relationalOperator=new RewriteRuleSubtreeStream(adaptor,"rule relationalOperator");

		try {
			// AcslParser.g:342:2: ( ( shiftExpression -> shiftExpression ) ( relationalOperator y= shiftExpression -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) ) )* )
			// AcslParser.g:342:4: ( shiftExpression -> shiftExpression ) ( relationalOperator y= shiftExpression -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) ) )*
			{
			// AcslParser.g:342:4: ( shiftExpression -> shiftExpression )
			// AcslParser.g:342:6: shiftExpression
			{
			pushFollow(FOLLOW_shiftExpression_in_relationalExpression2674);
			shiftExpression176=shiftExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_shiftExpression.add(shiftExpression176.getTree());
			// AST REWRITE
			// elements: shiftExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 342:22: -> shiftExpression
			{
				adaptor.addChild(root_0, stream_shiftExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:343:4: ( relationalOperator y= shiftExpression -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) ) )*
			loop35:
			while (true) {
				int alt35=2;
				int LA35_0 = input.LA(1);
				if ( ((LA35_0 >= GT && LA35_0 <= GTE)||(LA35_0 >= LT && LA35_0 <= LTE)) ) {
					alt35=1;
				}

				switch (alt35) {
				case 1 :
					// AcslParser.g:343:6: relationalOperator y= shiftExpression
					{
					pushFollow(FOLLOW_relationalOperator_in_relationalExpression2687);
					relationalOperator177=relationalOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_relationalOperator.add(relationalOperator177.getTree());
					pushFollow(FOLLOW_shiftExpression_in_relationalExpression2691);
					y=shiftExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_shiftExpression.add(y.getTree());
					// AST REWRITE
					// elements: relationalExpression, y, relationalOperator
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 344:6: -> ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) )
					{
						// AcslParser.g:344:9: ^( OPERATOR relationalOperator ^( ARGUMENT_LIST $relationalExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_relationalOperator.nextTree());
						// AcslParser.g:344:39: ^( ARGUMENT_LIST $relationalExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop35;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "relationalExpression"


	public static class relationalOperator_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "relationalOperator"
	// AcslParser.g:348:1: relationalOperator : ( LT | GT | LTE | GTE );
	public final AcslParser.relationalOperator_return relationalOperator() throws RecognitionException {
		AcslParser.relationalOperator_return retval = new AcslParser.relationalOperator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set178=null;

		Object set178_tree=null;

		try {
			// AcslParser.g:349:2: ( LT | GT | LTE | GTE )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set178=input.LT(1);
			if ( (input.LA(1) >= GT && input.LA(1) <= GTE)||(input.LA(1) >= LT && input.LA(1) <= LTE) ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set178));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "relationalOperator"


	public static class equalityExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "equalityExpression"
	// AcslParser.g:353:1: equalityExpression : ( relationalExpression -> relationalExpression ) ( equalityOperator y= relationalExpression -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) ) )* ;
	public final AcslParser.equalityExpression_return equalityExpression() throws RecognitionException {
		AcslParser.equalityExpression_return retval = new AcslParser.equalityExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope y =null;
		ParserRuleReturnScope relationalExpression179 =null;
		ParserRuleReturnScope equalityOperator180 =null;

		RewriteRuleSubtreeStream stream_equalityOperator=new RewriteRuleSubtreeStream(adaptor,"rule equalityOperator");
		RewriteRuleSubtreeStream stream_relationalExpression=new RewriteRuleSubtreeStream(adaptor,"rule relationalExpression");

		try {
			// AcslParser.g:354:2: ( ( relationalExpression -> relationalExpression ) ( equalityOperator y= relationalExpression -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) ) )* )
			// AcslParser.g:354:4: ( relationalExpression -> relationalExpression ) ( equalityOperator y= relationalExpression -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) ) )*
			{
			// AcslParser.g:354:4: ( relationalExpression -> relationalExpression )
			// AcslParser.g:354:6: relationalExpression
			{
			pushFollow(FOLLOW_relationalExpression_in_equalityExpression2758);
			relationalExpression179=relationalExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_relationalExpression.add(relationalExpression179.getTree());
			// AST REWRITE
			// elements: relationalExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 354:27: -> relationalExpression
			{
				adaptor.addChild(root_0, stream_relationalExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:355:4: ( equalityOperator y= relationalExpression -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) ) )*
			loop36:
			while (true) {
				int alt36=2;
				int LA36_0 = input.LA(1);
				if ( (LA36_0==EQ||LA36_0==NEQ) ) {
					alt36=1;
				}

				switch (alt36) {
				case 1 :
					// AcslParser.g:355:6: equalityOperator y= relationalExpression
					{
					pushFollow(FOLLOW_equalityOperator_in_equalityExpression2771);
					equalityOperator180=equalityOperator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_equalityOperator.add(equalityOperator180.getTree());
					pushFollow(FOLLOW_relationalExpression_in_equalityExpression2775);
					y=relationalExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_relationalExpression.add(y.getTree());
					// AST REWRITE
					// elements: equalityExpression, equalityOperator, y
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 356:6: -> ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) )
					{
						// AcslParser.g:356:9: ^( OPERATOR equalityOperator ^( ARGUMENT_LIST $equalityExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_equalityOperator.nextTree());
						// AcslParser.g:356:37: ^( ARGUMENT_LIST $equalityExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop36;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "equalityExpression"


	public static class equalityOperator_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "equalityOperator"
	// AcslParser.g:360:1: equalityOperator : ( EQ | NEQ );
	public final AcslParser.equalityOperator_return equalityOperator() throws RecognitionException {
		AcslParser.equalityOperator_return retval = new AcslParser.equalityOperator_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set181=null;

		Object set181_tree=null;

		try {
			// AcslParser.g:361:2: ( EQ | NEQ )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set181=input.LT(1);
			if ( input.LA(1)==EQ||input.LA(1)==NEQ ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set181));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "equalityOperator"


	public static class andExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "andExpression"
	// AcslParser.g:365:1: andExpression : ( equalityExpression -> equalityExpression ) ( AMPERSAND y= equalityExpression -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) ) )* ;
	public final AcslParser.andExpression_return andExpression() throws RecognitionException {
		AcslParser.andExpression_return retval = new AcslParser.andExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token AMPERSAND183=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope equalityExpression182 =null;

		Object AMPERSAND183_tree=null;
		RewriteRuleTokenStream stream_AMPERSAND=new RewriteRuleTokenStream(adaptor,"token AMPERSAND");
		RewriteRuleSubtreeStream stream_equalityExpression=new RewriteRuleSubtreeStream(adaptor,"rule equalityExpression");

		try {
			// AcslParser.g:366:2: ( ( equalityExpression -> equalityExpression ) ( AMPERSAND y= equalityExpression -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) ) )* )
			// AcslParser.g:366:4: ( equalityExpression -> equalityExpression ) ( AMPERSAND y= equalityExpression -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) ) )*
			{
			// AcslParser.g:366:4: ( equalityExpression -> equalityExpression )
			// AcslParser.g:366:6: equalityExpression
			{
			pushFollow(FOLLOW_equalityExpression_in_andExpression2834);
			equalityExpression182=equalityExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_equalityExpression.add(equalityExpression182.getTree());
			// AST REWRITE
			// elements: equalityExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 366:25: -> equalityExpression
			{
				adaptor.addChild(root_0, stream_equalityExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:367:4: ( AMPERSAND y= equalityExpression -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) ) )*
			loop37:
			while (true) {
				int alt37=2;
				int LA37_0 = input.LA(1);
				if ( (LA37_0==AMPERSAND) ) {
					alt37=1;
				}

				switch (alt37) {
				case 1 :
					// AcslParser.g:367:6: AMPERSAND y= equalityExpression
					{
					AMPERSAND183=(Token)match(input,AMPERSAND,FOLLOW_AMPERSAND_in_andExpression2847); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_AMPERSAND.add(AMPERSAND183);

					pushFollow(FOLLOW_equalityExpression_in_andExpression2851);
					y=equalityExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_equalityExpression.add(y.getTree());
					// AST REWRITE
					// elements: y, AMPERSAND, andExpression
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 368:6: -> ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) )
					{
						// AcslParser.g:368:9: ^( OPERATOR AMPERSAND ^( ARGUMENT_LIST $andExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_AMPERSAND.nextNode());
						// AcslParser.g:368:30: ^( ARGUMENT_LIST $andExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop37;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "andExpression"


	public static class exclusiveOrExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "exclusiveOrExpression"
	// AcslParser.g:373:1: exclusiveOrExpression : ( andExpression -> andExpression ) ( BITXOR y= andExpression -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) ) )* ;
	public final AcslParser.exclusiveOrExpression_return exclusiveOrExpression() throws RecognitionException {
		AcslParser.exclusiveOrExpression_return retval = new AcslParser.exclusiveOrExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token BITXOR185=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope andExpression184 =null;

		Object BITXOR185_tree=null;
		RewriteRuleTokenStream stream_BITXOR=new RewriteRuleTokenStream(adaptor,"token BITXOR");
		RewriteRuleSubtreeStream stream_andExpression=new RewriteRuleSubtreeStream(adaptor,"rule andExpression");

		try {
			// AcslParser.g:374:2: ( ( andExpression -> andExpression ) ( BITXOR y= andExpression -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) ) )* )
			// AcslParser.g:374:4: ( andExpression -> andExpression ) ( BITXOR y= andExpression -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) ) )*
			{
			// AcslParser.g:374:4: ( andExpression -> andExpression )
			// AcslParser.g:374:6: andExpression
			{
			pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression2895);
			andExpression184=andExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_andExpression.add(andExpression184.getTree());
			// AST REWRITE
			// elements: andExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 374:20: -> andExpression
			{
				adaptor.addChild(root_0, stream_andExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:375:4: ( BITXOR y= andExpression -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) ) )*
			loop38:
			while (true) {
				int alt38=2;
				int LA38_0 = input.LA(1);
				if ( (LA38_0==BITXOR) ) {
					alt38=1;
				}

				switch (alt38) {
				case 1 :
					// AcslParser.g:375:6: BITXOR y= andExpression
					{
					BITXOR185=(Token)match(input,BITXOR,FOLLOW_BITXOR_in_exclusiveOrExpression2908); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_BITXOR.add(BITXOR185);

					pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression2912);
					y=andExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_andExpression.add(y.getTree());
					// AST REWRITE
					// elements: BITXOR, y, exclusiveOrExpression
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 376:6: -> ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) )
					{
						// AcslParser.g:376:9: ^( OPERATOR BITXOR ^( ARGUMENT_LIST $exclusiveOrExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_BITXOR.nextNode());
						// AcslParser.g:376:27: ^( ARGUMENT_LIST $exclusiveOrExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop38;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "exclusiveOrExpression"


	public static class inclusiveOrExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "inclusiveOrExpression"
	// AcslParser.g:381:1: inclusiveOrExpression : ( exclusiveOrExpression -> exclusiveOrExpression ) ( BAR y= exclusiveOrExpression -> ^( OPERATOR BAR ^( ARGUMENT_LIST $inclusiveOrExpression $y) ) )* ;
	public final AcslParser.inclusiveOrExpression_return inclusiveOrExpression() throws RecognitionException {
		AcslParser.inclusiveOrExpression_return retval = new AcslParser.inclusiveOrExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token BAR187=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope exclusiveOrExpression186 =null;

		Object BAR187_tree=null;
		RewriteRuleTokenStream stream_BAR=new RewriteRuleTokenStream(adaptor,"token BAR");
		RewriteRuleSubtreeStream stream_exclusiveOrExpression=new RewriteRuleSubtreeStream(adaptor,"rule exclusiveOrExpression");

		try {
			// AcslParser.g:382:2: ( ( exclusiveOrExpression -> exclusiveOrExpression ) ( BAR y= exclusiveOrExpression -> ^( OPERATOR BAR ^( ARGUMENT_LIST $inclusiveOrExpression $y) ) )* )
			// AcslParser.g:382:4: ( exclusiveOrExpression -> exclusiveOrExpression ) ( BAR y= exclusiveOrExpression -> ^( OPERATOR BAR ^( ARGUMENT_LIST $inclusiveOrExpression $y) ) )*
			{
			// AcslParser.g:382:4: ( exclusiveOrExpression -> exclusiveOrExpression )
			// AcslParser.g:382:6: exclusiveOrExpression
			{
			pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2956);
			exclusiveOrExpression186=exclusiveOrExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_exclusiveOrExpression.add(exclusiveOrExpression186.getTree());
			// AST REWRITE
			// elements: exclusiveOrExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 382:28: -> exclusiveOrExpression
			{
				adaptor.addChild(root_0, stream_exclusiveOrExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:383:4: ( BAR y= exclusiveOrExpression -> ^( OPERATOR BAR ^( ARGUMENT_LIST $inclusiveOrExpression $y) ) )*
			loop39:
			while (true) {
				int alt39=2;
				int LA39_0 = input.LA(1);
				if ( (LA39_0==BAR) ) {
					int LA39_8 = input.LA(2);
					if ( (synpred84_AcslParser()) ) {
						alt39=1;
					}

				}

				switch (alt39) {
				case 1 :
					// AcslParser.g:383:6: BAR y= exclusiveOrExpression
					{
					BAR187=(Token)match(input,BAR,FOLLOW_BAR_in_inclusiveOrExpression2969); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_BAR.add(BAR187);

					pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2973);
					y=exclusiveOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_exclusiveOrExpression.add(y.getTree());
					// AST REWRITE
					// elements: BAR, inclusiveOrExpression, y
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 384:6: -> ^( OPERATOR BAR ^( ARGUMENT_LIST $inclusiveOrExpression $y) )
					{
						// AcslParser.g:384:9: ^( OPERATOR BAR ^( ARGUMENT_LIST $inclusiveOrExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_BAR.nextNode());
						// AcslParser.g:384:24: ^( ARGUMENT_LIST $inclusiveOrExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop39;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "inclusiveOrExpression"


	public static class logicalAndExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logicalAndExpression"
	// AcslParser.g:389:1: logicalAndExpression : ( inclusiveOrExpression -> inclusiveOrExpression ) ( LAND y= inclusiveOrExpression -> ^( OPERATOR LAND ^( ARGUMENT_LIST $logicalAndExpression $y) ) )* ;
	public final AcslParser.logicalAndExpression_return logicalAndExpression() throws RecognitionException {
		AcslParser.logicalAndExpression_return retval = new AcslParser.logicalAndExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LAND189=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope inclusiveOrExpression188 =null;

		Object LAND189_tree=null;
		RewriteRuleTokenStream stream_LAND=new RewriteRuleTokenStream(adaptor,"token LAND");
		RewriteRuleSubtreeStream stream_inclusiveOrExpression=new RewriteRuleSubtreeStream(adaptor,"rule inclusiveOrExpression");

		try {
			// AcslParser.g:390:2: ( ( inclusiveOrExpression -> inclusiveOrExpression ) ( LAND y= inclusiveOrExpression -> ^( OPERATOR LAND ^( ARGUMENT_LIST $logicalAndExpression $y) ) )* )
			// AcslParser.g:390:4: ( inclusiveOrExpression -> inclusiveOrExpression ) ( LAND y= inclusiveOrExpression -> ^( OPERATOR LAND ^( ARGUMENT_LIST $logicalAndExpression $y) ) )*
			{
			// AcslParser.g:390:4: ( inclusiveOrExpression -> inclusiveOrExpression )
			// AcslParser.g:390:6: inclusiveOrExpression
			{
			pushFollow(FOLLOW_inclusiveOrExpression_in_logicalAndExpression3017);
			inclusiveOrExpression188=inclusiveOrExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_inclusiveOrExpression.add(inclusiveOrExpression188.getTree());
			// AST REWRITE
			// elements: inclusiveOrExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 390:28: -> inclusiveOrExpression
			{
				adaptor.addChild(root_0, stream_inclusiveOrExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:391:4: ( LAND y= inclusiveOrExpression -> ^( OPERATOR LAND ^( ARGUMENT_LIST $logicalAndExpression $y) ) )*
			loop40:
			while (true) {
				int alt40=2;
				int LA40_0 = input.LA(1);
				if ( (LA40_0==LAND) ) {
					alt40=1;
				}

				switch (alt40) {
				case 1 :
					// AcslParser.g:391:6: LAND y= inclusiveOrExpression
					{
					LAND189=(Token)match(input,LAND,FOLLOW_LAND_in_logicalAndExpression3030); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LAND.add(LAND189);

					pushFollow(FOLLOW_inclusiveOrExpression_in_logicalAndExpression3034);
					y=inclusiveOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_inclusiveOrExpression.add(y.getTree());
					// AST REWRITE
					// elements: y, LAND, logicalAndExpression
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 392:6: -> ^( OPERATOR LAND ^( ARGUMENT_LIST $logicalAndExpression $y) )
					{
						// AcslParser.g:392:9: ^( OPERATOR LAND ^( ARGUMENT_LIST $logicalAndExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_LAND.nextNode());
						// AcslParser.g:392:25: ^( ARGUMENT_LIST $logicalAndExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop40;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logicalAndExpression"


	public static class logicalOrExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logicalOrExpression"
	// AcslParser.g:397:1: logicalOrExpression : ( logicalAndExpression -> logicalAndExpression ) ( LOR y= logicalAndExpression -> ^( OPERATOR LOR ^( ARGUMENT_LIST $logicalOrExpression $y) ) )* ;
	public final AcslParser.logicalOrExpression_return logicalOrExpression() throws RecognitionException {
		AcslParser.logicalOrExpression_return retval = new AcslParser.logicalOrExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token LOR191=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope logicalAndExpression190 =null;

		Object LOR191_tree=null;
		RewriteRuleTokenStream stream_LOR=new RewriteRuleTokenStream(adaptor,"token LOR");
		RewriteRuleSubtreeStream stream_logicalAndExpression=new RewriteRuleSubtreeStream(adaptor,"rule logicalAndExpression");

		try {
			// AcslParser.g:398:2: ( ( logicalAndExpression -> logicalAndExpression ) ( LOR y= logicalAndExpression -> ^( OPERATOR LOR ^( ARGUMENT_LIST $logicalOrExpression $y) ) )* )
			// AcslParser.g:398:4: ( logicalAndExpression -> logicalAndExpression ) ( LOR y= logicalAndExpression -> ^( OPERATOR LOR ^( ARGUMENT_LIST $logicalOrExpression $y) ) )*
			{
			// AcslParser.g:398:4: ( logicalAndExpression -> logicalAndExpression )
			// AcslParser.g:398:6: logicalAndExpression
			{
			pushFollow(FOLLOW_logicalAndExpression_in_logicalOrExpression3078);
			logicalAndExpression190=logicalAndExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_logicalAndExpression.add(logicalAndExpression190.getTree());
			// AST REWRITE
			// elements: logicalAndExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 398:27: -> logicalAndExpression
			{
				adaptor.addChild(root_0, stream_logicalAndExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:399:4: ( LOR y= logicalAndExpression -> ^( OPERATOR LOR ^( ARGUMENT_LIST $logicalOrExpression $y) ) )*
			loop41:
			while (true) {
				int alt41=2;
				int LA41_0 = input.LA(1);
				if ( (LA41_0==LOR) ) {
					alt41=1;
				}

				switch (alt41) {
				case 1 :
					// AcslParser.g:399:6: LOR y= logicalAndExpression
					{
					LOR191=(Token)match(input,LOR,FOLLOW_LOR_in_logicalOrExpression3091); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LOR.add(LOR191);

					pushFollow(FOLLOW_logicalAndExpression_in_logicalOrExpression3095);
					y=logicalAndExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_logicalAndExpression.add(y.getTree());
					// AST REWRITE
					// elements: LOR, y, logicalOrExpression
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 400:6: -> ^( OPERATOR LOR ^( ARGUMENT_LIST $logicalOrExpression $y) )
					{
						// AcslParser.g:400:9: ^( OPERATOR LOR ^( ARGUMENT_LIST $logicalOrExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_LOR.nextNode());
						// AcslParser.g:400:24: ^( ARGUMENT_LIST $logicalOrExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop41;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logicalOrExpression"


	public static class logicalImpliesExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "logicalImpliesExpression"
	// AcslParser.g:405:1: logicalImpliesExpression : ( logicalOrExpression -> logicalOrExpression ) ( IMPLY y= logicalOrExpression -> ^( OPERATOR IMPLY ^( ARGUMENT_LIST $logicalImpliesExpression $y) ) )* ;
	public final AcslParser.logicalImpliesExpression_return logicalImpliesExpression() throws RecognitionException {
		AcslParser.logicalImpliesExpression_return retval = new AcslParser.logicalImpliesExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token IMPLY193=null;
		ParserRuleReturnScope y =null;
		ParserRuleReturnScope logicalOrExpression192 =null;

		Object IMPLY193_tree=null;
		RewriteRuleTokenStream stream_IMPLY=new RewriteRuleTokenStream(adaptor,"token IMPLY");
		RewriteRuleSubtreeStream stream_logicalOrExpression=new RewriteRuleSubtreeStream(adaptor,"rule logicalOrExpression");

		try {
			// AcslParser.g:406:2: ( ( logicalOrExpression -> logicalOrExpression ) ( IMPLY y= logicalOrExpression -> ^( OPERATOR IMPLY ^( ARGUMENT_LIST $logicalImpliesExpression $y) ) )* )
			// AcslParser.g:406:4: ( logicalOrExpression -> logicalOrExpression ) ( IMPLY y= logicalOrExpression -> ^( OPERATOR IMPLY ^( ARGUMENT_LIST $logicalImpliesExpression $y) ) )*
			{
			// AcslParser.g:406:4: ( logicalOrExpression -> logicalOrExpression )
			// AcslParser.g:406:6: logicalOrExpression
			{
			pushFollow(FOLLOW_logicalOrExpression_in_logicalImpliesExpression3140);
			logicalOrExpression192=logicalOrExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_logicalOrExpression.add(logicalOrExpression192.getTree());
			// AST REWRITE
			// elements: logicalOrExpression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 406:26: -> logicalOrExpression
			{
				adaptor.addChild(root_0, stream_logicalOrExpression.nextTree());
			}


			retval.tree = root_0;
			}

			}

			// AcslParser.g:407:4: ( IMPLY y= logicalOrExpression -> ^( OPERATOR IMPLY ^( ARGUMENT_LIST $logicalImpliesExpression $y) ) )*
			loop42:
			while (true) {
				int alt42=2;
				int LA42_0 = input.LA(1);
				if ( (LA42_0==IMPLY) ) {
					alt42=1;
				}

				switch (alt42) {
				case 1 :
					// AcslParser.g:407:6: IMPLY y= logicalOrExpression
					{
					IMPLY193=(Token)match(input,IMPLY,FOLLOW_IMPLY_in_logicalImpliesExpression3153); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_IMPLY.add(IMPLY193);

					pushFollow(FOLLOW_logicalOrExpression_in_logicalImpliesExpression3157);
					y=logicalOrExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_logicalOrExpression.add(y.getTree());
					// AST REWRITE
					// elements: IMPLY, logicalImpliesExpression, y
					// token labels: 
					// rule labels: y, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_y=new RewriteRuleSubtreeStream(adaptor,"rule y",y!=null?y.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 408:6: -> ^( OPERATOR IMPLY ^( ARGUMENT_LIST $logicalImpliesExpression $y) )
					{
						// AcslParser.g:408:9: ^( OPERATOR IMPLY ^( ARGUMENT_LIST $logicalImpliesExpression $y) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_IMPLY.nextNode());
						// AcslParser.g:408:26: ^( ARGUMENT_LIST $logicalImpliesExpression $y)
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_retval.nextTree());
						adaptor.addChild(root_2, stream_y.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

				default :
					break loop42;
				}
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "logicalImpliesExpression"


	public static class conditionalExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "conditionalExpression"
	// AcslParser.g:413:1: conditionalExpression : logicalImpliesExpression ( -> logicalImpliesExpression | QUESTION term COLON conditionalExpression -> ^( OPERATOR QUESTION ^( ARGUMENT_LIST logicalImpliesExpression term conditionalExpression ) ) ) ;
	public final AcslParser.conditionalExpression_return conditionalExpression() throws RecognitionException {
		AcslParser.conditionalExpression_return retval = new AcslParser.conditionalExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token QUESTION195=null;
		Token COLON197=null;
		ParserRuleReturnScope logicalImpliesExpression194 =null;
		ParserRuleReturnScope term196 =null;
		ParserRuleReturnScope conditionalExpression198 =null;

		Object QUESTION195_tree=null;
		Object COLON197_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
		RewriteRuleSubtreeStream stream_conditionalExpression=new RewriteRuleSubtreeStream(adaptor,"rule conditionalExpression");
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
		RewriteRuleSubtreeStream stream_logicalImpliesExpression=new RewriteRuleSubtreeStream(adaptor,"rule logicalImpliesExpression");

		try {
			// AcslParser.g:414:2: ( logicalImpliesExpression ( -> logicalImpliesExpression | QUESTION term COLON conditionalExpression -> ^( OPERATOR QUESTION ^( ARGUMENT_LIST logicalImpliesExpression term conditionalExpression ) ) ) )
			// AcslParser.g:414:4: logicalImpliesExpression ( -> logicalImpliesExpression | QUESTION term COLON conditionalExpression -> ^( OPERATOR QUESTION ^( ARGUMENT_LIST logicalImpliesExpression term conditionalExpression ) ) )
			{
			pushFollow(FOLLOW_logicalImpliesExpression_in_conditionalExpression3203);
			logicalImpliesExpression194=logicalImpliesExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_logicalImpliesExpression.add(logicalImpliesExpression194.getTree());
			// AcslParser.g:415:2: ( -> logicalImpliesExpression | QUESTION term COLON conditionalExpression -> ^( OPERATOR QUESTION ^( ARGUMENT_LIST logicalImpliesExpression term conditionalExpression ) ) )
			int alt43=2;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==EOF||LA43_0==BAR||(LA43_0 >= COLON && LA43_0 <= COMMA)||LA43_0==RCURLY||(LA43_0 >= RPAREN && LA43_0 <= RSQUARE)||LA43_0==SEMICOL) ) {
				alt43=1;
			}
			else if ( (LA43_0==QUESTION) ) {
				alt43=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}

			switch (alt43) {
				case 1 :
					// AcslParser.g:415:4: 
					{
					// AST REWRITE
					// elements: logicalImpliesExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 415:4: -> logicalImpliesExpression
					{
						adaptor.addChild(root_0, stream_logicalImpliesExpression.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:416:8: QUESTION term COLON conditionalExpression
					{
					QUESTION195=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_conditionalExpression3219); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_QUESTION.add(QUESTION195);

					pushFollow(FOLLOW_term_in_conditionalExpression3221);
					term196=term();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_term.add(term196.getTree());
					COLON197=(Token)match(input,COLON,FOLLOW_COLON_in_conditionalExpression3223); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_COLON.add(COLON197);

					pushFollow(FOLLOW_conditionalExpression_in_conditionalExpression3225);
					conditionalExpression198=conditionalExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_conditionalExpression.add(conditionalExpression198.getTree());
					// AST REWRITE
					// elements: QUESTION, term, logicalImpliesExpression, conditionalExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 417:8: -> ^( OPERATOR QUESTION ^( ARGUMENT_LIST logicalImpliesExpression term conditionalExpression ) )
					{
						// AcslParser.g:417:11: ^( OPERATOR QUESTION ^( ARGUMENT_LIST logicalImpliesExpression term conditionalExpression ) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_QUESTION.nextNode());
						// AcslParser.g:418:13: ^( ARGUMENT_LIST logicalImpliesExpression term conditionalExpression )
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_logicalImpliesExpression.nextTree());
						adaptor.addChild(root_2, stream_term.nextTree());
						adaptor.addChild(root_2, stream_conditionalExpression.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "conditionalExpression"


	public static class quantifierExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "quantifierExpression"
	// AcslParser.g:427:1: quantifierExpression : quantifier binders SEMICOL assignmentExpression -> ^( quantifier binders assignmentExpression ) ;
	public final AcslParser.quantifierExpression_return quantifierExpression() throws RecognitionException {
		AcslParser.quantifierExpression_return retval = new AcslParser.quantifierExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SEMICOL201=null;
		ParserRuleReturnScope quantifier199 =null;
		ParserRuleReturnScope binders200 =null;
		ParserRuleReturnScope assignmentExpression202 =null;

		Object SEMICOL201_tree=null;
		RewriteRuleTokenStream stream_SEMICOL=new RewriteRuleTokenStream(adaptor,"token SEMICOL");
		RewriteRuleSubtreeStream stream_binders=new RewriteRuleSubtreeStream(adaptor,"rule binders");
		RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
		RewriteRuleSubtreeStream stream_quantifier=new RewriteRuleSubtreeStream(adaptor,"rule quantifier");

		try {
			// AcslParser.g:428:2: ( quantifier binders SEMICOL assignmentExpression -> ^( quantifier binders assignmentExpression ) )
			// AcslParser.g:428:4: quantifier binders SEMICOL assignmentExpression
			{
			pushFollow(FOLLOW_quantifier_in_quantifierExpression3324);
			quantifier199=quantifier();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_quantifier.add(quantifier199.getTree());
			pushFollow(FOLLOW_binders_in_quantifierExpression3326);
			binders200=binders();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_binders.add(binders200.getTree());
			SEMICOL201=(Token)match(input,SEMICOL,FOLLOW_SEMICOL_in_quantifierExpression3328); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_SEMICOL.add(SEMICOL201);

			pushFollow(FOLLOW_assignmentExpression_in_quantifierExpression3330);
			assignmentExpression202=assignmentExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression202.getTree());
			// AST REWRITE
			// elements: binders, assignmentExpression, quantifier
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			if ( state.backtracking==0 ) {
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 429:4: -> ^( quantifier binders assignmentExpression )
			{
				// AcslParser.g:429:7: ^( quantifier binders assignmentExpression )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_quantifier.nextNode(), root_1);
				adaptor.addChild(root_1, stream_binders.nextTree());
				adaptor.addChild(root_1, stream_assignmentExpression.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "quantifierExpression"


	public static class quantifier_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "quantifier"
	// AcslParser.g:443:1: quantifier : ( FORALL | EXISTS );
	public final AcslParser.quantifier_return quantifier() throws RecognitionException {
		AcslParser.quantifier_return retval = new AcslParser.quantifier_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set203=null;

		Object set203_tree=null;

		try {
			// AcslParser.g:444:2: ( FORALL | EXISTS )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set203=input.LT(1);
			if ( input.LA(1)==EXISTS||input.LA(1)==FORALL ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set203));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "quantifier"


	public static class assignmentExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "assignmentExpression"
	// AcslParser.g:455:1: assignmentExpression : ( ( unaryExpression ASSIGN )=> unaryExpression ASSIGN assignmentExpression -> ^( OPERATOR ASSIGN ^( ARGUMENT_LIST unaryExpression assignmentExpression ) ) | conditionalExpression | quantifierExpression );
	public final AcslParser.assignmentExpression_return assignmentExpression() throws RecognitionException {
		AcslParser.assignmentExpression_return retval = new AcslParser.assignmentExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token ASSIGN205=null;
		ParserRuleReturnScope unaryExpression204 =null;
		ParserRuleReturnScope assignmentExpression206 =null;
		ParserRuleReturnScope conditionalExpression207 =null;
		ParserRuleReturnScope quantifierExpression208 =null;

		Object ASSIGN205_tree=null;
		RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
		RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
		RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");

		try {
			// AcslParser.g:456:2: ( ( unaryExpression ASSIGN )=> unaryExpression ASSIGN assignmentExpression -> ^( OPERATOR ASSIGN ^( ARGUMENT_LIST unaryExpression assignmentExpression ) ) | conditionalExpression | quantifierExpression )
			int alt44=3;
			switch ( input.LA(1) ) {
			case ELLIPSIS:
			case FALSE:
			case FLOATING_CONSTANT:
			case INTEGER_CONSTANT:
			case NOTHING:
			case RESULT:
			case TRUE:
			case CHARACTER_CONSTANT:
				{
				int LA44_1 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ID:
				{
				int LA44_2 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case STRING_LITERAL:
				{
				int LA44_3 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case LCURLY:
				{
				int LA44_4 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case LPAREN:
				{
				int LA44_5 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case AMPERSAND:
			case COMP:
			case NOT:
			case PLUS:
			case STAR:
			case MINUS:
				{
				int LA44_6 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 6, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case SIZEOF:
				{
				int LA44_7 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 7, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case UNION:
				{
				int LA44_8 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 8, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case INTER:
				{
				int LA44_9 = input.LA(2);
				if ( (synpred90_AcslParser()) ) {
					alt44=1;
				}
				else if ( (synpred91_AcslParser()) ) {
					alt44=2;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 44, 9, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case EXISTS:
			case FORALL:
				{
				alt44=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 44, 0, input);
				throw nvae;
			}
			switch (alt44) {
				case 1 :
					// AcslParser.g:456:4: ( unaryExpression ASSIGN )=> unaryExpression ASSIGN assignmentExpression
					{
					pushFollow(FOLLOW_unaryExpression_in_assignmentExpression3402);
					unaryExpression204=unaryExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression204.getTree());
					ASSIGN205=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignmentExpression3404); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN205);

					pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression3406);
					assignmentExpression206=assignmentExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_assignmentExpression.add(assignmentExpression206.getTree());
					// AST REWRITE
					// elements: unaryExpression, ASSIGN, assignmentExpression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 458:4: -> ^( OPERATOR ASSIGN ^( ARGUMENT_LIST unaryExpression assignmentExpression ) )
					{
						// AcslParser.g:458:7: ^( OPERATOR ASSIGN ^( ARGUMENT_LIST unaryExpression assignmentExpression ) )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OPERATOR, "OPERATOR"), root_1);
						adaptor.addChild(root_1, stream_ASSIGN.nextNode());
						// AcslParser.g:459:9: ^( ARGUMENT_LIST unaryExpression assignmentExpression )
						{
						Object root_2 = (Object)adaptor.nil();
						root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST"), root_2);
						adaptor.addChild(root_2, stream_unaryExpression.nextTree());
						adaptor.addChild(root_2, stream_assignmentExpression.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// AcslParser.g:460:4: conditionalExpression
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_conditionalExpression_in_assignmentExpression3438);
					conditionalExpression207=conditionalExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression207.getTree());

					}
					break;
				case 3 :
					// AcslParser.g:461:4: quantifierExpression
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_quantifierExpression_in_assignmentExpression3443);
					quantifierExpression208=quantifierExpression();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, quantifierExpression208.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assignmentExpression"


	public static class term_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "term"
	// AcslParser.g:472:1: term : assignmentExpression ;
	public final AcslParser.term_return term() throws RecognitionException {
		AcslParser.term_return retval = new AcslParser.term_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope assignmentExpression209 =null;


		try {
			// AcslParser.g:473:2: ( assignmentExpression )
			// AcslParser.g:473:4: assignmentExpression
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_assignmentExpression_in_term3456);
			assignmentExpression209=assignmentExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression209.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "term"


	public static class constantExpression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "constantExpression"
	// AcslParser.g:477:1: constantExpression : conditionalExpression ;
	public final AcslParser.constantExpression_return constantExpression() throws RecognitionException {
		AcslParser.constantExpression_return retval = new AcslParser.constantExpression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope conditionalExpression210 =null;


		try {
			// AcslParser.g:478:2: ( conditionalExpression )
			// AcslParser.g:478:4: conditionalExpression
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_conditionalExpression_in_constantExpression3471);
			conditionalExpression210=conditionalExpression();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression210.getTree());

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "constantExpression"


	public static class constant_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "constant"
	// AcslParser.g:481:1: constant : ( INTEGER_CONSTANT | FLOATING_CONSTANT | CHARACTER_CONSTANT | TRUE | FALSE | RESULT | NOTHING | ELLIPSIS );
	public final AcslParser.constant_return constant() throws RecognitionException {
		AcslParser.constant_return retval = new AcslParser.constant_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set211=null;

		Object set211_tree=null;

		try {
			// AcslParser.g:482:2: ( INTEGER_CONSTANT | FLOATING_CONSTANT | CHARACTER_CONSTANT | TRUE | FALSE | RESULT | NOTHING | ELLIPSIS )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set211=input.LT(1);
			if ( input.LA(1)==ELLIPSIS||input.LA(1)==FALSE||input.LA(1)==FLOATING_CONSTANT||input.LA(1)==INTEGER_CONSTANT||input.LA(1)==NOTHING||input.LA(1)==RESULT||input.LA(1)==TRUE||input.LA(1)==CHARACTER_CONSTANT ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set211));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "constant"


	public static class unary_op_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "unary_op"
	// AcslParser.g:488:1: unary_op : ( PLUS | MINUS | NOT | COMP | STAR | AMPERSAND );
	public final AcslParser.unary_op_return unary_op() throws RecognitionException {
		AcslParser.unary_op_return retval = new AcslParser.unary_op_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set212=null;

		Object set212_tree=null;

		try {
			// AcslParser.g:489:5: ( PLUS | MINUS | NOT | COMP | STAR | AMPERSAND )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set212=input.LT(1);
			if ( input.LA(1)==AMPERSAND||input.LA(1)==COMP||input.LA(1)==NOT||input.LA(1)==PLUS||input.LA(1)==STAR||input.LA(1)==MINUS ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set212));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "unary_op"


	public static class binary_op_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "binary_op"
	// AcslParser.g:492:1: binary_op : ( PLUS | MINUS | STAR | DIVIDE | MOD | LSHIFT | RSHIFT | EQ | NEQ | LTE | GTE | LT | GT | LAND | LOR | XOR | AMPERSAND | BAR | IMPLY | EQUIV | BITXOR );
	public final AcslParser.binary_op_return binary_op() throws RecognitionException {
		AcslParser.binary_op_return retval = new AcslParser.binary_op_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set213=null;

		Object set213_tree=null;

		try {
			// AcslParser.g:493:5: ( PLUS | MINUS | STAR | DIVIDE | MOD | LSHIFT | RSHIFT | EQ | NEQ | LTE | GTE | LT | GT | LAND | LOR | XOR | AMPERSAND | BAR | IMPLY | EQUIV | BITXOR )
			// AcslParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set213=input.LT(1);
			if ( input.LA(1)==AMPERSAND||input.LA(1)==BAR||input.LA(1)==BITXOR||input.LA(1)==DIVIDE||(input.LA(1) >= EQ && input.LA(1) <= EQUIV)||(input.LA(1) >= GT && input.LA(1) <= GTE)||input.LA(1)==IMPLY||input.LA(1)==LAND||input.LA(1)==LOR||(input.LA(1) >= LT && input.LA(1) <= LTE)||(input.LA(1) >= MOD && input.LA(1) <= NEQ)||input.LA(1)==PLUS||input.LA(1)==STAR||input.LA(1)==XOR||(input.LA(1) >= LSHIFT && input.LA(1) <= MINUS)||input.LA(1)==RSHIFT ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(set213));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "binary_op"

	// $ANTLR start synpred24_AcslParser
	public final void synpred24_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:120:7: ( variable_ident_base LSQUARE RSQUARE )
		// AcslParser.g:120:7: variable_ident_base LSQUARE RSQUARE
		{
		pushFollow(FOLLOW_variable_ident_base_in_synpred24_AcslParser783);
		variable_ident_base();
		state._fsp--;
		if (state.failed) return;

		match(input,LSQUARE,FOLLOW_LSQUARE_in_synpred24_AcslParser785); if (state.failed) return;

		match(input,RSQUARE,FOLLOW_RSQUARE_in_synpred24_AcslParser787); if (state.failed) return;

		}

	}
	// $ANTLR end synpred24_AcslParser

	// $ANTLR start synpred34_AcslParser
	public final void synpred34_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:168:7: ( event_base PLUS event_base )
		// AcslParser.g:168:7: event_base PLUS event_base
		{
		pushFollow(FOLLOW_event_base_in_synpred34_AcslParser1115);
		event_base();
		state._fsp--;
		if (state.failed) return;

		match(input,PLUS,FOLLOW_PLUS_in_synpred34_AcslParser1117); if (state.failed) return;

		pushFollow(FOLLOW_event_base_in_synpred34_AcslParser1119);
		event_base();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred34_AcslParser

	// $ANTLR start synpred35_AcslParser
	public final void synpred35_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:170:7: ( event_base SUB event_base )
		// AcslParser.g:170:7: event_base SUB event_base
		{
		pushFollow(FOLLOW_event_base_in_synpred35_AcslParser1145);
		event_base();
		state._fsp--;
		if (state.failed) return;

		match(input,SUB,FOLLOW_SUB_in_synpred35_AcslParser1147); if (state.failed) return;

		pushFollow(FOLLOW_event_base_in_synpred35_AcslParser1149);
		event_base();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred35_AcslParser

	// $ANTLR start synpred36_AcslParser
	public final void synpred36_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:172:7: ( event_base AMPERSAND event_base )
		// AcslParser.g:172:7: event_base AMPERSAND event_base
		{
		pushFollow(FOLLOW_event_base_in_synpred36_AcslParser1175);
		event_base();
		state._fsp--;
		if (state.failed) return;

		match(input,AMPERSAND,FOLLOW_AMPERSAND_in_synpred36_AcslParser1177); if (state.failed) return;

		pushFollow(FOLLOW_event_base_in_synpred36_AcslParser1179);
		event_base();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred36_AcslParser

	// $ANTLR start synpred53_AcslParser
	public final void synpred53_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:227:7: ( LCURLY term BAR binders ( SEMICOL term )? RCURLY )
		// AcslParser.g:227:7: LCURLY term BAR binders ( SEMICOL term )? RCURLY
		{
		match(input,LCURLY,FOLLOW_LCURLY_in_synpred53_AcslParser1650); if (state.failed) return;

		pushFollow(FOLLOW_term_in_synpred53_AcslParser1652);
		term();
		state._fsp--;
		if (state.failed) return;

		match(input,BAR,FOLLOW_BAR_in_synpred53_AcslParser1654); if (state.failed) return;

		pushFollow(FOLLOW_binders_in_synpred53_AcslParser1656);
		binders();
		state._fsp--;
		if (state.failed) return;

		// AcslParser.g:227:31: ( SEMICOL term )?
		int alt47=2;
		int LA47_0 = input.LA(1);
		if ( (LA47_0==SEMICOL) ) {
			alt47=1;
		}
		switch (alt47) {
			case 1 :
				// AcslParser.g:227:32: SEMICOL term
				{
				match(input,SEMICOL,FOLLOW_SEMICOL_in_synpred53_AcslParser1659); if (state.failed) return;

				pushFollow(FOLLOW_term_in_synpred53_AcslParser1661);
				term();
				state._fsp--;
				if (state.failed) return;

				}
				break;

		}

		match(input,RCURLY,FOLLOW_RCURLY_in_synpred53_AcslParser1665); if (state.failed) return;

		}

	}
	// $ANTLR end synpred53_AcslParser

	// $ANTLR start synpred54_AcslParser
	public final void synpred54_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:229:7: ( LCURLY term RCURLY )
		// AcslParser.g:229:7: LCURLY term RCURLY
		{
		match(input,LCURLY,FOLLOW_LCURLY_in_synpred54_AcslParser1693); if (state.failed) return;

		pushFollow(FOLLOW_term_in_synpred54_AcslParser1695);
		term();
		state._fsp--;
		if (state.failed) return;

		match(input,RCURLY,FOLLOW_RCURLY_in_synpred54_AcslParser1697); if (state.failed) return;

		}

	}
	// $ANTLR end synpred54_AcslParser

	// $ANTLR start synpred60_AcslParser
	public final void synpred60_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:258:26: ( COMMA assignmentExpression )
		// AcslParser.g:258:26: COMMA assignmentExpression
		{
		match(input,COMMA,FOLLOW_COMMA_in_synpred60_AcslParser1951); if (state.failed) return;

		pushFollow(FOLLOW_assignmentExpression_in_synpred60_AcslParser1953);
		assignmentExpression();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred60_AcslParser

	// $ANTLR start synpred63_AcslParser
	public final void synpred63_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:267:4: ( SIZEOF LPAREN type_expr )
		// AcslParser.g:267:5: SIZEOF LPAREN type_expr
		{
		match(input,SIZEOF,FOLLOW_SIZEOF_in_synpred63_AcslParser2011); if (state.failed) return;

		match(input,LPAREN,FOLLOW_LPAREN_in_synpred63_AcslParser2013); if (state.failed) return;

		pushFollow(FOLLOW_type_expr_in_synpred63_AcslParser2015);
		type_expr();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred63_AcslParser

	// $ANTLR start synpred64_AcslParser
	public final void synpred64_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:269:4: ( SIZEOF unaryExpression )
		// AcslParser.g:269:4: SIZEOF unaryExpression
		{
		match(input,SIZEOF,FOLLOW_SIZEOF_in_synpred64_AcslParser2041); if (state.failed) return;

		pushFollow(FOLLOW_unaryExpression_in_synpred64_AcslParser2043);
		unaryExpression();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred64_AcslParser

	// $ANTLR start synpred66_AcslParser
	public final void synpred66_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:291:4: ( LPAREN type_expr RPAREN )
		// AcslParser.g:291:5: LPAREN type_expr RPAREN
		{
		match(input,LPAREN,FOLLOW_LPAREN_in_synpred66_AcslParser2151); if (state.failed) return;

		pushFollow(FOLLOW_type_expr_in_synpred66_AcslParser2153);
		type_expr();
		state._fsp--;
		if (state.failed) return;

		match(input,RPAREN,FOLLOW_RPAREN_in_synpred66_AcslParser2155); if (state.failed) return;

		}

	}
	// $ANTLR end synpred66_AcslParser

	// $ANTLR start synpred84_AcslParser
	public final void synpred84_AcslParser_fragment() throws RecognitionException {
		ParserRuleReturnScope y =null;


		// AcslParser.g:383:6: ( BAR y= exclusiveOrExpression )
		// AcslParser.g:383:6: BAR y= exclusiveOrExpression
		{
		match(input,BAR,FOLLOW_BAR_in_synpred84_AcslParser2969); if (state.failed) return;

		pushFollow(FOLLOW_exclusiveOrExpression_in_synpred84_AcslParser2973);
		y=exclusiveOrExpression();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred84_AcslParser

	// $ANTLR start synpred90_AcslParser
	public final void synpred90_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:456:4: ( unaryExpression ASSIGN )
		// AcslParser.g:456:5: unaryExpression ASSIGN
		{
		pushFollow(FOLLOW_unaryExpression_in_synpred90_AcslParser3393);
		unaryExpression();
		state._fsp--;
		if (state.failed) return;

		match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred90_AcslParser3395); if (state.failed) return;

		}

	}
	// $ANTLR end synpred90_AcslParser

	// $ANTLR start synpred91_AcslParser
	public final void synpred91_AcslParser_fragment() throws RecognitionException {
		// AcslParser.g:460:4: ( conditionalExpression )
		// AcslParser.g:460:4: conditionalExpression
		{
		pushFollow(FOLLOW_conditionalExpression_in_synpred91_AcslParser3438);
		conditionalExpression();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred91_AcslParser

	// Delegated rules

	public final boolean synpred64_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred64_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred84_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred84_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred66_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred66_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred36_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred36_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred34_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred34_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred53_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred53_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred24_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred24_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred90_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred90_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred63_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred63_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred35_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred35_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred54_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred54_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred91_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred91_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred60_AcslParser() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred60_AcslParser_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}



	public static final BitSet FOLLOW_LCOMMENT_in_function_contract267 = new BitSet(new long[]{0x0010801002000210L,0x0000002800000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_function_clause_in_function_contract272 = new BitSet(new long[]{0x0010801006802210L,0x0000002900000000L,0x0000000010000000L});
	public static final BitSet FOLLOW_named_behavior_block_in_function_contract287 = new BitSet(new long[]{0x0000000004802000L,0x0000000100000000L});
	public static final BitSet FOLLOW_completeness_clause_block_in_function_contract294 = new BitSet(new long[]{0x0000000004800000L,0x0000000100000000L});
	public static final BitSet FOLLOW_RCOMMENT_in_function_contract298 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_requires_clause_in_function_clause341 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_SEMICOL_in_function_clause343 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_terminates_clause_in_function_clause358 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_SEMICOL_in_function_clause360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_clause_in_function_clause375 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_SEMICOL_in_function_clause377 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_named_behavior_in_named_behavior_block402 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_completeness_clause_in_completeness_clause_block427 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_SEMICOL_in_completeness_clause_block429 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REQUIRES_in_requires_clause454 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_requires_clause456 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TEMINATES_in_terminates_clause481 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_terminates_clause483 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_binder_in_binders545 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_binders548 = new BitSet(new long[]{0x5000080040090000L,0x0080201000000100L,0x0000000000080000L});
	public static final BitSet FOLLOW_binder_in_binders550 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_type_expr_in_binder585 = new BitSet(new long[]{0x1000000000000000L,0x0000800000000400L});
	public static final BitSet FOLLOW_variable_ident_in_binder587 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_binder590 = new BitSet(new long[]{0x1000000000000000L,0x0000800000000400L});
	public static final BitSet FOLLOW_variable_ident_in_binder592 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_logic_type_expr_in_type_expr629 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_c_type_in_type_expr644 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_built_in_logic_type_in_logic_type_expr668 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_logic_type_expr683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STAR_in_variable_ident773 = new BitSet(new long[]{0x1000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_variable_ident_base_in_variable_ident775 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_ident_base_in_variable_ident783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_LSQUARE_in_variable_ident785 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_variable_ident787 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_ident_base_in_variable_ident795 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_variable_ident_base812 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_variable_ident_base820 = new BitSet(new long[]{0x1000000000000000L,0x0000800000000400L});
	public static final BitSet FOLLOW_variable_ident_in_variable_ident_base822 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_variable_ident_base824 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GUARDS_in_guards_clause841 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_guards_clause843 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assigns_clause_in_simple_clause867 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ensures_clause_in_simple_clause875 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_allocation_clause_in_simple_clause884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reads_clause_in_simple_clause892 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_depends_clause_in_simple_clause900 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_guards_clause_in_simple_clause908 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASSIGNS_in_assigns_clause925 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_assigns_clause927 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ENSURES_in_ensures_clause951 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_ensures_clause953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALLOC_in_allocation_clause977 = new BitSet(new long[]{0x1000548400600020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_allocation_clause979 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_allocation_clause982 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_allocation_clause984 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FREES_in_allocation_clause1004 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_allocation_clause1006 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_READS_in_reads_clause1030 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_reads_clause1032 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DEPENDS_in_depends_clause1056 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_list_in_depends_clause1058 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_event_in_event_list1082 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_event_list1085 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_in_event_list1087 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_event_base_in_event1115 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
	public static final BitSet FOLLOW_PLUS_in_event1117 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_base_in_event1119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_event_base_in_event1145 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_SUB_in_event1147 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_base_in_event1149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_event_base_in_event1175 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_AMPERSAND_in_event1177 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_base_in_event1179 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_event_base_in_event1205 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_READ_in_event_base1238 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LPAREN_in_event_base1240 = new BitSet(new long[]{0x1000548400400020L,0x0019C0C020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_event_base1242 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_event_base1244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WRITE_in_event_base1268 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LPAREN_in_event_base1270 = new BitSet(new long[]{0x1000548400400020L,0x0019C0C020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_event_base1272 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_event_base1274 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CALL_in_event_base1298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LPAREN_in_event_base1300 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_ID_in_event_base1302 = new BitSet(new long[]{0x0000000000200000L,0x0000008000000000L});
	public static final BitSet FOLLOW_COMMA_in_event_base1305 = new BitSet(new long[]{0x1000548400400020L,0x0019C0C020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_event_base1307 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_event_base1311 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOACT_in_event_base1338 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANYACT_in_event_base1360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_event_base1382 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_in_event_base1384 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_event_base1386 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BEHAVIOR_in_named_behavior1419 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_ID_in_named_behavior1421 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COLON_in_named_behavior1423 = new BitSet(new long[]{0x0010801002000610L,0x0000002800000000L});
	public static final BitSet FOLLOW_behavior_body_in_named_behavior1425 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_behavior_clause_in_behavior_body1454 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_SEMICOL_in_behavior_body1456 = new BitSet(new long[]{0x0010801002000612L,0x0000002800000000L});
	public static final BitSet FOLLOW_assumes_clause_in_behavior_clause1485 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_requires_clause_in_behavior_clause1494 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_clause_in_behavior_clause1502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASSUMES_in_assumes_clause1519 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_assumes_clause1521 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMPLETE_in_completeness_clause1545 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_BEHAVIORS_in_completeness_clause1547 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_id_list_in_completeness_clause1549 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DISJOINT_in_completeness_clause1564 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_BEHAVIORS_in_completeness_clause1566 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_id_list_in_completeness_clause1568 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_id_list1598 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_id_list1601 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_ID_in_id_list1603 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_constant_in_primaryExpression1629 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_primaryExpression1637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_primaryExpression1642 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LCURLY_in_primaryExpression1650 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_primaryExpression1652 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_BAR_in_primaryExpression1654 = new BitSet(new long[]{0x5000080040090000L,0x0080201000000100L,0x0000000000080000L});
	public static final BitSet FOLLOW_binders_in_primaryExpression1656 = new BitSet(new long[]{0x0000000000000000L,0x0000040200000000L});
	public static final BitSet FOLLOW_SEMICOL_in_primaryExpression1659 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_primaryExpression1661 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RCURLY_in_primaryExpression1665 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LCURLY_in_primaryExpression1693 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_primaryExpression1695 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RCURLY_in_primaryExpression1697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_primaryExpression1717 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_primaryExpression1719 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_primaryExpression1721 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_primaryExpression_in_postfixExpression1747 = new BitSet(new long[]{0x0000000010000082L,0x0000000000000C00L});
	public static final BitSet FOLLOW_LSQUARE_in_postfixExpression1764 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_postfixExpression1766 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_postfixExpression1768 = new BitSet(new long[]{0x0000000010000082L,0x0000000000000C00L});
	public static final BitSet FOLLOW_LPAREN_in_postfixExpression1842 = new BitSet(new long[]{0x1000548400400020L,0x0019C0C020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_postfixExpression1844 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_postfixExpression1846 = new BitSet(new long[]{0x0000000010000082L,0x0000000000000C00L});
	public static final BitSet FOLLOW_DOT_in_postfixExpression1877 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_ID_in_postfixExpression1879 = new BitSet(new long[]{0x0000000010000082L,0x0000000000000C00L});
	public static final BitSet FOLLOW_ARROW_in_postfixExpression1902 = new BitSet(new long[]{0x1000000000000000L});
	public static final BitSet FOLLOW_ID_in_postfixExpression1904 = new BitSet(new long[]{0x0000000010000082L,0x0000000000000C00L});
	public static final BitSet FOLLOW_assignmentExpression_in_argumentExpressionList1948 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_argumentExpressionList1951 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_assignmentExpression_in_argumentExpressionList1953 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_postfixExpression_in_unaryExpression1981 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unary_op_in_unaryExpression1986 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_castExpression_in_unaryExpression1988 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SIZEOF_in_unaryExpression2019 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LPAREN_in_unaryExpression2021 = new BitSet(new long[]{0x5000080040090000L,0x0080201000000100L,0x0000000000080000L});
	public static final BitSet FOLLOW_type_expr_in_unaryExpression2023 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_unaryExpression2025 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SIZEOF_in_unaryExpression2041 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_unaryExpression_in_unaryExpression2043 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UNION_in_unaryExpression2064 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LPAREN_in_unaryExpression2066 = new BitSet(new long[]{0x1000548400400020L,0x0019C0C020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_unaryExpression2068 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_unaryExpression2070 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTER_in_unaryExpression2094 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LPAREN_in_unaryExpression2096 = new BitSet(new long[]{0x1000548400400020L,0x0019C0C020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_argumentExpressionList_in_unaryExpression2098 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_unaryExpression2100 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_castExpression2161 = new BitSet(new long[]{0x5000080040090000L,0x0080201000000100L,0x0000000000080000L});
	public static final BitSet FOLLOW_type_expr_in_castExpression2163 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_castExpression2165 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_castExpression_in_castExpression2167 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unaryExpression_in_castExpression2185 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_castExpression_in_multiplicativeExpression2199 = new BitSet(new long[]{0x0000000000000002L,0x0000800000010000L,0x0000000000000200L});
	public static final BitSet FOLLOW_STAR_in_multiplicativeExpression2209 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_castExpression_in_multiplicativeExpression2213 = new BitSet(new long[]{0x0000000000000002L,0x0000800000010000L,0x0000000000000200L});
	public static final BitSet FOLLOW_DIV_in_multiplicativeExpression2239 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_castExpression_in_multiplicativeExpression2243 = new BitSet(new long[]{0x0000000000000002L,0x0000800000010000L,0x0000000000000200L});
	public static final BitSet FOLLOW_MOD_in_multiplicativeExpression2272 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_castExpression_in_multiplicativeExpression2276 = new BitSet(new long[]{0x0000000000000002L,0x0000800000010000L,0x0000000000000200L});
	public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2318 = new BitSet(new long[]{0x0000000000000002L,0x0002000020000000L});
	public static final BitSet FOLLOW_PLUS_in_additiveExpression2335 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2339 = new BitSet(new long[]{0x0000000000000002L,0x0002000020000000L});
	public static final BitSet FOLLOW_SUB_in_additiveExpression2379 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression2383 = new BitSet(new long[]{0x0000000000000002L,0x0002000020000000L});
	public static final BitSet FOLLOW_additiveExpression_in_rangeExpression2437 = new BitSet(new long[]{0x0000000020000002L});
	public static final BitSet FOLLOW_DOTDOT_in_rangeExpression2452 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_additiveExpression_in_rangeExpression2456 = new BitSet(new long[]{0x0020000000000002L});
	public static final BitSet FOLLOW_HASH_in_rangeExpression2490 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_additiveExpression_in_rangeExpression2494 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rangeExpression_in_shiftExpression2555 = new BitSet(new long[]{0x0000000000000002L,0x0000180000000000L});
	public static final BitSet FOLLOW_SHIFTLEFT_in_shiftExpression2572 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_rangeExpression_in_shiftExpression2576 = new BitSet(new long[]{0x0000000000000002L,0x0000180000000000L});
	public static final BitSet FOLLOW_SHIFTRIGHT_in_shiftExpression2616 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_rangeExpression_in_shiftExpression2620 = new BitSet(new long[]{0x0000000000000002L,0x0000180000000000L});
	public static final BitSet FOLLOW_shiftExpression_in_relationalExpression2674 = new BitSet(new long[]{0x000C000000000002L,0x0000000000003000L});
	public static final BitSet FOLLOW_relationalOperator_in_relationalExpression2687 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_shiftExpression_in_relationalExpression2691 = new BitSet(new long[]{0x000C000000000002L,0x0000000000003000L});
	public static final BitSet FOLLOW_relationalExpression_in_equalityExpression2758 = new BitSet(new long[]{0x0000002000000002L,0x0000000000020000L});
	public static final BitSet FOLLOW_equalityOperator_in_equalityExpression2771 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_relationalExpression_in_equalityExpression2775 = new BitSet(new long[]{0x0000002000000002L,0x0000000000020000L});
	public static final BitSet FOLLOW_equalityExpression_in_andExpression2834 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_AMPERSAND_in_andExpression2847 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_equalityExpression_in_andExpression2851 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression2895 = new BitSet(new long[]{0x0000000000008002L});
	public static final BitSet FOLLOW_BITXOR_in_exclusiveOrExpression2908 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression2912 = new BitSet(new long[]{0x0000000000008002L});
	public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2956 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_BAR_in_inclusiveOrExpression2969 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression2973 = new BitSet(new long[]{0x0000000000001002L});
	public static final BitSet FOLLOW_inclusiveOrExpression_in_logicalAndExpression3017 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
	public static final BitSet FOLLOW_LAND_in_logicalAndExpression3030 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_inclusiveOrExpression_in_logicalAndExpression3034 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
	public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression3078 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
	public static final BitSet FOLLOW_LOR_in_logicalOrExpression3091 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression3095 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
	public static final BitSet FOLLOW_logicalOrExpression_in_logicalImpliesExpression3140 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_IMPLY_in_logicalImpliesExpression3153 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_logicalOrExpression_in_logicalImpliesExpression3157 = new BitSet(new long[]{0x2000000000000002L});
	public static final BitSet FOLLOW_logicalImpliesExpression_in_conditionalExpression3203 = new BitSet(new long[]{0x0000000000000002L,0x0000000080000000L});
	public static final BitSet FOLLOW_QUESTION_in_conditionalExpression3219 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_conditionalExpression3221 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COLON_in_conditionalExpression3223 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_conditionalExpression_in_conditionalExpression3225 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quantifier_in_quantifierExpression3324 = new BitSet(new long[]{0x5000080040090000L,0x0080201000000100L,0x0000000000080000L});
	public static final BitSet FOLLOW_binders_in_quantifierExpression3326 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
	public static final BitSet FOLLOW_SEMICOL_in_quantifierExpression3328 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_assignmentExpression_in_quantifierExpression3330 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unaryExpression_in_assignmentExpression3402 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ASSIGN_in_assignmentExpression3404 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression3406 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditionalExpression_in_assignmentExpression3438 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quantifierExpression_in_assignmentExpression3443 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assignmentExpression_in_term3456 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditionalExpression_in_constantExpression3471 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_variable_ident_base_in_synpred24_AcslParser783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_LSQUARE_in_synpred24_AcslParser785 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_RSQUARE_in_synpred24_AcslParser787 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_event_base_in_synpred34_AcslParser1115 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
	public static final BitSet FOLLOW_PLUS_in_synpred34_AcslParser1117 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_base_in_synpred34_AcslParser1119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_event_base_in_synpred35_AcslParser1145 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
	public static final BitSet FOLLOW_SUB_in_synpred35_AcslParser1147 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_base_in_synpred35_AcslParser1149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_event_base_in_synpred36_AcslParser1175 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_AMPERSAND_in_synpred36_AcslParser1177 = new BitSet(new long[]{0x0000000000040040L,0x0200000400080400L});
	public static final BitSet FOLLOW_event_base_in_synpred36_AcslParser1179 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LCURLY_in_synpred53_AcslParser1650 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_synpred53_AcslParser1652 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_BAR_in_synpred53_AcslParser1654 = new BitSet(new long[]{0x5000080040090000L,0x0080201000000100L,0x0000000000080000L});
	public static final BitSet FOLLOW_binders_in_synpred53_AcslParser1656 = new BitSet(new long[]{0x0000000000000000L,0x0000040200000000L});
	public static final BitSet FOLLOW_SEMICOL_in_synpred53_AcslParser1659 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_synpred53_AcslParser1661 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RCURLY_in_synpred53_AcslParser1665 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LCURLY_in_synpred54_AcslParser1693 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_term_in_synpred54_AcslParser1695 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_RCURLY_in_synpred54_AcslParser1697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_synpred60_AcslParser1951 = new BitSet(new long[]{0x1000548400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_assignmentExpression_in_synpred60_AcslParser1953 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SIZEOF_in_synpred63_AcslParser2011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_LPAREN_in_synpred63_AcslParser2013 = new BitSet(new long[]{0x5000080040090000L,0x0080201000000100L,0x0000000000080000L});
	public static final BitSet FOLLOW_type_expr_in_synpred63_AcslParser2015 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SIZEOF_in_synpred64_AcslParser2041 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_unaryExpression_in_synpred64_AcslParser2043 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_synpred66_AcslParser2151 = new BitSet(new long[]{0x5000080040090000L,0x0080201000000100L,0x0000000000080000L});
	public static final BitSet FOLLOW_type_expr_in_synpred66_AcslParser2153 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
	public static final BitSet FOLLOW_RPAREN_in_synpred66_AcslParser2155 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BAR_in_synpred84_AcslParser2969 = new BitSet(new long[]{0x1000140400400020L,0x0019C04020300443L,0x0000000000200010L});
	public static final BitSet FOLLOW_exclusiveOrExpression_in_synpred84_AcslParser2973 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unaryExpression_in_synpred90_AcslParser3393 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ASSIGN_in_synpred90_AcslParser3395 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_conditionalExpression_in_synpred91_AcslParser3438 = new BitSet(new long[]{0x0000000000000002L});
}
