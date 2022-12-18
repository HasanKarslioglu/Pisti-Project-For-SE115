import java.sql.SQLOutput;

public class GameMode {


    //------DEFINITIONS---------//
    private int roundCount = 0;
    private int eachRoundStep = 1;
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
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬ Round "+ roundCount +" ▬▬▬ Step "+ eachRoundStep +" ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        //Printing ----COMPUTER HAND----
        computer.printHand();
        System.out.println("");

        //Printing ----TABLE----
        System.out.print("Table Deck ▶ ");
        if (tableCardList.getCard(0).getType() != 'x')
        {
            System.out.print("\t\t\t\t\t" +tableCardList.getCard(tableCardList.getLastIndex()).getType());
            System.out.println(tableCardList.getCard(tableCardList.getLastIndex()).getNum());
            System.out.print("\t\t\t\t\t\t\t ");
            for (int i = 0; i < (tableCardList.getLastIndex()); i++) {
                System.out.print(tableCardList.getCard(i).getType());
                System.out.print(tableCardList.getCard(i).getNum() + " ");
            }
        }

        //Printing ----USER HAND----
        System.out.println("\n");
        user.printHand();

        //Printing games border
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

    }

    public void printEndGameStats(){
        System.out.println("\nYour collected cards -> ");

        for (int i = 0; i <= user.getCollectedCards().getLastIndex(); i++) {
            System.out.print(user.getCollectedCards().getCard(i).getType());
            System.out.print(user.getCollectedCards().getCard(i).getNum() + " ");
        }
        System.out.println("\n\nComputer collected cards -> ");
        for (int i = 0; i <= computer.getCollectedCards().getLastIndex(); i++) {
            System.out.print(computer.getCollectedCards().getCard(i).getType());
            System.out.print(computer.getCollectedCards().getCard(i).getNum() + " ");
        }
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
        System.out.println("Cards dealt");
    }

    public void takeLeftBehindOnTable(){
        if (user.getUserCollectedLastly()){
            System.out.print("You collected lastly. That's why, ");
            for (int i = 0; i <= tableCardList.getLastIndex(); i++) {
                System.out.print(computer.getCollectedCards().getCard(i).getType());
                System.out.print(computer.getCollectedCards().getCard(i).getNum() + " ");
            }
            System.out.println("will be yours.");
            tableCardList.takeAllCards(user);
        }
        else{
            System.out.print("Computer collected lastly. That's why, ");
            for (int i = 0; i <= tableCardList.getLastIndex(); i++) {
                System.out.print(computer.getCollectedCards().getCard(i).getType());
                System.out.print(computer.getCollectedCards().getCard(i).getNum() + " ");
            }
            System.out.println("will be computer's.");
            tableCardList.takeAllCards(computer);
        }
    }

        //----------GETTERS----------//
    public int getRoundCount(){return roundCount;}

        //----------SETTERS----------//
    public void updateRoundCount(){roundCount += 1;}
    public void updateRoundStep(){eachRoundStep += 1;}
    public void clearRoundStep(){eachRoundStep = 1;}

}

