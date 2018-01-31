package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


public class Demo3 {

    public  static  void filter(List<String> names, Predicate<String> condition){
//        for (String name : names){
//            if(condition.test(name)){
//                System.out.println(name + " ");
//            }
//        }
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {System.out.println(name +" ");});
    }

    public static void main(String args[]){
        List<String> languages = Arrays.asList("java","scala", "c++", "haskell", "lisp");
        System.out.println("languages which starts wit j");

       //不同的动态行为
        filter(languages, (s) -> s.startsWith("j"));

        System.out.println("Print all languages");
        filter(languages, (s) -> true);

        filter(languages, (s) -> s.length() > 4);
    }
}
