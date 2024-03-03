package expression;

public class Negate extends UnaryOperation {
    public Negate(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected int performOperation(int value) {
        return -value;
    }

    @Override
    protected double performOperation(double value) {
        return -value;
    }

    @Override
    protected String getOperation() {
        return "-";
    }
}
