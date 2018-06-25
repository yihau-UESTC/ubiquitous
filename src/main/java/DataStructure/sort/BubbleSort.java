package DataStructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class BubbleSort {
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }

    /*1、当某一趟排序中未发生交换，则说明已经排好序了，结束循环；
     * 2、记录上次的交换位置，交换位置之前的是排好序的。*/
    public static int[] sort2(int[] arr) {
        int n = arr.length;
        int i = 0;
        //在循环的开始会赋值pos为n-1，如果没有发生变化，则跳出循环。
        while (i < n - 1) {
            //刚开始赋值为n-1，如果数组是有序的未发生交换，则直接就退出循环。
            int pos = n - 1;
            //循环结束条件到上次记录交换的位置，前面的已经有序。
            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    pos = j;//记录交换的位置
                }
            }
            i = pos;
        }
        return arr;
    }

    @Test
    public void test() {
        int[] arr = {21, 25, 49, 25, 16, 8};
//        System.out.println(Arrays.toString(sort(arr)));
//        System.out.println(Arrays.toString(sort2(arr)));
        System.out.println(Arrays.toString(sort3(arr)));
    }


    public static int[] sort3(int[] arr) {
        int n = arr.length;
        int i = 0;
        while (i < n - 1) {
            int pos = n - 1;
            for (int j = n - 1; j > i; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    pos = j;
                }
            }
            i = pos;
        }
        return arr;
    }

}
