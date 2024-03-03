package expression.exceptions;

import expression.CommonExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(int left, int right) {
        int result = super.performOperation(left, right);
        if ((Integer.signum(left) == -Integer.signum(right) && Integer.signum(result) != Integer.signum(left)) ||
                (right == Integer.MIN_VALUE && left == 0)) {
            throw new OverflowException("Overflow in subtraction");
        }
        return result;
    }
}
