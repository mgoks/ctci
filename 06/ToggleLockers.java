import java.util.Arrays;

public class ToggleLockers {
	final static boolean CLOSED = false, OPEN = true;

	static void toggle(boolean[] lockers, int i) {
		lockers[i] = lockers[i] == CLOSED? OPEN : CLOSED;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);	// number of lockers
		boolean[] lockers = new boolean[n+1];
		for (int round = 1; round <= n; round++) {
			for (int x = 1; x <= n; x++) {
				if (x % round == 0) {
					toggle(lockers, x);
				}
			}
		}
		int opens = 0;
		for (boolean open : lockers) {
			if (open) opens++;
		}
		System.out.format("%d open lockers%n", opens);
		System.out.println(Arrays.toString(lockers));
	}
}