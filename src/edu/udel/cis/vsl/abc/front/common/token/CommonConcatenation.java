package edu.udel.cis.vsl.abc.front.common.token;

import java.util.ArrayList;

import edu.udel.cis.vsl.abc.front.IF.token.CToken;
import edu.udel.cis.vsl.abc.front.IF.token.Concatenation;
import edu.udel.cis.vsl.abc.front.IF.token.SourceFile;

public class CommonConcatenation implements Concatenation {

	private ArrayList<CToken> constituents;

	public CommonConcatenation(ArrayList<CToken> constituents) {
		assert constituents != null;
		assert constituents.size() >= 1;
		this.constituents = constituents;
	}

	@Override
	public String suffix() {
		String result = " from concatenation of the following "
				+ getNumConstituents() + " tokens:";

		for (CToken token : constituents)
			result += "\n" + token;
		return result;
	}

	@Override
	public SourceFile getLastFile() {
		return constituents.get(0).getSourceFile();
	}

	@Override
	public int getNumConstituents() {
		return constituents.size();
	}

	@Override
	public CToken getConstituent(int index) {
		return constituents.get(index);
	}

	// @Override
	// public String fileShortName() {
	// return constituents.get(0).getFileShortName();
	// }

}
