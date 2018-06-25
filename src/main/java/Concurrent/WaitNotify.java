package Concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午8:38 17-12-26
 * @Description:
 * 1、调用wait方法后会直接释放掉对象的锁
 * 2、notify会将一个线程从等待队列移动到同步队列，notifyall则是将所有等待队列中所有线程移动到同步队列。移到同步队列的线程状态会
 * 变成BLOCKED但是并不会从wait方法中返回到正常的执行，必须在争取到对象的锁才能返回。这样可以保证从wait方法返回时可以看到其他线程对变量的修改。
 * 这种等待通知范式是生产者/消费者的经典范式
 * 消费者 1》、对产品池加锁，
 *       2》、当产品池中的产品不满足要求，产品池.wait；被notify后需要重新检查条件，由于这里wait后会释放掉锁，notify后需要重新获取锁，所以notify后一定会重新检查条件
 *       3》、满足条件则执行相应操作
 * 生产者 1》、对产品池加锁，
 *       2》、生产商品，改变条件
 *       3》、产品池.notifyAll，通知在等待的线程
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();
    static Thread waitThread = new Thread(new Wait(), "WaitThread");
    static Thread notifyThread = new Thread(new Notify(), "NotifyThread");

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new StateThread());
        t.setDaemon(true);
        t.start();
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        notifyThread.start();

    }


    static class Wait implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss")
                        .format(new Date()));
                        lock.wait();
                        }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is " + flag + ". running @ " +
                new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock. notify @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again. sleep@ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class StateThread implements Runnable{

        @Override
        public void run() {
            while (true){
                System.out.println("waitThread state = " + waitThread.getState());
                System.out.println("notifyThread state = " + notifyThread.getState());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
