package completableFuture;

import java.util.concurrent.CompletableFuture;

public class Demo2 {

    public static void main(String[] args){
        CompletableFuture<Double> futurePrice = getPriceAsync();
        System.out.println(1111);

        futurePrice.whenComplete(((aDouble, throwable) -> {
            System.out.println(aDouble);
        }));

        System.out.println(2222);
    }

    private static CompletableFuture<Double> getPriceAsync() {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futurePrice.complete(23.35);
        }).start();
        return futurePrice;
    }
}
