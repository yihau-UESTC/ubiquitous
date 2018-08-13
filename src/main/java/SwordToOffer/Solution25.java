package SwordToOffer;

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 * 考点：链表
 * 直接先复制链表，在循环复制random链接复杂度超时O(N^2);可以采用三步来完成复制
 * 1、先在每个节点复制插入一个节点，
 * 2、给复制插入的节点添加randome链接
 * 3、将链表拆分为两个链表
 * 注意在遍历包含了复制节点的链表中，next的指针是要指向复制的节点还是原链表的下个节点
 * 时间复杂度为O(N)
 */
public class Solution25 {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        RandomListNode cur = pHead;
        //复制节点
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        //添加random链接
        cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if (cur.random != null)
                clone.random = cur.random.next;
            cur = clone.next;
        }
        //拆分成两个链表
        cur = pHead;
        RandomListNode cloneHead = pHead.next;
        while (cur.next != null) {
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return cloneHead;

    }
}
