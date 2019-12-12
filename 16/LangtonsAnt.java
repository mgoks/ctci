import java.util.HashSet;
import java.lang.StringBuilder;

public class LangtonsAnt {
    public static void main(String[] args) {
        LangtonsAnt ant = new LangtonsAnt();
        ant.printMoves(Integer.parseInt(args[0]));  // number of steps
    }

    /* O(k) time
     * Space complexity is more difficult to determine. Seems like it is a function
     * of k which grows faster than log(k) but slower than k. */
    void printMoves(int k) {
        Board board = new Board();
        Ant ant = new Ant();
        while (k-- > 0) {
            boolean white = board.isWhite(ant.position);
            board.flipSquare(ant.position);
            ant.turn(white);
            ant.moveForward();
            board.ensureFit(ant.position);
        }
        System.out.println(board);
        // System.out.format("number of whites is %d%n", board.whites.size());
    }

    
    class Board {
        HashSet<Position> whites = new HashSet<>();
        Position minPosition = new Position(0, 0);
        Position maxPosition = new Position(0, 0);

        boolean isWhite(Position p) {
            return whites.contains(p);
        }

        void flipSquare(Position p) {
            if (isWhite(p)) whites.remove(p);
            else whites.add(p);
        }

        void ensureFit(Position p) {
            if (p.row < minPosition.row || p.column < minPosition.column) {
                minPosition = new Position(Math.min(p.row, minPosition.row), 
                                           Math.min(p.column, minPosition.column));
            }
            if (p.row > maxPosition.row || p.column > maxPosition.column) {
                maxPosition = new Position(Math.max(p.row, maxPosition.row),
                                           Math.max(p.column, maxPosition.column));
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int row = minPosition.row; row <= maxPosition.row; row++) {
                for (int col = minPosition.column; col <= maxPosition.column; col++) {
                    Position p = new Position(row, col);
                    sb.append(isWhite(p)? " " : "\u2588");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }


    class Ant {
        Position position = new Position(0, 0);
        Position orientation = new Position(0, 1);  // start facing right

        void turn(boolean turnRight) {
            if (turnRight) 
                orientation = new Position(orientation.column, -orientation.row);
            else 
                orientation = new Position(-orientation.column, orientation.row);
        }

        void moveForward() {
            position = new Position(position.row + orientation.row, position.column + orientation.column);
        }
    }


    class Position {    // immutable
        final int row;
        final int column;

        public Position(int row, int col) {
            this.row = row;
            this.column = col;
        }

        /* Need to override the 2 methods below for HashSet to work 
         * properly.    */
        @Override
        public boolean equals(Object o) {
            if (o instanceof Position) {
                Position other = (Position) o;
                return other.row == row && other.column == column;
            } 
            return false;
        }

        @Override
        public int hashCode(){
            return (row * 31) ^ column;
        }
    }

}