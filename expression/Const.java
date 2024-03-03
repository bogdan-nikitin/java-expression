package expression;

import java.util.Objects;

public class Const implements SingleExpression {
    private final Number value;
    public Const(int value) {
        this.value = value;
    }

    public Const(double value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public double evaluate(double x) {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Const that && that.value.equals(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
