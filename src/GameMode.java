public class GameMode {


    //------DEFINATIONS---------//
    private int roundCount = 0;
    private Deck unDistributedDeck;
    private Deck tableDeck;
    private Player computer;
    private Player user;


        //--------------CONSTRUCTOR--------------//
    GameMode(Deck unDistributedDeck, Deck tableDeck, Player user, Player computer){
        this.unDistributedDeck = unDistributedDeck;
        this.tableDeck = tableDeck;
        this.computer = computer;
        this.user = user;
    }

        //--------------METHODS--------------//
    public void printRound(){
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬  Round "+ roundCount +"  ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");


        //Printing computer hand
        computer.printHand();
        System.out.println("---------------------------------------------------------------------\n");
        //Printing table deck
        tableDeck.printDeck();
        System.out.println("\n---------------------------------------------------------------------");
        //Printing user hand
        user.printHand();


        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }

    public void dealCards(){
        //If roundCount is 0, it means its first dealing. That's why, we have to deal to tableDeck too
        if (roundCount == 0){
            for (int i = 0; i < 4; i++) {
                tableDeck.addCard(unDistributedDeck.removeCard());
                user.handDeck.addCard(unDistributedDeck.removeCard());
                computer.handDeck.addCard(unDistributedDeck.removeCard());
            }
        }
        //If roundCount is 1, it means its not first dealing. That's why,
        // we have to deal to just user and computer not tableDeck
        else {
            for (int i = 0; i < 4; i++) {
                user.handDeck.addCard(unDistributedDeck.removeCard());
                computer.handDeck.addCard(unDistributedDeck.removeCard());
            }
        }
    }


        //----------GETTERS----------//
    public int getRoundCount(){return roundCount;}

        //----------SETTERS----------//
    public void setRoundCount(int roundCount){this.roundCount = roundCount;}
}
