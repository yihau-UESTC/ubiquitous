package bishi.tp5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        int n = 3;
////        int m = 10;

////        int[][] time = {{0,3},{3,7},{7,0}};
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        int m = Integer.valueOf(scanner.nextLine());
        String s = scanner.nextLine();
        String[] split = s.split(" ");
        int[][] time = new int[n][2];
        for (int i = 0; i < 3; i++) {
            time[i][0] = Integer.valueOf(split[i]);
            time[i][1] = Integer.valueOf(split[i]);
        }
        for (int i = 0; i < n; i++) {
            if (time[i][0] > time[i][1]) {
                time[i][1] = m + time[i][1];
            }
        }
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int res = 1;
        int cur = 0;
        for (int i = 1; i < n; i++) {
            if (time[i][0] >= time[cur][1] && time[i][1] <= m) {
                res += 1;
                cur = i;
            }
        }
        System.out.println(res);
    }
}
