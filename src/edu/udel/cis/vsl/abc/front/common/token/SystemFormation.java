package edu.udel.cis.vsl.abc.front.common.token;

import java.io.File;

import edu.udel.cis.vsl.abc.front.IF.token.Formation;
import edu.udel.cis.vsl.abc.front.IF.token.SourceFile;

/**
 * This formation is used to represent the formation of a token through some
 * "system" means, instead of token derived from a source file.
 * 
 * @author siegel
 * 
 */
public class SystemFormation implements Formation {

	private String identifier;

	private SourceFile file;

	public SystemFormation(String identifier, int index) {
		this.identifier = identifier;
		this.file = new SourceFile(new File(identifier), index);
	}

	@Override
	public String suffix() {
		return identifier;
	}

	@Override
	public SourceFile getLastFile() {
		return file;
	}

}
