package Leetcode;

/**
 * leetcode41---First Missing Positive integer
 * time limt -> O(n), space limt ->constant O(1)
 * 这个解法的思路就是让每个元素回到其原来的位置。元素值为负数和大于数组长度的元素不动。
 */
public class Problem41 {
    public static int firstMissingPositive(int[] nums){
        int n = nums.length;
        for (int i = 0; i < n; i++){
            //1.负数不动
            //2.大于数组长度的不动，第一个miss的一定是[1，n+1]的
            //3.元素nums[i]应该在nums[nums[i] - 1] 的位置，如果不是，则交换。eg：2应该在nums[2 - 1] 的位置。
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]){
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        //遍历数组如果某个值和其位置不对则miss，否则为数组长度内不缺，缺其后面的一位
        for (int i = 0; i < n; i++){
            if (nums[i] != i + 1)return i + 1;
        }
        return n + 1;
    }

    public static void main(String[] args) {
        int[] a = {3, 4, -1, 1};
        System.out.println(firstMissingPositive(a));
    }
}
