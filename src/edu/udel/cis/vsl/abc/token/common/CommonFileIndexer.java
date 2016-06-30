package edu.udel.cis.vsl.abc.token.common;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.udel.cis.vsl.abc.token.IF.FileIndexer;
import edu.udel.cis.vsl.abc.token.IF.SourceFile;

public class CommonFileIndexer implements FileIndexer {

	private Map<File, SourceFile> sourceFileMap = new LinkedHashMap<>();

	private ArrayList<SourceFile> sourceFiles = new ArrayList<>();

	public CommonFileIndexer() {
	}

	@Override
	public int getNumSourceFiles() {
		return sourceFiles.size();
	}

	@Override
	public SourceFile getSourceFile(int index) {
		return sourceFiles.get(index);
	}

	@Override
	public SourceFile getOrAdd(File file) {
		SourceFile result = sourceFileMap.get(file);

		if (result == null) {
			result = new SourceFile(file, sourceFiles.size());
			sourceFiles.add(result);
			sourceFileMap.put(file, result);
		}
		return result;
	}

	@Override
	public void print(PrintStream out) {
		for (SourceFile sourceFile : sourceFiles) {
			out.println(
					sourceFile.getIndexName() + "\t: " + sourceFile.getPath());
		}
		out.println();
		out.flush();
	}

	@Override
	public SourceFile get(File file) {
		return sourceFileMap.get(file);
	}

}
