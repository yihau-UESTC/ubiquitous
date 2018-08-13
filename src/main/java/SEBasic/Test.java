package SEBasic;


import java.nio.channels.Selector;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(2);
        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        threadLocal1.set("yihau");
        System.out.println(threadLocal.get());
        System.out.println(threadLocal1.get());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
                System.out.println(threadLocal1.get());
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finish");


        CompletionStage<String> stage = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        stage.thenAccept(System.out::println);

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
