package DataStructure;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) {
            w[i][0] = sc.nextInt();
            w[i][1] = sc.nextInt();
        }
        int[] p = new int[m];
        for (int i = 0; i < m; i++) {
            p[i] = sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                if (p[i] >= w[j][0] && w[j][1] > s) s = w[j][1];
            }
            System.out.println(s);
        }
    }
}

