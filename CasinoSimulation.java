import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class CasinoSimulation {
    // List of players
    List<Player> players = new ArrayList<>();
    // List of matches
    static List<Match> matches = new ArrayList<>();
    // Casino host balance
    long casinoBalance = 0;

    public static void main(String[] args) {
        matchDataReader("sample/match_data.txt");
        for (Match each : matches) {
            each.show();
        }
    }

    public static void playerDataReader(String fileLocation) {
        // Match_dataからデータを引っ張ってきてmatchesに追加する
        try {
            RandomAccessFile file = new RandomAccessFile(fileLocation, "r");
            String str;
            while ((str = file.readLine()) != null) {
                matches.add(new Match(
                        str.substring(0, 36),
                        Double.parseDouble(str.substring(37, 41)),
                        Double.parseDouble(str.substring(42, 46)),
                        str.substring(47)));
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
                matches.add(new Match(
                        // UUID
                        str.substring(0, 36),
                        // Rate A
                        Double.parseDouble(str.substring(37, 41)),
                        // Rate B
                        Double.parseDouble(str.substring(42, 46)),
                        // Match result
                        str.substring(47)));
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}