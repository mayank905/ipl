import java.util.*;

public class Question4
{
    List<List> contains_matches ;
    List<List> contains_deliveries ;
    public final int season =1;
    //public final int team =4;
    public final String year="2015";
    public final int id=0;
    public final int bowler=8;
    public final int total_runs1=17;
    public final int noball=13;
    public final int wideball=10;

    public Question4(List<List> l,List<List>l2)
    {
        contains_matches=l;
        contains_deliveries=l2;

    }

    public Map economy_of_bowlers()
    {
        Map<String,Float> economy_map= new HashMap<>();
        List<List> match_sublist =new ArrayList<>();

        for (List list:contains_matches)//loop to create sublist
        {
            if(list.get(season).equals(year))
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



    private int returnlocation(String bowler,TreeSet<String> bowlers_set)
    {
        int place =0;
        for (String find:bowlers_set)
        {
            if(find.equals(bowler))
            {
                return place;
            }
            else
                place+=1;

        }
        return -1;

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


}
