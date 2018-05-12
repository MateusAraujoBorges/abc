parser grammar AcslParser;

/*
 * Grammar for ACSL: an ANSI/ISO C Specification Language,
 * with additional CIVL-C extensions.
 * Based on ACSL 1.12.
 * https://frama-c.com/acsl.html
 * 
 * Author: Manchun Zheng, University of Delaware
 * Author: Stephen F. Siegel, University of Delaware
 * Last changed: May 2018
 */

options
{
	language=Java;
	tokenVocab=PreprocessorLexer;
	output=AST;
    backtrack = true; // TODO: get rid of this
}

tokens{
    ABSENT;
    ABSTRACT_DECLARATOR;
    ACCESS_ACSL;
    ALLOC;
    ANYACT;
    ARGUMENT_LIST;
    ARRAY_SUFFIX;
    ASSUMES_ACSL;
    ASSIGNS_ACSL;
    ASSERT_ACSL;
    BEHAVIOR;
    BEHAVIOR_BODY;
    BEHAVIOR_COMPLETE;
    BEHAVIOR_DISJOINT;
    BEQUIV_ACSL;
    BIMPLIES_ACSL;
    BINDER;
    BINDER_LIST;
    BOOLEAN;
    BOTH;
    C_TYPE;
    CALL_ACSL;
    CAST;
    CLAUSE_NORMAL;
    CLAUSE_BEHAVIOR;
    CLAUSE_COMPLETE;
    COL;
    CONTRACT;
    DEPENDSON;
    DIRECT_ABSTRACT_DECLARATOR;
    ENSURES_ACSL;
    EVENT_BASE;
    EVENT_PLUS;
    EVENT_SUB;
    EVENT_INTS;
    EVENT_LIST;
    EVENT_PARENTHESIZED;
    EXECUTES_WHEN;
    EXISTS_ACSL;
    FALSE_ACSL;
    FORALL_ACSL;
    FREES;
    FUNC_CALL;
    FUNC_CONTRACT;
    FUNC_CONTRACT_BLOCK;
    ID_LIST;
    INDEX;
    INTEGER;
    INTER;
    LAMBDA_ACSL;
    LOGIC_TYPE;
    LOOP_ALLOC;
    LOOP_ASSIGNS;
    LOOP_BEHAVIOR;
    LOOP_CLAUSE;
    LOOP_CONTRACT;
    LOOP_CONTRACT_BLOCK;
    LOOP_FREE;
    LOOP_INVARIANT;
    LOOP_VARIANT;
    MAX;
    MIN;
    MPI_AGREE;
    MPI_COLLECTIVE;
    MPI_COMM_RANK;
    MPI_COMM_SIZE;
    MPI_CONSTANT;
    MPI_EMPTY_IN;
    MPI_EMPTY_OUT;
    MPI_EQUALS;
    MPI_EXPRESSION;
    MPI_EXTENT;
    MPI_OFFSET;
    MPI_VALID;
    MPI_REGION;
    NOTHING;
    NULL_ACSL;
    NUMOF;
    OBJECT_OF;
    OLD;
    OPERATOR;
    P2P;
    POINTER;
    PROD;
    PURE;
    PREDICATE;
    PREDICATE_CLAUSE;
    PREDICATE_DEFINITION;
    QUANTIFIED;
    QUANTIFIED_EXT;
    READ_ACSL;
    READS_ACSL;
    REAL_ACSL;
    RELCHAIN; // a chain of relational expressions
    RESULT_ACSL;
    REMOTE_ACCESS;
    REQUIRES_ACSL;
    SET_BINDERS;
    SET_SIMPLE;
    SIZEOF_EXPR;
    SIZEOF_TYPE;
    SPECIFIER_QUALIFIER_LIST;
    SUM;
    TERM_PARENTHESIZED;
    TERMINATES;
    TRUE_ACSL;
    TYPE_BUILTIN;
    TYPE_ID;
    UNION_ACSL;
    VALID;
    VAR_ID;
    VAR_ID_BASE;
    VAR_ID_SQUARE;
    VAR_ID_STAR;
    WAITSFOR;
    WRITE_ACSL;
}

@header
{
package edu.udel.cis.vsl.abc.front.c.parse;
}

contract
    : loop_contract 
    | function_contract 
    | predicate_contract
    | assert_contract
    ;

/* Section 2.4.2 Loop Annotations */
loop_contract
    : loop_contract_block
        ->^(LOOP_CONTRACT loop_contract_block)
    ;

loop_contract_block
    : lc+=loop_clause* lb+=loop_behavior* lv=loop_variant?
        ->^(LOOP_CONTRACT_BLOCK $lc* $lb* $lv?)
    ;

loop_clause
    : loop_invariant SEMI
        ->^(LOOP_CLAUSE loop_invariant)
    | loop_assigns SEMI
        ->^(LOOP_CLAUSE loop_assigns)
    | loop_allocation SEMI
        ->^(LOOP_CLAUSE loop_allocation)
    ;

loop_invariant
    : loop_key invariant_key term
        ->^(LOOP_INVARIANT term)
    ;

loop_assigns
    : loop_key assigns_key argumentExpressionList
        ->^(LOOP_ASSIGNS assigns_key argumentExpressionList)
    ;

loop_allocation
    : loop_key alloc_key argumentExpressionList (COMMA term)?
        ->^(LOOP_ALLOC argumentExpressionList term?)
    | loop_key frees_key argumentExpressionList
        ->^(LOOP_FREE argumentExpressionList)
    ;

