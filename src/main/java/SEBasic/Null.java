package SEBasic;

import org.junit.Test;

public class Null {

    private String s;
    //null 是所有引用类型的默认值就想int默认值为0一样
    @Test
    public void referenceDefaultValue(){
        Object o = null;
        System.out.println(s);
        System.out.println(o);
    }
    //null既不是对象也不是一种类型，null可以转化为任意类型,除了基本类型。
    public void nullIsWhat(){
        String str = (String) null;
        Double dou = (Double) null;
    }
    //null可以赋值给包装类，但是在包装类自动拆箱时却不会将null值自动转成对应基本类型的默认值，反而会抛出npe异常。
    @Test
    public void nullwithWrap(){
        Integer i = null;
        int ii = i;
        System.out.println(ii);
        //java.lang.NullPointerException
    }
    //instanceof对类型检查的作用。
    @Test
    public void nullwithInstanceof(){
        Integer i = null;
        if (i instanceof Integer){
            System.out.println("i is a Integer");
        }else {
            System.out.println("i isn't a Integer");
        }
        Integer j = (Integer)null;
        if (j instanceof Integer){
            System.out.println("j is a Integer");
        }else {
            System.out.println("j isn't a Integer");
        }
        System.out.println((String) null);
    }
    @Test
    public void nullwithCompare(){
        String abc = null;
        String cde = null;
        if (abc == cde){
            System.out.println("null == null is true");
        }
        if (abc != cde){
            System.out.println("null != null is false");
        }
        //不能使用大于小于号来比较
//        if (abc > null)
    }
}
