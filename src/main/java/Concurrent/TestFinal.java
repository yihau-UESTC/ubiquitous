package Concurrent;

public class TestFinal {
    public int i = 0;

    public synchronized void setI(int i){
        this.i = i;
    }

    public static void main(String[] args) {
        new TestFinal().setI(5);
    }
}
