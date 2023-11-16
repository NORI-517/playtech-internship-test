import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class CasinoSimulation {
    List<Player> players;
    static List<Match> matches = new ArrayList<>();
    long casinoBalance;

    // コンストラクタと必要なメソッドの実装

    public static void main(String[] args) {
        matchDataReader("sample/match_data.txt");

    }
    public static void playerDataReader(String fileLocation){
        //Match_dataからデータを引っ張ってきてmatchesに追加する
        try {
			RandomAccessFile file = new RandomAccessFile(fileLocation, "r");
			String str;
			while ((str = file.readLine()) != null) {
                matches.add(new Match(
                    str.substring(0,36),
                    Double.parseDouble(str.substring(37, 41)),
                    Double.parseDouble(str.substring(42, 46)),
                    str.substring(47)));
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void matchDataReader(String fileLocation){
        //Match_dataからデータを引っ張ってきてmatchesに追加する
        try {
			RandomAccessFile file = new RandomAccessFile(fileLocation, "r");
			String str;
			while ((str = file.readLine()) != null) {
                matches.add(new Match(
                    str.substring(0,36),
                    Double.parseDouble(str.substring(37, 41)),
                    Double.parseDouble(str.substring(42, 46)),
                    str.substring(47)));
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}