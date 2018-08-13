package SwordToOffer;

import org.junit.Test;

/**
 * 输入："I am a student."
 * 输出："student. a am I"
 * 看了题解说不能使用额外的空间，如果不使用额外的空间则应该先将每个单词逆序，判断到空格为一个单词，然后将整个字符串逆序
 */
public class Solution44 {
    public String ReverseSentence(String str) {
        if (str == null || str.equals("")) return "";
        String[] split = str.split(" ");
        if (split.length <= 1)
            return str;
        int l = 0, r = split.length - 1;
        while (l < r) {
            String tmp = split[l];
            split[l] = split[r];
            split[r] = tmp;
            l++;
            r--;
        }
        StringBuilder res = new StringBuilder();
        for (String s : split)
            res.append(s).append(" ");
        return res.substring(0, res.length() - 1);
    }

    @Test
    public void run() {
        System.out.println(ReverseSentence(""));
    }
}
