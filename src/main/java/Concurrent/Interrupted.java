package Concurrent;

import java.nio.channels.Selector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Interrupted {
    private static Object monitor = new Object();
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new BusyThread());
        t.start();
        System.out.println(threadLocal.get());
    }

    static class BusyThread implements Runnable {

        boolean flag = true;

        @Override
        public void run() {
            threadLocal.set(5);
            System.out.println(threadLocal.get());
            while (true) {

            }

        }
    }
}
