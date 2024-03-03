package expression.exceptions;

import expression.CommonExpression;
import expression.UnaryOperation;


public class CheckedLog10 extends UnaryOperation {
    public CheckedLog10(CommonExpression operand) {
        super(operand);
    }
    @Override
    protected int performOperation(int value) throws ValueException {
        if (value <= 0) {
            throw new Log10UndefinedException("log10 is undefined for values <= 0");
        }
        int result = 0;
        while (value >= 10) {
            result++;
            value /= 10;
        }
        return result;
    }

    @Override
    protected double performOperation(double value) {
        throw new UnsupportedOperationException("log10 doesn't support double");
    }

    @Override
    protected String getOperation() {
        return "log10";
    }
}
