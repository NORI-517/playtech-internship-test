import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class CasinoSimulation {
    // List of players
    static List<Player> players = new ArrayList<>();
    // List of matches
    static List<Match> matches = new ArrayList<>();
    // Casino host balance
    long casinoBalance = 0;

    public static void main(String[] args) {
        matchDataReader("sample/match_data.txt");
        playerDataReader("sample/player_data.txt");
    }

    public static int playerCheck(String playerId) {
        // Based to the UUID, if there's new player, add to the players list
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

    public static void playerDataReader(String fileLocation) {
        // get data from player_data and excute
        try {
            RandomAccessFile file = new RandomAccessFile(fileLocation, "r");
            String str;
            while ((str = file.readLine()) != null) {
                String[] each = str.split("[,]", 0);
                // index of current player on players list
                int playerNum = playerCheck(each[0]);
                switch (each[1]) {
                    case "BET":

                        break;
                    case "DEPOSIT":
                        players.get(playerNum).deposit(Integer.parseInt(each[3]));
                        break;
                    case "WITHDRAW":
                        players.get(playerNum).withdraw(Integer.parseInt(each[3]));
                        break;
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}