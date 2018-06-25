package patterns.observer;

import java.util.ArrayList;

public class Number implements Subject {

    private int state;
    private ArrayList<Observer> observers;

    public Number() {
        this.observers = new ArrayList<>();
    }

    public void changeState(int n) {
        this.state = n;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : this.observers) {
            observer.update(state);
        }
    }

    public static void main(String[] args) {
        Number number = new Number();
        NumberObserver1 observer1 = new NumberObserver1(number);
        NumberObserver2 observer2 = new NumberObserver2(number);
        number.changeState(5);
        number.changeState(11);
    }
}
