package SwordToOffer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 * 本题直接比较int型的大小是很难的，无法区别123和32谁放在前面，首先想到的是一位一位数字来比较，所以转换成字符串是最方便的，
 * 但其实可以直接对字符串进行排序，S1+S2 > S2+S1的话，那么S2就应该排在S1前面，利用这点，思路就很清晰了
 */
public class Solution32 {
    public String PrintMinNumber(int[] numbers) {
        int n = numbers.length;
        if (n <= 0) return "";
        String[] str = new String[n];
        for (int i = 0; i < n; i++)
            str[i] = String.valueOf(numbers[i]);
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        String res = "";
        for (String cur : str)
            res += cur;
        return res;
    }
}
