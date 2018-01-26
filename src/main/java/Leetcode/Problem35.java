package Leetcode;

/**
 * leetcode35---search insert position
 * 在排好序的数组中找到target的位置如果target不存在则返回其应该在的位置。
 */
public class Problem35 {
    public int searchInsert(int[] nums, int target) {
       if (nums.length == 0) return 0;
       if (target < nums[0]) return 0;//判断target比最小的还小的情况。
       if (target > nums[nums.length - 1])return nums.length;//判断target比最大还大的情况。
       int lo = 0, hi = nums.length - 1;
       while (lo < hi){//折半查找
           int mid = (lo + hi) / 2;
           if (nums[mid] == target)return mid;
           else if (nums[mid] < target) lo = mid + 1;
           else hi = mid - 1;
       }
       if (nums[lo] == target)return lo;//判断插入点。
       else if (nums[lo] < target) return lo + 1;
       else return lo;
    }

    public static void main(String[] args) {
        int a[] = {1,3,5,6};
        System.out.println(new Problem35().searchInsert(a,5));
        System.out.println(new Problem35().searchInsert(a,2));
        System.out.println(new Problem35().searchInsert(a,7));
        System.out.println(new Problem35().searchInsert(a,0));
        int b[] = {1,3};
        System.out.println(new Problem35().searchInsert(b, 2));
        int c[] = {1,2,4,6,7};
        System.out.println(new Problem35().searchInsert(c, 3));
    }
}
