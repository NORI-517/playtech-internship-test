public class Match {
    private String id;
    private double rateA;
    private double rateB;
    private String result;

    public Match(String id, double rateA, double rateB, String result){
        this.id = id;
        this.rateA = rateA;
        this.rateB = rateB;
        this.result = result;
    }
    /* 
    public void show(){
        System.out.println(
            this.id+", "+
            this.rateA+", "+
            this.rateB+", "+
            this.result
        );
    }
    */
}
