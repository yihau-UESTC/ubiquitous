package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo6 {
    public static void main(String[] args){
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        //Collectors.joining
        String G7Countries = G7.stream().map((s) -> s.toUpperCase()).collect(Collectors.joining(","));
        System.out.println(G7Countries);
    }
}
