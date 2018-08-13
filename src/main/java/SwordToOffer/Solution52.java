package SwordToOffer;

import java.util.HashMap;

public class Solution52 {
    public boolean match(char[] str, char[] pattern) {
        int p1 = 0, p2 = 0;
        while (p1 < str.length && p2 < pattern.length) {
            if (pattern[p2] == '.' || str[p1] == pattern[p2]) {
                p1++;
                p2++;
            } else if (str[p1] != pattern[p2]) {
                if (pattern[p2 + 1] == '*') {
                    p1++;
                    p2 += 2;
                } else
                    return false;
            }
        }
        return false;
    }
}
