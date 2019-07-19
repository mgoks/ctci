import java.util.concurrent.ThreadLocalRandom;

public class Apocalypse {
	static final boolean GIRL = false;
	static final boolean BOY  = true;

	static double simulate(int fams) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		System.out.format("There are %d families.%n", fams);
		int girls = 0, boys = 0;
		while (girls < fams) {	// while each family doesn't have a girl
			boolean baby = random.nextBoolean();
			String gender = baby == GIRL? "girl" : "boy";
			// System.out.format("New baby %s!%n", gender);
			if (baby == BOY) {
				boys++;
				// System.out.format("Current number of boys is %d.%n", boys);
			} else {
				girls++;
				// System.out.format("Current number of girls is %d.%n", girls);
			}
		}
		return (double) boys / (double) girls;
	}

	public static void main(String[] args) {
		int fams = Integer.parseInt(args[0]);
		double ratio = simulate(fams);
		System.out.format("The ratio is %f.%n", ratio);
	}
}