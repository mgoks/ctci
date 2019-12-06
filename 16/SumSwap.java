import java.util.Set;
import java.util.HashSet;

public class SumSwap {
    public static void main(String[] args) {
        int[] array1 = new int[Integer.parseInt(args[0])];
        int[] array2 = new int[args.length - array1.length - 1];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = Integer.parseInt(args[i+1]);
        }
        for (int i = 0; i < array2.length; i++) {
            array2[i] = Integer.parseInt(args[i + array1.length + 1]);
        }
        SumSwap sumSwap = new SumSwap();
        System.out.println(Arrays.toString(sumSwap.findIntegers(array1, array2)));
    }

    // O(n + m) time and space in the lengths of the arrays
    int[] findIntegers(int[] array1, int[] array2) {
        if (array1 == null || array2 == null)
            return null;
        
        int sumDiff = sum(array2) - sum(array1);
        if (Math.abs(sumDiff) % 2 == 1)
            return null;    // not gonna work

        Set<Integer> set1 = makeSet(array1);
        for (int int2 : array2) {
            int int1 = int2 - sumDiff/2;
            if (set1.contains(int1)) {
                int[] pair = {int1, int2};
                return pair;
            }
        }
        return null;
    }

    private int sum(int[] array) {
        int sum = 0;
        for (int i : array) sum += i;
        return sum;
    }

    private Set<Integer> makeSet(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int i : array) set.add(i);
        return set;
    }
}