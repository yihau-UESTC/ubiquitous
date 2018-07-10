package SwordToOffer;

/**
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 考察点字符串
 * 字符串替换，可以使用一行替换代码直接完成，但这里是要将空格改为“%20”，也就是一个字符变成三个字符，由于字符串是
 * 基于数组存储的，如果我们从前向后直接替换的话，会造成大量的数组copy，这样效率很低，所以可以从后往前复制，
 * 首先统计空格的个数，然后在字符串后面开辟出足够长度的空格，通过两个指针来完成复制替换过程。
 * 复杂度O(N)+O(1)
 */
public class Solution2 {
    public String replaceSpace(StringBuffer str) {
        int n = str.length();
        for(int i = 0; i < n; i++){
            if(str.charAt(i) == ' '){
                str.append("  ");
            }
        }
        int p1 = n - 1, p2 = str.length() - 1;
        while(p1 >= 0 && p2 > p1){
            char c = str.charAt(p1--);
            if(c == ' '){
                str.setCharAt(p2--, '0');
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            }else{
                str.setCharAt(p2--, c);
            }
        }
        return str.toString();
    }
}
