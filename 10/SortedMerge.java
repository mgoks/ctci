import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class SortedMerge {
	static void mergeSorted(int[] a, int[] b) {
		if (a == null || b == null) return;

		int index = a.length - 1,	// overall index
			i = a.length - b.length - 1,	// array a's index
			j = b.length - 1;				// array b's index
		while (j >= 0 && index >= 0) {
			if (i == 0) {
				a[index] = b[j];
				j--;
			} else if (a[i] >= b[j]) {
				a[index] = a[i];
				i--;
			} else {
				a[index] = b[j];
				j--;
			}
			index--;
		}
	}

	static void merge(int[] a, int[] b, int aLength, int bLength) {
		if (a == null || b == null) return;

		int i = aLength - 1,	// index of last element in a
			j = bLength - 1,	// index of last element in b
			index = aLength + bLength - 1;	// end of merged array
		while (j >= 0) {
			if (i >= 0 && a[i] >= b[j]) {
				a[index] = a[i];
				i--;
			} else {
				a[index] = b[j];
				j--;
			}
			index--;
		}
	}

	public static void main(String[] args) {
		int aLength = ThreadLocalRandom.current().nextInt(0, 100);
		int bLength = ThreadLocalRandom.current().nextInt(0, 100);
		int[] a = new int[aLength];
		int[] b = new int[bLength];
		for (int i = 0; i < aLength; i++) a[i] = ThreadLocalRandom.current().nextInt(0, 100);
		for (int i = 0; i < bLength; i++) b[i] = ThreadLocalRandom.current().nextInt(0, 100);
		Arrays.sort(a);
		Arrays.sort(b);
		int[] bigA = new int[aLength + bLength];
		for (int i = 0; i < aLength; i++) bigA[i] = a[i];
		System.out.println("A: " + Arrays.toString(bigA));
		System.out.println("B: " + Arrays.toString(b));
		merge(bigA, b, aLength, bLength);
		System.out.println("after merging in sorted order\n" + Arrays.toString(bigA));
	}
}