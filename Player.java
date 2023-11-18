//import java.lang.Math;
public class Player {
    private String uuid;
    private long balance;
    private int totalWins;
    private int totalMatchs;

    // Constructor
    public Player(String uuid) {
        this.uuid = uuid;
        this.balance = 0;
        this.totalWins = 0;
        this.totalMatchs = 0;
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

    public int getTotalMatchs() {
        return this.totalMatchs;
    }
    public void winCounter(){
        this.totalWins++;
    }
    //Deposit money to the account
    public void deposit(long addMoney) {
        // coin and real money are same rate? I've never been casino
        this.balance += addMoney;
    }

    //Withdraw money from the account
    // return false if there's illegal operation, if not, return true
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
    public void balanceManager(int value){
        this.balance += value;
    }

    // Count total match the player played
    public void matchCouont() {
        this.totalMatchs += 1;
    }

    public double winrate(){
        return (double)((int)((double)this.totalWins/this.totalMatchs*100))/100;
        //This doesnt work somehow Math.round((double)this.totalWins/this.totalMatchs*100)/100;
    }

    public void resetPlayerStats(){
        this.balance = 0;
        this.totalWins = 0;
        this.totalMatchs = 0;
    }
}