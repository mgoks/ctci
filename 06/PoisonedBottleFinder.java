import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Set;
import java.util.HashSet;

// optimal version
public class PoisonedBottleFinder {
	private static final int NUMBER_OF_BOTTLES = 1000,
							 NUMBER_OF_STRIPS  = 10;

	// returns the number of poisoned bottle
	public static int findPoisonedBottle(List<Bottle> bottles, List<TestStrip> strips) {
		/* for each bottle, put a drop on test strips according to its number in binary */
		for (int i = 0; i < bottles.size(); i++) {
			int b = i;	// bottle number
			/* codify the bottle number onto test strips in reverse binary representation
			e.g. if the bottle number is 47 (0000101111 in binary), the reverse of its
			binary representation would be 1111010000, so put a drop on strips 0, 1, 2, 3,
			and 5, for the sake of an easier implementation */
			for (TestStrip strip : strips) {
				if ((b & 1) == 1) strip.addDrop(bottles.get(i));
				b >>= 1;
			}
		}
		getResults(strips);	// takes 7 days
		int p = 0;	// the number of poisonous bottle
		// remember the strips are codified in reverse
		for (int i = 0; i < strips.size(); i++) {
			TestStrip strip = strips.get(i);
			if (strip.isPositive()) p |= (1 << i);
		}
		return p;
	}

	private static void getResults(List<TestStrip> strips) {
		for (TestStrip strip : strips) {
			for (Bottle bottle : strip.getDrops()) {
				if (bottle.isPoisoned()) {
					strip.setPositive();
					break;
				}
			}
		}
	}

	static class Bottle {
		private boolean poisoned = false;
		public boolean isPoisoned() {return poisoned;}
		public void poison() {poisoned = true;}
	}

	static class TestStrip {
		private Set<Bottle> drops = new HashSet<>();
		private boolean positive = false;
		public void addDrop(Bottle bottle) {drops.add(bottle);}
		public Set<Bottle> getDrops() {return drops;}
		public void setPositive() {positive = true;}
		public boolean isPositive() {return positive;}
	}

	public static void main(String[] args) {
		List<Bottle> bottles = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_BOTTLES; i++) {
			bottles.add(new Bottle());
		}
		int poisonIndex = ThreadLocalRandom.current().nextInt(0, NUMBER_OF_BOTTLES);
		bottles.get(poisonIndex).poison();
		
		List<TestStrip> strips = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_STRIPS; i++) {
			strips.add(new TestStrip());
		}

		int answer = findPoisonedBottle(bottles, strips);
		System.out.format("correct answer: %d\nyour answer: %d", poisonIndex, answer);
	}
}