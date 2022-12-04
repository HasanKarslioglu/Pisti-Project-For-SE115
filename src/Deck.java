import java.util.Random;
public class Deck {
    Random rnd = new Random();
    private int lastIndex = 0;
    private int cardsLength = 0;
    private char[] cardValues = {'A', '2', '3', '4', '5', '6', '7',
                                '8', '9', '0', 'J', 'Q', 'K'};
    private char[] cardTypes = {'♠', '♣', '♥', '♦'};


    private Card[] cards;

    //----------CONSTRUCTOR----------//
    Deck(int cardNumbers){
        this.cardsLength = cardNumbers;
        this.lastIndex = cardsLength;
        cards = new Card[cardsLength];
        for (int i = 0; i < cardsLength; i++) {
            cards[i] = new Card('0', '0');
        }
    }

    //----------METHODS----------//
    public void printDeck(){
        for (int i = 0; i < cardsLength; i++) {
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
}












