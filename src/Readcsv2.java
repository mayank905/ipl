import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Readcsv2
{
    private String csvFile;
    public Readcsv2(String s)
    {

        csvFile = s;
    }

    public List<Map> parseMatchesCsvfile() {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<Map> eachRow =new ArrayList<>();


        try {
            br = new BufferedReader(new FileReader(csvFile));
            line =  br.readLine();
            String[] heading = line.split(cvsSplitBy);
            while ((line = br.readLine()) != null)
            {
                Map<String,String> eachElement  =new HashMap<>();


                // use comma as separator
                String[] match = line.split(cvsSplitBy);
                for (int i = 0; i < match.length; i++) {
                    eachElement.put(heading[i],match[i]);

                }
                eachRow.add(eachElement);

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
        return eachRow;

    }

}
