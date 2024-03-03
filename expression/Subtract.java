package expression;

public class Subtract extends AdditiveOperation {
    public Subtract(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(int left, int right) {
        return left - right;
    }

    @Override
    protected double performOperation(double left, double right) {
        return left - right;
    }

    @Override
    protected String getOperation() {
        return "-";
    }

    @Override
    protected boolean canTakeLeftOperandFromRightOperand() {
        return false;
    }
}
