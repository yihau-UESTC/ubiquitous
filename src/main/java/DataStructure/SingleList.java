package DataStructure;

import org.junit.Test;

public class SingleList {
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

  public ListNode createList(int[] arr){
        if (arr.length < 1)throw new IllegalArgumentException("len must bigger 1");
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
  }

  public void printList(ListNode head){
      ListNode cur = head;
      while (cur != null){
          System.out.println(cur.val);
          cur = cur.next;
      }
  }




    public ListNode reverseBetween(ListNode head, int m, int n) {
       ListNode dummy = new ListNode(-1);
       dummy.next = head;
       ListNode pre = dummy;
       //将pre移动到要变动的[m,n]前一个节点
       for (int i = 0; i < m - 1; i++){
           pre = pre.next;
       }
       ListNode cur = pre.next;
        for (int i = 0; i < n - m; i++){
            //使用头插法将next节点插入到pre之后,将pre-cur-next--->pre-next-cur---->pre-next.next-next-cur
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;

    }

    @Test
    public void run(){
        int[] arr = {1,2,3};
        ListNode head = createList(arr);
        printList(head);
        ListNode l = reverseBetween(head, 2, 3);
        printList(head);
    }
}
