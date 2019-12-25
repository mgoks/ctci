import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class RandomSetGenerator {
    public static void main(String[] args) {
        RandomSetGenerator generator = new RandomSetGenerator();
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = i;
        System.out.println("recursive: " + Arrays.toString(generator.generateRandomSet(m, array)));
        System.out.println("iterative: " + Arrays.toString(generator.generateRandomSetIterative(m, array)));
    }

    int[] generateRandomSet(int m, int[] array) {
        if (array == null || array.length < m || m < 0)
            return null;
        return generateRandomSet(m, array, array.length);
    }

    /* Generates a random subset of size m using the first n
     * elements in array

     * m    subset size
     * n    array size   */
    private int[] generateRandomSet(int m, int[] array, int n) {
        if (n < m)
            return null;
        if (n == m) // base case
            return subarray(array, 0, m);
    
        /* generate a random subset of size m using the first n-1 
         * elements in array, i.e. alls element but the last one */
        int[] subset = generateRandomSet(m, array, n-1);

        /* Now we need to decide if the last element of array, i.e.
         * array[n-1] will be in the subset or not. Let's pick a random
         * index form array and assign array[n-1] to subset[index] if 
         * index is within the subset; else, we'll keep the current
         * subset[index].  */
        int index = ThreadLocalRandom.current().nextInt(0, n);
        if (index < m)
            subset[index] = array[n-1];
        return subset;
    }

    int[] generateRandomSetIterative(int m, int[] array) {
        if (array == null || array.length < m || m < 0)
            return null;
        int[] subset = subarray(array, 0, m);
        for (int i = m; i < array.length; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, i+1);
            if (index < m)
                subset[index] = array[i];
        }
        return subset;
    }

    private int[] subarray(int[] array, int start, int end) {
        int[] sub = new int[end - start];
        for (int i = start; i < end; i++)
            sub[i - start] = array[i];
        return sub;
    }
}