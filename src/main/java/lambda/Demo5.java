package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo5 {
    public static void main(String[] args){
        List<Integer> cost = Arrays.asList(100,200,300,400,500);
        for (Integer c:cost) {
            double temp = c + 0.12 * c;
            System.out.println(temp);
        }

        //函数是编程map lambda表达式
        List d = cost.stream().map((c) -> c + c * 0.12).collect(Collectors.toList());
        System.out.println(d);

        //reduce() add the elements of stream one by one
        double total = cost.stream().map((c) -> c + c * 0.12).reduce((x1,x2) -> x1 + x2).get();
        System.out.println(total);
    }
}
