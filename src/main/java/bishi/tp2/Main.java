package bishi.tp2;

import java.util.*;

public class Main {
    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] split = s.split(";");
            for (int j = 0; j < split.length; j++) {
                String[] split1 = split[j].split(",");
                intervals.add(new Interval(Integer.valueOf(split1[0]), Integer.valueOf(split1[1])));
            }
        }
        List<Interval> merge = merge(intervals);
        for (int i = 0; i < merge.size(); i++) {
            Interval c = merge.get(i);
            System.out.print(c.start + "," + c.end);
            if (i != n - 1)
                System.out.print(";");
        }
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        if (intervals.size() < 1)
            return result;
        Interval p = intervals.get(0);
        Interval temp = null;
        int i = 1;
        while (i < intervals.size()) {
            temp = intervals.get(i);
            if (p.end >= temp.start) {
                p.end = Math.max(p.end, temp.end);
            } else {
                result.add(p);
                p = temp;
            }
            i++;
        }
        result.add(p);
        return result;
    }
}
