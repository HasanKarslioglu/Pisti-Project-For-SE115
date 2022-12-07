public class Player {

        //------DEFINATIONS---------//
    //Users score
    private int score = 0;
    //Users name
    private String name = "NULL";

    //Cards which current cards
    public Deck handDeck = new Deck(4);
    //Cards which earned by the user
    public Deck collectionDeck = new Deck(52);


        //--------GETTERS---------//
    public int getScore(){return score;}
    public String getName(){return name;}

        //--------SETTERS---------//
    public void setScore(int score){this.score = score;}
    public void setName(String name){this.name = name;}
}
