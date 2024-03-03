package expression.exceptions;


import expression.*;
import expression.parser.CharSource;
import expression.parser.StringSource;


public class ExpressionParser implements TripleParser {

    @Override
    public CommonExpression parse(String expression) throws BaseParseException {
        return new Parser(new StringSource(expression)).parse();
    }

    private static class Parser extends BaseParser {

        public Parser(CharSource source) {
            super(source);
        }

        public CommonExpression parse() throws BaseParseException {
            CommonExpression expression = parseExpression();
            if (eof()) {
                return expression;
            }
            throw parseError("EOF expected");
        }

        public CommonExpression parseExpression() throws BaseParseException {
            return parseSetClear();
        }

        public CommonExpression parseSetClear() throws BaseParseException {
            CommonExpression setClear = parseAdditiveOperation();
            while (true) {
                if (take('s')) {
                    expect("et");
                    expectEndOfOperation();
                    setClear = new Set(setClear, parseAdditiveOperation());
                } else if (take('c')) {
                    expect("lear");
                    expectEndOfOperation();
                    setClear = new Clear(setClear, parseAdditiveOperation());
                } else {
                    break;
                }
            }
            return setClear;
        }

        public CommonExpression parseAdditiveOperation() throws BaseParseException {
            CommonExpression additiveOperation = parseTerm();
            while (true) {
                if (take('+')) {
                    additiveOperation = new CheckedAdd(additiveOperation, parseTerm());
                } else if (take('-')) {
                    additiveOperation = new CheckedSubtract(additiveOperation, parseTerm());
                } else {
                    break;
                }
            }
            return additiveOperation;
        }

        private Const parseConst() throws BaseParseException {
            final StringBuilder sb = new StringBuilder();
            takeInteger(sb);
            return parseInt(sb);
        }

        private Variable parseVariable() {
            return new Variable(take());
        }

        private Const parseInt(StringBuilder sb) throws BaseParseException {
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (NumberFormatException e) {
                throw parseError("Invalid number " + sb);
            }
        }

        private Const parseNegativeConst() throws BaseParseException {
            final StringBuilder sb = new StringBuilder();
            sb.append('-');
            sb.append(take());
            takeDigits(sb);
            return parseInt(sb);
        }

        private CommonExpression parseFactor() throws BaseParseException {
            skipWhitespace();
            final CommonExpression factor;
            if (take('-')) {
                if (isOneNine()) {
                    factor = parseNegativeConst();
                } else {
                    return new CheckedNegate(parseFactor());
                }
            } else if (take('c')) {
                expect("ount");
                expectEndOfOperation();
                return new Count(parseFactor());
            } else if (take('l')) {
                expect("og10");
                expectEndOfOperation();
                return new CheckedLog10(parseFactor());
            } else if (take('p')) {
                expect("ow10");
                expectEndOfOperation();
                return new CheckedPow10(parseFactor());
            } else if (testVariable()) {
                factor = parseVariable();
            } else if (take('(')) {
                factor = parseExpression();
                expect(')');
            } else if (isDigit()) {
                factor = parseConst();
            } else {
                throw parseError("Expected value");
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

        private CommonExpression parseTerm() throws BaseParseException {
            CommonExpression term = parseFactor();
            while (true) {
                if (take('*')) {
                    term = new CheckedMultiply(term, parseFactor());
                } else if (take('/')) {
                    term = new CheckedDivide(term, parseFactor());
                } else {
                    break;
                }
            }
            return term;
        }

        private boolean isOperation() {
            return isDigit() || between('a', 'z');
        }

        private void expectEndOfOperation() throws BaseParseException {
            if (isOperation()) {
                throw parseError("Expected end of operation");
            }
        }

        private boolean isDigit() {
            return between('0', '9');
        }

        private void takeDigits(final StringBuilder sb) {
            while (isDigit()) {
                sb.append(take());
            }
        }

        private boolean isOneNine() {
            return between('1', '9');
        }

        private void takeInteger(final StringBuilder sb) throws BaseParseException {
            if (take('0')) {
                sb.append('0');
            } else if (isOneNine()) {
                takeDigits(sb);
            } else {
                throw parseError("Invalid number");
            }
        }
    }
}
