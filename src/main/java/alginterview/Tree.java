package alginterview;

import org.junit.Test;
import org.w3c.dom.ls.LSInput;

import java.util.*;

public class Tree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 二叉树的深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    /**
     * 二叉树的最小深度，注意是到叶节点的最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    /**
     * 二叉树翻转
     * 二叉树深度优先遍历的应用，注意，这里第一次递归调用会改变root.left的值，所以要先用两个节点记录下来原来的子树信息，不然会丢失
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        root.left = invertTree(root.right);
        root.right = invertTree(root.left);
        return root;
    }

    /**
     * 判断两颗树是否相同
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        boolean pb = isSameTree(p.left, q.left);
        boolean qb = isSameTree(p.right, q.right);
        return p.val == q.val && pb && qb;
    }

    /**
     * 判断二叉树自身是否左右对称，
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        TreeNode left = root.left;
        TreeNode right = root.right;
        return init(left, right);
    }

    /**
     * @param left
     * @param right
     * @return
     * @see
     */
    private boolean init(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        boolean b1 = init(left.left, right.right);
        boolean b2 = init(left.right, right.left);
        return left.val == right.val && b1 && b2;
    }


    @Test
    public void run2() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
//        TreeNode t5 = new TreeNode(4);
//        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
//        t2.right = t5;
//        t3.left = t6;
        t3.left = t7;
        boolean symmetric = isSymmetric(t1);
        System.out.println(symmetric);
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftNum = 0;//节点个数
        int rightNum = 0;
        //求以当前节点为根的树的高度
        TreeNode node = root;
        int level = 0;
        while (node != null) {
            node = node.left;
            level++;
        }
        //求当前节点右子树的高度
        int rightLevel = 0;
        node = root.right;
        while (node != null) {
            node = node.left;
            rightLevel++;
        }
        //根据右子树是否为满二叉树来分别处理
        if (rightLevel == level - 2) {
            rightNum = 1 << rightLevel - 1;
            leftNum = countNodes(root.left);
        } else {
            leftNum = 1 << rightLevel - 1;
            rightNum = countNodes(root.right);
        }
        return leftNum + rightNum + 1;
    }

    @Test
    public void run3() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(4);
//        TreeNode t7 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
//        t3.left = t7;
        int i = countNodes(t1);
        System.out.println(i);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = getHight(root.left);
        int right = getHight(root.right);
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(left - right) <= 1;
    }

    private int getHight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHight(root.left), getHight(root.right)) + 1;
    }

    @Test
    public void run4() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(4);
