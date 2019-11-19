import java.util.Arrays;

public class DivingBoard {
	public static void main(String[] args) {
		/* args[0] ... number of blocks
		   args[1] ... length of shorter blocks
		   args[2] ... length of longer blocks */
		int k = Integer.parseInt(args[0]);
		int shorter = Integer.parseInt(args[1]);
		int longer = Integer.parseInt(args[2]);
		System.out.format("Lengths of all diving boards built with %d blocks of lengths %d and %d:%n%s%n", 
						  k, shorter, longer, Arrays.toString(getAllLengths(k, shorter, longer)));
	}

	// O(k) time and space complexity (optimal solution)
	static int[] getAllLengths(int k, int shorter, int longer) {
		// edge cases
		if (k < 1) return new int[0];
		if (shorter == longer) {
			int[] lengths = new int[1];
			lengths[0] = shorter * k;
			return lengths;
		}

		int[] lengths = new int[k+1];
		for (int i = 0; i < lengths.length; i++, k--) {
			lengths[i] = k * shorter + i * longer;
		}
		return lengths;
	}
}