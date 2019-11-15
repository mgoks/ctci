import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class StayingAlive {
    public static void main(String[] args) {
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
        System.out.format("Most people are alive on year %d%n", getYearMostAlive(persons));
    }

    static int getYearMostAlive(Person[] persons) {
        if (persons == null || persons.length == 0) return 0;
        
        int[] birthYears = new int[persons.length];
        int[] deathYears = new int[persons.length];
        for (int i = 0; i < persons.length; i++) {
            birthYears[i] = persons[i].birthYear;
            deathYears[i] = persons[i].deathYear;
        }
        Arrays.sort(birthYears);
        Arrays.sort(deathYears);

        int n = 0; // number of people alive currently
        int max_n = n;    // maximum number of people alive at any given time
        int yearMostAlive = 0;    // the year when most people are alive

        int i = 0;
        int j = 0;
        while (i < birthYears.length || j < deathYears.length) {
            while (i < birthYears.length && birthYears[i] <= deathYears[j]) {
                n++;
                if (n > max_n) {
                    n = max_n;
                    yearMostAlive = birthYears[i]; // current year
                }
                i++;
            }
            while (j < deathYears.length && ((i < birthYears.length && deathYears[j] <= birthYears[i]) || 
                                             (i == birthYears.length && deathYears[j] == birthYears[i-1]))) {
                n--;
                j--;
            }
        }
        return yearMostAlive;
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
            return String.format("%d - %d%n", birthYear, deathYear);
        }
    }
}