package expression.parser;

import expression.*;


public class ExpressionParser implements TripleParser {

    @Override
    public CommonExpression parse(String expression) {
        return new Parser(new StringSource(expression)).parse();
    }

    private static class Parser extends BaseParser {

        public Parser(CharSource source) {
            super(source);
        }

        public CommonExpression parse() {
            CommonExpression expression = parseSetClear();
            if (eof()) {
                return expression;
            }
            throw error("End of expression expected");
        }

        public CommonExpression parseSetClear() {
            CommonExpression setClear = parseAdditiveOperation();
            while (true) {
                if (take('s')) {
                    expect("et");
                    setClear = new Set(setClear, parseAdditiveOperation());
                } else if (take('c')) {
                    expect("lear");
                    setClear = new Clear(setClear, parseAdditiveOperation());
                } else {
                    break;
                }
            }
            return setClear;
        }
        public CommonExpression parseAdditiveOperation() {
            CommonExpression additiveOperation = parseTerm();
            while (true) {
                if (take('+')) {
                    additiveOperation = new Add(additiveOperation, parseTerm());
                } else if (take('-')) {
                    additiveOperation = new Subtract(additiveOperation, parseTerm());
                } else {
                    break;
                }
            }
            return additiveOperation;
        }

        private Const parseConst() {
            final StringBuilder sb = new StringBuilder();
            takeInteger(sb);
            return parseInt(sb);
        }

        private Variable parseVariable() {
            return new Variable(take());
        }

        private Const parseInt(StringBuilder sb) {
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw error("Invalid number " + sb);
            }
        }

        private Const parseNegativeConst() {
            final StringBuilder sb = new StringBuilder();
            sb.append('-');
            sb.append(take());
            takeDigits(sb);
            return parseInt(sb);
        }

        private CommonExpression parseFactor() {
            skipWhitespace();
            final CommonExpression factor;
            if (take('-')) {
                if (isOneNine()) {
                    factor = parseNegativeConst();
                } else {
                    return new Negate(parseFactor());
                }
            } else if (take('c')) {
                expect("ount");
                return new Count(parseFactor());
            } else if (testVariable()) {
                factor = parseVariable();
            } else if (take('(')) {
                factor = parseSetClear();
                expect(')');
            } else {
                factor = parseConst();
            }
            skipWhitespace();
            return factor;
        }

        private boolean testVariable() {
            return test('x') || test('y') || test('z');
        }

        private void skipWhitespace() {
            while (takeWhitespace()) {
                // skip
            }
        }

        private CommonExpression parseTerm() {
            CommonExpression term = parseFactor();
            while (true) {
                if (take('*')) {
                    term = new Multiply(term, parseFactor());
                } else if (take('/')) {
                    term = new Divide(term, parseFactor());
                } else {
                    break;
                }
            }
            return term;
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        private boolean isOneNine() {
            return between('1', '9');
        }

        private void takeInteger(final StringBuilder sb) {
            if (take('0')) {
                sb.append('0');
            } else if (isOneNine()) {
                takeDigits(sb);
            } else {
                throw error("Invalid number");
            }
        }
    }
}
