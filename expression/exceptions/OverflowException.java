package expression.exceptions;

public class OverflowException extends EvaluationException {
    public OverflowException() {
        super("Overflow during evaluation");
    }

    public OverflowException(String message) {
        super(message);
    }
}
