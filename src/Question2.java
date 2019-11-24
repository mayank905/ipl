import java.util.*;

public class Question2
{
    List<List> contains_matches ;
    public final int season =1;
    public final int team =10;

    public Question2(List<List> l)
    {
        contains_matches=l;

    }

    public Map number_of_years()
    {
        Set<String> set_for_different_year = new HashSet<String>();
        Set<String> set_for_different_team = new HashSet<String>();
        List<String> Year_list = new ArrayList<String>();
        List<String> Team_list = new ArrayList<String>();

        Map<String, List> number_of_win = new HashMap<String, List>();//for storing the output of question2
       // Map<String, List> number_of_win = new HashMap<String, List>();


        for (List list:contains_matches)
        {
            set_for_different_year.add((String) list.get(season));
            Year_list.add((String) list.get(season));


            Team_list.add((String) list.get(team));
            set_for_different_team.add((String) list.get(team));

        }
        int set_size=set_for_different_year.size();
        int team_size = set_for_different_team.size();
        int list_size=Year_list.size();

        TreeSet<String> ordered_set_for_diff_year = new TreeSet<String>(set_for_different_year);
        TreeSet<String> ordered_set_for_diff_team = new TreeSet<String>(set_for_different_team);

        int[][] win_count = new int[team_size][set_size];//for storing count of no. of matches win by each team
        int [] location = new int[2];

        for(int i=0;i<list_size;i++)//loop for calculating count of no. of wins scored by each team
        {
            if (ordered_set_for_diff_year.contains(Year_list.get(i)))
            {
                 location=returnlocation(Year_list.get(i),Team_list.get(i),ordered_set_for_diff_year,ordered_set_for_diff_team);
                win_count[location[0]][location[1]]+=1;
            }
        }



        Enumeration e1 = Collections.enumeration(ordered_set_for_diff_team);
        //System.out.println(ordered_set_for_diff_team);


        for(int j=0;j<team_size && e1.hasMoreElements();j++)
        {
            Enumeration e = Collections.enumeration(ordered_set_for_diff_year);
            List<Map> year_list =new ArrayList();
            for(int k=0;k<set_size && e.hasMoreElements();k++)
            {

                Map<String,Integer> count =new HashMap<>();
                count.put((String) e.nextElement(),win_count[j][k]);
                year_list.add(count);

            }
            number_of_win.put((String) e1.nextElement(),year_list);
        }

/*
        for(int j=0;j<set_size && e.hasMoreElements();j++)
        {
            number_of_matches.put((String) e.nextElement(),years_count[j]);
        }

        return number_of_matches;
*/
return number_of_win;
    }

    private int[] returnlocation(String y,String t,TreeSet<String> year,TreeSet<String>team)
    {
        int[] place ={0,0};
        for (String find:team)
        {

            if(find.equals(t)){break;}
            else
                place[0]+=1;

        }

        for(String find1:year)
        {
            if(find1.equals(y)){return place;}
            else
                place[1]+=1;

        }
        return null;

    }

}
