package NIO;

import org.junit.Test;

import java.nio.ByteBuffer;

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

}
