import java.util.*;
import static Constants.Constant.*;

public class Service
{

    public HashMap<String,Integer> question1(List<Map> matchesList)
    {
        HashMap<String,Integer>noOfMatches = new HashMap<>();
        for (Map<String,String> eachRow:matchesList)
        {
            String s=eachRow.get(SEASON);
            if(noOfMatches.containsKey(s))
            {
                noOfMatches.put(s,noOfMatches.get(s)+1);
            }
            else
                {
                    noOfMatches.put(s,1);
                }

        }

        return noOfMatches;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public HashMap<String,HashMap> question2(List<Map> matchesList)
    {
        HashMap<String,HashMap>noOfWinPerYear = new HashMap<>();


        for (Map<String,String> eachRow:matchesList)
        {
            String s=eachRow.get(WINNER);
            String s2 = eachRow.get(SEASON);
            if(!s.equals(null)) {
                HashMap<String, Integer> win = new HashMap<>();
                if (noOfWinPerYear.containsKey(s)) {
                    win = noOfWinPerYear.get(s);
                    if (win != null && win.containsKey(s2)) {
                        win.put(s2, win.get(s2) + 1);

                    } else {
                        win.put(s2, 1);
                    }


                    noOfWinPerYear.put(s, win);
                } else {
                    win.put(s2, 1);
                    noOfWinPerYear.put(s, win);
                }
            }
        }

        return noOfWinPerYear;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HashMap<String,Integer> question3(List<Map> matchesList,List<Map> deliveryList)
    {
        HashMap<String,Integer> extraRunConceed = new HashMap<>();////
        Set<String> matchId =new HashSet<>();

        for (Map<String,String> eachRow:matchesList)//////////////////////////loop over each row
        {
            String s2 = eachRow.get(SEASON);
            String s3 =eachRow.get(ID);
            if(s2.equals(YEAR))
            {
                matchId.add(s3);

            }

        }

        for (Map<String,String> eachRow:deliveryList)//////////////////////////loop over each row
        {
            String matchId1 =eachRow.get(MATCH_ID);
            String bowlinTeam = eachRow.get(BOWLING_TEAM);
            String extraRuns = eachRow.get(EXTRA_RUNS);



            if(extraRuns!=null) {
                int extraRun = Integer.parseInt(extraRuns);

                if (matchId.contains(matchId1))
                {
                    if (extraRunConceed.containsKey(bowlinTeam))
                    {
                        int previousRun = extraRunConceed.get(bowlinTeam);
                        extraRunConceed.put(bowlinTeam, previousRun + extraRun);
                    } else
                        {
                        extraRunConceed.put(bowlinTeam, extraRun);
                        }
                } else {
                    continue;
                }
            }


        }

        return extraRunConceed;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HashMap<String, Float> question4(List<Map> matchesList,List<Map> deliveryList)
    {
        HashMap<String,Float> economyOfBowler = new HashMap<>();
        HashMap<String,Integer> deliveriesOfBowler = new HashMap<>();
        Set<String> matchId =new HashSet<>();

        for (Map<String,String> eachRow:matchesList)//////////////////////////loop over each row
        {
            String s2 = eachRow.get(SEASON);
            String s3 =eachRow.get(ID);
            if(s2.equals(YEAR))
            {
                matchId.add(s3);

            }

        }

        for (Map<String,String> eachRow:deliveryList)
        {
            String matchId1 =eachRow.get(MATCH_ID);
            String s=eachRow.get(BOWLER);
            String s2 = eachRow.get(TOTAL_RUNS);
            int wide =Integer.parseInt(eachRow.get(WIDE_RUNS));
            int noBall =Integer.parseInt(eachRow.get(NO_BALL));
            Float runs =Float.parseFloat(s2);
            if (matchId.contains(matchId1)) {
                if (economyOfBowler.containsKey(s) && deliveriesOfBowler.containsKey(s)) {
                    Float previousRun = economyOfBowler.get(s);
                    int noOfDeliveries = deliveriesOfBowler.get(s);
                    economyOfBowler.put(s, previousRun + runs);
                    if (wide == 0 && noBall == 0) {
                        deliveriesOfBowler.put(s, noOfDeliveries + 1);
                    } else {
                        continue;
                    }
                } else {
                    economyOfBowler.put(s, runs);
                    deliveriesOfBowler.put(s, 1);
                }
            }
            else{continue;}
        }

        for(String s:economyOfBowler.keySet())
        {
            Float economy = (economyOfBowler.get(s)/deliveriesOfBowler.get(s))*6;
            economyOfBowler.put(s,economy);
        }




        return economyOfBowler;
    }

}
