/**
 * StringLexer.java
 *
 * Concrete implementation of Lexer abstraction. This works on an character
 * stream input that is a simple String object. (Other concrete implemenations
 * could work on char stream from files, etc.)
 *
 * @author Dr. Fenwick & Josh Elliott
 * @version Spring 2019
 */

public abstract class StringLexer implements Lexer {
    private String charStream;
    private int streamIndex;

    public StringLexer(String input) {
        if (input == null)
            throw new IllegalArgumentException("String input can not be null.");

        charStream = new String(input); // make sure we get a copy
        streamIndex = 0;
    }

    // Scanner-like methods to safely read chars from String
    protected boolean hasNextChar() {
        if (streamIndex >= charStream.length())
            return false;
        return true;
    }

    protected char nextChar() {
        return charStream.charAt(streamIndex++);
    }
}
