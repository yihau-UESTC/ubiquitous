package SwordToOffer;

import org.junit.Test;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字,不连续的也算上，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 本题直接使用双重遍历求解的话时间复杂度在O(N^2)肯定超时，
 * 使用归并排序的思想(实在联想不到)
 * 每次先对组内相邻的元素判断逆序对，因为组内的逆序对已经统计过，所以可以排序，
 * 然后对组与组之间的元素进行判断逆序对，后组元素大于前组元素的一定不行(不是逆序),统计前组元素大于后组的，
 * 考虑到组内元素已经有序，所以如果找到一个元素大于后组元素，那么该元素到mid的所有前组元素都应该和当前后组元素逆序对
 * 整个算法相比于归并排序就多了一行代码count += mid - i1 + 1;
 */
public class Solution35 {
    private int[] tmp;
    private long count;

    public int InversePairs(int[] array) {
        tmp = new int[array.length];
        mergeSort(array, 0, array.length - 1);
        return (int) (count % 1000000007);
    }

    private void mergeSort(int[] nums, int l, int h) {
        if (l < h) {
            int mid = (l + h) >>> 1;
            mergeSort(nums, l, mid);
            mergeSort(nums, mid + 1, h);
            merge(nums, l, mid, h);
        }
    }

    private void merge(int[] nums, int l, int mid, int h) {
        int i1 = l, i2 = mid + 1, c = 0;
        while (i1 <= mid && i2 <= h) {
            if (nums[i1] <= nums[i2])
                tmp[c++] = nums[i1++];
            else {
                tmp[c++] = nums[i2++];
                count += mid - i1 + 1;//因为前半段数组已经排过序了，所以碰到一个大于后面的数，应该把当前i1-->mid的所有元素都算上
            }
        }
        while (i1 <= mid)
            tmp[c++] = nums[i1++];
        while (i2 <= h)
            tmp[c++] = nums[i2++];
        for (int i = 0; i < h - l + 1; i++) {
            nums[l + i] = tmp[i];
        }
    }

    @Test
    public void run() {
        int[] arr = {7, 5, 4, 6};
        System.out.println(InversePairs(arr));
    }

}
