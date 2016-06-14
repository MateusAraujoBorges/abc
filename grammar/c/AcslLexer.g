lexer grammar AcslLexer;

options 
{
     tokenVocab=CivlCParser;
}

/*
 * Author: Manchun Zheng, University of Delaware
 * Last changed: June 2012
 *
 * This is a grammar for lexical analysis for ACSL.  
 * It follows the ACSL 1.9 Standard.
 *
 * The grammar has been extended to include keywords for
 * the CIVL-C extension of ACSL.
 */
 
@header{
	package edu.udel.cis.vsl.abc.front.c.preproc;
}


XOR     :   '^^';
//AMPERSAND    :   '&';
EQUIV   :   '<==>';

HASH    :   '#';

/* AT */
/*AT
    : '@'{$channel=HIDDEN;}
    ;*/


