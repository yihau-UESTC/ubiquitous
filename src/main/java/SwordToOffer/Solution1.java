package SwordToOffer;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 考察点：查找
 * 类似于在一维数组中的二分查找，但这里不是二分查找，由于二维数组也是有序的，所以可以利用有序性来快速查找
 * 而不用直接遍历数组，将起始位置从数组的右上角(0, n - 1)开始向左下角移动，判断当前元素和target的大小
 * 大的话j--,小的话i++。也可以从左下角向右上角开始搜索。类似。
 * 复杂度O(M+N)+ O(1)
 */
public class Solution1 {
    public boolean Find(int target, int [][] array) {
        int m = array.length;
        if(m <= 0)return false;
        int n = array[0].length;
        int i = 0, j = n - 1;
        while(i < m && j >= 0){
            if(array[i][j] == target)return true;
            else if(array[i][j] > target)j--;
            else i++;
        }
        return false;
    }
}