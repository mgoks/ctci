// 16.17 Contiguous sequence

public class MaxSubarraySum {
    public static void main(String[] args) {
        int[] a = new int[args.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        System.out.println(getMaxSubarraySum(a));
    }

    // O(n) time, O(1) space
    static int getMaxSubarraySum(int[] array) {
        int maxSum = Integer.MIN_VALUE;

        if (array == null || array.length == 0)
            return maxSum;

        int sum = 0;
        int maxInt = Integer.MIN_VALUE;
        boolean allIntegersAreNegative = true;

        /* Observe that a sequence with a sum < 0 can never
         * start or end a subarray, but can be in the middle
         * of one.                                        */
        for (int i : array) {
            if (i > 0) allIntegersAreNegative = false;
            maxInt = Math.max(maxInt, i);

            sum += i;
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) sum = 0;  // end sequence
        }

        return allIntegersAreNegative? maxInt : maxSum;
    }
}