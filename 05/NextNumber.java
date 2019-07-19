public class NextNumber {

    static int getNext(int n) {
        int c = n, c0 = 0, c1 = 0;
        while ((c & 1) == 0 && c != 0) {
            c0++;
            c >>= 1;
        }
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }
        if (c0 + c1 == 31 || c0 + c1 == 0)
            return -1;
        int p = c0 + c1;
        n |= (1 << p);
        n &= ~((1 << p) - 1);
        return n | ((1 << (c1 - 1)) - 1);
    }

    static int getPrev(int n) {
        int c = n, c0 = 0, c1 = 0;
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }
        if (c == 0)
            return -1;
        while ((c & 1) == 0 && c != 0) {
            c0++;
            c >>= 1;
        }
        int p = c0 + c1;
        n -= 1 << p;
        n |= (1 << p) - 1;
        n &= ~0 << (c0 - 1);
        return n;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int prev = getPrev(n), next = getNext(n);
        System.out.println(String.format("input: %32s", Integer.toBinaryString(n)));
        System.out.println(String.format("prev : %32s (%d)", Integer.toBinaryString(prev), prev));
        System.out.println(String.format("next : %32s (%d)", Integer.toBinaryString(next), next));
    }

}