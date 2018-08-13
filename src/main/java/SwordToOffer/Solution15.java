package SwordToOffer;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 * 考点：链表
 * 翻转链表 1、头插法
 * 2、使用pre、cur、next三指针
 * 复杂度O(N)+O(1)
 */
public class Solution15 {
    public ListNode ReverseList(ListNode head) {
        //头插法
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        while (cur != null) {
            ListNode memo = cur.next;
            if (!cur.equals(head)) {
                cur.next = dummy.next;
                dummy.next = cur;
            } else {
                cur.next = null;
            }
            cur = memo;
        }
        return dummy.next;
    }

    public ListNode ReverseList2(ListNode head) {
        //三指针
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
