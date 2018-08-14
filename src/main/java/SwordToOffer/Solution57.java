package SwordToOffer;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 * 总体分为两种情况，在右子树中和在祖先节点中，右子树中好判断，要不是右子树，要不是右子树中最左的节点，在祖先节点中需要注意，需要回溯
 * 到当前节点为父节点的左子树才可以，同时需要注意父节点是否为空
 */
public class Solution57 {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        //下个节点在右子树中
        if (pNode.right != null) {
            TreeLinkNode cur = pNode.right;
            while (cur.left != null)
                cur = cur.left;
            return cur;
        } else {
            //下个节点在祖先节点中，注意判断是否有父节点
            TreeLinkNode parent = pNode.next;
            while (parent != null) {
                if (pNode == parent.left)
                    return parent;
                pNode = parent;
                parent = parent.next;
            }
            return null;
        }
    }
}
