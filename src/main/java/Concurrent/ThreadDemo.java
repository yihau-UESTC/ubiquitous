package Concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {

    public static void main(String[] args) {
        Test t = new Test();
        Thread tt = new Thread(t);
        tt.start();
        while (true) {
            synchronized (t) {
                if (t.getFlag()) {
                    System.out.println("---------");
                    break;
                }
            }
        }

    }

    static class Test implements Runnable{

        private  boolean flag = false;

        public boolean getFlag(){
            return flag;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            flag = true;
            System.out.println("flag = " + getFlag());
        }
    }
}
