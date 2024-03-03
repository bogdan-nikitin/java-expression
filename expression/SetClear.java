package expression;

public abstract class SetClear extends BinaryOperation {
    private final static int PRIORITY = 0;
    public SetClear(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    protected double performOperation(double left, double right) {
        throw new UnsupportedOperationException("Bitwise operations not implemented for double");
    }

    @Override
    protected boolean canGiveLeftOperand() {
        return false;
    }

    @Override
    protected boolean canTakeLeftOperandFromRightOperand() {
        return false;
    }
}
