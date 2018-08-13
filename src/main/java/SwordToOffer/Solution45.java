package SwordToOffer;

import org.junit.Test;

/**
 * 扑克牌，其中大小鬼为癞子，输入数组用0表示。判断是否能组成顺子。
 * 很容易想到先排序，然后统计0的数量，然后遍历排过序的数组，用0补全中间确实的数字组成顺子，这样的话由于使用了排序，复杂度为O(NlogN)
 * 优化的方法是可以先遍历一边数组，统计出0的数量，以及数组中的最小值min，然后以min为偏移量，对numbers[i]-min索引的位置
 * 赋值为true，最后看false的空位能否用0补完，如果补不完则为false，这样复杂度为O(N)
 */

public class Solution45 {
    public boolean isContinuous(int[] numbers) {
        int n = numbers.length;
        if (n == 0)
            return false;
        int min = Integer.MAX_VALUE, king = 0;
        for (int i = 0; i < n; i++) {
            if (numbers[i] == 0) {
                king++;
                continue;
            } else {
                min = Math.min(min, numbers[i]);
            }
        }
        boolean[] continuous = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (numbers[i] == 0)
                continue;
            else if (numbers[i] - min < n)
                continuous[numbers[i] - min] = true;
        }
        for (int i = 0; i < n; i++)
            if (!continuous[i])
                king--;
        return king == 0;
    }

    @Test
    public void run() {
        int[] arr = {0, 3, 4, 2, 7};
        System.out.println(isContinuous(arr));
    }
}
