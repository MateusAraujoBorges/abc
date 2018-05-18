package edu.udel.cis.vsl.abc.token.IF;

import java.io.File;

/**
 * Information object for a source file processed by ABC. Wraps a {@link File}
 * with a unique integer index that can be used to identify that file in this
 * ABC invocation, and possibly other information.
 * 
 * An instance of {@link SourceFile} may be owned by a {@link FileIndexer} or
 * free (not owned). The instance can be owned by at most one
 * {@link FileIndexer}.
 * 
 * @author siegel
 */
public class SourceFile implements Comparable<SourceFile> {
	/**
	 * The {@link File} object wrapped by this {@link SourceFile}. Always non-
	 * {@code null}.
	 */
	private File file;

	/**
	 * The index of this {@link SourceFile} in the ordered list of all
	 * {@link SourceFile}s managed by the {@link FileIndexer} that is managing
	 * this {@link SourceFile}. Will be negative if this {@link SourceFile} is
	 * not owned by an indexer.
	 */
	private int index;

	private String nickname;

	// /**
	// * The {@link FileIndexer} containing this {@link SourceFile} object.
	// * Exactly one such {@link FileIndexer} exists. Will be {@code null} if
	// this
	// * {@link SourceFile} is not owned by an indexer.
	// */
	// private FileIndexer fileIndexer;

	// /**
	// * The index of this {@link SourceFile} in the ordered list of all
	// * {@link SourceFile}s which are managed by the {@link FileIndexer} and
	// * which have the same filename as this one. Value is ignore if this is
	// free
	// * (now owned).
	// */
	// private int indexInName;

	// // temporary hack. get rid of this method and fix the code that calls it
	// public SourceFile(File file, int index) {
	// this(null, file, index, -1);
	// }

	public SourceFile(File file, int index) {
		this(file, index, file.getName());
	}

	/**
	 * Constructs new {@link SourceFile} with given fields.
	 * 
	 * @param fileIndexer
	 *            the file indexer creating this object *
	 * @param file
	 *            the {@link File} object wrapped by this {@link SourceFile}
	 * @param index
	 *            the index of this {@link SourceFile} in the ordered list of
	 *            all {@link SourceFile}s managed by the {@link FileIndexer}
	 *            that is managing this {@link SourceFile}
	 * @param indexInName
	 *            the index of this {@link SourceFile} in the ordered list of
	 *            all {@link SourceFile}s which are managed by the
	 *            {@link FileIndexer} and which have the same filename as this
	 */
	// public SourceFile(FileIndexer fileIndexer, File file, int index,
	// int indexInName) {
	// this.fileIndexer = fileIndexer;
	// this.file = file;
	// this.index = index;
	// this.indexInName = indexInName;
	// }

	public SourceFile(File file, int index, String nickname) {
		this.file = file;
		this.index = index;
		this.nickname = nickname;
	}

	public File getFile() {
		return file;
	}

	public int getIndex() {
		return index;
	}

	// TODO: or get rid of reference to fileIndexer?
	// provide a way for the indexer to declare that this is a multiple file.
	// also allow for "system" and "transformer" files which are not indexed.

	/**
	 * If there is only one file in the indexer with this filename, returns the
	 * filename. Otherwise, returns a modified version of the filename in which
	 * the integer index-in-name of this source file has been inserted.
	 * 
	 * @see #getIndexInName()
	 * 
	 * @return a modified version of the filename of this sourcefile that is
	 *         unique among all source files controlled by the indexer
	 */
	public String getNickname() {
//		String filename = getFilename();
//
//		if (fileIndexer == null)
//			return filename;
//
//		int numWithSameName = fileIndexer.getSourceFilesWithName(filename)
//				.size();
//
//		if (numWithSameName == 1)
//			return filename;
//		return filename + "<" + indexInName + ">";
		return nickname;
	}

	public String toString() {
		return "SourceFile[" + index + "," + nickname + "," + file.getPath()
				+ "]";
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

	/**
	 * Returns the file name only, i.e., the last element in the path sequence
	 * specifying the file.
	 * 
	 * @return the file name
	 */
	public String getFilename() {
		return getFile().getName();
	}

	// /**
	// * Returns the index of this source file in the ordered set of source
	// files
	// * with the same filename as this. These are also numbered from 0.
	// *
	// * @return the index of this source file in the ordered set of source
	// files
	// * with the same filename as this source file
	// */
	// public int getIndexInName() {
	// return indexInName;
	// }

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

	@Override
	public int compareTo(SourceFile o) {
		int result = index - o.index;

		if (result != 0)
			return result;

		result = file.compareTo(o.file);

		return result;
	}

}
