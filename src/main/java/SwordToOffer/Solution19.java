package SwordToOffer;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 * 考点：数组
 * 这题就是考编程的能力，没有什么技巧
 */
public class Solution19 {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int r1 = 0, r2 = m - 1, c1 = 0, c2 = n - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int i = c1; i <= c2; i++)
                res.add(matrix[r1][i]);
            for (int i = r1 + 1; i <= r2; i++)
                res.add(matrix[i][c2]);
            if (r1 != r2) {
                for (int i = c2 - 1; i >= c1; i--)
                    res.add(matrix[r2][i]);
            }
            if (c1 != c2) {
                for (int i = r2 - 1; i >= r1 + 1; i--)
                    res.add(matrix[i][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }
}
