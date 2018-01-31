package completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

public class Demo1 {
    public static void main(String[] args)  {
        AtomicReference<String> reference = new AtomicReference<>();

        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reference.set("I'm done！");
        }).start();
    //jdk1.5 之前，需要空循环等到reference  not null
        while (reference.get() == null){

        }
        System.out.println("Finally, " + reference.get());

//1.5之后 callable and future if there is'n a return value  future.get() will be blocked till there is a value
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(() -> {
            System.out.println("Running task...");
            Thread.sleep(5000);
            return "Task return";
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Do something else");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();




    }
}
