package SEBasic.IO;

import org.junit.Test;

import java.io.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午5:28 18-1-22
 * @Description:
 */
public class TestStream {
    @Test
    public void test1() throws IOException {
        //获取输入流，这里的输入输出是以程序为主体的，输入程序则为输入
        InputStream inputStream = new FileInputStream("t1.txt");
        //获取输出流
        OutputStream outputStream = new FileOutputStream("t2.txt",true);
        //开辟一个byte数组用来存放一次输入输出的字节数
        byte[] b = new byte[1024];
        int num = 0;
        //读
        while ((num = inputStream.read()) != -1){
            //写
            outputStream.write(num);
            outputStream.flush();
        }

        //关闭流
        inputStream.close();
        outputStream.close();
    }

    @Test
    public void test2() throws IOException {
        Reader reader = new FileReader("t1.txt");
        Writer writer = new FileWriter("t2.txt");
        int data = 0;
        int i = 0;
        while ((data = reader.read()) != -1){
            System.out.println(i++);
            writer.write(data);
            writer.flush();
        }

        writer.close();
        reader.close();
    }

    @Test
    public void test3() throws IOException, BrokenBarrierException, InterruptedException {
        Writer writer = new FileWriter("t3.txt");
        CyclicBarrier cb = new CyclicBarrier(3);
        CountDownLatch cl = new CountDownLatch(2);
        new Thread(new Task(writer,cb,cl)).start();
        new Thread(new Task(writer, cb,cl)).start();
        cl.await();
        cb.await();
        System.out.println("finish");


    }

    @Test
    public void test4() throws IOException, BrokenBarrierException, InterruptedException {
        OutputStream outputStream = new FileOutputStream("t4.txt");
        CyclicBarrier cb = new CyclicBarrier(3);
        CountDownLatch cl = new CountDownLatch(2);
        new Thread(new Task1(outputStream,cb,cl)).start();
        new Thread(new Task1(outputStream, cb,cl)).start();
        cl.await();
        cb.await();
        System.out.println("finish");


    }

    static class Task implements Runnable{
        private Writer writer;
        private CyclicBarrier cyclicBarrier;
        private CountDownLatch countDownLatch;

        public Task(Writer writer, CyclicBarrier cb, CountDownLatch cl){
            this.writer = writer;
            this.cyclicBarrier = cb;
            this.countDownLatch = cl;
        }

        @Override
        public void run() {
            char[] chars = {'a','b','c','d','e','f','g','h','i','j'};
           countDownLatch.countDown();
            for (int i = 0; i < 10; i++){
                try {
                    writer.write(chars[i]);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    static class Task1 implements Runnable{
        private OutputStream outputStream;
        private CyclicBarrier cyclicBarrier;
        private CountDownLatch countDownLatch;

        public Task1(OutputStream outputStream, CyclicBarrier cb, CountDownLatch cl){
            this.outputStream = outputStream;
            this.cyclicBarrier = cb;
            this.countDownLatch = cl;
        }

        @Override
        public void run() {
            char[] chars = {'a','b','c','d','e','f','g','h','i','j'};
            countDownLatch.countDown();
            for (int i = 0; i < 10; i++){
//                Charset cs = Charset.defaultCharset();
//                byte[] bytes = cs.encode(CharBuffer.allocate(chars[i])).array();
                try {
//                    outputStream.write(bytes, 0, bytes.length);
                    outputStream.write(chars[i] & 0xff);
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] int2Bytes(int value, int len){
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++){
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }


    @Test
    public void randomAccessFile(){
        try(RandomAccessFile randomAccessFile = new RandomAccessFile("t1.txt","rw");){
            long pointer = randomAccessFile.getFilePointer();
            System.out.println(pointer);
            int data = randomAccessFile.read();
            System.out.println((char)(data & 0xff));
            randomAccessFile.seek(4);
            System.out.println(randomAccessFile.getFilePointer());
            data = randomAccessFile.read();
            System.out.println((char)(data & 0xff));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void ByteArrayStream() throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("object.data"));
        objectOutputStream.writeObject(new MyObject());
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.data"));
        System.out.println(objectInputStream.readObject());
    }

    static class MyObject implements Serializable{

    }
}
