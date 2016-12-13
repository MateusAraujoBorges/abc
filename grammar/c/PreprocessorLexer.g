lexer grammar PreprocessorLexer;

/*
 * Author: Stephen F. Siegel, University of Delaware
 * Last changed: June 2012
 *
 * This is a grammar for lexical analysis for a preprocessor
 * file.  It follows the C11 Standard.  This grammar assumes
 * that the stream of characters being scanned has already
 * gone through translation phases 1 and 2.  In particular
 * backslash followed by newline sequences have been removed.
 *
 * The grammar has been extended to include keywords for
 * the CIVL-C, ACSL, GNU, and CUDA extensions of C.
 */

@header
{
package edu.udel.cis.vsl.abc.front.c.preproc;
}

@members
{
@Override
public void emitErrorMessage(String msg) { // don't try to recover!
    throw new RuntimeException(msg);
}
}

/****** White space ******/
NEWLINE		:	'\r'? '\n'	;
WS		:	(' ' | '\t')+	;

/* Words that are used in both C and the preprocessor */
IF		:	'if'		;
ELSE		:	'else'		;

/* Words used in preprocessor but not in C */
DEFINE		:	'define'	;
DEFINED		:	'defined'	;
ELIF		:	'elif'		;
ENDIF		:	'endif'		;
ERROR		:	'error'		;
IFDEF		:	'ifdef'		;
IFNDEF		:	'ifndef'	;
INCLUDE		:	'include'	;
LINE		:	'line'		;
PRAGMA		:	'pragma'	;
UNDEF		:	'undef'		;

/****** C keywords, from C11 Sec. 6.4.1 ******/
AUTO		:	'auto'		;
ASM			:	'asm'		;
BREAK		:	'break'		;
CASE		:	'case'		;
CHAR		:	'char'		;
CONST		:	'const'		;
CONTINUE	:	'continue'	;
DEFAULT		:	'default'	;
DO		:	'do'		;
DOUBLE		:	'double'	;
ENUM		:	'enum'		;
EXTERN		:	'extern'	;
FLOAT		:	'float'		;
FOR		:	'for'		;
GOTO		:	'goto'		;
INLINE		:	'inline'	;
INT		:	'int'		;
LONG		:	'long'		;
REGISTER	:	'register'	;
RESTRICT	:	'restrict'	;
RETURN		:	'return'	;
SHORT		:	'short'		;
SIGNED		:	'signed'	;
SIZEOF		:	'sizeof'	;
STATIC		:	'static'	;
STRUCT		:	'struct'	;
SWITCH		:	'switch'	;
TYPEDEF		:	'typedef'	;
UNION		:	'union'		;
UNSIGNED	:	'unsigned'	;
VOID		:	'void'		;
VOLATILE	:	'volatile'	;
WHILE		:	'while'		;
ALIGNAS		:	'_Alignas'	;
ALIGNOF		:	'_Alignof'	;
ATOMIC		:	'_Atomic'	;
BOOL		:	'_Bool'		;
COMPLEX		:	'_Complex'	;
GENERIC		:	'_Generic'	;
IMAGINARY	:	'_Imaginary'	;
NORETURN	:	'_Noreturn'	;
STATICASSERT	:	'_Static_assert';
THREADLOCAL	:	'_Thread_local'	;

/* CIVL-C and ACSL keywords */
// Keep these in alphabetical order by token name.  Respect the formatting!
// Be sure to add these to PreprocessorParser.g too.
ABSTRACT	:	'$abstract'	;
ASSIGNS     	:   	'$assigns'	;
BIG_O		:	'$O'		;
CALLS       	:   	'$calls'	;
CHOOSE		:	'$choose'	;
CIVLATOMIC	:	'$atomic'	;
CIVLATOM	:	'$atom'		;
CIVLFOR		:	'$for'		;
COLLECTIVE	:	'$collective'	;
CONTIN		:	'$contin'	;
DEPENDS     	:   	'$depends'	;
DERIV		:	'$D'		;
DOMAIN		:	'$domain'	;
ENSURES		:	'$ensures'	;
EXISTS		: 	'$exists'	;
FORALL		:	'$forall'	;
FATOMIC     	:   	'$atomic_f'	;
GUARD       	:   	'$guard'	;
HERE		:	'$here'		;
INPUT		:	'$input'	;
INVARIANT	:	'$invariant'	;
LAMBDA		:	'$lambda'	;
OUTPUT		:	'$output'	;
ORIGINAL	:	'$original'	;
PARFOR		:	'$parfor'	;
PROCNULL	:	'$proc_null'	;
PURE		:	'$pure'		;
RANGE		:	'$range'	;
REAL		:	'$real'		;
REQUIRES	:	'$requires'	;
RESULT		:	'$result'	;
RUN        	:	'$run'		;
SCOPEOF		:	'$scopeof'	;
SELF		:	'$self'		;
STATE_F     	:   	'$state_f'  	;
STATE_NULL  	:	'$state_null'	;
READS       	:   	'$reads'	;
SPAWN		:	'$spawn'	;
SYSTEM      	:   	'$system'	;
UNIFORM		:	'$uniform'	;
UPDATE		:	'$update'	;
VALUE_AT	:	'$value_at'	;
WHEN		:	'$when'		;
WITH		:	'$with'		;

