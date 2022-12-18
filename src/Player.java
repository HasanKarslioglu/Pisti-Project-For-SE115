import java.nio.file.StandardWatchEventKinds;
import java.util.Scanner;

public class Player {

        //------DEFINITIONS---------//

    //Users score
    private int score = 0;
    private static boolean userCollectedLastly = false;

    //Users name
    private String name;

    //Player's current cards
    private CardList handCards = new CardList(4);

    //Collected cards by player's
    private CardList collectedCards = new CardList(52);

    //We will take a reference of scanner object from Main class
    //by using constructor instead of making a new one.
    private Scanner sc;

    //We need to store tableCards reference for adding or removing card.
    //Constructor is going to fill tableCards variable.
    private CardList tableCards;

    Player(String name, CardList tableCards, Scanner sc){
        this.name = name;
        this.tableCards = tableCards;
        this.sc = sc;
    }

    public void printHand(){

        System.out.print( name + " ▶ \t\t\t\t\t");

        //Following if's lines will print if it is computer
        if (name.equals("Computer")){
            for (int i = 0; i < 4; i++) {
                //Computers hand must be hidden
                if (handCards.getCard(i).getNum() == 'x'){
                    System.out.print("▱ ");
                }else{
                    System.out.print("▰ ");
                }
            }
            //Following else's lines will print if it is user
        }else {
            for (int i = 0; i < 4; i++) {
                if (handCards.getCard(i).getType() == 'x'){
                    //It prints '▱' this symbol for empty card
                    System.out.print("▱ ");
                }else{
                    //It prints card's type and number
                    System.out.print(handCards.getCard(i).getType());
                    System.out.print(handCards.getCard(i).getNum());
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    public void playCard(){
        //PlayCard method have been used just for determine who call playCard method (User? or Computer?)
        if (name.equals("Computer"))
            computerPlayCard();
        else
            userPlayCard();

        System.out.println("\n-----------------------------------------------------------------------");
    }

    private void computerPlayCard(){
        tableCards.addCard(handCards.removeCard());

        System.out.print("Computer played -> " + tableCards.getCard(tableCards.getLastIndex()).getType());
        System.out.println(tableCards.getCard(tableCards.getLastIndex()).getNum());

        if (tableCards.canCollectAllCard()){
            tableCards.takeAllCards(this);
            userCollectedLastly = false;
        }
    }
    private void userPlayCard(){
        //Choose is variable for user's chose card to play.
        int choose;
        System.out.println("Please Enter A Number To Play");

        //But we have to make sure user have entered valid number.
        while(true){
            choose = sc.nextInt();

            //Following if block checks if it is between 1-4 and also checks card is not empty.
            if (choose <= 4 && choose >= 1 && handCards.getCard(choose-1).getType() != 'x') break;
            System.out.println("Please enter valid number (Between 1-4)");
        }
        //User will play card to table.
        tableCards.addCard(handCards.removeCard(choose-1));

        System.out.print("You played -> " + tableCards.getCard(tableCards.getLastIndex()).getType());
        System.out.println(tableCards.getCard(tableCards.getLastIndex()).getNum());

        if (tableCards.canCollectAllCard()){
            tableCards.takeAllCards(this);
            userCollectedLastly = true;
        }
    }

        //--------SETTERS---------//
    public void setName(String name){this.name = name;}

        //--------GETTERS---------//
    public CardList getHandCards() {return handCards;}
    public CardList getCollectedCards() {return collectedCards;}
    public String getName(){return name;}
    public boolean getUserCollectedLastly(){return userCollectedLastly;}
}