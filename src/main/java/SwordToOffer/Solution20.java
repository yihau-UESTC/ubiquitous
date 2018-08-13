package SwordToOffer;

import scala.collection.mutable.HashTable;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数。
 * 考点：栈
 * 维护一个保存当前最小元素的栈，每当有个元素进栈，判断这个元素和当前最小栈的栈顶元素的大小，然后选择小的元素进栈。
 * 使得最小栈的栈顶总是当前栈中的最小元素。
 */
public class Solution20 {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        Solution20 stack = new Solution20();
        stack.push(1);
        stack.push(2);
        stack.push(5);
        stack.push(3);

    }
}
