import java.util.Scanner;

public class Player {

        //------DEFINATIONS---------//
    //Users score
    private int score = 0;
    //Users name
    private String name = "NULL";

    //Cards which current cards
    private CardList handCards = new CardList(4);
    //Cards which earned by the user
    public CardList collectedCards = new CardList(52);
    private Scanner sc;

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
            //Following else's lines will print if it is computer
        }else {
            for (int i = 0; i < 4; i++) {
                if (handCards.getCard(i).getType() == 'x'){
                    System.out.print("▱ ");
                }else{
                    System.out.print(handCards.getCard(i).getType());
                    System.out.print(handCards.getCard(i).getNum());
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    public void playCard(){
        if (name.equals("Computer"))
            computerPlayCard();
        else
            userPlayCard();
    }

    private void computerPlayCard(){
        tableCards.addCard(handCards.removeCard());
    }
    private void userPlayCard(){
        int choose;
        System.out.println("Please Enter A Number To Play");
        while(true){
            choose = sc.nextInt();
            if (choose <= 4 && choose >= 1 && handCards.getCard(choose-1).getType() != 'x') break;
            System.out.println("Please enter valid number (Between 1-4)");
        }
        tableCards.addCard(handCards.removeCard(choose-1));
    }

        //--------GETTERS---------//
    public int getScore(){return score;}

        //--------SETTERS---------//
    public void setScore(int score){this.score = score;}
    public void setName(String name){this.name = name;}

    public CardList getHandCards() {return handCards;}

    public void setHandCards(CardList handCards) {this.handCards = handCards;}
}
