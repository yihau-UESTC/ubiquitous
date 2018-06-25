package SEBasic;

import java.lang.reflect.Field;

public class MyObject {
    private String name;
    private int num;

    public MyObject(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public static void main(String[] args) {
        MyObject object = new MyObject("YIHAU", 2);
        try {
            Field field = object.getClass().getDeclaredField("name");
            System.out.println(field.isAccessible());
            Object name = field.get(object);
            System.out.println();

            System.out.println((String) name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
