package SwordToOffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 把字符串转换成整数
 * 这题的难度不大，主要考编程能力和一些边界条件的判断，上面是优化的代码，更整洁一点，不过没有判断数值大于int的范围的情况。
 */
public class Solution49 {

    public int StrToIntOpt(String str) {
        if (str == null || str.equals(""))
            return 0;
        char[] chars = str.toCharArray();
        boolean flag = chars[0] == '-';
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i == 0 && (c == '+' || c == '-'))
                continue;
            if (c < '0' || c > '9')
                return 0;
            res = res * 10 + (c - '0');
        }
        return flag ? -res : res;
    }


    public int StrToInt(String str) {
        if (str == null || str.equals(""))
            return 0;
        char[] chars = str.toCharArray();
        List<Integer> list = new ArrayList<>();
        boolean f = true;
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && chars[i] == 43) {
                f = true;
                continue;
            } else if (i == 0 && chars[i] == 45) {
                f = false;
                continue;
            }
            if (chars[i] > 47 && chars[i] < 58) {
                list.add(chars[i] - 48);
            } else
                return 0;
        }
        long num = 0;
        int n = list.size();
        for (int i = n - 1; i >= 0; i--) {
            num += list.get(i) * Math.pow(10, n - 1 - i);
        }
        if (!f)
            num = -num;
        if (num <= Integer.MAX_VALUE && num >= Integer.MIN_VALUE)
            return (int) num;
        return 0;
    }

    @Test
    public void run() {
        System.out.println(StrToInt("-2147483648"));
    }
}
