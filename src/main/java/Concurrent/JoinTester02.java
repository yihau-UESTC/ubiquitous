package Concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JoinTester02 implements Runnable {

    Thread thread;

    public JoinTester02(Thread thread) {
        this.thread = thread;
    }

    public void run() {
        synchronized (thread) {
            System.out.println("getObjectLock");
            System.out.printf("%s begins: %s\n", Thread.currentThread().getName(), new Date());
            try {
                Thread.sleep(9000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("ReleaseObjectLock");
            System.out.printf("%s finished: %s\n", Thread.currentThread().getName(), new Date());
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new JoinTester01("Three"));
        Thread getLockThread = new Thread(new JoinTester02(thread));

        getLockThread.start();
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Main finished!");
    }

    static class JoinTester01 implements Runnable {

        private String name;

        public JoinTester01(String name) {
            this.name = name;
        }

        public void run() {
            synchronized (Thread.currentThread()) {
                System.out.printf("%s begins: %s\n", name, new Date());
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s has finished: %s\n", name, new Date());
            }
        }
    }

}
