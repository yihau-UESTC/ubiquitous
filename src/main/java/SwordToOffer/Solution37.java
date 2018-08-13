package SwordToOffer;

import org.junit.Test;

/**
 * 统计一个数字在排序数组中出现的次数。
 * 排序的数组，一般使用二分搜索就是了
 */
public class Solution37 {
    public int GetNumberOfK(int[] array, int k) {
        int l = 0, h = array.length;
        int k1 = findK(array, l, h, k);
        if (k1 == -1) return 0;
        int res = 0;
        for (int i = k1; i < h; i++) {
            if (array[i] != k)
                break;
            res++;
        }
        for (int i = k1 - 1; i >= 0; i--) {
            if (array[i] != k)
                break;
            res++;
        }
        return res;
    }

    private int findK(int[] array, int l, int h, int k) {
        while (l < h) {
            int mid = (l + h) >>> 1;
            if (array[mid] == k)
                return mid;
            else if (array[mid] > k)
                h = mid - 1;
            else
                l = mid + 1;
        }
        return -1;
    }

    @Test
    public void run() {
        int[] arr = {3, 3, 3, 3, 4, 5};
        System.out.println(GetNumberOfK(arr, 3));
    }
}
