package Concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);
    private static CountDownLatch start;
    @Test
    public void fair() {
        testLock(fairLock);
    }
    @Test
    public void unfair() {
        testLock(unfairLock);
    }

    private void testLock(Lock lock){
        start = new CountDownLatch(5);
        for (int i = 0; i < 5; i++){
            Thread thread = new Job(lock);
            thread.setName("" + i);
            thread.start();
            start.countDown();
        }

    }

    private static class Job extends Thread{
        private Lock lock;
        public Job(Lock lock){
            this.lock = lock;
        }

        public String toString() {
            return getName();
        }

        @Override
        public void run() {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            for (int i = 0; i < 2; i++){
                lock.lock();
                try {
                    System.out.println("Lock by [" + getName() + "], Waiting by " + ((ReentrantLock2) lock).getQueuedThread());
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair){
            super(fair);
        }
        public Collection<Thread> getQueuedThread(){
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
