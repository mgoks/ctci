import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;

public class PondSizeCounter {

    private static final int WATER = 0;
    
    private static final int[][] DIRECTIONS = {
        {-1, -1}, {-1,  0}, {-1,  1},
        { 0, -1},           { 0,  1},
        { 1, -1}, { 1,  0}, { 1,  1}
    };

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

    // O(nm) in the lengths of map
    List<Integer> computePondSizes(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0)
            return null;

        List<Integer> pondSizes = new ArrayList<>();
        boolean[][] counted = new boolean[map.length][map[0].length];
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == WATER && !counted[row][col]) {
                    pondSizes.add(computePondSize(map, counted, row, col));
                }
            }
        }
        return pondSizes;
    }

    private int computePondSize(int[][] map, boolean[][] counted, int row, int column) {
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(new Location(row, column));
        int pondSize = 0;
        int rowLength = map.length;
        int colLength = map[0].length;

        while (!queue.isEmpty()) {
            Location node = queue.remove();
            for (Location neighbor : node.getNeighbors(rowLength, colLength)) {
                if (map[neighbor.row][neighbor.column] == WATER && 
                    !counted[neighbor.row][neighbor.column]) {
                    queue.add(neighbor);
                }
            }
            pondSize++;
            counted[node.row][node.column] = true;
        }
        return pondSize;
    }


    class Location {
        int row;
        int column;

        public Location(int row, int col) {
            this.row = row;
            column = col;
        }

        List<Location> getNeighbors(int rowLength, int colLength) {
            List<Location> neighbors = new ArrayList<>();
            for (int[] direction : DIRECTIONS) {
                int row = this.row + direction[0];
                int col = this.column + direction[1];
                if (0 <= row && row < rowLength && 0 <= col && col < colLength) {
                    neighbors.add(new Location(row, col));
                }
            }
            return neighbors;
        }
    }

}