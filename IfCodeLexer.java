/**
 * IfCodeLexer.java
 *
 * Concrete implementation of StringLexer for an IF token assignment.
 *
 * Here is where we code up the FSM!!
 *
 * @author Dr. Fenwick
 * @version Spring 2019
 */

public class IfCodeLexer extends StringLexer {

	public IfCodeLexer(String input) {
		super(input);
	}

	// required by Lexer interface
	public Token nextToken() {
		Token tok = Token.INIT_TOK;
		if (!hasNextChar())
			return Token.EOI_TOK;

		int state = 0;
		while (hasNextChar()) {
			char c = nextChar();

			switch (state) {
			case 0:
				if (c == 'i')
					state = 1;
				else
					state = 3;
				break;
			case 1:
				if (c == 'f')
					state = 2;
				else
					state = 3;
				break;
			case 2:
				// shouldn't have anymore input?
				state = 3;
				break;
			case 3:
				// shouldn't have anymore input?
				state = 3;
				break;
			default:
				// shouldn't have anymore input?
				state = 3;
				break;
			}

			// Deal with "final" states
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
