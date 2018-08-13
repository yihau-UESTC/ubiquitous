package DataStructure.bst;

import com.sun.org.apache.regexp.internal.RE;

public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public Node getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean insert(E e, Node root) {
        if (root == null) {
            root = new Node(e);
            return true;
        } else if (e.compareTo(root.e) < 0)
            insert(e, root.left);
        else if (e.compareTo(root.e) > 0)
            insert(e, root.right);
        else
            return false;
        return false;
    }


    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }


    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.insert(3, bst.getRoot());
        bst.insert(5, bst.getRoot());
        bst.insert(1, bst.getRoot());
        bst.insert(7, bst.getRoot());
        System.out.println(bst);
    }
}
