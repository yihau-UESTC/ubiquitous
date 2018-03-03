package NettyGuideDemo.chapter2;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NIOTimeServerDemo {

    public static void main(String[] args) {
        int port = 9999;
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        new Thread(multiplexerTimeServer).start();
    }

    static class MultiplexerTimeServer implements Runnable{
        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private volatile boolean stop;

        public MultiplexerTimeServer(int port){
            try {
                this.selector = Selector.open();
                this.serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);//第二个参数是最大的等待连接的个数。
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("The time server is start in port : " + port);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        public void setStop(){
            this.stop = true;
        }

        @Override
        public void run() {
            while (!stop){
                try {
                    selector.select(1000);//设置阻塞1s
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (iterator.hasNext()){
                        key = iterator.next();
                        iterator.remove();
                        try {
                            handleInput(key);
                        }catch (Exception e){
                            if (key != null){
                                key.cancel();
                                if (key.channel() != null){
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (selector != null){
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()){
                if (key.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int readBytes = 0;
                    if ((readBytes = socketChannel.read(byteBuffer)) != -1){
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);
                        String body = new String(bytes, "UTF-8");
                        System.out.println("The time server receive order : " + body);
                        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
                        doWrite(socketChannel, currentTime);
                    }else {
                        key.cancel();
                        socketChannel.close();
                    }
                }
            }
        }

        private void doWrite(SocketChannel socketChannel, String currentTime) throws IOException {
            if (currentTime != null && currentTime.trim().length() > 0){
                byte[] bytes = currentTime.getBytes();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.put(bytes);
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
            }
        }
    }
}
