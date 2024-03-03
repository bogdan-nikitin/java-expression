package expression.exceptions;

import expression.CommonExpression;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** :NOTE: сообщения об ошибке ни о чем мне не говорят
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ExpressionParser expressionParser = new ExpressionParser();
        System.out.println("Send EOF to quit");
        while (true) {
            try {
                System.out.println("Input expression:");
                CommonExpression expression = expressionParser.parse(in.nextLine());
                System.out.println("Input x, y, z:");
                int x;
                int y;
                int z;
                while (true) {
                    try {
                        x = in.nextInt();
                        y = in.nextInt();
                        z = in.nextInt();
                        in.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Error. You should input 3 integers. Try again");
                    }
                }
                System.out.println("Result: " + expression.evaluate(x, y, z));
            } catch (BaseParseException e) {
                System.out.println("Unable to parse expression: " + e.getMessage());
            } catch (EvaluationException e) {
                System.out.println("Unable to evaluate expression: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Abort");
            }
        }

    }
}
