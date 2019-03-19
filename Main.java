public class Main {
	public static void main(String[] args) {
		System.out.println("Processing args using IfTableLexer...");
		for (String s : args) {
			runLexer(new IfTableLexer(s));
		}
		System.out.println();
		System.out.println("Processing args using IfCodeLexer...");
		for (String s : args) {
			runLexer(new IfCodeLexer(s));
		}
	}

	private static void runLexer(Lexer lexer) {
		Token token = Token.INIT_TOK;

		while (token != Token.EOI_TOK && token != Token.ERR_TOK) {
			token = lexer.nextToken();
			if (token != Token.EOI_TOK)
				System.out.println("Found a " + token.name() + " token");
		}
	}
}
