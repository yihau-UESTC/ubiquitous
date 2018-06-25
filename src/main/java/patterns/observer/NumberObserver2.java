package patterns.observer;

public class NumberObserver2 implements Observer<Integer> {
    private Subject number;

    public NumberObserver2(Subject number) {
        this.number = number;
        number.registerObserver(this);
    }

    @Override
    public void update(Integer obj) {
        System.out.println("Observer2 --->" + obj);
    }
}
