/**
 * 直接插入排序，算法复杂度n平方
 * 最好的情况是已经排好序，每次直接插入，n
 * 最坏的情况是反序，每次比较为j次，一共1+2+3+...+n=n平方
 * 设立哨兵为当前排好序的序列的下一个数，然后对哨兵和当前排好的序列进行比较。
 * 如果序列中的数大于哨兵的值，则向后移动一位，否则当前位置就是哨兵要插入的位置。
 */
package DataStructure.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SInsertSort {
	
	
	public static int[] sort(int[] arr){
		int x, j = 0;
		int n  = arr.length;
        //直接从1开始，0位置一个元素是直接排好序的。
		for (int i = 1; i < n; i++) {
			j = i - 1;
			x = arr[i];//记录哨兵值
			for(; j >=0 && arr[j] > x; j--){
                arr[j + 1] = arr[j];//大于哨兵值的话向后移，依次向后覆盖
			}
			arr[j+1] = x;//哨兵直接插入已排好序列的后一位
		}
		return arr;
	}

    public static int[] binaryInsertSort(int[] arr) {
        int x = 0, n = arr.length;
        for (int i = 1; i < n; i++) {
            x = arr[i];
            int left = 0, right = i - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (x < arr[mid]) right = mid - 1;
                else left = mid + 1;
            }
            //二分搜索得到插入位置left，将left后面的元素向后移动。
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = x;
        }
        return arr;
    }

    public static int[] shellSort(int[] arr) {
        int n = arr.length;
        int gap = n;
        do {
            gap = gap / 3 + 1;
            for (int i = gap; i < n; i++) {
                //此次要插入的元素
                int anchor = arr[i];
                int j = i - gap;

                for (; j >= 0 && arr[j] > anchor; j -= gap) {
                    arr[j + gap] = arr[j];//当前位置比哨兵大的话往后移动一个gap；
                }
                arr[j + gap] = anchor;//哨兵插入到前面已经排好序的后面一个位置。
            }
            //调整gap值

        } while (gap > 1);
        return arr;
    }

    public static int minNumberInRotateArray(int[] array) {
        if (array.length == 0) return 0;
        int low = 0, high = array.length - 1;
        int min = array[high];
        while (low <= high) {
            int mid = (high + low) / 2;
            if (min < array[mid]) low = mid + 1;
            else {
                high = mid - 1;
                min = array[mid];
            }
        }
        return min;
    }


    @Test
    public void test() {
        int[] a = {3, 1, 5, 7, 2, 4, 9, 6};
//		System.out.println(Arrays.toString(binaryInsertSort(a)));
        System.out.println(Arrays.toString(shellSort(a)));
        System.out.println(Arrays.toString(sort3(a)));
//		int[] a1 = {3,4,5,1,2};
//		System.out.println(minNumberInRotateArray(a1));
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,1,5,7,2,4,9,6};
		a = sort(a);
		System.out.println(Arrays.toString(a));
	}

    public static int[] sort3(int[] arr) {
        int n = arr.length;
        int gap = n;
        do {
            gap = gap / 3 + 1;
            for (int i = gap; i < n; i++) {
                int anchor = arr[i];
                int j = i - gap;
                for (; j >= 0 && arr[j] > anchor; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = anchor;
            }
        } while (gap > 1);
        return arr;
    }
	

}
