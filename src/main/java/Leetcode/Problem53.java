package Leetcode;

/**
 * leetcode53---Maximum subarray
 * 找出数组中和最大的连续子数组
 */
public class Problem53 {
    public static int maxSubArray(int[] nums){
        int  n = nums.length;
        int max = nums[0], maxserach = nums[0];
        for (int i = 1; i < n; i++){
           maxserach = Math.max(maxserach + nums[i], nums[i]);//比较a(1:i)和a(i)的大小，求出目前最大的数
           max = Math.max(max, maxserach);//比较max和当前最大的数
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(a));
    }
}