loop_behavior
    : FOR ilist=id_list COLON lc+=loop_clause*
        ->^(LOOP_BEHAVIOR $ilist $lc*)
    ;

loop_variant
    : loop_key variant_key term
        ->^(LOOP_VARIANT term)
    | loop_key variant_key term FOR IDENTIFIER
        ->^(LOOP_VARIANT term IDENTIFIER)
    ;

/* sec. 2.3 Function contracts */
function_contract
    : pure_function? full_contract_block
      -> ^(FUNC_CONTRACT full_contract_block pure_function?)
    ;

/* sec. 2.6.1 Predicate and ... definitions */
predicate_contract
    : (a+=predicate_clause*) -> ^(PREDICATE  $a*)
    ; 
predicate_clause
    : predicate_key a=IDENTIFIER b=predicate_definition SEMI
        -> ^(PREDICATE_CLAUSE $a $b) 
    ;

/* simple ACSL assertion */
assert_contract
    : assert_key term SEMI -> ^(ASSERT_ACSL term)
    ;

/* ACSL predicate declaration, either binder is absent or body is
 * absent. They cannot be both absent. */
/* binders (optional) = predicate-body */
predicate_definition
    : LPAREN binders RPAREN ASSIGN term 
      -> ^(PREDICATE_DEFINITION binders term) 
    | LPAREN binders RPAREN 
      -> ^(PREDICATE_DEFINITION binders ABSENT)
    | ASSIGN term 
      -> ^(PREDICATE_DEFINITION ABSENT term) 
    ;

pure_function
    : pure_key SEMI
    ;

/* a full contract block non-terminal represents an ACSL contract
 * block for a function */
full_contract_block
    : (f+=function_clause)* (m+=contract_block)*
        (c+=completeness_clause_block)* 
        -> ^(FUNC_CONTRACT_BLOCK $f* $m* $c*) 
    ;

/* a partial contract block non-terminal represents an ACSL contract
 * block inside an MPI collective block. There is no nested MPI
 * collective block allowed */
partial_contract_block
    : (f+=function_clause)* (b+=named_behavior_block)* 
        (c+=completeness_clause_block)* 
        -> ^(FUNC_CONTRACT_BLOCK $f* $b* $c*) 
    ;

/* a block in contracts, either an mpi collective block or a behavior
* block. Behavior blocks are allowed to be inside an mpi collective
* block while an mpi collective block will not belong to a behavior
* block. An mpi collective block appears after a behavior block marks
* the end of the behavior block. */
contract_block
    : mpi_collective_block
    | named_behavior_block completeness_clause_block?
    ;

function_clause
    : requires_clause SEMI-> ^(CLAUSE_NORMAL requires_clause)
    | terminates_clause SEMI-> ^(CLAUSE_NORMAL terminates_clause)
    | simple_clause SEMI -> ^(CLAUSE_NORMAL simple_clause)
    ;

named_behavior_block
    : named_behavior -> ^(CLAUSE_BEHAVIOR named_behavior)
    ;

completeness_clause_block
    : completeness_clause SEMI -> ^(CLAUSE_COMPLETE completeness_clause)
    ;

requires_clause
    : requires_key term -> ^(REQUIRES_ACSL requires_key term)
    ;

terminates_clause
    : terminates_key term -> ^(TERMINATES terminates_key term)
    ;

binders
    : binder (COMMA binder)*
        ->^(BINDER_LIST binder+)
    ;

binder
    : type_expr variable_ident (COMMA variable_ident)*
        ->^(BINDER type_expr variable_ident+)
    ;

type_expr
    : logic_type_expr ->^(LOGIC_TYPE logic_type_expr)
    | specifierQualifierList abstractDeclarator
      -> ^(C_TYPE specifierQualifierList abstractDeclarator)
    ;

/* Start of C-like type name syntax */
specifierQualifierList
    : c_basic_type+
      -> ^(SPECIFIER_QUALIFIER_LIST c_basic_type+)
    ;

abstractDeclarator
    : pointer
      -> ^(ABSTRACT_DECLARATOR pointer ABSENT)
    | directAbstractDeclarator
      -> ^(ABSTRACT_DECLARATOR ABSENT directAbstractDeclarator)
    | pointer directAbstractDeclarator
      -> ^(ABSTRACT_DECLARATOR pointer directAbstractDeclarator)
    | -> ABSENT
    ;

directAbstractDeclarator
    : LPAREN abstractDeclarator RPAREN directAbstractDeclaratorSuffix*
      -> ^(DIRECT_ABSTRACT_DECLARATOR abstractDeclarator
           directAbstractDeclaratorSuffix*)
    | directAbstractDeclaratorSuffix+
      -> ^(DIRECT_ABSTRACT_DECLARATOR ABSENT directAbstractDeclaratorSuffix+)
    ;

pointer
    : STAR+ -> ^(POINTER STAR+)
    ;

directAbstractDeclaratorSuffix
    : LSQUARE assignmentExpression_opt RSQUARE
        -> ^(ARRAY_SUFFIX LSQUARE
             assignmentExpression_opt RSQUARE)
    ;
/* End of C-like type name syntax */


logic_type_expr
    : built_in_logic_type ->^(TYPE_BUILTIN built_in_logic_type)
    ;

c_basic_type
    : CHAR | DOUBLE | FLOAT | INT | LONG | SHORT | VOID
    ;

