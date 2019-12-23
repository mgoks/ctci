public class Adder {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        Adder adder = new Adder();
        System.out.println(adder.add(a, b));
        System.out.println(adder.addIterative(a, b));

    }

    /* O(1) time and space the method will not recurse more
     * than 32 times. */
    int add(int a, int b) {
        if (b == 0)
            return a;
        int sum = a ^ b;    // sum without carrying
        int carry = (a & b) << 1;
        return add(sum, carry);
    }

    /* O(1) time and space since the loop will never execute
     * more than 32 times. */
    int addIterative(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;    // sum without carrying
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }
}