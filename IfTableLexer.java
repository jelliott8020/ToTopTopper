/**
 * IfTableLexer.java
 *
 * Concrete implementation of StringLexer for an IF token assignment.
 *
 * Here is where we code up the FSM!!
 *
 * @author Dr. Fenwick
 * @version Spring 2019
 */

public class IfTableLexer extends StringLexer {

	int[][] lexTable = { /* i f ? */
			/* state 0 */ { 1, 3, 3 }, /* state 1 */ { 3, 2, 3 }, /* state 2 */ { 3, 3, 3 }, // Error "trap" state
			/* state 3 */ { 3, 3, 3 }, };

	public IfTableLexer(String input) {
		super(input);
	}

	private int columnMap(char c) {
		switch (c) {
		case 'i':
			return 0;
		case 'f':
			return 1;
		default:
			return 2;
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
				tok = Token.IF_TOK;
				break;
			case 3:
				return Token.ERR_TOK;
			}
		}
		return tok;
	}
}
