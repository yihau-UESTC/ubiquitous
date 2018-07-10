package SwordToOffer;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 考点：栈和队列的性质
 * 进队都进stack1，出栈的时候从stack2出，但要注意，每次stack2中为空的时候才能把stack1中的元素导入，而且每次导入
 * 要把当前stack1中的所有元素都导入。
 */
public class Solution5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(!stack2.empty()){
            return stack2.pop();
        }else{
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
            if(!stack2.empty()){
                return stack2.pop();
            }
        }
        return -1;
    }
}