package Concurrent;

public class Join {
    public static void main(String[] args) throws InterruptedException {
    Thread previous = Thread.currentThread();
    Thread thread = new Thread(new Domino(previous));
    Thread thread1 = new Thread(new PrintState(previous, thread));
    thread1.setDaemon(true);

    thread1.start();
    thread.start();
        synchronized (Thread.currentThread()){
            Thread.sleep(1000);
        }
    Thread.sleep(1000);
    }

    static class PrintState implements Runnable{
        private Thread thread1;
        private Thread thread2;
        public PrintState(Thread thread1, Thread thread2){
            this.thread1 = thread1;
            this.thread2 = thread2;
        }

        public void run() {
            while (true){
                System.out.println(thread1.getName() + " state = " + thread1.getState());
                System.out.println(thread2.getName() + " state = " + thread2.getState());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Domino implements Runnable{
        private Thread thread;

        public Domino(Thread thread){
            this.thread = thread;
        }

        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + " terminate");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//output:
/*
main state = TIMED_WAITING
Thread-0 state = BLOCKED
main state = TIMED_WAITING
Thread-0 state = BLOCKED
main state = TIMED_WAITING
Thread-0 state = WAITING
main state = TIMED_WAITING
Thread-0 state = WAITING
main terminate
main state = TERMINATED
Thread-0 state = TIMED_WAITING
main state = TERMINATED
Thread-0 state = TIMED_WAITING
 */
/*刚开始main线程持有Thread-0线程实例的锁，由于在thread中调用thread.join方法需要获取Thread-0
实例的锁，所以刚开始Thread-0是blocked状态，后来main线程释放锁，join方法中拿到thread的锁以后调用了
thread这个Object对象的wait方法，故Thread-0转到等待队列，状态为WAITING。

 */