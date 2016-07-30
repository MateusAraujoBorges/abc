package edu.udel.cis.vsl.abc.front.c.preproc;

import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ABSTRACT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ALIGNAS;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ALIGNOF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ASSIGNS;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ATOMIC;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.AUTO;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.BIG_O;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.BOOL;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.BREAK;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CALLS;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CASE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CHAR;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CHOOSE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CIVLATOM;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CIVLATOMIC;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CIVLFOR;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.COLLECTIVE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.COMPLEX;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CONST;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CONTIN;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.CONTINUE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DEFAULT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DEFINE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DEFINED;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DEPENDS;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DERIV;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DEVICE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DO;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DOMAIN;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.DOUBLE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ELIF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ELSE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ENDIF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ENSURES;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ENUM;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.ERROR;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.EXISTS;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.EXTERN;
//import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.FALSE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.FATOMIC;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.FLOAT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.FOR;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.FORALL;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.GENERIC;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.GLOBAL;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.GOTO;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.GUARD;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.HERE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.IDENTIFIER;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.IF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.IFDEF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.IFNDEF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.IMAGINARY;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.INCLUDE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.INLINE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.INPUT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.INT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.INVARIANT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.LAMBDA;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.LINE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.LONG;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.NORETURN;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.OUTPUT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.PARFOR;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.PRAGMA;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.PROCNULL;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.RANGE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.READS;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.REAL;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.REGISTER;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.REQUIRES;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.RESTRICT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.RESULT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.RETURN;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SCOPEOF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SELF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SHARED;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SHORT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SIGNED;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SIZEOF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SPAWN;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.STATIC;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.STATICASSERT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.STRUCT;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SWITCH;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.SYSTEM;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.THREADLOCAL;
//import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.TRUE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.TYPEDEF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.TYPEOF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.UNDEF;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.UNIFORM;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.UNION;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.UNSIGNED;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.VOID;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.VOLATILE;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.WHEN;
import static edu.udel.cis.vsl.abc.front.c.preproc.PreprocessorLexer.WHILE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;

import edu.udel.cis.vsl.abc.front.IF.PreprocessorException;
import edu.udel.cis.vsl.abc.front.IF.PreprocessorExpressionException;
import edu.udel.cis.vsl.abc.util.IF.ANTLRUtils;

public class PreprocessorUtils {

	private static int identifierMinIndex, identifierMaxIndex;
	private static boolean[] identifierPredicate = initIdentifierPredicate();

	private static boolean[] initIdentifierPredicate() {
		int[] identifierIndexes = new int[] { ABSTRACT, ALIGNAS, ALIGNOF,
				ASSIGNS, ATOMIC, AUTO, BIG_O, BOOL, BREAK, CALLS, CASE, CHAR,
				CHOOSE, CIVLATOM, CIVLATOMIC, CIVLFOR, COLLECTIVE, COMPLEX,
				CONST, CONTIN, CONTINUE, DEFAULT, DEFINE, DEFINED, DEPENDS,
				DERIV, DEVICE, DO, DOMAIN, DOUBLE, ELIF, ELSE, ENDIF, ENSURES,
				ENUM, ERROR, EXISTS, EXTERN, FATOMIC, FLOAT, FOR, FORALL,
				GENERIC, GLOBAL, GOTO, GUARD, HERE, IF, IFDEF, IFNDEF,
				IMAGINARY, INCLUDE, INLINE, INPUT, INT, INVARIANT, LAMBDA, LINE,
				LONG, NORETURN, OUTPUT, PARFOR, PRAGMA, PROCNULL, RANGE, READS,
				REAL, REGISTER, REQUIRES, RESTRICT, RESULT, RETURN, SCOPEOF,
				SELF, SHARED, SHORT, SIGNED, SIZEOF, SPAWN, STATIC,
				STATICASSERT, STRUCT, SWITCH, SYSTEM, THREADLOCAL, TYPEDEF,
				TYPEOF, UNDEF, UNIFORM, UNION, UNSIGNED, VOID, VOLATILE, WHEN,
				WHILE };
		boolean[] result;
		int length;
		int min = identifierIndexes[0], max = identifierIndexes[0];

		for (int index : identifierIndexes) {
			if (index < min)
				min = index;
			if (index > max)
				max = index;
		}
		length = max - min + 1;
		result = new boolean[length];
		for (int index : identifierIndexes)
			result[index - min] = true;
		identifierMinIndex = min;
		identifierMaxIndex = max;
		return result;
	}

