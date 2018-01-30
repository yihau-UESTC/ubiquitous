package SEBasic;

import java.util.Calendar;

public class GenericExtendsTest {

    public static void main(String[] args) {
//        Pair<? extends Employee> employeePair = new Pair<Manager>(new Manager(), new Manager());
//        Employee e = employeePair.getFirst();
//        employeePair.setFirst(new Manager());
//        employeePair.setFirst(new Employee());
//        Pair<? super Manager> managerPair = new Pair<Manager>(new Manager(), new Manager());
//        Employee ee = managerPair.getFirst();
//        Manager mm = managerPair.getFirst();
//        managerPair.setFirst(new Manager());
//        managerPair.setFirst(new CEO());
//        managerPair.setFirst(new Employee());
//        Calendar
    }


    static class Pair<T>{
        private T first;
        private T second;
        public Pair(T first, T second){this.first = first; this.second = second;}
        public T getFirst(){return this.first;}
        public T getSecond(){return this.second;}
        public void setFirst(T value){this.first = value;}
        public void setSecond(T value){this.second = value;}

        public static <T> Pair<T> makePair(Class<T> classa){
            try {
                return new Pair<>(classa.newInstance(),classa.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}

class Employee{

}

class Manager extends Employee{

}
class CEO extends Manager{

}