package SwordToOffer;

import org.junit.Test;

/**
 * 斐波那契数列，f(n) = f(n - 1) + f(n - 2);f(0) = 1, f(1) = 1;
 * 1、递归--->重复求解子问题
 * 2、记忆化搜索--->记录每次求解的问题的值，再次需要的时候直接取
 * 3、动态规划--->自底向上的方法。下面的方法是优化了空间复杂度的动态规划方法。
 * 时间复杂度O(N)+O(1)
 */
public class Solution7 {
    public int Fibonacci(int n) {
        int i1 = 0, i2 = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                i1 = i1 + i2;
            } else {
                i2 = i1 + i2;
            }
        }
        return n % 2 == 0 ? i1 : i2;
    }

    @Test
    public void run() {
        System.out.println(Fibonacci(4));
    }
}
