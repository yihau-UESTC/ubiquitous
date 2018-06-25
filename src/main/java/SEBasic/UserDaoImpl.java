package SEBasic;

import org.junit.Test;

public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save data");
    }

    @Override
    public void update() {
        System.out.println("update data");
    }


    @Test
    public void run() {
        UserDao dao = new UserDaoImpl();
        UserDao proxy = MyProxyUtils.getProxy(dao);
        proxy.save();
        proxy.update();
        System.out.println(Math.ceil(2.1));
    }
}
