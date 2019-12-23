import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DeckShuffler {
    public static void main(String[] args) {
        DeckShuffler shuffler = new DeckShuffler();
        ThreadLocalRandom randomNumberGenerator = ThreadLocalRandom.current();

        ArrayList<Card> deck = shuffler.createDeck();
        System.out.println("original deck: " + deck);

        ArrayList<Card> shuffled = shuffler.shuffleDeck(deck, randomNumberGenerator);
        System.out.println("shuffled deck: " + shuffled);
    }

    // contant time and space (upperbounded by 52)
    ArrayList<Card> shuffleDeck(ArrayList<Card> deck, ThreadLocalRandom randomNumberGenerator) {
        ArrayList<Card> shuffled = new ArrayList<>();
        shuffleDeck(deck, shuffled, randomNumberGenerator);
        return shuffled;
    }

    private void shuffleDeck(ArrayList<Card> orig, ArrayList<Card> shuffled, ThreadLocalRandom numGenerator) {
        if (shuffled.size() == 52)
            return;
        int i = numGenerator.nextInt(0, orig.size());
        Card card = orig.remove(i);
        shuffled.add(card);
        shuffleDeck(orig, shuffled, numGenerator);
    }

    private ArrayList<Card> createDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (String suit : Card.suits) {
            for (int rank = 1; rank < 14; rank++) {
                Card card;
                if (rank == 1)
                    card = new Card(suit, "A");
                else if (rank == 11)
                    card = new Card(suit, "J");
                else if (rank == 12)
                    card = new Card(suit, "Q");
                else if (rank == 13)
                    card = new Card(suit, "K");
                else 
                    card = new Card(suit, String.valueOf(rank));
                deck.add(card);
            }
        }
        return deck;
    }


    public static class Card {
        static String[] suits = {
            "\u2660", "\u2665", "\u2666", "\u2663"
        };

        final String suit;
        final String rank;

        public Card(String s, String r) {
            suit = s;
            rank = r;
        }

        @Override
        public String toString() {
            return rank + suit;
        }
    }
}