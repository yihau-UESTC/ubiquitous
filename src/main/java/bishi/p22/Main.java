package bishi.p22;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] split = s.split(" ");
        int n = Integer.valueOf(split[0]);
        int k = Integer.valueOf(split[1]);
        long count = 0;
        for (int y = k + 1; y <= n; y++) {
            count += (n / y) * (y - 1 - k + 1) + Math.max(n % y - k + 1, 0);
            if (k == 0)
                count--;
        }
        System.out.println(count);
    }
}