	/**
	 * Is the token a preprocessor identifier. That would be any C identifier
	 * (type IDENTIFIER) or any keyword.
	 * 
	 * @param token
	 *            any token
	 * @return true iff the token's type is either IDENTIFIER or any of the
	 *         types in the list of C keywords.
	 */
	public static boolean isIdentifier(Token token) {
		int type = token.getType();

		if (type == PreprocessorLexer.IDENTIFIER)
			return true;
		return type >= identifierMinIndex && type <= identifierMaxIndex
				&& identifierPredicate[type - identifierMinIndex];
	}

	public static boolean isPpNumber(Token token) {
		int type = token.getType();

		return type == PreprocessorLexer.INTEGER_CONSTANT
				|| type == PreprocessorLexer.FLOATING_CONSTANT
				|| type == PreprocessorLexer.PP_NUMBER;
	}

	/**
	 * Is the preprocessor token considered a white space token? Spaces, tabs,
	 * newlines, comments are all white space.
	 * 
	 * @param token
	 *            any token defined in the PreprocessorLexer
	 * @return true iff token is a form of white space
	 */
	public static boolean isWhiteSpace(Token token) {
		int type = token.getType();

		return type == PreprocessorLexer.WS || type == PreprocessorLexer.COMMENT
				|| type == PreprocessorLexer.NEWLINE;
	}

	/**
	 * This convenience method transforms a TokenSource by filtering out the
	 * white space tokens. The TokenSource returned is equivalent to the given
	 * TokenSource, except that all white space tokens (spaces, tabs, newlines)
	 * have been removed.
	 * 
	 * @param oldSource
	 *            a token source that might have white space
	 * @return a new token source equivalent to old but with white space tokens
	 *         removed
	 */
	public static TokenSource filterWhiteSpace(final TokenSource oldSource) {
		TokenSource newSource = new TokenSource() {
			@Override
			public String getSourceName() {
				return oldSource.getSourceName();
			}

			@Override
			public Token nextToken() {
				while (true) {
					Token token = oldSource.nextToken();

					if (!isWhiteSpace(token))
						return token;
				}
			}
		};
		return newSource;
	}

	public static Integer convertStringToInt(String text)
			throws PreprocessorExpressionException {
		String stripped, root;
		// String suffix;
		int length = text.length();
		Integer result;

		while (length >= 1) {
			char c = text.charAt(length - 1);

			if (c != 'U' && c != 'u' && c != 'l' && c != 'L')
				break;
			length--;
		}
		stripped = text.substring(0, length);
		// TODO: do anything with suffix?
		// suffix = text.substring(length);
		try {
			if (stripped.startsWith("0")) {
				if (stripped.startsWith("0x") || stripped.startsWith("0X")) {
					// hexadecimal
					root = stripped.substring(2);
					result = Integer.parseInt(root, 16);
				} else {
					// octal
					result = Integer.parseInt(stripped, 8);
				}
			} else {
				// decimal
				result = Integer.valueOf(stripped);
			}
		} catch (NumberFormatException e) {
			throw new PreprocessorExpressionException(
					"Unable to extract integer value from " + text + ":\n" + e);
		}
		return result;
	}

	public static Double convertStringToDouble(String text)
			throws PreprocessorExpressionException {
		// TODO: fix
		return new Double(text);
	}

	/**
	 * If the token has type which is one of the preprocessor keywords (e.g.,
	 * DEFINE, which is the type of the token "define"), it is changed to the
	 * IDENTIFIER type. Otherwise, the token is unchanged.
	 * 
	 * @param token
	 *            any preprocessing token (including whitespace)
	 */
	public static void convertPreprocessorIdentifiers(Token token) {
		int tokenType = token.getType();

		switch (tokenType) {
		case DEFINE:
		case DEFINED:
		case ELIF:
		case ENDIF:
		case ERROR:
		case IFDEF:
		case IFNDEF:
		case INCLUDE:
		case LINE:
		case PRAGMA:
		case UNDEF:
			token.setType(IDENTIFIER);
		default:
		}
	}

