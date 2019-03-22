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

	public boolean isLower(char c) {
		if (c <= 'z' && c >= 'a') {
			return true;
		}
		return false;
	}

	public boolean isOther(char c) {
		if ((c <= 'Z' && c >= 'A') || (c <= 9 && c >= 0) || c == '-' || c == '_') {
			return true;
		}
		return false;
	}

	public boolean isBad(char c) {
		if ((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A') || (c <= 9 && c >= 0) || c == '-' || c == '_') {
			return false;
		}
		return true;
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
				if (c == 't')
					state = 1;
				else if (isLower(c))
					state = 4;
				else if (isOther(c) || isBad(c))
					state = 5;
				break;
			case 1:
				if (c == 'o')
					state = 2;
				else if (isLower(c) || isOther(c))
					state = 4;
				else if (isBad(c))
					state = 5;
				break;
			case 2:
				if (c == 'p')
					state = 3;
				else if (isLower(c) || isOther(c))
					state = 4;
				else if (isBad(c)) {
					state = 5;
				}
				break;
			case 3:
				if (isLower(c) || isOther(c)) {
					state = 4;
				} else {
					state = 5;
				}
				break;
			case 4:
				if (isLower(c) || isOther(c)) {
					state = 4;
				} else {
					state = 5;
				}
				break;
			default:
				state = 5;
				break;
			}

			// Deal with "final" states
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
