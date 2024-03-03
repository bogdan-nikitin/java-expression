package expression.exceptions;

import expression.Add;
import expression.CommonExpression;


public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(int left, int right) {
        int result = super.performOperation(left, right);
        if (Integer.signum(left) == Integer.signum(right) && Integer.signum(result) != Integer.signum(left)) {
            throw new OverflowException("Overflow in addition");
        }
        return result;
    }
}
