package patterns.decorator;

/**
 * 装饰者的抽象基类，继承自被装饰者同时组合引用
 */
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void action() {
        this.component.action();
    }
}
