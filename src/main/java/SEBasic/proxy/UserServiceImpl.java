package SEBasic.proxy;

public class UserServiceImpl implements UserService {
    @Override
    public String getName() {
        System.out.println("--------getName----------");
        getAge();
        return "yihau";
    }

    public int getAge() {
        System.out.println("----------getAge---------------");
        return 11;
    }
}
