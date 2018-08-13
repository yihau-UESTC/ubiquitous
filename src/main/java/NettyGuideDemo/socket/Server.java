package NettyGuideDemo.socket;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8989);
            while (true) {
                Socket client = null;
                InputStream is = null;
                try {
                    System.out.println("wait a client");
                    client = server.accept();
                    System.out.println("get a client");
                    is = client.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        String string = new String(buffer, "utf-8");
                        System.out.println(string);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    is.close();
                    client.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
