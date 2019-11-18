import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class StayingAlive {
    public static void main(String[] args) {
        // Enter number of people as args[0]
        int n = Integer.parseInt(args[0]);  // number of people
        Person[] persons = new Person[n];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < persons.length; i++) {
            int birthYear = random.nextInt(1900, 2001);
            persons[i] = new Person(birthYear, random.nextInt(birthYear, 2001));
        }
        System.out.println("Dates:");
        for (Person p : persons) {
            System.out.println(p);
        }
        System.out.format("Most people are alive on year %d%n", getYearMostAliveBruteForce(persons));
    }

    // O(n) time (since number of years is always 100), O(n) space 
    static int getYearMostAliveBruteForce(Person[] persons) {
        if (persons == null || persons.length == 0) return 0;
        
        // assuming all people are born between 1900 and 2000
        // create an array of size 101 store the number of people alive at each year
        int[] n = new int[101]; // number of people alive in year i + 1900
        for (Person p : persons) {
            for (int i = p.birthYear; i <= p.deathYear; i++) {
                n[i-1900]++;
            }
        }

        // find the year with max number of people alive
        int maxAliveYear = 0;
        int n_max = 0;
        for (int y = 0; y < n.length; y++) {
            if (n[y] > n_max) {
                n_max = n[y];
                maxAliveYear = y + 1900;
            }
        }
        return maxAliveYear;
    }

    static class Person {
        int birthYear;
        int deathYear;

        public Person(int b, int d) {
            birthYear = b;
            deathYear = d;
        }

        @Override
        public String toString() {
            return String.format("%d - %d", birthYear, deathYear);
        }
    }
}