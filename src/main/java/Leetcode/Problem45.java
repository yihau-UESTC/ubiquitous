package Leetcode;

/**
 * LeetCode45--jump game2.
 */
public class Problem45 {
    /**
     * 自己想的方法，从后往前搜索，每次找到最靠前的能调到最后面的元素。
     * 功能无问题，时间超出限制。。失败。
     * @param nums
     * @return
     */
    public static int jump(int[] nums){
        int n = nums.length;
        int start = n - 1;
        int step = 0;
        while (start > 0){
            int index = start;
            for (int i = start - 1; i >= 0; i--){
                if (start <= (nums[i] + i)){
                    index = i;
                }
            }
            start = index;
            step++;
        }
        return step;
    }

    /**
     * 这里用到了广度优先搜索BFS，
     * eg：{2,3,1,1,4}
     * level1:2|
     * level2:3,1|
     * level3:1,4|
     * 所以从leve1->level3需要jump2次。
     * 下个level就是当前层能jump到的所有的元素位置。
     * 第一次先搜索出当前层的所有元素，依次这样，广度优先的思想。
     * @param nums
     * @return
     */
    public static int jump2(int[] nums){
        int n = nums.length;
        if (n < 2)return 0;
        int level = 0, cmax = 0, i = 0, nmax = 0;
        while (i < n) {
            level++;
            for (; i <= cmax; i++) {//搜索当前层的所有元素。
                nmax = Math.max(nmax, nums[i] + i);//确定下一层的位置。
                if (nmax >= n - 1) return level;//如果终点在下一层中，则返回当前层的level则为最大jump
            }
            cmax = nmax;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a = {2,3,1,1,4};
        int[] a1 = {2,3,1};
        System.out.println(jump2(a));
        System.out.println(jump2(a1));
    }
}
