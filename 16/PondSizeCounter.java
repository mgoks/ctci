import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class PondSizeCounter {

    private static final int WATER = 0;
    private static final int COUNTED = -1;

    public static void main(String[] args) {
        int rowLength = Integer.parseInt(args[0]);
        int colLength = Integer.parseInt(args[1]);
        if (args.length != rowLength * colLength + 2) {
            System.err.format("number of args must be args[0] * args[1] + 2 = %d%n", 
                rowLength * colLength + 2);
            return;
        }
        int[][] map = new int[rowLength][colLength];
        int argsIndex = 2;
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < colLength; col++) {
                map[row][col] = Integer.parseInt(args[argsIndex]);
                argsIndex++;
            }
        }

        // print map
        System.out.println("MAP:");
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");

        PondSizeCounter pondSizeCounter = new PondSizeCounter();
        System.out.println(pondSizeCounter.computePondSizes(map));
    }

    // Using depth-first search
    // O(nm) time and O(1) space in the lengths of map
    List<Integer> computePondSizes(int[][] map) {
        if (map == null)
            return null;

        List<Integer> pondSizes = new ArrayList<>();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == WATER) {
                    pondSizes.add(computePondSize(map, row, col));
                }
            }
        }
        return pondSizes;
    }

    private int computePondSize(int[][] map, int row, int column) {
        /* if out of bounds, already counted, or is not water */
        if (row < 0 || column < 0 || row >= map.length || column >= map[row].length || 
            map[row][column] != WATER) {    // is not water or already counted
            return 0;
        }

        int pondSize = 1;
        map[row][column] = COUNTED;
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                pondSize += computePondSize(map, row + r, column + c);
            }
        }
        return pondSize;
    }
}