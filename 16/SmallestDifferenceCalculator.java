import java.util.Arrays;

public class SmallestDifferenceCalculator {
    public static void main(String[] args) {
        int aLength = Integer.parseInt(args[0]);
        int[] a = new int[aLength];
        int[] b = new int[args.length - aLength - 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(args[i+1]);
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = Integer.parseInt(args[i + a.length + 1]);
        }
        System.out.println("array 1: " + Arrays.toString(a));
        System.out.println("array 2: " + Arrays.toString(b));
        System.out.format("Smallest difference is %d%n", calculateSmallestDifference(a, b));
    }

    static int calculateSmallestDifference(int[] a, int[] b) {
        int diff = Integer.MAX_VALUE;
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0, j = 0; i < a.length && j < b.length;) {
            int d = a[i] - b[j];
            if (d < 0) i++;
            else {
                diff = Math.min(diff, d);
                j++;
            }
        }
        return diff;
    }
}