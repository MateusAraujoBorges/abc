package edu.udel.cis.vsl.abc.token.IF;

import java.io.File;

/**
 * Information object for a source file processed by ABC. Wraps a {@link File}
 * with a unique integer index that can be used to identify that file in this
 * ABC invocation.
 * 
 * @author siegel
 */
public class SourceFile {

	private File file;

	private int index;

	public SourceFile(File file, int index) {
		this.file = file;
		this.index = index;
	}

	public File getFile() {
		return file;
	}

	public int getIndex() {
		return index;
	}

	public String toString() {
		return "SourceFile[" + index + "," + file.getPath() + "]";
	}

	public String getIndexName() {
		return "f" + index;
	}

	public String getName() {
		return file.getName();
	}

	public String getPath() {
		return file.getPath();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object instanceof SourceFile) {
			return file.equals(((SourceFile) object).file)
					&& index == ((SourceFile) object).index;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return file.hashCode() ^ index * 37;
	}

}
