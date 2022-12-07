public class GameMode {
    private int raundCount = 1;

    public void dealCards(Deck unDistributedDeck, Deck tableDeck, Player user, Player computer){
        //If raundCount is 0, it means its first dealing. That's why, we have to deal to tableDeck too
        if (raundCount == 0){
            for (int i = 0; i < 4; i++) {
                tableDeck.addCard(unDistributedDeck.removeCard());
                user.handDeck.addCard(unDistributedDeck.removeCard());
                computer.handDeck.addCard(unDistributedDeck.removeCard());
            }
        }
        //If raundCount is 1, it means its not first dealing. That's why,
        // we have to deal to just user and computer not tableDeck
        else {
            for (int i = 0; i < 4; i++) {
                user.handDeck.addCard(unDistributedDeck.removeCard());
                computer.handDeck.addCard(unDistributedDeck.removeCard());
            }
        }
    }


        //----------GETTERS----------//
    public int getRaundCount(){return raundCount;}

        //----------SETTERS----------//
    public void setRaundCount(int raundCount){this.raundCount = raundCount;}
}
