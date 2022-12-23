import java.util.Random;
import java.util.Scanner;

public class CardList {

        //------DEFINITIONS---------//
    private static final char[] cardValues = {'A', '2', '3', '4', '5', '6', '7',
                                '8', '9', '0', 'J', 'Q', 'K'};
    private static final char[] cardTypes = {'♠', '♣', '♥', '♦'};

    //We will take a reference of random and scanner object from
    //Main class by using constructor instead of making a new one.
    private Random rnd;
    private Scanner sc;
    private Card[] cards;

    //Last Index provides us to follow how many cards there in the cards array.
    //And also helps us about adding and removing card from array.
    private int lastIndex = -1;


    //We need to check because we want to know who will start to game first.
    private boolean isUserCutDeck = false;

        //----------CONSTRUCTOR----------//
    CardList(int cardNumbers, Random rnd, Scanner sc){
        //We will take a reference of random and scanner object from main class.
        this.rnd = rnd;
        this.sc = sc;

        //It produces array of cards by using argument.
        cards = new Card[cardNumbers];
        for (int i = 0; i < cardNumbers; i++) {
            cards[i] = new Card();
        }
    }

    CardList(int cardNumbers){
        cards = new Card[cardNumbers];
        for (int i = 0; i < cardNumbers; i++) {
            cards[i] = new Card();
        }
    }

    //----------METHODS----------//
    public void fillDeck(){
        int count = 0;
        //Filling deck based on cardTypes and cardValues
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[count].setType(cardTypes[i]);
                cards[count].setNum(cardValues[j]);
                count++;
            }
        }
        //Last index will be used for removeCard method later.
        lastIndex = 51;
    }

    public void shuffleDeck(){
        //if for loop count(cards.length * 2) gets bigger, the more random it will be
        for (int i = 0; i < (cards.length * 2); i++) {
            //Choosing two random index from cards array
            int firstIndex = rnd.nextInt(cards.length);
            int secondIndex = rnd.nextInt(cards.length);

            //Then switch two of them
            Card temp = cards[firstIndex];
            cards[firstIndex] = cards[secondIndex];
            cards[secondIndex] = temp;
        }
    }

    public void cutDeck(){

        //Asking -> Will the user want to cut it or will the computer cut it?
        System.out.println("Do you want to cut it? Please enter 1->Yes, 0->No");
        int choose;
        //Looping that ask user again, until user enter valid number
        while (true){
            choose = sc.nextInt();
            if(choose == 0 || choose == 1) break;
            System.out.println("Please enter invalid number (Enter 1 or 0)");
        }

        //Right sight of equation shows us if it is 1 it means user want to cut
        //Because of that, it will return true
        isUserCutDeck = (choose == 1);

        //choseNumber is a number for where to cut the deck
        int cutIndex;

        //if choose is 0, it means computer will choose a number for cutting
        if (choose == 0){
             cutIndex = rnd.nextInt(51) + 1;
            System.out.println("\nComputer chose " + cutIndex + " for cutting.");
        //if choose is 1, it means user will choose a number for cutting
        }else {
            System.out.println("Enter a number for where you want to cut deck (1-51)");
            cutIndex = sc.nextInt();
            //Looping that ask user again, until user enter valid number
            while (!(cutIndex > 0 && cutIndex < 52)){
                System.out.println("Please enter invalid number (Between 1 and 51)");
                cutIndex = sc.nextInt();
            }
            System.out.println("\nYou chose " + cutIndex + " for cutting.");
        }

        //Making empty Card array. It going to be filled from old array
        //First make new empty array look like ( , , , , , )
        Card[] tempCardArr = new Card[cards.length];
        //For ex if my array is looks like (2,3,6,0,8,5,7) and choseNumber is 3

        //Move the index after the 3rd index in the old array to new empty array
        int index = 0;
        for (int i = cutIndex; i < cards.length; i++) {
            tempCardArr[index] = cards[i];
            index++;
        }
        //Now new array looks like (0,8,5,7, , , )

        //Then the index after 0. index to 3rd index in the old array to new array
        for (int i = 0; i < cutIndex; i++) {
            tempCardArr[index] = cards[i];
            index++;
        }
        //Now, new array looks like (0,8,5,7,2,3,6) and it has been cut

        //Finally, I want to use first array name called 'cards'. That's why,
        //I'm copying the new array onto the old array
        for (int i = 0; i < cards.length; i++) {
            cards[i] = tempCardArr[i];
        }
    }

    public boolean canCollectAllCard(){
        //If board cards last index is joker it returns true.
        //If board cards last 2 index's numbers is equal it returns true. Otherwise returns false.
        if (lastIndex > 0){
            if(cards[lastIndex].getNum() == 'J') return true;
            if(cards[lastIndex].getNum() == cards[lastIndex - 1].getNum()) return true;
        }
        return false;
    }

    public void takeAllCards(Player player){

        //It checks if there is any pisti.
        if (lastIndex == 1 && cards[0].getNum() == cards[1].getNum())
            player.incrementPistiCount();

        int forLoopTime = lastIndex;
        //It tooks all card to player's collected cards array.
        for (int i = 0; i <= forLoopTime; i++) {
            player.getCollectedCards().addCard(removeCard());
        }
        System.out.println(player.getName() + " took all cards.");

    }

    public void addCard(Card newCard){
        //Add card to cards array
        lastIndex++;
        cards[lastIndex] = newCard;
    }

    public Card removeCard(int index){
        //Remove card from specific index
        Card temp = cards[index];
        //Add empty card instead of removed card
        cards[index] = new Card();
        lastIndex--;
        return temp;
    }

    public Card removeCard(){
        //Remove card from specific index
        Card temp = cards[lastIndex];
        //Add empty card instead of removed card
        cards[lastIndex] = new Card();
        lastIndex--;
        return temp;
    }

    //--------GETTERS---------//
    public int getLastIndex(){return lastIndex;}
    public boolean getWasUserCutDeck() {return isUserCutDeck;}
    public Card getLastCard(){
        if (lastIndex < 0) return cards[0];
        return cards[lastIndex];
    }

    public Card getCard(int index){
        if (index < 0) index = 0;
        return cards[index];
    }
}













