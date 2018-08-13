package SwordToOffer;

/**
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 * 在字符串后面append自身即可，注意两点，1、空字符串，2、n>str的长度，可能需要append不止一个str；
 */
public class Solution43 {
    public String LeftRotateString(String str, int n) {
        if (str == null || str.equals("")) return "";
        StringBuilder sb = new StringBuilder(str);
        while (n + str.length() > sb.length())
            sb.append(str);
        return sb.substring(n, n + str.length());
    }
}
