package expression.exceptions;

public class EvaluationException extends RuntimeException {
    public EvaluationException(String message) {
        super(message);
    }
    public EvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvaluationException() {
        super();
    }
    public EvaluationException(Throwable cause) {
        super(cause);
    }
}
