package SwordToOffer;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 * 考点：回溯法
 * 递归终止条件叶子节点，回溯法要注意，回来的时候要把之前加上的节点再去除。
 */
public class Solution24 {
    private ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null)
            return res;
        find(root, target, new ArrayList());
        return res;
    }

    private void find(TreeNode root, int remain, ArrayList<Integer> list) {
        if (root.left == null && root.right == null) {
            if (remain == root.val) {
                list.add(root.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
            return;
        }
        list.add(root.val);
        if (root.left != null)
            find(root.left, remain - root.val, list);
        if (root.right != null)
            find(root.right, remain - root.val, list);
        list.remove(list.size() - 1);
    }

    @Test
    public void run() {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(7);
//        TreeNode t5 = new TreeNode(12);
        t1.left = t2;
//        t1.right = t5;
        t2.left = t3;
        t2.right = t4;
        ArrayList<ArrayList<Integer>> arrayLists = FindPath(t1, 10);
        System.out.println(arrayLists.toString());
    }
}