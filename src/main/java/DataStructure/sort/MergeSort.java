package DataStructure.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午1:00 17-10-10
 * 归并排序，主要思想利用递归和分治技术将数据序列划分直到剩下一个元素，然后将排好序的子表进行合并。
 * 每次递归需要将待排序的子表扫描一遍耗费O(n),由完全二叉树的深度可知归并排序需要进行log2n次，故总复杂度为O(nlog2n).算法稳定。
 */
public class MergeSort {
    public static void main(String[] args){
        int[] a = {3,1,6,5};
        sort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
    }

    public static void sort(int[] a, int left, int right){
        //递归地从中间分割数组
        if (left < right) {
            int mid = (left + right) / 2;
            sort(a, left, mid);
            sort(a, mid + 1, right);
            merge(a, left, mid, right);
            System.out.println(Arrays.toString(a));
        }
    }

    public static void merge(int[] a, int left, int mid, int right){
        int[] result = new int[right - left + 1];//直接执行时需要额外的O（n）的空间复杂度
        int s1 = left, s2 = mid + 1, t = 0;
        //较小的数先放到新数组中
        while (s1 <= mid && s2 <= right){
            if (a[s1] < a[s2])result[t++] = a[s1++];
            else result[t++] = a[s2++];
        }
        //多余的数排在数组后面
        while (s1 <= mid)result[t++] = a[s1++];
        while (s2 <= right)result[t++] = a[s2++];
        System.out.println(Arrays.toString(result));
        //排好序的子数组覆盖原数组的相应部分
        for (int i = 0; i < result.length; i++){
            a[left + i] = result[i];
        }
    }


    public int[] mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge1(arr, low, mid, high);
        }
        return arr;
    }

    private void merge1(int[] arr, int low, int mid, int high) {
        int[] seq = new int[high - low + 1];
        int i1 = low, i2 = mid + 1, c = 0;
        while (i1 <= mid && i2 <= high) {
            if (arr[i1] < arr[i2]) seq[c++] = arr[i1++];
            else seq[c++] = arr[i2++];
        }

        while (i1 <= mid) seq[c++] = arr[i1++];
        while (i2 <= high) seq[c++] = arr[i2++];

        for (int i = 0; i < seq.length; i++) {
            arr[low + i] = seq[i];
        }
    }

    @Test
    public void run() {
        int[] a = {10, 3, 1, 6, 5};
        System.out.println(Arrays.toString(new MergeSort().mergeSort(a, 0, 4)));
    }
}
