package SwordToOffer;

import java.util.*;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * 考点：数组
 * 简单看就是一个统计多数的问题，可以使用查找表(hashMap)来统计，然后遍历map，拿到个数最大的元素即可，由于
 * 哈希表的查找时间是O(1)，整体的时间复杂度在O(N)级别，空间复杂度也是O(N)
 * 参考答案用的一种更为巧妙的方法，初始化第一个元素为多数，遍历数组，每个元素判断是否和当前多数的元素相同
 * 如果相同cnt+1，否则cnt-1，这样如果有超过半数的元素，那么一定会使majority。复杂度也是O(N)
 */
public class Solution28 {
    public int MoreThanHalfNum_Solution(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
        }
        int half = array.length / 2;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > half)
                return entry.getKey();
        }
        return 0;
    }

    public int MoreThanHalfNum2(int[] array) {
        int n = array.length;
        int cnt = 1;
        int majority = array[0];
        for (int i = 1; i < n; i++) {
            cnt = majority == array[i] ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                majority = array[i];
                cnt = 1;
            }
        }
        cnt = 0;
        for (int val : array) {
            if (val == majority)
                cnt++;
        }
        return cnt > n / 2 ? majority : 0;
    }
}
