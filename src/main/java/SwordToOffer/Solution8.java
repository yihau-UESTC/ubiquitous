package SwordToOffer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
 * 考点：动态规划
 * 经典的动态规划例子，和斐波那契数列很像,状态是跳到某个台阶的跳法
 * 状态转移方程f(n) = f(n - 1) + f(n - 2)
 * 复杂度O(N) + O(N)
 */
public class Solution8 {
    public int JumpFloor(int target) {
        if (target == 0) return 1;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }
}
