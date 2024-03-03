package expression;

public class Set extends SetClear {

    public Set(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(int left, int right) {
        return left | (1 << right);
    }

    @Override
    protected String getOperation() {
        return "set";
    }
}
