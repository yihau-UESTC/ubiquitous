package SwordToOffer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] split = s.split(" ");
        int n = 0, m = 0;
        if (split.length == 2) {
            n = Integer.valueOf(split[0]);
            m = Integer.valueOf(split[1]);
        } else
            throw new IllegalArgumentException();
        int[] hard = new int[n];
        int[] pay = new int[n];
        int[] level = new int[m];
        for (int i = 0; i < n; i++) {
            s = scanner.nextLine();
            split = s.split(" ");
            if (split.length == 2) {
                hard[i] = Integer.valueOf(split[0]);
                pay[i] = Integer.valueOf(split[1]);
            } else
                throw new IllegalArgumentException();
        }
        s = scanner.nextLine();
        split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            level[i] = Integer.valueOf(split[i]);
        }
        int[] work = findWork(hard, pay, level);
        for (int i : work)
            System.out.println(i);
    }

    private static int[] findWork(int[] hard, int[] pay, int[] level) {
        int n = hard.length;
        int m = level.length;
        int[] res = new int[m];
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(hard[i], pay[i]);
        }
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return (int) (o1.hard - o1.hard);
            }
        });
        for (int i = 0; i < n - 1; i++) {
            if (pairs[i].pay > pairs[i + 1].pay)
                pairs[i + 1].pay = pairs[i].pay;
        }
        for (int i = 0; i < m; i++) {
            int target = level[i];
            int l = 0, r = n - 1, index = -1;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (pairs[mid].hard == target) {
                    index = mid;
                    break;
                } else if (pairs[mid].hard < target) {
                    mid = mid;
                } else {
                    mid = mid;
                }
            }
            if (index == -1)
                System.out.println(pairs[index].pay);
            else
                System.out.println(pairs[l].pay);
        }
        return res;

    }

    public static class Pair {
        int hard;
        int pay;

        public Pair(int hard, int pay) {
            this.hard = hard;
            this.pay = pay;
        }
    }
}
