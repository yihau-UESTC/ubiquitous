package alginterview;

import org.junit.Test;

import java.util.List;
import java.util.Stack;

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
          System.out.print(cur.val + "->");
          cur = cur.next;
      }
      System.out.println("NULL");
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

    /**
     * 86. Partition List
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        ListNode lower = dummy;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val < x) {
                //如果当前节点就是lower的下个节点的话直接跳过
                if (cur != lower.next) {
                    pre.next = next;
                    cur.next = lower.next;
                    lower.next = cur;
                }
                //修改lower指针和cur指针，注意这里不需要修改pre指针，因为cur指向的节点被拿去前面了，所以pre后面是原来的next,刚刚好
                lower = lower.next;
                cur = next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void run2() {
        int[] arr = {1, 1, 3, 1};
        ListNode list = createList(arr);
        printList(list);
        ListNode partition = partition(list, 2);
        printList(partition);
    }

    /**
     * 328. Odd Even Linked List
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        int num = 0;
        ListNode odd = dummy;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        while (cur != null) {
            ListNode next = cur.next;
            num++;
            if (num % 2 != 0) {
                if (odd.next != cur) {
                    pre.next = next;
                    cur.next = odd.next;
                    odd.next = cur;
                }
                odd = odd.next;
                cur = next;
            } else {
                pre = cur;
                cur = pre.next;
            }
        }

        return dummy.next;
    }

    @Test
    public void run3() {
        int[] arr = {2, 1, 3, 5, 6, 4, 7};
        ListNode list = createList(arr);
        printList(list);
        ListNode listNode = oddEvenList(list);
        printList(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode cur = res;
        int move = 0;
        while (p1 != null && p2 != null) {
            int sum = p1.val + p2.val + move;
            int val = sum % 10;
            move = sum / 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p2 != null) {
            p1 = p2;
        }
        while (p1 != null) {
            int sum = p1.val + move;
            int val = sum % 10;
            move = sum / 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            p1 = p1.next;
        }
        if (move != 0) cur.next = new ListNode(move);
        return res.next;
    }

    @Test
    public void run4() {
        int[] arr1 = {2, 4, 3};
        int[] arr2 = {5, 6, 6, 3};
        ListNode list = createList(arr1);
        ListNode list1 = createList(arr2);
        ListNode listNode = addTwoNumbers(list, list1);
        printList(listNode);
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int move = 0;
        while (!stack1.empty() && !stack2.empty()) {
            int sum = stack1.pop() + stack2.pop() + move;
            int val = sum % 10;
            move = sum / 10;
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        if (!stack2.empty()) {
            stack1 = stack2;
        }
        while (!stack1.empty()) {
            int sum = stack1.pop() + move;
            int val = sum % 10;
            move = sum / 10;
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        if (move != 0) cur.next = new ListNode(move);
        return reverseList(res.next);
    }

    private ListNode reverseList(ListNode head) {
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

    @Test
    public void run5() {
        int[] arr1 = {9, 1, 6};
        int[] arr2 = {0};
        ListNode list = createList(arr1);
        ListNode list1 = createList(arr2);
        ListNode listNode = addTwoNumbers2(list, list1);
        printList(listNode);
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void run6() {
        int[] arr = {6, 2, 6, 3, 4, 5, 6};
        ListNode list = createList(arr);
        printList(list);
        ListNode listNode = removeElements(list, 6);
        printList(listNode);
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                cur.next = cur.next.next.next;
                while (cur.next != null && cur.next.val == val) cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void run7() {
        int[] arr = {1, 1, 3, 3, 4, 4, 5};
        ListNode list = createList(arr);
        printList(list);
        ListNode listNode = deleteDuplicates(list);
        printList(listNode);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while (l1 != null && l2 != null) {
            int val = l1.val < l2.val ? l1.val : l2.val;
            cur.next = new ListNode(val);
            cur = cur.next;
            if (val == l1.val) l1 = l1.next;
            else l2 = l2.next;
        }
        if (l2 != null) l1 = l2;
        if (l1 != null) cur.next = l1;
        return res.next;
    }

    @Test
    public void run8() {
        int[] arr1 = {1, 2, 4};
        int[] arr2 = {1, 3, 4};
        ListNode list = createList(arr1);
        ListNode list1 = createList(arr2);
        ListNode listNode = mergeTwoLists(list, list1);
        printList(listNode);
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        while (p.next != null && p.next.next != null) {
            ListNode n1 = p.next;
            ListNode n2 = p.next.next;

            n1.next = n2.next;
            n2.next = n1;
            p.next = n2;

            p = n1;
        }
        return dummy.next;
    }

    @Test
    public void run9() {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode list = createList(arr);
        printList(list);
        ListNode listNode = swapPairs(list);
        printList(listNode);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int count = 0;
        ListNode first = dummy;
        ListNode end = head;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count++;
            if (count >= k) {
                first.next = pre;
                first = end;
                end = cur;
                pre = null;
                count = 0;
            }
        }
        if (count < k) {
            ListNode rpre = null;
            ListNode rcur = pre;
            while (rcur != null) {
                ListNode rnext = rcur.next;
                rcur.next = rpre;
                rpre = rcur;
                rcur = rnext;
            }
            first.next = rpre;
        }
        return dummy.next;
    }

    @Test
    public void run10() {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode list = createList(arr);
        ListNode listNode = reverseKGroup(list, 3);
        printList(listNode);
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = head;//排好序的链表的末尾
        ListNode cur = pre.next;//当前需要排序的节点
        while (cur != null) {
            ListNode next = cur.next;
            int val = cur.val;
            //如果当前元素的值大于等于排好序的末端节点值，直接插入(原地不动),修改遍历的指针即可。
            if (val >= pre.val) {
                pre = cur;
                cur = next;
                continue;
            }
            //如果不符合上个条件，则需要一次从头到已排好序的节点的遍历，找到应该插入的位置。
            ListNode start = dummy;
            ListNode insert = start.next;
            pre.next = null;//首先将排好序的部分和当前需要排序的节点的指针断开，这样在下面的遍历中到尾端就会退出循环。
            while (insert != null) {
                if (insert.val > val) {
                    start.next = cur;
                    cur.next = insert;
                    //这里重新将断开的部分连接起来
                    pre.next = next;
                    cur = next;
                    break;
                } else {
                    start = insert;
                    insert = insert.next;
                }
            }
        }
        return dummy.next;
    }

    @Test
    public void run11() {
        int[] arr = {-1, 5, 3, 4, 0};
        ListNode list = createList(arr);
        ListNode listNode = insertionSortList(list);
        printList(listNode);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        int size = 0;
        ListNode cur = head;
        //count节点的个数
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode left = null, right = null, tail = null;
        //自底向上的归并排序，分组宽度依次为2->4->8->...->size
        for (int width = 1; width < size; width *= 2) {
            cur = dummy.next;
            tail = dummy;//tail表示上段序列的末尾，也是下端序列的开头
            while (cur != null) {
                left = cur;
                //将分组第一段节点从链表中断开
                right = split(left, width);
                //将分组第二段节点从链表中断开
                cur = split(right, width);
                //对两端链表进行合并
                tail = merge(left, right, tail);
            }
        }
        return dummy.next;
    }

    /**
     * @param l1   归并的一个头结点
     * @param l2   归并的另一个头结点
     * @param head 上一段的尾节点
     * @return 归并过后的尾节点
     */
    private ListNode merge(ListNode l1, ListNode l2, ListNode head) {
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            }
        }
        cur.next = (l1 == null ? l2 : l1);
        while (cur.next != null) cur = cur.next;
        return cur;
    }

    /**
     * @param head  起始节点
     * @param width 分组的宽度
     * @return
     */
    private ListNode split(ListNode head, int width) {
        //联系到主函数，检查过head的存在性，但是这里还是要检查head是否存在，因为第二段调用的时候head已经变了
        for (int i = 1; head != null && i < width; i++) head = head.next;
        if (head == null) return null;

        ListNode second = head.next;
        //将第一段和第二段之间的引用断开
        head.next = null;
        return second;
    }

    @Test
    public void run12() {
        int[] arr = {-1, 5, 3, 4, 0};
        ListNode list = createList(arr);
        ListNode listNode = sortList(list);
        printList(listNode);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        for (int i = 0; p1 != null && i < n; i++) {
            p1 = p1.next;
        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return dummy.next;
    }

    @Test
    public void run13() {
        int[] arr = {1};
        ListNode list = createList(arr);
        ListNode listNode = removeNthFromEnd(list, 1);
        printList(listNode);
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        int n = k % size;
        if (n == 0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        for (int i = 0; i < n; i++) {
            p1 = p1.next;
        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        dummy.next = p2.next;
        p1.next = head;
        p2.next = null;
        return dummy.next;
    }

    @Test
    public void run14() {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode list = createList(arr);
        ListNode listNode = rotateRight(list, 5);
        printList(listNode);
        System.out.println(0 % 5);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        while (p1.next != null && p1.next.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        ListNode mid = p2.next;

        p2.next = null;
        //翻转后半段
        ListNode pre = null;
        ListNode cur = mid;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        ListNode l1 = head;
        ListNode l2 = pre;
        cur = dummy;
        while (l1 != null) {
            cur.next = l1;
            l1 = l1.next;
            cur = cur.next;
            cur.next = l2;
            l2 = l2.next;
            cur = cur.next;
        }
        cur.next = l2;
    }

    @Test
    public void run15() {
        int[] arr = {1};
        ListNode list = createList(arr);
        reorderList(list);
        System.out.println("");
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        while (p1.next != null && p1.next.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        ListNode mid = p2.next;

        p2.next = null;
        //翻转后半段
        ListNode pre = null;
        ListNode cur = mid;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        p1 = head;
        p2 = pre;
        while (p1 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    @Test
    public void run16() {
        int[] arr = {1, 2, 2, 1};
        ListNode list = createList(arr);
        boolean palindrome = isPalindrome(list);
        System.out.println(palindrome);
    }


}
