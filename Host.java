public class Host {
    private static long casinoBalance = 0;

    public long getCasinoBalance() {
        return this.casinoBalance;
    }

    public void setCasinoBalance(long casinoBalance) {
        this.casinoBalance = casinoBalance;
    }
    public void resetHostStats(){
        this.casinoBalance = 0;
    }
    public void balanceManager(int value){
        this.casinoBalance += value;
    }
}
