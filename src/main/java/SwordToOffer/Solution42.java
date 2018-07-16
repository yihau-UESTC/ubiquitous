package SwordToOffer;

import java.util.ArrayList;

/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 使用双指针法，普通操作，
 * 这里有一点需要知道，其实从两边开始遍历找到的第一组数就是乘积最小的，因为有下面定理：
 * 两个数的和一定，它们的差越小，乘积越大
 */
public class Solution42 {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int n1 = 0, n2 = array.length - 1, min = Integer.MAX_VALUE;
        ArrayList<Integer> res = new ArrayList<>();
        while (n1 < n2){
            int cur = array[n1] + array[n2];
            if (cur == sum){
                if (array[n1] * array[n2] < min){
                    min = array[n1] * array[n2];
                    res.clear();
                    res.add(array[n1]);
                    res.add(array[n2]);
                }
                n1++;
            }else if (cur > sum)
                n2--;
            else
                n1++;
        }
        return res;
    }
}
