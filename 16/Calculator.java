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
        LinkedListNode head = tokenize(expression);
        LinkedListNode node = head;

        // do multiplications and divisions first
        while (node != null && node.next != null && node.next.next != null) {
            Object token = node.next.value;
            if (token instanceof Character) {
                char operator = (Character) token;
                if (operator == STAR || operator == SLASH) {
                    double term1 = 0.0;
                    double term2 = 0.0;
                    if (node.value instanceof Double)
                        term1 = (Double) node.value;
                    if (node.next.next.value instanceof Double)
                        term2 = (Double) node.next.next.value;
                    double result = operator == STAR? term1 * term2 : term1 / term2;
                    node.value = result;
                    node.next = node.next.next.next;
                }
            } else {
                node = node.next;
            }
        }

        // now do additions and subtractions
        while (node != null && node.next != null && node.next.next != null) {
            Object token = node.next.value;
            if (token instanceof Character) {
                char operator = (Character) token;
                if (operator == PLUS || operator == MINUS) {
                    double term1 = 0.0;
                    double term2 = 0.0;
                    if (node.value instanceof Double)
                        term1 = (Double) node.value;
                    if (node.next.next.value instanceof Double)
                        term2 = (Double) node.next.next.value;
                    double result = operator == PLUS? term1 + term2 : term1 - term2;
                    node.value = result;
                    node.next = node.next.next.next;
                }
            } else {
                node = node.next;
            }
        }

        return node.value instanceof Double? (Double) node.value : Double.NaN;
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
    }
}