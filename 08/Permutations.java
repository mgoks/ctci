import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Permutations {
    static List<String> permute(String s) {
        if (s == null) return null;

        Map<Character, Integer> charCount = new HashMap<>();
        int nChars = 0; // total number of characters in s
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            nChars++;
        }
        return permute(charCount, nChars);
    }

    // nChars: number of characters in charCount
    private static List<String> permute(Map<Character,Integer> charCount, int nChars) {
        List<String> perms = new ArrayList<>();
        if (nChars < 0) return null;
        else if (nChars == 0) perms.add("");
        else {
            // for each character c of input string, pick c as the first character of permutation
            for (char c : charCount.keySet()) {
                int count = charCount.get(c);
                if (count > 0) {
                    charCount.replace(c, count - 1); // decrease the multiplicity of c
                    for (String permutation : permute(charCount, nChars - 1))
                        perms.add(c + permutation);
                    charCount.replace(c, count); // add 1 back to c's multiplicity
                }
            }
        }
        return perms;
    }

    public static void main(String[] args) {
        System.out.println(permute(args.length > 0? args[0] : null));
    }
}