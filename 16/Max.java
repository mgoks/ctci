public class Max {
    public static void main(String[] args) {
        System.out.println(getMax(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }

    static int getMax(int a, int b) {
        int msb = getMsb(a - b);
        return a * (1^msb) + b * msb;
    }

    // Gets the most significant bit of i
    static int getMsb(int i) {
        /* When getting a bit it is a good idea to reduce the target bit to the least significant bit,
           rather than creating a bit mask which targets that bit directly. */
        return (i >>> (Integer.BYTES * 8 - 1)) & 1;
    }

    // todo: What if a - b > Integer.MAX_VALUE?
}