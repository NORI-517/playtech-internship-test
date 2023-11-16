public class test {
    public static void main(String[]args){
        Player player1 = new Player();
        Player player2 = new Player();
        Match round1 = new Match(player1,player2);

    }
}
class Player{
    int bet;
    public Player(){
    }
}
class Host{
    
}

class Match{
    Player sideA;
    Player sideB;
    Match(Player sideA, Player sideB){
        this.sideA = sideA;
        this.sideB = sideB;
    }

}
