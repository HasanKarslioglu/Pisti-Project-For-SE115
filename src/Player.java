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

    Player(String name){
        this.name = name;
    }

    public void printHand(){

        System.out.print( name + " ▶ \t\t\t\t\t");

        //Following if's lines will print if it is computer
        if (name.equals("Computer")){
            for (int i = 0; i < (handDeck.getLastIndex()); i++) {
                //Computers hand must be hidden
                System.out.print("▰ ");
            }
            for (int i = 0; i < (3 - (handDeck.getLastIndex())); i++) {
                //That for loop prints computer already discarded cards
                System.out.print("▱ ");
            }
            //Following else's lines will print if it is computer
        }else {
            for (int i = 0; i < handDeck.getLastIndex(); i++) {
                System.out.print(handDeck.getCard(i).getType() + "" + handDeck.getCard(i).getNum() + " ");
            }
            for (int i = 0; i < (3 - handDeck.getLastIndex()); i++) {
                System.out.print("▱ ");
            }
        }

        System.out.println();
    }

        //--------GETTERS---------//
    public int getScore(){return score;}
    public String getName(){return name;}

        //--------SETTERS---------//
    public void setScore(int score){this.score = score;}
    public void setName(String name){this.name = name;}
}
