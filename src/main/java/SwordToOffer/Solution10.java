package SwordToOffer;

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 考点：动态规划
 * 本题看似很难，但是分析过后和跳台阶是一样的，
 * 状态：n的方法数
 * 状态转移方程f(n) = f(n - 1) + f(n - 2)
 * 因为当前的方法数，可以分为n - 1时 + 一个竖着放置的小矩形 和 n - 2时 + 2个横着放置的小矩形，为啥n-2时不计算竖着放置两个小矩形
 * 这是因为会与n-1的情况相同。
 * 复杂度O(N) + O(N)
 */
public class Solution10 {
    public int RectCover(int target) {
        if (target == 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target];
    }
}
