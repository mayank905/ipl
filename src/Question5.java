import java.util.*;

/////// list of most dismissed players
public class Question5
{
    List<List> contains_deliveries ;
    public final int dismissed =18;

    public Question5(List<List> l)
    {
        contains_deliveries=l;

    }

    public Map dismissed_batsman()
    {
        Map<String,Integer> dismiss_map =new HashMap<>();

        Set<String> set_for_different_batsman = new HashSet<String>();
        List<String> batsman_list = new ArrayList<String>();

        for (List list:contains_deliveries)
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


        dismiss_map=sortByValue((HashMap<String, Integer>) dismiss_map);


        return dismiss_map;
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
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
