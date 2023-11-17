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
    public void matchExcute(Host host, Player player, String matchUuid, int bet, String BetOn, Match match) {
        // return true if there's no problem, if not, return false
        if (player.getBalance() < bet) {
            // illegal operation
        } else {
            // legal operation
            player.bet(bet);
            player.matchCouont();
            if (match.result.equals(BetOn)) {
                // if you win
                player.setTotalWin(player.getTotalWins() + 1);
                if (match.result == "A") {
                    // if you win & bet on A
                    // calculated reward will be added to the players balance
                    int reward = (int) (player.getBalance() - bet + bet * match.getRateA());
                    player.setBalance(reward);
                    player.setTotalEarn(player.getTotalEarn()+reward);
                    // withdraw coin from casino host balance
                    host.setCasinoBalance((int) (host.getCasinoBalance() - bet * match.getRateA()));
                } else {
                    // if you win & bet on B
                    // calculated reward will be added to the players balance
                    int reward = (int) (player.getBalance() - bet + bet * match.getRateB());
                    player.setBalance(reward);
                    player.setTotalEarn(player.getTotalEarn()+reward);
                    // withdraw coin from casino host balance
                    host.setCasinoBalance((int) (host.getCasinoBalance() - bet * match.getRateA()));
                }
            } else if (!match.result.equals(BetOn)) {
                // if you lose
                // withdraw coin from players balance
                player.setBalance(player.getBalance() - bet);
                player.setTotalEarn(player.getTotalEarn()+bet);
                // add coin to the casino host balance
                host.setCasinoBalance(host.getCasinoBalance() + bet);
            }
        }
    }
}
