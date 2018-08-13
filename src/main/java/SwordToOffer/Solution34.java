package SwordToOffer;

import org.junit.Test;

/**
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1.
 * 本题就是用空间来换取时间的问题，一般可以使用查找表，不过这里只有字母组成我们可以使用ascii256长度的数组
 * 用两个数组一个记录频率，一个记录索引，其实记录索引的数组不是必要的，我们统计完频率后再次遍历str找到最早出现的频率为1的
 * 字符即可，
 * 复杂度O(N) + O(N)
 */
public class Solution34 {
    public int FirstNotRepeatingChar(String str) {
        int[] fre = new int[256];
        int[] index = new int[256];
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            fre[chars[i]]++;
            index[chars[i]] = i;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 256; i++) {
            if (fre[i] == 1)
                min = Math.min(min, index[i]);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    @Test
    public void run() {
        System.out.println(FirstNotRepeatingChar("aacbdce"));
        System.out.println(7 % 1000000007);
    }
}
