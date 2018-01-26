package Leetcode;

import java.util.Arrays;

/**
 * leetcode34--search for a range
 * 在一个排好序的数组中搜索某个值的范围，如在[5,7,7,8,8,10]中搜索8 返回[3,4]，搜索不到返回[-1，-1]
 */
public class Problem34 {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)return new int[]{-1, -1};//数组为空直接返回
        int lo = 0, hi = nums.length - 1;
        while (lo < hi){//使用折半查找
            int mid = (lo + hi) / 2;
            if (nums[mid] == target){//找到target则由此向两边搜索。
                int p1 = mid, p2 = mid;
                while (p1 >= lo && nums[p1] == target)p1--;
                while (p2 <= hi && nums[p2] == target)p2++;
                return new int[]{p1 + 1, p2 - 1};
            }else if (nums[mid] < target)lo = mid + 1;
            else hi = mid - 1;
        }
        return nums[lo] == target ? new int[]{lo,lo} : new int[]{-1, -1};//处理数组中只有一个数的情况。
    }

    public static void main(String[] args) {
        int[] a = {1};
        System.out.println(Arrays.toString(new Problem34().searchRange(a, 1)));
    }
}
