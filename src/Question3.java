import java.util.*;

public class Question3
{
    List<List> contains_matches ;
    List<List> contains_deliveries ;
    public final int season =1;
    public final int team =4;
    public final String year="2016";
    public final int id=0;
    public final int bowling_team=3;
    public final int extra_runs1=16;

    public Question3(List<List> l,List<List>l2)
    {
        contains_matches=l;
        contains_deliveries=l2;

    }
    public Map extra_runs_conceed()
    {
        Map<String,Integer> final_ans=new HashMap<>();
        List<List> match_sublist =new ArrayList<>();

        for (List list:contains_matches)//loop to create sublist
        {
            if(list.get(season).equals(year))
            {
                match_sublist.add(list);
            }

        }

        Set<String> teams =new HashSet<>();


        for (List list1:match_sublist)//loop to create list of teams in that year
        {
            teams.add((String) list1.get(team));

        }
        int set_size_current =teams.size();
        int[] extra_runs = new int[set_size_current];
        TreeSet<String> ordered_set_for_currentyear = new TreeSet<String>(teams);



        for (List list2:match_sublist)//nested loop over match file and delivery file
        {
            String match_id= (String) list2.get(id);
           // List<List> current_match = new ArrayList<>();

            for (List list3:contains_deliveries)
            {
                if(match_id.equals(list3.get(id)))
                {
                    int location =returnlocation((String) list3.get(bowling_team),ordered_set_for_currentyear);
                    extra_runs[location] += Integer.parseInt((String) list3.get(extra_runs1));
                }


            }


        }

        Enumeration e = Collections.enumeration(ordered_set_for_currentyear);

        for(int j=0;j<set_size_current && e.hasMoreElements();j++)
        {
            final_ans.put((String) e.nextElement(),extra_runs[j]);
        }

        return final_ans;
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