/* Cuda-C keywords */
DEVICE		:	'__device__'	;
GLOBAL		:	'__global__'	;
SHARED		:	'__shared__'	;

/* GNU-C keywords */
TYPEOF  	:	'typeof'	;

/****** Punctuators: C11 Sec. 6.4.6 ******/
ELLIPSIS	: 	'...'	 	;
DOTDOT		: 	'..' 		;
DOT		:	'.' 		;
AMPERSAND	:	'&'		;
AND		:	'&&'		;
ARROW		:	'->'		;
ASSIGN		:	'='		;
BITANDEQ	:	'&='		;
BITOR		:	'|'		;
BITOREQ		:	'|='		;
BITXOR		:	'^'		;
BITXOREQ	:	'^='		;
COLON		:	':'		;
COMMA		:	','		;
DIV		:	'/'		;
DIVEQ		:	'/='		;
EQUALS		:	'=='		;
GT		:	'>'		;
GTE		:	'>='		;
HASH		:	'#' | '%:'	;
HASHHASH	:	'##' | '%:%:'	;
LCURLY		:	'{' | '<%'	;
LPAREN		:	'('		;
LSQUARE		:	'[' | '<:'	;
LT		:	'<'		;
LTE		:	'<='		;
MINUSMINUS	:	'--'		;
MOD		:	'%'		;
MODEQ		:	'%='		;
NEQ		:	'!='		;
NOT		:	'!'		;
OR		:	'||'		;
PLUS		:	'+'		;
PLUSEQ		:	'+='		;
PLUSPLUS	:	'++'		;
QMARK		:	'?'		;
RCURLY		:	'}' | '%>'	;
RPAREN		:	')'		;
RSQUARE		:	']' | ':>'	;
SEMI		:	';'		;
SHIFTLEFT	:	'<<'		;
SHIFTLEFTEQ	:	'<<='		;
SHIFTRIGHT	:	'>>'		;
SHIFTRIGHTEQ	:	'>>='		;
STAR		:	'*'		;
STAREQ		:	'*='		;
SUB		:	'-'		;
SUBEQ		:	'-='		;
TILDE		:	'~'		;

/* CIVL-C and ACSL Punctuators */
ANNOTATION_START	:	'/*@'	;
ANNOTATION_END		:	'*/'	;
AT			:	'@'	;
EQUIV_ACSL		:	'<==>'	;
IMPLIES			:	'=>'	;
IMPLIES_ACSL		:	'==>'	;
INLINE_ANNOTATION_START :	'//@'	;
// LSLIST and RSLIST enclose a scope list
LSLIST			:	'<|'	;
RSLIST			:	'|>'	;
XOR_ACSL		:	'^^'	;

/* CUDA Punctuators */
LEXCON			:	'<<<'	;
REXCON			:	'>>>'	;

/****** Identifiers: C11 Sec. 6.4.2 ******/
IDENTIFIER	:	IdentifierNonDigit
			(IdentifierNonDigit | Digit)*
		;
		
fragment
IdentifierNonDigit
		:	NonDigit | UniversalCharacterName ;

fragment
Zero		:	'0' ;

fragment
Digit		:	Zero | NonZeroDigit ;

fragment
NonZeroDigit	:	'1' .. '9' ;

fragment
NonDigit	:	'A'..'Z' | 'a'..'z' | '_' | '$';

fragment
UniversalCharacterName
		:	'\\' 'u' HexQuad 
		|	'\\' 'U' HexQuad HexQuad
		;

fragment
HexQuad		:	HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit ;

fragment
HexadecimalDigit
		:	'0'..'9' | 'a'..'f' | 'A'..'F' ;

/****** Sec. 6.4.4.1: Integer constants ******/
INTEGER_CONSTANT
		:	DecimalConstant IntegerSuffix?
		|	OctalConstant IntegerSuffix?
		|	HexadecimalConstant IntegerSuffix?
		;

