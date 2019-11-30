import static Constants.Constant.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        Readcsv2 match_file =new Readcsv2();
        List<String[]> matchesStringList = match_file.parseMatchesCsvfile(MATCHES_CSV);
        List<Match> matchesList =new ArrayList<>();
        matchObject(matchesStringList,matchesList);


        Readcsv2 deliveryFile =new Readcsv2();
        List<String[]> deliveryStringList = deliveryFile.parseMatchesCsvfile(DELIVERIES_CSV);
        List<Delivery> deliveryList =new ArrayList<>();
        deliveryObject(deliveryStringList,deliveryList);




        Service service1=new Service();//create service class object


        HashMap<String,Integer> noOfMatchPerYear=service1.question1(matchesList);

        printF(Q1_ANS,noOfMatchPerYear);


        HashMap<String,HashMap> noOfWinPerYear=service1.question2(matchesList);
        printF(Q2_ANS,noOfWinPerYear);


        HashMap<String,Integer> extraRunConceed=service1.question3(matchesList,deliveryList);
        printF(Q3_ANS,extraRunConceed);


        HashMap<String,Float> economyOfBowler=service1.question4(matchesList,deliveryList);
        printF(Q4_ANS,economyOfBowler);


    }

    private static void printF(String s,HashMap m)
    {
        System.out.println(s);
        System.out.println(m);System.out.println();System.out.println();
    }

    private static void deliveryObject(List<String[]> deliveryStringList, List<Delivery> deliveryList)
    {
        deliveryStringList.stream().forEach(y->{
            Delivery d1 =new Delivery();
            d1.setBowlingTeam(y[BOWLING_TEAM]);//enter year value
            d1.setExtraRuns(y[EXTRA_RUNS]);//enter winner value
            d1.setMatchID(y[MATCH_ID]);
            d1.setBowler(y[BOWLER]);
            d1.setNoBall(y[NO_BALL]);
            d1.setWideRuns(y[WIDE_RUNS]);
            d1.setTotalRuns(y[TOTAL_RUNS]);
            deliveryList.add(d1);
        });
    }

    private static void matchObject(List<String[]> matchesStringList,List<Match> matchesList)
    {
        matchesStringList.stream().forEach(y->{
            Match m1=new Match();
            m1.setSeason(y[SEASON]);//enter year value
            m1.setWinner(y[WINNER]);//enter winner value
            m1.setMatchID(y[ID]);
            matchesList.add(m1);
        });

    }


}
