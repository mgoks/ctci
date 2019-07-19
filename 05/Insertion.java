import java.util.concurrent.ThreadLocalRandom;

public class Insertion {
    
    // my solution
    static int insert(int n, int m, int i, int j) {
        int mask = (-1 << j+1) | ((1 << i) - 1);    // -1 is a sequence of only 1s in binary
        return n & mask | (m << i);
    }

    // CtCI solution
    static int updateBits(int n, int m, int i, int j) {
        int allOnes     = ~0;
        int left        = allOnes << (j+1);
        int right       = ((1 << i) -1);
        int mask        = left | right;
        int n_cleared   = n & mask;
        int m_shifted   = m << i;
        return n_cleared | m_shifted;
    }

    static int getBit(int N, int p) {
        return (1 << p & N) > 0? 1 : 0;
    }

    static String bin(int integer) {
        String bin = "";
        for (int i = 31; i >= 0; i--)
            bin += getBit(integer, i);
        return bin;
    }

    public static void main(String[] args) {
        int nTestCases = Integer.parseInt(args[0]);
        while (nTestCases-- >= 0) {
            int n = ThreadLocalRandom.current().nextInt();
            int i = ThreadLocalRandom.current().nextInt(0, 32);
            int j = ThreadLocalRandom.current().nextInt(i, 32);
            int m = ThreadLocalRandom.current().nextInt(0, (int) Math.pow(2, j-i+1));
            if (insert(n, m, i, j) != updateBits(n, m, i, j)) {
                System.out.print(String.format("test failed\n N = %d, M = %d, i = %d, j = %d", n, m, i, j));
            }
        }
        System.out.println("All test passed!");
    }
}