lexer grammar OmpLexerF08;

options 
{
     tokenVocab=FortranParserExtras;
}

//import PreprocessorLexer;

/* OpenMP keywords */
T_BARRIER		:	'BARRIER'		;
T_CAPTURE		:	'CAPTURE'		;
T_COLLAPSE		:	'COLLAPSE' 		;
T_COPYIN		:	'COPYIN'		;
T_COPYPRIVATE	:	'COPYPRIVATE'	;
T_CRITICAL		:	'CRITICAL'		;
//DEFAULT		:	'default'		;
T_DYNAMIC		:	'DYNAMIC'		;
T_FST_PRIVATE	:	'FIRSTPRIVATE'	;
//FLUSH			:	'flush'			;
T_GUIDED		:	'GUIDED'		;
T_LST_PRIVATE	:	'LASTPRIVATE'	;
T_MASTER		:	'MASTER'		;
//NONE			:	'none'			;
T_NOWAIT		:	'NOWAIT'		;
T_NUM_THREADS	:	'NUM_THREADS'	;
T_OMPATOMIC		:	'ATOMIC'	  	;
T_ORDERED		:	'ORDERED'		;
T_PARALLEL		:	'PARALLEL'		;
//PRIVATE		:	'private'		;
//READ			:	'read'			;
T_REDUCTION		:	'REDUCTION'		;
T_RUNTIME		:	'RUNTIME'		;
T_SCHEDULE		:	'SCHEDULE'		;
T_SECTIONS		:	'SECTIONS'		;
T_SECTION		:	'SECTION'		;
T_SEQ_CST		:	'SEQ_CST'		;
T_SHARED		:	'SHARED'		;
T_SINGLE		: 	'SINGLE'		;
T_STATIC		:	'STATIC'		;
T_THD_PRIVATE	:	'THREADPRIVATE'	;
T_UPDATE		: 	'UPDATE'		;
//WRITE			:	'write'			;
T_AMPERSAND		:	'&'				;
T_BITXOR		:	'^'				;
T_BITOR			:	'|'				;
