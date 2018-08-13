package bishi.p2;

import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] apple = new int[n];
        for (int i = 0; i < n; i++) {
            apple[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] ask = new int[m];
        for (int i = 0; i < m; i++) {
            ask[i] = scanner.nextInt();
        }
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(apple[0], 1);
        for (int i = 1; i < n; i++) {
            apple[i] = apple[i] + apple[i - 1];
            treeMap.put(apple[i], i + 1);
        }
        for (int i = 0; i < m; i++) {
            Integer integer = treeMap.ceilingKey(ask[i]);
            System.out.println(treeMap.get(integer));
        }

//        for (int i = 0; i < m; i++){
//            for (int j = 0; j < n; j++){
//                if (ask[i] <= apple[j]){
//                    System.out.println(j + 1);
//                    break;
//                }
//            }
//        }

    }
}
