import java.lang.ArithmeticException;

public class SubtractMultiplyDivide {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.format("%d - %d = %d (%d)%n", a, b, subtract(a, b), a-b);
        System.out.format("%d * %d = %d (%d)%n", a, b, multiply(a, b), a*b);
        System.out.format("%d / %d = %d (%d)%n", a, b, divide(a, b), a/b);
    }
    static int subtract(int a, int b) {
        return a + negate(b);
    }

    static int multiply(int a, int b) {
        int pos_b = b;  // positive b
        if (b < 0) pos_b = negate(b);
        
        int product = 0;
        for (int i = 0; i < pos_b; i++) {
            product += a;
        }
        return b < 0? negate(product) : product;
    }

    static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        int pos_a = a;  
        int pos_b = b;
        if (a < 0) pos_a = negate(a);
        if (b < 0) pos_b = negate(b);

        int quotient = 0;
        int product = 0;    // = quotient * pos_b
        while (product + pos_b <= pos_a) {
            quotient++;
            product += pos_b;
        }
        return sign(a) != sign(b)? negate(quotient) : quotient;
    }

    private static int sign(int a) {
        return a < 0? -1 : 1;
    }

    // O(a)
    private static int negateSlow(int a) {
        if (a == 0) return 0;
        int unit = a > 0? -1 : 1;
        int neg_a = 0;
        while (a != 0) {
            neg_a += unit;
            a += unit;
        }
        return neg_a;
    }

    /* O(log^2 i)
    In the while loop, the algorithm perform O(log i) iterations and 
    the longest round takes O(log i) work. Therefore, as an upper bound,
    this algorithm takes O(log^2 i) time. In this case, the upper bound 
    happens to be the true time. */
    private static int negate(int i) {
        int neg_i = 0;
        final int direction = i > 0? -1 : 1;
        int delta = direction;
        
        while (i != 0) {
            boolean diffSigns = (i > 0) != (i + delta > 0);
            // if delta is too large, reset it
            if (diffSigns && (i + delta != 0)) {
                delta = direction;
            }
            neg_i += delta;
            i += delta;
            delta += delta;
        }
        return neg_i;
    }
}