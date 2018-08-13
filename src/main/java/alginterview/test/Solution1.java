package alginterview.test;

public class Solution1 {
    public static int method(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++)
            dp[i] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }
        int max = 0;
        for (int i : dp)
            max = Math.max(max, i);
        return max;
    }

    public static void main(String[] args) {
        int[] ints = {10, 4, 5, 12, 8};
        int method = method(ints);
        System.out.println(method);
    }
}
