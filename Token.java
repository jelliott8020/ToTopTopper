/**
 * Token.java
 *
 * Enum to define token names.
 *
 * All lexers should have these three tokens available. INIT means Initial value
 * (to allow entry into loops) ERR means Error (illegal symbol, etc.) EOI means
 * End of Input (end of string, end of file, etc.)
 *
 * Token numeric Values don't matter since all client code should use
 * Token.EOP_TOK, etc. names instead. Just don't use the same value for two
 * tokens!
 *
 * @author Dr. Fenwick & Josh Elliott
 * @version Spring 2019
 */

public enum Token {

	INIT_TOK, ERR_TOK

	// Custom tokens can go here.
	, TO_TOK, TOP_TOK, NAME_TOK

	, EOI_TOK;
}