built_in_logic_type
    : boolean_type | integer_type | real_type
    ;

variable_ident
    : STAR variable_ident_base
        ->^(VAR_ID_STAR variable_ident_base)
    | variable_ident_base LSQUARE RSQUARE
        ->^(VAR_ID_SQUARE variable_ident_base)
    | variable_ident_base
        ->^(VAR_ID variable_ident_base)
    ;

variable_ident_base
    : IDENTIFIER
      ->^(IDENTIFIER)
    | LPAREN variable_ident RPAREN
      ->^(VAR_ID_BASE variable_ident)
    ;

guards_clause
    : executeswhen_key term ->^(EXECUTES_WHEN executeswhen_key term)
    ;

simple_clause
    : assigns_clause
    | ensures_clause 
    | allocation_clause
    | reads_clause
    | depends_clause
    | guards_clause
    | waitsfor_clause
    ;

assigns_clause
    : assigns_key argumentExpressionList ->^(ASSIGNS_ACSL assigns_key argumentExpressionList)
    ;

ensures_clause
    : ensures_key term ->^(ENSURES_ACSL ensures_key term)
    ;

allocation_clause
    : alloc_key argumentExpressionList ->^(ALLOC alloc_key argumentExpressionList)
    | frees_key argumentExpressionList ->^(FREES frees_key argumentExpressionList)
    ;

reads_clause
    : reads_key argumentExpressionList ->^(READS_ACSL reads_key argumentExpressionList)
    ;

waitsfor_clause
    : waitsfor_key argumentExpressionList -> ^(WAITSFOR waitsfor_key argumentExpressionList)
    ;

depends_clause
    : dependson_key event_list ->^(DEPENDSON dependson_key event_list)
    ;

event_list
    : event (COMMA event)* -> ^(EVENT_LIST event+)
    ;

event
    : event_base PLUS event_base
        -> ^(EVENT_PLUS event_base event_base)
    | event_base SUB event_base
        -> ^(EVENT_SUB event_base event_base)
    | event_base AMPERSAND event_base
        -> ^(EVENT_INTS event_base event_base)
    | event_base
        -> ^(EVENT_BASE event_base)
    ;

event_base
    : read_key LPAREN argumentExpressionList RPAREN
        -> ^(READ_ACSL read_key argumentExpressionList)
    | write_key LPAREN argumentExpressionList RPAREN
        -> ^(WRITE_ACSL write_key argumentExpressionList)
    | access_key LPAREN argumentExpressionList RPAREN
        -> ^(ACCESS_ACSL access_key argumentExpressionList)
    | call_key LPAREN IDENTIFIER (COMMA argumentExpressionList)? RPAREN
        -> ^(CALL_ACSL call_key IDENTIFIER argumentExpressionList?)
    | nothing_key
    | anyact_key
    | LPAREN event RPAREN
        -> ^(EVENT_PARENTHESIZED event)
    ;

/* ACSL-MPI extensions: constructors */
mpi_collective_block
    : mpicollective_key LPAREN IDENTIFIER COMMA kind=mpi_collective_kind  RPAREN COLON
      c=partial_contract_block -> ^(MPI_COLLECTIVE mpicollective_key IDENTIFIER $kind $c)
    ;



/* sec. 2.3.3 contracts with named behaviors */
named_behavior
    : behavior_key IDENTIFIER COLON behavior_body
        -> ^(BEHAVIOR behavior_key IDENTIFIER behavior_body)
    ;

behavior_body
    : (b+=behavior_clause SEMI)+ -> ^(BEHAVIOR_BODY $b+)
    ;

behavior_clause
    : assumes_clause 
    | requires_clause
    | simple_clause
    ;

assumes_clause
    : assumes_key term ->^(ASSUMES_ACSL assumes_key term)
    ;

completeness_clause
    : completes_key behaviors_key id_list
        -> ^(BEHAVIOR_COMPLETE completes_key behaviors_key id_list)
    | disjoint_key behaviors_key id_list
        -> ^(BEHAVIOR_DISJOINT disjoint_key behaviors_key id_list)
    ;

id_list
    :
    | IDENTIFIER (COMMA IDENTIFIER)* -> ^(ID_LIST IDENTIFIER+)
    ;

/* C11 section 6.5 Expressions: Grammar here is organized with a
 * backwards order against the order of sub-sections in C11 standard,
 * because it's a more viewful way to illustrate how expressions will
 * be derived
 */
 
 /* ****************************** Expressions ******************************* */

// SFS: why is this called a "term"?  Why not "formula"?
term
    : quantifierExpression | assignmentExpression 
    ;
    
quantifierExpression
	: forall_key binders SEMI term
	   -> ^(QUANTIFIED forall_key binders term) 
    | exists_key binders SEMI term
       -> ^(QUANTIFIED exists_key binders term) 
	| lambda_key binders SEMI term
	   -> ^(LAMBDA_ACSL lambda_key binders term)
	;

/* SFS: Does ACSL have an assignment expression?
 * 6.5.16 
 * assignment-expression
 *   conditional-expression
 *   unary-expression assignment-operator assignment-expression
 * Tree:
 * Root: OPERATOR
 * Child 0: ASSIGN, in ACSL other side-effective assign operators 
 *          are not allowed
 * Child 1: ARGUMENT_LIST
 * Child 1.0: unaryExpression
 * Child 1.1: assignmentExpression
 */
