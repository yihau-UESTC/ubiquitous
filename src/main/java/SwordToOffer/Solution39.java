package SwordToOffer;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 * 两重递归，树的后序遍历的应用，判断一颗树是否为BST，首先判断其左右子树是否为BST-->一重递归
 * 然后判断当前节点的左右子树高度差是否<=1，需要递归求得子树的高度-->二重递归
 */
public class Solution39 {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null)
            return true;
        boolean b1 = IsBalanced_Solution(root.left);
        boolean b2 = IsBalanced_Solution(root.right);
        int h1 = height(root.left);
        int h2 = height(root.right);
        boolean f = Math.abs(h1 - h2) < 2;
        return f && b1 && b2;
    }

    private int height(TreeNode root){
        if (root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
