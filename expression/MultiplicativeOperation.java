package expression;


public abstract class MultiplicativeOperation extends BinaryOperation {
    private final static int PRIORITY = 200;

    public MultiplicativeOperation(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }
}
