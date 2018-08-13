package SwordToOffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个字符串，按字典序打印出该字符串中字符的所有排列。例如输入字符串 abc，则打印出由字符 a, b, c 所能排列出来的所有字符串 abc, acb, bac, bca, cab 和 cba。
 * 考点：回溯法
 * 排列问题和组合问题都是用回溯法解决的经典问题，本题需要注意两点
 * 1、使用一个visited来进行剪枝
 * 2、因为可能有重复的字符，所以需要将字符排序，然后处理过程中使用给一个pre节点来记录之前的节点，从而在广度上判断重复剪枝。
 * 还有一点可以优化的地方，把下面记录结果的current换成StringBuilder会更好
 */
public class Solution27 {
    private ArrayList<String> res = new ArrayList<>();
    private boolean[] visited;

    public ArrayList<String> Permutation(String str) {
        if (str == null || str.equals(""))
            return res;
        visited = new boolean[str.length()];
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        find(chars, 0, "");
        return res;
    }

    private void find(char[] str, int index, String current) {
        if (index == str.length) {
            res.add(current);
            return;
        }
        char pre = '0';
        for (int i = 0; i < str.length; i++) {
            if (!visited[i] && (pre == '0' || str[i] != pre)) {
                current += str[i];
                visited[i] = true;
                find(str, index + 1, current);
                visited[i] = false;
                current = current.substring(0, current.length() - 1);
                pre = str[i];
            }
        }
    }

    @Test
    public void run() {
        ArrayList<String> abc = Permutation("aa");
        System.out.println(abc.toString());
    }
}
