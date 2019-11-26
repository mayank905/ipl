import java.util.*;

public class Question1
{
    List<List> contains_matches ;
    List<List> contains_deliveries ;
    public final int season =1;
    public final int team =10;
    public final int team2 =4;
    public final String year="2016";
    public final int id=0;
    public final int bowling_team=3;
    public final int extra_runs1=16;

    public final String year2="2015";
    public final int bowler=8;
    public final int total_runs1=17;
    public final int noball=13;
    public final int dismissed =18;
    public final int wideball=10;

    public Question1(List<List> l)
    {
        contains_matches=l;

    }

    public Question1(List<List> l,List<List>l2)
    {
        contains_matches=l;
        contains_deliveries=l2;
    }

    public Map numberOfMatchesPlayed()
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


    ////////////////////////////////////Quesetion2/////////////////////////////////////////////////////

    public Map number_of_matches_won_by_all_team()
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
                location=returnlocation1(Year_list.get(i),Team_list.get(i),ordered_set_for_diff_year,ordered_set_for_diff_team);
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

        return number_of_win;
    }


    private int[] returnlocation1(String y,String t,TreeSet<String> year,TreeSet<String>team)
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
//////////////////////////////////////////////////Question3//////////////////////////////////////////////////////

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
            teams.add((String) list1.get(team2));

        }
        int set_size_current =teams.size();
        int[] extra_runs = new int[set_size_current];
        TreeSet<String> ordered_set_for_currentyear = new TreeSet<String>(teams);

/*

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
*/
        List<String> match_ids=new ArrayList<>();
        for (List list2:match_sublist)//nested loop over match file and delivery file
        {
            match_ids.add((String) list2.get(id));
        }



            for (List list3:contains_deliveries)
            {if(match_ids.contains(list3.get(id)))
                {

                    int location =returnlocation((String) list3.get(bowling_team),ordered_set_for_currentyear);
                    extra_runs[location] += Integer.parseInt((String) list3.get(extra_runs1));
                }


            }






        Enumeration e = Collections.enumeration(ordered_set_for_currentyear);

        for(int j=0;j<set_size_current && e.hasMoreElements();j++)
        {
            final_ans.put((String) e.nextElement(),extra_runs[j]);
        }

        return final_ans;
    }

///////////////////////////////////Question4///////////////////////////////////////////////////////


    public Map economy_of_bowlers()
    {
        Map<String,Float> economy_map= new HashMap<>();
        List<List> match_sublist =new ArrayList<>();

        for (List list:contains_matches)//loop to create sublist
        {
            if(list.get(season).equals(year2))
            {
                match_sublist.add(list);
            }

        }

        Set<String> bowlers =new HashSet<>();
        for (List list2:match_sublist)//nested loop create list of bowler in that year
        {
            String match_id = (String) list2.get(id);


            for (List list1 : contains_deliveries)
            {
                if(match_id.equals(list1.get(id))){
                    bowlers.add((String) list1.get(bowler));}

            }
        }
        int set_size_current =bowlers.size();
        float[] total_runs = new float[set_size_current];
        int[] total_deliveries = new int[set_size_current];
        TreeSet<String> ordered_set_for_currentyear = new TreeSet<String>(bowlers);




        for (List list2:match_sublist)//nested loop over match file and delivery file,,for year 2015
        {
            String match_id= (String) list2.get(id);

            for (List list3:contains_deliveries)//for events of each delivery
            {
                if(match_id.equals(list3.get(id)))
                {
                    if(Integer.parseInt((String) list3.get(noball))==1||Integer.parseInt((String) list3.get(wideball))==1)
                    {
                        int location = returnlocation((String) list3.get(bowler), ordered_set_for_currentyear);
                        total_runs[location] += Integer.parseInt((String) list3.get(total_runs1));

                    }
                    else
                    {
                        int location = returnlocation((String) list3.get(bowler), ordered_set_for_currentyear);
                        total_runs[location] += Integer.parseInt((String) list3.get(total_runs1));
                        total_deliveries[location] += 1;
                    }
                }


            }


        }

        for(int j=0;j<set_size_current;j++)
        {
            total_runs[j]=(total_runs[j]/total_deliveries[j])*6;
        }

        Enumeration e = Collections.enumeration(ordered_set_for_currentyear);

        for(int j=0;j<set_size_current && e.hasMoreElements();j++)
        {
            economy_map.put((String) e.nextElement(),total_runs[j]);
        }
////////////////////////////////sort the hash map/////////////////////////////////////////////////////

        economy_map=sortByValue((HashMap<String, Float>) economy_map);

        return economy_map;
    }


    public static HashMap<String, Float> sortByValue(HashMap<String, Float> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Float> > list =
                new LinkedList<Map.Entry<String, Float> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Float> >() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Float> temp = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    ////////////////////////////////Question5/////////////////////////////////////////////////////////


    public Map dismissed_batsman()
    {
        Map<String,Integer> dismiss_map =new HashMap<>();

        Set<String> set_for_different_batsman = new HashSet<String>();
        List<String> batsman_list = new ArrayList<String>();

        for (List list:contains_matches)
        {
            try {
                set_for_different_batsman.add((String) list.get(dismissed));
                batsman_list.add((String) list.get(dismissed));

            }catch (IndexOutOfBoundsException e){continue;}
        }

        int set_size=set_for_different_batsman.size();
        int list_size=batsman_list.size();

        TreeSet<String> ordered_set_for_diff_batsman = new TreeSet<String>(set_for_different_batsman);

        int[] dismiss_count = new int[set_size];//for storing count of no. of matches played

        for(int i=0;i<list_size;i++)//loop for calculating count of no. of matches played
        {
            if (ordered_set_for_diff_batsman.contains(batsman_list.get(i)))
            {
                int location=returnlocation(batsman_list.get(i),ordered_set_for_diff_batsman);
                dismiss_count[location]+=1;
            }
        }

        Enumeration e = Collections.enumeration(ordered_set_for_diff_batsman);

        for(int j=0;j<set_size && e.hasMoreElements();j++)
        {
            dismiss_map.put((String) e.nextElement(),dismiss_count[j]);
        }


        dismiss_map=sortByValue1((HashMap<String, Integer>) dismiss_map);


        return dismiss_map;
    }

    public static HashMap<String, Integer> sortByValue1(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (-(o1.getValue()).compareTo(o2.getValue()));
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }




}
