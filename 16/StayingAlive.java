import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class StayingAlive {
    private static final int MIN_BIRTH_YEAR = 1900;
    private static final int MAX_BIRTH_YEAR = 2000;
    private static final int LIFE_YEARS = 100;

    public static void main(String[] args) {
        // Enter number of people as args[0]
        int n = Integer.parseInt(args[0]);  // number of people
        Person[] persons = new Person[n];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < persons.length; i++) {
            int birthYear = random.nextInt(MIN_BIRTH_YEAR, MAX_BIRTH_YEAR + 1);
            // assuming a person lives 100 years max
            persons[i] = new Person(birthYear, random.nextInt(birthYear, birthYear + LIFE_YEARS + 1));
        }
        System.out.println("Dates:");
        for (Person p : persons) {
            System.out.println(p);
        }
        System.out.format("Most people are alive on year %d%n", 
                          getYearMostAliveBruteForce(persons, MIN_BIRTH_YEAR, MAX_BIRTH_YEAR));
    }

    // O(n) time (since number of years is always 100), O(n) space 
    static int getYearMostAliveBruteForce(Person[] persons, int minYear, int maxYear) {
        if (persons == null || persons.length == 0) return 0;
        
        // assuming all people are born between 1900 and 2000
        // create an array of size maxYear - minYear + 1 store the number of people alive each year
        // since we are assuming all births to be between 1900 and 2000, number of people alive on
        // years after 2000 will not be higher than years before 2000
        int[] numAlive = new int[maxYear - minYear + 1]; // number of people alive in year i + 1900
        for (Person person : persons) {
            for (int year = person.birthYear; year <= person.deathYear; year++) {
                if (year - minYear < numAlive.length) {
                    numAlive[year - minYear]++;
                }
            }
        }

        // find the year with max number of people alive
        int maxAliveYear = 0;
        int n_max = 0;
        for (int y = 0; y < numAlive.length; y++) {
            if (numAlive[y] > n_max) {
                n_max = numAlive[y];
                maxAliveYear = y + minYear;
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