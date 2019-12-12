import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator7 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RandomNumberGenerator7 random = new RandomNumberGenerator7();
        int[] dist = new int[7];
        while (n-- > 0) {
            int randomInt = random.rand7();
            dist[randomInt]++;
        }
        System.out.println("number   frequency");
        for (int i = 0; i < dist.length; i++) {
            System.out.format("%d        %d%n", i, dist[i]);
        }
    }

    int rand7() {
        while(true) {
            int i = 5 * rand5() + rand5();
            if (i < 21) return i % 7;
        }
    }

    int rand5(){
        return ThreadLocalRandom.current().nextInt(0, 5);
    }
}