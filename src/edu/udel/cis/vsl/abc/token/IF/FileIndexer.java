package edu.udel.cis.vsl.abc.token.IF;

import java.io.File;
import java.io.PrintStream;

public interface FileIndexer {

	int getNumSourceFiles();

	SourceFile getSourceFile(int index);
	
	SourceFile get(File file);

	SourceFile getOrAdd(File file);

	void print(PrintStream out);

}
