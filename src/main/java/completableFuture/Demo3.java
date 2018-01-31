package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Demo3 {
    public static void main(String[] args){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(()->{
            try {
                if (true) {
                    throw new RuntimeException("Something wrong");
                }
                futurePrice.complete(23.55);
            }catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }
        }).start();

        try {
            System.out.println(futurePrice.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
