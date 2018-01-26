package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 56--merge intervals
 * Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 无序的间隔

 */
public class Problem56 {
    /**
     * 自己想的方法，只对start进行了排序。时间104ms；而且都是在list‘上操作。
     * @param intervals
     * @return
     */
    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        intervals.sort((i1,i2) -> {
            return i1.start > i2.start ? 1 : i1.start < i2.start ? -1 : 0;
        });
        for (int i = 0; i < intervals.size(); i++){
            Interval temp = intervals.get(i);
            int start = temp.start;
            int max = temp.end;
            while (i+1 < intervals.size() && intervals.get(i+1).start <= max){
                max = Math.max(max, intervals.get(i + 1).end);
                i++;
            }
            int end = max;
            res.add(new Interval(start, end));
        }
        return res;
    }

    /**
     * 别人的最快的方法，将start和end分成两份数组，分别进行排序。时间14ms
     *
     * @param intervals
     * @return
     */
    public  static List<Interval> merge2(List<Interval> intervals){
        List<Interval> result = new ArrayList<Interval>();
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for(int i=0;i<n;i++){
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        for(int i = 0,j = 0; i<n;i++){//这里i代表当前点，j代表起点
            if(i==n-1 ||starts[i+1]>ends[i]){//当某个起点大于上个终点的时候说明有一个不重叠的间隔了。
                result.add(new Interval(starts[j],ends[i]));//间隔的位置是起点到终点。
                j = i+1;//然后将起点赋值为上个终点的下一位。
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2,3));
        list.add(new Interval(4,5));
        list.add(new Interval(1,10));

        List<Interval> res = merge(list);
        System.out.println();
    }
}

