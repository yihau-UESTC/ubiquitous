package Concurrent.ThreadPoolExample;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        SimpleHttpServer.setBasePath("E:/code/src/main/java/Concurrent/ThreadPoolExample");
        SimpleHttpServer.setPort(9999);
        SimpleHttpServer.start();
    }
}
