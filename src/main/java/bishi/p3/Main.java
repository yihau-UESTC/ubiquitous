package bishi.p3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int n;
    static int m;
    static int k;
    static List<String> res = new ArrayList<>();
    static String s = "az";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();

        find(0, "", 0, 0);
    }

    public static void find(int index, String str, int la, int lz) {
        if (index == n + m) {
            res.add(str);
            return;
        }
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                if (la < n) {
                    str += 'a';
                    find(index + 1, str, la + 1, lz);
                } else
                    return;
            }
            if (i == 1) {
                if (lz < m) {
                    str += 'z';
                    find(index + 1, str, la, lz + 1);
                } else
                    return;
            }
            str = str.substring(0, str.length() - 1);
        }
    }

    @Test
    public void run() {
        n = 2;
        m = 2;
        k = 6;
        find(0, "", 0, 0);
    }

    private int binarySearchMax(int[] data, int key) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (data[mid] <= key)
                left = mid + 1;
            else
                right = mid - 1;
        }
        if (right >= 0 && data[right] == key)
            return right;
        return -1;
    }

    @Test
    public void run2() {
        int[] a = {0, 1, 2, 2, 3, 5, 6};
        int i = binarySearchMax(a, 2);
        System.out.println(i);
    }
}
