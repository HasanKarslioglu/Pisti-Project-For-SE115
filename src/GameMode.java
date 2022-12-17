public class GameMode {


    //------DEFINITIONS---------//
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

        //Printing round count and games border
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬  Round "+ roundCount +"  ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        //Printing ----COMPUTER HAND----
        computer.printHand();
        System.out.println("---------------------------------------------------------------------\n");

        //Printing ----TABLE----
        System.out.print("Table Deck ▶ ");
        System.out.print("\t\t\t\t\t" +tableCardList.getCard(tableCardList.getLastIndex()).getType());
        System.out.println(tableCardList.getCard(tableCardList.getLastIndex()).getNum());
        System.out.print("\t\t\t\t\t\t\t ");
        for (int i = 0; i < (tableCardList.getLastIndex()); i++) {
            System.out.print(tableCardList.getCard(i).getType());
            System.out.print(tableCardList.getCard(i).getNum() + " ");
        }

        //Printing ----USER HAND----
        System.out.println("\n\n---------------------------------------------------------------------");
        user.printHand();

        //Printing games border
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

    }

    public void dealCards(){
        //If roundCount is 1, it means we are in the first dealing. That's why, we have to deal to tableDeck too.
        if (roundCount == 1){
            for (int i = 0; i < 4; i++) {
                //Dealing for table.
                tableCardList.addCard(unDistributedCardList.removeCard());
                //Dealing for user.
                user.getHandCards().addCard(unDistributedCardList.removeCard());
                //Dealing for computer.
                computer.getHandCards().addCard(unDistributedCardList.removeCard());
            }
        }

        //If roundCount is greater than 1, it means its not first dealing. That's why,
        //we have to deal to just user and computer. Not tableDeck.
        else {
            for (int i = 0; i < 4; i++) {
                //Dealing for user.
                user.getHandCards().addCard(unDistributedCardList.removeCard());
                //Dealing for computer.
                computer.getHandCards().addCard(unDistributedCardList.removeCard());
            }
        }
    }

        //----------GETTERS----------//
    public int getRoundCount(){return roundCount;}

        //----------SETTERS----------//
    public void updateRoundCount(){this.roundCount += 1;}
}

