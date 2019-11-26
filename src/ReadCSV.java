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

    public void parsecsvfile() {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Set<String> set_for_different_year = new HashSet<String>();
        List<String> Year_list = new ArrayList<String>();
        List<String> Winner_list = new ArrayList<String>();
        Set<String> set_for_different_team = new HashSet<String>();

      //  Map<String, String> map = new HashMap<String, String>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();//skip heading
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] match = line.split(cvsSplitBy);
                set_for_different_year.add(match[1]);
                set_for_different_team.add(match[4]);
                Winner_list.add(match[10]);


                Year_list.add(match[1]);

                //System.out.println(match[1]);//print match year column

            }
           //set_for_different_year.sort();
           // System.out.println("total years= "+list);
            //System.out.println("different years= "+set_for_different_year);

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

        int set_size=set_for_different_year.size();

        TreeSet<String> ordered_set_for_diff_year = new TreeSet<String>(set_for_different_year);
        TreeSet<String> ordered_set_for_diff_team = new TreeSet<String>(set_for_different_team);

        int list_size=Year_list.size();
        int[] years_count = new int[set_size];//for storing count of no. of matches played

        for(int i=0;i<list_size;i++)//loop for calculating count of no. of matches played
        {
            if (ordered_set_for_diff_year.contains(Year_list.get(i)))
            {
                int location=returnlocation(Year_list.get(i),ordered_set_for_diff_year);
                years_count[location]+=1;
            }
        }
        Map<String, Integer> number_of_matches = new HashMap<String, Integer>();
        Map<String, Integer> per_year_win = new HashMap<String, Integer>();
        HashMap<String, List<Map>> team_win = new HashMap<String, List<Map>>();

        Enumeration e = Collections.enumeration(ordered_set_for_diff_year);
        for(int j=0;j<list_size&&e.hasMoreElements();j++)
        {
            number_of_matches.put((String) e.nextElement(),years_count[j]);
        }
        System.out.println("Question1 answer");
        System.out.println(number_of_matches);



    }

    private int returnlocation(String s,TreeSet<String> t)
    {
        int place =0;
        for (String find:t)
        {
            if(find.equals(s)){return place;}
            else
                place+=1;

        }
        return -1;

    }

}
