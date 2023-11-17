import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class CasinoSimulation {
    // List of players
    static List<Player> players = new ArrayList<>();
    // List of matches
    static List<Match> matches = new ArrayList<>();
    // List of illegal operation
    static List<String> illegalAction = new ArrayList<>();

    // Main method
    public static void main(String[] args) {
        Host host = new Host();
        matchDataReader("sample/match_data.txt");
        playerDataExcuter(host,"sample/player_data.txt");
        System.out.println(players.get(0).getBalance());
        System.out.println(players.get(0).winrate());
        System.out.println(host.getCasinoBalance());
    }

    // Based to the UUID, if there's new player, add to the players list
    public static int playerCheck(String playerId) {
        // returns index of the player in the players list
        boolean playerExists = false;
        int count = 0;
        for (Player each : players) {
            if (each.getUuid().equals(playerId)) {
                playerExists = true;
                return count;
            }
            count++;
        }
        if (!playerExists) {
            players.add(new Player(playerId));
        }
        return players.size() - 1;
    }

    // get data from player_data and excute
    // Read the file and excute line by line without storing
    public static void playerDataExcuter(Host host,String fileLocation) {

        try {
            RandomAccessFile file = new RandomAccessFile(fileLocation, "r");
            String str;
            while ((str = file.readLine()) != null) {
                String[] eachPlayer = str.split("[,]", 0);
                // index of current player on players list
                int playerNum = playerCheck(eachPlayer[0]);
                switch (eachPlayer[1]) {
                    case "BET":
                        for (Match eachMatch : matches) {
                            if (eachPlayer[2].equals(eachMatch.getuuid()))
                                if(!eachMatch.matchExcute(
                                        host,
                                        players.get(playerNum),
                                        eachPlayer[2],
                                        Integer.parseInt(eachPlayer[3]),
                                        eachPlayer[4],
                                        eachMatch))
                                        illegalOperationDealer(host, players.get(playerNum), str);
                        }
                        break;
                    case "DEPOSIT":
                        players.get(playerNum).deposit(Integer.parseInt(eachPlayer[3]));
                        break;
                    case "WITHDRAW":
                        if(!players.get(playerNum).withdraw(Integer.parseInt(eachPlayer[3])))illegalOperationDealer(host, players.get(playerNum), str);
                        break;
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read the match data and store in list matches
    public static void matchDataReader(String fileLocation) {
        try {
            RandomAccessFile file = new RandomAccessFile(fileLocation, "r");
            String str;
            // get each line from the file and change the data type and store in matches
            // list as Match
            while ((str = file.readLine()) != null) {
                // split the row with ","
                String[] each = str.split("[,]", 0);
                // add splitted & formatted into the match list
                matches.add(new Match(
                        // UUID, Rate A,Rate B,Match result
                        each[0], Double.parseDouble(each[1]), Double.parseDouble(each[2]), each[3]));
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // handle the illegal operation
    public static void illegalOperationDealer(Host host,Player player, String action) {
        System.out.println("deposit: "+player.getBalance());
        System.out.println("earn:"+ player.getTotalEarn());
        System.out.println(player.winrate());
        System.out.println(player.getUuid());
        System.out.println(players.get(0).getBalance());
        illegalAction.add(action);
        player.getBalance();
        host.setCasinoBalance(host.getCasinoBalance()+player.getTotalEarn());
        System.out.println("host balance: "+host.getCasinoBalance());
    }

    // write output
    public static void resultPrinter() {

    }
}