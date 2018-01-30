package SEBasic;

import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generic {
    private List<String> slist = new ArrayList<>();

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException {
        Type t = Generic.class.getDeclaredField("slist").getGenericType();
        Type[] types1 = ((ParameterizedType)t).getActualTypeArguments();
        for (Type tt : types1){
            System.out.println(tt + " ");
        }

        System.out.println(String.class.getSuperclass().getName());
        Class<?>[] cc = String.class.getInterfaces();
        for (Class<?> c : cc){
            System.out.println(c.getName());
        }
        System.out.println("#######################");
        Type[] t1 = String.class.getGenericInterfaces();
        Type t11 = t1[1];
        if (ParameterizedType.class.isAssignableFrom(t11.getClass())){
           Type[] tz = ((ParameterizedType) t11).getActualTypeArguments();
                for (Type temp : tz){
                    System.out.println(temp);
                }
            }

    }




}
