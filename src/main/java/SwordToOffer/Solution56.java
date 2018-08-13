package SwordToOffer;

import org.junit.Test;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 * 考点：链表
 * 这题如果只是删除重复的节点，比如1->2->3->3->4->4->5 处理后为 1->2->3->4->5的话是很好解决的，
 * 用链表的pre，cur，next很容易，不过这题也不难，多一点判断，碰到重复的迭代检测到值发生变化的节点，然后直接
 * 将pre的指针指向变化的节点即可，掌握pre，cur，next的常规操作即可。
 */
public class Solution56 {
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode dummy = new ListNode(-1);
        dummy.next = pHead;
        ListNode pre = dummy, cur = pHead;
        while (cur != null) {
            ListNode next = cur.next;
            if (next != null && cur.val == next.val) {
                while (next != null && cur.val == next.val) {
                    next = next.next;
                }
                pre.next = next;
                cur = next;
            } else {
                pre = cur;
                cur = next;
            }
        }
        return dummy.next;
    }

    @Test
    public void run() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        System.out.println(deleteDuplication(l1));
    }

}
