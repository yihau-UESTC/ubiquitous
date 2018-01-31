package lambda;

import java.util.Arrays;
import java.util.List;

public class Demo2 {
    public static void main(String args[]){
        List features = Arrays.asList("Lambda","Default Method", "Stream API","Data and Time API");
        //java8 之前
        for (Object feature : features){
            System.out.println(feature);
        }
        //java8 之后
        features.forEach(n -> System.out.println(n));
        //java8 的方法引用更方便，方法引用用：：操作符表示
        features.forEach(System.out::println);
    }
}
