/**
 * IfTableLexer.java
 *
 * Concrete implementation of StringLexer for the ToTopTopper assignment.
 *
 * Here is where we code up the FSM!!
 *
 * @author Dr. Fenwick & Josh Elliott
 * @version Spring 2019
 */

public class ToTableLexer extends StringLexer {

	int[][] lexTable = { /* t o p a-z A-Z0-9_- err */
			/* state 0 */ { 1, 4, 4, 4, 5, 5 }, /* state 1 */ { 4, 2, 4, 4, 4, 5 }, /* state 2 */ { 4, 4, 3, 4, 4, 5 },
			/* state 3 */ { 4, 4, 4, 4, 4, 5 }, /* state 4 */ { 4, 4, 4, 4, 4, 5 },
			/* state 5 */ { 5, 5, 5, 5, 5, 5 } };

	public ToTableLexer(String input) {
		super(input);
	}

	private int columnMap(char c) {
		// Prioritize to, top
		// If find t, but space after, go to 5
		int x = 0;

		if (c == 't') {
			x = 0;
		} else if (c == 'o') {
			x = 1;
		} else if (c == 'p') {
			x = 2;
		} else if (c <= 'z' && c >= 'a') {
			x = 3;
		} else if ((c <= 'Z' && c >= 'A') || (c <= 9 && c >= 0) || c == '-' || c == '_') {
			x = 4;
		} else {
			x = 5;
		}

		switch (x) {
		// t
		case 0:
			return x;
		// o
		case 1:
			return x;
		// p
		case 2:
			return x;
		// lower but not t o p
		case 3:
			return x;
		// A-Z 0-9 _ -
		case 4:
			return x;
		default:
			return x;
		}
	}

	// required by Lexer interface
	public Token nextToken() {
		Token tok = Token.INIT_TOK;
		if (!hasNextChar())
			return Token.EOI_TOK;

		int state = 0;
		while (hasNextChar()) {
			char c = nextChar();
			state = lexTable[state][columnMap(c)];

			// Did we just enter a "final" state? Might need to do something
			switch (state) {
			case 2:
				tok = Token.TO_TOK;
				break;
			case 3:
				tok = Token.TOP_TOK;
				break;
			case 4:
				tok = Token.NAME_TOK;
				break;
			case 5:
				return Token.ERR_TOK;
			}
		}
		return tok;
	}
}
