package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * leetcode40---combinationsum2
 * 在一个数组中找出给定数的组合，每个元素只能用一次，但是组合要唯一。本题使用回溯法求解。
 * 是一种深度优先搜索的过程。
 */
public class Problem40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Arrays.sort(candidates);
        backtrace(result, new LinkedList<Integer>(), candidates, target, 0);
        return result;
    }


    private void backtrace(List<List<Integer>> list, List<Integer> tempList,int[] array, int remain, int start){
        if (remain < 0)return;
        else if (remain == 0)list.add(new ArrayList<>(tempList));
        else{
            for (int i = start; i < array.length; i++){
                if (i > start && array[i] == array[i - 1])continue;//这里是与上一题不一样的点，避免在广度上的一样元素，
                // 来避免[2,2,2]->4这样的出现[2,2],[2,2]的重复结果。只能在深度上使用重复元素，而不能在广度上使用同样的元素。
                tempList.add(array[i]);
                backtrace(list, tempList, array, remain - array[i], i + 1);//这里是+1，要避免同一元素用多次。
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {10, 1, 2, 7, 6, 1, 5};
        int[] b = {2,2,2};
        new Problem40().combinationSum2(a, 8).stream().forEach(System.out::println);
        new Problem40().combinationSum2(b, 4).stream().forEach(System.out::println);
    }
}
