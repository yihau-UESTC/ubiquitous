package patterns.decorator;

import java.io.*;

/**
 * 装饰者的具体实现类，可以添加不同的装饰者。
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void action() {
        super.action();
        this.addAction();
    }

    public void addAction() {
        System.out.println("装饰功能。。。");
    }

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Decorator decorator = new ConcreteDecorator(component);
        decorator.action();
        try {
            //这里InputStream是原始组件抽象类，FileInputStream是具体的组件类
            InputStream stream = new FileInputStream(new File("123.txt"));
            //FilterInputStream是装饰类基类，其中保存了InputStream的引用，BufferedInputStream是具体的装饰类，装饰的功能是添加缓冲功能
            FilterInputStream bufferedInputStream = new BufferedInputStream(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
