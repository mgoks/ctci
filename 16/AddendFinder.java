import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// 16.24 Pairs with Sum
public class AddendFinder {
    public static void main(String[] args) {
        int sum = Integer.parseInt(args[0]);
        int[] array = new int[args.length-1];
        for (int i = 1; i < args.length; i++) {
            array[i-1] = Integer.parseInt(args[i]);
        }
        AddendFinder addendFinder = new AddendFinder();
        System.out.println("array: " + Arrays.toString(array));
        System.out.println("sum  = " + sum);
        System.out.println("pairs: " + addendFinder.findAddends(array, sum));
    }

    // O(n) time and space (no sorting needed)
    // does not double-count an element, i.e. an integer can be part of one pair only
    ArrayList<Pair> findAddends(int[] array, int sum) {
        if (array == null || array.length < 2)
            return null;
        ArrayList<Pair> pairs = new ArrayList<>();
        HashMap<Integer, Integer> counts = countElements(array);
        for (int addend1 : array) {
            int addend2 = sum - addend1;
            if (addend1 == addend2 && counts.get(addend1) > 1 ||
                // make sure both addends are not assigned to a pair yet
                addend1 != addend2 && counts.getOrDefault(addend1, 0) > 0 && counts.getOrDefault(addend2, 0) > 0) {
                pairs.add(new Pair(addend1, addend2));
                decrementCount(counts, addend1);
                decrementCount(counts, addend2);
            }
        }
        return pairs;
    }

    HashMap<Integer, Integer> countElements(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : array) {
            if (!map.containsKey(i)) map.put(i, 1);
            else map.replace(i, map.get(i) + 1);
        }
        return map;
    }

    void decrementCount(HashMap<Integer, Integer> counts, int element) {
        counts.replace(element, counts.get(element) - 1);
    }


    class Pair {
        int lesser;
        int greater;

        public Pair(int i, int j) {
            lesser = i < j? i : j;
            greater = lesser == i? j : i;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", lesser, greater);
        }
    }
}