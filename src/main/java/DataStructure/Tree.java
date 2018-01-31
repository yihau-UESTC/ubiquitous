package DataStructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午3:45 17-10-25
 */
class Node<T> {

    private Node<T> left;
    private Node<T> right;
    private T value;

    public Node() {
    }
    public Node(Node<T> left,Node<T> right,T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node(T value) {
        this(null,null,value);
    }
    public Node<T> getLeft() {
        return left;
    }
    public void setLeft(Node<T> left) {
        this.left = left;
    }
    public Node<T> getRight() {
        return right;
    }
    public void setRight(Node<T> right) {
        this.right = right;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return (String) this.getValue();
    }
}

public class Tree<T> {

    private Node<T> root;

    public Tree() {
    }

    public Tree(Node<T> root) {
        this.root = root;
    }

    //创建二叉树
    public void buildTree() {

        Scanner scn = null;
        try {
            scn = new Scanner(new File("/home/yihau/input.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        root = createTree(root,scn);
    }
    //先序遍历创建二叉树，#代表空节点。节点为空代表此子树建立完毕//先序遍历的应用之一，首先创立根节点，然后递归创建左子树，右子树。
    private Node<T> createTree(Node<T> node,Scanner scn) {

        String temp  = scn.next();

        if (temp.trim().equals("#")) {
            return null;
        } else {
            node = new Node<T>((T)temp);
            node.setLeft(createTree(node.getLeft(), scn));
            node.setRight(createTree(node.getRight(), scn));
            return node;
        }

    }

    //复制二叉树，与copy函数一起使用，完成二叉树的复制过程，
    public Tree<T> copyTree(Node<T> node){
        Node<T> root = copy(node);
        return new Tree<>(root);
    }
    //二叉树前序遍历的应用，首先复制根节点，然后递归复制左子树，复制右子树。
    public Node<T> copy(Node<T> node){
        if (node == null)return null;
        Node<T> root = new Node<>(node.getValue());
        root.setLeft(copy(node.getLeft()));
        root.setRight(copy(node.getRight()));
        return root;
    }
    //二叉树前序遍历的应用，首先判断空树的情况，然后f1的判断相当于对根节点的操作，后面依次是遍历左子树，遍历右子树。
    public  boolean isEqual(Node<T> node1, Node<T> node2){
        if (node1 == null && node2 == null) return true;
        else if (node1 == null) return false;
        else if (node2 == null) return false;
        else{
            boolean f1 = node1.getValue().equals(node2.getValue());
            boolean f2 = isEqual(node1.getLeft(),node2.getLeft());
            boolean f3 = isEqual(node1.getRight(),node2.getRight());
            if(f1 && f2 && f3)return true;
            else return false;
        }
    }
    //二叉树前序遍历的应用，根节点为空的时候返回，输出根节点的值相当于对根节点的操作，后面依次是遍历左子树，遍历右子树。
    public void displayTree(Node<T> root){
        if (root == null)return;
        else {
            System.out.print(root.getValue());
            System.out.print("[");
            displayTree(root.getLeft());
            System.out.print(",");
            displayTree(root.getRight());
            System.out.print("]");
        }
    }

    public Node<T> find(Node<T> node, String a){
        if (node == null) return null;
        else {
            boolean f = node.getValue().equals(a);
            if (f)return node;
            Node<T> node1 =  find(node.getLeft(),a);
            Node<T> node2 =  find(node.getRight(),a);
            return node1 == null && node2 == null ? null : node1 == null ? node2 : node1;
        }
    }

    //统计二叉树的节点个数，后序遍历的应用，先左子树的size，然后右子树的size，最后加上根的个数1.
    public int size(Node<T> node){
        if (node == null) return 0;
        else{
            int leftSize = size(node.getLeft());
            int rightSize = size(node.getRight());
            return 1 + leftSize + rightSize;
            //简化版本return 1 + size(node.getLeft()) + size(node.getRight())
        }
    }
    //计算二叉树的高度/深度，后序遍历的应用，计算左右子树的depth，取其中较大者+1得depth
    public int depth(Node<T> node){
        if (node == null) return 0;
        else {
            int leftDepth = depth(node.getLeft());
            int rightDepth = depth(node.getRight());
            return (leftDepth > rightDepth) ? leftDepth + 1 : rightDepth +1;
        }
    }

    public Node<T> destory(Node<T> root){
        if (root == null) return root;
        else{
            destory(root.getLeft());
            destory(root.getRight());
            root = null;
            return root;
        }
    }

    //中序遍历(递归)
    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    public void inOrderTraverse(Node<T> node) {
        if (node != null) {
            inOrderTraverse(node.getLeft());
            System.out.println(node.getValue());
            inOrderTraverse(node.getRight());
        }
    }


    //中序遍历(非递归)
    public void nrInOrderTraverse() {

        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
            node = stack.pop();
            System.out.println(node.getValue());
            node = node.getRight();

        }

    }
    //先序遍历(递归)
    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    public void preOrderTraverse(Node<T> node) {
        if (node != null) {
            System.out.println(node.getValue());
            preOrderTraverse(node.getLeft());
            preOrderTraverse(node.getRight());
        }
    }


    //先序遍历（非递归）
    public void nrPreOrderTraverse() {

        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> node = root;

        stack.push(null);//
        while (node != null){
            System.out.println(node.getValue());
            if (node.getRight() != null)stack.push(node.getRight());
            if (node.getLeft() != null)node = node.getLeft();
            else node = stack.pop();
        }

//        while (node != null || !stack.isEmpty()) {
//
//            while (node != null) {
//                System.out.println(node.getValue());
//                stack.push(node);
//                node = node.getLeft();
//            }
//            node = stack.pop();
//            node = node.getRight();
//        }

    }

    //后序遍历(递归)
    public void postOrderTraverse() {
        postOrderTraverse(root);
    }

    public void postOrderTraverse(Node<T> node) {
        if (node != null) {
            postOrderTraverse(node.getLeft());
            postOrderTraverse(node.getRight());
            System.out.println(node.getValue());
        }
    }

    //后续遍历(非递归)
    public void nrPostOrderTraverse() {

        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> node = root;
        Node<T> preNode = null;//表示最近一次访问的节点

        while (node != null || !stack.isEmpty()) {

            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }

            node = stack.peek();

            if (node.getRight() == null || node.getRight() == preNode) {
                System.out.println(node.getValue());
                node = stack.pop();
                preNode = node;
                node = null;
            } else {
                node = node.getRight();
            }

        }




    }

    //按层次遍历
    public void levelTraverse() {
        levelTraverse(root);
    }
    //利用队列实现，访问本层节点时把下层节点加入队尾。
    public void levelTraverse(Node<T> node) {

        Queue<Node<T>> queue = new LinkedBlockingQueue<Node<T>>();
        queue.add(node);
        while (!queue.isEmpty()) {

            Node<T> temp = queue.poll();
            if (temp != null) {
                System.out.println(temp.getValue());//访问当前节点
                queue.add(temp.getLeft());//左子树入队
                queue.add(temp.getRight());//右子树入队
            }

        }

    }


    //根据二叉树的前序和中序序列来构建二叉树。
    public static Node<String> createBinaryTreeFromPreAndInOrder(String[] preOrder, String[] inOrder, int length){
        if (length == 0) return null;//序列为空时，到达叶子节点，返回null，递归的终止条件
        int i = 0, k = 0;//i记录前序中当前节点在数组中位置，k记录同value的节点在中序中的位置，
        while (!preOrder[i].equals(inOrder[k]))k++;
        Node<String> n = new Node<>(preOrder[i]);//建立当前节点
        String[] leftPreOrder = Arrays.copyOfRange(preOrder,i + 1,k + 1);//根据当前节点位置产生左子树的前序序列
        String[] leftInOrder = Arrays.copyOfRange(inOrder, i, k);//根据当前节点的位置产生左子树的中序序列
        n.setLeft(createBinaryTreeFromPreAndInOrder(leftPreOrder, leftInOrder, k));//递归创建左子树。
        String[] rightPreOrder = Arrays.copyOfRange(preOrder, k+1, length +1);//根据当前节点位置产生右子树的前序序列
        String[] rightInOrder = Arrays.copyOfRange(inOrder, k+1, length+1);//根据当前节点位置产生右子树的中序序列
        n.setRight(createBinaryTreeFromPreAndInOrder(rightPreOrder, rightInOrder, length - k - 1));//递归创建右子树
        return n;//返回当前创建的节点
    }


    public static void main(String[] args){
        Tree<Integer> tree = new Tree<Integer>();
        tree.buildTree();
        System.out.println("中序遍历");
//        tree.inOrderTraverse();
        tree.nrInOrderTraverse();
        System.out.println("后续遍历");
//        tree.nrPostOrderTraverse();
//        tree.postOrderTraverse();
        tree.nrPostOrderTraverse();
        System.out.println("先序遍历");
        tree.preOrderTraverse();
        tree.nrPreOrderTraverse();
        int i = tree.size(tree.root);
        System.out.println("节点个数:" + i);
        int j = tree.depth(tree.root);
        System.out.println("树的深度:" + j);
        Tree<Integer> tree1 = tree.copyTree(tree.root);
        tree1.inOrderTraverse();
        boolean f = tree.isEqual(tree.root, tree1.root);
        System.out.println("tree 和 tree1是否相同：" + f);
        System.out.print("print tree:");
        tree.displayTree(tree.root);
        System.out.println();
        Node<Integer> node  = tree1.destory(tree1.root);
        System.out.print("print tree1 has been destoried:");
        Tree<Integer> tree2 = new Tree<>(node);
        tree2.displayTree(tree2.root);
        System.out.println();
        Node<Integer> node1 =  tree.find(tree.root,"e");
        System.out.print(node1.toString());

        String[] strings1 = {"A","B","H","F","D","E","C","K","G"};
        String[] strings2 = {"H","B","D","F","A","E","K","C","G"};
        Node<String> node3 =  createBinaryTreeFromPreAndInOrder(strings1,strings2,9);
        Tree<String> tree3 = new Tree<>(node3);
        tree3.displayTree(tree3.root);
    }



}
