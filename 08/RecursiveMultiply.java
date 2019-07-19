public class RecursiveMultiply {
    static int multiply(int a, int b) {
        return mult(Math.min(a, b), Math.max(a, b));
    }
    static int mult(int small, int big) {
        if (small == 0) return 0;
        else if (small == 1) return big;
        else {
            int half = mult(small >> 1, big);
            if ((small & 1) == 0) return half + half;   // if 'small' is even number
            else return half + half + big;              // if 'small' is odd
        }
    }

    public static void main(String[] args) {
        int h = Integer.parseInt(args[0]),
            w = Integer.parseInt(args[1]);
        System.out.println(multiply(h, w));
        System.out.println(h * w);
    }
}