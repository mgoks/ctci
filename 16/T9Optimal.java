import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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

    // digit sequence to valid words mappings
    private final Map<String, List<String>> matchingWords = new HashMap<>();

    public static void main(String[] args) {
        String digits = args[0];
        args[0] = null;

        /* takes O(nm) time for caching and O(m) for each call to getMatchingWords 
         * in the length of the digit sequence beacuse of the hash table 
         * look up (need to convert the String) 
         * O(nm) space because of the hash table*/
        T9Optimal t9 = new T9Optimal();
        t9.cacheWords(args);
        System.out.println(t9.getMatchingWords(digits));
    }

    // O(n*m) time in the length of List and the length of String
    void cacheWords(String[] validWords) {
        if (validWords == null)
            return;

        /* for each valid word, compute its digit form and put it in the map */
        for (String word : validWords) {
            if (word == null)
                continue;
            String digits = t9Digitize(word);
            if (!matchingWords.containsKey(digits))
                matchingWords.put(digits, new ArrayList<>());
            matchingWords.get(digits).add(word);
        }
    }

    // O(m) time
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