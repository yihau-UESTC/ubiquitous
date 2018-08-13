package NettyGuideDemo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = new Socket();
        OutputStream outputStream = null;
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 8989));
            outputStream = socket.getOutputStream();
            outputStream.write("hello".getBytes());
            outputStream.flush();
            System.out.println("finish");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
