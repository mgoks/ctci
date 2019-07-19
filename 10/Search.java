import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class Search {
	static int search(int[] a, int e) {
		return binSearch(a, e, 0, a.length - 1);
	}

	static int binSearch(int[] a, int e, int low, int high) {
		if (high < low) return -1;

		int mid = (high + low) / 2;
		if (a[mid] == e) {
			return mid;
		} else if (a[mid] < e) {	// search right
			int fromRight = binSearch(a, e, mid + 1, high);
			if (fromRight > -1) return fromRight;
		}
		return binSearch(a, e, low, mid - 1);	// search left
	}

	public static void main(String[] args) {
		int[] a = new int[args.length];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(args[i]);
		}
		for (int i : a) {
			System.out.printf("Find %d in %s\n", i, Arrays.toString(a));
			System.out.println(search(a, i));
		}
	}
}