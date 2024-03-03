package expression;

public class Clear extends SetClear {

    public Clear(CommonExpression leftOperand, CommonExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected int performOperation(int left, int right) {
        return left - ((1 << right) & left);
    }

    @Override
    protected String getOperation() {
        return "clear";
    }
}
