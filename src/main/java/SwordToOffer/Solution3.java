package SwordToOffer;

import java.util.ArrayList;
import java.util.Stack;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

/**
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * 本题解法较多
 * 1、遍历链表，翻转数组
 * 2、递归遍历链表
 * 3、遍历链表，使用栈存储，然后出栈就是逆序
 * 4、翻转链表，在遍历。
 * 前三个都是常规思路，需要额外的空间，第四中不需要额外空间，使用头插法将链表翻转，这里要掌握头插法的使用，链表中很多需要翻转的情况
 */
public class Solution3 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) return list;
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }

    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null)return list;
        //设立虚拟头节点
        ListNode head = new ListNode(-1);
        //翻转链表
        while (listNode != null){
            ListNode next = listNode.next;
            //将head后的线接在将要插入的listNOde节点后面
            listNode.next = head.next;
            //将listNode接在head之后
            head.next = listNode;
            listNode = next;
        }
        ListNode cur = head.next;
        while (cur != null){
            list.add(cur.val);
            cur = cur.next;
        }
        return list;
    }
}
