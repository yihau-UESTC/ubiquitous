package SwordToOffer;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * 考点：数组，维护变量定义
 * 定义p1使得[0,p1)表示奇数,[p1,i)表示偶数，遍历数组，碰到偶数直接跳过，碰到奇数，需要将[p1,i)部分集体向后移动一位
 * 类似插入排序中的后移过程，最后把i位置的放入p1位置，然后增长p1.
 * 复杂度O(N^2)+O(1)
 */
public class Solution13 {
    public void reOrderArray(int[] array) {
        int n = array.length;
        if (n <= 1) return;
        int p1 = 0;//[0,p1)维护奇数，[p1,i)维护偶数
        for (int i = 0; i < n; i++) {
            int num = array[i];
            if (num % 2 != 0) {
                for (int j = i - 1; j >= p1; j--) {
                    array[j + 1] = array[j];
                }
                array[p1] = num;
                p1++;
            }
        }
    }

    public void reOrderArray2(int[] array) {
        int n = array.length;
        if (n <= 0) return;
        int op = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] % 2 != 0) {
                int temp = array[i];
                int j = i - 1;
                for (; j >= op; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
                op++;
            }
        }
    }
}
