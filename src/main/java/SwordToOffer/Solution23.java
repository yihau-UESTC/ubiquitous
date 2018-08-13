package SwordToOffer;

import org.junit.Test;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * 考点：二叉搜索树的性质
 * 后序遍历的最后一个节点是根节点，BST满足root的左子树小于root.val,root的右子树大于root.val，递归判断
 * 每个子树的合法性，注意两个元素无论如何都是合法的BST后序序列。
 */
public class Solution23 {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] sequence, int start, int end) {
        if (end - start <= 1)
            return true;//两个元素，无论如何都是合法的BST后序序列
        int rootVal = sequence[end];
        int cur = start;
        while (cur < end && sequence[cur] < rootVal) {
            cur++;
        }
        for (int i = cur; i < end; i++) {
            if (sequence[i] < rootVal)
                return false;
        }
        return verify(sequence, start, cur - 1) && verify(sequence, cur, end - 1);
    }

    @Test
    public void run() {
        int[] arr = {5, 4, 3, 2, 1};
        System.out.println(VerifySquenceOfBST(arr));
    }
}
