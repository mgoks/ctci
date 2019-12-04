import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class T9 {
    private static final char[][] KEYBOARD = {
        {},
        {},
        {'a', 'b', 'c'},
        {'d', 'e', 'f'},
        {'g', 'h', 'i'},
        {'j', 'k', 'l'},
        {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v'},
        {'w', 'x', 'y', 'z'}
    };

    public static void main(String[] args) {
        Set<String> validWords = new HashSet<>();
        for (int i = 1; i < args.length; i++) {
            validWords.add(args[i]);
        }
        T9 t9 = new T9();
        System.out.println(t9.getWords(args[0], validWords));
    }

    // O(4^n) time, O(log(4^n)) space complexity
    List<String> getWords(String digits, Set<String> validWords) {
        if (digits == null)
            return null;

        List<String> matchingWords = new ArrayList<>();
        char[] chars = new char[digits.length()];
        int index = 0; // current index
        addAllMatchingWords(matchingWords, digits, chars, index, validWords);
        return matchingWords;
    }

    void addAllMatchingWords(List<String> wordList, String digits, char[] chars, int index, Set<String> validWords) {
        if (index == digits.length()) {
            String candidate = new String(chars);
            if (validWords.contains(candidate)) wordList.add(candidate);
            return;
        }
        int digit = digits.charAt(index) - '0';
        for (char c : KEYBOARD[digit]) {
            chars[index] = c;
            addAllMatchingWords(wordList, digits, chars, index + 1, validWords);
        }
    }
}