assignmentExpression
	: (unaryExpression ASSIGN)=> unaryExpression ASSIGN assignmentExpression
	  -> ^(OPERATOR ASSIGN
            ^(ARGUMENT_LIST unaryExpression assignmentExpression))
	| conditionalExpression
	;

assignmentExpression_opt
    : -> ABSENT
    | assignmentExpression
    ;

/* 6.5.15
 * In C11 it is
 * conditional-expression:
 *   logical-OR-expression
 *   logical-OR-expression ? expression : conditional-expression
 * 
 * Note:  "a?b:c?d:e".  Is it (1) "(a?b:c)?d:e"  or (2) "a?b:(c?d:e)".
 * Answer is (2), it is "right associative".
 *
 * Note: the order matters in the two alternatives below.
 * The alternatives are tried in order from first to last.
 * Therefore it is necessary for the non-empty to appear first.
 * Else the empty will always be matched.
 */
conditionalExpression
	: a=logicalEquivExpression
        ( QMARK b=conditionalExpression COLON 
            (c=quantifierExpression | c=conditionalExpression)
            -> ^(OPERATOR QMARK ^(ARGUMENT_LIST $a $b $c))
        | -> $a
    	)
	;

/* ACSL Logical equivalence: a<==>b.
 * Left associative: a<==>b<==>c means (a<==>b)<==>c.
 */
logicalEquivExpression
	: (a=logicalImpliesExpression -> $a)
        ( EQUIV_ACSL (b=quantifierExpression | b=logicalImpliesExpression)
            -> ^(OPERATOR EQUIV_ACSL ^(ARGUMENT_LIST $logicalEquivExpression $b))
        )*
    ;

/* ACSL logical implies expression: a==>b.
 * NOTE: *RIGHT* associative: a==>b==>c is a==>(b==>c).
 */
logicalImpliesExpression
	: a=logicalOrExpression
        ( op=(IMPLIES|IMPLIES_ACSL) (b=quantifierExpression | b=logicalImpliesExpression)
            -> ^(OPERATOR $op ^(ARGUMENT_LIST $a $b))
        | -> $a
        )
    ;

/* logical-OR-expression: a||b.
 * Left associative: a||b||c is (a||b)||c.
 */
logicalOrExpression
	: (a=logicalXorExpression -> $a)
        ( OR (b=quantifierExpression | b=logicalXorExpression)
            -> ^(OPERATOR OR ^(ARGUMENT_LIST $logicalOrExpression $b))
        )*
	;
	
/* ACSL logical exclusive or: a^^b.
 * Left associative.
 */
logicalXorExpression
	: (a=logicalAndExpression -> $a)
        ( XOR_ACSL (b=quantifierExpression | b=logicalAndExpression)
            -> ^(OPERATOR XOR_ACSL ^(ARGUMENT_LIST $logicalXorExpression $b))
        )*
	;

/* 6.5.13, logical and: a && b.
 * Left associative.
 */
logicalAndExpression
	: (a=bitwiseEquivExpression -> $a)
        ( AND (b=quantifierExpression | b=bitwiseEquivExpression)
            -> ^(OPERATOR AND ^(ARGUMENT_LIST $logicalAndExpression $b))
        )*
	;

/* ACSL bitwise equivalence: a <--> b. 
 * Left associative.
 */
bitwiseEquivExpression
	: (a=bitwiseImpliesExpression -> $a)
        ( bitequiv_op b=bitwiseImpliesExpression
            -> ^(OPERATOR BEQUIV_ACSL ^(ARGUMENT_LIST $bitwiseEquivExpression $b))
        )*
	;

/* ACSL bitwise implies: a-->b.
 * RIGHT associative
 */
bitwiseImpliesExpression
	: a=inclusiveOrExpression
        ( op=bitimplies_op b=bitwiseImpliesExpression
            -> ^(OPERATOR BIMPLIES_ACSL ^(ARGUMENT_LIST $a $b))
        | -> $a
        )
	;

// TODO: SFS: look at this, it doesn't make sense...
/* 6.5.12 *
 * Bitwise inclusive OR
 * inclusive-OR-expression:
 *   exclusive-OR-expression
 *   inclusive-OR-expression | exclusive-OR-expression
 *
 * Note: the syntatic predicate before BITOR is to solve the ambiguity with
 *       set expressions because ACSL type names are parsed as IDENTIFIER tokens.
 * For example, {a|integer | integer a; a<10}.
 * The first | is a bitor operator and the first "integer" is some variable name.
 * Without the predicate, the grammar would consider the second | as an bitor operator
 * and crashes, because "integer" is an IDENTIFIER token and it thinkgs that the second
 * "integer" is an identifier expression.
 */
inclusiveOrExpression
	: ( exclusiveOrExpression -> exclusiveOrExpression )
	  ( {!(input.LA(2)==IDENTIFIER && input.LA(3)==IDENTIFIER)}?BITOR y=exclusiveOrExpression
	    -> ^(OPERATOR BITOR ^(ARGUMENT_LIST $inclusiveOrExpression $y))
	  )*
	;

/* 6.5.11 *
 * Bitwise exclusive OR
 * exclusive-OR-expression:
 *   AND-expression
 *   exclusive-OR-expression ^ AND-expression
 */
exclusiveOrExpression
    : ( andExpression -> andExpression )
	  ( BITXOR y=andExpression
	    -> ^(OPERATOR BITXOR ^(ARGUMENT_LIST $exclusiveOrExpression $y))
	  )*
	;

