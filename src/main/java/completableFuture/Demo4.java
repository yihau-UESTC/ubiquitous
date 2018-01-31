package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() ->{
            if (true){
                throw new RuntimeException("Something wrong");
            }
            return 23.55;
        }, r -> new Thread(r).start());


        System.out.println(futurePrice.get());
    }
}
