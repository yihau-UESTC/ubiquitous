package Leetcode;

import java.util.Arrays;

/**
 * leetcode problem27--remove element.
 */
public class Problem27 {
    public int removeElement(int[] nums, int val){
        int count = 0;//记录留下的元素的个数以及位置。
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != val){
                nums[count] = nums[i];//出现需要留下的元素时，将其向前移动。count++
                count++;
            }
            //出现相同的需要移除的元素时直接跳过。但是这时count是不变的还在上个需要移除的元素位置等待替换。
        }
        System.out.println(Arrays.toString(nums));
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;

        System.out.println(new Problem27().removeElement(nums, val));

    }
}
