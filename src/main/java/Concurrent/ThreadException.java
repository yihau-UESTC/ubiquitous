package Concurrent;


public class ThreadException implements Runnable {

    @Override
    public void run() {
        try {
            throw new IllegalArgumentException("illegal");

        } catch (Exception e) {
            System.out.println("child thread" + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Thread t = new Thread(new ThreadException());
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + "--->" + e.getMessage());
            }
        });
        t.start();

    }
}
