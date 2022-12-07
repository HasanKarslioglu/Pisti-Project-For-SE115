
import java.util.Scanner;

public class Main {

    static GameMode gamemode = new GameMode();
    static Deck unDistributedDeck = new Deck(52);
    static Deck tableDeck = new Deck(52);
    static Player user = new Player();
    static Player computer = new Player();
    static int raundCount = 0;

    public static void startGame(){

        unDistributedDeck.fillDeck();
        unDistributedDeck.shuffleDeck();
        gamemode.dealCards(unDistributedDeck, tableDeck, user, computer);



        //unDistributedDeck.cutDeck();
        /*
        //Asking UserName
        System.out.println("Please Enter Your Name");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        user.setName(name);

        //Setting ComputerName
        computer.setName("Computer");
        */

        //Asking cut.
    }

    public static void main(String[] args) {
        startGame();

    }
}










