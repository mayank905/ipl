import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        ReadCSV matches_file =new ReadCSV("matches.csv");


        List<List> Matcheslist=matches_file.parse_matches_csvfile();
        Question1 q1=new Question1(Matcheslist);
        Map<String,Integer> m1 = q1.number_of_years();

        System.out.println("Question1 answer");
        System.out.println(m1);System.out.println();System.out.println();
        Question2 q2 =new Question2(Matcheslist);
        Map<String,List > m2 = q2.number_of_years();

        System.out.println("Question2 answer");
        System.out.println(m2);System.out.println();System.out.println();


        ReadCSV delivery_file = new ReadCSV("deliveries.csv");
        List<List> Delivery_list=delivery_file.parse_matches_csvfile();
        Question3 q3 =new Question3(Matcheslist,Delivery_list);
        Map<String,Integer> m3 = q3.extra_runs_conceed();
        System.out.println("Question3 answer");
        System.out.println(m3);System.out.println();System.out.println();


        Question4 q4 =new Question4(Matcheslist,Delivery_list);
        Map<String,Float> m4 = q4.economy_of_bowlers();
        System.out.println("Question4 answer");
        System.out.println(m4);System.out.println();System.out.println();


        Question5 q5 =new Question5(Delivery_list);
        Map<String,Float> m5 = q5.dismissed_batsman();
        System.out.println("Question5 answer");
        System.out.println(m5);System.out.println();System.out.println();

    }

}
