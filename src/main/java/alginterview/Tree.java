package alginterview;

import org.junit.Test;
import scala.tools.nsc.backend.icode.Members;

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


    public int kthSmallest(TreeNode root, int k) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()) {
                TreeNode pop = stack.pop();
                k--;
                if (k <= 0) return pop.val;
                node = pop.right;
            }
        }
        throw new IllegalArgumentException();
    }

    private TreeNode par;
    private boolean pf;
    private boolean qf;


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //递归终止条件
        if (root == null || root.val == p.val || root.val == q.val) return root;
        //在左子树中搜索p和q
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        //在右子树中搜索p和q
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null && right == null) return null;
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        int min = Integer.MAX_VALUE;
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()) {
                TreeNode pop = stack.pop();
                if (last != null) {
                    min = Math.min(pop.val - last.val, min);
                }
                last = pop;
                node = pop.right;
            }
        }
        return min;
    }

    private int min = Integer.MAX_VALUE;
    private TreeNode pre = null;

    public int getMinimumDifferenceByRe(TreeNode root) {
        int min = Integer.MAX_VALUE;
        if (root == null) return min;
        int lmin = getMinimumDifferenceByRe(root.left);
        if (pre != null) {
            min = Math.min(lmin, root.val - pre.val);
        }
        pre = root;
        int rmin = getMinimumDifferenceByRe(root.right);
        return Math.min(min, rmin);
    }

    @Test
    public void run11() {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        int i = getMinimumDifferenceByRe(t1);
        System.out.println(i);
    }

    private List<String> res = new ArrayList<>();
    private String[] lettersMap = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits == "") return res;
        find(digits, 0, "");
        return res;
    }

    private void find(String digits, int index, String cur) {
        if (index == digits.length()) {
            res.add(cur);
            return;
        }
        char c = digits.charAt(index);
        String letter = lettersMap[c - '2'];
        for (int i = 0; i < letter.length(); i++) {
            find(digits, index + 1, cur + letter.charAt(i));
        }
    }

    @Test
    public void run12() {
        List<String> list = letterCombinations("");
        System.out.println(list.toString());
    }

    private List<String> ipres = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.equals("")) return ipres;
        findIp(s, 0, 0, "");
        return ipres;
    }

    private void findIp(String s, int index, int level, String cur) {
        if (level == 4) {
            if (index == s.length()) {
                ipres.add(cur.substring(0, cur.length() - 1));
            }
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (index + i <= s.length()) {
                String temp = s.substring(index, index + i);
                if (temp.equals("0") || temp.charAt(0) != '0') {
                    int num = Integer.valueOf(temp);
                    if (num < 256 && num > -1) {
                        findIp(s, index + i, level + 1, cur + temp + ".");
                    }
                }
            }
        }
    }

    @Test
    public void run13() {
        List<String> list = restoreIpAddresses("010010");
        System.out.println(ipres.toString());
    }

    private List<List<String>> palindromRes = new ArrayList<>();

    public List<List<String>> partition(String s) {
        findPalindrome(s, 0, new ArrayList<>());
        return palindromRes;
    }

    private void findPalindrome(String s, int index, List<String> list) {
        if (index == s.length()) {
            palindromRes.add(new ArrayList<>(list));
            return;
        }
        for (int i = 1; i <= s.length() - index; i++) {
            String cur = s.substring(index, index + i);
            if (isPalindrome(cur)) {
                list.add(cur);
                findPalindrome(s, index + i, list);
                //当前选择的cur的所有可能性已经遍历结束，需要删掉。不能放在if的外面，可能有时候并没有添加所以不需要删除
//                 而且也不需要判断remove的时候list的size，因为上面刚刚add一个。
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        char[] chars = str.toCharArray();
        int l = 0, r = chars.length - 1;
        while (l < r) {
            if (chars[l] != chars[r]) return false;
            l++;
            r--;
        }
        return true;
    }

    @Test
    public void run14() {
        List<List<String>> aab = partition("abbab");
        System.out.println(aab.toString());
    }

    private List<List<Integer>> permuteRes = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return permuteRes;
        used = new boolean[nums.length];
        findPermute(nums, 0, new ArrayList<>());
        return permuteRes;
    }

    private void findPermute(int[] nums, int index, List<Integer> list) {
        if (index == nums.length) {
            permuteRes.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //这里只有list中未使用过的nums才可以添加，使用一个used辅助数组来标志某个nums是否已经使用过。
            if (!used[i]) {
                list.add(nums[i]);
                used[i] = true;
                findPermute(nums, index + 1, list);
                //要注意，当前的遍历完后需要恢复之前的状态。
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }

    private List<List<Integer>> permuteRes2 = new ArrayList<>();
    private boolean[] used2;

    public List<List<Integer>> permute2(int[] nums) {
        if (nums.length == 0) return permuteRes2;
        used2 = new boolean[nums.length];
        Arrays.sort(nums);
        findPermute2(nums, 0, new ArrayList<>());
        return permuteRes2;
    }

    private void findPermute2(int[] nums, int index, List<Integer> list) {
        if (index == nums.length) {
            permuteRes2.add(new ArrayList<>(list));
            return;
        }
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //前提是我对数组进行了排序
            //要在广度上禁止重复元素，但是深度上不用，所以这里设置一个局部变量pre表示上一个遍历的元素
            //在i+1之后如果发现当前nums和pre相同则跳过，pre的赋值在当前可能性遍历完之后执行。
            //这里不能是if(!used2[i] && (i == 0 || nums[i] != nums[i - 1])),这样会在深度上也排除掉某些元素
            if (!used2[i] && (i == 0 || nums[i] != pre)) {
                list.add(nums[i]);
                used2[i] = true;
                findPermute2(nums, index + 1, list);
                //要注意，当前的遍历完后需要恢复之前的状态。
                list.remove(list.size() - 1);
                used2[i] = false;
                pre = nums[i];
            }
        }
    }

    @Test
    public void run15() {
        int[] nums = {1, 1, 2};
        List<List<Integer>> lists = permute2(nums);
        System.out.println(lists.toString());
    }

    private List<List<Integer>> combineRes;

    public List<List<Integer>> combine(int n, int k) {
        findCombine(n, k, 1, new ArrayList<>());
        return combineRes;
    }

    //组合问题优化，剪枝！！！
    private void findCombine(int n, int k, int start, List<Integer> list) {
        if (list.size() == k) {
            combineRes.add(new ArrayList<>(list));
            return;
        }
//        for (int i = start; i <= n; i++){遍历到某个i的时候可能剩下的元素根本不够组成一个list，所以可以提前结束
        for (int i = start; i <= n - (k - list.size()) + 1; i++) {
            list.add(i);
            findCombine(n, k, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    private List<List<Integer>> res39 = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        findCombinationSum(candidates, 0, target, new ArrayList<>());
        return res39;
    }

    private void findCombinationSum(int[] candidates, int start, int remaining, List<Integer> list) {
        if (remaining == 0) {
            System.out.println("result : " + list.toString());
            Arrays.sort(candidates);
            res39.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            remaining -= candidates[i];
            if (remaining < 0) continue;
            list.add(candidates[i]);
            System.out.println("add : " + candidates[i]);
            findCombinationSum(candidates, i, remaining, list);
            System.out.println("remove : " + list.get(list.size() - 1));
            remaining += candidates[i];
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void run16() {
        int[] arr = {2, 3, 6, 7};
        List<List<Integer>> lists = combinationSum(arr, 7);
        System.out.println(lists.toString());
    }

    private List<List<Integer>> res40 = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        findCombinationSum2(candidates, 0, target, new ArrayList<>());
        return res40;
    }

    private void findCombinationSum2(int[] candidates, int start, int remaining, List<Integer> list) {
        if (remaining == 0) {
            res40.add(new ArrayList<>(list));
            return;
        }
        int pre = Integer.MAX_VALUE;
        for (int i = start; i < candidates.length; i++) {
            if (i == 0 || candidates[i] != pre) {
                remaining -= candidates[i];
                if (remaining < 0) continue;
                list.add(candidates[i]);
                findCombinationSum2(candidates, i + 1, remaining, list);
                list.remove(list.size() - 1);
                remaining += candidates[i];
                pre = candidates[i];
            }
        }
    }

    @Test
    public void run17() {
        int[] arr = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> lists = combinationSum2(arr, 8);
        System.out.println(lists.toString());
    }


    private List<List<Integer>> res216 = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        findCombinationSum3(k, 1, n, new ArrayList<>());
        return res216;
    }

    private void findCombinationSum3(int k, int start, int remaining, List<Integer> list) {
        if (list.size() == k && remaining == 0) {
            res216.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= 9 - (k - list.size()) + 1; i++) {
            remaining -= i;
            if (remaining < 0) continue;
            list.add(i);
            findCombinationSum3(k, i + 1, remaining, list);
            list.remove(list.size() - 1);
            remaining += i;
        }
    }

    @Test
    public void run18() {
        List<List<Integer>> lists = combinationSum3(3, 9);
        System.out.println(lists.toString());
    }

    private List<List<Integer>> res78 = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            findSubsets(nums, i, 0, new ArrayList<>());
        }
        return res78;
    }

    private void findSubsets(int[] nums, int num, int start, List<Integer> list) {
        if (list.size() == num) {
            res78.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length - (num - list.size()) + 1; i++) {
            list.add(nums[i]);
            findSubsets(nums, num, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void run19() {
        int[] arr = {1, 2, 3};
        List<List<Integer>> subsets = subsets(arr);
        System.out.println(subsets.toString());
    }

    private List<List<Integer>> res90 = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length; i++) {
            findSubsets2(nums, i, 0, new ArrayList<>());
        }
        return res90;
    }

    private void findSubsets2(int[] nums, int num, int start, List<Integer> list) {
        if (list.size() == num) {
            res90.add(new ArrayList<>(list));
            return;
        }
        int pre = Integer.MAX_VALUE;
        for (int i = start; i < nums.length - (num - list.size()) + 1; i++) {
            if (i == start || nums[i] != pre) {
                list.add(nums[i]);
                findSubsets2(nums, num, i + 1, list);
                list.remove(list.size() - 1);
                pre = nums[i];
            }
        }
    }

    @Test
    public void run20() {
        int[] arr = {1, 2, 2};
        List<List<Integer>> subsets = subsetsWithDup(arr);
        System.out.println(subsets.toString());
        System.out.println(Integer.valueOf("08"));
    }

    private List<String> res401 = new ArrayList<>();
    private int[] timeTable = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
    private boolean[] unitMap = {true, true, true, true, false, false, false, false, false, false};

    public List<String> readBinaryWatch(int num) {
        findCombine4(num, 0, new ArrayList<>());
        return res401;
    }

    private void findCombine4(int num, int start, List<Integer> list) {
        if (list.size() == num) {
            int hour = 0, minute = 0;
            for (int i = 0; i < num; i++) {
                int index = list.get(i);
                if (unitMap[index]) hour += timeTable[index];
                else minute += timeTable[index];
            }
            if (hour < 12 && minute < 60) {
                String res = hour + ":";
                if (minute < 10) res += "0";
                res += minute;
                res401.add(res);
            }
            return;
        }
        for (int i = start; i < 10; i++) {
            list.add(i);
            findCombine4(num, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void run21() {
        List<String> list = readBinaryWatch(2);
        System.out.println(list.toString());
    }

    /*79--------------------------------------------------------------*/
    //在二维平面上上右下左移动的坐标变换
    private int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m <= 0) return false;
        int n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (serachWord(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean serachWord(char[][] board, String word, int index, int x, int y) {
        //如果当前搜索到最后一位，返回是否匹配
        if (index == word.length() - 1) {
            return board[x][y] == word.charAt(index);
        }
        //其他情况需要继续递归搜索,先判断当前位置是否匹配，匹配继续向下搜索，否则直接返回
        if (board[x][y] == word.charAt(index)) {
            //记录当前位置
            visited[x][y] = true;
            //沿当前位置向上右下左搜索
            for (int i = 0; i < 4; i++) {
                int newX = x + move[i][0];
                int newY = y + move[i][1];
                //判断新的坐标是否越界，新的坐标是否被访问过了，然后进入新的坐标判断，如果返回true，则函数返回true
                //如果四个方向都搜索完毕没有true则退出当前节点visited重置。
                if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
                        && !visited[newX][newY]
                        && serachWord(board, word, index + 1, newX, newY))
                    return true;
            }
            visited[x][y] = false;
        }
        return false;
    }

    @Test
    public void run22() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        boolean abcced = exist(board, "SEE");
        System.out.println(abcced);
    }

    /*200--------------------------------------------------------------*/
    private boolean[][] visited200;
    private int res200;

    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m <= 0) return 0;
        int n = grid[0].length;
        visited200 = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited200[i][j]) {
                    res200++;
                    floodfill(grid, i, j);
                }
            }
        }
        return res200;
    }

    private void floodfill(char[][] grid, int x, int y) {
        visited200[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + move[i][0];
            int newY = y + move[i][1];
            //递归结束的条件包含在这里面，只有符合这些条件的才向下递归，同时不符合的就递归结束
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                    && grid[newX][newY] == '1'
                    && !visited200[newX][newY]) {
                floodfill(grid, newX, newY);
            }
        }
    }

    @Test
    public void run23() {
        char[][] chars = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };
        System.out.println(numIslands(chars));
    }
    /*130------------------------------------------------------------
     * 先对边界上的O进行洪泛，将其洪泛区域标记为*，然后遍历board，对*标记的修改为O，其他O修改为X
     * */

    private boolean[][] visited130;

    public void solve(char[][] board) {
        int m = board.length;
        if (m <= 0) return;
        int n = board[0].length;
        visited130 = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && !visited130[i][j]) {
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                        dfs(board, i, j);
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '*') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        visited130[x][y] = true;
        board[x][y] = '*';
        for (int i = 0; i < 4; i++) {
            int newX = x + move[i][0];
            int newY = y + move[i][1];
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
                    && board[newX][newY] == 'O'
                    && !visited130[newX][newY]) {
                dfs(board, newX, newY);
            }
        }
    }

    @Test
    public void run24() {
        char[][] chars = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        solve(chars);
        System.out.println();
    }

    private List<List<String>> res51 = new ArrayList<>();
    /*判断列、对角线1，对角线2上是否有皇后
      对角线1--》i+j,对角线2--》i-j + n - 1
    */
    private boolean[] col, dig1, dig2;

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return res51;
        col = new boolean[n];
        dig1 = new boolean[2 * n - 1];
        dig2 = new boolean[2 * n - 1];
        putQueens(n, 0, new ArrayList<>());
        return res51;
    }

    private void putQueens(int n, int x, List<Integer> list) {
        if (x == n) {
            List<String> sol = generateResult(list);
            res51.add(new ArrayList<>(sol));
        }
        for (int y = 0; y < n; y++) {
            if (!col[y] && !dig1[x + y] && !dig2[x - y + n - 1]) {
                list.add(y);
                col[y] = true;
                dig1[x + y] = true;
                dig2[x - y + n - 1] = true;
                putQueens(n, x + 1, list);
                dig2[x - y + n - 1] = false;
                dig1[x + y] = false;
                col[y] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    private List<String> generateResult(List<Integer> list) {
        List<String> res = new ArrayList<>();
        int n = list.size();
        for (int index : list) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i == index) sb.append("Q");
                else sb.append(".");
            }
            res.add(sb.toString());
        }
        return res;
    }

    @Test
    public void run25() {
        List<List<String>> lists = solveNQueens(4);
        System.out.println(lists.toString());
    }

    /**
     * n皇后问题优化，使用一维数组来存储判断能否放置皇后
     * 使用给一个n个元素的一维数组Q[N]，每个元素代表在每行上放置皇后的列的位置，直接不存在行冲突
     * 列冲突可以在放置的时候遍历一维数组来判断是否有相应的列存在Q[i],对角线冲突的两个位置是|x1 - x2| = |y1 - y2|
     */
    private int[] Q;

    public List<List<String>> solveNQueens2(int n) {
        if (n <= 0) return res51;
        Q = new int[n];
        for (int i = 0; i < n; i++) {
            Q[i] = Integer.MIN_VALUE;
        }
        putQueens2(n, 0, new ArrayList<>());
        return res51;
    }

    private void putQueens2(int n, int x, List<Integer> list) {
        if (x == n) {
            List<String> sol = generateResult(list);
            res51.add(new ArrayList<>(sol));
        }
        for (int y = 0; y < n; y++) {
            if (isValidPos(x, y, n)) {
                list.add(y);
                Q[x] = y;
                putQueens2(n, x + 1, list);
                Q[x] = Integer.MIN_VALUE;
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isValidPos(int x, int y, int n) {
        for (int i = 0; i < n; i++) {
            if (Q[i] == y || Math.abs(i - x) == Math.abs(Q[i] - y)) return false;
        }
        return true;
    }

    @Test
    public void run26() {
        List<List<String>> lists = solveNQueens2(4);
        System.out.println(lists.toString());
    }

    @Test
    public void run27() {
        int i = solveQueenByBit(4);
        System.out.println(sum);
    }

    private int sum = 0;

    public int solveQueenByBit(int n) {
        int uplimit = (1 << n) - 1;
        putQueensByBit(uplimit, 0, 0, 0);
        return sum;
    }

    private void putQueensByBit(int uplimit, int row, int ld, int rd) {
        if (row != uplimit) {
            int pos = uplimit & ~(row | ld | rd);
            while (pos != 0) {
                int p = pos & (~pos + 1);

                pos -= p;

                putQueensByBit(uplimit, row + p, (ld + p) << 1, (rd + p) >> 1);
            }
        } else sum++;
    }




}
