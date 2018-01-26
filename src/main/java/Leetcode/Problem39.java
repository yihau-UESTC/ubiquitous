package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode39---combinationsum
 * 在一个数组中找出给定数的组合，每个元素可以用多次，但是组合要唯一。本题使用回溯法求解。
 * 是一种深度优先搜索的过程。
 */
public class Problem39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Arrays.sort(candidates);
        backtrack(result, new LinkedList<Integer>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, LinkedList<Integer> list, int[] candidates, int remain, int start) {
        if (remain < 0) return;//小于0的话说明加多了，返回到remove行，移除该元素
        else if (remain == 0)result.add(new LinkedList<Integer>(list));//=0表明表示搜索到合适的组合，将其添加到结果集，然后返回还要
        //remove 该元素，继续搜寻其他结果。
        else {//否则的话继续添加元素。
            for (int i = start; i < candidates.length; i++){
                list.add(candidates[i]);
                backtrack(result, list, candidates,remain - candidates[i], i);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {2,3,6,7};
        List<List<Integer>> list =  new Problem39().combinationSum(a, 7);
        System.out.println(list.toString());
    }
}
