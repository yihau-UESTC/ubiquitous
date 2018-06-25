package SEBasic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyUtils {

    public static UserDao getProxy(final UserDao dao) {
        UserDao proxy = (UserDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(), dao.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("save")) System.out.println("save");
                        method.invoke(dao, args);
                        return null;
                    }
                });
        return proxy;
    }
}
