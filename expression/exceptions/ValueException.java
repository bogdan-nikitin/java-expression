package expression.exceptions;

public class ValueException extends EvaluationException {
    public ValueException(String message) {
        super(message);
    }
    public ValueException() {
        super();
    }
    public ValueException(String message, Throwable cause) {
        super(message, cause);
    }
    public ValueException(Throwable cause) {
        super(cause);
    }
}
