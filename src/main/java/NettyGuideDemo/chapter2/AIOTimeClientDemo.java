package NettyGuideDemo.chapter2;

public class AIOTimeClientDemo {
    public static void main(String[] args) {
        int port = 8888;
        new Thread(new AsyncTimeClientHandler("127.0.0.1", port)).start();
    }
}
