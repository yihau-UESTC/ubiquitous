package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Demo4 {
    public static void main(String[] args){
        //and()链 equals and link two predicate condition,
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> len = (n) -> n.length() > 3;
        languages.stream().filter(startsWithJ.and(len)).forEach((n) -> System.out.println(n));
    }
}
