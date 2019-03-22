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

	int[][] lexTable = { /* t o p ? */
			/* state 1 */ { 2, 5, 5, 5 }, /* state 2 */ { 5, 3, 5, 5 }, /* state 3 */ { 5, 5, 4, 5 }, // Error "trap"
																										// state
			/* state 4 */ { 5, 5, 5, 5 }, /* state 5 */ { 5, 5, 5, 5 }, };

	public ToTableLexer(String input) {
		super(input);
	}

	private int columnMap(char c) {
		switch (c) {
		case 't':
			return 2;
		case 'o':
			return 3;
		case 'p':
			return 4;
		default:
			return 5;
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
			case 3:
				tok = Token.TO_TOK;
				break;
			case 4:
				tok = Token.TOP_TOK;
			case 5:
				return Token.NAME_TOK;
			}
		}
		return tok;
	}
}
