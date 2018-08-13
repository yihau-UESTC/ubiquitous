package SwordToOffer;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 考点：二叉树、递归
 * 对于每个节点，镜像其子树，然后交换其子树，
 */
public class Solution18 {
    public void Mirror(TreeNode root) {
        if (root == null) return;
        Mirror(root.left);
        Mirror(root.right);
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
    }
}
