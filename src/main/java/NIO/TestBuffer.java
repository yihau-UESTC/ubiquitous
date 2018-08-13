package NIO;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午5:40 18-1-21
 * @Description:
 */
public class TestBuffer {

    @Test
    public void test1(){
        ByteBuffer buf  = ByteBuffer.allocate(1024);
        System.out.println(buf.mark());

        String str = "abcde";
        buf.put(str.getBytes());
        System.out.println(buf.mark());

        buf.flip();
        System.out.println(buf.mark());

        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));
        System.out.println(buf.mark());
        buf.rewind();
        System.out.println(buf.mark());
        buf.clear();
        System.out.println(buf.mark());
        System.out.println(buf.get());
        ByteBuffer.allocateDirect(1024);

    }

    @Test
    public void run() throws IOException {
        RandomAccessFile file = new RandomAccessFile("E:\\code\\ubiquitous\\resource\\1.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int read = channel.read(buf);
        while (read != -1) {
            System.out.println("read " + read);
            //翻转模式
            buf.flip();
            while (buf.hasRemaining()) {
                byte[] bytes = new byte[1024];
                buf.get(bytes, 0, read);
                System.out.println(new String(bytes));
            }
            System.out.println("finish");
            buf.clear();
            read = channel.read(buf);
        }
    }

}
