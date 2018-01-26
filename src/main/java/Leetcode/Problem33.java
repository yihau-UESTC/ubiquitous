package Leetcode;

/**
 * leetcode33--search in rotated sorted array
 * 在一个排好序但是做了一定旋转的数组中搜索，例如{4,5,6,7,0,1,2}但是不知道在哪个位置做了旋转。
 */
public class Problem33 {
    public int search(int[] nums, int target) {
       int lo = 0, hi = nums.length - 1;
       while (lo < hi){//首先找到数组中最小元素的位置。即旋转点。
           int mid = (lo + hi) / 2;
           if (nums[mid] > nums[hi])lo = mid + 1;
           else hi = mid;
       }
       int rotation = lo;
       lo = 0; hi = nums.length - 1;
       while (lo <= hi){//二分搜索。
           int mid = (lo + hi) / 2;
           int realMid = (mid + rotation) % nums.length;//然后通过旋转点来计算出真正的中点。类似于一个循环队列然后做mode运算来求解。
           if (nums[realMid] == target)return realMid;
           else if (nums[realMid] < target) lo = mid + 1;
           else hi = mid - 1;
       }
       return -1;
    }

    public static void main(String[] args) {
        int[] a = {4,5,6,7,0,1,2};
        System.out.println(new Problem33().search(a, 3));

    }
}
