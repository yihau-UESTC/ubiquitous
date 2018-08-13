package SwordToOffer;

import org.junit.Test;

/**
 * 连续子数组的最大和：{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。子向量的长度至少为1
 * 连续子数组，要想求的最大和，如果之前的数组和已经为负数了，则应该抛弃，从新的元素开始累加，
 * 每次累加一个元素都要与当前的最大值比较以求得最大值。
 */
public class Solution30 {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0)
            return 0;
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum >= 0 ? sum + array[i] : array[i];
            res = Math.max(res, sum);
        }
        return res;
    }

    @Test
    public void run() {
        int[] arr = {-2, -8, -1, -5, -9};
        System.out.println(FindGreatestSumOfSubArray(arr));
    }
}
