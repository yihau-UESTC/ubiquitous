package Concurrent;

public class DeadLoopClass {
    static class a {
        static {
            if (true) {
                System.out.println(Thread.currentThread().getName() + "init deadloopclass");
                while (true) {

                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start");
                a dlc = new a();
                System.out.println(Thread.currentThread().getName() + "run over");
            }
        };
        Thread t1 = new Thread(script, "t1");
        Thread t2 = new Thread(script, "t2");
        t1.start();
        t2.start();
    }
}
