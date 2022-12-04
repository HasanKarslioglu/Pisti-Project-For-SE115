public class Main {
    public static void main(String[] args) {
        Deck undistributedDeck = new Deck(52);
        Deck tableDeck = new Deck(52);
        Deck playerDeck = new Deck(4);
        Deck playerCollectedDeck = new Deck(4);
        Deck computerDeck = new Deck(52);
        Deck computerCollectedDeck = new Deck(52);

        undistributedDeck.fillDeck();
        undistributedDeck.printDeck();
        playerDeck.printDeck();
    }
}
