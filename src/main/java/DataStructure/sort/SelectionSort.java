/**
 * 选择排序，不稳定的，在交换元素的位置时可能改变相等元素的次序。算法复杂度n平方
 * 每次迭代挑选出最小的元素或者最大的，然后跟未排序的序列中最前一个或最后一个交换位置
 * 每次迭代只交换一次。迭代n次完成排序。
 * 下面还有一种改进方法是二元选择排序，可以一次迭代中选择出最大和最小的两个元素，这样最多迭代
 * n/2次。
 */
package DataStructure.sort;

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
			boolean minf = false, maxf = false;
			for (int j = i + 1; j < n - i; j++) {
				if (a[j] < a[min]) {
					min = j;
					minf = true;
					continue;
				}
				if (a[j] > a[max]) {
					max = j;
					maxf = true;
				}
			}
			if (minf) {
				int temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
			if (maxf) {
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

}
