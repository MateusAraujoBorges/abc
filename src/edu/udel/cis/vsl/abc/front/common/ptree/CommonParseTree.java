package edu.udel.cis.vsl.abc.front.common.ptree;

import edu.udel.cis.vsl.abc.config.IF.Configuration.Language;
import edu.udel.cis.vsl.abc.front.IF.ptree.ParseTree;

public class CommonParseTree implements ParseTree {

	private Language language;

	public CommonParseTree(Language language) {
		this.language = language;
	}

	@Override
	public Language getLanguage() {
		return this.language;
	}

}
