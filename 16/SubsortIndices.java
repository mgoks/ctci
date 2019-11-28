import java.util.Arrays;

public class SubsortIndices {
    public static void main(String[] args) {
        int[] a = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        int[] indices = getSubsortIndices(a);
        System.out.println(Arrays.toString(indices));
    }

    // O(n) time O(1) space
    static int[] getSubsortIndices(int[] array) {
        if (array == null || array.length == 0)
            return null;

        // first find the sorted subarrays from both ends
        int i_leftMax = getSortedSubarrayIndex(array);

        // if array is fully sorted return {0,0}
        if (i_leftMax == array.length - 1) {
            int[] indices = {0, 0};
            return indices;
        }

        int i_rightMin = getSortedSubarrayIndexFromRight(array);

        /* For all elements, we want left < middle < right, but this
         * condition will never be met. The middle section is, by definition,
         * the elements that are out of order. It is always 
         * left.end > middle.start and middle.end > right.start. 
         * Let min = min(middle and right side) and max = max(min and left side)
         * and find min and max. */
        int i_min = i_leftMax;
        int i_max = i_leftMax;
        for (int i = i_leftMax; i <= i_rightMin; i++) {
            if (array[i] < array[i_min]) 
                i_min = i;
            if (array[i] > array[i_max])
                i_max = i;
        }

        int m = findSortedIndex(array, array[i_min]);
        int n = findSortedIndexFromRight(array, array[i_max]);
        int[] indices = {m, n};
        return indices;
    }

    private static int getSortedSubarrayIndex(int[] a) {
        for (int i = 0; i < a.length-1; i++) {
            if (a[i] > a[i+1])
                return i;
        }
        return a.length - 1;
    }

    private static int getSortedSubarrayIndexFromRight(int[] a) {
        for (int i = a.length-2; i >= 0; i--) {
            if (a[i] > a[i+1])
                return i+1;
        }
        return 0;
    }

    private static int findSortedIndex(int[] a, int val) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > val)
                return i;
        }
        return a.length - 1;
    }

    private static int findSortedIndexFromRight(int[] a, int val) {
        for (int i = a.length-1; i >= 0; i--) {
            if (a[i] < val)
                return i;
        }
        return 0;
    }
}