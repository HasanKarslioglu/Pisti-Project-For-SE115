
import java.util.Scanner;

public class Main {

    static Deck unDistributedDeck = new Deck(52);
    static Deck tableDeck = new Deck(52);
    static Player user = new Player();
    static Player computer = new Player();

    public static void startGame(){
        //Making Decks
        unDistributedDeck.fillDeck();
        unDistributedDeck.ShuffleDeck();

        //Asking UserName
        System.out.println("Please Enter Your Name");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        user.setName(name);

        //Setting ComputerName
        computer.setName("Computer");
    }
    public static void main(String[] args) {
        startGame();
    }
}
