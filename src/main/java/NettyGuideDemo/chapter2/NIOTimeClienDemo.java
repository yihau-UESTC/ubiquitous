package NettyGuideDemo.chapter2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOTimeClienDemo {
    public static void main(String[] args) {
        int port = 9999;
        new Thread(new TimeClientHandle("127.0.0.1", port)).start();
    }

    static class TimeClientHandle implements Runnable{
        private String host;
        private int port;
        private Selector selector;
        private SocketChannel socketChannel;
        private volatile boolean stop;

        public TimeClientHandle(String host, int port){
            this.host = host;
            this.port = port;
            try {
                this.selector = Selector.open();
                this.socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);

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
            try {
                doConnect();
            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }

            while (!stop){
                try {
                    selector.select(1000);
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
                    System.exit(1);
                }
            }

        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()){
                SocketChannel socketChannel = (SocketChannel) key.channel();
                if (key.isConnectable()){
                    if (socketChannel.finishConnect()){
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        doWrite(socketChannel);
                    }else
                        System.exit(1);
                }
                if (key.isReadable()){
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int readBytes = socketChannel.read(byteBuffer);
                    if (readBytes > 0){
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);
                        String body = new String(bytes, "UTF-8");
                        System.out.println("Now is : " + body);
                        setStop();
                    }else if (readBytes < 0 ){
                        key.cancel();
                        socketChannel.close();
                    }else
                        ;

                }
            }
        }

        private void doConnect() throws IOException {
            if (socketChannel.connect(new InetSocketAddress(this.host,this.port))){
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);
            }else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }

        private void doWrite(SocketChannel socketChannel) throws IOException {
            byte[] bytes = "QUERY TIME ORDER".getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            if (!byteBuffer.hasRemaining()){
                System.out.println("Send order 2 server succeed.");
            }
        }
    }
}
