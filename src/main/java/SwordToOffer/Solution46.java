package SwordToOffer;

import org.junit.Test;

/**
 * 约瑟夫环问题
 * 以前见过是用循环队列来做，没想到这居然是一道递归的问题，
 * 递归的公式
 * F(1) = 0
 * F(n) = (F(n - 1) + m) % n
 * 举一个例子，来解释这个递归公式
 * 假设n = 9, m = 4;
 * 0,1,2,3,4,5,6,7,8 -- 3出局--> 0,1,2,4,5,6,7,8(0,1,2)
 * 重新标记将4看做是0-->    0,1,2,3,4,5,6,7
 * 如何由重新编号得到原始编号-->0+m%n,...7+m%n
 * 所以有old = (new + m) % n
 */
public class Solution46 {
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0)
            return -1;
        if (n == 1)
            return 0;
        return (LastRemaining_Solution(n - 1, m) + m) % n;
    }

    @Test
    public void run() {
        System.out.println(LastRemaining_Solution(5, 3));
    }
}
