import java.util.concurrent.ThreadLocalRandom;

public class NumberSwapper {
	public static void main(String[] args) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int a = 0, b = 0;
		if (args.length > 0) {
			a = Integer.parseInt(args[0]);
			b = Integer.parseInt(args[1]);
		} else {
			a = random.nextInt(); 
			b = random.nextInt();
		}
		System.out.format("a = %d, b = %d%n", a, b);

		// swap a with b without using extra variables
		a = b - a;
		b = b - a;
		a = a + b;

		System.out.format("a = %d, b = %d%n", a, b);
	}
}