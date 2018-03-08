package edu.udel.cis.vsl.abc.transform.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.udel.cis.vsl.abc.ast.IF.AST;
import edu.udel.cis.vsl.abc.ast.IF.ASTFactory;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Entity.EntityKind;
import edu.udel.cis.vsl.abc.ast.entity.IF.OrdinaryEntity;
import edu.udel.cis.vsl.abc.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.abc.ast.entity.IF.Variable;
import edu.udel.cis.vsl.abc.token.IF.SyntaxException;
import edu.udel.cis.vsl.abc.transform.IF.BaseTransformer;
import edu.udel.cis.vsl.abc.transform.IF.Transform;
import edu.udel.cis.vsl.abc.transform.IF.Transformer;

/**
 * External linkage variables that have declarations in block scopes are global
 * variables. And at the same time, for an aforementioned declaration, there may
 * exist local variable of the same name at a visible prior location. For
 * example: <code>
 * int x = 1;   // extern-linkage
 * 
 * void f() {
 *   int x;  // no-linkage
 *   if (1 > 0) {
 *     extern int x; // extern-linkage
 *     
 *     assert(x == 1); // x refers to the global one 
 *   }
 * }
 * </code>
 * 
 * In this case, it is hard for for the back-end to distinguish the two "x"
 * variales at the assertion. Renaming the global "x" helps the back-end to
 * distinguish them.
 * 
 * @author ziqing
 */
public class ExternLinkageVariableRenamer extends BaseTransformer {
	/**
	 * counting instances of this class.
	 */
	private static int instanceId = 0;

	/**
	 * The short code used to identify this {@link Transformer}.
	 */
	public final static String CODE = "extRename";

	/**
	 * The long name used to identify this {@link Transformer}.
	 */
	public final static String LONG_NAME = "ExternLinkageVariableRenamer";

	/**
	 * The short description of what this {@link Transformer} does.
	 */
	public final static String SHORT_DESCRIPTION = "renames variables of external linkage that "
			+ "have declarations in block scopes";

	/**
	 * The name suffix that will be appened to names of the renaming variables
	 * to form new names.
	 */
	private final static String newNameSuffix = "$ext";

	/**
	 * The numeric identifier distinguishes different programs. External linkage
	 * variables in different programs shall be renamed to different unique new
	 * names.
	 */
	private final int programID;

	public ExternLinkageVariableRenamer(ASTFactory astFactory) {
		super(CODE, LONG_NAME, SHORT_DESCRIPTION, astFactory);
		// each program has an instance of this transformer:
		this.programID = instanceId++;

	}

	private void addRenamingExternLinkageVariables(AST ast,
			Map<Entity, String> newNameMap) throws SyntaxException {
		Iterator<OrdinaryEntity> ordEntIter = ast.getExternalEntities();

		while (ordEntIter.hasNext()) {
			OrdinaryEntity entity = ordEntIter.next();

			if (entity.getEntityKind() != EntityKind.VARIABLE)
				continue;

			int numDecls = entity.getNumDeclarations();
			boolean hasBlockDecl = false;

			for (int i = 0; i < numDecls; i++)
				if (entity.getDeclaration(i).getScope()
						.getScopeKind() == ScopeKind.BLOCK) {
					hasBlockDecl = true;
					break;
				}
			if (hasBlockDecl) {
				String newName = ((Variable) entity).getName() + newNameSuffix
						+ programID;

				newNameMap.putIfAbsent(entity, newName);
			}
		}
	}

	@Override
	public AST transform(AST ast) throws SyntaxException {
		Map<Entity, String> newNameMap = new HashMap<>();

		addRenamingExternLinkageVariables(ast, newNameMap);
		return Transform.nameTransformer(newNameMap, astFactory).transform(ast);
	}
}
