package Concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUseCase {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public void conditionWait(){
        lock.lock();
        try {
            condition.await();//等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void conditionSignal(){
        lock.lock();
        try {
            condition.signal();//通知
        }finally {
            lock.unlock();
        }
    }
}
