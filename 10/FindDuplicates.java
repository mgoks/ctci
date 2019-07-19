import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class FindDuplicates {
	static void printDups(Integer[] arr) {
		if (arr == null || arr.length == 0) 
			System.out.println("no duplicates found");
		else {
			boolean[] isDup = new boolean[32001];
			for (int i : arr) {
				if (isDup[i]) System.out.println(i);
				else isDup[i] = true;
			}
		}
	}

	public static void main(String[] args) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int N = random.nextInt(1, 32001);	// upperbound for numbers
		// N = Integer.parseInt(args[0]);
		int numberOfDups = random.nextInt(0, N+1);
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) list.add(i);
		for (int i = 0; i < numberOfDups; i++) list.add(random.nextInt(1, N+1));
		Collections.shuffle(list);
		System.out.println("array: " + list);
		System.out.println("duplicates:");
		printDups(list.toArray(new Integer[0]));
	}
}