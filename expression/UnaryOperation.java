package expression;

import java.util.Objects;

public abstract class UnaryOperation implements SingleExpression {
    private final CommonExpression operand;
    protected UnaryOperation(CommonExpression operand) {
        this.operand = operand;
    }
    abstract protected int performOperation(int value);
    abstract protected double performOperation(double value);

    @Override
    public double evaluate(double x) {
        return performOperation(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return performOperation(operand.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return performOperation(operand.evaluate(x));
    }

    @Override
    public boolean equals(Object obj) {
        return operand instanceof UnaryOperation that && obj.getClass() == operand.getClass() &&
                operand.equals(that.operand);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(sb);
        return sb.toString();
    }

    abstract protected String getOperation();

    @Override
    public void buildString(StringBuilder sb) {
        sb.append(getOperation()).append("(");
        operand.buildString(sb);
        sb.append(")");
    }

    @Override
    public void buildMiniString(StringBuilder sb) {
        sb.append(getOperation());
        if (operand instanceof SingleExpression) {
            sb.append(' ');
            operand.buildMiniString(sb);
        } else {
            sb.append('(');
            operand.buildMiniString(sb);
            sb.append(')');
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, getClass());
    }
}
