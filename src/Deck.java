import java.util.Random;

public class Deck {

    //------DEFINATIONS---------//
    Random rnd = new Random();
    private int lastIndex = 0;
    private int DeckLength = 0;
    private char[] cardValues = {'A', '2', '3', '4', '5', '6', '7',
                                '8', '9', '0', 'J', 'Q', 'K'};
    private char[] cardTypes = {'♠', '♣', '♥', '♦'};
    private Card[] cards;

    //----------CONSTRUCTOR----------//
    Deck(int cardNumbers){
        this.DeckLength = cardNumbers;
        this.lastIndex = DeckLength;

        cards = new Card[DeckLength];
        for (int i = 0; i < DeckLength; i++) {
            cards[i] = new Card('x', 'x');
        }
    }

    //----------METHODS----------//
    public void printDeck(){
        for (int i = 0; i < DeckLength; i++) {
            System.out.print(cards[i].getNum() + " - ");
            System.out.println(cards[i].getType());
        }
    }
    public void fillDeck(){
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[count].setType(cardTypes[i]);
                cards[count].setNum(cardValues[j]);
                count++;
            }
        }
    }

    public void ShuffleDeck(){
        for (int i = 0; i < (DeckLength * 2); i++) {
            int firstIndex = rnd.nextInt(DeckLength);
            int secondIndex = rnd.nextInt(DeckLength);

            Card temp = cards[firstIndex];
            cards[firstIndex] = cards[secondIndex];
            cards[secondIndex] = temp;
        }
    }
}












