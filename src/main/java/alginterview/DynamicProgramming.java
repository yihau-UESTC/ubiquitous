package alginterview;

import org.junit.Test;

import java.util.*;

public class DynamicProgramming {
    /**
     * 70. Climbing Stairs,递归版本 Time Limit Exceeded
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 70. Climbing Stairs,记忆化搜索版本，自顶向下的方法，ac
     *
     * @param n
     * @return
     */
    public int climbStairsBaseMemo(int n) {
        int[] memo = new int[n + 1];
        return calcuWays(memo, n);
    }

    private int calcuWays(int[] memo, int n) {
        if (n == 0 || n == 1) return 1;
        if (memo[n] == 0) memo[n] = calcuWays(memo, n - 1) + calcuWays(memo, n - 2);
        return memo[n];
    }

    /**
     * 70. Climbing Stairs,动态规划版本，自底向上的方法，ac
     *
     * @param n
     * @return
     */
    public int climbStrirsBaseDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 120. Triangle, 保存所有中间状态
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = triangle.get(i).get(j);
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j] + dp[i][j], dp[i + 1][j + 1] + dp[i][j]);
            }
        }
        return dp[0][0];
    }

    @Test
    public void run1() {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3, 4));
        list.add(Arrays.asList(6, 5, 7));
        list.add(Arrays.asList(4, 1, 8, 3));

        int i = minimumTotal2(list);
        System.out.println(i);
    }

    /**
     * 120. Triangle, 只保存一行的状态，每计算新的一行覆盖原来的结果。
     *
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int m = triangle.size();//triangle的行和最下面一行的列相同
        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = triangle.get(m - 1).get(i);
        }
        for (int i = m - 2; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + list.get(j);
            }
        }
        return dp[0];
    }

    /**
     * 64. Minimum Path Sum 动态规划
     * 状态转移方程 dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[0][i];
            dp[0][i] = sum;
        }
        sum = 0;
        for (int i = 0; i < m; i++) {
            sum += grid[i][0];
            dp[i][0] = sum;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(Math.max(j * (i - j), j * dp[i - j]), dp[i]);
            }
        }
        return dp[n];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) dp[0][i] = 1;
            else break;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) dp[i][0] = 1;
            else break;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }

    @Test
    public void run3() {
        int[] arr = {4, 3, 1, 3};
        System.out.println(rob2(arr));
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        if (n <= 0) return 0;
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        //分成两种情况
        //1、抢0不抢n-1
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        int m1 = dp[n - 2];
        //2、抢n-1不抢0
        dp[0] = 0;
        dp[1] = nums[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        int m2 = dp[n - 1];
        return Math.max(m1, m2);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * rob 递归版本
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        if (root.left != null) {
            res += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            res += rob(root.right.left) + rob(root.right.right);
        }
        //抢劫根节点和子节点的子节点与抢劫两个子节点的大者
        return Math.max(res + root.val, rob(root.left) + rob(root.right));
    }

    /**
     * rob 记忆化搜索
     * 从递归的代码可以很容易看出有很多重复求解的地方，所以我们可以将此记录下来，
     * 使用一个hashMap来解决问题
     *
     * @param root
     * @return
     */
    public int robBaseMemo(TreeNode root) {
        return init(root, new HashMap<>());
    }

    private int init(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int res = 0;
        if (root.left != null) {
            res += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            res += rob(root.right.left) + rob(root.right.right);
        }
        //抢劫根节点和子节点的子节点与抢劫两个子节点的大者
        res = Math.max(res + root.val, rob(root.left) + rob(root.right));
        memo.put(root, res);
        return res;
    }

    /**
     * rob,动态规划版本
     * 之前的版本在到达一个新的节点时并不知道其子节点有没有被抢劫，只是知道抢的钱，丢失了抢该节点和不抢该节点的钱各式多少
     * 这里修改为返回一个数组，只有两个元素，1-》不抢该节点的最多钱，2-》抢该节点的最多钱。
     *
     * @param root
     * @return
     */
    public int robBaseDP(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        //节点为空，抢与不抢都是0
        if (root == null) return new int[2];
        int[] res = new int[2];
        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        //不抢当前节点，需要计算其子节点能得到的最大值(在抢与不抢中取大的)
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //抢当前节点，当前值+不抢子节点。
        res[1] = root.val + left[0] + right[0];
        return res;
    }

    @Test
    public void run4() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        t1.left = t2;
        rob(t1);
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 0) return 0;
        int[] s0 = new int[n];//未买入状态
        int[] s1 = new int[n];//买入状态
        int[] s2 = new int[n];//cooldown状态
        s0[0] = 0;
        s1[0] = -prices[0];
        s2[0] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);
            s1[i] = Math.max(s1[i - 1], s0[i - 1] + prices[i]);
            s2[i] = s1[i - 1] + prices[i];
        }
        return Math.max(s0[n - 1], s2[n - 1]);
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        int c = sum / 2;
        boolean[] dp = new boolean[c + 1];
        for (int i = 0; i <= c; i++) {
            dp[i] = (nums[0] == i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = c; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[c];
    }

    /**
     * 322. Coin Change 递归-》变记忆化搜索版本
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int res = findCoins(coins, amount, new HashMap<>());
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //寻找组成remian硬币的最少个数
    private int findCoins(int[] coins, int remain, Map<Integer, Integer> memo) {
        if (remain == 0) return 0;
        if (memo.containsKey(remain)) return memo.get(remain);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (remain >= coins[i]) {
                int l = findCoins(coins, remain - coins[i], memo);
                if (l != Integer.MAX_VALUE) {
                    res = Math.min(res, l + 1);
                    memo.put(remain, res);
                }
            }
        }
        return res;
    }

    @Test
    public void run5() {
        int[] arr = {1, 2, 5};
        int i = coinChangeBaseDP(arr, 100);
        System.out.println(i);
    }

    /**
     * 322. Coin Change, DP 版本
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeBaseDP(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) dp[i] = Integer.MAX_VALUE;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < n; j++) {
                if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}
