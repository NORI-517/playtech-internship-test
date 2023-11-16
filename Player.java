public class Player {
    private String uuid;
    private long balance;
    private int totalBets;
    private int totalWins;

    public Player(String uuid) {
        this.uuid = uuid;
        this.balance = 0;
    }

    void bet() {

    }

    public void deposit(long addMoney) {
        this.balance += addMoney;
    }

    public void withdraw(long subtractMoney) {
        if(balance <= subtractMoney){
            //legal operation
            this.balance -= subtractMoney;
        }else{
            //illegal operation
            //incomplete
        }
        
    }
}