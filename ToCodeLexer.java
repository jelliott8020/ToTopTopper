/**
 * IfCodeLexer.java
 *
 * Concrete implementation of StringLexer for the ToTopTopper assignment
 *
 * Here is where we code up the FSM!!
 *
 * @author Dr. Fenwick & Josh Elliott
 * @version Spring 2019
 */

public class ToCodeLexer extends StringLexer {

	public ToCodeLexer(String input) {
		super(input);
	}

	// required by Lexer interface
	public Token nextToken() {
		Token tok = Token.INIT_TOK;
		if (!hasNextChar())
			return Token.EOI_TOK;

		int state = 1;
		while (hasNextChar()) {
			char c = nextChar();

			switch (state) {
			case 1:
				if (c == 't')
					state = 2;
				else
					state = 5;
				break;
			case 2:
				if (c == 'o')
					state = 3;
				else
					state = 5;
				break;
			case 3:
				if (c == 'p')
					state = 4;
				else
					state = 5;
				break;
			case 4:
				state = 5;
				break;
			case 5:
				state = 5;
				break;
			default:
				state = 5;
				break;
			}

			// Deal with "final" states
			switch (state) {
			case 3:
				tok = Token.TO_TOK;
				break;
			case 4:
				tok = Token.TOP_TOK;
				break;
			case 5:
				return Token.ERR_TOK;
			}
		}

		return tok;
	}
}
