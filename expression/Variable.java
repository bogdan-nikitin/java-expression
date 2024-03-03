package expression;

import java.util.Objects;

public class Variable implements SingleExpression {
    private final String name;
    public Variable(String name) {
        this.name = name;
    }

    public Variable(char name) {
        this(String.valueOf(name));
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new AssertionError("Cannot evaluate variable " + name);
        };
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable that) {
            return Objects.equals(that.name, name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
