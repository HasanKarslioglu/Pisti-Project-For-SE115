import java.util.Scanner;
import java.util.Random;

public class Main {

    //Scanner and random object produced one times for the game
    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random(System.currentTimeMillis());


    static CardList unDistributedCardList = new CardList(52, rnd, sc);
    static CardList tableCards = new CardList(52, rnd, sc);
    static Player user = new Player("Hasan");
    static Player computer = new Player("Computer");
    static GameMode gamemode = new GameMode(unDistributedCardList, tableCards, user, computer);

    public static void startGame(){

        //Asking and setting UserName
        System.out.println("Please Enter Your Name");
        String name = sc.nextLine();
        user.setName(name);

        unDistributedCardList.fillDeck();
        unDistributedCardList.shuffleDeck();
        unDistributedCardList.cutDeck();

        gamemode.dealCards();
        gamemode.printRound();

        //Setting ComputerName
        computer.setName("Computer");
    }

    public static void loopGame(){

    }

    public static void endGame(){

    }
    public static void main(String[] args) {
        startGame();
        loopGame();
        endGame();
    }
}










