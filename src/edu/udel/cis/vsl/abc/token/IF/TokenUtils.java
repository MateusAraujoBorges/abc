package edu.udel.cis.vsl.abc.token.IF;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;

/**
 * Utility class providing static methods dealing with Token objects.
 * 
 * @author siegel
 * 
 */
public class TokenUtils {

	public final static Token eofToken = new CommonToken(Token.EOF);

	/**
	 * The maximum number of tokens that will be printed when summarizing a
	 * range of tokens. If the number of tokens exceeds this bound, the ellipsis
	 * will be used in the summary.
	 */
	public final static int summaryBound = 10;

	/**
	 * A utility function to extract the filename, line number, and character
	 * index of a token of any type, and return a string representation of this
	 * in a consistent way.
	 * 
	 * @param token
	 *            any instance of Token
	 * @param abbreviated
	 *            true iff the resulting file name a the shorter one (f1, f2,
	 *            ...) instead of the original one.
	 * @return string explaining where the token came from
	 */
	public static String location(Token token, boolean abbreviated) {
		String filename = getShortFilename(token, abbreviated);
		int line = token.getLine();
		int pos = token.getCharPositionInLine();

		return filename + " " + line + "." + pos;
	}

	/**
	 * Computes a short version of the file name from a token's source file.
	 * 
	 * @param token
	 *            a token
	 * @param abbreviated
	 *            true iff the result is an abbreviated file name, i.e., shorter
	 *            file name, which is calculated by the static hash map.
	 * @return the short file name
	 */
	public static String getShortFilename(Token token, boolean abbreviated) {
		if (token instanceof CivlcToken) {
			CivlcToken ppToken = (CivlcToken) token;
			SourceFile file = ppToken.getSourceFile();

			if (abbreviated)
				return file.getIndexName();
			else
				return file.getName();
		} else {
			CharStream stream = token.getInputStream();

			if (stream == null)
				return "<unknown file>";
			else {
				String filename = stream.getSourceName();
				int separatorIndex = filename
						.lastIndexOf(File.pathSeparatorChar);

				if (separatorIndex >= 0
						&& separatorIndex < filename.length() - 1)
					filename = filename.substring(separatorIndex + 1);
				return filename;
			}
		}
	}

	/**
	 * Given a non-empty list of tokens, constructs a string which summarizes
	 * the range of text in the original source file(s) from whence those tokens
	 * came. The string may have a form such as "filename:n.a-m.b" where n is
	 * the line number of the first token, a is the character index of the first
	 * token, m is the line number of the last token, and b is the character
	 * index of the last token. Or this form may be abbreviated or modified as
	 * necessary.
	 * 
	 * @param first
	 *            first token in linked list
	 * @param last
	 *            last token in linked list
	 * @param abbreviated
	 *            should the filename be abbreviated?
	 * @return string representation of token range
	 */
	public static String summarizeRangeLocation(CivlcToken first,
			CivlcToken last, boolean abbreviated) {
		String result;
		String filename1 = getShortFilename(first, abbreviated);
		String filename2 = getShortFilename(last, abbreviated);
		int line1 = first.getLine();
		int pos1 = first.getCharPositionInLine();
		String endPosition;
		int line2, pos2;

		// This doesn't make sense in case of macro expansion.
		// the next token is misleading if the token is the
		// last replacement token...

		// CivlcToken next = last.getNext();

		// if (pos1 < 0) {
		// throw new ABCRuntimeException(
		// "Encountered token without charPositionInLine: "
		// + first.getText());
		// }

		// if (next != null) {
		// int line3 = next.getLine();
		// int pos3 = next.getCharPositionInLine();
		//
		// if (pos3 == 0) {
		// line2 = line3 - 1;
		// if (line2 == last.getLine()) {
		// // better to use start and stop indexes
		// // which may be more accurate than text
		// pos2 = last.getCharPositionInLine()
		// + last.getText().length();
		// } else {
		// pos2 = -1;
		// }
		// } else {
		// line2 = line3;
		// pos2 = pos3;
		// }
		// } else {
		line2 = last.getLine();
		if (last.getType() == Token.EOF)
			pos2 = 0;
		else
			pos2 = last.getCharPositionInLine() + last.getStopIndex()
					- last.getStartIndex();
		// }
		if (pos2 >= 0) {
			endPosition = line2 + "." + pos2;
		} else {
			endPosition = line2 + ".EOL";
		}
		if (filename1.equals(filename2)) {
			if (line1 == line2) {
				// TODO: When intermediate file used, delete below:
				// = = = = =
				if (line1 <= 0 && line2 <= 0)
					return filename1;
				// = = = = =
				if (pos1 == pos2)
					result = filename1 + ":" + line1 + "." + pos1;
				else
					result = filename1 + ":" + line1 + "." + pos1 + "-" + pos2;
			} else {
				result = filename1 + ":" + line1 + "." + pos1 + "-"
						+ endPosition;
			}
		} else {
			result = filename1 + ":" + line1 + "." + pos1 + "-" + filename2
					+ ":" + endPosition;
		}
		return result;
	}

