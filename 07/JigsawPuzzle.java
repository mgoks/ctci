public class JigsawPuzzle {
	
	private Piece[][] puzzle;
	private Piece[]   pieces;
	private int       N;

	public JigsawPuzzle(int n, Piece[] pieces) {
		N = n;
		puzzle = new Piece[n][n];
		this.pieces = pieces;
	}

	// Returns true if edge1 and edge2 belong together
	public static boolean fitsWith(Edge edge1, Edge edge2) {
		return edge1.fitsWith(edge2);
	}

	public static solveTopRow() {...}
	public static solveBottomRow() {...}
	public static solveLeftCorner() {...}
	public static solveRightCorner() {...}



	public Piece[][] solvePuzzle() {

		

	}
}

public class Piece {	// puzzle piece
	
 /* edges[0] is upper edge
	edges[1] is right edge
	edges[2] is lower edge
	edges[3] is left  edge */
	private Edge[] edges;
	private boolean isUsed = false;

	public Piece(Edge[] edges) {
		this.edges = edges;
	}

}

public enum Edge {
	INNER, OUTER, FLAT;

	public booleanbooleanfitsWith(Edge other) {
		return this == Edge.INNER && other == Edge.OUTER || this == Edge.OUTER && other == Edge.INNER;
	}
}