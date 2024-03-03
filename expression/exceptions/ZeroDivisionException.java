package expression.exceptions;

public class ZeroDivisionException extends EvaluationException {
    public ZeroDivisionException() {
        super("Division by zero");
    }

    public ZeroDivisionException(String message) {
        super(message);
    }
}
