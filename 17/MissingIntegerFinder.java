import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class MissingIntegerFinder {
    public static void main(String[] args) {
        MissingIntegerFinder finder = new MissingIntegerFinder();
        int n = ThreadLocalRandom.current().nextInt(0, 1000000);
        int missingInt = ThreadLocalRandom.current().nextInt(0, n+1);
        System.out.println("Missing integer is " + missingInt);
        int[] a = new int[n];
        int offset = 0;
        for (int i = 0; i <= n; i++) {
            if (i == missingInt) {
                offset = 1;
                continue;
            }
            a[i - offset] = i;
        }
        finder.shuffle(a);
        // System.out.println("A = " + Arrays.toString(a));
        System.out.println("findMissingInteger returns " + finder.findMissingInteger(a));
    }

    // all a[i] is non-negative
    int findMissingInteger(int[] a) {
        if (a == null)
            return -1;
        int n = a.length;
        int a_xor = 0;  // all a[i] XORed
        int n_xor = 0;  // all integers from 0 to n XORed
        for (int i = 0; i <= n; i++)
            n_xor ^= i;
        for (int j = 0; j < Integer.SIZE; j++) {
            int bit = 0;
            for (int i : a)
                bit ^= fetchBit(j, i);  // can't access a[i] directly according to problem description
            a_xor = a_xor | (bit << j); // set jth bit of a_xor to bit
        }
        return a_xor ^ n_xor;
    }

    // sig ... significance
    private int fetchBit(int sig, int integer) {
        // System.out.format("fetchBit(%d, %s) = %d%n", sig, Integer.toBinaryString(integer), (integer>>sig)&1);
        return (integer >> sig) & 1;
    }

    private void shuffle(int[] a) {
        if (a == null)
            return;
        for (int i = 0; i < a.length; i++) {
            int j = ThreadLocalRandom.current().nextInt(0, i+1);
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}