package SEBasic;

public class Person {
    public Person() {
    }
}

class Student extends Person {
    public Student() {
    }

    public void print() {
        System.out.println(this.hashCode());
        System.out.println(super.hashCode());
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.print();
    }
}

