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
            String main = value.substring(0, mainSize);         
            int remainingLength = size - mainSize * mainCharCount;       
            // If the number of characters left for each b doesn't have any decimals
            // e.g. b cannot have length 3.14
            if (altCharCount == 0 || remainingLength % altCharCount == 0) {
                int altSize = altCharCount == 0? 0 : remainingLength / altCharCount;
                // start index of the first alt string in value
                int altStartIndex = pattern.indexOf(altChar) * mainSize;
                String alt = altCharCount == 0? "" : value.substring(altStartIndex, altStartIndex + altSize);
                String cand = buildFromPattern(pattern, main, alt);
                if (cand.equals(value))
                    return true;
            } 
        }
        return false;
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