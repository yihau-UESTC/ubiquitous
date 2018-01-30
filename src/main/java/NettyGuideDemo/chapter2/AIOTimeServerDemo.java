package NettyGuideDemo.chapter2;

public class AIOTimeServerDemo {
    public static void main(String[] args) {
        int port = 8888;
        AsyncTimeServerHandle timeServerHandle = new AsyncTimeServerHandle(port);
        new Thread(timeServerHandle).start();
    }
}
