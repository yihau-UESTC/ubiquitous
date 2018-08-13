package bishi.p1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] intrest = new int[n];
        int[] wake = new int[n];
        for (int i = 0; i < n; i++) {
            intrest[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            wake[i] = scanner.nextInt();
        }
        int l = 0, r = k, max = 0;
        while (r < n) {
            int sum = 0;
            for (int i = l; i < r; i++) {
                if (wake[i] == 0)
                    sum += intrest[i];
            }
            max = Math.max(sum, max);
            l++;
            r++;
        }
        for (int i = 0; i < n; i++) {
            if (wake[i] == 1)
                max += intrest[i];
        }
        System.out.println(max);
    }
}
