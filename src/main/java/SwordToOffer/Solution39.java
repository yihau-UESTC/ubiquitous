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

    //上边一种方法要有很多重复计算高度的步骤，使用后序遍历，判断当某个子树不平衡的时候直接停止返回-1，平衡的时候返回高度，
    //继续向上回溯。
    public boolean IsBalanced_Solution2(TreeNode root) {
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = getDepth(root.left);
        if (left == -1)
            return -1;
        int right = getDepth(root.right);
        if (right == -1)
            return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }
}
