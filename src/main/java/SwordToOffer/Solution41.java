package SwordToOffer;

import java.util.ArrayList;

/**
 * 输出所有和为 S 的连续正数序列。
 * 例如和为 100 的连续序列有：
 * [9, 10, 11, 12, 13, 14, 15, 16]
 * [18, 19, 20, 21, 22]。
 * 使用双指针维护一个滑动窗口，如果当前值大于sum，start后移，当前值小于sum，end值后移，==的话记录结果。
 */
public class Solution41 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int start = 1, end = 2;
        int curSum = 3;
        while (end < sum){
            if (curSum > sum){
                curSum -= start;
                start++;
            }else if (curSum < sum){
                end++;
                curSum += end;
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++)
                    list.add(i);
                res.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return res;
    }
}
