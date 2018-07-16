package SwordToOffer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次，找出这两个数。
 * 使用查找表可以很容易得到O(N)级别的算法，更好的方法使用位运算（）
 */
public class Solution40 {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++){
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if (entry.getValue() == 1)
                if (num1[0] == 0)
                    num1[0] = entry.getKey();
                else
                    num2[0] = entry.getKey();
        }
    }

    public void FindNumsAppearOnce2(int [] array,int num1[] , int num2[]) {
        int num = 0;
        for (int n : array)
            num ^= n;
        //此时num = a ^ b
        num &= -num;
        for (int n : array)
            if ((n & num) == 0)
                num1[0] ^= n;
            else
                num2[0] ^= n;
    }

    @Test
    public void run(){
        int[] arr = {1,2,3,2};
        FindNumsAppearOnce2(arr, new int[1], new int[1]);
    }

}
