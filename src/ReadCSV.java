import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadCSV
{

    private String csvFile;
    public ReadCSV(String s)
    {

        csvFile = s;
    }

    public List<List> parse_matches_csvfile() {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<List> Total_Row = new ArrayList<List>();

        //Set<String> set_for_different_team = new HashSet<String>();


        //  Map<String, String> map = new HashMap<String, String>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();//skip heading
            while ((line = br.readLine()) != null) {
                List<String> Each_Row = new ArrayList<String>();


                // use comma as separator
                String[] match = line.split(cvsSplitBy);
                for (int i = 0; i < match.length; i++) {
                    Each_Row.add(match[i]);
                }
                Total_Row.add(Each_Row);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Total_Row;

    }
}


