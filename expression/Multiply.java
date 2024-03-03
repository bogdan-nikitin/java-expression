package expression;

public class Multiply extends MultiplicativeOperation {

    public Multiply(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(int left, int right) {
        return left * right;
    }

    @Override
    protected double performOperation(double left, double right) {
        return left * right;
    }

    @Override
    protected String getOperation() {
        return "*";
    }

    @Override
    protected boolean canGiveLeftOperand() {
        return true;
    }

    @Override
    protected boolean canTakeLeftOperandFromRightOperand() {
        return true;
    }

}
