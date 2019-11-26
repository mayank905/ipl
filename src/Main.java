import static Constants.Constant.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        Readcsv2 match_file =new Readcsv2(MATCHES_CSV);
        List<Map> matchesList = match_file.parseMatchesCsvfile();
        Readcsv2 deliveryFile =new Readcsv2(DELIVERIES_CSV);
        List<Map> deliveryList = deliveryFile.parseMatchesCsvfile();
        Service service1=new Service();


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


}
