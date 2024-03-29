import java.io.File;
import java.util.Scanner;
import java.util.Formatter;


public class GameMode {

                //--------DEFINITIONS-----------//
    private int roundCount = 0;
    private int eachRoundStep = 1;

    //We need to know reference of deck, tableCardList, computer and user.
    //Then, we can add or remove card to what we want.
    private CardList deck;
    private CardList tableCardList;
    private Player computer;
    private Player user;

        //--------------CONSTRUCTOR--------------//
    GameMode(CardList deck, CardList tableCardList, Player user, Player computer){
        //Following lines stores references.
        //REMINDING -> They are not copy of arrays. Thanks to that way if we..
        //..change anything on the array the original one will change.
        this.deck = deck;
        this.tableCardList = tableCardList;
        this.computer = computer;
        this.user = user;
    }

        //--------------METHODS--------------//
    public void printRound(){

        //Printing round count and games border.
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬ Round "+ roundCount +" --- Step "+ eachRoundStep +" ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        //Printing ----COMPUTER HAND----
        computer.printHand();
        System.out.println();

        //Printing ----TABLE----
        System.out.print("Table Deck ▶ ");
        if (tableCardList.getCard(0).getType() != 'x')
        {
            //Printing top card's type and num.
            System.out.print("\t\t\t\t\t" +tableCardList.getCard(tableCardList.getLastIndex()).getType());
            System.out.println(tableCardList.getCard(tableCardList.getLastIndex()).getNum());
            System.out.print("\t\t\t\t\t\t\t");

            //Printing others board cards.
            for (int i = 0; i < (tableCardList.getLastIndex()); i++){
                System.out.print(tableCardList.getCard(i).getType());
                System.out.print(tableCardList.getCard(i).getNum() + " ");
            }
        }

        //Printing ----USER HAND----
        System.out.println("\n");
        user.printHand();

        //Printing games border.
        System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
    }

    public void printEndGameStats(){

        System.out.println("\nYour collected cards -> ");

        //Printing user collected cards (Types and numbers).
        for (int i = 0; i <= user.getCollectedCards().getLastIndex(); i++) {
            System.out.print(user.getCollectedCards().getCard(i).getType());
            System.out.print(user.getCollectedCards().getCard(i).getNum() + " ");
        }

        //Printing user's number of pistis.
        System.out.println("\nYour pisti's count: " + user.getPistiCount());

        //----------------------------------------------------------------------------------------------------

        System.out.println("\nComputer collected cards -> ");
        //Printing computer collected cards (Types and numbers).
        for (int i = 0; i <= computer.getCollectedCards().getLastIndex(); i++) {
            System.out.print(computer.getCollectedCards().getCard(i).getType());
            System.out.print(computer.getCollectedCards().getCard(i).getNum() + " ");
        }
        //Printing computer's number of pistis.
        System.out.println("\nComputer pisti's count: " + computer.getPistiCount());
    }

    public void dealCards(){
        //If roundCount is 1, it means we are in the first dealing. That's why, we have to deal to tableDeck too.
        if (roundCount == 1){
            for (int i = 0; i < 4; i++) {
                //Dealing for table.
                tableCardList.addCard(deck.removeCard());
                //Dealing for user.
                user.getHandCards().addCard(deck.removeCard());
                //Dealing for computer.
                computer.getHandCards().addCard(deck.removeCard());
            }
        }

        //If roundCount is greater than 1, it means it's not first dealing. That's why,
        //we have to deal to just user and computer. Not tableDeck.
        else {
            for (int i = 0; i < 4; i++) {
                //Dealing for user.
                user.getHandCards().addCard(deck.removeCard());
                //Dealing for computer.
                computer.getHandCards().addCard(deck.removeCard());
            }
        }
        System.out.println("Cards dealt.");
    }

    public void takeRemainingCardsOnBoard(){

        //That if block will be executed if user collected lastly.
        if (user.getUserCollectedLastly()){
            //Printing user name + information message.
            System.out.print(user.getName() + "collected lastly. That's why, ");

            //That for loop prints all cards which remaining on the board.
            for (int i = 0; i <= tableCardList.getLastIndex(); i++) {
                System.out.print(tableCardList.getCard(i).getType());           //Printing card type.
                System.out.print(tableCardList.getCard(i).getNum() + " ");      //Printing card number.
            }
            System.out.println("will be yours.");
            tableCardList.takeAllCards(user);                           //It transfers all cards to user.
        }
        //That else block will be executed if computer collected lastly.
        else{
            //Printing computer name + information message.
            System.out.print("Computer collected lastly. That's why, ");

            //That for loop prints all cards which remaining on the board.
            for (int i = 0; i <= tableCardList.getLastIndex(); i++) {
                System.out.print(tableCardList.getCard(i).getType());           //Printing card type.
                System.out.print(tableCardList.getCard(i).getNum() + " ");      //Printing card number.
            }
            System.out.println("will be computer's.");
            tableCardList.takeAllCards(computer);                       //It transfers all cards to user.
        }
    }

