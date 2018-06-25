package alginterview;

import org.junit.Test;
import org.omg.CORBA.MARSHAL;
import utils.MyUtil;

import java.util.*;

public class BinarySearch {

    private static int binarySearch(int[] arr, int target) {
        int n = arr.length;
//        int low = 0, high = n - 1;//在[low...high]中查找
        int low = 0, high = n;//在[low...high)中查找

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;

            if (arr[mid] > target) high = mid;
            else low = mid + 1;
        }

        return -1;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 1;
        int k = 1;
        int num = nums[0];
        int count = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
                if (count < 2) {
                    if (i != k) nums[k] = nums[i];
                    k++;
                }
            }


            if (nums[i] != num) {
                if (i != k) {
                    num = nums[i];
                    nums[k] = nums[i];
                    k++;
                    count = 0;
                } else {
                    num = nums[i];
                    k++;
                    count = 0;
                }

            }
        }
        return k;
    }

    public static void main(String[] args) {
//        int[] arr = {1,3,4,6,7,89,111};
//        int i = binarySearch(arr, 89);
//        System.out.println(i);
        int[] arr = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int i = removeDuplicates(arr);
        System.out.println(i);
        int qq = 5 * 5;
        int qe = 5 << 1;
        System.out.println(qe);
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input the number:");
        int n = scanner.nextInt();
        while (n > 0) {
            int y = n % 10;
            n = n / 10;
            System.out.println(y);
        }

    }

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int index = n - k;//确定第k大的数在数组中所处的位置索引
        int i = find(nums, 0, n - 1, index);
        return nums[i];
    }

    public int find(int[] arr, int low, int high, int index) {
        int pivotPos = partition(arr, low, high);
        if (pivotPos == index) return pivotPos;
        else if (pivotPos > index) {
            return find(arr, low, pivotPos - 1, index);
        } else {
            return find(arr, pivotPos + 1, high, index);
        }
    }

    /**
     * @param arr
     * @param low
     * @param high
     */
    public int[] quickSort(int[] arr, int low, int high) {
        //当low = high的时候只有一个元素就没必要划分小于pivot和大于pivot的了
        if (low < high) {
            int pivotPos = partition(arr, low, high);
            quickSort(arr, low, pivotPos - 1);
            quickSort(arr, pivotPos + 1, high);
        }
        return arr;
    }


    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int pivotPos = low;//[low + 1,pivotPos]维护小于pivot的元素
        for (int i = low + 1; i <= high; i++) {
            if (arr[i] < pivot) {
                pivotPos++;
                MyUtil.swap(arr, i, pivotPos);
            }
        }
        arr[low] = arr[pivotPos];
        arr[pivotPos] = pivot;
        return pivotPos;
    }

    @Test
    public void run() {
//        int[] arr = {3,2,1,5,6,4};
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
//        int kthLargest = findKthLargest(arr, 2);
        int kthLargest = findKthLargest(arr, 4);
        System.out.println(kthLargest);
    }

    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        //使用一个对撞指针的方式,时间复杂度为O(N)
        int l = 0, r = n - 1;
        while (l < r) {
            //这里不包括=，因为题目要求index2>index1
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] < target) l++;
            else r++;
        }
        return null;
    }

    @Test
    public void run1() {
//        int[] arr = {3,2,1,5,6,4};
        int[] arr = {2, 7, 11, 15};
//        int kthLargest = findKthLargest(arr, 2);
        twoSum(arr, 9);
        String s = "asdfsa";

    }

    @Test
    public void run2() {
        String s = "A man, a plan, a canal: Panama";
        char[] chars = s.toCharArray();

        HashSet<Object> objects = new HashSet<>();
        String ss = new String(chars);
        boolean palindrome = isPalindrome(s);
        System.out.println(palindrome);

    }

    public boolean isPalindrome(String s) {
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            int left = s.charAt(l);
            if (left < 48 || left > 57 && left < 65 || left > 90 && left < 97 || left > 122) {
                l++;
                continue;
            }
            if (left < 91 && left > 64) {
                left = left + 32;
            }
            int right = s.charAt(r);
            if (right < 48 || right > 57 && right < 65 || right > 90 && right < 97 || right > 122) {
                r--;
                continue;
            }
            if (right < 91 && right > 64) {
                right = right + 32;
            }

            if (right != left) return false;
            else {
                l++;
                r--;
            }
        }
        return true;
    }

    public int maxArea(int[] height) {
        int n = height.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int area = (j - i) * (height[j] > height[i] ? height[i] : height[j]);
                if (area > max) max = area;
            }
        }
        return max;
    }

    @Test
    public void run3() {
//        int[] arr = {1,2,1};
//        int i = maxArea(arr);
        int result = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result = result + i * j;
            }
        }
        System.out.println(result);
        HashMap<Character, Integer> map = new HashMap<>();
        map.putIfAbsent('a', 1);

    }

    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length();
        int m = p.length();
        List<Integer> res = new ArrayList<>();
        //当s<p的时候直接返回
        if (n < m) return res;
        char[] schars = s.toCharArray();
        char[] pchars = p.toCharArray();
        int l = 0, r = m - 1;//[l...p.length - 1]维持长度固定的滑动窗口->r++的时候l++
        //保存窗口中的字符映射
        int[] window = new int[26];
        //保存p字符串中的字符映射，用于和窗口中的做匹配
        int[] map = new int[26];
        //保存p字符串中字符种类
        Set<Character> set = new HashSet<>();
        //初始化map和set
        for (int i = 0; i < m; i++) {
            map[pchars[i] - 97] += 1;
            set.add(pchars[i]);
        }
        //初始化window
        for (int i = 0; i < m; i++) {
            window[schars[i] - 97] += 1;
        }
        //由于窗口的长度是固定的，所以判断r<n即可
        while (r < n) {
            //判断window和p是否为anagram
            boolean flag = true;
            for (Character c : set) {
                if (map[c - 97] != window[c - 97]) {
                    flag = false;
                    break;
                }
            }

            if (flag) res.add(l);
            //如果r+1 == n的话直接退出循环
            if (r + 1 < n) {
                //窗口整体向右移动一步
                r++;
                window[schars[r] - 97] += 1;
                window[schars[l++] - 97]--;
            } else break;
        }
        return res;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int n = s.length();
        int m = p.length();
        List<Integer> res = new ArrayList<>();

        if (n < m) return res;
        //类似的这种字符串比较相同的，可以利用一个数组，然后进来一个该字符++，然后出去一个字符，该字符--，最后判断数组是否全为0，如果全为0，则说明相同
        int[] map = new int[256];
        Arrays.asList(map);
        char[] schars = s.toCharArray();
        char[] pchars = p.toCharArray();
        for (int i = 0; i < m; i++) {
            map[schars[i]]++;
            map[pchars[i]]--;
        }
        int l = 0, r = m - 1;
        while (r < n) {
            boolean flag = true;
            for (int i = 0; i < map.length; i++) {
                if (map[i] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) res.add(l);
            if (r + 1 < n) {
                r++;
                map[schars[r]]++;
                map[schars[l]]--;
                l++;
            } else break;
        }
        return res;
    }


    @Test
    public void run4() {
        String s = "cbaebabacd", p = "abc";
        List<Integer> anagrams = findAnagrams2(s, p);
        System.out.println(anagrams);
    }

    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        String res = "";
        int min = n + 1;
        if (n < m) return res;
        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();
        Set<Character> set = new HashSet<>();
        int[] map = new int[256];
        for (int i = 0; i < m; i++) {
            set.add(tchars[i]);
            map[schars[i]]++;
            map[tchars[i]]--;
        }
        int l = 0, r = m - 1;
        while (r < n) {
            if (contains(set, map)) {
                if ((r - l + 1) < min) {
                    res = s.substring(l, r + 1);
                    min = r - l + 1;
                }
                map[schars[l]]--;
                l++;
            } else {
                if (r + 1 < n) {
                    r++;
                    map[schars[r]]++;
                } else break;
            }
        }
        return res;
    }

    private boolean contains(Set<Character> set, int[] map) {
        for (Character c : set) {
            if (map[c] < 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void run5() {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        String s1 = minWindow(s, t);
        System.out.println(s1);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else map.put(nums1[i], 1);
        }
        Set<String> set;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                int value = map.get(nums2[i]);
                if (value > 0) {
                    map.put(nums2[i], value - 1);
                    list.add(nums2[i]);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    @Test
    public void run6() {
        boolean isomorphic = isIsomorphic("ab", "ca");
        System.out.println(isomorphic);
    }

    public boolean isIsomorphic(String s, String t) {
        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < schars.length; i++) {
            if (map.containsKey(schars[i])) {
                char value = map.get(schars[i]);
                if (value != tchars[i]) return false;
            } else if (map.containsValue(tchars[i])) {
                return false;
            } else map.put(schars[i], tchars[i]);
        }
        return true;
    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else map.put(c, 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : list) {
            char key = (char) entry.getKey();
            int value = (int) entry.getValue();
            for (int i = 0; i < value; i++) {
                sb.append(key);
            }
        }
        return sb.toString();
    }

    class ValueComparator implements Comparator<Character> {

        Map<Character, Integer> map;

        public ValueComparator(Map<Character, Integer> map) {
            this.map = map;
        }

        @Override
        public int compare(Character o1, Character o2) {
            return map.get(o1) - map.get(o2);
        }
    }

    @Test
    public void run7() {
        String tree = frequencySort("tree");
        System.out.println(tree);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int[] s = new int[3];
        for (int i = 0; i < nums.length; i++) {
            s[0] = nums[i];
            for (int j = 1; j < nums.length; j++) {
                s[1] = nums[j];
                int remain = -nums[i] - nums[j];
                if (set.contains(remain)) {
                    s[2] = remain;
                    Arrays.sort(s);
                    boolean flag = true;
                    for (int k = 0; k < res.size(); k++) {
                        List<Integer> list = res.get(k);
                        if (list.get(0) == s[0] && list.get(1) == s[1] && list.get(2) == s[2]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) res.add(Arrays.asList(s[0], s[1], s[2]));
                } else set.add(nums[j]);
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j == i + 1 || j > i + 1 && nums[j] != nums[j - 1]) {
                        int remain = target - nums[i] - nums[j];
                        int l = j + 1, r = nums.length - 1;
                        while (l < r) {
                            if (remain == nums[l] + nums[r]) {
                                res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                                while (l < r && nums[l] == nums[l + 1]) l++;
                                while (l < r && nums[r] == nums[r - 1]) r--;
                                l++;
                                r--;
                            } else if (remain > nums[l] + nums[r]) l++;
                            else r--;
                        }
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void run8() {
        int[] arr = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> lists = fourSum(arr, 0);
        System.out.println(lists.toString());
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int remain = target - nums[i];
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (Math.abs(remain - sum) < min) {
                    min = Math.abs(remain - sum);
                    res = sum + nums[i];
                }
                if (remain == sum) return target;
                else if (remain > sum) l++;
                else r--;
            }
        }
        return res;
    }

    @Test
    public void run9() {
        int[] arr = {-1, 2, 1, -4};
        int i = threeSumClosest(arr, 1);
        System.out.println(i);
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = C[i] + D[j];
                if (map.containsKey(value)) {
                    map.put(value, map.get(value) + 1);
                } else map.put(value, 1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int remain = 0 - A[i] - B[j];
                res += map.getOrDefault(remain, 0);
            }
        }
        return res;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        //使用map来实现，首先要考虑如果判断两个字符串是angrams，容易的方法是将两个字符串按照字典顺序排序
        //这样就可以比较了，容易想到的是转成char数组来实现，将orderd字符串用作map的键，
        // 所有angrams的字符串放进值中，使用一个list来保存

        List<List<String>> res = new ArrayList<>();
        int n = strs.length;
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String order = String.valueOf(chars);
            List<String> list = map.getOrDefault(order, new ArrayList<String>());
            list.add(strs[i]);
            map.put(order, list);
        }
        res.addAll(map.values());
        return res;
    }


    @Test
    public void run10() {
//        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs = {"", ""};
        List<List<String>> lists = groupAnagrams(strs);
        System.out.println(lists.toString());
    }

    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int res = 0;
        Map<Integer, Integer> map = null;
        for (int i = 0; i < n; i++) {
            map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int dis = distance(points[i], points[j]);
                    map.put(dis, map.getOrDefault(dis, 0) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> node : map.entrySet()) {
                int value = node.getValue();
                res += value * (value - 1);
            }
        }
        return res;
    }

    private int distance(int[] p1, int[] p2) {
        return (int) (Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

    @Test
    public void run11() {
        int[][] ints = {{0, 0}, {1, 0}, {2, 0}};
        numberOfBoomerangs(ints);
    }

    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    class Line {
        float a;
        float b;

        Line(float x, float y) {
            a = x;
            b = y;
        }

        @Override
        public boolean equals(Object o) {
            Line line = (Line) o;
            return Float.compare(line.a, a) == 0 &&
                    Float.compare(line.b, b) == 0;
        }

        @Override
        public int hashCode() {

            return Objects.hash(a, b);
        }
    }

    private Line getLine(Point p1, Point p2) {
        float x = 0, o = 0;
        if (p1.x - p2.x == 0) {
            return new Line(Integer.MAX_VALUE, p1.x);
        } else if (p1.y - p2.y == 0) {
            return new Line(0, p1.y);
        } else {
            x = (float) (p2.y - p1.y) / (float) (p2.x - p1.x);
        }
        if (p1.x == 0 && p1.y == 0) {
            o = (float) p2.y - x * p2.x;
        } else {
            o = (float) p1.y - x * p1.x;
        }
        return new Line(x, o);
    }

    public int maxPoints(Point[] points) {
        int n = points.length;
        if (n <= 2) return points.length;
        Map<Line, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            int max = 0;
            int same = 0;
            for (int j = i + 1; j < n; j++) {
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    same++;
                    continue;
                }
                Line line = getLine(points[i], points[j]);
                map.put(line, map.getOrDefault(line, 0) + 1);
                max = Math.max(max, map.get(line));
            }
            res = Math.max(res, max + same + 1);

            map.clear();
        }
        return res;
    }

    @Test
    public void run12() {
        int[][] arr = {{0, -1}, {0, 3}, {0, -4}, {0, -2}, {0, -4}, {0, 0}, {0, 0}, {0, 1}, {0, -2}, {0, 4}};
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(94911151, 94911150);
        points[2] = new Point(94911152, 94911151);
//        points[3] = new Point(4,1);
//        points[4] = new Point(2,3);
//        points[5] = new Point(1,4);
        int i = maxPoints(points);
        System.out.println(i);
    }

    @Test
    public void run13() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(2);
        set.add(5);
        set.add(9);
        set.add(0);
        set.add(1);
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(4);
        Integer ceiling = set.ceiling(5);
        System.out.println(ceiling);
        Integer floor = set.floor(5);
        System.out.println(floor);
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (!set.add((long) nums[i])) return true;
            if (set.size() > k) set.remove((long) nums[i - k]);
        }
        return false;
    }

    @Test
    public void run14() {
        int[] arr = {1, 0, 1, 1};
        System.out.println(containsNearbyDuplicate(arr, 1));
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        //使用long的原因是可能越界
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            //floor是查找表中小于等于给定值的最大的元素<=t，
            Long floor = set.floor((long) nums[i] + (long) t);
            //限定nums[i]和nums[j]的差值在t之间
            if (floor != null && floor >= (long) nums[i] - (long) t) return true;
            //维护滑动窗口的大小为k
            set.add((long) nums[i]);
            if (set.size() > k) set.remove((long) nums[i - k]);
        }
        return false;
    }

    @Test
    public void run15() {
        int[] arr = {1, 5, 9, 1, 5, 9};
        System.out.println(containsNearbyAlmostDuplicate(arr, 2, 3));

    }

}
