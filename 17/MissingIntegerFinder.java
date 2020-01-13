import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MissingIntegerFinder {
    int findMissingInteger(BitInteger[] a) {
        if (a == null)
            return -1;
        return findMissingInteger(Arrays.asList(a), 0, 0);
    }

    /* O(n) because in the first call it will go over n objects, 
     * in the second call n/2, in the third n/4, etc. */
    private int findMissingInteger(List<BitInteger> a, int missingInt, int j) {
        if (a.isEmpty()) {
            return missingInt;
        }
        ArrayList<BitInteger> zeroes = new ArrayList<>();
        ArrayList<BitInteger> ones   = new ArrayList<>();
        for (BitInteger bitInteger : a) {
            if (bitInteger.fetchBit(j) == 0)
                zeroes.add(bitInteger);
            else 
                ones.add(bitInteger);
        }
        if (zeroes.size() <= ones.size())   // j^th bit of missing integer is 0
            return findMissingInteger(zeroes, missingInt, j+1); // eliminate all 1s
        else 
            return findMissingInteger(ones, missingInt | (1<<j), j+1);
    }


    private void shuffle(BitInteger[] a) {
        if (a == null)
            return;
        for (int i = 0; i < a.length; i++) {
            int j = ThreadLocalRandom.current().nextInt(0, i+1);
            BitInteger temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void main(String[] args) {
        MissingIntegerFinder finder = new MissingIntegerFinder();
        int n = Integer.parseInt(args[0]);
        int missingInt = ThreadLocalRandom.current().nextInt(0, n+1);
        System.out.println("Missing integer is " + missingInt);
        BitInteger[] a = new BitInteger[n];
        int offset = 0;
        for (int i = 0; i <= n; i++) {
            if (i == missingInt) {
                offset = 1;
                continue;
            }
            a[i - offset] = new BitInteger(i);
        }
        finder.shuffle(a);
        System.out.println("findMissingInteger returns " + finder.findMissingInteger(a));
    }


    static class BitInteger {
        private int value;

        BitInteger(int v) {
            value = v;
        }

        // sig ... significance
        int fetchBit(int sig) {
            return (value >> sig) & 1;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}