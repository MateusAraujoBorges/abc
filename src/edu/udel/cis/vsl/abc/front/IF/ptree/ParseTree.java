package edu.udel.cis.vsl.abc.front.IF.ptree;

import edu.udel.cis.vsl.abc.astgen.IF.ASTBuilder;
import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;

/**
 * This represents a parse tree which is the result of preprocessing and parsing
 * a source in various languages, such as CIVL-C, Fortran, C, etc. It will be
 * used as the input for the AST generation module {@link ASTBuilder}.
 * 
 * @author Manchun Zheng
 *
 */
public interface ParseTree {
	/**
	 * returns the language that associates with this Parse tree
	 * 
	 * @return
	 */
	Language getLanguage();
}