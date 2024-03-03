package expression;


public class Add extends AdditiveOperation {
    public Add(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperation() {
        return "+";
    }

    @Override
    protected boolean canTakeLeftOperandFromRightOperand() {
        return true;
    }

    @Override
    protected int performOperation(int left, int right) {
        return left + right;
    }

    @Override
    protected double performOperation(double left, double right) {
        return left + right;
    }

}
