package expression.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveParser {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ExpressionParser parser = new ExpressionParser();
        while (true) {
            try {
                String input = reader.readLine();
                if (input == null) {
                    break;
                }
                System.out.println(parser.parse(input));
            } catch (IOException e) {
                return;
            }

        }
    }
}
