public class MasterMind {
    public static final int NUMBER_OF_SLOTS = 4;
    public static final int NUMBER_OF_COLORS = 4;

    public static void main(String[] args) {
        Color[] guess = toColorArray(args[0]);
        Color[] sol = toColorArray(args[1]);
        System.out.println("guess = " + args[0]);
        System.out.println("sol   = " + args[1]);
        HitCount hitCount = countHits(guess, sol);
        System.out.format("%d hit(s), %d pseudohit(s)%n", hitCount.hits, hitCount.pseudos);
    }

    // O(1) time and space
    static HitCount countHits(Color[] guess, Color[] solution) {
        if (guess == null || solution == null || guess.length != NUMBER_OF_SLOTS || solution.length != NUMBER_OF_SLOTS)
            return null;

        int hits = 0;
        int pseudos = 0;

        /* Count hits and color counts (the number of occurence of the color) for the indices that are not hits.
           index    color
            0       red
            1       yellow
            2       green
            3       blue    */
        int[] guessColorCount = new int[NUMBER_OF_COLORS];
        int[] solColorCount = new int[NUMBER_OF_COLORS];
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            if (guess[i] == solution[i]) {
                hits++;
            } else {
                guessColorCount[guess[i].ordinal()]++;
                solColorCount[solution[i].ordinal()]++;
            }
        }

        // Calculate the pseudo hit count from the arrays
        for (int i = 0; i < NUMBER_OF_COLORS; i++ ) {
            pseudos += Math.min(guessColorCount[i], solColorCount[i]);
        }
        return new HitCount(hits, pseudos);
    }

    private static Color[] toColorArray(String s) {
        Color[] colors = new Color[s.length()];
        for (int i = 0; i < s.length(); i++) {
            colors[i] = Color.toColor(s.charAt(i));
        }
        return colors;
    }


    static class HitCount {
        int hits;
        int pseudos;

        public HitCount(int h, int p) {
            hits = h;
            pseudos = p;
        }
    }


    static enum Color {
        RED, YELLOW, GREEN, BLUE;

        static Color toColor(char c) {
            switch (Character.toUpperCase(c)) {
                case 'R': return RED;
                case 'Y': return YELLOW;
                case 'G': return GREEN;
                case 'B': return BLUE;
            }
            return null;
        }
    }
}