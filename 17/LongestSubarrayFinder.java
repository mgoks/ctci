import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;

/* CTCI 17.5
 * Given an array of letters and digits, finds the longest subarray that consists of equal
 * number of of letters and digits. */
public class LongestSubarrayFinder {

    /* returns the longest subarray with equal number of digits and letters 
     * O(n) time and space */
    char[] findLongestSubarray(char[] array) {
        if (array == null)
            return null;
        int[] diffs = getDiffs(array);

        // find the indices of equals diff values furthest apart from each other
        int maxLength = 0;  // max subarray length
        int bestStart = 0;  // start index of longest such subarray
        HashMap<Integer, Integer> indices = new HashMap<>();    // diff to left-most index mappings
        indices.put(0, -1);
        for (int end = 0; end < diffs.length; end++) {
            int diff = diffs[end];
            if (indices.containsKey(diff)) {
                int firstSeenIndex = indices.get(diff);
                int start = firstSeenIndex + 1;  // subarray starts right after firstSeenIndex
                int length = end - start + 1;
                if (length > maxLength) {
                    maxLength = length;
                    bestStart = start;
                }
            } else {
                indices.put(diff, end);
            }
        }
        return Arrays.copyOfRange(array, bestStart, bestStart + maxLength);
    }

    // characters that are not digits or letters are omitted
    private int[] getDiffs(char[] array) {
        int[] table = new int[array.length];
        int diff = 0;
        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i]))
                diff++;
            else if (Character.isLetter(array[i]))
                diff--;
            table[i] = diff;
        }
        return table;
    }


    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);
        char[] chars = new char[length];
        String charSet = "abcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < length; i++) 
            chars[i] = charSet.charAt(ThreadLocalRandom.current().nextInt(0, charSet.length()));
        System.out.println("original array  : " + Arrays.toString(chars));
        LongestSubarrayFinder subarrayFinder = new LongestSubarrayFinder();
        char[] sub = subarrayFinder.findLongestSubarray(chars);
        System.out.println("Longest subarray: " + Arrays.toString(sub));
    }
}