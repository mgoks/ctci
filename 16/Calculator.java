import java.lang.StringBuilder;

public class Calculator {
    static final char STAR = '*';
    static final char SLASH = '/';
    static final char PLUS = '+';
    static final char MINUS = '-';

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate(args[0]));
    }

    public double calculate(String expression) {
        if (expression == null)
            return Double.NaN;
        LinkedListNode tokens = tokenize(expression);
        doOperations(tokens, STAR, SLASH);    // do multiplications and divisions first
        doOperations(tokens, PLUS, MINUS);    // now do additions and subtractions
        return tokens.value instanceof Double? (Double) tokens.value : Double.NaN;
    }

    private void doOperations(LinkedListNode node, char sign1, char sign2) {
        while (node != null && node.next != null && node.next.next != null) {
            Object token = node.next.value;
            if (token instanceof Character) {
                char operator = (Character) token;
                if (operator == sign1 || operator == sign2) {
                    double term1 = (Double) node.value;
                    double term2 = (Double) node.next.next.value;
                    double result = 0.0;
                    switch (operator) {
                        case PLUS: result = term1 + term2;
                            break;
                        case MINUS: result = term1 - term2;
                            break;
                        case STAR: result = term1 * term2;
                            break;
                        case SLASH: result = term1 / term2;
                            break;
                        default: System.err.println("Undefined operator: " + operator);
                            return;
                    }
                    node.value = result;
                    node.next = node.next.next.next;
                    continue;
                } 
            }
            node = node.next; 
        }
    }

    private LinkedListNode tokenize(String expression) {
        LinkedListNode node = new LinkedListNode();
        LinkedListNode head = node;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                node.value = Double.parseDouble(sb.toString());
                node.next = new LinkedListNode(c);
                node.next.next = new LinkedListNode();
                sb = new StringBuilder();
                node = node.next.next;
            }
        }
        node.value = Double.parseDouble(sb.toString());
        return head;
    }


    private class LinkedListNode {
        Object value;
        LinkedListNode next = null;

        LinkedListNode() {
            value = null;
        }

        LinkedListNode(Object v) {
            value = v;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(value) + " ");
            if (next == null)
                return sb.toString();
            sb.append(next.toString());
            return sb.toString();
        }
    }
}