	/**
	 * Prints the stream of tokens emanating from a token source. Used mainly
	 * for debugging. Uses the tokens' "toString" method.
	 * 
	 * @param out
	 *            a print stream to which the output is sent
	 * @param source
	 *            any instance of TokenSource
	 * @throws PreprocessorException
	 *             if any exception is thrown while printing a token or getting
	 *             the next token. CommonToken's toString method can throw all
	 *             manner of exceptions
	 */
	public static void printTokenSource(PrintStream out, TokenSource source)
			throws PreprocessorException {
		try {
			CommonToken token;

			do {
				token = (CommonToken) source.nextToken();
				out.println(token.toString());
				out.flush();
			} while (token.getType() != PreprocessorLexer.EOF);
		} catch (RuntimeException e) {
			e.printStackTrace(System.err);
			throw new PreprocessorException(e.toString());
		}
	}

	public static void sourceTokenSource(PrintStream out, TokenSource source)
			throws PreprocessorException {
		try {
			CommonToken token;
			int type;

			while (true) {
				token = (CommonToken) source.nextToken();
				type = token.getType();
				if (type == PreprocessorParser.EOF)
					break;
				if (type == PreprocessorParser.COMMENT)
					out.print(" ");
				else
					out.print(token.getText());
				out.flush();
			}
		} catch (RuntimeException e) {
			e.printStackTrace(out);
			throw new PreprocessorException(e.getMessage());
		}
	}

	public static void source(PrintStream out, File file)
			throws PreprocessorException {
		try {
			ANTLRUtils.source(out, file);
		} catch (IOException e) {
			e.printStackTrace(out);
			throw new PreprocessorException(e.toString());
		}
	}

	public static CharStream newFilteredCharStreamFromFile(File file)
			throws IOException {
		InputStream inputStream = new FileInputStream(file);
		CharStream charStream = new CommonCharacterStream(
				file.getAbsolutePath(), inputStream);
		CharStream filteredStream = new FilteredStream(charStream);

		return filteredStream;
	}

	/**
	 * Creates new filtered character stream from the specified internal
	 * resource. Used to read files that are stored inside the class path
	 * (including inside a jar file).
	 * 
	 * @param name
	 *            a name to assign to the stream; used only for reporting
	 *            errors, referring to the stream, etc.
	 * @param resource
	 *            the actual name of the resource, which is absolute path
	 *            relative to the class path
	 * @return the character stream or <code>null</code> if the resource could
	 *         not be found
	 * @throws IOException
	 *             if something goes wrong reading from the stream
	 */
	public static CharStream newFilteredCharStreamFromResource(String name,
			String resource) throws IOException {
		InputStream inputStream = PreprocessorUtils.class
				.getResourceAsStream(resource);

		if (inputStream == null)
			return null;

		CharStream charStream = new CommonCharacterStream(name, inputStream);
		CharStream filteredStream = new FilteredStream(charStream);

		return filteredStream;
	}

	/**
	 * Find the file with the given name by looking through the directories in
	 * the given list. Go through list from first to last. Returns first
	 * instance found.
	 * 
	 * Note: the filename may itself containing directory structure, e.g.,
	 * "sys/stdio.h".
	 * 
	 * @param paths
	 *            list of directories to search
	 * @param filename
	 *            name of file
	 * @return file named filename, or null if not found
	 */
	public static File findFile(File[] paths, String filename) {
		for (File path : paths) {
			File result = new File(path, filename);

			if (result.isFile())
				return result;
		}
		return null;
	}

	/**
	 * Converts a macro map to an ANTLR character stream. The macro map
	 * specifies macros as key-value pairs, where the key is the name of the
	 * macro (and possible formal parameter list, if the macro is a
	 * function-like macro) and the value is the body. The character stream
	 * return follows the C preprocessor format: a sequence of
	 * newline-terminated lines of the form "#define NAME BODY"
	 * 
	 * @param macroMap
	 *            map from macro names to bodies
	 * @return character stream defining macros in the C preprocessor format
	 */
	public static CharStream macroMapToCharStream(
			Map<String, String> macroMap) {
		StringBuffer sb = new StringBuffer();

		for (Entry<String, String> entry : macroMap.entrySet()) {
			sb.append("#define ");
			sb.append(entry.getKey());
			sb.append(" ");
			sb.append(entry.getValue());
			sb.append(System.lineSeparator());
		}

		int n = sb.length();
		char[] charArray = new char[n];

		sb.getChars(0, n, charArray, 0);
		return new ANTLRStringStream(charArray, n);
	}

}
