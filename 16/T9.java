import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class T9 {
    private static final char END_OF_WORD = '*';

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
        String digits = args[0];
        TrieNode trieRoot = new TrieNode('\0');
        T9 wordFinder = new T9();
        for (int i = 0; i < args.length; i++) {
            wordFinder.addWord(trieRoot, args[i], 0);
        }
        System.out.println(wordFinder.getAllMatchingWords(digits, trieRoot));
    }

    // O(4^n) time and space complexity
    List<String> getAllMatchingWords(String digits, TrieNode root) {
        if (digits == null)
            return null;

        // convert digits to int[]
        int[] digits_ = new int[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            digits_[i] = digits.charAt(i) - '0';
        }

        List<String> wordList = new ArrayList<>();
        char[] chars = new char[digits.length()];   // valid characters so far
        addAllMatchingWords(wordList, root, digits_, chars, 0);
        return wordList;
    }

    private void addAllMatchingWords(List<String> wordList, TrieNode node, int[] digits, char[] chars, int index) {
        if (node == null)
            return;

        // went through all digits, check if we are at a word terminating node
        if (index == digits.length && node.character == END_OF_WORD) {
            wordList.add(new String(chars));
        } else if (index == digits.length) {    // and not end of word
            return;
        } else {    // keep building the word and looking for it in the trie
            for (char c : getT9Chars(digits[index])) {
                chars[index] = c;
                addAllMatchingWords(wordList, node.getChild(c), digits, chars, index + 1);
            }
        }
    }

    private char[] getT9Chars(int digit) {
        return KEYBOARD[digit];
    }

    private void addWord(TrieNode node, String word, int index) {
        if (node == null || word == null) 
            return;
        if (index == word.length()) {
            node.addChild(END_OF_WORD);
        } else {
            char c = word.charAt(index);
            if (!node.hasChild(c)) node.addChild(c);
            addWord(node.getChild(c), word, index + 1);
        }
    }


    static class TrieNode {
        char character; // redundant?
        Map<Character, TrieNode> children;

        public TrieNode(char c) {
            character = c;
            children = new HashMap<>();
        }

        boolean hasChild(char c) {
            return children.get(c) != null;
        }

        void addChild(char c) {
            children.put(c, new TrieNode(c));
        }

        TrieNode getChild(char c) {
            return children.get(c);
        }
    }
}