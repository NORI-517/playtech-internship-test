import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CasinoSimulation {
    static List<Player> players = new ArrayList<>();
    static List<Match> matches = new ArrayList<>();
    static HashMap<Player, String> illegalOperation = new HashMap<>();
    static String matchfileLocation = "sample/match_data.txt";
    static String player_dataLocation = "sample/player_data.txt";
    static Host host = new Host();

    // Main method
    public static void main(String[] args) {
        matchDataReader(matchfileLocation);
        playerDataExcuter(host, player_dataLocation);
        resultPrinter();
    }

    // get data from player_data and excute
    // Read the file and excute line by line without storing
    static int count = 1;

    public static void playerDataExcuter(Host host, String fileLocation) {
        try {
            RandomAccessFile file = new RandomAccessFile(fileLocation, "r");
            String str;
            while ((str = file.readLine()) != null) {
                String[] eachPlayer = str.split("[,]", 0);
                String playerUuid = eachPlayer[0];
                String operation = eachPlayer[1];
                String matchUuid = eachPlayer[2];
                int value = Integer.parseInt(eachPlayer[3]);
                String betOn = (eachPlayer.length > 4) ? eachPlayer[4] : null;
                // index of current player on players list
                Player currentPlayer = players.get(playerCheck(playerUuid));
                if (illegalOperation.keySet().contains(currentPlayer))
                    continue;
                switch (operation) {
                    case "BET":
                    boolean matchFound = false;
                        for (Match currentMatch : matches) {
                            if (matchUuid.equals(currentMatch.getuuid())){
                                if (!currentMatch.matchExcute(
                                        host,
                                        currentPlayer,
                                        matchUuid,
                                        value,
                                        betOn,
                                        currentMatch))
                                    illegalOperationDealer(currentPlayer, str);
                            matchFound = true;
                            }
                        }
                        if(!matchFound) System.out.println("Match does not exsist. Match UUID: "+matchUuid);
                        break;
                    case "DEPOSIT":
                        currentPlayer.deposit(value);
                        break;
                    case "WITHDRAW":
                        if (!currentPlayer.withdraw(value))
                            illegalOperationDealer(currentPlayer, str);
                        break;
                }
            }
            if (count > 0 && !illegalOperation.isEmpty()) {
                for (Player player : players) {
                    player.resetPlayerStats();
                }
                host.resetHostStats();
                count--;
                playerDataExcuter(host, fileLocation);
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
    public static void illegalOperationDealer(Player player, String action) {
        illegalOperation.put(player, action);
    }

    // Based to the UUID, if there's new player, add to the players list
    public static int playerCheck(String playerId) {
        // returns index of the player in the players list
        boolean playerExists = false;
        int count = 0;
        for (Player player : players) {
            if (player.getUuid().equals(playerId)) {
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

    // write output
    public static void resultPrinter() {
        try {
            File result = new File("sample/result.txt");
            if (result.createNewFile()) {
                FileWriter myWriter = new FileWriter("sample/result.txt");
                boolean legalPlayer = false;
                boolean illegalPlayer = false;
                for (Player player : players) {
                    if (!illegalOperation.keySet().contains(player)) {
                        myWriter.write(player.getUuid() + " " + player.getBalance() + " "
                                + String.valueOf(player.winrate()).replace('.', ',') + "\n\n");
                        legalPlayer = true;
                    }
                }
                if (legalPlayer == false)
                    myWriter.write("\n\n");
                for (Player player : players) {
                    if (illegalOperation.keySet().contains(player)) {
                        String[] str = illegalOperation.get(player).split(",");
                        if (str[1].equals("BET")) {
                            myWriter.write(str[0] + " " + str[1] + " " + str[2] + " " + str[3] + " " + str[4] + "\n\n");
                        } else {
                            myWriter.write(str[0] + " " + str[1] + " null " + str[3] + " null\n\n");
                        }
                        illegalPlayer = true;
                    }
                }
                if (illegalPlayer == false)
                    myWriter.write("\n\n");
                myWriter.write(String.valueOf(host.getCasinoBalance()));
                myWriter.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}