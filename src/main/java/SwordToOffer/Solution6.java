package SwordToOffer;

import org.junit.Test;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * 考点：二分查找
 * 直接遍历肯定是不符合时间要求的，二分查找，解释一下为啥当mid<=h 的时候是h=mid而不是h=mid - 1;
 * 假如刚好此时mid就是最小的元素，那么h=mid-1的话会错过最小元素--->error
 * 但是当mid>h的时候可以是l = mid+1，因为此时mid一定不会是最小的元素。
 * 复杂度O(logN) + O(1)
 */
public class Solution6 {
    public int minNumberInRotateArray(int[] array) {
        int n = array.length;
        if (n == 0) return 0;
        int l = 0, h = n - 1, mid = 0;
        while (l < h) {
            mid = (l + h) >>> 1;
            if (array[mid] <= array[h]) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return array[l];
    }

    @Test
    public void run() {
        int[] a = {3, 4, 5, 1, 2};
        System.out.println(minNumberInRotateArray(a));
    }
}
