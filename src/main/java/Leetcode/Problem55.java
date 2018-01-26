package Leetcode;

/**
 * leetcode--55.jump game
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 For example:
 A = [2,3,1,1,4], return true.

 A = [3,2,1,0,4], return false.
 使用BFS
 */
public class Problem55 {
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        if (n < 2) return true;
        int i = 0, cMax = 0, nmax = 0;
        while (i < n){
            for (; i <= cMax; i++){//当前层能跳的最远的地方cmax
                nmax = Math.max(nmax, nums[i] + i);//在当前层计算全局现在能跳到的最远的地方。
                if (nmax >= n - 1)return true;//如果能跳到最后，返回true
                else if (nmax <= i)return false;//如果全局能跳到的最远的地方小于等于当前层的最后一个，则返回false。
            }
            cMax = nmax;//修改下一层的范围。
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {3,2,1,0,4};
        System.out.println(canJump(nums1));
    }
}
