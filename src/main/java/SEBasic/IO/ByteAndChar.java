package SEBasic.IO;

import java.io.UnsupportedEncodingException;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午3:10 18-1-22
 * @Description:
 */
public class ByteAndChar {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String str = "你";
//        int a = 129;
//
//
//        System.out.println("字符长度：" + str.length());
//        System.out.println("字节长度：" + str.getBytes("gbk").length);
//        System.out.println("encode : " + System.getProperty("file.encoding"));
//        byte[] bytes = str.getBytes();
//        for (byte b : bytes){
//            System.out.println(Integer.toHexString(b));
//        }
//        byte[] z = int2Bytes(a, 3);
//        int x = bytes2Int(z, 0, 3);
//        System.out.println(x);
        System.out.println((1000>>>8) & 0xFF);


    }

    public static int bytes2Int(byte[] b, int start, int len){
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++){
            int n = ((int)b[i]) & 0xff;
            n <<= (--len) * 8;
            sum += n;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value, int len){
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++){
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }

}
