import java.lang.StringBuilder;

public class PatternMatcher {
    public static void main(String[] args) {
        System.out.println(doesMatch(args[0], args[1]));
    }

    // O(n^4) time
    static boolean doesMatch(String pattern, String value) {
        if (pattern.isEmpty()) 
            return value.isEmpty();

        int size = value.length();

        // Let main denote the first character in pattern 
        // and alt, the other one
        for (int mainSize = 0; mainSize < size; mainSize++) {
            String main = value.substring(0, mainSize);
            for (int altStart = mainSize; altStart <= size; altStart++) {
                for (int altEnd = altStart; altEnd <= size; altEnd++) {
                    String alt = value.substring(altStart, altEnd);
                    String cand = buildFromPattern(pattern, main, alt);
                    if (cand.equals(value))
                        return true;
                }
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
}