/* 6.5.10 *
 * Bitwise AND
 * AND-expression
 *   equality-expression
 *   AND-expression & equality-expression
 */
andExpression
	: ( relationalExpression -> relationalExpression )
	  ( AMPERSAND y=relationalExpression
	    -> ^(OPERATOR AMPERSAND ^(ARGUMENT_LIST $andExpression $y))
	  )*
	;


/*
  Note on ACSL relational expressions, from the ACSL manual:
  The construct t1 relop1 t2 relop2 t3 · · · tk
  with several consecutive comparison operators is a shortcut
  (t1 relop1 t2) && (t2 relop2 t3) && ···.
  It is required that the relopi operators must be in the same “direction”,
  i.e. they must all belong either to {<, <=, ==} or to {>,>=,==}.
  Expressions such as x < y > z or x != y != z are not allowed.

  Also, <,<=,>=,> have higher precedence than == and !=.  Though
  not sure what that means, so ignoring it.

  "a<b==c" means "a<b && b==c".

   a<b<c<d : (and (a<b) (and (b<c) (c<d)))

  Grammar:  The following works but doesn't check for illegal expressions.
  Better: create a new node RELCHAIN
  args: a < b <= c < d, in order, then check and assemble in Java code.

relationalExpression
    : x=shiftExpression
        ( r=relChain[(Tree)$x.tree] -> $r
        | -> $x
        )
    ;

// t is the tree of a single shiftExpression,  t < y (< ...) 
relChain[Tree t]
    : r=relOp y=shiftExpression
        ( z=relChain[(Tree)$y.tree]
            -> ^(OPERATOR AND ^(ARGUMENT_LIST
                    ^(OPERATOR $r ^(ARGUMENT_LIST {$t} $y))
            $z))
        | -> ^(OPERATOR $r ^(ARGUMENT_LIST {$t} $y))
        )
    ;
*/

/* A relational operator */
relOp: EQUALS | NEQ | LT | LTE | GT | GTE ;

/* A relational expression or chain of such expressions.
 * Returns a tree with root RELCHAIN and then the sequence
 * that alternates shiftExpression, relational operator,
 * and begins and ends with a shiftExpression.
 */
relationalExpression
    : x=shiftExpression
        ( (s+=relOp s+=shiftExpression)+ -> ^(RELCHAIN $x $s*)
        | -> $x
        )
    ;


/* 6.5.7 *
 * In C11:
 * shift-expression:
 *   additive-expression
 *   shift-expression <</>> additive-expression
 *
 * CIVL-C extends C11 with a range-expression. see range-expression
 * shift-expression:
 *   range-expression:
 *   shift-expression <</>> range-expression
 */
shiftExpression
	: (rangeExpression -> rangeExpression)
        ( SHIFTLEFT y=rangeExpression
          -> ^(OPERATOR SHIFTLEFT ^(ARGUMENT_LIST $shiftExpression $y))
        | SHIFTRIGHT y=rangeExpression
          -> ^(OPERATOR SHIFTRIGHT ^(ARGUMENT_LIST $shiftExpression $y))
        )*
	;

/* 6.5.6.5 *
 *
 * CIVL-C range expression "lo .. hi" or "lo .. hi # step" 
 * a + b .. c + d is equivalent to (a + b) .. (c + d) 
 * (*,/,%) > (+,-) > range > shift > ...
 */
rangeExpression
	: x=additiveExpression
        ( DOTDOT s=rangeSuffix -> ^(DOTDOT $x $s)
        | -> $x
        )
    ;

rangeSuffix
    : additiveExpression (HASH! additiveExpression)?
    ;

/* 6.5.6 *
 * additive-expression:
 *   multiplicative-expression
 *   additive-expression +/- multiplicative-expression
 */
additiveExpression
	: (multiplicativeExpression -> multiplicativeExpression)
        ( PLUS y=multiplicativeExpression
          -> ^(OPERATOR PLUS ^(ARGUMENT_LIST $additiveExpression $y))
        | SUB y=multiplicativeExpression
          -> ^(OPERATOR SUB ^(ARGUMENT_LIST $additiveExpression $y))
        )*
	;

/* 6.5.5 * 
 * In C11:
 * multiplicative-expression:
 *   cast-expression
 *   multiplicative-expression STAR/DIV/MOD cast-expression
 */
multiplicativeExpression
	: (castExpression -> castExpression)
	( STAR y=castExpression
	  -> ^(OPERATOR STAR ^(ARGUMENT_LIST $multiplicativeExpression $y))
	| DIV y=castExpression
	  -> ^(OPERATOR DIV ^(ARGUMENT_LIST $multiplicativeExpression $y))
    | MOD y=castExpression
	  -> ^(OPERATOR MOD ^(ARGUMENT_LIST $multiplicativeExpression $y))
    )*
	;

/* 6.5.4 *
 * cast-expression:
 *   unary-expression
 *   (type-name) cast-expression
 *
 */
// ambiguity 1: (expr) is a unary expression and looks like (typeName).
// ambiguity 2: (typeName){...} is a compound literal and looks like cast
castExpression
	: (LPAREN type_expr RPAREN)=> l=LPAREN type_expr RPAREN castExpression
	  -> ^(CAST type_expr castExpression)
	| unaryExpression
	;

