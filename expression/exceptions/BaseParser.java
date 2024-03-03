package expression.exceptions;

import expression.parser.CharSource;


public class BaseParser {
    private static final char END = '\0';
    private final CharSource source;
    private char ch;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean takeWhitespace() {
        if (Character.isWhitespace(ch)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    private String formatCurrentChar() {
        return ch == END ? "EOF" : "'" + ch + "'";
    }

    protected void expect(final char expected) throws BaseParseException {
        if (!take(expected)) {
            throw parseError("Expected '" + expected + "', found " + formatCurrentChar());
        }
    }

    protected void expect(final String value) throws BaseParseException {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected BaseParseException parseError(final String message) {
        return new ParseException(message, source.getPos() - 1);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
