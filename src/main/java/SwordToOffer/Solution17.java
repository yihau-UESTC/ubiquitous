package SwordToOffer;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * 考点：二叉树、递归
 * 这是一个双重递归问题，一层递归：对于A中每个节点判断是否存在B，||，二层递归：判断A中某个节点的子结构和B是否相同。&&
 */
public class Solution17 {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return isSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private boolean isSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        return root1.val == root2.val && isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
    }

}
