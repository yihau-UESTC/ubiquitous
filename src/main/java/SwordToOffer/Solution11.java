package SwordToOffer;

import org.junit.Test;

/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * 考点：位运算
 * n&(n - 1)可以去除整数最右边的一位1；使用循环每次移除最右边的1
 * java的Integer中有个函数直接可以返回1的位数，Integer.bitCount(n);
 */
public class Solution11 {
    public int NumberOf1(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n = n & n - 1;
        }
        return res;
    }

    @Test
    public void run() {
        System.out.println(NumberOf1(5));
    }
}
