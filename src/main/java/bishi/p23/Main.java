package bishi.p23;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x1 = new int[n];
        int[] y1 = new int[n];
        int[] x2 = new int[n];
        int[] y2 = new int[n];
        for (int i = 0; i < n; i++) {
            x1[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            y1[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            x2[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            y2[i] = scanner.nextInt();
        }
        Set<Integer> px = new HashSet<>();
        Set<Integer> py = new HashSet<>();
        for (int i = 0; i < n; i++) {
            px.add(x1[i]);
            px.add(x2[i]);
            py.add(y1[i]);
            py.add(y2[i]);
        }
        int res = 1;
        for (Integer x : px)
            for (Integer y : py) {
                int cnt = 0;
                for (int i = 0; i < n; i++) {
                    if (x1[i] <= x && y1[i] <= y && x2[i] > x && y2[i] > y)
                        cnt++;
                }
                res = Math.max(res, cnt);
            }
        System.out.println(res);

    }
}
