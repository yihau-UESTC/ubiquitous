package SwordToOffer;

import java.util.LinkedList;
import java.util.Queue;

public class Utils {
    public static void printTree(TreeNode node) {
        if (node == null) System.out.println("the tree is empty");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                System.out.print(poll.val + " ");
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
            System.out.println();
        }
    }

    public static void printLinkList(ListNode node) {
        if (node == null) System.out.println("the list is empty");
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.val).append("-->");
            node = node.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }
}
