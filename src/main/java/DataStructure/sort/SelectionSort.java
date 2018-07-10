/**
 * 选择排序，不稳定的，在交换元素的位置时可能改变相等元素的次序。算法复杂度n平方
 * 每次迭代挑选出最小的元素或者最大的，然后跟未排序的序列中最前一个或最后一个交换位置
 * 每次迭代只交换一次。迭代n次完成排序。
 * 下面还有一种改进方法是二元选择排序，可以一次迭代中选择出最大和最小的两个元素，这样最多迭代
 * n/2次。
 */
package DataStructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class SelectionSort {

	public static void simpleSelectionSort(int a[]){
		int n = a.length;
		for(int i = 0; i < n; i++){
			int min = i;
			for(int j = i + 1; j < n; j++){
				if(a[j] < a[min]){
					min = j;//选择最小元素
				}
			}
			//交换
			int temp = a[i];
			a[i] = a[min];
			a[min] = temp;
		}
		System.out.println(Arrays.toString(a));
	}
	
	public static void improveSelectionSort(int a[]) {
		int n = a.length;
		for (int i = 0; i <= n / 2; i++) {
			int min = i, max = i;
			for (int j = i + 1; j < n - i; j++) {
				if (a[j] < a[min]) {
					min = j;
					continue;
				}
				if (a[j] > a[max]) {
					max = j;
				}
			}
            if (min != i) {
				int temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
            if (max != i) {
				int temp = a[n - i - 1];
				a[n - i - 1] = a[max];
				a[max] = temp;
			}
		}
		System.out.println(Arrays.toString(a));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 3, 1, 5, 7, 2, 4, 9, 6 };
//		simpleSelectionSort(a);
		improveSelectionSort(a);
	}


    public static int[] sort1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[index]) index = j;
            }
            if (index != i) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
        return arr;
    }

    @Test
    public void test1() {
        int[] a = {9, 3, 5, 7, 2, 4, 6, 1};
//		impoveSelectSort(a);
        System.out.println(Arrays.toString(heapSort(a)));
    }


    public int[] heapSort(int[] arr) {
        int n = arr.length;
        //build heap,i = 0 ,1, 2...n-2/2
        for (int i = (n - 2) / 2; i >= 0; i--) {
            siftDown(arr, i, n);
        }
        //select
        for (int i = 0; i < n - 1; i++) {
            int temp = arr[0];
            arr[0] = arr[n - i - 1];
            arr[n - i - 1] = temp;
            siftDown(arr, 0, n - i - 1);
        }
        return arr;
    }

    private void siftDown(int[] arr, int start, int length) {
        int child = 2 * start + 1;
        int temp = arr[start];
        while (child < length) {
            if (child + 1 < length && arr[child + 1] > arr[child]) child++;
            if (arr[child] <= temp) break;
            else {
                arr[start] = arr[child];
                start = child;
                child = 2 * start + 1;
            }
        }
        arr[start] = temp;
    }


    public int[] impoveSelectSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= n / 2; i++) {
            int min = i, max = n - i - 1;
            for (int j = i; j < n - i; j++) {
                if (arr[j] < arr[min]) min = j;
                if (arr[j] > arr[max]) max = j;
            }

            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }

            if (min != n - i - 1 && max != n - i - 1) {
                int temp = arr[n - i - 1];
                arr[n - i - 1] = arr[max];
                arr[max] = temp;
            }
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }
}
