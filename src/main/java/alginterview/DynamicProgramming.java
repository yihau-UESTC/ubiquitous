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

    /**
     * 377. Combination Sum IV
     * 状态转移方程
     * f(target) = f(target - nums[1]) + f(target - nums[2]) + ... + f(target - nums[n])
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] <= target) dp[nums[i]] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    @Test
    public void run6() {
        int[] arr = {9};
        int i = combinationSum4(arr, 3);
        System.out.println(i);
    }

    /**
     * 474. Ones and Zeroes
     * 这是一个背包问题的变种，相当于有n个字符串strs[]，
     * 把这n个字符串放入有m和n来限制的"棋盘"中，能放入的最多的字符串的数量
     * 相当于有三个限制条件,状态转移方程。
     * F(s,m,n) = Max(F(s - 1, m, n), F(s - 1, m - s0, n - s1) + 1)
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (m == 0 && n == 0) return 0;
        List<int[]> count = new ArrayList<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            int c0 = 0, c1 = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '0') c0++;
                if (chars[i] == '1') c1++;
            }
            count.add(new int[]{c0, c1});
        }
        int[][][] dp = new int[count.size() + 1][m + 1][n + 1];
        int[] cur = new int[]{0, 0};
        for (int k = 0; k <= count.size(); k++) {
            if (k > 0) {
                cur = count.get(k - 1);
            }
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (k == 0) dp[k][i][j] = 0;
                    else if (i >= cur[0] && j >= cur[1]) {
                        dp[k][i][j] = Math.max(dp[k - 1][i][j], dp[k - 1][i - cur[0]][j - cur[1]] + 1);
                    } else {
                        dp[k][i][j] = dp[k - 1][i][j];
                    }
                }
            }
        }
        return dp[count.size()][m][n];
    }

    /**
     * 474. Ones and Zeroes
     * 压缩动态规划使用的空间，可以从三维数组压缩到二维
     * 为什么m和n是从后往前遍历，其实空间压缩很多都是从后往前，因为需要用到的dp[i - cur[0]][j - cur[1]]
     * 是比当前的i和j要小的，所以从前往后更新会覆盖掉之前上一层的数据，但从后往前不会。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxFormReduceSpace(String[] strs, int m, int n) {
        if (m == 0 && n == 0) return 0;
        List<int[]> count = new ArrayList<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            int c0 = 0, c1 = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '0') c0++;
                if (chars[i] == '1') c1++;
            }
            count.add(new int[]{c0, c1});
        }
        int[][] dp = new int[m + 1][n + 1];

        for (int k = 0; k < count.size(); k++) {
            int[] cur = count.get(k);
            for (int i = m; i >= cur[0]; i--) {
                for (int j = n; j >= cur[1]; j--) {
                    if (i >= cur[0] && j >= cur[1]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - cur[0]][j - cur[1]] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }

    @Test
    public void run7() {
        String[] str = {"10", "0001", "111001", "1", "0"};
        System.out.println(findMaxFormReduceSpace(str, 5, 3));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int m = s.length();
        int n = wordDict.size();
        boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = 0; i <= n; i++) dp[0][i] = true;
        for (int i = 1; i <= m; i++) dp[i][0] = false;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                String s1 = wordDict.get(j - 1);
                if (i >= s1.length()) {
                    String cur = s.substring(i - s1.length(), i);
                    dp[i][j] = dp[i][j] || (dp[i - s1.length()][j] && cur.equals(s1));
                }
            }
        }
        return dp[m][n];
    }

    @Test
    public void run8() {
        List<String> list = Arrays.asList("apple", "pen");
        boolean leetcode = wordBreak("applepenapple", list);
        System.out.println(leetcode);
    }

    /**
     * 494. Target Sum，类似背包问题，求由n个数可以组成某个S的方法数
     * 其中每个数有+或-两种方式
     * 状态转移方程为
     * F(i, s) = F(n - 1, s - nums[i]) + F(n - 1, s + nums[i]);
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int i : nums) sum += i;
        //防止S越界的情况
        if (Math.abs(sum) < Math.abs(S)) return 0;
        int[][] dp = new int[n + 1][2 * sum + 1];
        //0个数组成0的情况为1，其他情况都不能由0个数组成
        dp[0][sum] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 2 * sum; j++) {
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
                if (j + nums[i - 1] <= 2 * sum) {
                    dp[i][j] += dp[i - 1][j + nums[i - 1]];
                }
            }
        }
        //注意这个结果不一定是最后一个
        return dp[n][sum + S];
    }

    @Test
    public void run9() {
        int[] arr = {1, 1, 1, 1, 1};
        int targetSumWays = findTargetSumWays(arr, 3);
        System.out.println(targetSumWays);
    }

    /**
     * 300. Longest Increasing Subsequence
     * 状态转移方程
     * F(i) = max(F(j) + 1, 其中满足nums[j] < nums[i])
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 0) return 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    @Test
    public void run10() {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(arr));
    }

    /**
     * 376. Wiggle Subsequence
     * 状态转移方程 F(i) = max(F(j) + 1,if nums[i] - nums[j]的符号与当前j位置的符号相反)
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = nums[i] - nums[j];
                if (temp == 0) continue;
                else temp = temp / Math.abs(temp);
                if (dp[j][1] == 0 || dp[j][1] != temp) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
                    dp[i][1] = temp;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

    /**
     * 376. Wiggle Subsequence
     * O（n）复杂度的解法
     * 设置两个数组，分别表示在index i的上升和下降长度。
     * 状态转移方程为
     * if(nums[i] > nums[i - 1])up[i] = down[i - 1] + 1;down[i] = down[i - 1]
     * if(nums[i] < nums[i - 1])down[i] = up[i - 1] + 1;up[i] = up[i - 1]
     * if(nums[i] == nums[i - 1])up[i] = up[i - 1];down[i] = down[i - 1]
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    @Test
    public void run11() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(wiggleMaxLength(arr));
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length;
        int n = s.length;
        int i = m - 1, j = n - 1, res = 0;
        while (i >= 0 && j >= 0) {
            if (s[j] >= g[i]) {
                i--;
                j--;
                res++;
            } else {
                i--;
            }
        }
        return res;
    }

    /**
     * 392. Is Subsequence
     * 贪心选择：选择t中最早和s中字符匹配的位置
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int m = chars.length, n = chart.length, i = 0, j = 0;
        while (i < m && j < n) {
            if (chars[i] == chart[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == m;
    }

    @Test
    public void run12() {
        boolean subsequence = isSubsequence("abc", "ahbgdc");
        System.out.println(subsequence);
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * 435. Non-overlapping Intervals
     * 需要删除的最少的区间与需要保留的最多的区间等价
     * 贪心选择：每次选择区间end最早的区间，只有这样才能留给后面的区间尽可能多的位置。
     * 所以首先按照end的大小排列，然后根据每个区间的start是否和上个区间的end重合来判断是否保留
     * 最后用总数-保留的区间即可。
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        int n = intervals.length;
        if (n <= 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end != o2.end) {
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });
        int res = 0, pre = 0;
        for (int i = 1; i < n; i++) {
            if (intervals[i].start >= intervals[pre].end) {
                res++;
                pre = i;
            }
        }
        return n - res;
    }
}
