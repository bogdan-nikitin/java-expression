package expression.exceptions;

import expression.CommonExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression operand) {
        super(operand);
    }

    @Override
    protected int performOperation(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in negation");
        }
        return super.performOperation(value);
    }
}
