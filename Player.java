public class Player {
    private String id;
    private long balance;
    private int totalBets;
    private int totalWins;

    public Player(){
        this.balance = 0;
    }

    void bet(){
        totalBets++;
    }
    void deposit(){

    }
    void withdraw(){

    }
}