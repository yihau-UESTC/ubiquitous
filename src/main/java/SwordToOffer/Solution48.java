package SwordToOffer;

import org.junit.Test;

/**
 * 不用加减乘除做加法
 * 一般这种都是使用位运算来实现的，
 * a^b 可以表示不算进位的情况下的加法
 * (a&b)<<1 可以表示进位情况，
 * 然后将这两者的答案相加就得到所求了
 * 那么这两者的答案如何相加，那就可以递归调用，
 * 直到，两者没有进位即b==0的时候递归就可以终止了。
 */
public class Solution48 {
    public int Add(int num1, int num2) {
        return num2 == 0 ? num1 : (Add(num1 ^ num2, (num1 & num2) << 1));
    }
}