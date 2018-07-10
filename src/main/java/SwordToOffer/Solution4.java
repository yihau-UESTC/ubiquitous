package SwordToOffer;

import org.junit.Test;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * 考点：递归，二叉树遍历
 * 根据前序遍历序列和中序遍历序列递归构建二叉树
 */
public class Solution4 {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        int n = pre.length;
        if (n <= 0) return null;
        return init(pre, in, 0, 0, in.length);
    }

    /**
     *
     * @param pre 前序序列
     * @param in 中序序列
     * @param sp 前序索引
     * @param si 中序索引
     * @param len 序列长度
     * @return
     */
    private TreeNode init(int[] pre, int[] in, int sp, int si, int len) {
        if (len == 0)return null;
        if (len == 1) {
            TreeNode node = new TreeNode(pre[sp]);
            return node;
        }
        TreeNode root = new TreeNode(pre[sp]);
        int llen = 0, rlen =0;
        for (int i = si; i < si + len; i++) {
            if (in[i] == pre[sp]) {
                llen = i - si;
                break;
            }
        }
        rlen = len - llen - 1;
        root.left = init(pre, in, sp + 1, si, llen);
        root.right = init(pre, in, sp + 1 + llen, si + llen + 1, rlen);
        return root;
    }
    @Test
    public void run(){
        int[] a1 = {1,2,4,7,3,5,6,8};
        int[] a2 = {4,7,2,1,5,3,8,6};
        TreeNode treeNode = reConstructBinaryTree(a1, a2);
        Utils.printTree(treeNode);
    }
}
