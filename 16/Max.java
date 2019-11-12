public class Max {
    public static void main(String[] args) {
        System.out.println(max(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }

    // naive way
    static int getMax(int a, int b) {
        int msb = getMsb(a - b);
        return a * (1^msb) + b * msb;
    }

    
    // What if a - b > Integer.MAX_VALUE?
    static int max(int a, int b) {
        int msb_a = getMsb(a);
        int msb_b = getMsb(b);

        // if a and b have different MSBs, this value will be 1, else 0
        int diff_bits = msb_a ^ msb_b;

        // When calculating MSB of a-b, we need to account for the overflow.
        // If a and b have different MSBs, MSB of a-b will equal MSB of a.
        int msb_diff = diff_bits * msb_a + (1^diff_bits) * getMsb(a-b);

        return a * (1^msb_diff) + b * msb_diff; 
    }

    // Gets the most significant bit of i
    static int getMsb(int i) {
        /* When getting a bit it is a good idea to reduce the target bit to the least significant bit,
           rather than creating a bit mask which targets that bit directly. */
        return (i >>> (Integer.SIZE - 1)) & 1;
    }
}