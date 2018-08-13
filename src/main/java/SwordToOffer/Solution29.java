package SwordToOffer;

import org.junit.Test;

import java.util.*;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 * 1、利用快排，
 * 2、利用大顶堆，每次移除最大的元素
 */
public class Solution29 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (k > input.length || k < 0)
            return new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < input.length; i++) {
            queue.add(input[i]);
            if (queue.size() > k) {
                queue.remove();
            }
        }
        return new ArrayList<>(queue);
    }

    @Test
    public void run() {
        int[] arr = {4, 5, 1, 6, 2, 7, 3, 8};
        System.out.println(GetLeastNumbers_Solution(arr, 4).toString());
    }
}
