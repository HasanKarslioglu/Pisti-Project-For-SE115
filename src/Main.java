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
        System.out.println("Please enter your name.");
        String name = sc.nextLine();
        user.setName(name);

        //Setting ComputerName
        computer.setName("Computer");

        unDistributedCardList.fillDeck();
        unDistributedCardList.shuffleDeck();
        unDistributedCardList.cutDeck();
    }

    public static void loopGame(){

        while (gamemode.getRoundCount() < 6){

            gamemode.updateRoundCount();
            gamemode.clearRoundStep();
            gamemode.dealCards();
            gamemode.printRound();

            for (int i = 0; i < 4; i++) {
                if (unDistributedCardList.getWasUserCutDeck()) {
                    computer.playCard();
                    gamemode.updateRoundStep();
                    gamemode.printRound();
                    user.playCard();
                }else {
                    user.playCard();
                    gamemode.updateRoundStep();
                    gamemode.printRound();
                    computer.playCard();
                }
                gamemode.updateRoundStep();
                gamemode.printRound();
            }
        }
    }

    public static void endGame(){
        System.out.println("\n\n\nHere is the end of the game!!!");
        System.out.println("--------------------------------------------------------------------------------");

        gamemode.takeLeftBehindOnTable();
        gamemode.printEndGameStats();
    }
    public static void main(String[] args) {
        startGame();
        loopGame();
        endGame();
    }
}










