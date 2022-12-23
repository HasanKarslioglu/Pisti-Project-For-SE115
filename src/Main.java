import java.util.Scanner;
import java.util.Random;

public class Main {

                        //--------------DEFINITIONS--------------//
    //Scanner and random object created once for the game.
    //Random class were seeded with current time of computer due to more randomization.
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rnd = new Random(System.currentTimeMillis());


    //Undistributed deck were created with length of 52.
    private static CardList deck = new CardList(52, rnd, sc);
    //Cards of table were created
    private static CardList tableCards = new CardList(52, rnd, sc);


    //Player class includes name, score, pisti count, hand cards and collected cards.
    //2 player were created for the user and computer.
    private static Player user = new Player(tableCards, sc);
    private static Player computer = new Player(tableCards, sc);


    //GameMode were created. It is going to handle all stuff about how it works of game..
    //..such as printing round, dealing cards, calculating score and etc...
    private static GameMode gamemode = new GameMode(deck, tableCards, user, computer);



                    //--------------FUNCTIONS--------------//
    public static void startGame(){

        System.out.println("Please follow the game step by step!!");

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

            gamemode.updateRoundCount();            //Updates round count.
            gamemode.clearRoundStep();              //It clears round step count (For more information..
                                                    //..go to eachRoundStep variable where on the GameMode class.)

            gamemode.dealCards();                   //Dealing cards to players.
                                                    //If it is first round, it deals table too.

            gamemode.printRound();                  //Printing round.

            //That for is root of game loop and each round players must plays 4 card.
            //That's why, number of loop is 4.
            for (int i = 0; i < 4; i++) {
                if (deck.getWasUserCutDeck()) {     //If block execute if user cut the deck.
                    computer.playCard();            //Computer plays first.
                    gamemode.updateRoundStep();
                    gamemode.printRound();
                    user.playCard();                //User plays second.
                }else {                             //Else block execute if computer cut the deck
                    user.playCard();                //User plays first
                    gamemode.updateRoundStep();
                    gamemode.printRound();
                    computer.playCard();            //Computer plays second.
                }
                gamemode.updateRoundStep();         //Updating round step
                gamemode.printRound();
            }
        }
    }

    public static void endGame(){
        System.out.println("\n\n\nHere is the end of the game!!!");
        System.out.println("--------------------------------------------------------------------------------");

        gamemode.takeRemainingCardsOnBoard();           //It takes remaining cards on the board.
        gamemode.printEndGameStats();                   //It prints stats of the finished game.
                                                        //Such as each player collected cards, number of pistis.
        gamemode.calculatePlayerScore(user);            //It calculates player score.
        gamemode.calculatePlayerScore(computer);        //It calculates computer score.
        gamemode.addScoreWhoseCardIsMore();             //It adds 3 point to whose cards is more than others.

        //Following lines prints players scores.
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










