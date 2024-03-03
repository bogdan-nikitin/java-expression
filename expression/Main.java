package expression;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Specify x as an argument");
            return;
        }
        int x;
        try {
            x = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argument must be integer");
            return;
        }
        Expression expression = new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")),
                new Multiply(new Const(2), new Variable("x"))), new Const(1));
        System.out.println(expression.evaluate(x));
    }
}
