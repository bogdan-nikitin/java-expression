package expression.exceptions;

import expression.CommonExpression;
import expression.UnaryOperation;

public class CheckedPow10 extends UnaryOperation {
    protected CheckedPow10(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected int performOperation(int value) throws OverflowException {
        if (value < 0) {
            throw new Pow10UndefinedException("pow10 is undefined for negative numbers");
        }
        return binPow(value);
    }

    private int binPow(int value) throws OverflowException {
        if (value == 0) {
            return 1;
        }
        if (value % 2 == 1) {
            int prev = binPow(value - 1);
            int result = prev * 10;
            if (result / 10 != prev) {
                throw new OverflowException("Overflow in pow10");
            }
            return result;
        }
        int halfPow = binPow(value / 2);
        int result = halfPow * halfPow;
        if (result / halfPow != halfPow) {
            throw new OverflowException("Overflow in pow10");
        }
        return result;
    }

    @Override
    protected double performOperation(double value) {
        throw new UnsupportedOperationException("pow10 doesn't support double");
    }

    @Override
    protected String getOperation() {
        return "pow10";
    }
}