	public static String summarizeRange(CivlcToken first, CivlcToken last,
			boolean abbreviated, boolean isException) {
		if (isException)
			return summarizeDetailedRange(first, last, abbreviated);

		String result = summarizeRangeLocation(first, last, abbreviated);
		String excerpt = "";
		int tokenCount = 0;
		CivlcToken token = first;

		while (token != null && token != last
				&& tokenCount < summaryBound - 1) {
			excerpt += token.getText();
			token = token.getNext();
			tokenCount++;
		}
		if (token != null) {
			if (token != last)
				excerpt += " ... ";
			excerpt += last.getText();
		}
		excerpt = quoteText(excerpt);
		result = result + " " + excerpt;
		{ // experimental
			Formation formation = first.getFormation();

			if (formation != null)
				result += formation.suffix();
		}
		return result;
	}

	public static String contentOfRange(CivlcToken first, CivlcToken last,
			boolean abbreviated) {
		String result = summarizeRangeLocation(first, last, abbreviated);
		String excerpt = "";
		// int tokenCount = 0;
		CivlcToken token = first;

		while (token != null && token != last) {
			excerpt += token.getText();
			token = token.getNext();
			// tokenCount++;
		}
		if (token != null) {
			if (token != last)
				excerpt += " ... ";
			excerpt += last.getText();
		}
		excerpt = quoteText(excerpt);
		result = result + " " + excerpt;
		{ // experimental
			Formation formation = first.getFormation();

			if (formation != null)
				result += formation.suffix();
		}
		return result;
	}

	/**
	 * A utility function to return the text of a token surrounded by double
	 * quotes, with newlines, returns and tabs replaced by escape sequences.
	 * 
	 * @param token
	 *            any instance of Token
	 * @return the text of the token, nicely formatted, in quotes
	 */
	public static String quotedText(Token token) {
		String txt = token.getText();

		if (txt != null)
			return quoteText(txt);
		return "<no text>";
	}

	private static String quoteText(String text) {
		String txt = text.replaceAll("\n", "\\\\n");

		txt = txt.replaceAll("\r", "\\\\r");
		txt = txt.replaceAll("\t", "\\\\t");
		return "\"" + txt + "\"";
	}

	public static TokenSource makeTokenSourceFromList(CivlcToken first) {
		return new ListTokenSource(first);
	}

