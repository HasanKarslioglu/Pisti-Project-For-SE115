import java.util.Scanner;

public class Player {

        //------DEFINITIONS---------//

    //Users score
    private int score = 0;
    private int pistiCount = 0;

    //This boolean provides us to take remaining cards to last player who collected lastly.
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

    Player(CardList tableCards, Scanner sc){
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
        System.out.println("");
    }

    public void playCard(){
        //PlayCard method have been used just for determine who call playCard method (User? or Computer?)
        if (name.equals("Computer"))
            computerPlayCard();
        else
            userPlayCard();

        System.out.println("\n");
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

        //Printing user played card.
        System.out.print("You played -> " + tableCards.getCard(tableCards.getLastIndex()).getType());
        System.out.println(tableCards.getCard(tableCards.getLastIndex()).getNum());

        if (tableCards.canCollectAllCard()){                    //Checks if it is collectable (It checks is they..
                                                                //..same number and was joker played)
            tableCards.takeAllCards(this);                //User takes all board's cards.
            userCollectedLastly = true;                         //This boolean provides us to take remaining cards..
                                                                //..to last player who collected lastly.
        }
    }

    private void computerPlayCard(){

        //Computer has 3 checklist to play strategically.
        //1-Firstly, computer checks if there is any matched with his hand cards and board cards.
        //For this situation there is a method called doIHaveMatchedCard.
        //doIHaveMatchedCard returns index of matched cards.
        //If it can't find any matched card it returns -1 by default.
        //If there is matched card, computer is going to play that card.
        //tableCards.getLastCard().getNum() != 'x' checks if table last cards is valid.
        if (tableCards.getLastCard().getNum() != 'x' && doIHaveMatchedCard() != -1)
            tableCards.addCard(handCards.removeCard(doIHaveMatchedCard()));
        //2-Secondly 'else if' condition will be executed when it couldn't find any matched number.
        //It checks if computer hand cards includes joker with called doIHaveJoker method.
        //doIHaveMatchedCard returns index of matched cards.
        //If it can't find any matched card it returns -1 by default.
        //If there is joker computer is going to play that joker.
        else if (tableCards.getLastCard().getNum() != 'x' && doIHaveJoker() != -1)
            tableCards.addCard(handCards.removeCard(doIHaveJoker()));
        //3-Thirdly 'else' condition will be executed when it couldn't find any matched card or joker.
        //Computer will try to play card from 0 index, if there is no card in 0. index..
        //..it will try to play card 1 until end of the cards.
        //Attention** it looks like plays row by row and doesn't look like randomly but his cards are already..
        //..random because of the shuffling and dealing deck.
        else {
            for (int i = 0; i < 4; i++) {
                if (handCards.getCard(i).getNum() != 'x'){
                    tableCards.addCard(handCards.removeCard(i));
                    break;
                }
            }
        }

        //Printing computer played card.
        System.out.print("Computer played -> " + tableCards.getCard(tableCards.getLastIndex()).getType());
        System.out.println(tableCards.getCard(tableCards.getLastIndex()).getNum());

        //canCollectAllCard method checks can player takes all cards.
        if (tableCards.canCollectAllCard()){
            tableCards.takeAllCards(this);
            userCollectedLastly = false;
        }
    }

    //If hand cards includes same number with board last card it returns index of matched card.
    private int doIHaveMatchedCard(){
        for (int i = 0; i < 4; i++) {
            if (tableCards.getLastCard().getNum() == handCards.getCard(i).getNum())
                return i;
        }
        return -1;
    }

    //If hand cards includes joker card, it returns index of matched card.
    private int doIHaveJoker(){
        for (int i = 0; i < 4; i++) {
            if (handCards.getCard(i).getNum() == 'J') return i;
        }
        return -1;
    }

        //--------SETTERS---------//
    public void setName(String name){this.name = name;}
    public void addScore(int score){this.score += score;}
    public void incrementPistiCount() {
        pistiCount++;
        System.out.println(name + " maked pisti.");
    }

    //--------GETTERS---------//
    public CardList getHandCards() {return handCards;}
    public CardList getCollectedCards() {return collectedCards;}
    public String getName(){return name;}
    public int getScore(){return score;}
    public boolean getUserCollectedLastly(){return userCollectedLastly;}
    public int getPistiCount(){return pistiCount;}
}
