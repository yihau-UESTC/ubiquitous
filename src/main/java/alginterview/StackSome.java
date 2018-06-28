package alginterview;

import org.junit.Test;

import java.util.*;

public class StackSome {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' || chars[i] == '[' || chars[i] == '(') {
                stack.push(chars[i]);
            } else {
                if (!stack.empty()) {
                    char c = stack.pop();
                    char match = '!';
                    if (chars[i] == '}') match = '{';
                    else if (chars[i] == ']') match = '[';
                    else match = '(';
                    if (match != c) return false;
                } else return false;
            }
        }
        if (!stack.empty()) return false;
        return true;
    }

    @Test
    public void run() {
        String s = "{[]}";
        boolean valid = isValid(s);
        System.out.println(valid);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+") || tokens[i].equals("-")
                    || tokens[i].equals("*") || tokens[i].equals("/")) {
                int o1 = stack.pop();
                int o2 = stack.pop();
                int val = 0;
                switch (tokens[i]) {
                    case "+":
                        val = o2 + o1;
                        break;
                    case "-":
                        val = o2 - o1;
                        break;
                    case "*":
                        val = o2 * o1;
                        break;
                    case "/":
                        val = o2 / o1;
                        break;
                }
                stack.push(val);
            } else {
                int val = Integer.valueOf(tokens[i]);
                stack.push(val);
            }
        }
        return stack.pop();
    }

    @Test
    public void run1() {
        String[] strings = {"2", "1", "+", "3", "*"};
        int i = evalRPN(strings);
        System.out.println(i);
    }

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] dirs = path.split("/");
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i].equals(".") || dirs[i].equals("")) continue;
            else if (dirs[i].equals("..")) {
                if (!stack.empty()) stack.pop();
            } else {
                stack.push(dirs[i]);
            }
        }
        if (stack.empty()) return "/";
        String res = "";
        while (!stack.empty()) {
            String str = stack.pop();
            res = "/" + str + res;
        }
        return res;
    }

    @Test
    public void run2() {
        String path = "/";
        String s = simplifyPath(path);
        System.out.println(s);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rePreorder(root, res);
        return res;
    }

    private void rePreorder(TreeNode node, List<Integer> res) {
        if (node != null) {
            res.add(node.val);
            rePreorder(node.left, res);
            rePreorder(node.right, res);
        }
    }

    public class Command {
        String command;
        TreeNode node;

        Command(String command, TreeNode node) {
            this.command = command;
            this.node = node;
        }
    }

    public List<Integer> systemStackPreorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        TreeNode node = null;
        while (!stack.empty()) {
            Command command = stack.pop();
            if ((node = command.node) != null) {
                if (command.command.equals("print")) {
                    res.add(node.val);
                } else {
                    if (node.right != null) stack.add(new Command("go", node.right));
                    if (node.left != null) stack.add(new Command("go", node.left));
                    stack.add(new Command("print", node));
                }
            }
        }
        return res;
    }

    public List<Integer> StackPreorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                res.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        reInorder(root, res);
        return res;
    }

    private void reInorder(TreeNode node, List<Integer> res) {
        if (node != null) {
            reInorder(node.left, res);
            res.add(node.val);
            reInorder(node.right, res);
        }
    }

    public List<Integer> systemStackInorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        TreeNode node = null;
        while (!stack.empty()) {
            Command command = stack.pop();
            if ((node = command.node) != null) {
                if (command.command.equals("print")) {
                    res.add(node.val);
                } else {
                    if (node.right != null) stack.add(new Command("go", node.right));
                    stack.add(new Command("print", node));
                    if (node.left != null) stack.add(new Command("go", node.left));

                }
            }
        }
        return res;
    }

    /**
     * 如果节点有左子树->进栈，直到左子树为null，然后访问当前节点，访问过之后添加节点的右孩子到栈中，
     * 同样右孩子也可能有左子树，重复上面的操作。最后当前节点和栈中的元素都没了之后结束
     *
     * @param root
     * @return
     */
    public List<Integer> StackInorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()) {
                TreeNode node1 = stack.pop();
                res.add(node1.val);
                node = node1.right;
            }
        }
        return res;
    }

    /**
     * 碰到一个节点首先将其左子树上的节点全部压入栈，然后判断当前节点有没有右子树
     * 有的话将其右子树当做当前节点，继续压入左子树，然后，首先判断右子树是否访问过了
     * 如果访问过了就可以访问当前节点，没有访问过需要先处理其右子树，所以这里需要一个节点来
     * 标记上一个访问的节点。跟中序的类似
     *
     * @param root
     * @return
     */
    public List<Integer> StackPostorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root, last = null;
        while (node != null || !stack.empty()) {
            //先进到左子树的底
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            //访问当前节点的时候首先看右子树是否存在，或者是否被访问过了
            if (node.right != null && last != node.right) {
                //如果右子树没有被访问，则将当前节点定位到右子树
                node = node.right;
            } else {
                //右子树已经被访问过了或者没有右子树，此时可以放心的访问当前节点
                stack.pop();
                res.add(node.val);
                last = node;
                node = null;//现在的部分二叉树访问完了，需要弹出栈中的上层元素。

            }
        }
        return res;
    }

    @Test
    public void run4() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        List<Integer> integers = StackPostorder(t1);
        System.out.println(integers);
    }

    public class NestedInteger {
        Object data;

        public NestedInteger(Object data) {
            this.data = data;
        }

        public boolean isInteger() {
            return data instanceof Integer;
        }

        public Integer getInteger() {
            return (Integer) data;
        }

        public List<NestedInteger> getList() {
            return (List<NestedInteger>) data;
        }
    }


    public class NestedIterator implements Iterator<Integer> {

        private Stack<ListIterator<NestedInteger>> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            stack.push(nestedList.listIterator());
        }

        @Override
        public Integer next() {
            hasNext();
            return stack.peek().next().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.empty()) {
                if (!stack.peek().hasNext()) {
                    stack.pop();
                } else {
                    NestedInteger x = stack.peek().next();
                    if (x.isInteger())
                        //会将指针回置
                        return stack.peek().previous() == x;
                    stack.push(x.getList().listIterator());
                }
            }
            return false;
        }
    }

    @Test
    public void run5() {
        List<NestedInteger> list = new ArrayList<>();
        List<NestedInteger> list1 = new ArrayList<>();
        list.add(new NestedInteger(list1));
        list1.add(new NestedInteger(1));
        list1.add(new NestedInteger(2));
        list.add(new NestedInteger(list1));
        list.add(new NestedInteger(3));
        List<NestedInteger> list2 = new ArrayList<>();
        list2.add(new NestedInteger(4));
        list2.add(new NestedInteger(5));
        list.add(new NestedInteger(list2));
        NestedIterator nestedIterator = new NestedIterator(list);
        while (nestedIterator.hasNext()) {
            int next = nestedIterator.next();
            System.out.println(next);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode anchor = new TreeNode(-1);
        queue.add(root);
        queue.add(anchor);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != anchor) {
                if (node != null) {
                    list.add(node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            } else {
                res.add(new ArrayList<>(list));
                list.clear();
                if (!queue.isEmpty()) {
                    queue.add(anchor);
                }
            }
        }
        return res;
    }

    class Pair {
        TreeNode node;
        int level;

        public Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Pair> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair peek = queue.poll();
            TreeNode node = peek.node;
            int level = peek.level;
            if (res.size() == level) res.add(new ArrayList<>());
            //隔层翻转
            if (level % 2 == 0) {
                while (!stack.empty()) {
                    Integer pop = stack.pop();
                    res.get(level - 1).add(pop);
                }
                res.get(level).add(node.val);
            } else {
                stack.push(node.val);
            }
            if (node.left != null) queue.add(new Pair(node.left, level + 1));
            if (node.right != null) queue.add(new Pair(node.right, level + 1));
        }
        while (!stack.empty()) {
            Integer pop = stack.pop();
            res.get(res.size() - 1).add(pop);
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean zigzag = false;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                if (zigzag) {
                    level.add(0, node.val);
                } else {
                    level.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
            zigzag = !zigzag;
        }
        return res;

    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) return result;
        Stack<TreeNode> left = new Stack();
        Stack<TreeNode> right = new Stack();
        ArrayList<Integer> list = new ArrayList<>();
        left.push(pRoot);
        while (!left.empty() || !right.empty()) {
            //访问左栈，子树按先左后右进入右栈
            if (!left.empty()) {
                while (!left.empty()) {
                    TreeNode current = left.pop();
                    list.add(current.val);
                    if (current.left != null) right.push(current.left);
                    if (current.right != null) right.push(current.right);
                }
                result.add(new ArrayList(list));
                list.clear();
            }
            //访问右栈，子树按先右后左进入左栈
            if (!right.empty()) {
                while (!right.empty()) {
                    TreeNode current = right.pop();
                    list.add(current.val);
                    if (current.right != null) left.push(current.right);
                    if (current.left != null) left.push(current.left);
                }
                result.add(new ArrayList(list));
                list.clear();
            }
        }
        return result;
    }

    @Test
    public void run6() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t4 = new TreeNode(4);
