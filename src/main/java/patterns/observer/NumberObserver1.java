package patterns.observer;

public class NumberObserver1 implements Observer<Integer> {
    private Subject number;

    public NumberObserver1(Subject number) {
        this.number = number;
        number.registerObserver(this);
    }

    @Override
    public void update(Integer obj) {
        System.out.println("Observer1 --->" + obj);
    }
}
