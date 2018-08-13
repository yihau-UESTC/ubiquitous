package SwordToOffer;

/**
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 * 使用两个指针，快慢指针2,1，如果链表有环，则快慢指针一定会在环内相遇，相遇点距离入口点的距离和头结点
 * 到入口点的距离相同。然后将fast指针指向头结点，改为每次走一步，下次fast与slow相遇的节点即使入口点。
 */
public class Solution55 {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null)
            return null;
        ListNode fast = pHead, slow = pHead;
        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
