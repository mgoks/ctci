import java.util.concurrent.ThreadLocalRandom;

public class LangtonsAnt {
    public static void main(String[] args) {
        LangtonsAnt ant = new LangtonsAnt();
        ant.printMoves(Integer.parseInt(args[0]));
    }

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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ThreadLocalRandom.current().nextBoolean();
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
        turn(direction, turnRight);

        // move forward
        position[0] += direction[0];
        position[1] += direction[1];

        simulateMoves(movesLeft-1, grid, position, direction);
    }

    void turn(int[] direction, boolean turnRight) {
        // swap direction[0] and direction[1]
        int temp = direction[0];
        direction[0] = direction[1];
        direction[1] = temp;

        /* if turning right, change sign of direction[1];
         * else, change sign of direction[0] */
        direction[turnRight? 1 : 0] *= -1;
    }

    void printGrid(boolean[][] grid) {
        System.out.print("    ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");

        System.out.print("    ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("--");
        }
        System.out.println("");
        
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " | ");
            for (boolean white : grid[i]) {
                System.out.print(white? "O " : "# ");
            }
            System.out.println("");
        }
    }
}