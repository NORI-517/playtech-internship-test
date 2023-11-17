import java.util.ArrayList;

public class Player {
    private String uuid;
    private long balance;
    private int totalBets;
    private int totalWins;
    private int totalMatchs;
    private int totalEarn;

    // Constructor
    public Player(String uuid) {
        this.uuid = uuid;
        this.balance = 0;
        this.totalBets = 0;
        this.totalWins = 0;
        this.totalMatchs = 0;
        this.totalEarn = 0;
    }

    // getter and setter
    public String getUuid() {
        return this.uuid;
    }

    public long getBalance() {
        return this.balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getTotalWins() {
        return this.totalWins;
    }

    public void setTotalWin(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalEarn() {
        return this.totalEarn;
    }

    public void setTotalEarn(int earn) {
        this.totalEarn = earn;
    }

    public void deposit(long addMoney) {
        // coin and real money are same rate? I've never been casino
        this.balance += addMoney;
    }

    public boolean withdraw(long subtractMoney) {
        if (balance >= subtractMoney) {
            // legal operation
            this.balance -= subtractMoney;
            return true;
        } else {
            // illegal operation
            return false;
        }
    }

    // Count total bet
    public void bet(int bet) {
        totalBets += bet;
    }

    // Count total match the player played
    public void matchCouont() {
        this.totalMatchs += 1;
    }
}