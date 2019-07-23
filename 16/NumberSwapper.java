import java.util.concurrent.ThreadLocalRandom;

public class NumberSwapper {

	// using arithmetic
	public static void swap(int[] a) {
		a[0] = a[1] - a[0];
		a[1] = a[1] - a[0];
		a[0] = a[0] - a[1];
	}

	// using bit manipulation
	public static void swapBits(int[] a) {
		a[0] = a[0] ^ a[1];
		a[1] = a[0] ^ a[1];
		a[0] = a[0] ^ a[1];
	}

	public static void main(String[] args) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int a = 0, b = 0;
		if (args.length > 1) {
			a = Integer.parseInt(args[0]);
			b = Integer.parseInt(args[1]);
		} else {
			a = random.nextInt(); 
			b = random.nextInt();
		}
		int[] numbers = {a, b};
		System.out.format("a = %d, b = %d%n", numbers[0], numbers[1]);
		swapBits(numbers);
		System.out.format("a = %d, b = %d%n", numbers[0], numbers[1]);
	}
}