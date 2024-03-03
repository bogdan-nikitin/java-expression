package expression;

public class Count extends UnaryOperation {
    public Count(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected int performOperation(int value) {
        return Integer.bitCount(value);
    }

    @Override
    protected double performOperation(double value) {
        throw new UnsupportedOperationException("Bitwise operations not implemented for double");
    }

    @Override
    protected String getOperation() {
        return "count";
    }
}