/* 6.5.3 *
 * unary-expression:
 *   postfix-expression
 *   ++/--/sizeof unary-expression
 *   unary-operator cast-expression
 *   sizeof (type-name)
 */
unaryExpression
	: postfixExpression
	| unary_op (b=castExpression | b=quantifierExpression)
        -> ^(OPERATOR unary_op ^(ARGUMENT_LIST $b))
	| (SIZEOF LPAREN type_expr)=> SIZEOF LPAREN type_expr RPAREN
        -> ^(SIZEOF_TYPE type_expr)
	| SIZEOF unaryExpression
        -> ^(SIZEOF_EXPR unaryExpression)
    | union_key LPAREN argumentExpressionList RPAREN
        -> ^(UNION_ACSL union_key argumentExpressionList RPAREN)
    | inter_key LPAREN argumentExpressionList RPAREN
        -> ^(INTER inter_key argumentExpressionList RPAREN)
    | valid_key LPAREN term RPAREN
        -> ^(VALID valid_key term RPAREN)
    | extendedQuantification ->^(QUANTIFIED_EXT extendedQuantification)
    | object_of_key LPAREN term RPAREN -> ^(OBJECT_OF object_of_key LPAREN term RPAREN)
    | mpi_expression -> ^(MPI_EXPRESSION mpi_expression)
    | old_key LPAREN term RPAREN 
        -> ^(OLD old_key term RPAREN)
	;

extendedQuantification
	: sum_key LPAREN term COMMA term COMMA term RPAREN
        -> ^(SUM sum_key term+)
    | max_key LPAREN term COMMA term COMMA term RPAREN
        -> ^(MAX max_key term+)
    | min_key LPAREN term COMMA term COMMA term RPAREN
        -> ^(MIN min_key term+)
    | product_key LPAREN term COMMA term COMMA term RPAREN
        -> ^(PROD product_key term+)
    | numof_key LPAREN term COMMA term COMMA term RPAREN
        -> ^(NUMOF numof_key term+)
	;

/* 6.5.2 *
 * postfix-expression:
 *   primary-expression
 *   postfix-expression [expression]
 *   postfix-expression (argument-expression-list)
 *   postfix-expression . identifier
 *   postfix-expression -> identifier
 *   postfix-expression ++
 *   postfix-expression --
 *   (type-name) {initializer-list}
 *   (type-name) {initializer-list, }
 */
postfixExpression
	: (primaryExpression -> primaryExpression)
		// array index operator:
	  ( l=LSQUARE term RSQUARE
	    -> ^(OPERATOR
	           INDEX[$l]
	           ^(ARGUMENT_LIST $postfixExpression term)
	           RSQUARE)
	  |	// function call:
	    LPAREN argumentExpressionList RPAREN
	    -> ^(FUNC_CALL $postfixExpression argumentExpressionList
	    	 )
	  | DOT IDENTIFIER
	    -> ^(DOT $postfixExpression IDENTIFIER)
	  | ARROW IDENTIFIER
	    -> ^(ARROW $postfixExpression IDENTIFIER)
	  )*
	 ;

/* 6.5.2 */
argumentExpressionList
	: -> ^(ARGUMENT_LIST)
	| assignmentExpression (COMMA assignmentExpression)*
	  -> ^(ARGUMENT_LIST assignmentExpression+)
	;

/* 6.5.1 */
primaryExpression
	: constant
    | IDENTIFIER
	| STRING_LITERAL
    | LCURLY term BITOR binders (SEMI term)? RCURLY
        ->^(SET_BINDERS term binders term?)
    | LCURLY term RCURLY
        ->^(SET_SIMPLE term)
	| LPAREN term RPAREN 
	  	-> ^(TERM_PARENTHESIZED term)
	| remoteExpression
	;


/* 6.5.0.1 *
 * remote-expression:
 *    REMOTE_ACCESS ( identifier , shiftExpression ).
 * A remote-expression should be used in the same way as a variable 
 * identifier.
 */
remoteExpression
    : remote_key LPAREN a=shiftExpression COMMA b=term RPAREN
        -> ^(REMOTE_ACCESS remote_key  $a $b)
	;
    
/* 6.6 */
constantExpression
	: conditionalExpression 
	;

constant
	: INTEGER_CONSTANT
	| FLOATING_CONSTANT
	| CHARACTER_CONSTANT
	| true_key | false_key  | result_key | nothing_key | ELLIPSIS
    | SELF | null_key
    | mpi_constant -> ^(MPI_CONSTANT mpi_constant)
	;

/* ACSL-MPI extensions Expressions and Constants  */
mpi_expression
    : mpiemptyin_key LPAREN term RPAREN
        -> ^(MPI_EMPTY_IN mpiemptyin_key term)
    | mpiemptyout_key LPAREN term RPAREN
        -> ^(MPI_EMPTY_OUT mpiemptyout_key term)
    | mpiagree_key LPAREN a=term RPAREN 
        -> ^(MPI_AGREE mpiagree_key $a) 
    | mpiregion_key LPAREN a=term COMMA b=term COMMA c=term RPAREN
        -> ^(MPI_REGION mpiregion_key $a $b $c)
    | mpiequals_key LPAREN a=term COMMA b=term RPAREN
        -> ^(MPI_EQUALS mpiequals_key $a $b)
    | mpiextent_key LPAREN a=primaryExpression RPAREN
        -> ^(MPI_EXTENT mpiextent_key $a)
    | mpioffset_key LPAREN a=term COMMA b=term COMMA c=term RPAREN
        -> ^(MPI_OFFSET mpioffset_key $a $b $c)
    | mpivalid_key LPAREN a=term COMMA b=term COMMA c=term RPAREN
        -> ^(MPI_VALID mpivalid_key $a $b $c)
    ;

