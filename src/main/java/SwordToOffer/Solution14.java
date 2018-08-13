package SwordToOffer;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 * 考点：链表
 * 使用两个指针，第一个指针先走k个节点，然后两个指针一起移动，当第一个指针到尾的时候第二个指针既是所求
 * 复杂度O(N)+O(1)
 */
public class Solution14 {
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode p1 = head;
        int i = 0;
        while (i < k && p1 != null) {
            p1 = p1.next;
            i++;
        }
        //k大于链表长度直接返回
        if (i < k) return null;
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
