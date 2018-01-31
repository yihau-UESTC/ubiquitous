package Concurrent;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午9:31 17-12-25
 * @Description: thread.start()执行完后，这个程序中已经没有非Daemon守护线程了，所以程序会退出，但是在守护线程中的finally块中的内容并不会执行。
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("DaemonThread finally run");
            }
        }
    }
}
