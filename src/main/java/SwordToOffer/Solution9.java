package SwordToOffer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 考点：动态规划
 * 跟上一题一样，就是上台阶的方法变多了，
 * 状态：在某一节台阶上的跳法
 * 状态转移方程 f(n) = f(n - 1) + ...f(0);
 * 复杂度O(N^2)+O(N)
 */
public class Solution9 {
    public int JumpFloorII(int target) {
        if (target == 0) return 1;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[target];
    }
}