fragment
DecimalConstant	:	NonZeroDigit Digit* ;


fragment
IntegerSuffix	:	UnsignedSuffix LongSuffix?
		|	UnsignedSuffix LongLongSuffix
		|	LongSuffix UnsignedSuffix?
		|	LongLongSuffix UnsignedSuffix?
		;

fragment
UnsignedSuffix	:	'u' | 'U'	;

fragment
LongSuffix	:	'l' | 'L'	;

fragment
LongLongSuffix	:	'll' | 'LL'	;

fragment	
OctalConstant	:	Zero OctalDigit* IntegerSuffix? ;

fragment
HexadecimalConstant
		:	HexPrefix HexadecimalDigit+ IntegerSuffix? ;

fragment
HexPrefix	:	Zero ('x' | 'X') ;

/****** Sec. 6.4.4.2: Floating Constants ******/

FLOATING_CONSTANT
		:	DecimalFloatingConstant
		|	HexadecimalFloatingConstant
		;

fragment
DecimalFloatingConstant
		:	FractionalConstant ExponentPart? FloatingSuffix?
		|	Digit+ ExponentPart FloatingSuffix?
		;

fragment
FractionalConstant
		:	Digit* DOT Digit+
		|	Digit+ DOT
		;

fragment
ExponentPart	:	('e' | 'E') ('+' | '-')? Digit+ ;

fragment
FloatingSuffix	:	'f' | 'l' | 'F' | 'L' ;

fragment
HexadecimalFloatingConstant
		:	HexPrefix HexFractionalConstant BinaryExponentPart
			FloatingSuffix?
		|	HexPrefix HexadecimalDigit+ BinaryExponentPart
			FloatingSuffix?
		;

fragment
HexFractionalConstant
		:	HexadecimalDigit* DOT HexadecimalDigit+
		|	HexadecimalDigit+ DOT 
		;

fragment
BinaryExponentPart
		:	('p' | 'P') ('+' | '-')? Digit+ ;


/****** Preprocessing Numbers: C11 Sec 6.4.8 ******/

/* PP_NUMBER should be anything that doesn't match the previous
 * rules but does match this one.
 */
PP_NUMBER	:	'.'? Digit
			( '.'
			| IdentifierNonDigit
			| Digit
			| ('e' | 'E' | 'p' | 'P') ('+' | '-')
			)*
		;


/****** Sec. 6.4.4.4: Character Constants ******/

CHARACTER_CONSTANT
		:	('L' | 'U' | 'u')? '\'' CChar+ '\'' ;

fragment
CChar		:	~('\'' | '\\' | '\n') | EscapeSequence ;

fragment
EscapeSequence	:	'\\' ( '\'' | '"' | '\?' | '\\' |
			       'a' | 'b' | 'f' | 'n' |'r' | 't' | 'v'
			     )
		|	OctalEscape
		|	HexEscape
		;
fragment
OctalEscape	:	'\\' OctalDigit (OctalDigit OctalDigit?)? ;

fragment
OctalDigit	:	'0' .. '7';

fragment
HexEscape	:	'\\' 'x' HexadecimalDigit+ ;


/****** 6.4.5: String Literals *****/


STRING_LITERAL  :	('u8' | 'u' | 'U' | 'L')? '"' SChar* '"'
		;

fragment
SChar		:	~('"' | '\\' | '\n') | EscapeSequence ;



/* ***** Comments: C11 Sec 6.4.9 ******/

// the following is not quite perfect because in the case of the \n or \r
// immediately following the // it counts that white space as part of the
// comment, otherwise it doesn't.  Would like to make the \n or \r NOT
// part of the comment always, but how --- need to look ahead one character?

fragment
INLINE_COMMENT : '//'
                 (  (~('@' | '\n' | '\r') ( options {greedy=true;} : ~('\n'|'\r') )*)
                 |  NEWLINE
                 |  EOF
                 )
               ;

fragment
BLOCK_COMMENT : '/*'
                ( '*/' | ~('@') ( options {greedy=false;} : . )* '*/')
              ;
              
COMMENT : INLINE_COMMENT | BLOCK_COMMENT ;

/* Special keywords starting with backslash reserved for extensions
 * such as ACSL */
EXTENDED_IDENTIFIER
	:
	'\\' IdentifierNonDigit (IdentifierNonDigit | Digit)* 
	;

/****** Other characters: C11 Sec. 6.4 ******/
OTHER		: . ;
