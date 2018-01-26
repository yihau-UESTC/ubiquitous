package Leetcode;

import java.util.Arrays;

/**
 * leetcode59---spiral matrix2
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

 For example,
 Given n = 3,

 You should return the following matrix:
 [
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
 ]
 采取上右左下的顺序来赋值，注意改变相应的上下限。
 */
public class Problem59 {

    public static int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int left = 0,top = 0;
        int right = n - 1,down = n - 1;
        int count = 1;
        while (left <= right) {
            for (int j = left; j <= right; j ++) {
                ret[top][j] = count++;
            }
            top ++;//行下限增加
            for (int i = top; i <= down; i ++) {
                ret[i][right] = count ++;
            }
            right --;//列上限减少
            for (int j = right; j >= left; j --) {
                ret[down][j] = count ++;
            }
            down --;//行上限减少
            for (int i = down; i >= top; i --) {
                ret[i][left] = count ++;
            }
            left ++;//列下限增加
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] a = generateMatrix(3);
        Arrays.toString(a);
    }
}
