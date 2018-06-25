package SEBasic;

public class Test {
    public static void main(String[] args) {
        int i = 513;
        i--;
        i |= i >>> 1;
        System.out.println(Integer.toBinaryString(i));
        i |= i >>> 2;
        System.out.println(Integer.toBinaryString(i));
        i |= i >>> 4;
        System.out.println(Integer.toBinaryString(i));
        i |= i >>> 8;
        System.out.println(Integer.toBinaryString(i));
        i |= i >>> 16;
        System.out.println(Integer.toBinaryString(i));
        i++;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(i);

    }
}
