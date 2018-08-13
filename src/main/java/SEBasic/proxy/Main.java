package SEBasic.proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);
        UserService proxy = (UserService) Proxy.newProxyInstance(Main.class.getClassLoader(),
                userService.getClass().getInterfaces(), myInvocationHandler);
        System.out.println(proxy.getName());
//        System.out.println(proxy.getAge());
    }
}
