package Concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午7:58 17-12-26
 * @Description: 线程安全退出的两种方式，都是通过run方法执行结束来退出。
 * 1、通过开放一个方法来设置运行标志，并将该变量使用volatile修饰。
 * 2、通过interrupte方法来进行线程通信。
 */
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one);
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two  = new Runner();
        countThread = new Thread(two);
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
        countThread.notify();
    }


    private static class Runner implements Runnable{

        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel(){
            on = false;
        }
    }
}
