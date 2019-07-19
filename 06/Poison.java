import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Poison {
	static final boolean POISONED = true;
	static final int DAYS_WAITED = 7;	// in days

	static int findPoisonDays(List<Boolean> bottles, int numStrips, int numDays) {
		if (bottles.size() == 1 && bottles.get(0) == POISONED) {
			return numDays;
		}
		int groupSize = bottles.size() / numStrips;
		if (bottles.size() % numStrips > 0) groupSize++;
		List<List<Boolean>> groups = groupBottles(bottles, groupSize, numStrips);
		List<Boolean> poisonGroup = null;
		for (List<Boolean> group : groups) {
			if (containsPoison(group)) {
				poisonGroup = group;
				break;
			}
		}
		return findPoisonDays(poisonGroup, numStrips - 1, numDays + DAYS_WAITED);
	}

	static List<List<Boolean>> groupBottles(List<Boolean> bottles, int maxGroupSize, int numGroups) {
		List<List<Boolean>> groups = new ArrayList<>();
		for (int i = 0; i < numGroups; i++) {
			groups.add(new ArrayList<>());
		}
		int i = 0;	// current group index
		for (Boolean bottle : bottles) {
			if (groups.get(i).size() == maxGroupSize) i++;
			groups.get(i).add(bottle);
		}
		return groups;
	}

	static boolean containsPoison(List<Boolean> bottles) {
		for (Boolean bottle : bottles) {
			if (bottle == POISONED) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int numBottles = Integer.parseInt(args[0]);
		List<Boolean> bottles = new ArrayList<>();
		for (int i = 0; i < numBottles - 1; i++) {
			bottles.add(!POISONED);
		}
		int p = ThreadLocalRandom.current().nextInt(numBottles);
		bottles.add(p, POISONED);
		System.out.format("Bottle #%d is poisoned.%n", p);
		System.out.format("It took %d days to find the poisoned bottle.%n", findPoisonDays(bottles, 10, 0));
	}
}