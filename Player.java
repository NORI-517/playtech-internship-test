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
            // illegal operation
            // incomplete
        }
    }

    public void reward(long reward) {
        this.balance += reward;
    }

    public void loss(long loss) {
        this.balance -= loss;
    }
    /* I separated deposit, withdraw, reward, loss because they should have 
     * exchange rate between in game coin and real life currency
     */
}