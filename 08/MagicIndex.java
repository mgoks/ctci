public class MagicIndex {
	static int magicIndex(int[] a) {
		// return magicIndex(a, 0, a.length - 1);
		return magic(a, 0, a.length - 1);
	}
	static int magicIndex(int[] A, int lo, int hi) {
		if (hi < lo) 
			return -1;
		int mid = (lo + hi) / 2;
		if (A[mid] == mid) 
			return mid;
		else if (A[mid] < mid) 
			return magicIndex(A, mid+1, hi);	// search right
		else 
			return magicIndex(A, lo, mid-1); 	// search left
	}

	// What if values aren't distinct?
	static int magic(int[] a, int lo, int hi) {
		if (hi < lo)
			return -1;
		int midIndex = (lo + hi) / 2,
			midVal   = a[midIndex];
		if (midIndex == midVal)
			return midIndex;
		int leftIndex = Math.min(midIndex - 1, midVal),
			rightIndex = Math.max(midIndex + 1, midVal);
		int magicIndexFromLeft = magic(a, lo, leftIndex);
		return magicIndexFromLeft > -1? magicIndexFromLeft : magic(a, rightIndex, hi);
	}

	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		int[] a = new int[size];
		for (int i = 1; i < args.length; i++) {
			a[i-1] = Integer.parseInt(args[i]);
		}
		System.out.println(magicIndex(a));
	}
}