package DataStructure;

import java.util.Arrays;

public class KMP {
    /**
     * 暴力算法
     * @param p
     * @param text
     * @return
     */
    public static int BFMatch(char[] p, char[] text){
        int n = text.length;
        int m = p.length;
        int i = 0, j =0;
        //j<m退出代表找到匹配的字符串，返回结果i-j就是在文本中对应的位置
        //i<n退出代表没有找到匹配的字符串，返回i-j可以判断出大于n-m(最靠右的匹配位置，再往右就没有那么多字符可以匹配)，匹配失败
        while (j < m && i < n){
            //当前字符匹配，i与j都加一
            if (text[i] == p[j]){
                i++; j++;
            //当前字符不匹配，需要把i从开始的位置向后移动一位。因为i与j携手共进了j个位置，所以先减j，然后在加1.j需要置0开始新一轮的对比
            }else {
                i = i - j + 1; j = 0;
            }
        }
        return i - j;
    }

    public static int KMPMatch(char[] p, char[] text){
        int[] next = buildNext(p);
        int n = text.length;
        int m = p.length;
        int i = 0, j = 0;
        while (j < m && i < n){
            //匹配的时候，i和j携手共进
            if (j < 0 || text[i] == p[j]){
                i++;j++;
                //不匹配的时候j回退到上一个i和j匹配的位置
            }else {
                j = next[j];
            }
        }
        return i - j;
    }

    /**
     * 构建一个数组，数组中的值为当前位置之前的串中，前缀和后缀匹配的最大长度，其算法和KMP的主算法很类似。
     * @param p
     * @return
     */
    private static int[] buildNext(char[] p) {
        int m = p.length;
        int j = 0;//j相当于主指针
        int next[] = new int[m];
        //这里刚好可以和后面的判断条件t<0配合
        next[0] = -1;
        int t = -1;//t相当于已经匹配的前缀和后缀底的长度
        while (j < m - 1){
            //匹配的时候，记录next数组的值，j和t携手共进1
            if (t < 0 || p[j] == p[t]){
                j++;t++;
                next[j] = p[j] != p[t] ? t : next[t];
                //不匹配的时候，t回退到上一个前缀和后缀匹配的位置。
            }else {
                t = next[t];
            }
        }
        return next;
    }


    public static void main(String[] args) {
        String t = "ababc";
        String t1 = "abaabcabdppababc";
        int i = BFMatch(t.toCharArray(), t1.toCharArray());
        System.out.println(i);
        System.out.println(Arrays.toString(buildNext(t.toCharArray())));
        int i1 = KMPMatch(t.toCharArray(), t1.toCharArray());
        System.out.println(i1);
    }
}
