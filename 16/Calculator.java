import java.util.ArrayDeque;

public class Calculator {
    /* O(n) time and space */
    public int calculate(String s) {
        ArrayDeque<Integer> numberStack = new ArrayDeque<>();
        char operator = '+';
        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') 
                continue;
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else {    // c is an operator
                applyOperator(operator, numberStack, number);
                number = 0;
                operator = c;
            }
        }
        // need to this one last time otherwise last operator in s would not be applied
        applyOperator(operator, numberStack, number);
        
        int result = 0;
        for (int i : numberStack)
            result += i;
        return result;
    }
    
    /* If operator is plus or minus pushes (-)number onto the stack; else, pops the top
     * of the stack, applies operator, and pushes the result back.    
     */
    private void applyOperator(char operator, ArrayDeque<Integer> numberStack, int number) {
        if (operator == '+' || operator == '-') {
            numberStack.push((operator == '+'? 1 : -1) * number);
        } else {
            int firstNumber = numberStack.pop();
            int result = operator == '*'? firstNumber * number : firstNumber / number;
            numberStack.push(result);
        }
    }
}