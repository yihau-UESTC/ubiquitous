package bishi.tp3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.valueOf(s.nextLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            String s1 = s.nextLine();
            String[] split = s1.split(" ");
            arr[i][0] = Integer.valueOf(split[0]);
            arr[i][1] = Integer.valueOf(split[1]);
        }
        int max = 0;
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i][0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int cur = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < i; j++) {
            }
        }
    }
}
