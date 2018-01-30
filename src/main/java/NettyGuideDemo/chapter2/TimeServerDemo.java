package NettyGuideDemo.chapter2;

import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeServerDemo {

    @Test
    public void blockedServer(){
        int port = 8888;
        try(ServerSocket serverSocket = new ServerSocket(port);) {
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            while (true){
                socket = serverSocket.accept();
                new Thread(new TimeServerHandle(socket)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void pseudoAsyServer(){
        int port = 8888;
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (true){
                socket = serverSocket.accept();
                executorService.execute(new TimeServerHandle(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void client(){
        try (Socket socket = new Socket("localhost", 8888);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
            out.println("QUERY TIME ORDER");
            System.out.println("Send order 2 server succeed.");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class TimeServerHandle implements Runnable {
        private Socket socket;

        public TimeServerHandle(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)){
                String currentTime = null;
                String body = null;

                while (true){
                    body = in.readLine();
                    if (body == null)break;
                    System.out.println("The time server receive order : " + body);
                    currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
                            System.currentTimeMillis()).toString() : "BAD ORDER";
                    out.println(currentTime);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
