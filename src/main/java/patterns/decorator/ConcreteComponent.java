package patterns.decorator;

/**
 * 具体组件的实现接口，可以有多种不同原始组件
 */
public class ConcreteComponent implements Component {
    @Override
    public void action() {
        System.out.println("组件原始功能。。。");
    }
}
