import java.util.*;
import static Constants.Constant.*;

public class Service
{

    public HashMap<String,Integer> question1(List<Match> matchesList)
    {
        HashMap<String,Integer>noOfMatches = new HashMap<>();
        matchesList.stream().forEach(y->
        {
            String s= (String) y.getSeason();
            if(noOfMatches.containsKey(s))
            {
                noOfMatches.put(s,noOfMatches.get(s)+1);
            }
            else
                {
                    noOfMatches.put(s,1);
                }

        });

        return noOfMatches;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public HashMap<String,HashMap> question2(List<Match> matchesList)
    {
        HashMap<String,HashMap>noOfWinPerYear = new HashMap<>();


        matchesList.stream().forEach(y->
        {
            String s= (String) y.getWinner();
            String s2 = (String) y.getSeason();
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
        });


        return noOfWinPerYear;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HashMap<String,Integer> question3(List<Match> matchesList, List<Delivery> deliveryList)
    {
        HashMap<String,Integer> extraRunConceed = new HashMap<>();////
        Set<String> matchId =new HashSet<>();

        matchesList.stream().forEach(y->
        {
            String s2 = (String) y.getSeason();
            String s3 = (String) y.getMatchID();
            if(s2.equals(YEAR))
            {
                matchId.add(s3);

            }

        });

        deliveryList.stream().forEach(y->//////////////////////////loop over each row
        {
            String matchId1 = (String) y.getMatchID();
            String bowlinTeam = (String) y.getBowlingTeam();
            String extraRuns = (String) y.getExtraRuns();



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
                }
            }


        });

        return extraRunConceed;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public HashMap<String, Float> question4(List<Match> matchesList,List<Delivery> deliveryList)
    {
        HashMap<String,Float> economyOfBowler = new HashMap<>();
        HashMap<String,Integer> deliveriesOfBowler = new HashMap<>();
        Set<String> matchId =new HashSet<>();
        matchesList.stream().forEach(y->
        {
            String s2 = (String) y.getSeason();
            String s3 = (String) y.getMatchID();
            if(s2.equals(YEAR2))
            {
                matchId.add(s3);

            }

        });
        deliveryList.stream().forEach(y->
        {
            String matchId1 = (String) y.getMatchID();
            String s= (String) y.getBowler();
            String s2 = (String) y.getTotalRuns();
            int wide =Integer.parseInt((String) y.getWideRuns());
            int noBall =Integer.parseInt((String) y.getNoBall());
            Float runs =Float.parseFloat(s2);
            if (matchId.contains(matchId1))
            {
                if (economyOfBowler.containsKey(s) && deliveriesOfBowler.containsKey(s))
                {
                    Float previousRun = economyOfBowler.get(s);
                    int noOfDeliveries = deliveriesOfBowler.get(s);
                    economyOfBowler.put(s, previousRun + runs);
                    if (wide == 0 && noBall == 0) {
                        deliveriesOfBowler.put(s, noOfDeliveries + 1);
                    }
                } else {
                    economyOfBowler.put(s, runs);
                    deliveriesOfBowler.put(s, 1);
                }
            }

        });

        economyOfBowler.keySet().stream().forEach(y->
        {
            Float economy = (economyOfBowler.get(y)/deliveriesOfBowler.get(y))*6;
            economyOfBowler.put(y,economy);
        });




        return economyOfBowler;
    }

}
