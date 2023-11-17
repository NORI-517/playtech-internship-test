public class Player {
    private String uuid;
    private long balance;
    private int totalBets;
    private int totalWins;

    public Player(String uuid) {
        this.uuid = uuid;
        this.balance = 0;
    }
    public String getUuid(){
        return this.uuid;
    }
    public long getBalance(){
        return this.balance;
    }
    public void setBalance(long balance){
        this.balance = balance;
    }
    void bet() {

    }

    public void deposit(long addMoney) {
        // coin and real money are same rate? I've never been casino:o
        this.balance += addMoney;
    }

    public void withdraw(long subtractMoney) {
        if (balance <= subtractMoney) {
            // legal operation
            this.balance -= subtractMoney;
        } else {
            System.out.println("illegal activity");
            // illegal operation
            // incomplete
        }
    }
}