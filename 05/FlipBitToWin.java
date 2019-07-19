import java.util.concurrent.ThreadLocalRandom;

public class FlipBitToWin {

    static int N_BITS = Integer.BYTES * 8; // number of bits used to represent an integer;
    
    static int flipBitToWin(int n) {
        if (n == -1) // -1 has no 0s in binary representation;
            return N_BITS; 
        int maxLength = 1;
        int i = 0, j = 0;
        for (int mask = 1; mask != 0; mask <<= 1) {
            if ((n & mask) == 0) {
                if (j > maxLength) maxLength = j;
                j = i + 1;
                i = 0;
            } else {
                i++;
                j++;
            }
        }
        return Math.max(maxLength, j);
    }

    // ctci's solution
    static int flipBit(int a) {
        if (~a == 0) return Integer.BYTES * 8;
        int cur = 0;
        int prev = 0;
        int max = 1;
        while (a != 0) {
            if ((a & 1) == 1) 
                cur++;
            else if ((a & 1) == 0) {
                prev = (a & 2) == 0? 0 : cur;
                cur = 0;
            }
            max = Math.max(prev + cur + 1, max);
            a >>>= 1;
        }
        return max;
    }

    public static void main(String[] args) {
        int n_tests = Integer.parseInt(args[0]);
        while (n_tests-- > 0) {
            int n = ThreadLocalRandom.current().nextInt();
            int myAnswer = flipBitToWin(n), ctciAnswer = flipBit(n);
            if (myAnswer != ctciAnswer) {
                System.out.println("input: " + Integer.toBinaryString(n));
                System.out.println("your answer: " + myAnswer);
                System.out.println("ctci answer: " + ctciAnswer);
                return;
            }
        }
        System.out.println("Passed all tests!");
    }

}