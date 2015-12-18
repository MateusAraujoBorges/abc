package edu.udel.cis.vsl.abc.front.common.token;

import edu.udel.cis.vsl.abc.front.IF.token.CToken;
import edu.udel.cis.vsl.abc.front.IF.token.CharacterToken;
import edu.udel.cis.vsl.abc.front.IF.token.ExecutionCharacter;

public class CommonCharacterToken extends CommonCToken implements
		CharacterToken {

	/**
	 * Eclipse make me do it.
	 */
	private static final long serialVersionUID = 4443038776536972250L;

	private ExecutionCharacter character;

	public CommonCharacterToken(CToken token, ExecutionCharacter character) {
		super(token, token.getFormation());
		assert character != null;
		this.character = character;
		this.setNext(token.getNext());
		this.setTokenIndex(token.getTokenIndex());
		if (!token.isExpandable())
			this.makeNonExpandable();
	}

	@Override
	public ExecutionCharacter getExecutionCharacter() {
		return character;
	}

}