mpi_constant
    : mpicommrank_key |  mpicommsize_key
    ;
	
mpi_collective_kind
    : col_key | p2p_key | both_key
    ;

bitimplies_op
	: MINUSMINUS GT
	;	
	
bitequiv_op
	: LT MINUSMINUS GT
	;	

unary_op
    : PLUS | SUB | NOT | TILDE | STAR | AMPERSAND
    ;
    
/* rules for ACSL types */
boolean_type
    : {input.LT(1).getText().equals("boolean")}? IDENTIFIER
        -> ^(BOOLEAN IDENTIFIER)
    ;

integer_type
    : {input.LT(1).getText().equals("integer")}? IDENTIFIER
        -> ^(INTEGER IDENTIFIER)
    ;

real_type
    : {input.LT(1).getText().equals("real")}? IDENTIFIER
        -> ^(REAL_ACSL IDENTIFIER)
    ;

/* rules for ACSL contract clause keywords */
    
alloc_key 
    : {input.LT(1).getText().equals("allocates")}? IDENTIFIER
    ; 

assigns_key 
    : {input.LT(1).getText().equals("assigns")}? IDENTIFIER
    ; 

assumes_key 
    : {input.LT(1).getText().equals("assumes")}? IDENTIFIER
    ; 

assert_key
    : {input.LT(1).getText().equals("assert")}? IDENTIFIER
    ;

behaviors_key 
    : {input.LT(1).getText().equals("behaviors")}? IDENTIFIER
    ; 

behavior_key 
    : {input.LT(1).getText().equals("behavior")}? IDENTIFIER
    ; 

completes_key 
    : {input.LT(1).getText().equals("complete")}? IDENTIFIER
    ; 

decreases_key
    : {input.LT(1).getText().equals("decreases")}? IDENTIFIER
    ; 

disjoint_key 
    : {input.LT(1).getText().equals("disjoint")}? IDENTIFIER
    ; 

ensures_key 
    : {input.LT(1).getText().equals("ensures")}? IDENTIFIER
    ;    
  
frees_key
    : {input.LT(1).getText().equals("frees")}? IDENTIFIER
    ; 
  
invariant_key
    : {input.LT(1).getText().equals("invariant")}? IDENTIFIER
    ;

loop_key 
	: {input.LT(1).getText().equals("loop")}? IDENTIFIER
    ; 

requires_key
    : {input.LT(1).getText().equals("requires")}? IDENTIFIER
    ;
    
terminates_key
    : {input.LT(1).getText().equals("terminates")}? IDENTIFIER
    ;
   
variant_key
    : {input.LT(1).getText().equals("variant")}? IDENTIFIER
    ;

waitsfor_key
    : {input.LT(1).getText().equals("waitsfor")}? IDENTIFIER
    ;

predicate_key
	: {input.LT(1).getText().equals("predicate")}? IDENTIFIER
	;

/* ACSL terms keywords */
/* keywords of terms */
empty_key
    : {input.LT(1).getText().equals("\\empty")}? EXTENDED_IDENTIFIER
    ;

exists_key
    : {input.LT(1).getText().equals("\\exists")}? EXTENDED_IDENTIFIER
    -> ^(EXISTS_ACSL EXTENDED_IDENTIFIER)
    ;

false_key
    : {input.LT(1).getText().equals("\\false")}? EXTENDED_IDENTIFIER
    -> ^(FALSE_ACSL EXTENDED_IDENTIFIER)
    ;

forall_key
    : {input.LT(1).getText().equals("\\forall")}? EXTENDED_IDENTIFIER
    -> ^(FORALL_ACSL EXTENDED_IDENTIFIER)
    ;

inter_key
    : {input.LT(1).getText().equals("\\inter")}? EXTENDED_IDENTIFIER
    ;

let_key
    : {input.LT(1).getText().equals("\\let")}? EXTENDED_IDENTIFIER
    ;

nothing_key
    : {input.LT(1).getText().equals("\\nothing")}? EXTENDED_IDENTIFIER
    -> ^(NOTHING EXTENDED_IDENTIFIER)
    ;

null_key
    : {input.LT(1).getText().equals("\\null")}? EXTENDED_IDENTIFIER
    -> ^(NULL_ACSL EXTENDED_IDENTIFIER)
    ;

old_key
    : {input.LT(1).getText().equals("\\old")}? EXTENDED_IDENTIFIER
    ;

result_key
    : {input.LT(1).getText().equals("\\result")}? EXTENDED_IDENTIFIER
    -> ^(RESULT_ACSL EXTENDED_IDENTIFIER)
    ;

true_key
    : {input.LT(1).getText().equals("\\true")}? EXTENDED_IDENTIFIER
    -> ^(TRUE_ACSL EXTENDED_IDENTIFIER)
    ;

union_key
    : {input.LT(1).getText().equals("\\union")}? EXTENDED_IDENTIFIER
    ;

valid_key
    : {input.LT(1).getText().equals("\\valid")}? EXTENDED_IDENTIFIER
    ;

with_key
    : {input.LT(1).getText().equals("\\with")}? EXTENDED_IDENTIFIER
    ;

