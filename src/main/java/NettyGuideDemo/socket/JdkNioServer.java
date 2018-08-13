package NettyGuideDemo.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class JdkNioServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            Selector selector = Selector.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(8990));
            server.register(selector, 1024);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
