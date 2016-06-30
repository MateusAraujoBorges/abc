package edu.udel.cis.vsl.abc.main;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import edu.udel.cis.vsl.abc.config.IF.Configurations;
import edu.udel.cis.vsl.abc.config.IF.Configurations.Language;

/**
 * A {@link UnitTask} specifies how a single source unit will be processed in
 * order to form an AST representing a single translation unit. A single source
 * unit may be specified as a sequence of files.
 * 
 * @author siegel
 *
 */
public class UnitTask {

	/**
	 * The source files to read and preprocess. These files are essentially
	 * concatenated before being preprocessed. There is no default value; these
	 * must be specified at construction.
	 */
	private File[] sourceFiles;

	/**
	 * The language in which the source files are all written. Default is
	 * {@link Language#CIVL_C}.
	 */
	private Language language = Language.CIVL_C;

	// /**
	// * The codes specifying the transformations to apply to the translation
	// unit
	// * after its AST is constructed. Default is the empty list.
	// */
	// private List<String> transformCodes = new LinkedList<>();

	/**
	 * List of system include paths for the preprocessor. The default is the
	 * constant {@link ABC#DEFAULT_SYSTEM_INCLUDE_PATHS}.
	 */
	private File[] systemIncludes = ABC.DEFAULT_SYSTEM_INCLUDE_PATHS;

	/**
	 * List of user include paths for the preprocessor. The default is the
	 * constant {@link ABC#DEFAULT_USER_INCLUDE_PATHS}.
	 */
	private File[] userIncludes = ABC.DEFAULT_USER_INCLUDE_PATHS;

	/**
	 * Predefined macros, mapping the macro name to its body. Typically these
	 * are specified on command line with "-Dname=body". Default is empty list.
	 */
	private Map<String, String> macros = new LinkedHashMap<>();

	/**
	 * Allow GNU C features in the source files? Default is <code>false</code>.
	 */
	private boolean gnuc = false;

	/**
	 * Constructs new unit task with the given sequence of source files and
	 * default values for all other parameters.
	 * 
	 * @param sourceFiles
	 *            the sequence of files to read in and preprocess as a single
	 *            unit
	 */
	public UnitTask(File[] sourceFiles) {
		assert sourceFiles != null;
		assert sourceFiles.length >= 1;
		this.sourceFiles = sourceFiles;

		LinkedList<String> names = new LinkedList<>();

		for (File file : sourceFiles)
			names.add(file.getName());
		this.language = Configurations.bestLanguage(names);
	}

	/**
	 * Gets the language in which the source files are written. Default is
	 * {@link Language#CIVL_C}.
	 * 
	 * @return the language in which all the source files are written
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Sets the language in which all source files are written. Default is
	 * {@link Language#CIVL_C}.
	 * 
	 * @param language
	 *            the language in which all the source files are written
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * Gets the system include path sequence. The default is the constant
	 * {@link ABC#DEFAULT_SYSTEM_INCLUDE_PATHS}.
	 * 
	 * @return the system include path sequence
	 */
	public File[] getSystemIncludes() {
		return systemIncludes;
	}

	/**
	 * Sets the system include path sequence. The default is the constant
	 * {@link ABC#DEFAULT_SYSTEM_INCLUDE_PATHS}.
	 * 
	 * @param systemIncludes
	 *            the system include path sequence
	 */
	public void setSystemIncludes(File[] systemIncludes) {
		this.systemIncludes = systemIncludes;
	}

	/**
	 * Gets the user include path sequence. The default is the constant
	 * {@link ABC#DEFAULT_USER_INCLUDE_PATHS}.
	 * 
	 * @return the userIncludes the user include path sequence
	 */
	public File[] getUserIncludes() {
		return userIncludes;
	}

	/**
	 * Sets the user include path sequence. The default is the constant
	 * {@link ABC#DEFAULT_USER_INCLUDE_PATHS}.
	 * 
	 * @param userIncludes
	 *            the user include path sequence
	 */
	public void setUserIncludes(File[] userIncludes) {
		this.userIncludes = userIncludes;
	}

	/**
	 * Gets the predefined macro map. This is map which maps a macro name to its
	 * body. These are typically defined on the command line using
	 * "-Dname=body".
	 * 
	 * @return the map mapping predefined macro names to their bodies
	 */
	public Map<String, String> getMacros() {
		return this.macros;
	}

	/**
	 * Sets the predefined macro map. This is map which maps a macro name to its
	 * body. These are typically defined on the command line using
	 * "-Dname=body".
	 * 
	 * @param macros
	 *            the map mapping predefined macro names to their bodies
	 */
	public void setMacros(Map<String, String> macros) {
		this.macros = macros;
	}

	/**
	 * Gets the source files that comprise this preprocessor translation unit.
	 * Must be specified at construction time.
	 * 
	 * @return the source files
	 */
	public File[] getSourceFiles() {
		return sourceFiles;
	}

	// /**
	// * Gets the sequence of transformation codes that will be applied to the
	// * translation unit after preprocessing.
	// *
	// * @return the sequence of transformation codes
	// */
	// public Collection<String> getTransformCodes() {
	// return transformCodes;
	// }
	//
	// /**
	// * Adds a transformation code to the end of the list of transformation
	// * codes.
	// *
	// * @param code
	// * an AST transformation code
	// */
	// public void addTransformCode(String code) {
	// transformCodes.add(code);
	// }
	//
	// /**
	// * Adds a complete sequence of transformation codes to the end of the list
	// * of transformation codes for this unit task.
	// *
	// * @param codes
	// * a sequence of AST transformation codes
	// */
	// public void addAllTransformCodes(Collection<String> codes) {
	// transformCodes.addAll(codes);
	// }

	/**
	 * Are the GNU C extensions allowed in the source files for this translation
	 * unit? Default is <code>false</code>.
	 * 
	 * @return <code>true</code> iff the GNU C extensions are allowed
	 */
	public boolean getGNUC() {
		return gnuc;
	}

	/**
	 * Specifies whether the GNU C extensions are allowed in the source files
	 * for this translation unit. Default is <code>false</code>.
	 * 
	 * @param flag
	 *            <code>true</code> iff the GNU C extensions are allowed
	 */
	public void setGNUC(boolean flag) {
		this.gnuc = flag;
	}

}
