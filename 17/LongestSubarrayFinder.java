import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/* CTCI 17.5
 * Given an array of letters and digits, finds the longest subarray that consists of equal
 * number of of letters and digits. */
public class LongestSubarrayFinder {

    // returns the start and end indices of such subarray
    int[] findLongestSubarray(char[] chars) {
        if (chars == null || chars.length == 0)
            return null;
        
        int bestCount = 0;
        int bestStart = 0;
        int bestEnd = 0;
        for (int start = 0; start < chars.length; start++) {
            for (int end = start; end < chars.length; end++) {
                int digitCount = 0;
                int letterCount = 0;
                for (int i = start; i <= end; i++) {
                    if (isDigit(chars[i])) digitCount++;
                    else letterCount++;
                    if (digitCount == letterCount && digitCount > bestCount) {
                        bestCount = digitCount;
                        bestStart = start;
                        bestEnd = end;
                    }
                }
            }
        }
        int[] indices = {bestStart, bestEnd};
        return indices;
    }

    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }


    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);
        char[] chars = new char[length];
        String charSet = "abcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < length; i++) {
            chars[i] = charSet.charAt(ThreadLocalRandom.current().nextInt(0, charSet.length()));
        }
        System.out.println("original array  : " + Arrays.toString(chars));
        LongestSubarrayFinder subarrayFinder = new LongestSubarrayFinder();
        int[] subarrayIndices = subarrayFinder.findLongestSubarray(chars);
        System.out.println("Longest subarray: " 
            + Arrays.toString(Arrays.copyOfRange(chars, subarrayIndices[0], subarrayIndices[1]+1)));
    }
}