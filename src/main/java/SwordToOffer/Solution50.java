package SwordToOffer;

import java.util.HashSet;
import java.util.Set;

/**
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 * 常规操作使用查找表，但是题目中如果不让使用额外空间的话使用第二种方法。
 */
public class Solution50 {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (set.contains(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            }
            set.add(numbers[i]);
        }
        return false;
    }

    /**
     * 检查每个数字，将其放在正确的位置，如果发现某个数字的正确位置上的数值和其自身相等，那么说明该数字重复。
     */
    public boolean duplicate2(int numbers[], int length, int[] duplication) {
        for (int i = 0; i < length; i++) {
            //这里使用while，每次换过来的数可能还是不在正确位置，需要继续判断
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                //swap
                int tmp = numbers[i];
                numbers[i] = numbers[tmp];
                numbers[tmp] = tmp;
            }
        }
        return false;
    }
}
