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
                    System.err.format("main = '%s', alt = '%s'%n", value.substring(0, mainSize), 
                        value.substring(firstAltIndex, firstAltIndex + altSize));
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

    /* Checks if value.substring(start1, start1 + size) equals value.substring(start2, start2 + size) 
     * without creating the substrings */
    private static boolean equals(String string, int start1, int start2, int size) {
        for (int i = 0; i < size; i++) {
            if (string.charAt(start1 + i) != string.charAt(start2 + i))
                return false;
        }
        return true;
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