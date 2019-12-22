public class Adder {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        Adder adder = new Adder();
        System.out.println(adder.addNonNegative(a, b));

    }

    /* O(1) time since while loop will execute 31 times at most 
     * assuming input is non-negative and carry can be non-zero
     * for a limited number of times which makes space complexity
     * upper bounded by constanct factor as well. */
    int addNonNegative(int a, int b) {
        int res = 0;
        int mask = 1; 
        int carry = 0;
        while (a > 0 || b > 0) {
            int a_ = a & 1;   // least significant bit of a
            int b_ = b & 1;
            if ((a_ & b_) == 1)
                carry |= (mask << 1);
            if ((a_ ^ b_) == 1)
                res |= mask;
            a >>= 1;
            b >>= 1;
            mask <<= 1;
        }
        if (carry == 0)
            return res;
        return addNonNegative(carry, res);
    }
}