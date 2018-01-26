package Leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 * LeetCode54--spiral matrix.
 * 将矩阵以回旋的顺序输出。这题没有什么简单解法，就是先上后右再下在左。
 */
public class Problem54 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length <= 0) return list;
        int m = matrix.length;
        int n = matrix[0].length;
        int num = m * n;
        int count = 0;
        int i = 0, j = 0;
        int flag = 0;
        while (count < num){
            while (j < n - flag){
                list.add(matrix[i][j]);
                j++;count++;
            }
            if(count >= num)break;
            j--;
            i++;
            while (i < m - flag){
                list.add(matrix[i][j]);
                count++;
                i++;
            }
            if(count >= num)break;
            i--;
            j--;
            while (j >= flag){
                list.add(matrix[i][j]);
                count++;
                j--;
            }
            if(count >= num)break;
            i--;
            j++;
            flag++;
            while (i >= flag){
                list.add(matrix[i][j]);
                count++;
                i--;
            }
            if(count >= num)break;
            i++;
            j++;
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix2 = {{2,3}};
        spiralOrder(matrix2).stream().forEach(System.out::println);
    }
}
