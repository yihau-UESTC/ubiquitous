package NIO;

import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOExample {
    /**
     * 文件NIO读写实例
     *
     * @param src
     * @param dist
     */
    public void fileCopy(String src, String dist) {
        try (   //获取输入channel
                FileChannel fcin = new FileInputStream(src).getChannel();
                //获取输出channel
                FileChannel fcout = new FileOutputStream(dist).getChannel()) {
            //获取buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                int len = fcin.read(buffer);
                if (len == -1)
                    break;
                //转换buffer读写模式
                buffer.flip();
                fcout.write(buffer);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("copy done!");
        }
    }

    @Test
    public void Test() {
        String src = "E:\\code\\ubiquitous\\resource\\1.txt";
        String dist = "E:\\code\\ubiquitous\\resource\\2.txt";
        fileCopy(src, dist);
    }

    /**
     *
     */
    public void socketServer() {
        try {
            //打开Selector
            Selector selector = Selector.open();
            //打开ServerSocketChannel
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //设置ssc为非阻塞模式
            ssc.configureBlocking(false);
            //将ssc注册到selector上
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            //绑定端口
            ssc.bind(new InetSocketAddress("127.0.0.1", 9999));
            System.out.println("server start!");
            while (true) {
                selector.select();
                //返回就绪的key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    //accept就绪
                    if (key.isAcceptable()) {
                        //取出该key上绑定的channel即之前注册的ssc，执行accept()
                        SocketChannel socket = ((ServerSocketChannel) key.channel()).accept();
                        //这个socket对应着客户端的连接，同样配置成非阻塞模式
                        socket.configureBlocking(false);
                        //将该客户端连接注册到selector上，监听读事件
                        socket.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        //取出key绑定的channel，即上次注册的socket
                        SocketChannel socket = (SocketChannel) key.channel();
                        readData(socket);
                        //如果不需要继续读取，可以关闭
                        socket.close();
                    }
                    //注意处理完后需要remove该key，否则下次select时会返回已经处理过的key
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(SocketChannel socket) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder sb = new StringBuilder();
        while (true) {
            buffer.clear();
            int len = socket.read(buffer);
            if (len == -1)
                break;
            buffer.flip();
            sb.append(new String(buffer.array(), 0, len));
        }
        System.out.println(sb.toString());
    }

    @Test
    public void runServer() {
        socketServer();
    }

    @Test
    public void runClient() throws IOException {
        Socket client = new Socket("127.0.0.1", 9999);
        OutputStream outputStream = client.getOutputStream();
        outputStream.write("hello nio".getBytes());
        outputStream.close();
    }
}