	/**
	 * Get the whole line in the source file for the given token.
	 * 
	 * @param token
	 * @return
	 */
	private static String getLineContentFromToken(CivlcToken token) {
		String line = null;
		String filePath = token.getSourceFile().getFile().getAbsolutePath();

		if (token.getType() < 0)
			return token.getText();
		try {
			if (filePath.startsWith("/include")) {
				StringBuilder sBuilder = new StringBuilder();
				int lineCount = token.getLine() - 1;
				InputStream lines = Class.class.getResourceAsStream(filePath);

				char c = (char) lines.read();
				while (c != -1 && lineCount != 0){
					if (c == '\n')
						lineCount --;
					c = (char) lines.read();
				}
				while (c != -1 && c != '\n'){
					sBuilder.append(c);
					c = (char) lines.read();
				}
				line = sBuilder.toString();
				lines.close();
			} else {
				Stream<String> lines = Files.lines(Paths.get(filePath));
				line = lines.skip(token.getLine() - 1).findFirst().get();
				lines.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	/**
	 * Return the detailed source location information with highlights.
	 * 
	 * @param first
	 *            first token in linked list
	 * @param last
	 *            last token in linked list
	 * @param abbreviated
	 *            should the filename be abbreviated?
	 * @return string representation of token range
	 */
	public static String summarizeDetailedRange(CivlcToken first,
			CivlcToken last, boolean abbreviated) {
		String lineContent;
		String filename1 = getShortFilename(first, abbreviated);
		String filename2 = getShortFilename(last, abbreviated);
		int startLine = first.getLine();
		int endLine = last.getLine();
		int startIndex = first.getCharPositionInLine();
		int endIndex = last.getType() == Token.EOF
				? 0
				: last.getCharPositionInLine() + last.getStopIndex()
						- last.getStartIndex();
		int highlightCount = 0;
		int otherCount = 0;
		StringBuilder sBuilder = new StringBuilder();
		CivlcToken tempToken = null;

		if (filename1.equals(filename2)) {
			if (startLine == endLine) {
				// Construct file and location
				sBuilder.append(getShortFilename(first, false));
				sBuilder.append(":");
				sBuilder.append(startLine);
				sBuilder.append(".");
				sBuilder.append(startIndex);
				sBuilder.append("-");
				sBuilder.append(endIndex);
				sBuilder.append("\n");
				// Construct content
				lineContent = getLineContentFromToken(first);
				sBuilder.append(lineContent);
				// Calculate the position of highlights.
				tempToken = first;
				while (tempToken != null && tempToken != last) {
					highlightCount += tempToken.getText().length();
					tempToken = tempToken.getNext();
				}
				highlightCount += tempToken.getText().length();
				otherCount = first.getCharPositionInLine();
				// Construct highlight
				sBuilder.append("\n");
				for (int i = 0; i < otherCount; i++)
					if (lineContent.charAt(i) == '\t')
						sBuilder.append("\t");
					else
						sBuilder.append(" ");
				while (highlightCount > 0) {
					sBuilder.append("^");
					highlightCount--;
				}
			} else {
				// Construct file and location
				sBuilder.append(getShortFilename(first, false));
				sBuilder.append(":");
				sBuilder.append(startLine);
				sBuilder.append(".");
				sBuilder.append(startIndex);
				sBuilder.append("-");
				sBuilder.append(endLine);
				sBuilder.append(".");
				sBuilder.append(endIndex >= 0 ? endIndex >= 0 : "EOL");
				sBuilder.append("\n\t");
				// Construct content
				lineContent = getLineContentFromToken(first);
				sBuilder.append(lineContent);
				// Calculate the position of highlights.
				tempToken = first;
				while (tempToken != null && tempToken != last) {
					highlightCount += tempToken.getText().length();
					tempToken = tempToken.getNext();
				}
				highlightCount += tempToken.getText().length();
				otherCount = first.getCharPositionInLine();
				// Construct highlight
				sBuilder.append("\n");
				for (int i = 0; i < otherCount; i++)
					if (lineContent.charAt(i) == '\t')
						sBuilder.append("\t");
					else
						sBuilder.append(" ");
				while (highlightCount > 0) {
					sBuilder.append("^");
					highlightCount--;
				}
				// Construct multi-line abbreviation
				sBuilder.append("\n\t...\n");
				// Construct content
				lineContent = getLineContentFromToken(last);
				sBuilder.append(lineContent);
				// Calculate the position of highlights.
				highlightCount = last.getCharPositionInLine();
				tempToken = last;
				highlightCount += tempToken.getText().length();
				otherCount = last.getCharPositionInLine();
				// Construct highlight
				sBuilder.append("\n");
				for (int i = 0; i < otherCount; i++)
					if (lineContent.charAt(i) == '\t')
						sBuilder.append("\t");
					else
						sBuilder.append(" ");
				while (highlightCount > 0) {
					sBuilder.append("^");
					highlightCount--;
				}
			}
		} else {
			String lastFileName = last.getSourceFile().getName();
			String tempFileName = first.getSourceFile().getName();

			assert lastFileName.equals(tempFileName);
			// Construct file and location
			sBuilder.append(getShortFilename(first, false));
			sBuilder.append(":");
			sBuilder.append(startLine);
			sBuilder.append(".");
			sBuilder.append(startIndex);
			sBuilder.append("-");
			sBuilder.append(getShortFilename(last, false));
			sBuilder.append(":");
			sBuilder.append(endLine);
			sBuilder.append(".");
			sBuilder.append(endIndex >= 0 ? endIndex >= 0 : "EOL");
			sBuilder.append("\n\t");
			/* Note that this situation is rarely happened */

			// Construct content
			lineContent = getLineContentFromToken(first);
			sBuilder.append(lineContent);
			// Calculate the position of highlights.
			tempToken = first;
			while (tempToken != null && tempToken != last) {
				highlightCount += tempToken.getText().length();
				tempToken = tempToken.getNext();
			}
			highlightCount += tempToken.getText().length();
			otherCount = first.getCharPositionInLine();
			// Construct highlight
			sBuilder.append("\n");
			for (int i = 0; i < otherCount; i++)
				if (lineContent.charAt(i) == '\t')
					sBuilder.append("\t");
				else
					sBuilder.append(" ");
			while (highlightCount > 0) {
				sBuilder.append("^");
				highlightCount--;
			}
			// Construct multi-line abbreviation
			sBuilder.append("\n\t...\n");
			// Construct content
			lineContent = getLineContentFromToken(last);
			sBuilder.append(lineContent);
			// Calculate the position of highlights.
			highlightCount = last.getCharPositionInLine();
			tempToken = last;
			highlightCount += tempToken.getText().length();
			otherCount = last.getCharPositionInLine();
			// Construct highlight
			sBuilder.append("\n");
			for (int i = 0; i < otherCount; i++)
				if (lineContent.charAt(i) == '\t')
					sBuilder.append("\t");
				else
					sBuilder.append(" ");
			while (highlightCount > 0) {
				sBuilder.append("^");
				highlightCount--;
			}
		}
		return sBuilder.toString();
	}

}

/**
 * A simple TokenSource formed from a linked list of PreprocessorTokens, given
 * the first element in the list. The token source appends an infinite number of
 * invalid tokens???? after the last token in the list.
 * 
 * @author siegel
 * 
 */
class ListTokenSource implements TokenSource {

	private CivlcToken current;

	ListTokenSource(CivlcToken first) {
		this.current = first;
	}

	@Override
	public Token nextToken() {
		Token result = current;

		if (result == null)
			result = TokenUtils.eofToken;

		else
			current = current.getNext();
		return result;
	}

	@Override
	public String getSourceName() {
		if (current == null)
			return "unknown";

		CharStream stream = current.getInputStream();

		if (stream == null)
			return "unknown";

		String name = stream.getSourceName();

		if (name == null)
			return "unknown";

		return name;
	}

}
