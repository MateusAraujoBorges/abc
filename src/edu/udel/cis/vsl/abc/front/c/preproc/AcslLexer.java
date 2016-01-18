// $ANTLR 3.5.2 AcslLexer.g 2016-01-05 22:55:01

package edu.udel.cis.vsl.abc.front.c.preproc;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class AcslLexer extends Lexer {
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

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public AcslLexer() {} 
	public AcslLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public AcslLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "AcslLexer.g"; }

	// $ANTLR start "BOOLEAN"
	public final void mBOOLEAN() throws RecognitionException {
		try {
			int _type = BOOLEAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:20:9: ( 'boolean' )
			// AcslLexer.g:20:13: 'boolean'
			{
			match("boolean"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOLEAN"

	// $ANTLR start "INTEGER"
	public final void mINTEGER() throws RecognitionException {
		try {
			int _type = INTEGER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:21:9: ( 'integer' )
			// AcslLexer.g:21:13: 'integer'
			{
			match("integer"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER"

	// $ANTLR start "REAL"
	public final void mREAL() throws RecognitionException {
		try {
			int _type = REAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:22:9: ( 'real' )
			// AcslLexer.g:22:13: 'real'
			{
			match("real"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REAL"

	// $ANTLR start "CHAR"
	public final void mCHAR() throws RecognitionException {
		try {
			int _type = CHAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:24:7: ( 'char' )
			// AcslLexer.g:24:9: 'char'
			{
			match("char"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CHAR"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:25:9: ( 'double' )
			// AcslLexer.g:25:11: 'double'
			{
			match("double"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:26:8: ( 'float' )
			// AcslLexer.g:26:10: 'float'
			{
			match("float"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:27:7: ( 'int' )
			// AcslLexer.g:27:9: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "LONG"
	public final void mLONG() throws RecognitionException {
		try {
			int _type = LONG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:28:7: ( 'long' )
			// AcslLexer.g:28:9: 'long'
			{
			match("long"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LONG"

	// $ANTLR start "SHORT"
	public final void mSHORT() throws RecognitionException {
		try {
			int _type = SHORT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:29:8: ( 'short' )
			// AcslLexer.g:29:10: 'short'
			{
			match("short"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SHORT"

	// $ANTLR start "VOID"
	public final void mVOID() throws RecognitionException {
		try {
			int _type = VOID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:30:7: ( 'void' )
			// AcslLexer.g:30:9: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOID"

	// $ANTLR start "REQUIRES"
	public final void mREQUIRES() throws RecognitionException {
		try {
			int _type = REQUIRES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:33:9: ( 'requires' )
			// AcslLexer.g:33:13: 'requires'
			{
			match("requires"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REQUIRES"

	// $ANTLR start "TERMINATES"
	public final void mTERMINATES() throws RecognitionException {
		try {
			int _type = TERMINATES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:34:11: ( 'terminates' )
			// AcslLexer.g:34:13: 'terminates'
			{
			match("terminates"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TERMINATES"

	// $ANTLR start "DECREASES"
	public final void mDECREASES() throws RecognitionException {
		try {
			int _type = DECREASES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:35:10: ( 'decreases' )
			// AcslLexer.g:35:13: 'decreases'
			{
			match("decreases"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DECREASES"

	// $ANTLR start "GUARDS"
	public final void mGUARDS() throws RecognitionException {
		try {
			int _type = GUARDS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:36:9: ( 'guards' )
			// AcslLexer.g:36:13: 'guards'
			{
			match("guards"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GUARDS"

	// $ANTLR start "ASSIGNS"
	public final void mASSIGNS() throws RecognitionException {
		try {
			int _type = ASSIGNS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:37:9: ( 'assigns' )
			// AcslLexer.g:37:13: 'assigns'
			{
			match("assigns"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSIGNS"

	// $ANTLR start "ENSURES"
	public final void mENSURES() throws RecognitionException {
		try {
			int _type = ENSURES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:38:9: ( 'ensures' )
			// AcslLexer.g:38:13: 'ensures'
			{
			match("ensures"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ENSURES"

	// $ANTLR start "ALLOC"
	public final void mALLOC() throws RecognitionException {
		try {
			int _type = ALLOC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:39:9: ( 'allocates' )
			// AcslLexer.g:39:13: 'allocates'
			{
			match("allocates"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALLOC"

	// $ANTLR start "BEHAVIORS"
	public final void mBEHAVIORS() throws RecognitionException {
		try {
			int _type = BEHAVIORS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:40:10: ( 'behaviors' )
			// AcslLexer.g:40:14: 'behaviors'
			{
			match("behaviors"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BEHAVIORS"

	// $ANTLR start "BEHAVIOR"
	public final void mBEHAVIOR() throws RecognitionException {
		try {
			int _type = BEHAVIOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:41:9: ( 'behavior' )
			// AcslLexer.g:41:13: 'behavior'
			{
			match("behavior"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BEHAVIOR"

	// $ANTLR start "ASSUMES"
	public final void mASSUMES() throws RecognitionException {
		try {
			int _type = ASSUMES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:42:9: ( 'assumes' )
			// AcslLexer.g:42:13: 'assumes'
			{
			match("assumes"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSUMES"

	// $ANTLR start "COMPLETE"
	public final void mCOMPLETE() throws RecognitionException {
		try {
			int _type = COMPLETE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:43:9: ( 'complete' )
			// AcslLexer.g:43:13: 'complete'
			{
			match("complete"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMPLETE"

	// $ANTLR start "DISJOINT"
	public final void mDISJOINT() throws RecognitionException {
		try {
			int _type = DISJOINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:44:9: ( 'disjoint' )
			// AcslLexer.g:44:13: 'disjoint'
			{
			match("disjoint"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DISJOINT"

	// $ANTLR start "DEPENDS"
	public final void mDEPENDS() throws RecognitionException {
		try {
			int _type = DEPENDS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:46:9: ( 'depends' )
			// AcslLexer.g:46:13: 'depends'
			{
			match("depends"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DEPENDS"

	// $ANTLR start "READS"
	public final void mREADS() throws RecognitionException {
		try {
			int _type = READS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:47:9: ( 'reads' )
			// AcslLexer.g:47:13: 'reads'
			{
			match("reads"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "READS"

	// $ANTLR start "FREES"
	public final void mFREES() throws RecognitionException {
		try {
			int _type = FREES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:48:9: ( 'frees' )
			// AcslLexer.g:48:13: 'frees'
			{
			match("frees"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FREES"

	// $ANTLR start "EMPTY"
	public final void mEMPTY() throws RecognitionException {
		try {
			int _type = EMPTY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:52:9: ( '\\\\empty' )
			// AcslLexer.g:52:13: '\\\\empty'
			{
			match("\\empty"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EMPTY"

	// $ANTLR start "OLD"
	public final void mOLD() throws RecognitionException {
		try {
			int _type = OLD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:53:9: ( '\\\\old' )
			// AcslLexer.g:53:13: '\\\\old'
			{
			match("\\old"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OLD"

	// $ANTLR start "RESULT"
	public final void mRESULT() throws RecognitionException {
		try {
			int _type = RESULT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:54:9: ( '\\\\result' )
			// AcslLexer.g:54:13: '\\\\result'
			{
			match("\\result"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RESULT"

	// $ANTLR start "NOTHING"
	public final void mNOTHING() throws RecognitionException {
		try {
			int _type = NOTHING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:55:9: ( '\\\\nothing' )
			// AcslLexer.g:55:13: '\\\\nothing'
			{
			match("\\nothing"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOTHING"

	// $ANTLR start "UNION"
	public final void mUNION() throws RecognitionException {
		try {
			int _type = UNION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:56:9: ( '\\\\union' )
			// AcslLexer.g:56:13: '\\\\union'
			{
			match("\\union"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UNION"

	// $ANTLR start "INTER"
	public final void mINTER() throws RecognitionException {
		try {
			int _type = INTER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:57:9: ( '\\\\inter' )
			// AcslLexer.g:57:13: '\\\\inter'
			{
			match("\\inter"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTER"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException {
		try {
			int _type = TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:58:9: ( '\\\\true' )
			// AcslLexer.g:58:13: '\\\\true'
			{
			match("\\true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException {
		try {
			int _type = FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:59:9: ( '\\\\false' )
			// AcslLexer.g:59:13: '\\\\false'
			{
			match("\\false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE"

	// $ANTLR start "WITH"
	public final void mWITH() throws RecognitionException {
		try {
			int _type = WITH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:60:9: ( '\\\\with' )
			// AcslLexer.g:60:13: '\\\\with'
			{
			match("\\with"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WITH"

	// $ANTLR start "LET"
	public final void mLET() throws RecognitionException {
		try {
			int _type = LET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:61:9: ( '\\\\let' )
			// AcslLexer.g:61:13: '\\\\let'
			{
			match("\\let"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LET"

	// $ANTLR start "SIZEOF"
	public final void mSIZEOF() throws RecognitionException {
		try {
			int _type = SIZEOF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:62:9: ( 'sizeof' )
			// AcslLexer.g:62:13: 'sizeof'
			{
			match("sizeof"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SIZEOF"

	// $ANTLR start "FOR"
	public final void mFOR() throws RecognitionException {
		try {
			int _type = FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:63:9: ( 'for' )
			// AcslLexer.g:63:13: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOR"

	// $ANTLR start "READ"
	public final void mREAD() throws RecognitionException {
		try {
			int _type = READ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:64:9: ( '\\\\read' )
			// AcslLexer.g:64:13: '\\\\read'
			{
			match("\\read"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "READ"

	// $ANTLR start "WRITE"
	public final void mWRITE() throws RecognitionException {
		try {
			int _type = WRITE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:65:9: ( '\\\\write' )
			// AcslLexer.g:65:13: '\\\\write'
			{
			match("\\write"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WRITE"

	// $ANTLR start "CALL"
	public final void mCALL() throws RecognitionException {
		try {
			int _type = CALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:66:9: ( '\\\\call' )
			// AcslLexer.g:66:13: '\\\\call'
			{
			match("\\call"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CALL"

	// $ANTLR start "NOACT"
	public final void mNOACT() throws RecognitionException {
		try {
			int _type = NOACT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:67:9: ( '\\\\noact' )
			// AcslLexer.g:67:13: '\\\\noact'
			{
			match("\\noact"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOACT"

	// $ANTLR start "ANYACT"
	public final void mANYACT() throws RecognitionException {
		try {
			int _type = ANYACT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:68:9: ( '\\\\anyact' )
			// AcslLexer.g:68:13: '\\\\anyact'
			{
			match("\\anyact"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ANYACT"

	// $ANTLR start "FORALL"
	public final void mFORALL() throws RecognitionException {
		try {
			int _type = FORALL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:69:9: ( '\\\\forall' )
			// AcslLexer.g:69:13: '\\\\forall'
			{
			match("\\forall"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FORALL"

	// $ANTLR start "EXISTS"
	public final void mEXISTS() throws RecognitionException {
		try {
			int _type = EXISTS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:70:9: ( '\\\\exists' )
			// AcslLexer.g:70:13: '\\\\exists'
			{
			match("\\exists"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXISTS"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:73:9: ( '+' )
			// AcslLexer.g:73:13: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "SUB"
	public final void mSUB() throws RecognitionException {
		try {
			int _type = SUB;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:74:7: ( '-' )
			// AcslLexer.g:74:11: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUB"

	// $ANTLR start "STAR"
	public final void mSTAR() throws RecognitionException {
		try {
			int _type = STAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:75:9: ( '*' )
			// AcslLexer.g:75:13: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STAR"

	// $ANTLR start "DIVIDE"
	public final void mDIVIDE() throws RecognitionException {
		try {
			int _type = DIVIDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:76:9: ( '/' )
			// AcslLexer.g:76:13: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIVIDE"

	// $ANTLR start "MOD"
	public final void mMOD() throws RecognitionException {
		try {
			int _type = MOD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:77:9: ( '%' )
			// AcslLexer.g:77:13: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MOD"

	// $ANTLR start "SHIFTLEFT"
	public final void mSHIFTLEFT() throws RecognitionException {
		try {
			int _type = SHIFTLEFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:78:12: ( '<<' )
			// AcslLexer.g:78:16: '<<'
			{
			match("<<"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SHIFTLEFT"

	// $ANTLR start "SHIFTRIGHT"
	public final void mSHIFTRIGHT() throws RecognitionException {
		try {
			int _type = SHIFTRIGHT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:79:13: ( '>>' )
			// AcslLexer.g:79:17: '>>'
			{
			match(">>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SHIFTRIGHT"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:80:9: ( '==' )
			// AcslLexer.g:80:13: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "NEQ"
	public final void mNEQ() throws RecognitionException {
		try {
			int _type = NEQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:81:9: ( '!=' )
			// AcslLexer.g:81:13: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEQ"

	// $ANTLR start "LTE"
	public final void mLTE() throws RecognitionException {
		try {
			int _type = LTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:82:9: ( '<=' )
			// AcslLexer.g:82:13: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LTE"

	// $ANTLR start "GTE"
	public final void mGTE() throws RecognitionException {
		try {
			int _type = GTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:83:9: ( '>=' )
			// AcslLexer.g:83:13: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GTE"

	// $ANTLR start "LT"
	public final void mLT() throws RecognitionException {
		try {
			int _type = LT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:84:9: ( '<' )
			// AcslLexer.g:84:13: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LT"

	// $ANTLR start "GT"
	public final void mGT() throws RecognitionException {
		try {
			int _type = GT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:85:9: ( '>' )
			// AcslLexer.g:85:13: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GT"

	// $ANTLR start "LAND"
	public final void mLAND() throws RecognitionException {
		try {
			int _type = LAND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:86:9: ( '&&' )
			// AcslLexer.g:86:13: '&&'
			{
			match("&&"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LAND"

	// $ANTLR start "LOR"
	public final void mLOR() throws RecognitionException {
		try {
			int _type = LOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:87:9: ( '||' )
			// AcslLexer.g:87:13: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOR"

	// $ANTLR start "BAR"
	public final void mBAR() throws RecognitionException {
		try {
			int _type = BAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:88:9: ( '|' )
			// AcslLexer.g:88:13: '|'
			{
			match('|'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BAR"

	// $ANTLR start "XOR"
	public final void mXOR() throws RecognitionException {
		try {
			int _type = XOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:89:9: ( '^^' )
			// AcslLexer.g:89:13: '^^'
			{
			match("^^"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "XOR"

	// $ANTLR start "AMPERSAND"
	public final void mAMPERSAND() throws RecognitionException {
		try {
			int _type = AMPERSAND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:90:14: ( '&' )
			// AcslLexer.g:90:18: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AMPERSAND"

	// $ANTLR start "IMPLY"
	public final void mIMPLY() throws RecognitionException {
		try {
			int _type = IMPLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:91:9: ( '==>' )
			// AcslLexer.g:91:13: '==>'
			{
			match("==>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IMPLY"

	// $ANTLR start "EQUIV"
	public final void mEQUIV() throws RecognitionException {
		try {
			int _type = EQUIV;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:92:9: ( '<==>' )
			// AcslLexer.g:92:13: '<==>'
			{
			match("<==>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUIV"

	// $ANTLR start "ARROW"
	public final void mARROW() throws RecognitionException {
		try {
			int _type = ARROW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:93:9: ( '->' )
			// AcslLexer.g:93:13: '->'
			{
			match("->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARROW"

	// $ANTLR start "BITXOR"
	public final void mBITXOR() throws RecognitionException {
		try {
			int _type = BITXOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:94:9: ( '^' )
			// AcslLexer.g:94:13: '^'
			{
			match('^'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BITXOR"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:95:9: ( '!' )
			// AcslLexer.g:95:13: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "COMP"
	public final void mCOMP() throws RecognitionException {
		try {
			int _type = COMP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:96:9: ( '~' )
			// AcslLexer.g:96:13: '~'
			{
			match('~'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMP"

	// $ANTLR start "ELLIPSIS"
	public final void mELLIPSIS() throws RecognitionException {
		try {
			int _type = ELLIPSIS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:97:9: ( '...' )
			// AcslLexer.g:97:13: '...'
			{
			match("..."); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELLIPSIS"

	// $ANTLR start "DOTDOT"
	public final void mDOTDOT() throws RecognitionException {
		try {
			int _type = DOTDOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:98:9: ( '..' )
			// AcslLexer.g:98:13: '..'
			{
			match(".."); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOTDOT"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:99:9: ( '.' )
			// AcslLexer.g:99:13: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "QUESTION"
	public final void mQUESTION() throws RecognitionException {
		try {
			int _type = QUESTION;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:100:9: ( '?' )
			// AcslLexer.g:100:13: '?'
			{
			match('?'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "QUESTION"

	// $ANTLR start "COLON"
	public final void mCOLON() throws RecognitionException {
		try {
			int _type = COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:101:9: ( ':' )
			// AcslLexer.g:101:13: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON"

	// $ANTLR start "SEMICOL"
	public final void mSEMICOL() throws RecognitionException {
		try {
			int _type = SEMICOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:102:9: ( ';' )
			// AcslLexer.g:102:13: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOL"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:103:9: ( ',' )
			// AcslLexer.g:103:13: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "LPAREN"
	public final void mLPAREN() throws RecognitionException {
		try {
			int _type = LPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:104:9: ( '(' )
			// AcslLexer.g:104:13: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LPAREN"

	// $ANTLR start "RPAREN"
	public final void mRPAREN() throws RecognitionException {
		try {
			int _type = RPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:105:9: ( ')' )
			// AcslLexer.g:105:13: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RPAREN"

	// $ANTLR start "LCURLY"
	public final void mLCURLY() throws RecognitionException {
		try {
			int _type = LCURLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:106:9: ( '{' )
			// AcslLexer.g:106:13: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LCURLY"

	// $ANTLR start "RCURLY"
	public final void mRCURLY() throws RecognitionException {
		try {
			int _type = RCURLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:107:9: ( '}' )
			// AcslLexer.g:107:13: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RCURLY"

	// $ANTLR start "LSQUARE"
	public final void mLSQUARE() throws RecognitionException {
		try {
			int _type = LSQUARE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:108:9: ( '[' )
			// AcslLexer.g:108:13: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LSQUARE"

	// $ANTLR start "RSQUARE"
	public final void mRSQUARE() throws RecognitionException {
		try {
			int _type = RSQUARE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:109:9: ( ']' )
			// AcslLexer.g:109:13: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RSQUARE"

	// $ANTLR start "ASSIGN"
	public final void mASSIGN() throws RecognitionException {
		try {
			int _type = ASSIGN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:110:9: ( '=' )
			// AcslLexer.g:110:13: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSIGN"

	// $ANTLR start "HASH"
	public final void mHASH() throws RecognitionException {
		try {
			int _type = HASH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:111:9: ( '#' )
			// AcslLexer.g:111:13: '#'
			{
			match('#'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HASH"

	// $ANTLR start "INTEGER_CONSTANT"
	public final void mINTEGER_CONSTANT() throws RecognitionException {
		try {
			int _type = INTEGER_CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:116:3: ( DecimalConstant ( IntegerSuffix )? | OctalConstant ( IntegerSuffix )? | HexadecimalConstant ( IntegerSuffix )? )
			int alt4=3;
			int LA4_0 = input.LA(1);
			if ( ((LA4_0 >= '1' && LA4_0 <= '9')) ) {
				alt4=1;
			}
			else if ( (LA4_0=='0') ) {
				int LA4_2 = input.LA(2);
				if ( (LA4_2=='X'||LA4_2=='x') ) {
					alt4=3;
				}

				else {
					alt4=2;
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// AcslLexer.g:116:5: DecimalConstant ( IntegerSuffix )?
					{
					mDecimalConstant(); 

					// AcslLexer.g:116:21: ( IntegerSuffix )?
					int alt1=2;
					int LA1_0 = input.LA(1);
					if ( (LA1_0=='L'||LA1_0=='U'||LA1_0=='l'||LA1_0=='u') ) {
						alt1=1;
					}
					switch (alt1) {
						case 1 :
							// AcslLexer.g:116:21: IntegerSuffix
							{
							mIntegerSuffix(); 

							}
							break;

					}

					}
					break;
				case 2 :
					// AcslLexer.g:117:5: OctalConstant ( IntegerSuffix )?
					{
					mOctalConstant(); 

					// AcslLexer.g:117:19: ( IntegerSuffix )?
					int alt2=2;
					int LA2_0 = input.LA(1);
					if ( (LA2_0=='L'||LA2_0=='U'||LA2_0=='l'||LA2_0=='u') ) {
						alt2=1;
					}
					switch (alt2) {
						case 1 :
							// AcslLexer.g:117:19: IntegerSuffix
							{
							mIntegerSuffix(); 

							}
							break;

					}

					}
					break;
				case 3 :
					// AcslLexer.g:118:5: HexadecimalConstant ( IntegerSuffix )?
					{
					mHexadecimalConstant(); 

					// AcslLexer.g:118:25: ( IntegerSuffix )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0=='L'||LA3_0=='U'||LA3_0=='l'||LA3_0=='u') ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// AcslLexer.g:118:25: IntegerSuffix
							{
							mIntegerSuffix(); 

							}
							break;

					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER_CONSTANT"

	// $ANTLR start "DecimalConstant"
	public final void mDecimalConstant() throws RecognitionException {
		try {
			// AcslLexer.g:122:17: ( NonZeroDigit ( Digit )* )
			// AcslLexer.g:122:19: NonZeroDigit ( Digit )*
			{
			mNonZeroDigit(); 

			// AcslLexer.g:122:32: ( Digit )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// AcslLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop5;
				}
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DecimalConstant"

	// $ANTLR start "IntegerSuffix"
	public final void mIntegerSuffix() throws RecognitionException {
		try {
			// AcslLexer.g:126:15: ( UnsignedSuffix ( LongSuffix )? | UnsignedSuffix LongLongSuffix | LongSuffix ( UnsignedSuffix )? | LongLongSuffix ( UnsignedSuffix )? )
			int alt9=4;
			switch ( input.LA(1) ) {
			case 'U':
			case 'u':
				{
				switch ( input.LA(2) ) {
				case 'l':
					{
					int LA9_5 = input.LA(3);
					if ( (LA9_5=='l') ) {
						alt9=2;
					}

					else {
						alt9=1;
					}

					}
					break;
				case 'L':
					{
					int LA9_6 = input.LA(3);
					if ( (LA9_6=='L') ) {
						alt9=2;
					}

					else {
						alt9=1;
					}

					}
					break;
				default:
					alt9=1;
				}
				}
				break;
			case 'l':
				{
				int LA9_2 = input.LA(2);
				if ( (LA9_2=='l') ) {
					alt9=4;
				}

				else {
					alt9=3;
				}

				}
				break;
			case 'L':
				{
				int LA9_3 = input.LA(2);
				if ( (LA9_3=='L') ) {
					alt9=4;
				}

				else {
					alt9=3;
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// AcslLexer.g:126:17: UnsignedSuffix ( LongSuffix )?
					{
					mUnsignedSuffix(); 

					// AcslLexer.g:126:32: ( LongSuffix )?
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0=='L'||LA6_0=='l') ) {
						alt6=1;
					}
					switch (alt6) {
						case 1 :
							// AcslLexer.g:
							{
							if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 2 :
					// AcslLexer.g:127:5: UnsignedSuffix LongLongSuffix
					{
					mUnsignedSuffix(); 

					mLongLongSuffix(); 

					}
					break;
				case 3 :
					// AcslLexer.g:128:5: LongSuffix ( UnsignedSuffix )?
					{
					mLongSuffix(); 

					// AcslLexer.g:128:16: ( UnsignedSuffix )?
					int alt7=2;
					int LA7_0 = input.LA(1);
					if ( (LA7_0=='U'||LA7_0=='u') ) {
						alt7=1;
					}
					switch (alt7) {
						case 1 :
							// AcslLexer.g:
							{
							if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 4 :
					// AcslLexer.g:129:5: LongLongSuffix ( UnsignedSuffix )?
					{
					mLongLongSuffix(); 

					// AcslLexer.g:129:20: ( UnsignedSuffix )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0=='U'||LA8_0=='u') ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// AcslLexer.g:
							{
							if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IntegerSuffix"

	// $ANTLR start "UnsignedSuffix"
	public final void mUnsignedSuffix() throws RecognitionException {
		try {
			// AcslLexer.g:133:16: ( 'u' | 'U' )
			// AcslLexer.g:
			{
			if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UnsignedSuffix"

	// $ANTLR start "LongSuffix"
	public final void mLongSuffix() throws RecognitionException {
		try {
			// AcslLexer.g:136:12: ( 'l' | 'L' )
			// AcslLexer.g:
			{
			if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LongSuffix"

	// $ANTLR start "LongLongSuffix"
	public final void mLongLongSuffix() throws RecognitionException {
		try {
			// AcslLexer.g:139:16: ( 'll' | 'LL' )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0=='l') ) {
				alt10=1;
			}
			else if ( (LA10_0=='L') ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// AcslLexer.g:139:18: 'll'
					{
					match("ll"); 

					}
					break;
				case 2 :
					// AcslLexer.g:139:25: 'LL'
					{
					match("LL"); 

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LongLongSuffix"

	// $ANTLR start "OctalConstant"
	public final void mOctalConstant() throws RecognitionException {
		try {
			// AcslLexer.g:142:15: ( Zero ( OctalDigit )* ( IntegerSuffix )? )
			// AcslLexer.g:142:17: Zero ( OctalDigit )* ( IntegerSuffix )?
			{
			mZero(); 

			// AcslLexer.g:142:22: ( OctalDigit )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( ((LA11_0 >= '0' && LA11_0 <= '7')) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// AcslLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop11;
				}
			}

			// AcslLexer.g:142:34: ( IntegerSuffix )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='L'||LA12_0=='U'||LA12_0=='l'||LA12_0=='u') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// AcslLexer.g:142:34: IntegerSuffix
					{
					mIntegerSuffix(); 

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OctalConstant"

	// $ANTLR start "HexadecimalConstant"
	public final void mHexadecimalConstant() throws RecognitionException {
		try {
			// AcslLexer.g:146:3: ( HexPrefix ( HexadecimalDigit )+ ( IntegerSuffix )? )
			// AcslLexer.g:146:5: HexPrefix ( HexadecimalDigit )+ ( IntegerSuffix )?
			{
			mHexPrefix(); 

			// AcslLexer.g:146:15: ( HexadecimalDigit )+
			int cnt13=0;
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( ((LA13_0 >= '0' && LA13_0 <= '9')||(LA13_0 >= 'A' && LA13_0 <= 'F')||(LA13_0 >= 'a' && LA13_0 <= 'f')) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// AcslLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt13 >= 1 ) break loop13;
					EarlyExitException eee = new EarlyExitException(13, input);
					throw eee;
				}
				cnt13++;
			}

			// AcslLexer.g:146:33: ( IntegerSuffix )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0=='L'||LA14_0=='U'||LA14_0=='l'||LA14_0=='u') ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// AcslLexer.g:146:33: IntegerSuffix
					{
					mIntegerSuffix(); 

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexadecimalConstant"

	// $ANTLR start "HexPrefix"
	public final void mHexPrefix() throws RecognitionException {
		try {
			// AcslLexer.g:149:11: ( Zero ( 'x' | 'X' ) )
			// AcslLexer.g:149:13: Zero ( 'x' | 'X' )
			{
			mZero(); 

			if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexPrefix"

	// $ANTLR start "FLOATING_CONSTANT"
	public final void mFLOATING_CONSTANT() throws RecognitionException {
		try {
			int _type = FLOATING_CONSTANT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:154:3: ( DecimalFloatingConstant | HexadecimalFloatingConstant )
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0=='0') ) {
				int LA15_1 = input.LA(2);
				if ( (LA15_1=='.'||(LA15_1 >= '0' && LA15_1 <= '9')||LA15_1=='E'||LA15_1=='e') ) {
					alt15=1;
				}
				else if ( (LA15_1=='X'||LA15_1=='x') ) {
					alt15=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 15, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA15_0=='.'||(LA15_0 >= '1' && LA15_0 <= '9')) ) {
				alt15=1;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}

			switch (alt15) {
				case 1 :
					// AcslLexer.g:154:5: DecimalFloatingConstant
					{
					mDecimalFloatingConstant(); 

					}
					break;
				case 2 :
					// AcslLexer.g:155:5: HexadecimalFloatingConstant
					{
					mHexadecimalFloatingConstant(); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOATING_CONSTANT"

	// $ANTLR start "DecimalFloatingConstant"
	public final void mDecimalFloatingConstant() throws RecognitionException {
		try {
			// AcslLexer.g:160:3: ( FractionalConstant ( ExponentPart )? ( FloatingSuffix )? | ( Digit )+ ExponentPart ( FloatingSuffix )? )
			int alt20=2;
			alt20 = dfa20.predict(input);
			switch (alt20) {
				case 1 :
					// AcslLexer.g:160:5: FractionalConstant ( ExponentPart )? ( FloatingSuffix )?
					{
					mFractionalConstant(); 

					// AcslLexer.g:160:24: ( ExponentPart )?
					int alt16=2;
					int LA16_0 = input.LA(1);
					if ( (LA16_0=='E'||LA16_0=='e') ) {
						alt16=1;
					}
					switch (alt16) {
						case 1 :
							// AcslLexer.g:160:24: ExponentPart
							{
							mExponentPart(); 

							}
							break;

					}

					// AcslLexer.g:160:38: ( FloatingSuffix )?
					int alt17=2;
					int LA17_0 = input.LA(1);
					if ( (LA17_0=='F'||LA17_0=='L'||LA17_0=='f'||LA17_0=='l') ) {
						alt17=1;
					}
					switch (alt17) {
						case 1 :
							// AcslLexer.g:
							{
							if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 2 :
					// AcslLexer.g:161:5: ( Digit )+ ExponentPart ( FloatingSuffix )?
					{
					// AcslLexer.g:161:5: ( Digit )+
					int cnt18=0;
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( ((LA18_0 >= '0' && LA18_0 <= '9')) ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt18 >= 1 ) break loop18;
							EarlyExitException eee = new EarlyExitException(18, input);
							throw eee;
						}
						cnt18++;
					}

					mExponentPart(); 

					// AcslLexer.g:161:25: ( FloatingSuffix )?
					int alt19=2;
					int LA19_0 = input.LA(1);
					if ( (LA19_0=='F'||LA19_0=='L'||LA19_0=='f'||LA19_0=='l') ) {
						alt19=1;
					}
					switch (alt19) {
						case 1 :
							// AcslLexer.g:
							{
							if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DecimalFloatingConstant"

	// $ANTLR start "FractionalConstant"
	public final void mFractionalConstant() throws RecognitionException {
		try {
			// AcslLexer.g:166:3: ( ( Digit )* DOT ( Digit )+ | ( Digit )+ DOT )
			int alt24=2;
			alt24 = dfa24.predict(input);
			switch (alt24) {
				case 1 :
					// AcslLexer.g:166:5: ( Digit )* DOT ( Digit )+
					{
					// AcslLexer.g:166:5: ( Digit )*
					loop21:
					while (true) {
						int alt21=2;
						int LA21_0 = input.LA(1);
						if ( ((LA21_0 >= '0' && LA21_0 <= '9')) ) {
							alt21=1;
						}

						switch (alt21) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop21;
						}
					}

					mDOT(); 

					// AcslLexer.g:166:16: ( Digit )+
					int cnt22=0;
					loop22:
					while (true) {
						int alt22=2;
						int LA22_0 = input.LA(1);
						if ( ((LA22_0 >= '0' && LA22_0 <= '9')) ) {
							alt22=1;
						}

						switch (alt22) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt22 >= 1 ) break loop22;
							EarlyExitException eee = new EarlyExitException(22, input);
							throw eee;
						}
						cnt22++;
					}

					}
					break;
				case 2 :
					// AcslLexer.g:167:5: ( Digit )+ DOT
					{
					// AcslLexer.g:167:5: ( Digit )+
					int cnt23=0;
					loop23:
					while (true) {
						int alt23=2;
						int LA23_0 = input.LA(1);
						if ( ((LA23_0 >= '0' && LA23_0 <= '9')) ) {
							alt23=1;
						}

						switch (alt23) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt23 >= 1 ) break loop23;
							EarlyExitException eee = new EarlyExitException(23, input);
							throw eee;
						}
						cnt23++;
					}

					mDOT(); 

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FractionalConstant"

	// $ANTLR start "ExponentPart"
	public final void mExponentPart() throws RecognitionException {
		try {
			// AcslLexer.g:171:14: ( ( 'e' | 'E' ) ( '+' | '-' )? ( Digit )+ )
			// AcslLexer.g:171:16: ( 'e' | 'E' ) ( '+' | '-' )? ( Digit )+
			{
			if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// AcslLexer.g:171:28: ( '+' | '-' )?
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0=='+'||LA25_0=='-') ) {
				alt25=1;
			}
			switch (alt25) {
				case 1 :
					// AcslLexer.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			// AcslLexer.g:171:41: ( Digit )+
			int cnt26=0;
			loop26:
			while (true) {
				int alt26=2;
				int LA26_0 = input.LA(1);
				if ( ((LA26_0 >= '0' && LA26_0 <= '9')) ) {
					alt26=1;
				}

				switch (alt26) {
				case 1 :
					// AcslLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt26 >= 1 ) break loop26;
					EarlyExitException eee = new EarlyExitException(26, input);
					throw eee;
				}
				cnt26++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ExponentPart"

	// $ANTLR start "FloatingSuffix"
	public final void mFloatingSuffix() throws RecognitionException {
		try {
			// AcslLexer.g:174:16: ( 'f' | 'l' | 'F' | 'L' )
			// AcslLexer.g:
			{
			if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FloatingSuffix"

	// $ANTLR start "HexadecimalFloatingConstant"
	public final void mHexadecimalFloatingConstant() throws RecognitionException {
		try {
			// AcslLexer.g:178:3: ( HexPrefix HexFractionalConstant BinaryExponentPart ( FloatingSuffix )? | HexPrefix ( HexadecimalDigit )+ BinaryExponentPart ( FloatingSuffix )? )
			int alt30=2;
			alt30 = dfa30.predict(input);
			switch (alt30) {
				case 1 :
					// AcslLexer.g:178:5: HexPrefix HexFractionalConstant BinaryExponentPart ( FloatingSuffix )?
					{
					mHexPrefix(); 

					mHexFractionalConstant(); 

					mBinaryExponentPart(); 

					// AcslLexer.g:179:4: ( FloatingSuffix )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0=='F'||LA27_0=='L'||LA27_0=='f'||LA27_0=='l') ) {
						alt27=1;
					}
					switch (alt27) {
						case 1 :
							// AcslLexer.g:
							{
							if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;
				case 2 :
					// AcslLexer.g:180:5: HexPrefix ( HexadecimalDigit )+ BinaryExponentPart ( FloatingSuffix )?
					{
					mHexPrefix(); 

					// AcslLexer.g:180:15: ( HexadecimalDigit )+
					int cnt28=0;
					loop28:
					while (true) {
						int alt28=2;
						int LA28_0 = input.LA(1);
						if ( ((LA28_0 >= '0' && LA28_0 <= '9')||(LA28_0 >= 'A' && LA28_0 <= 'F')||(LA28_0 >= 'a' && LA28_0 <= 'f')) ) {
							alt28=1;
						}

						switch (alt28) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt28 >= 1 ) break loop28;
							EarlyExitException eee = new EarlyExitException(28, input);
							throw eee;
						}
						cnt28++;
					}

					mBinaryExponentPart(); 

					// AcslLexer.g:181:4: ( FloatingSuffix )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0=='F'||LA29_0=='L'||LA29_0=='f'||LA29_0=='l') ) {
						alt29=1;
					}
					switch (alt29) {
						case 1 :
							// AcslLexer.g:
							{
							if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexadecimalFloatingConstant"

	// $ANTLR start "HexFractionalConstant"
	public final void mHexFractionalConstant() throws RecognitionException {
		try {
			// AcslLexer.g:186:3: ( ( HexadecimalDigit )* DOT ( HexadecimalDigit )+ | ( HexadecimalDigit )+ DOT )
			int alt34=2;
			alt34 = dfa34.predict(input);
			switch (alt34) {
				case 1 :
					// AcslLexer.g:186:5: ( HexadecimalDigit )* DOT ( HexadecimalDigit )+
					{
					// AcslLexer.g:186:5: ( HexadecimalDigit )*
					loop31:
					while (true) {
						int alt31=2;
						int LA31_0 = input.LA(1);
						if ( ((LA31_0 >= '0' && LA31_0 <= '9')||(LA31_0 >= 'A' && LA31_0 <= 'F')||(LA31_0 >= 'a' && LA31_0 <= 'f')) ) {
							alt31=1;
						}

						switch (alt31) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop31;
						}
					}

					mDOT(); 

					// AcslLexer.g:186:27: ( HexadecimalDigit )+
					int cnt32=0;
					loop32:
					while (true) {
						int alt32=2;
						int LA32_0 = input.LA(1);
						if ( ((LA32_0 >= '0' && LA32_0 <= '9')||(LA32_0 >= 'A' && LA32_0 <= 'F')||(LA32_0 >= 'a' && LA32_0 <= 'f')) ) {
							alt32=1;
						}

						switch (alt32) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt32 >= 1 ) break loop32;
							EarlyExitException eee = new EarlyExitException(32, input);
							throw eee;
						}
						cnt32++;
					}

					}
					break;
				case 2 :
					// AcslLexer.g:187:5: ( HexadecimalDigit )+ DOT
					{
					// AcslLexer.g:187:5: ( HexadecimalDigit )+
					int cnt33=0;
					loop33:
					while (true) {
						int alt33=2;
						int LA33_0 = input.LA(1);
						if ( ((LA33_0 >= '0' && LA33_0 <= '9')||(LA33_0 >= 'A' && LA33_0 <= 'F')||(LA33_0 >= 'a' && LA33_0 <= 'f')) ) {
							alt33=1;
						}

						switch (alt33) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt33 >= 1 ) break loop33;
							EarlyExitException eee = new EarlyExitException(33, input);
							throw eee;
						}
						cnt33++;
					}

					mDOT(); 

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexFractionalConstant"

	// $ANTLR start "BinaryExponentPart"
	public final void mBinaryExponentPart() throws RecognitionException {
		try {
			// AcslLexer.g:192:3: ( ( 'p' | 'P' ) ( '+' | '-' )? ( Digit )+ )
			// AcslLexer.g:192:5: ( 'p' | 'P' ) ( '+' | '-' )? ( Digit )+
			{
			if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// AcslLexer.g:192:17: ( '+' | '-' )?
			int alt35=2;
			int LA35_0 = input.LA(1);
			if ( (LA35_0=='+'||LA35_0=='-') ) {
				alt35=1;
			}
			switch (alt35) {
				case 1 :
					// AcslLexer.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			// AcslLexer.g:192:30: ( Digit )+
			int cnt36=0;
			loop36:
			while (true) {
				int alt36=2;
				int LA36_0 = input.LA(1);
				if ( ((LA36_0 >= '0' && LA36_0 <= '9')) ) {
					alt36=1;
				}

				switch (alt36) {
				case 1 :
					// AcslLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt36 >= 1 ) break loop36;
					EarlyExitException eee = new EarlyExitException(36, input);
					throw eee;
				}
				cnt36++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BinaryExponentPart"

	// $ANTLR start "PP_NUMBER"
	public final void mPP_NUMBER() throws RecognitionException {
		try {
			int _type = PP_NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:200:11: ( ( '.' )? Digit ( '.' | IdentifierNonDigit | Digit | ( 'e' | 'E' | 'p' | 'P' ) ( '+' | '-' ) )* )
			// AcslLexer.g:200:13: ( '.' )? Digit ( '.' | IdentifierNonDigit | Digit | ( 'e' | 'E' | 'p' | 'P' ) ( '+' | '-' ) )*
			{
			// AcslLexer.g:200:13: ( '.' )?
			int alt37=2;
			int LA37_0 = input.LA(1);
			if ( (LA37_0=='.') ) {
				alt37=1;
			}
			switch (alt37) {
				case 1 :
					// AcslLexer.g:200:13: '.'
					{
					match('.'); 
					}
					break;

			}

			mDigit(); 

			// AcslLexer.g:201:4: ( '.' | IdentifierNonDigit | Digit | ( 'e' | 'E' | 'p' | 'P' ) ( '+' | '-' ) )*
			loop38:
			while (true) {
				int alt38=5;
				switch ( input.LA(1) ) {
				case '.':
					{
					alt38=1;
					}
					break;
				case '$':
				case 'A':
				case 'B':
				case 'C':
				case 'D':
				case 'F':
				case 'G':
				case 'H':
				case 'I':
				case 'J':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
				case 'O':
				case 'Q':
				case 'R':
				case 'S':
				case 'T':
				case 'U':
				case 'V':
				case 'W':
				case 'X':
				case 'Y':
				case 'Z':
				case '\\':
				case '_':
				case 'a':
				case 'b':
				case 'c':
				case 'd':
				case 'f':
				case 'g':
				case 'h':
				case 'i':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case 'n':
				case 'o':
				case 'q':
				case 'r':
				case 's':
				case 't':
				case 'u':
				case 'v':
				case 'w':
				case 'x':
				case 'y':
				case 'z':
					{
					alt38=2;
					}
					break;
				case 'E':
				case 'P':
				case 'e':
				case 'p':
					{
					int LA38_4 = input.LA(2);
					if ( (LA38_4=='+'||LA38_4=='-') ) {
						alt38=4;
					}
					else {
						alt38=2;
					}

					}
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					{
					alt38=3;
					}
					break;
				}
				switch (alt38) {
				case 1 :
					// AcslLexer.g:201:6: '.'
					{
					match('.'); 
					}
					break;
				case 2 :
					// AcslLexer.g:202:6: IdentifierNonDigit
					{
					mIdentifierNonDigit(); 

					}
					break;
				case 3 :
					// AcslLexer.g:203:6: Digit
					{
					mDigit(); 

					}
					break;
				case 4 :
					// AcslLexer.g:204:6: ( 'e' | 'E' | 'p' | 'P' ) ( '+' | '-' )
					{
					if ( input.LA(1)=='E'||input.LA(1)=='P'||input.LA(1)=='e'||input.LA(1)=='p' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop38;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PP_NUMBER"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			int _type = STRING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:213:17: ( ( 'u8' | 'u' | 'U' | 'L' )? '\"' ( SChar )* '\"' )
			// AcslLexer.g:213:19: ( 'u8' | 'u' | 'U' | 'L' )? '\"' ( SChar )* '\"'
			{
			// AcslLexer.g:213:19: ( 'u8' | 'u' | 'U' | 'L' )?
			int alt39=5;
			switch ( input.LA(1) ) {
				case 'u':
					{
					int LA39_1 = input.LA(2);
					if ( (LA39_1=='8') ) {
						alt39=1;
					}
					else if ( (LA39_1=='\"') ) {
						alt39=2;
					}
					}
					break;
				case 'U':
					{
					alt39=3;
					}
					break;
				case 'L':
					{
					alt39=4;
					}
					break;
			}
			switch (alt39) {
				case 1 :
					// AcslLexer.g:213:20: 'u8'
					{
					match("u8"); 

					}
					break;
				case 2 :
					// AcslLexer.g:213:27: 'u'
					{
					match('u'); 
					}
					break;
				case 3 :
					// AcslLexer.g:213:33: 'U'
					{
					match('U'); 
					}
					break;
				case 4 :
					// AcslLexer.g:213:39: 'L'
					{
					match('L'); 
					}
					break;

			}

			match('\"'); 
			// AcslLexer.g:213:49: ( SChar )*
			loop40:
			while (true) {
				int alt40=2;
				int LA40_0 = input.LA(1);
				if ( ((LA40_0 >= '\u0000' && LA40_0 <= '\t')||(LA40_0 >= '\u000B' && LA40_0 <= '!')||(LA40_0 >= '#' && LA40_0 <= '\uFFFF')) ) {
					alt40=1;
				}

				switch (alt40) {
				case 1 :
					// AcslLexer.g:213:49: SChar
					{
					mSChar(); 

					}
					break;

				default :
					break loop40;
				}
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "SChar"
	public final void mSChar() throws RecognitionException {
		try {
			// AcslLexer.g:217:8: (~ ( '\"' | '\\\\' | '\\n' ) | EscapeSequence )
			int alt41=2;
			int LA41_0 = input.LA(1);
			if ( ((LA41_0 >= '\u0000' && LA41_0 <= '\t')||(LA41_0 >= '\u000B' && LA41_0 <= '!')||(LA41_0 >= '#' && LA41_0 <= '[')||(LA41_0 >= ']' && LA41_0 <= '\uFFFF')) ) {
				alt41=1;
			}
			else if ( (LA41_0=='\\') ) {
				alt41=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}

			switch (alt41) {
				case 1 :
					// AcslLexer.g:217:10: ~ ( '\"' | '\\\\' | '\\n' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// AcslLexer.g:217:33: EscapeSequence
					{
					mEscapeSequence(); 

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SChar"

	// $ANTLR start "EscapeSequence"
	public final void mEscapeSequence() throws RecognitionException {
		try {
			// AcslLexer.g:221:16: ( '\\\\' ( '\\'' | '\"' | '\\?' | '\\\\' | 'a' | 'b' | 'f' | 'n' | 'r' | 't' | 'v' ) | OctalEscape )
			int alt42=2;
			int LA42_0 = input.LA(1);
			if ( (LA42_0=='\\') ) {
				int LA42_1 = input.LA(2);
				if ( (LA42_1=='\"'||LA42_1=='\''||LA42_1=='?'||LA42_1=='\\'||(LA42_1 >= 'a' && LA42_1 <= 'b')||LA42_1=='f'||LA42_1=='n'||LA42_1=='r'||LA42_1=='t'||LA42_1=='v') ) {
					alt42=1;
				}
				else if ( ((LA42_1 >= '0' && LA42_1 <= '7')) ) {
					alt42=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 42, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}

			switch (alt42) {
				case 1 :
					// AcslLexer.g:221:18: '\\\\' ( '\\'' | '\"' | '\\?' | '\\\\' | 'a' | 'b' | 'f' | 'n' | 'r' | 't' | 'v' )
					{
					match('\\'); 
					if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='?'||input.LA(1)=='\\'||(input.LA(1) >= 'a' && input.LA(1) <= 'b')||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t'||input.LA(1)=='v' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// AcslLexer.g:224:5: OctalEscape
					{
					mOctalEscape(); 

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EscapeSequence"

	// $ANTLR start "OctalEscape"
	public final void mOctalEscape() throws RecognitionException {
		try {
			// AcslLexer.g:227:13: ( '\\\\' OctalDigit ( OctalDigit ( OctalDigit )? )? )
			// AcslLexer.g:227:15: '\\\\' OctalDigit ( OctalDigit ( OctalDigit )? )?
			{
			match('\\'); 
			mOctalDigit(); 

			// AcslLexer.g:227:31: ( OctalDigit ( OctalDigit )? )?
			int alt44=2;
			int LA44_0 = input.LA(1);
			if ( ((LA44_0 >= '0' && LA44_0 <= '7')) ) {
				alt44=1;
			}
			switch (alt44) {
				case 1 :
					// AcslLexer.g:227:32: OctalDigit ( OctalDigit )?
					{
					mOctalDigit(); 

					// AcslLexer.g:227:43: ( OctalDigit )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( ((LA43_0 >= '0' && LA43_0 <= '7')) ) {
						alt43=1;
					}
					switch (alt43) {
						case 1 :
							// AcslLexer.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

					}

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OctalEscape"

	// $ANTLR start "OctalDigit"
	public final void mOctalDigit() throws RecognitionException {
		try {
			// AcslLexer.g:230:12: ( '0' .. '7' )
			// AcslLexer.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OctalDigit"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:234:8: ( IdentifierNonDigit ( IdentifierNonDigit | Digit )* )
			// AcslLexer.g:234:10: IdentifierNonDigit ( IdentifierNonDigit | Digit )*
			{
			mIdentifierNonDigit(); 

			// AcslLexer.g:235:4: ( IdentifierNonDigit | Digit )*
			loop45:
			while (true) {
				int alt45=3;
				int LA45_0 = input.LA(1);
				if ( (LA45_0=='$'||(LA45_0 >= 'A' && LA45_0 <= 'Z')||LA45_0=='\\'||LA45_0=='_'||(LA45_0 >= 'a' && LA45_0 <= 'z')) ) {
					alt45=1;
				}
				else if ( ((LA45_0 >= '0' && LA45_0 <= '9')) ) {
					alt45=2;
				}

				switch (alt45) {
				case 1 :
					// AcslLexer.g:235:5: IdentifierNonDigit
					{
					mIdentifierNonDigit(); 

					}
					break;
				case 2 :
					// AcslLexer.g:235:26: Digit
					{
					mDigit(); 

					}
					break;

				default :
					break loop45;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "IdentifierNonDigit"
	public final void mIdentifierNonDigit() throws RecognitionException {
		try {
			// AcslLexer.g:240:3: ( NonDigit | UniversalCharacterName )
			int alt46=2;
			int LA46_0 = input.LA(1);
			if ( (LA46_0=='\\') ) {
				int LA46_1 = input.LA(2);
				if ( (LA46_1=='U'||LA46_1=='u') ) {
					alt46=2;
				}

				else {
					alt46=1;
				}

			}
			else if ( (LA46_0=='$'||(LA46_0 >= 'A' && LA46_0 <= 'Z')||LA46_0=='_'||(LA46_0 >= 'a' && LA46_0 <= 'z')) ) {
				alt46=1;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 46, 0, input);
				throw nvae;
			}

			switch (alt46) {
				case 1 :
					// AcslLexer.g:240:5: NonDigit
					{
					mNonDigit(); 

					}
					break;
				case 2 :
					// AcslLexer.g:240:16: UniversalCharacterName
					{
					mUniversalCharacterName(); 

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IdentifierNonDigit"

	// $ANTLR start "Zero"
	public final void mZero() throws RecognitionException {
		try {
			// AcslLexer.g:243:7: ( '0' )
			// AcslLexer.g:243:9: '0'
			{
			match('0'); 
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Zero"

	// $ANTLR start "Digit"
	public final void mDigit() throws RecognitionException {
		try {
			// AcslLexer.g:246:8: ( Zero | NonZeroDigit )
			// AcslLexer.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Digit"

	// $ANTLR start "NonZeroDigit"
	public final void mNonZeroDigit() throws RecognitionException {
		try {
			// AcslLexer.g:249:14: ( '1' .. '9' )
			// AcslLexer.g:
			{
			if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NonZeroDigit"

	// $ANTLR start "NonDigit"
	public final void mNonDigit() throws RecognitionException {
		try {
			// AcslLexer.g:252:10: ( 'A' .. 'Z' | 'a' .. 'z' | '_' | '\\\\' | '$' )
			// AcslLexer.g:
			{
			if ( input.LA(1)=='$'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='\\'||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NonDigit"

	// $ANTLR start "UniversalCharacterName"
	public final void mUniversalCharacterName() throws RecognitionException {
		try {
			// AcslLexer.g:256:3: ( '\\\\' 'u' HexQuad | '\\\\' 'U' HexQuad HexQuad )
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0=='\\') ) {
				int LA47_1 = input.LA(2);
				if ( (LA47_1=='u') ) {
					alt47=1;
				}
				else if ( (LA47_1=='U') ) {
					alt47=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 47, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}

			switch (alt47) {
				case 1 :
					// AcslLexer.g:256:5: '\\\\' 'u' HexQuad
					{
					match('\\'); 
					match('u'); 
					mHexQuad(); 

					}
					break;
				case 2 :
					// AcslLexer.g:257:5: '\\\\' 'U' HexQuad HexQuad
					{
					match('\\'); 
					match('U'); 
					mHexQuad(); 

					mHexQuad(); 

					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UniversalCharacterName"

	// $ANTLR start "HexQuad"
	public final void mHexQuad() throws RecognitionException {
		try {
			// AcslLexer.g:261:10: ( HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit )
			// AcslLexer.g:261:12: HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit
			{
			mHexadecimalDigit(); 

			mHexadecimalDigit(); 

			mHexadecimalDigit(); 

			mHexadecimalDigit(); 

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexQuad"

	// $ANTLR start "HexadecimalDigit"
	public final void mHexadecimalDigit() throws RecognitionException {
		try {
			// AcslLexer.g:265:3: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
			// AcslLexer.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexadecimalDigit"

	// $ANTLR start "LCOMMENT"
	public final void mLCOMMENT() throws RecognitionException {
		try {
			int _type = LCOMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:270:5: ( '/*' )
			// AcslLexer.g:270:9: '/*'
			{
			match("/*"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LCOMMENT"

	// $ANTLR start "RCOMMENT"
	public final void mRCOMMENT() throws RecognitionException {
		try {
			int _type = RCOMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:273:5: ( '*/' )
			// AcslLexer.g:273:9: '*/'
			{
			match("*/"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RCOMMENT"

	// $ANTLR start "AT"
	public final void mAT() throws RecognitionException {
		try {
			int _type = AT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:277:5: ( '@' )
			// AcslLexer.g:277:7: '@'
			{
			match('@'); 
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AT"

	// $ANTLR start "NEWLINE"
	public final void mNEWLINE() throws RecognitionException {
		try {
			int _type = NEWLINE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:281:10: ( NewLine )
			// AcslLexer.g:281:12: NewLine
			{
			mNewLine(); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEWLINE"

	// $ANTLR start "NewLine"
	public final void mNewLine() throws RecognitionException {
		try {
			// AcslLexer.g:284:10: ( ( '\\r' )? '\\n' )
			// AcslLexer.g:284:12: ( '\\r' )? '\\n'
			{
			// AcslLexer.g:284:12: ( '\\r' )?
			int alt48=2;
			int LA48_0 = input.LA(1);
			if ( (LA48_0=='\r') ) {
				alt48=1;
			}
			switch (alt48) {
				case 1 :
					// AcslLexer.g:284:12: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NewLine"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// AcslLexer.g:286:5: ( ( ' ' | '\\t' )+ )
			// AcslLexer.g:286:7: ( ' ' | '\\t' )+
			{
			// AcslLexer.g:286:7: ( ' ' | '\\t' )+
			int cnt49=0;
			loop49:
			while (true) {
				int alt49=3;
				int LA49_0 = input.LA(1);
				if ( (LA49_0==' ') ) {
					alt49=1;
				}
				else if ( (LA49_0=='\t') ) {
					alt49=2;
				}

				switch (alt49) {
				case 1 :
					// AcslLexer.g:286:8: ' '
					{
					match(' '); 
					_channel=HIDDEN;
					}
					break;
				case 2 :
					// AcslLexer.g:286:32: '\\t'
					{
					match('\t'); 
					_channel=HIDDEN;
					}
					break;

				default :
					if ( cnt49 >= 1 ) break loop49;
					EarlyExitException eee = new EarlyExitException(49, input);
					throw eee;
				}
				cnt49++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// AcslLexer.g:1:8: ( BOOLEAN | INTEGER | REAL | CHAR | DOUBLE | FLOAT | INT | LONG | SHORT | VOID | REQUIRES | TERMINATES | DECREASES | GUARDS | ASSIGNS | ENSURES | ALLOC | BEHAVIORS | BEHAVIOR | ASSUMES | COMPLETE | DISJOINT | DEPENDS | READS | FREES | EMPTY | OLD | RESULT | NOTHING | UNION | INTER | TRUE | FALSE | WITH | LET | SIZEOF | FOR | READ | WRITE | CALL | NOACT | ANYACT | FORALL | EXISTS | PLUS | SUB | STAR | DIVIDE | MOD | SHIFTLEFT | SHIFTRIGHT | EQ | NEQ | LTE | GTE | LT | GT | LAND | LOR | BAR | XOR | AMPERSAND | IMPLY | EQUIV | ARROW | BITXOR | NOT | COMP | ELLIPSIS | DOTDOT | DOT | QUESTION | COLON | SEMICOL | COMMA | LPAREN | RPAREN | LCURLY | RCURLY | LSQUARE | RSQUARE | ASSIGN | HASH | INTEGER_CONSTANT | FLOATING_CONSTANT | PP_NUMBER | STRING_LITERAL | ID | LCOMMENT | RCOMMENT | AT | NEWLINE | WS )
		int alt50=93;
		alt50 = dfa50.predict(input);
		switch (alt50) {
			case 1 :
				// AcslLexer.g:1:10: BOOLEAN
				{
				mBOOLEAN(); 

				}
				break;
			case 2 :
				// AcslLexer.g:1:18: INTEGER
				{
				mINTEGER(); 

				}
				break;
			case 3 :
				// AcslLexer.g:1:26: REAL
				{
				mREAL(); 

				}
				break;
			case 4 :
				// AcslLexer.g:1:31: CHAR
				{
				mCHAR(); 

				}
				break;
			case 5 :
				// AcslLexer.g:1:36: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 6 :
				// AcslLexer.g:1:43: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 7 :
				// AcslLexer.g:1:49: INT
				{
				mINT(); 

				}
				break;
			case 8 :
				// AcslLexer.g:1:53: LONG
				{
				mLONG(); 

				}
				break;
			case 9 :
				// AcslLexer.g:1:58: SHORT
				{
				mSHORT(); 

				}
				break;
			case 10 :
				// AcslLexer.g:1:64: VOID
				{
				mVOID(); 

				}
				break;
			case 11 :
				// AcslLexer.g:1:69: REQUIRES
				{
				mREQUIRES(); 

				}
				break;
			case 12 :
				// AcslLexer.g:1:78: TERMINATES
				{
				mTERMINATES(); 

				}
				break;
			case 13 :
				// AcslLexer.g:1:89: DECREASES
				{
				mDECREASES(); 

				}
				break;
			case 14 :
				// AcslLexer.g:1:99: GUARDS
				{
				mGUARDS(); 

				}
				break;
			case 15 :
				// AcslLexer.g:1:106: ASSIGNS
				{
				mASSIGNS(); 

				}
				break;
			case 16 :
				// AcslLexer.g:1:114: ENSURES
				{
				mENSURES(); 

				}
				break;
			case 17 :
				// AcslLexer.g:1:122: ALLOC
				{
				mALLOC(); 

				}
				break;
			case 18 :
				// AcslLexer.g:1:128: BEHAVIORS
				{
				mBEHAVIORS(); 

				}
				break;
			case 19 :
				// AcslLexer.g:1:138: BEHAVIOR
				{
				mBEHAVIOR(); 

				}
				break;
			case 20 :
				// AcslLexer.g:1:147: ASSUMES
				{
				mASSUMES(); 

				}
				break;
			case 21 :
				// AcslLexer.g:1:155: COMPLETE
				{
				mCOMPLETE(); 

				}
				break;
			case 22 :
				// AcslLexer.g:1:164: DISJOINT
				{
				mDISJOINT(); 

				}
				break;
			case 23 :
				// AcslLexer.g:1:173: DEPENDS
				{
				mDEPENDS(); 

				}
				break;
			case 24 :
				// AcslLexer.g:1:181: READS
				{
				mREADS(); 

				}
				break;
			case 25 :
				// AcslLexer.g:1:187: FREES
				{
				mFREES(); 

				}
				break;
			case 26 :
				// AcslLexer.g:1:193: EMPTY
				{
				mEMPTY(); 

				}
				break;
			case 27 :
				// AcslLexer.g:1:199: OLD
				{
				mOLD(); 

				}
				break;
			case 28 :
				// AcslLexer.g:1:203: RESULT
				{
				mRESULT(); 

				}
				break;
			case 29 :
				// AcslLexer.g:1:210: NOTHING
				{
				mNOTHING(); 

				}
				break;
			case 30 :
				// AcslLexer.g:1:218: UNION
				{
				mUNION(); 

				}
				break;
			case 31 :
				// AcslLexer.g:1:224: INTER
				{
				mINTER(); 

				}
				break;
			case 32 :
				// AcslLexer.g:1:230: TRUE
				{
				mTRUE(); 

				}
				break;
			case 33 :
				// AcslLexer.g:1:235: FALSE
				{
				mFALSE(); 

				}
				break;
			case 34 :
				// AcslLexer.g:1:241: WITH
				{
				mWITH(); 

				}
				break;
			case 35 :
				// AcslLexer.g:1:246: LET
				{
				mLET(); 

				}
				break;
			case 36 :
				// AcslLexer.g:1:250: SIZEOF
				{
				mSIZEOF(); 

				}
				break;
			case 37 :
				// AcslLexer.g:1:257: FOR
				{
				mFOR(); 

				}
				break;
			case 38 :
				// AcslLexer.g:1:261: READ
				{
				mREAD(); 

				}
				break;
			case 39 :
				// AcslLexer.g:1:266: WRITE
				{
				mWRITE(); 

				}
				break;
			case 40 :
				// AcslLexer.g:1:272: CALL
				{
				mCALL(); 

				}
				break;
			case 41 :
				// AcslLexer.g:1:277: NOACT
				{
				mNOACT(); 

				}
				break;
			case 42 :
				// AcslLexer.g:1:283: ANYACT
				{
				mANYACT(); 

				}
				break;
			case 43 :
				// AcslLexer.g:1:290: FORALL
				{
				mFORALL(); 

				}
				break;
			case 44 :
				// AcslLexer.g:1:297: EXISTS
				{
				mEXISTS(); 

				}
				break;
			case 45 :
				// AcslLexer.g:1:304: PLUS
				{
				mPLUS(); 

				}
				break;
			case 46 :
				// AcslLexer.g:1:309: SUB
				{
				mSUB(); 

				}
				break;
			case 47 :
				// AcslLexer.g:1:313: STAR
				{
				mSTAR(); 

				}
				break;
			case 48 :
				// AcslLexer.g:1:318: DIVIDE
				{
				mDIVIDE(); 

				}
				break;
			case 49 :
				// AcslLexer.g:1:325: MOD
				{
				mMOD(); 

				}
				break;
			case 50 :
				// AcslLexer.g:1:329: SHIFTLEFT
				{
				mSHIFTLEFT(); 

				}
				break;
			case 51 :
				// AcslLexer.g:1:339: SHIFTRIGHT
				{
				mSHIFTRIGHT(); 

				}
				break;
			case 52 :
				// AcslLexer.g:1:350: EQ
				{
				mEQ(); 

				}
				break;
			case 53 :
				// AcslLexer.g:1:353: NEQ
				{
				mNEQ(); 

				}
				break;
			case 54 :
				// AcslLexer.g:1:357: LTE
				{
				mLTE(); 

				}
				break;
			case 55 :
				// AcslLexer.g:1:361: GTE
				{
				mGTE(); 

				}
				break;
			case 56 :
				// AcslLexer.g:1:365: LT
				{
				mLT(); 

				}
				break;
			case 57 :
				// AcslLexer.g:1:368: GT
				{
				mGT(); 

				}
				break;
			case 58 :
				// AcslLexer.g:1:371: LAND
				{
				mLAND(); 

				}
				break;
			case 59 :
				// AcslLexer.g:1:376: LOR
				{
				mLOR(); 

				}
				break;
			case 60 :
				// AcslLexer.g:1:380: BAR
				{
				mBAR(); 

				}
				break;
			case 61 :
				// AcslLexer.g:1:384: XOR
				{
				mXOR(); 

				}
				break;
			case 62 :
				// AcslLexer.g:1:388: AMPERSAND
				{
				mAMPERSAND(); 

				}
				break;
			case 63 :
				// AcslLexer.g:1:398: IMPLY
				{
				mIMPLY(); 

				}
				break;
			case 64 :
				// AcslLexer.g:1:404: EQUIV
				{
				mEQUIV(); 

				}
				break;
			case 65 :
				// AcslLexer.g:1:410: ARROW
				{
				mARROW(); 

				}
				break;
			case 66 :
				// AcslLexer.g:1:416: BITXOR
				{
				mBITXOR(); 

				}
				break;
			case 67 :
				// AcslLexer.g:1:423: NOT
				{
				mNOT(); 

				}
				break;
			case 68 :
				// AcslLexer.g:1:427: COMP
				{
				mCOMP(); 

				}
				break;
			case 69 :
				// AcslLexer.g:1:432: ELLIPSIS
				{
				mELLIPSIS(); 

				}
				break;
			case 70 :
				// AcslLexer.g:1:441: DOTDOT
				{
				mDOTDOT(); 

				}
				break;
			case 71 :
				// AcslLexer.g:1:448: DOT
				{
				mDOT(); 

				}
				break;
			case 72 :
				// AcslLexer.g:1:452: QUESTION
				{
				mQUESTION(); 

				}
				break;
			case 73 :
				// AcslLexer.g:1:461: COLON
				{
				mCOLON(); 

				}
				break;
			case 74 :
				// AcslLexer.g:1:467: SEMICOL
				{
				mSEMICOL(); 

				}
				break;
			case 75 :
				// AcslLexer.g:1:475: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 76 :
				// AcslLexer.g:1:481: LPAREN
				{
				mLPAREN(); 

				}
				break;
			case 77 :
				// AcslLexer.g:1:488: RPAREN
				{
				mRPAREN(); 

				}
				break;
			case 78 :
				// AcslLexer.g:1:495: LCURLY
				{
				mLCURLY(); 

				}
				break;
			case 79 :
				// AcslLexer.g:1:502: RCURLY
				{
				mRCURLY(); 

				}
				break;
			case 80 :
				// AcslLexer.g:1:509: LSQUARE
				{
				mLSQUARE(); 

				}
				break;
			case 81 :
				// AcslLexer.g:1:517: RSQUARE
				{
				mRSQUARE(); 

				}
				break;
			case 82 :
				// AcslLexer.g:1:525: ASSIGN
				{
				mASSIGN(); 

				}
				break;
			case 83 :
				// AcslLexer.g:1:532: HASH
				{
				mHASH(); 

				}
				break;
			case 84 :
				// AcslLexer.g:1:537: INTEGER_CONSTANT
				{
				mINTEGER_CONSTANT(); 

				}
				break;
			case 85 :
				// AcslLexer.g:1:554: FLOATING_CONSTANT
				{
				mFLOATING_CONSTANT(); 

				}
				break;
			case 86 :
				// AcslLexer.g:1:572: PP_NUMBER
				{
				mPP_NUMBER(); 

				}
				break;
			case 87 :
				// AcslLexer.g:1:582: STRING_LITERAL
				{
				mSTRING_LITERAL(); 

				}
				break;
			case 88 :
				// AcslLexer.g:1:597: ID
				{
				mID(); 

				}
				break;
			case 89 :
				// AcslLexer.g:1:600: LCOMMENT
				{
				mLCOMMENT(); 

				}
				break;
			case 90 :
				// AcslLexer.g:1:609: RCOMMENT
				{
				mRCOMMENT(); 

				}
				break;
			case 91 :
				// AcslLexer.g:1:618: AT
				{
				mAT(); 

				}
				break;
			case 92 :
				// AcslLexer.g:1:621: NEWLINE
				{
				mNEWLINE(); 

				}
				break;
			case 93 :
				// AcslLexer.g:1:629: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA20 dfa20 = new DFA20(this);
	protected DFA24 dfa24 = new DFA24(this);
	protected DFA30 dfa30 = new DFA30(this);
	protected DFA34 dfa34 = new DFA34(this);
	protected DFA50 dfa50 = new DFA50(this);
	static final String DFA20_eotS =
		"\4\uffff";
	static final String DFA20_eofS =
		"\4\uffff";
	static final String DFA20_minS =
		"\2\56\2\uffff";
	static final String DFA20_maxS =
		"\1\71\1\145\2\uffff";
	static final String DFA20_acceptS =
		"\2\uffff\1\1\1\2";
	static final String DFA20_specialS =
		"\4\uffff}>";
	static final String[] DFA20_transitionS = {
			"\1\2\1\uffff\12\1",
			"\1\2\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
			"",
			""
	};

	static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
	static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
	static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
	static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
	static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
	static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
	static final short[][] DFA20_transition;

	static {
		int numStates = DFA20_transitionS.length;
		DFA20_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
		}
	}

	protected class DFA20 extends DFA {

		public DFA20(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 20;
			this.eot = DFA20_eot;
			this.eof = DFA20_eof;
			this.min = DFA20_min;
			this.max = DFA20_max;
			this.accept = DFA20_accept;
			this.special = DFA20_special;
			this.transition = DFA20_transition;
		}
		@Override
		public String getDescription() {
			return "159:1: fragment DecimalFloatingConstant : ( FractionalConstant ( ExponentPart )? ( FloatingSuffix )? | ( Digit )+ ExponentPart ( FloatingSuffix )? );";
		}
	}

	static final String DFA24_eotS =
		"\3\uffff\1\4\1\uffff";
	static final String DFA24_eofS =
		"\5\uffff";
	static final String DFA24_minS =
		"\2\56\1\uffff\1\60\1\uffff";
	static final String DFA24_maxS =
		"\2\71\1\uffff\1\71\1\uffff";
	static final String DFA24_acceptS =
		"\2\uffff\1\1\1\uffff\1\2";
	static final String DFA24_specialS =
		"\5\uffff}>";
	static final String[] DFA24_transitionS = {
			"\1\2\1\uffff\12\1",
			"\1\3\1\uffff\12\1",
			"",
			"\12\2",
			""
	};

	static final short[] DFA24_eot = DFA.unpackEncodedString(DFA24_eotS);
	static final short[] DFA24_eof = DFA.unpackEncodedString(DFA24_eofS);
	static final char[] DFA24_min = DFA.unpackEncodedStringToUnsignedChars(DFA24_minS);
	static final char[] DFA24_max = DFA.unpackEncodedStringToUnsignedChars(DFA24_maxS);
	static final short[] DFA24_accept = DFA.unpackEncodedString(DFA24_acceptS);
	static final short[] DFA24_special = DFA.unpackEncodedString(DFA24_specialS);
	static final short[][] DFA24_transition;

	static {
		int numStates = DFA24_transitionS.length;
		DFA24_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA24_transition[i] = DFA.unpackEncodedString(DFA24_transitionS[i]);
		}
	}

	protected class DFA24 extends DFA {

		public DFA24(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 24;
			this.eot = DFA24_eot;
			this.eof = DFA24_eof;
			this.min = DFA24_min;
			this.max = DFA24_max;
			this.accept = DFA24_accept;
			this.special = DFA24_special;
			this.transition = DFA24_transition;
		}
		@Override
		public String getDescription() {
			return "165:1: fragment FractionalConstant : ( ( Digit )* DOT ( Digit )+ | ( Digit )+ DOT );";
		}
	}

	static final String DFA30_eotS =
		"\6\uffff";
	static final String DFA30_eofS =
		"\6\uffff";
	static final String DFA30_minS =
		"\1\60\1\130\2\56\2\uffff";
	static final String DFA30_maxS =
		"\1\60\1\170\1\146\1\160\2\uffff";
	static final String DFA30_acceptS =
		"\4\uffff\1\1\1\2";
	static final String DFA30_specialS =
		"\6\uffff}>";
	static final String[] DFA30_transitionS = {
			"\1\1",
			"\1\2\37\uffff\1\2",
			"\1\4\1\uffff\12\3\7\uffff\6\3\32\uffff\6\3",
			"\1\4\1\uffff\12\3\7\uffff\6\3\11\uffff\1\5\20\uffff\6\3\11\uffff\1\5",
			"",
			""
	};

	static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
	static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
	static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
	static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
	static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
	static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
	static final short[][] DFA30_transition;

	static {
		int numStates = DFA30_transitionS.length;
		DFA30_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
		}
	}

	protected class DFA30 extends DFA {

		public DFA30(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 30;
			this.eot = DFA30_eot;
			this.eof = DFA30_eof;
			this.min = DFA30_min;
			this.max = DFA30_max;
			this.accept = DFA30_accept;
			this.special = DFA30_special;
			this.transition = DFA30_transition;
		}
		@Override
		public String getDescription() {
			return "177:1: fragment HexadecimalFloatingConstant : ( HexPrefix HexFractionalConstant BinaryExponentPart ( FloatingSuffix )? | HexPrefix ( HexadecimalDigit )+ BinaryExponentPart ( FloatingSuffix )? );";
		}
	}

	static final String DFA34_eotS =
		"\3\uffff\1\4\1\uffff";
	static final String DFA34_eofS =
		"\5\uffff";
	static final String DFA34_minS =
		"\2\56\1\uffff\1\60\1\uffff";
	static final String DFA34_maxS =
		"\2\146\1\uffff\1\146\1\uffff";
	static final String DFA34_acceptS =
		"\2\uffff\1\1\1\uffff\1\2";
	static final String DFA34_specialS =
		"\5\uffff}>";
	static final String[] DFA34_transitionS = {
			"\1\2\1\uffff\12\1\7\uffff\6\1\32\uffff\6\1",
			"\1\3\1\uffff\12\1\7\uffff\6\1\32\uffff\6\1",
			"",
			"\12\2\7\uffff\6\2\32\uffff\6\2",
			""
	};

	static final short[] DFA34_eot = DFA.unpackEncodedString(DFA34_eotS);
	static final short[] DFA34_eof = DFA.unpackEncodedString(DFA34_eofS);
	static final char[] DFA34_min = DFA.unpackEncodedStringToUnsignedChars(DFA34_minS);
	static final char[] DFA34_max = DFA.unpackEncodedStringToUnsignedChars(DFA34_maxS);
	static final short[] DFA34_accept = DFA.unpackEncodedString(DFA34_acceptS);
	static final short[] DFA34_special = DFA.unpackEncodedString(DFA34_specialS);
	static final short[][] DFA34_transition;

	static {
		int numStates = DFA34_transitionS.length;
		DFA34_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA34_transition[i] = DFA.unpackEncodedString(DFA34_transitionS[i]);
		}
	}

	protected class DFA34 extends DFA {

		public DFA34(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 34;
			this.eot = DFA34_eot;
			this.eof = DFA34_eof;
			this.min = DFA34_min;
			this.max = DFA34_max;
			this.accept = DFA34_accept;
			this.special = DFA34_special;
			this.transition = DFA34_transition;
		}
		@Override
		public String getDescription() {
			return "185:1: fragment HexFractionalConstant : ( ( HexadecimalDigit )* DOT ( HexadecimalDigit )+ | ( HexadecimalDigit )+ DOT );";
		}
	}

	static final String DFA50_eotS =
		"\1\uffff\16\56\1\uffff\1\124\1\126\1\130\1\uffff\1\133\1\136\1\140\1\142"+
		"\1\144\1\146\1\150\1\uffff\1\152\13\uffff\2\154\3\56\5\uffff\41\56\7\uffff"+
		"\1\u00a2\4\uffff\1\u00a4\11\uffff\1\u00a6\1\uffff\1\u00a7\1\uffff\4\154"+
		"\1\u00a7\1\163\1\uffff\4\154\2\163\3\56\1\u00c1\12\56\1\u00cd\30\56\7"+
		"\uffff\1\163\2\u00a7\5\154\1\163\1\u00a7\11\154\1\163\2\154\3\56\1\uffff"+
		"\1\u0107\2\56\1\u010a\7\56\1\uffff\1\u0112\2\56\1\u0115\10\56\1\u011e"+
		"\13\56\1\u012a\2\56\1\163\1\u00a7\3\154\1\u00a7\20\154\5\163\3\56\1\uffff"+
		"\1\u0142\1\56\1\uffff\5\56\1\u0149\1\u014a\1\uffff\1\u014b\1\56\1\uffff"+
		"\10\56\1\uffff\1\56\1\u0156\4\56\1\u015b\2\56\1\u015e\1\56\1\uffff\1\u0160"+
		"\1\56\17\154\2\163\1\u00a7\3\56\1\uffff\2\56\1\u0177\3\56\3\uffff\1\u017b"+
		"\1\56\1\u017d\4\56\1\u0182\2\56\1\uffff\1\56\1\u0186\1\u0187\1\u0188\1"+
		"\uffff\1\u0189\1\56\1\uffff\1\u018b\1\uffff\1\56\15\154\1\163\2\u00a7"+
		"\1\u0195\1\56\1\u0197\2\56\1\uffff\1\56\1\u019b\1\56\1\uffff\1\56\1\uffff"+
		"\1\u019e\1\u019f\1\56\1\u01a1\1\uffff\1\u01a2\1\u01a3\1\56\4\uffff\1\u01a5"+
		"\1\uffff\1\u01a6\7\154\1\u00a7\1\uffff\1\u01a8\1\uffff\1\u01a9\1\u01aa"+
		"\1\56\1\uffff\1\u01ac\1\56\2\uffff\1\56\3\uffff\1\u01af\2\uffff\1\u01b0"+
		"\3\uffff\1\u01b1\1\uffff\1\56\1\u01b3\3\uffff\1\u01b4\2\uffff";
	static final String DFA50_eofS =
		"\u01b5\uffff";
	static final String DFA50_minS =
		"\1\11\1\145\1\156\1\145\1\150\1\145\1\154\1\157\1\150\1\157\1\145\1\165"+
		"\1\154\1\156\1\141\1\uffff\1\76\1\57\1\52\1\uffff\1\74\3\75\1\46\1\174"+
		"\1\136\1\uffff\1\56\13\uffff\2\44\3\42\5\uffff\1\157\1\150\1\164\2\141"+
		"\1\155\1\165\1\143\1\163\1\157\1\145\1\162\1\156\1\157\1\172\1\151\1\162"+
		"\1\141\1\163\1\154\1\163\1\155\1\154\1\145\1\157\2\156\1\162\1\141\1\151"+
		"\1\145\1\141\1\156\7\uffff\1\75\4\uffff\1\76\11\uffff\1\56\1\uffff\1\44"+
		"\1\uffff\5\44\1\53\1\uffff\4\44\2\56\1\42\1\154\1\141\1\44\1\144\1\165"+
		"\1\162\1\160\1\142\1\162\1\145\1\152\1\141\1\145\1\44\1\147\1\162\1\145"+
		"\1\144\1\155\1\162\1\151\1\157\1\165\1\160\1\151\1\144\2\141\1\151\1\164"+
		"\1\165\1\154\1\162\1\164\1\151\1\164\1\154\1\171\7\uffff\1\53\7\44\1\60"+
		"\12\44\1\60\2\44\1\145\1\166\1\147\1\uffff\1\44\1\163\1\151\1\44\2\154"+
		"\1\145\1\156\1\157\1\164\1\163\1\uffff\1\44\1\164\1\157\1\44\1\151\1\144"+
		"\1\147\1\155\1\143\1\162\1\164\1\163\1\44\1\165\1\144\1\150\1\143\1\157"+
		"\2\145\1\163\1\141\1\150\1\164\1\44\1\154\1\141\1\60\25\44\1\60\1\53\3"+
		"\60\1\141\1\151\1\145\1\uffff\1\44\1\162\1\uffff\2\145\1\141\1\144\1\151"+
		"\2\44\1\uffff\1\44\1\146\1\uffff\1\156\1\163\1\156\1\145\1\141\1\145\1"+
		"\171\1\164\1\uffff\1\154\1\44\1\151\1\164\1\156\1\162\1\44\1\145\1\154"+
		"\1\44\1\145\1\uffff\1\44\1\143\17\44\1\53\1\60\1\44\1\156\1\157\1\162"+
		"\1\uffff\1\145\1\164\1\44\2\163\1\156\3\uffff\1\44\1\141\1\44\2\163\1"+
		"\164\1\163\1\44\1\163\1\164\1\uffff\1\156\3\44\1\uffff\1\44\1\154\1\uffff"+
		"\1\44\1\uffff\1\164\15\44\1\60\3\44\1\162\1\44\1\163\1\145\1\uffff\1\145"+
		"\1\44\1\164\1\uffff\1\164\1\uffff\2\44\1\145\1\44\1\uffff\2\44\1\147\4"+
		"\uffff\1\44\1\uffff\11\44\1\uffff\1\44\1\uffff\2\44\1\163\1\uffff\1\44"+
		"\1\145\2\uffff\1\163\3\uffff\1\44\2\uffff\1\44\3\uffff\1\44\1\uffff\1"+
		"\163\1\44\3\uffff\1\44\2\uffff";
	static final String DFA50_maxS =
		"\1\176\1\157\1\156\1\145\2\157\1\162\1\157\1\151\1\157\1\145\1\165\1\163"+
		"\1\156\1\167\1\uffff\1\76\1\57\1\52\1\uffff\1\75\1\76\2\75\1\46\1\174"+
		"\1\136\1\uffff\1\71\13\uffff\2\172\1\70\2\42\5\uffff\1\157\1\150\1\164"+
		"\1\161\1\141\1\155\1\165\1\160\1\163\1\157\1\145\1\162\1\156\1\157\1\172"+
		"\1\151\1\162\1\141\1\163\1\154\1\163\1\170\1\154\1\145\1\157\2\156\1\162"+
		"\1\157\1\162\1\145\1\141\1\156\7\uffff\1\75\4\uffff\1\76\11\uffff\1\56"+
		"\1\uffff\1\172\1\uffff\5\172\1\71\1\uffff\4\172\1\146\1\145\1\42\1\154"+
		"\1\141\1\172\1\154\1\165\1\162\1\160\1\142\1\162\1\145\1\152\1\141\1\145"+
		"\1\172\1\147\1\162\1\145\1\144\1\155\1\162\1\165\1\157\1\165\1\160\1\151"+
		"\1\144\1\163\1\164\1\151\1\164\1\165\1\154\1\162\1\164\1\151\1\164\1\154"+
		"\1\171\7\uffff\1\71\7\172\1\71\12\172\1\146\2\172\1\145\1\166\1\147\1"+
		"\uffff\1\172\1\163\1\151\1\172\2\154\1\145\1\156\1\157\1\164\1\163\1\uffff"+
		"\1\172\1\164\1\157\1\172\1\151\1\144\1\147\1\155\1\143\1\162\1\164\1\163"+
		"\1\172\1\165\1\144\1\150\1\143\1\157\2\145\1\163\1\141\1\150\1\164\1\172"+
		"\1\154\1\141\1\71\25\172\1\160\1\71\3\160\1\141\1\151\1\145\1\uffff\1"+
		"\172\1\162\1\uffff\2\145\1\141\1\144\1\151\2\172\1\uffff\1\172\1\146\1"+
		"\uffff\1\156\1\163\1\156\1\145\1\141\1\145\1\171\1\164\1\uffff\1\154\1"+
		"\172\1\151\1\164\1\156\1\162\1\172\1\145\1\154\1\172\1\145\1\uffff\1\172"+
		"\1\143\17\172\2\71\1\172\1\156\1\157\1\162\1\uffff\1\145\1\164\1\172\2"+
		"\163\1\156\3\uffff\1\172\1\141\1\172\2\163\1\164\1\163\1\172\1\163\1\164"+
		"\1\uffff\1\156\3\172\1\uffff\1\172\1\154\1\uffff\1\172\1\uffff\1\164\15"+
		"\172\1\71\3\172\1\162\1\172\1\163\1\145\1\uffff\1\145\1\172\1\164\1\uffff"+
		"\1\164\1\uffff\2\172\1\145\1\172\1\uffff\2\172\1\147\4\uffff\1\172\1\uffff"+
		"\11\172\1\uffff\1\172\1\uffff\2\172\1\163\1\uffff\1\172\1\145\2\uffff"+
		"\1\163\3\uffff\1\172\2\uffff\1\172\3\uffff\1\172\1\uffff\1\163\1\172\3"+
		"\uffff\1\172\2\uffff";
	static final String DFA50_acceptS =
		"\17\uffff\1\55\3\uffff\1\61\7\uffff\1\104\1\uffff\1\110\1\111\1\112\1"+
		"\113\1\114\1\115\1\116\1\117\1\120\1\121\1\123\5\uffff\1\127\1\130\1\133"+
		"\1\134\1\135\41\uffff\1\101\1\56\1\132\1\57\1\131\1\60\1\62\1\uffff\1"+
		"\70\1\63\1\67\1\71\1\uffff\1\122\1\65\1\103\1\72\1\76\1\73\1\74\1\75\1"+
		"\102\1\uffff\1\107\1\uffff\1\124\6\uffff\1\126\55\uffff\1\100\1\66\1\77"+
		"\1\64\1\105\1\106\1\125\31\uffff\1\7\13\uffff\1\45\71\uffff\1\3\2\uffff"+
		"\1\4\7\uffff\1\10\2\uffff\1\12\10\uffff\1\33\13\uffff\1\43\27\uffff\1"+
		"\30\6\uffff\1\6\1\31\1\11\12\uffff\1\46\4\uffff\1\40\2\uffff\1\42\1\uffff"+
		"\1\50\26\uffff\1\5\3\uffff\1\44\1\uffff\1\16\4\uffff\1\32\3\uffff\1\51"+
		"\1\36\1\37\1\41\1\uffff\1\47\11\uffff\1\1\1\uffff\1\2\3\uffff\1\27\2\uffff"+
		"\1\17\1\24\1\uffff\1\20\1\54\1\34\1\uffff\1\53\1\52\1\uffff\1\23\1\13"+
		"\1\25\1\uffff\1\26\2\uffff\1\35\1\22\1\15\1\uffff\1\21\1\14";
	static final String DFA50_specialS =
		"\u01b5\uffff}>";
	static final String[] DFA50_transitionS = {
			"\1\61\1\60\2\uffff\1\60\22\uffff\1\61\1\27\1\55\1\47\1\56\1\23\1\30\1"+
			"\uffff\1\41\1\42\1\21\1\17\1\40\1\20\1\34\1\22\1\51\11\50\1\36\1\37\1"+
			"\24\1\26\1\25\1\35\1\57\13\56\1\54\10\56\1\53\5\56\1\45\1\16\1\46\1\32"+
			"\1\56\1\uffff\1\14\1\1\1\4\1\5\1\15\1\6\1\13\1\56\1\2\2\56\1\7\5\56\1"+
			"\3\1\10\1\12\1\52\1\11\4\56\1\43\1\31\1\44\1\33",
			"\1\63\11\uffff\1\62",
			"\1\64",
			"\1\65",
			"\1\66\6\uffff\1\67",
			"\1\71\3\uffff\1\72\5\uffff\1\70",
			"\1\73\2\uffff\1\75\2\uffff\1\74",
			"\1\76",
			"\1\77\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\105\6\uffff\1\104",
			"\1\106",
			"\1\122\1\uffff\1\121\1\uffff\1\107\1\116\2\uffff\1\114\2\uffff\1\120"+
			"\1\uffff\1\112\1\110\2\uffff\1\111\1\uffff\1\115\1\113\1\uffff\1\117",
			"",
			"\1\123",
			"\1\125",
			"\1\127",
			"",
			"\1\131\1\132",
			"\1\135\1\134",
			"\1\137",
			"\1\141",
			"\1\143",
			"\1\145",
			"\1\147",
			"",
			"\1\151\1\uffff\12\153",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\163\11\uffff\1\161\1\uffff\12\155\7\uffff\4\163\1\162\6\163\1\160"+
			"\10\163\1\156\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\4\163\1\162\6"+
			"\163\1\157\10\163\1\156\5\163",
			"\1\163\11\uffff\1\161\1\uffff\10\164\2\171\7\uffff\4\163\1\162\6\163"+
			"\1\167\10\163\1\165\2\163\1\170\2\163\1\uffff\1\163\2\uffff\1\163\1\uffff"+
			"\4\163\1\162\6\163\1\166\10\163\1\165\2\163\1\170\2\163",
			"\1\55\25\uffff\1\172",
			"\1\55",
			"\1\55",
			"",
			"",
			"",
			"",
			"",
			"\1\173",
			"\1\174",
			"\1\175",
			"\1\176\17\uffff\1\177",
			"\1\u0080",
			"\1\u0081",
			"\1\u0082",
			"\1\u0083\14\uffff\1\u0084",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\u0088",
			"\1\u0089",
			"\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091",
			"\1\u0092\12\uffff\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"\1\u0097",
			"\1\u0098",
			"\1\u0099",
			"\1\u009a\15\uffff\1\u009b",
			"\1\u009c\10\uffff\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"\1\u00a0",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u00a1",
			"",
			"",
			"",
			"",
			"\1\u00a3",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u00a5",
			"",
			"\1\163\11\uffff\1\163\1\uffff\12\u00aa\7\uffff\4\163\1\u00a8\1\u00a9"+
			"\5\163\1\u00a9\16\163\1\uffff\1\163\2\uffff\1\163\1\uffff\4\163\1\u00a8"+
			"\1\u00a9\5\163\1\u00a9\16\163",
			"",
			"\1\163\11\uffff\1\161\1\uffff\12\155\7\uffff\4\163\1\162\6\163\1\160"+
			"\10\163\1\156\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\4\163\1\162\6"+
			"\163\1\157\10\163\1\156\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00ac\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00ab\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u00ae\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00ad\10\163\1\u00ae\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00af\10\163\1"+
			"\u00ae\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u00ae\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\u00aa\7\uffff\4\163\1\u00a8\1\u00a9"+
			"\5\163\1\u00a9\16\163\1\uffff\1\163\2\uffff\1\163\1\uffff\4\163\1\u00a8"+
			"\1\u00a9\5\163\1\u00a9\16\163",
			"\1\u00b0\1\uffff\1\u00b0\2\uffff\12\u00b1",
			"",
			"\1\163\11\uffff\1\161\1\uffff\10\164\2\171\7\uffff\4\163\1\162\6\163"+
			"\1\167\10\163\1\165\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\4\163\1"+
			"\162\6\163\1\166\10\163\1\165\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00b4\10\163\1"+
			"\u00b3\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00b2\10\163"+
			"\1\u00b3\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00b7\10\163\1"+
			"\u00b6\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00b5\10\163"+
			"\1\u00b6\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00b8\10\163\1"+
			"\u00b6\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00b9\10\163"+
			"\1\u00b6\5\163",
			"\1\u00bb\1\uffff\12\u00bc\7\uffff\4\u00bd\1\u00ba\1\u00bd\32\uffff\4"+
			"\u00bd\1\u00ba\1\u00bd",
			"\1\161\1\uffff\12\171\13\uffff\1\162\37\uffff\1\162",
			"\1\55",
			"\1\u00be",
			"\1\u00bf",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\4\56\1\u00c0\25\56",
			"\1\u00c3\7\uffff\1\u00c2",
			"\1\u00c4",
			"\1\u00c5",
			"\1\u00c6",
			"\1\u00c7",
			"\1\u00c8",
			"\1\u00c9",
			"\1\u00ca",
			"\1\u00cb",
			"\1\u00cc",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u00ce",
			"\1\u00cf",
			"\1\u00d0",
			"\1\u00d1",
			"\1\u00d2",
			"\1\u00d3",
			"\1\u00d4\13\uffff\1\u00d5",
			"\1\u00d6",
			"\1\u00d7",
			"\1\u00d8",
			"\1\u00d9",
			"\1\u00da",
			"\1\u00dc\21\uffff\1\u00db",
			"\1\u00de\22\uffff\1\u00dd",
			"\1\u00df",
			"\1\u00e0",
			"\1\u00e1",
			"\1\u00e2",
			"\1\u00e3",
			"\1\u00e4",
			"\1\u00e5",
			"\1\u00e6",
			"\1\u00e7",
			"\1\u00e8",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u00e9\1\uffff\1\u00e9\2\uffff\12\u00ea",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\u00aa\7\uffff\4\163\1\u00a8\1\u00a9"+
			"\5\163\1\u00a9\16\163\1\uffff\1\163\2\uffff\1\163\1\uffff\4\163\1\u00a8"+
			"\1\u00a9\5\163\1\u00a9\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\13\163\1\u00eb\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00ec\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u00ed\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u00ed\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u00ed\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u00ed\5\163",
			"\12\u00b1",
			"\1\163\11\uffff\1\163\1\uffff\12\u00b1\7\uffff\5\163\1\u00ee\5\163\1"+
			"\u00ee\16\163\1\uffff\1\163\2\uffff\1\163\1\uffff\5\163\1\u00ee\5\163"+
			"\1\u00ee\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00b7\10\163\1"+
			"\u00f0\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00ef\10\163"+
			"\1\u00f0\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00f2\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00f1\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00f3\10\163\1"+
			"\u00f0\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00b9\10\163"+
			"\1\u00f0\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00b7\10\163\1"+
			"\u00f5\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00f4\10\163"+
			"\1\u00f5\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00f7\10\163\1"+
			"\u00b3\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00f6\10\163"+
			"\1\u00b3\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00f8\10\163\1"+
			"\u00f9\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u00f9\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00fa\10\163\1"+
			"\u00f5\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00b9\10\163"+
			"\1\u00f5\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u00f9\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00fb\10\163\1\u00f9\5\163",
			"\1\163\6\uffff\1\163\1\uffff\1\163\1\u00ff\1\uffff\12\u00bc\7\uffff"+
			"\4\u00bd\1\u00ba\1\u00bd\5\163\1\u00fe\3\163\1\u0100\4\163\1\u00fc\5"+
			"\163\1\uffff\1\163\2\uffff\1\163\1\uffff\4\u00bd\1\u00ba\1\u00bd\5\163"+
			"\1\u00fd\3\163\1\u0100\4\163\1\u00fc\5\163",
			"\12\u0102\7\uffff\4\u0103\1\u0101\1\u0103\32\uffff\4\u0103\1\u0101\1"+
			"\u0103",
			"\1\163\11\uffff\1\u00ff\1\uffff\12\u00bc\7\uffff\4\u00bd\1\u00ba\1\u00bd"+
			"\5\163\1\u00fe\3\163\1\u0100\4\163\1\u00fc\5\163\1\uffff\1\163\2\uffff"+
			"\1\163\1\uffff\4\u00bd\1\u00ba\1\u00bd\5\163\1\u00fd\3\163\1\u0100\4"+
			"\163\1\u00fc\5\163",
			"\1\163\11\uffff\1\u00ff\1\uffff\12\u00bc\7\uffff\4\u00bd\1\u00ba\1\u00bd"+
			"\5\163\1\u00fe\3\163\1\u0100\4\163\1\u00fc\5\163\1\uffff\1\163\2\uffff"+
			"\1\163\1\uffff\4\u00bd\1\u00ba\1\u00bd\5\163\1\u00fd\3\163\1\u0100\4"+
			"\163\1\u00fc\5\163",
			"\1\u0104",
			"\1\u0105",
			"\1\u0106",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0108",
			"\1\u0109",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u010b",
			"\1\u010c",
			"\1\u010d",
			"\1\u010e",
			"\1\u010f",
			"\1\u0110",
			"\1\u0111",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0113",
			"\1\u0114",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0116",
			"\1\u0117",
			"\1\u0118",
			"\1\u0119",
			"\1\u011a",
			"\1\u011b",
			"\1\u011c",
			"\1\u011d",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u011f",
			"\1\u0120",
			"\1\u0121",
			"\1\u0122",
			"\1\u0123",
			"\1\u0124",
			"\1\u0125",
			"\1\u0126",
			"\1\u0127",
			"\1\u0128",
			"\1\u0129",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u012b",
			"\1\u012c",
			"\12\u00ea",
			"\1\163\11\uffff\1\163\1\uffff\12\u00ea\7\uffff\5\163\1\u00a9\5\163\1"+
			"\u00a9\16\163\1\uffff\1\163\2\uffff\1\163\1\uffff\5\163\1\u00a9\5\163"+
			"\1\u00a9\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00b7\10\163\1"+
			"\u012d\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00f4\10\163"+
			"\1\u012d\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00f2\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00f1\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\13\163\1\u012e\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u012f\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00fa\10\163\1"+
			"\u012d\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00b9\10\163"+
			"\1\u012d\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0130\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00fb\10\163\1\u0130\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00f7\10\163\1"+
			"\u00b3\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00f6\10\163"+
			"\1\u00b3\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u00f9\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0131\10\163\1\u00f9\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0132\10\163\1"+
			"\u00f9\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u00f9\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0133\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0133\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00f8\10\163\1"+
			"\u0130\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0130\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0133\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0133\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0136\10\163\1"+
			"\u0135\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0134\10\163"+
			"\1\u0135\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0139\10\163\1"+
			"\u0138\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0137\10\163"+
			"\1\u0138\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u013a\10\163\1"+
			"\u0138\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u013b\10\163"+
			"\1\u0138\5\163",
			"\12\u0102\7\uffff\4\u0103\1\u0101\1\u0103\11\uffff\1\u013c\20\uffff"+
			"\4\u0103\1\u0101\1\u0103\11\uffff\1\u013c",
			"\1\u013d\1\uffff\1\u013d\2\uffff\12\u013e",
			"\12\u0102\7\uffff\4\u0103\1\u0101\1\u0103\11\uffff\1\u013c\20\uffff"+
			"\4\u0103\1\u0101\1\u0103\11\uffff\1\u013c",
			"\12\u0102\7\uffff\4\u0103\1\u0101\1\u0103\11\uffff\1\u013c\20\uffff"+
			"\4\u0103\1\u0101\1\u0103\11\uffff\1\u013c",
			"\12\u0102\7\uffff\4\u0103\1\u0101\1\u0103\11\uffff\1\u013c\20\uffff"+
			"\4\u0103\1\u0101\1\u0103\11\uffff\1\u013c",
			"\1\u013f",
			"\1\u0140",
			"\1\u0141",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0143",
			"",
			"\1\u0144",
			"\1\u0145",
			"\1\u0146",
			"\1\u0147",
			"\1\u0148",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u014c",
			"",
			"\1\u014d",
			"\1\u014e",
			"\1\u014f",
			"\1\u0150",
			"\1\u0151",
			"\1\u0152",
			"\1\u0153",
			"\1\u0154",
			"",
			"\1\u0155",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0157",
			"\1\u0158",
			"\1\u0159",
			"\1\u015a",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u015c",
			"\1\u015d",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u015f",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0161",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u00f2\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u00f1\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0133\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0133\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0133\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0133\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0139\10\163\1"+
			"\u0163\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0162\10\163"+
			"\1\u0163\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0165\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0164\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0166\10\163\1"+
			"\u0163\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u013b\10\163"+
			"\1\u0163\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0139\10\163\1"+
			"\u0168\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0167\10\163"+
			"\1\u0168\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u016a\10\163\1"+
			"\u0135\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0169\10\163"+
			"\1\u0135\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u016b\10\163\1"+
			"\u016c\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u016c\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u016d\10\163\1"+
			"\u0168\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u013b\10\163"+
			"\1\u0168\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u016c\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u016e\10\163\1\u016c\5\163",
			"\1\u016f\1\uffff\1\u016f\2\uffff\12\u0170",
			"\12\u013e",
			"\1\163\11\uffff\1\163\1\uffff\12\u013e\7\uffff\5\163\1\u0171\5\163\1"+
			"\u0171\16\163\1\uffff\1\163\2\uffff\1\163\1\uffff\5\163\1\u0171\5\163"+
			"\1\u0171\16\163",
			"\1\u0172",
			"\1\u0173",
			"\1\u0174",
			"",
			"\1\u0175",
			"\1\u0176",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0178",
			"\1\u0179",
			"\1\u017a",
			"",
			"",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u017c",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u017e",
			"\1\u017f",
			"\1\u0180",
			"\1\u0181",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0183",
			"\1\u0184",
			"",
			"\1\u0185",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u018a",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"\1\u018c",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0139\10\163\1"+
			"\u018d\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0167\10\163"+
			"\1\u018d\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0165\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0164\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\13\163\1\u018e\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u018f\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u016d\10\163\1"+
			"\u018d\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u013b\10\163"+
			"\1\u018d\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0190\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u016e\10\163\1\u0190\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u016a\10\163\1"+
			"\u0135\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0169\10\163"+
			"\1\u0135\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u016c\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0191\10\163\1\u016c\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0192\10\163\1"+
			"\u016c\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u016c\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0193\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0193\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u016b\10\163\1"+
			"\u0190\5\163\1\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0190\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0193\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0193\5\163",
			"\12\u0170",
			"\1\163\11\uffff\1\163\1\uffff\12\u0170\7\uffff\5\163\1\u0194\5\163\1"+
			"\u0194\16\163\1\uffff\1\163\2\uffff\1\163\1\uffff\5\163\1\u0194\5\163"+
			"\1\u0194\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0196",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u0198",
			"\1\u0199",
			"",
			"\1\u019a",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u019c",
			"",
			"\1\u019d",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u01a0",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u01a4",
			"",
			"",
			"",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\13\163\1\u0165\16\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\13\163\1\u0164\16\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0193\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0193\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\24\163\1\u0193\5\163\1"+
			"\uffff\1\163\2\uffff\1\163\1\uffff\24\163\1\u0193\5\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"\1\163\11\uffff\1\163\1\uffff\12\163\7\uffff\32\163\1\uffff\1\163\2"+
			"\uffff\1\163\1\uffff\32\163",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\22\56\1\u01a7\7\56",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u01ab",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"\1\u01ad",
			"",
			"",
			"\1\u01ae",
			"",
			"",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"\1\u01b2",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			"",
			"",
			"\1\56\13\uffff\12\56\7\uffff\32\56\1\uffff\1\56\2\uffff\1\56\1\uffff"+
			"\32\56",
			"",
			""
	};

	static final short[] DFA50_eot = DFA.unpackEncodedString(DFA50_eotS);
	static final short[] DFA50_eof = DFA.unpackEncodedString(DFA50_eofS);
	static final char[] DFA50_min = DFA.unpackEncodedStringToUnsignedChars(DFA50_minS);
	static final char[] DFA50_max = DFA.unpackEncodedStringToUnsignedChars(DFA50_maxS);
	static final short[] DFA50_accept = DFA.unpackEncodedString(DFA50_acceptS);
	static final short[] DFA50_special = DFA.unpackEncodedString(DFA50_specialS);
	static final short[][] DFA50_transition;

	static {
		int numStates = DFA50_transitionS.length;
		DFA50_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA50_transition[i] = DFA.unpackEncodedString(DFA50_transitionS[i]);
		}
	}

	protected class DFA50 extends DFA {

		public DFA50(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 50;
			this.eot = DFA50_eot;
			this.eof = DFA50_eof;
			this.min = DFA50_min;
			this.max = DFA50_max;
			this.accept = DFA50_accept;
			this.special = DFA50_special;
			this.transition = DFA50_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( BOOLEAN | INTEGER | REAL | CHAR | DOUBLE | FLOAT | INT | LONG | SHORT | VOID | REQUIRES | TERMINATES | DECREASES | GUARDS | ASSIGNS | ENSURES | ALLOC | BEHAVIORS | BEHAVIOR | ASSUMES | COMPLETE | DISJOINT | DEPENDS | READS | FREES | EMPTY | OLD | RESULT | NOTHING | UNION | INTER | TRUE | FALSE | WITH | LET | SIZEOF | FOR | READ | WRITE | CALL | NOACT | ANYACT | FORALL | EXISTS | PLUS | SUB | STAR | DIVIDE | MOD | SHIFTLEFT | SHIFTRIGHT | EQ | NEQ | LTE | GTE | LT | GT | LAND | LOR | BAR | XOR | AMPERSAND | IMPLY | EQUIV | ARROW | BITXOR | NOT | COMP | ELLIPSIS | DOTDOT | DOT | QUESTION | COLON | SEMICOL | COMMA | LPAREN | RPAREN | LCURLY | RCURLY | LSQUARE | RSQUARE | ASSIGN | HASH | INTEGER_CONSTANT | FLOATING_CONSTANT | PP_NUMBER | STRING_LITERAL | ID | LCOMMENT | RCOMMENT | AT | NEWLINE | WS );";
		}
	}

}
