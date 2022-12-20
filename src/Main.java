import java.util.Scanner;
import java.util.Random;

public class Main {

                        //--------------DEFINITIONS--------------//
    //Scanner and random object created once for the game.
    //Random class were seeded with current time of computer due to more randomization.
    private static Scanner sc = new Scanner(System.in);
    private static Random rnd = new Random(System.currentTimeMillis());

    //Undistributed deck were created with length of 52.
    private static CardList deck = new CardList(52, rnd, sc);
    //Cards of table were created
    private static CardList tableCards = new CardList(52, rnd, sc);


    //Player class includes name, score, pisti count, hand cards and collected cards.
    //2 player were created for the user and computer.
    private static Player user = new Player(tableCards, sc);
    private static Player computer = new Player(tableCards, sc);


    //Gamemode were created. It is going to handle all stuff about how it works of game..
    //..such as printing round, dealing cards, calculating score and etc...
    private static GameMode gamemode = new GameMode(deck, tableCards, user, computer);



                    //--------------FUNCTIONS--------------//
    public static void startGame(){

        //Asking UserName.
        System.out.println("Please enter your name.");
        String name = sc.nextLine();
        //Setting UserName.
        user.setName(name);
        //Setting ComputerName
        computer.setName("Computer");


        deck.fillDeck();            //Deck is going to be 'filled'.
        deck.shuffleDeck();         //Deck is going to be 'shuffled'.
        deck.cutDeck();             //Deck is going to be 'cut'. That method will ask..
                                    //..is going to user or computer cut the deck.
    }

    public static void loopGame(){

        //LoopGame function will work until end of the game.
        while (gamemode.getRoundCount() < 6){

            gamemode.updateRoundCount();
            gamemode.clearRoundStep();
            gamemode.dealCards();
            gamemode.printRound();

            for (int i = 0; i < 4; i++) {
                if (deck.getWasUserCutDeck()) {
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

        gamemode.calculatePlayerScore(user);
        gamemode.calculatePlayerScore(computer);
        gamemode.addScoreWhoseCardIsMore();
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Your Score = " + user.getScore());
        System.out.println("Computer Score = " + computer.getScore());


    }

            //--------------MAIN FUNCTION--------------//

    //The program will execute this function firstly.
    public static void main(String[] args) {
        startGame();
        loopGame();
        endGame();
    }
}










