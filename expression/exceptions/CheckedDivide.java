package expression.exceptions;

import expression.CommonExpression;
import expression.Divide;

public class CheckedDivide extends Divide {

    public CheckedDivide(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(int left, int right) {
        if (right == 0) {
            throw new ZeroDivisionException();
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException("Overflow in division");
        }
        return super.performOperation(left, right);
    }
}
