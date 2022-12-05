public class Card {

    //------DEFINATIONS---------//
    private char num;
    private char type;

    //----------CONSTRUCTORS----------//
    Card(char num, char type){
        this.num = num;
        this.type = type;
    }
    Card(){
        num = '0';
        type = '0';
    }

    //------GETTERS---------//
    public char getNum() {return num;}
    public char getType() {return type;}

    //------SETTERS---------//
    public void setNum(char num) {this.num = num;}
    public void setType(char type) {this.type = type;}
}