/* ACSL CIVL extension */
executeswhen_key
    : {input.LT(1).getText().equals("executes_when")}? IDENTIFIER
    ; 

pure_key
    : {input.LT(1).getText().equals("pure")}? IDENTIFIER
    -> ^(PURE IDENTIFIER)
    ;

reads_key
    : {input.LT(1).getText().equals("reads")}? IDENTIFIER
    ;
    
remote_key
    : {input.LT(1).getText().equals("\\on")}? EXTENDED_IDENTIFIER
    ;

/* ACSL dependence-specification extension */

access_key
    : {input.LT(1).getText().equals("\\access")}? EXTENDED_IDENTIFIER
//    -> ^(ACCESS_ACSL EXTENDED_IDENTIFIER)
    ;

anyact_key
    : {input.LT(1).getText().equals("\\anyact")}? EXTENDED_IDENTIFIER
    -> ^(ANYACT EXTENDED_IDENTIFIER)
    ;


call_key
    : {input.LT(1).getText().equals("\\call")}? EXTENDED_IDENTIFIER
    ;

dependson_key
    : {input.LT(1).getText().equals("depends_on")}? IDENTIFIER
    ;
    
object_of_key
    : {input.LT(1).getText().equals("\\object_of")}? EXTENDED_IDENTIFIER
    ; 

read_key
    : {input.LT(1).getText().equals("\\read")}? EXTENDED_IDENTIFIER
//    -> ^(READ_ACSL EXTENDED_IDENTIFIER)
    ;
    
region_of_key
    : {input.LT(1).getText().equals("\\region_of")}? EXTENDED_IDENTIFIER
    ;

write_key
    : {input.LT(1).getText().equals("\\write")}? EXTENDED_IDENTIFIER
//    -> ^(WRITE_ACSL EXTENDED_IDENTIFIER)
    ;
    
/* ACSL MPI-extension keywords */

both_key
    : {input.LT(1).getText().equals("BOTH")}? IDENTIFIER
    -> ^(BOTH IDENTIFIER)
    ;

col_key
    : {input.LT(1).getText().equals("COL")}? IDENTIFIER
    -> ^(COL IDENTIFIER)
    ;

p2p_key
    : {input.LT(1).getText().equals("P2P")}? IDENTIFIER
    -> ^(P2P IDENTIFIER)
    ;

mpiagree_key
    : {input.LT(1).getText().equals("\\mpi_agree")}? EXTENDED_IDENTIFIER
//    -> ^(MPI_AGREE EXTENDED_IDENTIFIER)
    ;

mpicollective_key
    : {input.LT(1).getText().equals("\\mpi_collective")}? EXTENDED_IDENTIFIER
//    -> ^(MPI_COLLECTIVE EXTENDED_IDENTIFIER)
    ;

mpicommsize_key
    : {input.LT(1).getText().equals("\\mpi_comm_size")}? EXTENDED_IDENTIFIER
    -> ^(MPI_COMM_SIZE EXTENDED_IDENTIFIER)
    ;

mpicommrank_key
    : {input.LT(1).getText().equals("\\mpi_comm_rank")}? EXTENDED_IDENTIFIER
    -> ^(MPI_COMM_RANK EXTENDED_IDENTIFIER)
    ;

mpiemptyin_key
    : {input.LT(1).getText().equals("\\mpi_empty_in")}? EXTENDED_IDENTIFIER
//    -> ^(MPI_EMPTY_IN EXTENDED_IDENTIFIER)
    ;

mpiemptyout_key
    : {input.LT(1).getText().equals("\\mpi_empty_out")}? EXTENDED_IDENTIFIER
//    -> ^(MPI_EMPTY_OUT EXTENDED_IDENTIFIER)
    ;

mpiequals_key
    : {input.LT(1).getText().equals("\\mpi_equals")}? EXTENDED_IDENTIFIER
//    -> ^(MPI_EQUALS EXTENDED_IDENTIFIER)
    ;

mpiextent_key
    : {input.LT(1).getText().equals("\\mpi_extent")}? EXTENDED_IDENTIFIER
//    -> ^(MPI_EXTENT EXTENDED_IDENTIFIER)
    ;

mpioffset_key
    : {input.LT(1).getText().equals("\\mpi_offset")}? EXTENDED_IDENTIFIER
//    -> ^(MPI_OFFSET EXTENDED_IDENTIFIER)
    ;

mpivalid_key
	: {input.LT(1).getText().equals("\\mpi_valid")}? EXTENDED_IDENTIFIER
        ;

mpiregion_key
	: {input.LT(1).getText().equals("\\mpi_region")}? EXTENDED_IDENTIFIER
	;

/** ACSL higher-order keywords */
lambda_key
	: {input.LT(1).getText().equals("\\lambda")}? EXTENDED_IDENTIFIER
	;
	
sum_key
	: {input.LT(1).getText().equals("\\sum")}? EXTENDED_IDENTIFIER
	;

max_key
	: {input.LT(1).getText().equals("\\max")}? EXTENDED_IDENTIFIER
	;
	
min_key
	: {input.LT(1).getText().equals("\\min")}? EXTENDED_IDENTIFIER
	;

product_key
	: {input.LT(1).getText().equals("\\product")}? EXTENDED_IDENTIFIER
	;
	
numof_key
	: {input.LT(1).getText().equals("\\numof")}? EXTENDED_IDENTIFIER
	;
