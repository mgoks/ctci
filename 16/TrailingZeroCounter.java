public class TrailingZeroCounter {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.format("Number of trailing zeroes in %d! is %d%n", 
                            n, computeTrailingZeroesOfFactorial(n));

    }

    static int computeTrailingZeroesOfFactorial(int n) {
        if (n < 0) return -1;

        int fives = 0;
        // For each factor of the factorial, compute the number of times it can be divided by 5.
        for (int i = 5; i <= n; i++) {
            for (int j = i; j % 5 == 0; j /= 5) {
                fives++;
            }
        }
        return fives;
    }

    // Result is the same as above but has only one loop.
    static int countFactZeroes(int n) {
        if (n < 0) return -1;

        int count = 0;
        for (int i = 5; n/i > 0; i *= 5) {
            count += n/i;
        }
        return count;
    }
}