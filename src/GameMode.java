public class GameMode {


    //------DEFINATIONS---------//
    private int roundCount = 0;
    private CardList unDistributedCardList;
    private CardList tableCardList;
    private Player computer;
    private Player user;


        //--------------CONSTRUCTOR--------------//
    GameMode(CardList unDistributedCardList, CardList tableCardList, Player user, Player computer){
        this.unDistributedCardList = unDistributedCardList;
        this.tableCardList = tableCardList;
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
        tableCardList.printDeck();
        System.out.println("\n---------------------------------------------------------------------");
        //Printing user hand
        user.printHand();


        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }

    public void dealCards(){
        //If roundCount is 0, it means its first dealing. That's why, we have to deal to tableDeck too
        if (roundCount == 0){
            for (int i = 0; i < 4; i++) {
                tableCardList.addCard(unDistributedCardList.removeCard());
                user.getHandCards().addCard(unDistributedCardList.removeCard());
                computer.getHandCards().addCard(unDistributedCardList.removeCard());
            }
        }
        //If roundCount is 1, it means its not first dealing. That's why,
        // we have to deal to just user and computer not tableDeck
        else {
            for (int i = 0; i < 4; i++) {
                user.getHandCards().addCard(unDistributedCardList.removeCard());
                computer.getHandCards().addCard(unDistributedCardList.removeCard());
            }
        }
    }


        //----------GETTERS----------//
    public int getRoundCount(){return roundCount;}

        //----------SETTERS----------//
    public void setRoundCount(int roundCount){this.roundCount = roundCount;}
}
