package expression.exceptions;

public class BaseParseException extends Exception { // :NOTE: как минимум абстрактный

    public BaseParseException() {
        super();
    }

    public BaseParseException(String message) {
        super(message);
    }

    public BaseParseException(String message, Throwable cause) {
        super(message);
    }

    public BaseParseException(Throwable cause) {
        super(cause);
    }
}
