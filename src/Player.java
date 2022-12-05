public class Player {
    private int score = 0;
    private String name = "NULL";

    public Deck handDeck = new Deck(4);
    public Deck collectionDeck = new Deck(52);


    //--------GETTERS---------//
    public int getScore(){return score;}
    public String getName(){return name;}

    //--------SETTERS---------//
    public void setScore(int score){this.score = score;}
    public void setName(String name){this.name = name;}
}
