import java.util.List;
import java.util.ArrayList;

public class PermsWithoutDups {
    // O(n^2 * n!) time 
    // static List<String> getPerms(String str) {
    //     List<String> perms = new ArrayList<>();
    //     if (str == null) return perms;
    //     else {  // base case
    //         perms.add("");
    //         return getPerms(str, 0, perms);
    //     }
    // }

    // static List<String> getPerms(String str, int i, List<String> prevPerms) {
    //     if (i < 0 || i >= str.length()) return prevPerms;
    //     List<String> currentPerms = new ArrayList<>();
    //     for (String s : prevPerms) 
    //         currentPerms.addAll(makePerms(s, str.charAt(i)));
    //     return getPerms(str, i+1, currentPerms);
    // }

    // static List<String> makePerms(String str, char c) {
    //     List<String> permutations = new ArrayList<>();
    //     for (int i = 0; i <= str.length(); i++) 
    //         permutations.add(str.substring(0,i) + c + str.substring(i));
    //     return permutations;
    // }

    static List<String> permutationsOf(String s) {
        List<String> perms = new ArrayList<>();
        if (s == null) System.err.println("ERROR: input string is null");
        else if (s.isEmpty()) perms.add("");
        else {
            int i = 0;
            for (char c : s.toCharArray()) {
                String t = s.substring(0, i) + s.substring(i+1);    // s without c
                for (String partialPerm : permutationsOf(t))
                    perms.add(c + partialPerm);
                i++;
            }
        }
        return perms;
    }

    public static void main(String[] args) {
        System.out.println(permutationsOf(args.length > 0? args[0] : null));
    }
}