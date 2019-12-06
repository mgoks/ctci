import java.util.concurrent.ThreadLocalRandom;

public class LangtonsAnt {
    void printMoves(int k) {
        if (k < 0) {
            System.err.println("number of moves can't be negative.");
        }
        
        boolean[][] grid = createRandomGrid(2*k);
        
        System.out.println("original grid");
        printGrid(grid);
        System.out.println("");
        
        int[] position = {grid.length/2, grid.length/2};
        int[] direction = {0, 1};   // facing right
        simulateMoves(k, grid, position, direction);
        
        System.out.println("after " + k + " moves");
        printGrid(grid);
        System.out.println("");
    }

    /* Creates new boolean 2d-array representing the grid
     * grid[i][j] == true indicates that the square at row i column j
     * is white; else, the square is black */
    boolean[][] createRandomGrid(int size) {
        if (size < 0) 
            return null;
        boolean[][] grid = new boolean[size][size];
        for (boolean[] row : grid) {
            for (boolean white : row) {
                white = ThreadLocalRandom.current().nextBoolean();
            }
        }
        return grid;
    }

    void simulateMoves(int movesLeft, boolean[][] grid, int[] position, int[] direction) {
        if (movesLeft == 0)
            return;
        int row = position[0];
        int col = position[1];
        boolean turnRight = grid[row][col]; // turn right if square is white
        grid[row][col] = !grid[row][col];   // flip color
        int[] newDir = turn(direction, turnRight);

        // move forward

    }
}