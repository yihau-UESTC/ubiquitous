package Concurrent;

public class ThreadOpp {


    public static void main(String[] args) {
     Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println("------------------------");
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)t.suspend();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)t.resume();
            }
        });

        t.start();
                t1.start();
        t2.start();
    }
}
