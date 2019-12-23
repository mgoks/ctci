import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class DeckShuffler {
    public static void main(String[] args) {
        DeckShuffler shuffler = new DeckShuffler();
        ThreadLocalRandom randomNumberGenerator = ThreadLocalRandom.current();

        Card[] deck = shuffler.createDeck();
        System.out.println("original deck: " + Arrays.toString(deck));

        shuffler.shuffleDeck(deck);
        System.out.println("shuffled deck: " + Arrays.toString(deck));

        Card[] deck2 = shuffler.createDeck();
        shuffler.shuffleDeckIterative(deck2);
        System.out.println("iteratively  : " + Arrays.toString(deck2));
    }

    // shuffle deck in-place recursively
    void shuffleDeck(Card[] deck) {
        shuffleDeck(deck, deck.length - 1);
    }

    private void shuffleDeck(Card[] deck, int index) {
        if (index <= 0)
            return;
        shuffleDeck(deck, index-1);
        int i = ThreadLocalRandom.current().nextInt(0, index + 1);    // upper bound exlusive
        swap(i, index, deck);
    }

    void shuffleDeckIterative(Card[] deck) {
        for (int i = 0; i < deck.length; i++) {
            int j = ThreadLocalRandom.current().nextInt(0, i+1);
            swap(j, i, deck);
        }
    }

    private void swap(int index1, int index2, Object[] array) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private Card[] createDeck() {
        Card[] deck = new Card[52];
        int index = 0;
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
                deck[index++] = card;;
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