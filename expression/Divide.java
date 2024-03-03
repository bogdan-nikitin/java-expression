package expression;

public class Divide extends MultiplicativeOperation {
    public Divide(final CommonExpression leftOperand, final CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(final int left, final int right) {
        return left / right;
    }

    @Override
    protected double performOperation(final double left, final double right) {
        return left / right;
    }

    @Override
    protected String getOperation() {
        return "/";
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
