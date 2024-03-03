package expression.exceptions;

import expression.CommonExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(int left, int right) {
        int result = super.performOperation(left, right);
        if (right != 0 && left != result / right) {
            throw new OverflowException();
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException();
        }
        if (right == Integer.MIN_VALUE && left == -1) {
            throw new OverflowException("Overflow in multiplication");
        }
        return result;
    }
}