//        TreeNode t7 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
//        t3.left = t7;
        boolean balanced = isBalanced(t1);
        System.out.println(balanced);
    }

    /**
     * 叶子节点：左右子树均为空。
     * 我们在树中递归的终止条件一般是node==null；但是这并不总是成立，
     * 比如这题要求的是找到根节点到叶子节点的路径，如果我们按照node==null来判断，会有的路径不是根到叶子节点的
     * 5
     * \
     * 9
     * 比如这里5向左子树搜索，但是左子树并没有根到叶子节点的路径。
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        //给的空树，不存在根节点到叶子节点的路径，直接return false
        if (root == null) return false;
        //判断当前节点是否为叶子节点，如果是叶子节点，判断是否符合sum要求
        if (root.left == null && root.right == null)
            return root.val == sum;
        //当前节点不是叶子节点，递归其左右子树。
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);

    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    @Test
    public void run6() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        t3.left = t5;
        t3.right = t6;
        t5.right = t7;
        t6.left = t8;
        int sum = sumOfLeftLeaves(t1);
        System.out.println(sum);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }
        List<String> left = binaryTreePaths(root.left);
        for (int i = 0; i < left.size(); i++) {
            res.add(root.val + "->" + left.get(i));
        }
        List<String> right = binaryTreePaths(root.right);
        for (int i = 0; i < right.size(); i++) {
            res.add(root.val + "->" + right.get(i));
        }
        return res;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        //递归终止条件
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                List<Integer> list = new ArrayList<>();
                list.add(root.val);
                res.add(list);
            }
            return res;
        }

        List<List<Integer>> left = pathSum(root.left, sum - root.val);
        for (int i = 0; i < left.size(); i++) {
            List<Integer> list = left.get(i);
            list.add(0, root.val);
            res.add(list);
        }

        List<List<Integer>> right = pathSum(root.right, sum - root.val);
        for (int i = 0; i < right.size(); i++) {
            List<Integer> list = right.get(i);
            list.add(0, root.val);
            res.add(list);
        }
        return res;
    }

    public int sumNumbers(TreeNode root) {
        List<String> list = allPath(root);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += Integer.valueOf(list.get(i));
        }
        return sum;
    }

    public List<String> allPath(TreeNode root) {
        List<String> res = new ArrayList<>();
        //递归终止条件
        if (root == null) return res;
        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }
        List<String> left = allPath(root.left);
        for (int i = 0; i < left.size(); i++) {
            String s = left.get(i);
            res.add(root.val + s);
        }
        List<String> right = allPath(root.right);
        for (int i = 0; i < right.size(); i++) {
            String s = right.get(i);
            res.add(root.val + s);
        }
        return res;
    }

    @Test
    public void run7() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        int i = sumNumbers(t1);
        System.out.println(i);
        System.out.println(Long.valueOf("58332768314401"));
    }

    public int sumNumbers2(TreeNode root) {
        return sumNumbers(root, 0);
    }

    public int sumNumbers(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return sumNumbers(root.left, sum) + sumNumbers(root.right, sum);
    }

    public int pathSum3(TreeNode root, int sum) {
        if (root == null) return 0;
        int res = findPath(root, sum);
        res += pathSum3(root.left, sum);
        res += pathSum3(root.right, sum);
        return res;
    }

    private int findPath(TreeNode root, int sum) {
        if (root == null) return 0;
        int res = 0;
        if (root.val == sum) {
            res += 1;
        }
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val) return p;
        if (root.val == q.val) return q;
        if (root.val < p.val && root.val > q.val) return root;
        if (root.val > p.val && root.val < q.val) return root;

        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        throw new IllegalArgumentException();
    }

    public boolean isValidBST(TreeNode root) {
        return last(root, null);
    }

    /**
     * 中序遍历的变形
     *
     * @param node
     * @param pre
     * @return
     */
    private boolean last(TreeNode node, TreeNode pre) {
        if (node == null) return true;
        //走到最左边
        if (!last(node.left, pre)) return false;
        //判断出不符合条件的返回false
        if (pre != null && pre.val >= node.val) return false;
        pre = node;
        if (!last(node.right, pre)) return false;
        return true;
    }

    @Test
    public void run8() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(1);
        t1.left = t2;
        boolean validBST = isValidBST(t1);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode par = new TreeNode(-1);
        //在根节点上加一个节点，使得删除是root的时候可以统一处理。
        par.left = root;
        initDelete(root, root, par, key);
        return par.left;
    }

    public void initDelete(TreeNode root, TreeNode cur, TreeNode par, int key) {
        if (cur == null) return;
        //找到key对应的节点
        if (key == cur.val) {
            //分为当前节点为父节点的左子树还是右子树，
            if (cur == par.left) {
                //1、如果当前节点的左右子树都为空，直接删除
                if (cur.left == null && cur.right == null) {
                    par.left = null;
                    //2、如果当前节点只有一个子树，直接使用子树覆盖当前节点
                } else if (cur.right == null) {
                    par.left = cur.left;
                } else if (cur.left == null) {
                    par.left = cur.right;
                    //3、当前节点有两个子树，首先寻找当前节点在BST中的后继，这时分成两种情况
                } else {
                    TreeNode succ = null;
                    //3.1后继节点一定在右子树中，如果是当前节点的右孩子，直接用右孩子替换掉当前节点
                    if (cur.right.left == null) {
                        succ = cur.right;
                        par.left = succ;
                        succ.left = cur.left;
                        //3.2后继节点不是右孩子，那么一定在右孩子的左子树中最左的节点，这时
                    } else {
                        TreeNode pary = cur.right;
                        succ = pary.left;
                        while (succ.left != null) {
                            pary = succ;
                            succ = pary.left;
                        }
                        //首先用后继节点的右孩子替换自身
                        pary.left = succ.right;
                        //然后用后继节点替换当前节点
                        par.left = succ;
                        succ.right = cur.right;
                        succ.left = cur.left;
                    }
                }
            } else {
                //分为当前节点为父节点右子树，与上面的对称
                if (cur.left == null && cur.right == null) {
                    par.right = null;
                } else if (cur.right == null) {
                    par.right = cur.left;
                } else if (cur.left == null) {
                    par.right = cur.right;
                } else {
                    TreeNode succ = null;
                    if (cur.right.left == null) {
                        succ = cur.right;
                        par.right = succ;
                        succ.left = cur.left;
                    } else {
                        TreeNode pary = cur.right;
                        succ = pary.left;
                        while (succ.left != null) {
                            pary = succ;
                            succ = pary.left;
                        }
                        pary.left = succ.right;
                        par.right = succ;
                        succ.right = cur.right;
                        succ.left = cur.left;
                    }
                }
            }
            return;
        } else if (key < cur.val) {
            initDelete(root, cur.left, cur, key);
        } else {
            initDelete(root, cur.right, cur, key);
        }
    }

    @Test
    public void run9() {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;
        TreeNode treeNode = deleteNode(t1, 0);
        System.out.println(treeNode);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)return null;
        int n = nums.length;
        return split(nums, 0, n - 1);
    }

    private TreeNode split(int[] nums, int low, int high){
        if (low < high){
            int mid = (low + high) / 2;
            TreeNode cur = new TreeNode(nums[mid]);
            cur.left = split(nums, low, mid - 1);
            cur.right = split(nums, mid + 1, high);
            return cur;
        }else if (low == high){
            return new TreeNode(nums[low]);//LOW == HIGH
        }else {
            return null;
        }

    }

    @Test
    public void run10(){
        int[] arr = {-10,-3,0,5,9};
        TreeNode node = sortedArrayToBST(arr);
        System.out.println(node);
    }


}
