package edu.udel.cis.vsl.abc.program.IF;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;

/**
 * <p>
 * This class will take a collection of ASTs, each representing one translation
 * unit, and produce one big AST representing the whole program. This big AST
 * will then be analyzed like any other AST.
 * </p>
 * 
 * <p>
 * Identifiers/entities in file scope with internal or no linkage will be
 * renamed as necessary so that identifiers naming different entities have
 * different names. For example, if Translation Unit 1 has an entity with
 * internal linkage, or with no linkage in file scope, named "x", and so does
 * Translation Unit 2, the "x" in TU1 might be renamed _TU1_x and the "x" in TU2
 * might be renamed _TU2_x. The linkage kind (external, internal, or none) of
 * all entities (including these) will remain unchanged.
 * </p>
 * 
 * <p>
 * Entities with external linkage and same name will be merged into a single
 * entity. This will occur naturally because these entities will not be renamed,
 * yet they will be declared with external linkage in one AST. When the AST is
 * analyzed, they will create one Entity.
 * </p>
 * 
 * <p>
 * The new AST is formed by essentially concatenating the external definitions
 * of the ASTs of the individual translation units (after the renaming described
 * above). The new AST is then analyzed like any AST.
 * </p>
 * 
 * @author siegel
 * 
 */
public interface ProgramFactory {

	ASTFactory getASTFactory();

	Program newProgram(AST ast) throws SyntaxException;

	Program newProgram(AST[] asts) throws SyntaxException;

	Program newProgram(Iterable<AST> asts) throws SyntaxException;

}
