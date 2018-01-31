/**
 * 直接插入排序，算法复杂度n平方
 * 最好的情况是已经排好序，每次直接插入，n
 * 最坏的情况是反序，每次比较为j次，一共1+2+3+...+n=n平方
 * 设立哨兵为当前排好序的序列的下一个数，然后对哨兵和当前排好的序列进行比较。
 * 如果序列中的数大于哨兵的值，则向后移动一位，否则当前位置就是哨兵要插入的位置。
 */
package DataStructure.sort;

import java.util.Arrays;

public class SInsertSort {
	
	
	public static int[] sort(int[] arr){
		int x, j = 0;
		int n  = arr.length;
		for (int i = 1; i < n; i++) {
			j = i - 1;
			x = arr[i];//记录哨兵值
			for(; j >=0 && arr[j] > x; j--){
				arr[j+1] = arr[j];//大于哨兵值的话向后移
			}
			arr[j+1] = x;//哨兵直接插入已排好序列的后一位
		}
		return arr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,1,5,7,2,4,9,6};
		a = sort(a);
		System.out.println(Arrays.toString(a));
	}
	

}