    public void calculatePlayerScore(Player player){


        player.addScore(player.getCollectedCards().getLastIndex()+1);   //It calculates others cards(cards with 1 point)

        player.addScore(player.getPistiCount() * 8);                    //It calculates pistis points (It is 8 because..
                                                                        //..they already were added 2 point on..
                                                                        //..previous step because of they are 2 card).

        if (doesHaveDiamonds10(player.getCollectedCards())){            //If collectedCards includes ♦10 we must add..
            player.addScore(2);                                         //..3 point. But on the previous we already..
        }                                                               //..added 1 point each card include ♦10..
                                                                        //That's why, it will add just 2 point.

        if (doesHaveClubs2(player.getCollectedCards())){                //If collectedCards includes ♣2 we must add..
            player.addScore(1);                                         //..2 point. But on the previous we already..
        }                                                               //..added 1 point each card include ♣2.
    }                                                                   //That's why, it will add just 1 point.
    private boolean doesHaveDiamonds10(CardList cards){

        //Following for loop checks if there is diamonds 10 card in the collected cards.
        for (int i = 0; i <= cards.getLastIndex(); i++) {
            //If there is, it returns true. If it can't find diamond 10 it returns false.
            if (cards.getCard(i).getNum() == '0' && cards.getCard(i).getType() == '♦') return true;
        }
        return false;
    }
    private boolean doesHaveClubs2(CardList cards){
        //Following for loop checks if there is clubs 2 card in the collected cards.
        for (int i = 0; i <= cards.getLastIndex(); i++) {
            //If there is, it returns true. If it can't find clubs 2 it returns false.
            if (cards.getCard(i).getNum() == '2' && cards.getCard(i).getType() == '♣') return true;
        }
        return false;
    }
    public void addScoreWhoseCardIsMore(){

        //Following if block and else if block checks which cards are more than others.
        if (user.getCollectedCards().getLastIndex() > computer.getCollectedCards().getLastIndex())
            //If user cards are more than computers, it adds 3 point to user.
            user.addScore(3);
        else if (user.getCollectedCards().getLastIndex() < computer.getCollectedCards().getLastIndex())
            //If computer cards are more than user, it adds 3 point to computer.
            computer.addScore(3);
   }


            //--------TOP 10 LIST CODE-----------//
    public void saveScoresToFile(Player winner){

        //That string array is going to be store scores of the players by reading file.
        String[] scores = new String[11];

        System.out.println("\n--------OLD LIST----------");
        readFile(scores);                                                //Reading file

        System.out.println("--------NEW LIST----------");
        organizeScoreArray(winner, scores);                              //Organizing file

        writeFile(scores);                                               //Writing file
        System.out.println("New list has been written to text file successfully.");
    }

    private void readFile(String[] scores){

        File scoresFile;
        Scanner reader = null;

       try{
           scoresFile = new File("Scores.txt");

           //If Scores.txt not exist it will create new one.
           if (!scoresFile.exists())
               scoresFile.createNewFile();

           reader = new Scanner(scoresFile);

           int numbersOfItems = 0;
           //It stores scores to string array line by line.
           while (reader.hasNextLine()){
               scores[numbersOfItems] = reader.nextLine();
               numbersOfItems++;
           }

       }catch (Exception e){
           System.out.println("Scores.txt not found...");

       }finally {
           if (reader != null)
               reader.close();
       }

        //Printing stored list (Not organized one).
        for (int i = 0; i < 11; i++) {
           if (scores[i] != null)
               System.out.println(scores[i]);
       }
   }

   private void organizeScoreArray(Player winner, String[] scores){

       //Following for loop determines how many score in the array and then stores to lastIndex variable.
       int lastIndex = 0;
       for (int i = 0; i < scores.length; i++) {
           if (scores[i] == null){
               lastIndex = i - 1;
               break;
           }
       }

       boolean isPlaced = false;


       //Following lines organises array based on players score.
       for (int i = 0; i <= lastIndex; i++) {
           //String array includes line of text. And that line have 2 part.
           //The first part is player's name, second part is player's score.
           //We need to split the second part and store to current score.
           int currentScore = Integer.parseInt(scores[i].split(" ")[1]);
           //If winner score is more than current score it will scroll the below to one bottom line
           if (winner.getScore() > currentScore) {
               for (int j = lastIndex; j >= i; j--) {
                   scores[j + 1] = scores[j];
               }
               scores[i] = winner.getName() + " " + winner.getScore();
               isPlaced = true;
               lastIndex++;
               break;
           }
       }


       //If it couldn't find any place to store, it will be stored the last index of array.
       if (!isPlaced){
            scores[lastIndex + 1] = winner.getName() + " " + winner.getScore();
       }

       //Printing stored list (Organized one).
       for (int i = 0; i < 10; i++) {
           if (scores[i] == null) break;
           System.out.println(scores[i]);
       }
   }

   private void writeFile(String[] scores){

       Formatter f = null;
       try {
           f = new Formatter("Scores.txt");
           //It writes to Scores.txt line by line.
           for (int i = 0; i < 10; i++) {
               if (scores[i] == null) break;
               f.format(scores[i] + "\n");
           }
       } catch (Exception e) {
           System.err.println("Something went wrong.");
       } finally {
           if (f != null) {
               f.close();
           }
       }
   }


        //----------GETTERS----------//
    public int getRoundCount(){return roundCount;}          //Returning round count.

        //----------SETTERS----------//
    public void updateRoundCount(){roundCount += 1;}        //Incrementing round count.
    public void updateRoundStep(){eachRoundStep += 1;}      //Incrementing round step count.
    public void clearRoundStep(){eachRoundStep = 1;}        //It clears round step count.

}

