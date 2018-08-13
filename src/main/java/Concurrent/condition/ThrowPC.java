package Concurrent.condition;

public class ThrowPC {
    private int count;
    private final int capatity;

    public ThrowPC(int capatity) {
        this.capatity = capatity;
        count = 0;
    }

    public synchronized boolean add() throws Exception {
        if (count >= capatity)
            throw new Exception();
        count++;
        return true;
    }

    public synchronized boolean del() throws Exception {
        if (count <= 0)
            throw new Exception();
        count--;
        this.getClass().getClassLoader();
        return true;

    }
}
