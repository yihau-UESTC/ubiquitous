package SwordToOffer;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * 考点:BST
 * 在中序遍历的过程中利用一个pre节点来记录上一个节点，然后将两个节点链接起来
 */
public class Solution26 {
    private TreeNode head = null;
    private TreeNode pre = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        inOrder(pRootOfTree);
        return head;
    }

    private void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        root.left = pre;
        if (pre != null)
            pre.right = root;
        pre = root;
        if (head == null)
            head = root;
        inOrder(root.right);
    }
}
