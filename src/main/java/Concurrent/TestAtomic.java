package Concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class TestAtomic {

    @Test
    public void atomicArray(){
        int[] value = new int[]{1,2};
        AtomicIntegerArray ai = new AtomicIntegerArray(value);
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
    @Test
    public void atomicReference(){
        AtomicReference<User> au = new AtomicReference<>();
        User u = new User("wang", 20);
        au.set(u);
        au.compareAndSet(u, new User("wang", 21));
        System.out.println(au.get().getOld());
    }

    @Test
    public void atomicField(){
        AtomicIntegerFieldUpdater<User> af = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
        User conan = new User("conan", 10);
        af.getAndIncrement(conan);
        System.out.println(af.get(conan));
    }

    static class User{
        private String name;
        public volatile int old;

        public User(String name, int old){
            this.name = name;
            this.old = old;
        }
        public String getName(){
            return name;
        }
        public int getOld(){
            return old;
        }
    }
}
