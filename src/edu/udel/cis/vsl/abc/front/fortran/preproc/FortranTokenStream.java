/*******************************************************************************
 * Copyright (c) 2005, 2006 Los Alamos National Security, LLC.
 * This material was produced under U.S. Government contract DE-AC52-06NA25396
 * for Los Alamos National Laboratory (LANL), which is operated by the Los Alamos
 * National Security, LLC (LANS) for the U.S. Department of Energy. The U.S. Government has
 * rights to use, reproduce, and distribute this software. NEITHER THE
 * GOVERNMENT NOR LANS MAKES ANY WARRANTY, EXPRESS OR IMPLIED, OR
 * ASSUMES ANY LIABILITY FOR THE USE OF THIS SOFTWARE. If software is modified
 * to produce derivative works, such modified software should be clearly marked,
 * so as not to confuse it with the version available from LANL.
 *
 * Additionally, this program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package edu.udel.cis.vsl.abc.front.fortran.preproc;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.LegacyCommonTokenStream;
import org.antlr.runtime.Token;

import edu.udel.cis.vsl.abc.front.fortran.parse.IFortranParserAction;
import edu.udel.cis.vsl.abc.token.IF.Formation;
import edu.udel.cis.vsl.abc.token.common.CommonCivlcToken;

public class FortranTokenStream extends LegacyCommonTokenStream {
	public FortranLexer lexer;
	public int needIdent;
	public int parserBacktracking;
	public boolean matchFailed;
	@SuppressWarnings("rawtypes")
	private List currLine;
	private int lineLength;
	private Token eofToken = null;
	private ArrayList<Token> packedList;
	private ArrayList<Token> newTokenList;

	public FortranTokenStream(FortranLexer lexer) {
		super(lexer);
		this.lexer = lexer;
		this.needIdent = 0;
		this.parserBacktracking = 0;
		this.matchFailed = false;
		this.currLine = null;
		this.lineLength = 0;
		this.packedList = null;
		this.newTokenList = new ArrayList<Token>();

		this.fillBuffer();

		// For some reason antlr v3.3 LA/LT(1) no longer return <EOF> token
		// save it last token from source (EOF) and return it in LT method.
		eofToken = tokenSource.nextToken();
		eofToken.setTokenIndex(size());

		FortranStream fs = ((FortranLexer) lexer).getInput();
		eofToken.setText(fs.getFileName() + ":" + fs.getAbsolutePath());
	} // end constructor

	/**
	 * For some reason antlr v3.3 LA/LT() no longer returns <EOF> token, so save
	 * it last token from source (EOF) and return it in LT method.
	 */
	public Token LT(int k) {
		if (index() + k - 1 >= this.size()) {
			return eofToken;
		}
		return super.LT(k);
	}

	/**
	 * Create a subset list of the non-whitespace tokens in the current line.
	 */
	private ArrayList<Token> createPackedList() {
		int i = 0;
		Token tk = null;

		ArrayList<Token> pList = new ArrayList<Token>(this.lineLength + 1);

		for (i = 0; i < currLine.size(); i++) {
			tk = getTokenFromCurrLine(i);
			try {
				if (tk.getChannel() != lexer.getIgnoreChannelNumber()) {
					pList.add(tk);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		} // end for(each item in buffered line)

		// need to make sure the line was terminated with a T_EOS. this may
		// not happen if we're working on a file that ended w/o a newline
		if (pList.get(pList.size() - 1).getType() != FortranLexer.T_EOS) {
			CommonCivlcToken eos = new CommonCivlcToken(lexer.getInput(),
					FortranLexer.T_EOS, Token.DEFAULT_CHANNEL,
					lexer.getInput().index(), lexer.getInput().index() + 1,
					lexer.getFormation());
			eos.setText("\n");
			packedList.add(eos);
		}

		return pList;
	} // end createPackedList()

	public String lineToString(int lineStart, int lineEnd) {
		int i = 0;
		StringBuffer lineText = new StringBuffer();

		for (i = lineStart; i < packedList.size() - 1; i++) {
			lineText.append(packedList.get(i).getText());
		}

		return lineText.toString();
	} // end lineToString()

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getTokens(int start, int stop) {
		return super.getTokens(start, stop);
	} // end getTokens()

	public int getCurrLineLength() {
		return this.packedList.size();
	}

	public int getRawLineLength() {
		return this.currLine.size();
	}

	public int getLineLength(int start) {
		int lineLength;
		Token token;

		lineLength = 0;
		if (start >= super.tokens.size())
			return lineLength;

		// this will not give you a lexer.EOF, so may need to
		// add a T_EOS token when creating the packed list if the file
		// ended w/o a T_EOS (now new line at end of the file).
		do {
			token = super.get(start + lineLength);
			lineLength++;
		} while ((start + lineLength) < super.tokens.size()
				&& (token.getChannel() == lexer.getIgnoreChannelNumber()
						|| token.getType() != FortranLexer.T_EOS
								&& token.getType() != FortranLexer.EOF));

		return lineLength;
	} // end getLineLength()

	public int findTokenInPackedList(int start, int desiredToken) {
		Token tk;

		if (start >= this.packedList.size()) {
			return -1;
		}

		do {
			tk = (Token) (packedList.get(start));
			start++;
		} while (start < this.packedList.size()
				&& tk.getType() != desiredToken);

		if (tk.getType() == desiredToken)
			// start is one token past the one we want
			return start - 1;

		return -1;
	} // end findTokenInPackedList()

	public Token getToken(int pos) {
		if (pos >= this.packedList.size() || pos < 0) {
			System.out.println("pos is out of range!");
			System.out.println("pos: " + pos + " packedListSize: "
					+ this.packedList.size());
			return null;
		} else
			return (Token) (packedList.get(pos));
	} // end getToken()

	public Token getToken(int start, int desiredToken) {
		int index;

		index = findToken(start, desiredToken);
		if (index != -1)
			return (Token) (packedList.get(index));
		else
			return null;
	} // end getToken()

	public int findToken(int start, int desiredToken) {
		Token tk;

		if (start >= this.packedList.size()) {
			System.out.println("start is out of range!");
			System.out.println("start: " + start + " packedListSize: "
					+ this.packedList.size());
			return -1;
		}

		do {
			tk = (Token) (packedList.get(start));
			start++;
		} while (start < this.packedList.size()
				&& tk.getType() != desiredToken);

		if (tk.getType() == desiredToken)
			// start is one token past the one we want
			return start - 1;

		return -1;
	} // end findToken()

	/**
	 * Search the currLine list for the desired token.
	 */
	public int findTokenInCurrLine(int start, int desiredToken) {
		int size;
		Token tk;

		size = currLine.size();
		if (start >= size)
			return -1;

		do {
			// get the i'th object out of the list
			tk = (Token) (currLine.get(start));
			start++;
		} while (start < size && tk.getType() != desiredToken);

		if (tk.getType() == desiredToken)
			return start;

		return -1;
	} // end findTokenInCurrLine()

	/**
	 * @param pos
	 *            Current location in the currLine list; the search will begin
	 *            by looking at the next token (pos+1).
	 */
	public Token getNextNonWSToken(int pos) {
		Token tk;

		tk = (Token) (packedList.get(pos + 1));

		return tk;
	} // end getNextNonWSToken()

	/**
	 * @param pos
	 *            Current location in the currLine list; the search will begin
	 *            by looking at the next token (pos+1).
	 */
	public int getNextNonWSTokenPos(int pos) {
		Token tk;

		// find the next non WS token
		tk = getNextNonWSToken(pos);
		// find it's position now
		pos = findTokenInCurrLine(pos, tk.getType());

		return pos;
	} // end getNextNonWSTokenPos()

	public Token getTokenFromCurrLine(int pos) {
		if (pos >= currLine.size() || pos < 0) {
			return null;
		} else {
			return ((Token) (currLine.get(pos)));
		}
	} // end getTokenFromCurrLine()

	public void setCurrLine(int lineStart) {
		this.lineLength = this.getLineLength(lineStart);

		// this will get the tokens [lineStart->((lineStart+lineLength)-1)]
		currLine = this.getTokens(lineStart, (lineStart + this.lineLength) - 1);
		if (currLine == null) {
			System.err.println("currLine is null!!!!");
			System.exit(1);
		}

		// pack all non-ws tokens
		this.packedList = createPackedList();

	} // end setCurrLine()

	/**
	 * This will use the super classes methods to keep track of the start and
	 * end of the original line, not the line buffered by this class.
	 */
	public int findTokenInSuper(int lineStart, int desiredToken) {
		int lookAhead = 0;
		int tk, channel;

		/*****
		 * OBSOLETE NOTE: returning -1 is painful when looking for T_EOS // if
		 * this line is a comment, skip scanning it if (super.LA(1) ==
		 * FortranLexer.LINE_COMMENT) { return -1; } OBSOLETE
		 *****/

		do {
			// lookAhead was initialized to 0
			lookAhead++;

			// get the token
			Token token = LT(lookAhead);
			tk = token.getType();
			channel = token.getChannel();

			// continue until find what looking for or reach end
		} while ((tk != FortranLexer.EOF && tk != FortranLexer.T_EOS
				&& tk != desiredToken)
				|| channel == lexer.getIgnoreChannelNumber());

		if (tk == desiredToken) {
			// we found a what we wanted to
			return lookAhead;
		}

		return -1;
	} // end findTokenInSuper()

	public void printCurrLine() {
		System.out.println("=================================");
		System.out.println("currLine.size() is: " + currLine.size());
		System.out.println(currLine.toString());
		System.out.println("=================================");

		return;
	} // end printCurrLine()

	public void printPackedList() {

		System.out.println("*********************************");
		System.out.println("packedListSize is: " + this.packedList.size());
		System.out.println(this.packedList.toString());
		System.out.println("*********************************");

		return;
	} // end printPackedList()

	@SuppressWarnings("rawtypes")
	public void outputTokenList(IFortranParserAction actions) {
		ArrayList<Token> tmpArrayList = null;
		List tmpList = null;

		tmpList = super.getTokens();
		tmpArrayList = new ArrayList<Token>(tmpList.size());
		for (int i = 0; i < tmpList.size(); i++) {
			try {
				tmpArrayList.add((Token) tmpList.get(i));
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		for (int i = 0; i < tmpArrayList.size(); i++) {
			Token tk = tmpArrayList.get(i);
			actions.next_token(tk);
		}
	} // end printTokenList()

	public int currLineLA(int lookAhead) {
		Token tk = null;

		// get the token from the packedList
		try {
			tk = (Token) (packedList.get(lookAhead - 1));
		} catch (Exception e) {
			// e.printStackTrace();
			// System.exit(1);
			return -1;
		}
		return tk.getType();
	} // end currLineLA()

	public boolean lookForToken(int desiredToken) {
		int lookAhead = 1;
		int tk;

		do {
			// get the next token
			tk = this.LA(lookAhead);
			// update lookAhead in case we look again
			lookAhead++;
		} while (tk != FortranLexer.T_EOS && tk != FortranLexer.EOF
				&& tk != desiredToken);

		if (tk == desiredToken) {
			return true;
		} else {
			return false;
		}
	} // end testForFunction()

	public boolean appendToken(int tokenType, String tokenText,
			Formation formation) {
		CommonCivlcToken newToken = new CommonCivlcToken(tokenType, tokenText,
				formation);
		// append a token to the end of newTokenList
		return this.packedList.add(newToken);
	} // end appendToken()

	public void addToken(Token token) {
		this.packedList.add(token);
	}

	public void addToken(int index, int tokenType, String tokenText,
			Formation formation) {
		try {
			// for example:
			// index = 1
			// packedList == label T_CONTINUE T_EOS (size is 3)
			// newTokenList.size() == 22
			// 22-3+1=20
			// so, inserted between the label and T_CONTINUE
			this.packedList.add(index,
					new CommonCivlcToken(tokenType, tokenText, formation));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		return;
	} // end addToken()

	public void set(int index, Token token) {
		packedList.set(index, token);
	} // end set()

	public void add(int index, Token token) {
		packedList.add(index, token);
	}

	public void removeToken(int index) {
		packedList.remove(index);
		return;
	} // end removeToken()

	public void clearTokensList() {
		this.packedList.clear();
		return;
	} // end clearTokensList()

	public ArrayList<Token> getTokensList() {
		return this.packedList;
	} // end getTokensList()

	public void setTokensList(ArrayList<Token> newList) {
		this.packedList = newList;
		return;
	} // end setTokensList()

	public int getTokensListSize() {
		return this.packedList.size();
	} // end getTokensListSize()

	public CommonCivlcToken createToken(int type, String text, int line,
			int col, Formation formation) {
		CommonCivlcToken token = new CommonCivlcToken(type, text, formation);
		token.setLine(line);
		token.setCharPositionInLine(col);
		return token;
	} // end createToken()

	public void addTokenToNewList(Token token) {
		if (this.newTokenList.add(token) == false) {
			System.err.println("Couldn't add to newTokenList!");
		}
		return;
	}

	public void finalizeLine() {
		if (this.newTokenList.addAll(packedList) == false) {
			System.err.println("Couldn't add to newTokenList!");
		}
	} // end finalizeLine()

	public void finalizeTokenStream() {
		super.tokens = this.newTokenList;
	} // end finalizeTokenStream()

} // end class FortranTokenStream
