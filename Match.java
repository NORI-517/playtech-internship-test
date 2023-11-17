import java.util.ArrayList;
import java.util.List;

public class Match {
    private String uuid;
    private double rateA;
    private double rateB;
    private String result;

    public Match(String uuid, double rateA, double rateB, String result) {
        this.uuid = uuid;
        this.rateA = rateA;
        this.rateB = rateB;
        this.result = result;
    }

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
    public void matchExcute(Player player, String matchUuid, int bet, String BetOn, Match match) {
        if (player.getBalance() < bet) {
            System.out.println("illegal activity");
        } else {
            if (match.result.equals(BetOn)) {
                if (match.result == "A") {
                    int reward = (int) (player.getBalance() - bet + bet * match.getRateA());
                    player.setBalance(reward);
                } else {
                    int reward = (int) (player.getBalance() - bet + bet * match.getRateB());
                    player.setBalance(reward);
                }
                ;
            } else if (!match.result.equals(BetOn)) {
                player.setBalance(player.getBalance() - bet);
            }
        }
    }
}
