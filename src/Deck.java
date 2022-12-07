import java.util.Random;
import java.util.Scanner;
public class Deck {

    //------DEFINATIONS---------//
    Random rnd = new Random();
    Scanner sc = new Scanner(System.in);
    private char[] cardValues = {'A', '2', '3', '4', '5', '6', '7',
                                '8', '9', '0', 'J', 'Q', 'K'};
    private char[] cardTypes = {'♠', '♣', '♥', '♦'};
    private Card[] cards;

    //----------CONSTRUCTOR----------//
    Deck(int cardNumbers){
        cards = new Card[cardNumbers];
        for (int i = 0; i < cardNumbers; i++) {
            cards[i] = new Card('x', 'x');
        }
    }

    //----------METHODS----------//
    public void printDeck(){
        for (int i = 0; i < cards.length; i += 4) {
            for (int j = 0; j < 4; j++) {
                System.out.print(cards[i+j].getNum() + "-");
                System.out.print(cards[i+j].getType());
                System.out.print("   ");
            }
            System.out.println();
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

    public void shuffleDeck(){
        for (int i = 0; i < (cards.length * 2); i++) {
            int firstIndex = rnd.nextInt(cards.length);
            int secondIndex = rnd.nextInt(cards.length);

            Card temp = cards[firstIndex];
            cards[firstIndex] = cards[secondIndex];
            cards[secondIndex] = temp;
        }
    }

    public void cutDeck(){
        //choosedNumber is a number for where to cut the deck
        int choosedNumber = 0;

        //Asking -> Does the user want to cut it or will the computer cut it?
        System.out.println("Do you want to cut it? please enter 1->Yes, 0->No");
        int choose = sc.nextInt();
        //Looping that ask user again, until user enter valid number
        while (!(choose == 0 || choose == 1)){
            System.out.println("Please enter invalid number (Enter 1 or 0)");
            choose = sc.nextInt();
        }

        //if choose is 0, it means computer will choose a number for cutting
        if (choose == 0){
             choosedNumber = rnd.nextInt(51) + 1;
            System.out.println("Computer choosed " + choosedNumber + " for cutting.");
        //if choose is 1, it means user will choose a number for cutting
        }else {
            System.out.println("Enter a number for where you want to cut deck");
            choosedNumber = sc.nextInt();
            //Looping that ask user again, until user enter valid number
            while (!(choosedNumber > 0 && choosedNumber < 52)){
                System.out.println("Please enter invalid number (Between 1 and 51)");
                choosedNumber = sc.nextInt();
            }
        }

        //Making empty Card array it going to be filled from old array
        //First make new empty array look like ( , , , , , )
        Card[] tempCardArr = new Card[cards.length];
        //For ex if my array is looks like (2,3,6,0,8,5,7) and choosedNumber is 3

        //Move the index after the 3rd index in the old array to new empty array
        int index = 0;
        for (int i = choosedNumber; i < cards.length; i++) {
            tempCardArr[index] = cards[i];
            index++;
        }
        //Now new array looks like (0,8,5,7, , , )

        //Then the index after 0. index to 3rd index in the old array to new array
        for (int i = 0; i < choosedNumber; i++) {
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
}












