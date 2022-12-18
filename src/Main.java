import java.util.Scanner;
import java.util.Random;

public class Main {

    //Scanner and random object produced one times for the game
    private static Scanner sc = new Scanner(System.in);
    private static Random rnd = new Random(System.currentTimeMillis());


    private static CardList unDistributedCardList = new CardList(52, rnd, sc);
    private static CardList tableCards = new CardList(52, rnd, sc);
    private static Player user = new Player("Hasan", tableCards, sc);
    private static Player computer = new Player("Computer", tableCards, sc);
    private static GameMode gamemode = new GameMode(unDistributedCardList, tableCards, user, computer);

    public static void startGame(){

        //Asking and setting UserName
        //System.out.println("Please Enter Your Name");
        //String name = sc.nextLine();
        //user.setName(name);

        unDistributedCardList.fillDeck();
        unDistributedCardList.shuffleDeck();
        //unDistributedCardList.cutDeck();


        //Setting ComputerName
        computer.setName("Computer");
    }

    public static void loopGame(){

        while (gamemode.getRoundCount() < 6){

            gamemode.updateRoundCount();
            gamemode.dealCards();
            gamemode.printRound();

            for (int i = 0; i < 4; i++) {
                if (unDistributedCardList.getIsUserCutDeck()) {
                    computer.playCard();
                    user.playCard();
                }else {
                    user.playCard();
                    computer.playCard();
                }
                gamemode.printRound();

            }
        }
    }

    public static void endGame(){

    }
    public static void main(String[] args) {
        startGame();
        loopGame();
        endGame();
    }
}










