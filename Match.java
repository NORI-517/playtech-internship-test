public class Match {
    private String uuid;
    private double rateA;
    private double rateB;
    private String result;

    // Constructor
    public Match(String uuid, double rateA, double rateB, String result) {
        this.uuid = uuid;
        this.rateA = rateA;
        this.rateB = rateB;
        this.result = result;
    }

    // getter and setter
    public String getuuid() {
        return this.uuid;
    }

    public String getResult() {
        return this.result;
    }

    public double getRateA() {
        return this.rateA;
    }

    public double getRateB() {
        return this.rateB;
    }

    // excute the match
    public boolean matchExcute(Host host, Player player, String matchUuid, int bet, String BetOn, Match match) {
        // return true if there's no problem, if not, return false
        if (player.getBalance() < bet) {
            // illegal operation
            return false;
        } else {
            // legal operation
            player.bet(bet);
            player.matchCouont();
            if (match.result.equals(BetOn)) {
                // if you win
                player.setTotalWin(player.getTotalWins() + 1);
                if (match.result.equals("A")) {
                    // if you win & bet on A
                    // calculated reward will be added to the players balance
                    int value = (int)(bet * match.getRateA());
                    player.balanceManager(value);
                    // withdraw coin from casino host balance
                    host.setCasinoBalance(-value);
                } else {
                    // if you win & bet on B
                    // calculated reward will be added to the players balance
                    int value = (int)(bet * match.getRateB());
                    player.balanceManager(value);
                    // withdraw coin from casino host balance
                    host.balanceManager(-value);;
                }
            } else if (match.result.equals("DRAW")) {
            } else {
                // if you lose
                // withdraw coin from players balance
                player.balanceManager(-bet);
                // add coin to the casino host balance
                host.balanceManager(bet);
            }
        }
        return true;
    }
}
