import java.util.*;

public class Question1
{
    List<List> contains_matches ;
    public final int season =1;

    public Question1(List<List> l)
    {
        contains_matches=l;

    }

    public Map number_of_years()
    {
        Set<String> set_for_different_year = new HashSet<String>();
        List<String> Year_list = new ArrayList<String>();

        for (List list:contains_matches)
        {
            set_for_different_year.add((String) list.get(season));
            Year_list.add((String) list.get(season));
        }
        int set_size=set_for_different_year.size();
        int list_size=Year_list.size();

        TreeSet<String> ordered_set_for_diff_year = new TreeSet<String>(set_for_different_year);

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

        Enumeration e = Collections.enumeration(ordered_set_for_diff_year);

        for(int j=0;j<set_size && e.hasMoreElements();j++)
        {
            number_of_matches.put((String) e.nextElement(),years_count[j]);
        }

        return number_of_matches;

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
