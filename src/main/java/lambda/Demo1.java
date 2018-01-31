package lambda;
//lambda 表达式实现Runnable 替换匿名类
public class Demo1 {

    public static void main(String args[]){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10; i++ ) {
                    System.out.println("Before java 8, too much code for too little to do");
                }
                }
        }).start();


        new Thread(() -> { for (int i = 0; i < 10; i++) System.out.println("In java 8, Lambda expression rocks!"); }).start();
    }
}
