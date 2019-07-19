import java.io.*;
import java.util.Scanner;

public class MissingInt {
    static int findMissingInt(String fileName) throws FileNotFoundException {
        int rangeSize = (1 << 18),
            arraySize = Integer.MAX_VALUE / rangeSize + 1;
        int[] blocks = new int[arraySize];

        Scanner scan = new Scanner(new FileReader(fileName));
        while (scan.hasNextInt()) blocks[scan.nextInt() / rangeSize]++;
        scan.close();

        int nonfullBlock = 0;
        for (int block = 0; block < blocks.length; block++) {
            if (blocks[block] < rangeSize) {
                nonfullBlock = block;
                break;
            }
        }

        boolean[] vector = new boolean[rangeSize];
        int start = nonfullBlock * rangeSize,
            end   = start + rangeSize;
        scan = new Scanner(new FileReader(fileName));
        while (scan.hasNextInt()) {
            int i = scan.nextInt();
            if (start <= i && i < end) vector[i - start] = true;
        }
        scan.close();

        for (int i = 0; i < vector.length; i++) if (!vector[i]) return start + i;

        return -1;  // all non-negative integers exist in the file
    }

    public static void main(String[] args) {
        try {
            System.out.println(findMissingInt(args[0]));
        } catch (FileNotFoundException x) {
            System.err.format("file %s not found %s%n", args[0], x);
        }
    }
}