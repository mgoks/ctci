import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class StayingAlive {
    private static final int MIN_BIRTH_YEAR = 1900;
    private static final int MAX_BIRTH_YEAR = 2000;
    private static final int LIFE_YEARS = 100;  // assuming a person lives 100 years max

    public static void main(String[] args) {
        // Enter number of people as args[0]
        int n = Integer.parseInt(args[0]);  
        Person[] persons = new Person[n];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < persons.length; i++) {
            int birthYear = random.nextInt(MIN_BIRTH_YEAR, MAX_BIRTH_YEAR + 1);
            persons[i] = new Person(birthYear, random.nextInt(birthYear, birthYear + LIFE_YEARS + 1));
        }
        System.out.println("Dates:");
        for (Person p : persons) {
            System.out.println(p);
        }
        System.out.format("Most people are alive on year %d, %d (sort)%n", 
                          getYearMostAlive(persons, MIN_BIRTH_YEAR, MAX_BIRTH_YEAR),
                          getYearMostAliveSort(persons, MIN_BIRTH_YEAR, MAX_BIRTH_YEAR));
    }

    /* O(n + m) time in the length of persons (n) and number of years (m, assumed to be 100) 
     * Uses the magnitude of change in population for each year */
    static int getYearMostAlive(Person[] persons, int minYear, int maxYear) {
        if (persons == null || persons.length == 0) return 0;

        int[] delta = new int[maxYear - minYear + 1]; // magnitude of population change at year i
        for (Person person : persons) {
            delta[person.birthYear - minYear]++;
            if (person.deathYear < maxYear) {
                // decrement delta[year+1] because if person was born and died in same year,
                // he/she is counted as alive for that year
                delta[person.deathYear - minYear + 1]--;
            }
        }

        // walk through delta to find the year when most people were alive
        int maxAlive = 0;
        int maxAliveYear = 0;
        int currentAlive = 0;
        for (int i = 0; i < delta.length; i++) {
            currentAlive += delta[i];
            if (currentAlive > maxAlive) {
                maxAlive = currentAlive;
                maxAliveYear = i + minYear;
            }
        }
        return maxAliveYear;
    }

    /* O(n logn) time in the length of persons 
     * This algorithm might be slower than the one above for most inputs, 
     * the speeds cannot be directly compared. */
    static int getYearMostAliveSort(Person[] persons, int minYear, int maxYear) {
        if (persons == null) return 0;

        int[] births = getSortedYears(persons, true);
        int[] deaths = getSortedYears(persons, false);

        int i = 0; // index for births
        int j = 0; // index for deaths
        int currentAlive = 0;
        int maxAlive = currentAlive;
        int maxAliveYear = 0;

        /* We can stop once i == births.length, since there will be no new births, 
           thus no new maximum, after that. */
        while (i < births.length && j < deaths.length) {
            if (births[i] <= deaths[j]) {
                currentAlive++;
                if (currentAlive > maxAlive) {
                    maxAlive = currentAlive;
                    maxAliveYear = births[i];
                }
                i++;
            } else {
                currentAlive--;
                j++;
            }
        }
        return maxAliveYear;
    }


    private static int[] getSortedYears(Person[] persons, boolean sortByBirthYear) {
        int[] years = new int[persons.length];
        for (int i = 0; i < persons.length; i++) {
            years[i] = sortByBirthYear? persons[i].birthYear : persons[i].deathYear;
        }
        Arrays.sort(years);
        return years;
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