//        TreeNode t5 = new TreeNode(5);
//        TreeNode t6 = new TreeNode(6);
//        TreeNode t7 = new TreeNode(7);
        t1.left = t2;
//        t1.right = t3;
//        t2.left = t4;
//        t2.right = t5;
//        t3.left = t6;
//        t3.right = t7;
        List<List<Integer>> lists = levelOrderBottom(t1);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (i == n - 1) res.add(node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }
        }
        return res;
    }

    /**
     * 整个代码与图的BFS算法很像，使用队列来存储，visited数组来控制访问过的节点
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int level = 0, size = 0, remain = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(n);
        visited[n] = true;
        while (!queue.isEmpty()) {
            size = queue.size();
            //使用这层for循环是为了便于记录当前的层次信息level
            for (int i = 0; i < size; i++) {
                Integer num = queue.poll();
                if (num == 0) return level;
                //每个点的相邻节点由n-j^2来得出
                for (int j = 1; (remain = num - j * j) >= 0; j++) {
                    if (remain == 0) return level + 1;
                    if (!visited[remain]) {
                        queue.add(remain);
                        visited[remain] = true;
                    }
                }
            }
            level++;
        }
        throw new IllegalArgumentException();
    }

    public int numSquaresByDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        int remain = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; (remain = i - j * j) >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[remain] + 1);
            }
        }
        return dp[n];
    }

    @Test
    public void run7() {
        int i = numSquaresByDP(12);
        System.out.println(i);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0) return 0;
        Set<String> set = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        for (String s : wordList) {
            set.add(s);
        }
        if (!set.contains(endWord)) {
            return 0;
        }
        int level = 1, size = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        set.add(beginWord);
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                char[] chars = cur.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char org = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == org) continue;
                        chars[j] = c;
                        String test = String.valueOf(chars);
                        if (test.equals(endWord)) {
                            return level + 1;
                        }
                        if (set.contains(test)) {
                            set.remove(test);
                            queue.add(test);
                            map.put(test, cur);
                        }
                    }
                    chars[j] = org;
                }
            }
            level++;
        }
        return 0;
    }

    private boolean transofrmed(char[] chars, String str) {
        int count = 0;
        char[] chars1 = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars1[i]) count++;
            if (count > 1) return false;
        }
        if (count == 1) return true;
        else return false;
    }

    @Test
    public void run8() {
        String[] strings = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> list = Arrays.asList(strings);
        int i = ladderLength("hit", "cog", list);
        System.out.println(i);
    }

    public int ladderLengthg(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String s : wordList) {
            set.add(s);
        }
        if (!set.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                char[] arr = s.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char original = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == arr[j]) {
                            continue;
                        }
                        arr[j] = c;
                        String test = String.valueOf(arr);
                        if (test.equals(endWord)) return step + 1;
                        if (set.contains(test)) {
                            queue.offer(test);
                            set.remove(test);
                        }
                    }
                    arr[j] = original;
                }
            }
            step++;
        }
        return 0;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : wordList) set.add(s);
        if (!set.contains(endWord)) return res;
        int level = 1, size = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        map.put(beginWord, new ArrayList<>());
        boolean flag = false;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                char[] chars = cur.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char org = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == org) continue;
                        chars[j] = c;
                        String test = String.valueOf(chars);
                        if (test.equals(endWord)) {
                            flag = true;
                            List<String> list = new ArrayList<>();
                            list.add(0, test);
                            list.add(0, cur);
                            dfs(res, map, beginWord, cur, list);
                        }
                        if (set.contains(test)) {
                            queue.add(test);
                            if (map.containsKey(test)) {
                                List<String> list = map.get(test);
                                list.add(cur);
                            } else {
                                List<String> list = new ArrayList<>();
                                list.add(cur);
                                map.put(test, list);
                            }
                        }
                    }
                    chars[j] = org;
                }
            }
            if (flag) break;
            level++;
        }
        return res;
    }

    private void dfs(List<List<String>> res, Map<String, List<String>> map, String end, String cur, List<String> list) {
        if (cur.equals(end)) {
            res.add(new ArrayList<>(list));
            return;
        } else {
            List<String> list1 = map.get(cur);
            for (String s : list1) {
                list.add(0, s);
                dfs(res, map, end, s, list);
            }
        }
    }

    @Test
    public void run9() {
        String[] strings = {"ted", "tex", "red", "tax", "tad", "den", "rex", "pee"};
        List<String> list = Arrays.asList(strings);
        List<List<String>> ladders = findLadders("red", "tax", list);
//        String[] strings = {"a","b","c"};
//        List<String> list = Arrays.asList(strings);
//        List<List<String>> ladders = findLadders("a", "c", list);
        System.out.println(ladders.toString());
    }

    public List<List<String>> findLadders2(String start, String end, List<String> wordList) {
        HashSet<String> dict = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node
        HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node
        ArrayList<String> solution = new ArrayList<String>();

        dict.add(start);
        bfs(start, end, dict, nodeNeighbors, distance);
        dfs(start, end, dict, nodeNeighbors, distance, solution, res);
        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<String>());

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                ArrayList<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {// Check if visited
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor))// Found the shortest path
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }

            if (foundEnd)
                break;
        }
    }

    // Find all next level nodes.
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }

    // DFS: output all paths with the shortest distance.
    private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<String>(solution));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }
}
