public class TripleStep {
    
    // plain recursion in O(3^n) time and space
    static int countWays(int n) {
        if (n < 0) return 0;
        else if (n == 0) return 1;
        else return countWays(n-1) + countWays(n-2) + countWays(n-3);
    }

    // top-down dynamic programing (memoization) in O(n) time and space
    static int memoize(int n) {
        return memoize(n, new int[n+1]);
    }
    static int memoize(int i, int[] memo) {
        if (i < 0) return 0;
        if (memo[i] == 0) {
            if (i == 0) memo[i] = 1;
            else memo[i] = memoize(i-1, memo) + memoize(i-2, memo) + memoize(i-3, memo);
        }
        return memo[i];
    }

    // bottom-up dynamic programming in O(n) time O(1) space
    static int tripleStep(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        int a = 0, b = 0, c = 1;
        for (int i = 1; i < n; i++) {
            int temp = c;
            c = a + b + c;
            a = b; 
            b = temp;
        }
        return a + b + c;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 37; i++) {
            if (memoize(i) != tripleStep(i)) {
                System.out.printf("Test failed: %d\n", i);
            }
        }
        System.out.println("All tests passed!");
    }
}