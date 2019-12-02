import java.lang.StringBuilder;

public class PatternMatcher {
    public static void main(String[] args) {
        System.out.println(doesMatch(args[0], args[1]));
    }

    // O(n^2) time
    static boolean doesMatch(String pattern, String value) {
        if (pattern.isEmpty()) 
            return value.isEmpty();

        int size = value.length();                              
        char mainChar = pattern.charAt(0);
        char altChar = mainChar == 'a'? 'b' : 'a';
        int mainCharCount = countChar(mainChar, pattern);         
        int altCharCount = pattern.length() - mainCharCount;
        int maxMainSize = size / mainCharCount;

        for (int mainSize = 0; mainSize <= maxMainSize; mainSize++) {   
            int remainingLength = size - mainSize * mainCharCount;
            if (altCharCount == 0 || remainingLength % altCharCount == 0) {
                int altSize = altCharCount == 0? 0 : remainingLength / altCharCount;
                int firstAltIndex = pattern.indexOf(altChar) * mainSize;
                if (matches(pattern, value, mainSize, altSize, firstAltIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Iterates through pattern starting at character c at index 1. If c is the main character, i.e. the first
     * character of pattern, it compares that substring of value to main; else, compares it to alt without 
     * actually creating the substrings.
     */
    private static boolean matches(String pattern, String value, int mainSize, int altSize, int firstAltIndex) {
        int valueIndex = mainSize;
        for (int i = 1; i < pattern.length(); i++) {
            int size = pattern.charAt(i) == pattern.charAt(0)? mainSize : altSize;
            int start1 = pattern.charAt(i) == pattern.charAt(0)? 0 : firstAltIndex;
            if (!equals(value, start1, valueIndex, size)) 
                return false;
            
            valueIndex += size;
        }
        return true;
    }

    /* Checks if value.substring(i, i + size) equals value.substring(j, j + size) without creating the substrings */
    private static boolean equals(String value, int start1, int start2, int size) {
        for (int i = 0; i < size; i++) {
            if (value.charAt(start1 + i) != value.charAt(start2 + i))
                return false;
        }
        return true;
    }

    private static String buildFromPattern(String pattern, String main, String alt) {
        StringBuilder s = new StringBuilder();
        char first = pattern.charAt(0);
        for (char c : pattern.toCharArray()) {
            if (c == first) s.append(main);
            else s.append(alt);
        }
        return s.toString();
    }

    private static int countChar(char c, String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == c)
                count++;
        }
        return count;
    }
}