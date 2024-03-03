package expression.exceptions;

public class ParseException extends BaseParseException { // :NOTE: для разных сиутаций разные исключения
    private final int pos;
    public ParseException(String message, int pos) {
        super("Error on position " + (pos + 1) + ": " + message);
        this.pos = pos;

    }
    public int getPos() {
        return pos;
    }
}
