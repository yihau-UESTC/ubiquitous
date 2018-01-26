package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LEETCODE57---INSERT INTERVAL
 */
public class Problem57 {
    /**
     * 和56题一样，只是将newInterval加入数组中在进行排序，然后和上一题的一样，利用两个指针来表示一个间隔的start和end
     * @param intervals
     * @param newInterval
     * @return
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int[] first = new int[intervals.size() + 1];
        int[] second = new int[intervals.size() + 1];
        for (int i = 0; i < first.length - 1; i++){
            first[i] = intervals.get(i).start;
            second[i] = intervals.get(i).end;
        }
        first[first.length - 1] = newInterval.start;
        second[first.length - 1] = newInterval.end;
        Arrays.sort(first);
        Arrays.sort(second);
       for (int i = 0, j = 0; i < first.length; i++){
           if ( first.length - 1 == i || first[i + 1] > second[i]){
               result.add(new Interval(first[j], second[i]));
               j = i + 1;
           }
       }
        return result;
    }

    /**
     * 首先根据newInterval.start 判断出新的间隔之前的无交集间隔加入结果，然后根据newInterval.end判断出之间的
     * 有交集间隔并得出一个交集后的间隔加入结果，然后剩下的就是后边的无交集间隔。
     * @param intervals
     * @param newInterval
     * @return
     */
    public static List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start){
            result.add(intervals.get(i++));
        }
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end){
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            i++;
        }
        result.add(newInterval);
        while (i < intervals.size()){
            result.add(intervals.get(i++));
        }
        return result;
    }
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1,5));
        List<Interval> result = insert2(list, new Interval(0,1));
        System.out.println(result.toString());
    }
}

 class Interval{
    int start;
    int end;
    Interval(){start = 0; end = 0;}
    Interval(int s, int e){start = s; end = e;}

    public String toString(){
        return "[" +start + "," + end + "]";
    }
}
