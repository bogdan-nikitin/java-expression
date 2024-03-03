package expression;

public abstract class AdditiveOperation extends BinaryOperation {
    private static final int PRIORITY = 100;

    public AdditiveOperation(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    protected boolean canGiveLeftOperand() {
        return true;
    }
}
