/**
 * 希尔插入排序，又叫缩小增量排序，根据增量对增量序列进行直接插入排序，依次缩小增量，到增量为1的时候排序结束。
 * 希尔排序是不稳定的排序方法，相等的值可能在不同增量序列中。时间复杂度难以分析。
 */
package DataStructure.sort;

import java.util.Arrays;

public class ShellSort {

	private static int[] shellInsertSort(int[] a) {
		// TODO Auto-generated method stub
		int n = a.length;
		int dk = n / 2;//产生第一个增量
		while (dk >= 1) {
			for (int i = dk; i < n; i++) {//对增量序列进行直接插入排序
				int x = a[i];
				int j = i - dk;
				for (; j >= 0 && a[j] > x; j = j - dk) {
					a[j + dk] = a[j];
				}
				a[j + dk] = x;
			}
			System.out.println("dk = " + dk + ", " + Arrays.toString(a));
			dk = dk / 2;//缩小增量
		}

		return a;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 3, 1, 5, 7, 2, 4, 9, 6 };
		shellInsertSort(a);
	}

}
