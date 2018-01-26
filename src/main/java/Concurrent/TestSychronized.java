package Concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class TestSychronized {
    public  static volatile int race = 0;
    public synchronized static void increase(){
        race++;
        System.out.println(race);
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }

    }
}
