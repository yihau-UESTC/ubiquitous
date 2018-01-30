package SEBasic;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;

public class GenericImpl<T> implements GenericInterface<T>{
    @Override
    public void getRealT() {
        System.out.println(this.getClass().getInterfaces().toString());
        System.out.println(this.getClass().getGenericInterfaces());
        Type[] types = this.getClass().getGenericInterfaces();
        for (Type tt : types){
            System.out.println(tt.getTypeName());
        }
    }

    public static <T> T getMiddle(T...a){
        return a[a.length/2];
    }
//    public static T getMidd(T...a){
//        return a[a.length/2];
//    }

    public static <T> T get(){
        System.out.println("asdfsadg");
        return (T)"sdf";
    }


    public static <T extends Comparable<? super T>> T min(T...a){
        if (a == null || a.length == 0)return null;
        T smallest = a[0];
        for (int i = 1; i < a.length; i++){
            if (smallest.compareTo(a[i]) > 0)smallest = a[i];
        }
        return smallest;
    }

    public static void main(String[] args) {

        Type[] types =  HashMap.class.getTypeParameters();
        for (Type t : types){
            System.out.println(t.getTypeName());
        }
    }

    static class Pair<T>{
        private T first;
        private T second;
        public Pair(T first, T second){this.first = first; this.second = second;}
        public T getFirst(){return this.first;}
        public T getSecond(){return this.second;}
        public void setFirst(T value){this.first = value;}
        public void setSecond(T value){this.second = value;}

        public static <T> Pair<T> makePair(Class<T> classa){
            try {
                return new Pair<>(classa.newInstance(),classa.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}


