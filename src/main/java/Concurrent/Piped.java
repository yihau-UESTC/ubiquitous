package Concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.locks.ReentrantLock;

public class Piped {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();
        int receive = 0;

       try {
           while ((receive = System.in.read()) != -1){//从System.in流读取字符写入到管道输出流，管道输出流链接则一个管道输入流
               out.write(receive);
           }
       }finally {
           out.close();
       }

    }
    static class Print implements Runnable{
        private PipedReader in;

        public Print(PipedReader in){
            this.in = in;
        }
        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1){//从管道输入流读取字符在输出。
                    System.out.print((char)receive);
                }
            }catch (IOException ex){

            }
        }
    }
}
