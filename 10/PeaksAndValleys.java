import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class PeaksAndValleys {
    
    // suboptimal O(nlogn)
    static void sortAndSwap(int[] a) {
        if (a == null) return;
        Arrays.sort(a);
        for (int i = 1; i < a.length; i += 2) {
            swap(a, i-1, i);
        }
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) return;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // optimal O(n)
    static void swapWithoutSort(int[] array) {
        if (array == null) return;

        for (int i = 1; i < array.length; i += 2) {
            swap(array, i, highIndex(array, i));
        }
    }

    /* Returns the index of the maximum of array[i-1], array[i], and array[i+1] */
    private static int highIndex(int[] array, int i) {
        int a = array[i-1],
            b = array[i],
            c = i+1 < array.length? array[i+1] : Integer.MIN_VALUE,
            max = Math.max(a, Math.max(b, c));

        if (max == array[i-1]) return i-1;
        else if (max == array[i]) return i;
        else return i+1;
    }
    
    // for testing only
    private static void printGraph(int[] a) {
        for (int max = max(a); max > 0; max--) {
            System.out.print(" ");
            for (int j : a) {
                if (j >= max) System.out.print("#");
                else System.out.print(" ");
                System.out.print("  ");
            }
            System.out.println("");
        }
        System.out.print(" ");
        for (int j : a) System.out.print("_  ");
        System.out.println("");
    }

    private static int max(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i : a) if (i > max) max = i;
        return max;
    }

    public static void main(String[] args) {
        int[] a;
        if (args.length == 2) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            a = new int[Integer.parseInt(args[0])];
            int max = Integer.parseInt(args[1]);
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(max);
            }
        } else {
            a = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                a[i] = Integer.parseInt(args[i]);
            }
        }
        System.out.println("original: " + Arrays.toString(a));

        int[] aCopy = Arrays.copyOf(a, a.length);

        swapWithoutSort(a);
        System.out.println("your answer: " + Arrays.toString(a) + "\n");
        printGraph(a);
        
        sortValleyPeak(aCopy);
        System.out.println("correct sol: " + Arrays.toString(aCopy) + "\n");
        printGraph(aCopy);
    }
}