public class Main {

    static Deck unDistributedDeck = new Deck(52);
    static Deck tableDeck = new Deck(52);
    static Player user = new Player("Hasan");
    static Player computer = new Player("Computer");
    static GameMode gamemode = new GameMode(unDistributedDeck, tableDeck, user, computer);

    public static void startGame(){

        unDistributedDeck.fillDeck();
        unDistributedDeck.shuffleDeck();
        gamemode.dealCards();
        gamemode.printRound();

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










