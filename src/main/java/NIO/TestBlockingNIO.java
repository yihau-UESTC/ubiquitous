package NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author: yihau UESTC
 * @Date: Created in 上午10:11 18-1-22
 * @Description:
 * Selector SelectableChannel 的多路复用器，用于监控SelectableChannel的IO状况
 * 非阻塞IO只用于网络IO
 * SelectableChannel
 *  |--socketChannel
 *  |--serverSocketChannel
 *  |--datagramChannel
 */
public class TestBlockingNIO {

    @Test
    public void client(){
        //通道
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        //缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            //打开通道
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
            fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            //将文件中的数据写到网络通道中
            while (fileChannel.read(byteBuffer) != -1){
                byteBuffer.flip();//改为写模式
                socketChannel.write(byteBuffer);
                byteBuffer.clear();//重置buf中的几个指针，用于循环读取
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void server(){
        ServerSocketChannel serverSocketChannel = null;
        FileChannel fileChannel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            fileChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8888));
            socketChannel = serverSocketChannel.accept();
            while (socketChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                fileChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
