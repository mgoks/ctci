import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class T9Optimal {
    private static final char[] DIGITS = {
        '2', '2', '2',        // a, b, c
        '3', '3', '3',        // d, e, f
        '4', '4', '4',        // ...
        '5', '5', '5',
        '6', '6', '6',
        '7', '7', '7', '7',
        '8', '8', '8',
        '9', '9', '9', '9'
    };

    private final Map<String, List<String>> matchingWords = new HashMap<>();

    public static void main(String[] args) {
        String digits = args[0];
        List<String> validWords = Arrays.asList(args);
        validWords.remove(digits);

        T9Optimal t9 = new T9Optimal();
        /* takes O(nm) for caching and constant time for each 
         * call getMatchingWords */
        t9.cacheWords(validWords);
        System.out.println(t9.getMatchingWords(digits));
    }

    // O(n*m) time in the length of List and the length of String
    void cacheWords(List<String> validWords) {
        if (validWords == null)
            return;

        /* for each valid word, compute its digit form and put it in the map */
        for (String word : validWords) {
            String digits = t9Digitize(word);
            if (!matchingWords.containsKey(digits))
                matchingWords.put(digits, new ArrayList<>());
            matchingWords.get(digits).add(word);
        }
    }

    List<String> getMatchingWords(String digits) {
        return matchingWords.get(digits);
    }

    private String t9Digitize(String word) {
        if (word == null)
            return null;

        char[] digits = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            digits[i] = t9Digitize(word.charAt(i));
        }
        return new String(digits);
    }

    private char t9Digitize(char c) {
        return DIGITS[Character.toLowerCase(c) - 'a'];
    }
}