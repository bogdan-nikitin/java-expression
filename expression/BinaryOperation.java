package expression;

import java.util.Objects;

public abstract class BinaryOperation implements CommonExpression {
    private final CommonExpression leftOperand;
    private final CommonExpression rightOperand;

    public BinaryOperation(CommonExpression leftOperand, CommonExpression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public abstract int getPriority();

    protected abstract int performOperation(int left, int right);

    protected abstract double performOperation(double left, double right);

    protected abstract String getOperation();

    @Override
    public final int evaluate(int x) {
        return performOperation(leftOperand.evaluate(x), rightOperand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return performOperation(leftOperand.evaluate(x, y, z), rightOperand.evaluate(x, y, z));
    }

    @Override
    public double evaluate(double x) {
        return performOperation(leftOperand.evaluate(x), rightOperand.evaluate(x));
    }

    @Override
    public void buildString(StringBuilder sb) {
        sb.append('(');
        leftOperand.buildString(sb);
        sb.append(' ');
        sb.append(getOperation());
        sb.append(' ');
        rightOperand.buildString(sb);
        sb.append(')');
    }

    private void buildOperandMiniString(StringBuilder sb, CommonExpression operand, boolean frameWithParentheses) {
        if (frameWithParentheses) {
            sb.append('(');
            operand.buildMiniString(sb);
            sb.append(')');
        } else {
            operand.buildMiniString(sb);
        }
    }

    private boolean needParenthesesForRight(Object rightOperand) {
        return rightOperand instanceof BinaryOperation that
               && !(canTakeLeftOperandFromRightOperand() && that.canGiveLeftOperand() &&
                 getPriority() == that.getPriority())
               && that.getPriority() <= getPriority();
    }

    protected abstract boolean canGiveLeftOperand();

    protected abstract boolean canTakeLeftOperandFromRightOperand();

    private boolean needParenthesesForLeft(CommonExpression leftOperand) {
        return leftOperand instanceof BinaryOperation that && that.getPriority() < getPriority();
    }

    @Override
    public void buildMiniString(StringBuilder sb) {
        buildOperandMiniString(sb, leftOperand, needParenthesesForLeft(leftOperand));
        sb.append(' ').append(getOperation()).append(' ');
        buildOperandMiniString(sb, rightOperand, needParenthesesForRight(rightOperand));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(sb);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BinaryOperation that && getClass() == that.getClass() &&
                Objects.equals(leftOperand, that.leftOperand) &&
                Objects.equals(rightOperand, that.rightOperand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftOperand, rightOperand, this.getClass());
    }
}
