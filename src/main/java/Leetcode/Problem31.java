package Leetcode;

import java.util.Arrays;

/**
 * leetcode31---next permutation.
 * 求出给出序列的下一个全排列，比如123 -> 132.如果到达尾端则返回第一个全排列如 321 -> 123
 */
public class Problem31 {
    public void nextPermutation(int[] nums){
        for (int i = nums.length - 1; i > 0; i--){
            if (nums[i] > nums[i - 1]){//从尾端开始，依次检查是否有前面的数小于后面的数，如有，则这个元素之后的数进行排序，按字典顺序。
                Arrays.sort(nums, i, nums.length);
                System.out.println(Arrays.toString(nums));
                int j = i;
                while (nums[i - 1] >= nums[j] && j < nums.length)j++;//然后求出后面排序之中比这个元素大的最小的那个，进行交换
                int temp = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = temp;
                System.out.println(Arrays.toString(nums));
                return;
            }//如果所有后面的数都小于前面的数，则到达全排列的最后一个序列，则返回字典顺序即全排列的第一个序列。
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int[] a = {1,5,1};
        new Problem31().nextPermutation(a);
    }
}
