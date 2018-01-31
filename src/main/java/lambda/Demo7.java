package lambda;

import java.util.Arrays;
import java.util.List;

public class Demo7 {

    public static void main(String[] args){
        //distinct remove double elements
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        numbers.stream().distinct().forEach(System.out::println);
    }
}
