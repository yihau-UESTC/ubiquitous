package SEBasic.IO;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午4:04 18-1-23
 * @Description:
 */
public class TestFile {
    @Test
    public void test1() throws IOException {
        InputStream inputStream = new FileInputStream("t1.txt");
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[1024];
                int len = 0;
                try {
                    while ((len = inputStream.read(bytes)) != -1){
                        pipedOutputStream.write(bytes, 0, len);
                    }
                    try {
                        System.out.println(1);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        pipedInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[1024];
                int len = 0;
                try {
                    while ((len = pipedInputStream.read(bytes)) != -1){
                        System.out.println(new String(bytes, 0, len));
                    }
                    try {
                        System.out.println(2);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        pipedOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();

        try {
            System.out.println(3);
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
