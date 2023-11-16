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

    public void matchExcute(Match match, Player player) {
        
    }

    // show the whole match info
    public void show() {
        System.out.println(
                this.uuid + ", " +
                        this.rateA + ", " +
                        this.rateB + ", " +
                        this.result);
    }
}
