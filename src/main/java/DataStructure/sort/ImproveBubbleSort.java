package DataStructure.sort;

import java.util.Arrays;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午7:22 17-10-3
 * 冒泡算法的改进版，记录每趟排序中最后一次交换的位置，因为pos位置后或者前的位置是已经排好序的,
 * 所以在下次排序的时候只需要扫描到pos位置即可。
 */
public class ImproveBubbleSort {
    public static void main(String[] args){
        int[] a = {3,1,6,5,2,3,9};
        sort(a);
    }

    public static void  sort(int[] a){
        int n = a.length;
        int i = 0;//这里按从小到大排序，初始时需要扫描到最前面
        while (i < n - 1){
            int pos = n - 1;//每趟开始时，没记录交换，即pos应该在最后一个元素
            for (int j = n - 1; j > i; j--){//扫描到交换的位置
                if (a[j] < a[j - 1]){
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                    pos = j;//记录交换位置
                }
            }
            i = pos;//更新交换位置
            System.out.println("sorting: " + Arrays.toString(a));
        }
        System.out.println("sorted: " + Arrays.toString(a));
    }
}
