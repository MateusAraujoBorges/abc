package edu.udel.cis.vsl.abc.main;

import java.io.File;

/**
 * A dynamic tasks specifies an action to be taken when preprocessing reveals
 * that a certain file was included.
 * 
 * @author siegel
 *
 */
public interface DynamicTask {

	/**
	 * Returns unit tasks to be added to the unit task list.
	 * 
	 * @param file
	 * @return
	 */
	UnitTask[] generateTasks(File file);
}
