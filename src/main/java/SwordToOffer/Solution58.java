package SwordToOffer;

/**
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * 一种做法是直接构造其镜像，然后比较，不过这种又麻烦，性能又差，
 * 另一种直接在树上判断
 * 当前两个节点是否相等
 * 左子树的左子树和右子树的右子树是否相等
 * 左子树的右子树和右子树的左子树是否相等
 */
public class Solution58 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)
            return true;
        return init(pRoot.left, pRoot.right);
    }

    private boolean init(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        return left.val == right.val && init(left.left, right.right) && init(left.right, right.left);
    }
}