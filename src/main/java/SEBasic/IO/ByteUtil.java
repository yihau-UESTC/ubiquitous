package SEBasic.IO;

import org.junit.Test;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午3:24 18-1-23
 * @Description:
 * short,char,int,long,float,double 与 byte数组之间的转换
 * short -> 2
 * char -> 2
 * int -> 4
 * long -> 8
 * float -> 4
 * double -> 8
 * 这里使用的大端存储，即高位字节在前，低位字节在后的存储方式。
 */
public class ByteUtil {
    /**
     * 高位左移8位，1个字节，然后|组合两个字节。
     * @param b
     * @return
     */
    public static short bytes2short(byte[] b){
        if (b.length != 2)throw new IllegalArgumentException("byte[] length should be two");
        return (short) ((b[0] << 8 & 0xff00) | (b[1] &0xff));
    }

    public static char bytes2char(byte[] b){
        if (b.length != 2)throw new IllegalArgumentException("byte[] length should be two");
        return (char) ((b[0] << 8 & 0xff00) | (b[1] &0xff));
    }

    public static int bytes2int(byte[] b){
        if (b.length != 4)throw new IllegalArgumentException("byte[] length should be four");
        return (b[0] << 24 & 0xff000000) | (b[1] << 16 & 0xff0000) | (b[2] << 8 & 0xff00) | (b[3] & 0xff);
    }

    public static long bytes2long(byte[] b){
        if (b.length != 8)throw new IllegalArgumentException("byte[] length should be eight");
        return (((long)b[0] << 56) & 0xff00000000000000L) | (((long)b[1] << 48) & 0xff000000000000L)
                | (((long)b[2] << 40) & 0xff0000000000L) | (((long)b[3] << 32) & 0xff00000000L)
                | (((long)b[4] << 24) & 0xff000000L) | (((long)b[5] << 16) & 0xff0000L) | (((long)b[6] << 8) & 0xff00L)
                | (((long)b[7]) & 0xffL);
    }

    public static float bytes2float(byte[] b){
        return Float.intBitsToFloat(bytes2int(b));
    }

    public static double bytes2double(byte[] b){
        return Double.longBitsToDouble(bytes2long(b));
    }

    /**
     * 取高8位存储在前。
     * @param d
     * @return
     */
    public static byte[] short2bytes(short d){
        byte[] b = new byte[2];
        b[0] = (byte) ((d &0xff00) >> 8);
        b[1] = (byte) (d &0xff);
        return b;
    }

    public static byte[] char2bytes(char d){
        byte[] b = new byte[2];
        b[0] = (byte) ((d &0xff00) >> 8);
        b[1] = (byte) (d &0xff);
        return b;
    }

    public static byte[] int2bytes(int d){
        byte[] b = new byte[4];
        b[0] = (byte) ((d & 0xff000000) >> 24);
        b[1] = (byte) ((d & 0xff0000) >> 16);
        b[2] = (byte) ((d & 0xff00) >> 8);
        b[3] = (byte) ((d & 0xff) >> 0);
        return b;
    }

    public static byte[] long2bytes(long d){
        byte[] b = new byte[8];
        b[0] = (byte) ((d & 0xff00000000000000L) >> 56);
        b[1] = (byte) ((d & 0xff000000000000L) >> 48);
        b[2] = (byte) ((d & 0xff0000000000L) >> 40);
        b[3] = (byte) ((d & 0xff00000000L) >> 32);
        b[4] = (byte) ((d & 0xff000000L) >> 24);
        b[5] = (byte) ((d & 0xff0000) >> 16);
        b[6] = (byte) ((d & 0xff00) >> 8);
        b[7] = (byte) ((d & 0xff) >> 0);
        return b;
    }

    public static byte[] float2bytes(float d){
        return int2bytes(Float.floatToIntBits(d));
    }

    public static byte[] double2bytes(double d){
        return long2bytes(Double.doubleToLongBits(d));
    }

    @Test
    public void test(){
        short s = 3;
        char c = 'a';
        int i = 123;
        long l = 12222222;
        float f = 2.33f;
        double d = 122.33;

        System.out.println(bytes2short(short2bytes(s)));
        System.out.println(bytes2char(char2bytes(c)));
        System.out.println(bytes2int(int2bytes(i)));
        System.out.println(bytes2long(long2bytes(l)));
        System.out.println(bytes2float(float2bytes(f)));
        System.out.println(bytes2double(double2bytes(d)));
    }

}
