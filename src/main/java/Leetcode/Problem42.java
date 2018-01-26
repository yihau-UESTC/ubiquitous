package Leetcode;

import java.util.Stack;

/**
 * leetcode42 trapping rain water
 */
public class Problem42 {
    /**
     * Complexity Analysis
     Time complexity: O(n^2) For each element of array, we iterate the left and right parts.
     Space complexity: O(1)O(1) extra space.
     * @param height
     * @return
     */
    public static int trap1(int[] height){
        int result = 0;
        for (int i = 1; i < height.length - 1; i++){
            int maxleft = 0, maxright = 0;
            //遍历当前节点左边的节点，找出左最大。
            for (int j = i; j >= 0; j--) {
                maxleft = Math.max(maxleft, height[j]);
            }
            //遍历当前节点右边的节点，找出右最大。
            for (int j = i; j < height.length; j++) {
                maxright = Math.max(maxright, height[j]);
            }
            //计算当前节点能存的水。
            result +=  Math.min(maxleft, maxright) - height[i];
        }
        return result;
    }

    /**
     * Complexity analysis

     Time complexity: O(n).
     We store the maximum heights upto a point using 2 iterations of O(n) each.
     We finally update ans using the stored values in O(n).
     Space complexity: O(n)extra space.
     Additional O(n) space for left_maxleft_max and right_maxright_max arrays than in Approach #1.
     * @param height
     * @return
     */
    public static int trap2(int[] height){
        int n = height.length;
        int result = 0;
        int[] leftHeight = new int[n], rightHeight = new int[n];
        //按当前点左边最高的节点存水，每个节点的高度。
        for (int i = 0; i < n; i++){
            if (i <= 0)leftHeight[i] = Math.max(height[i], 0);
            else leftHeight[i] = Math.max(height[i], leftHeight[i - 1]);
        }
        //按当前点右边最高的节点存水，每个节点的高度。
        for (int i = n - 1; i >= 0; i--){
            if (i >= n - 1)rightHeight[i] = Math.max(height[i], 0);
            else rightHeight[i] = Math.max(height[i], rightHeight[i + 1]);
        }
        //合并左右节点的数组，取较低的那个高度，然后减去节点本身高度。
        for (int i = 0; i < n; i++){
            result += Math.min(leftHeight[i], rightHeight[i]) - height[i];
        }
        return result;
    }

    /**
     * Complexity analysis
     Time complexity: O(n).
     Single iteration of O(n) in which each bar can be touched at most twice(due to insertion and deletion from stack) and insertion and deletion from stack takes O(1)O(1) time.
     Space complexity: O(n). Stack can take upto O(n) space in case of stairs-like or flat structure.
     * @param height
     * @return
     */
    public static int trap3(int[] height){
        int result = 0, current = 0;
        Stack<Integer> stack = new Stack<>();
        while (current < height.length){
            //当前节点的高度大于栈顶元素高度时，右边也有挡板了，说明已经可以积水了，开始出栈。
            while (!stack.isEmpty() && height[current] > height[stack.peek()]){
                int pre = stack.pop();
                if (stack.isEmpty())break;//如果栈里只有一个元素，说明是高度从开始就在升高，左边无挡板，不能积水。
                int distance = current - stack.peek() - 1;//计算左右两边挡板之间的距离
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[pre];//计算挡板的最低高度，然后减去中间的高度。
                result += distance * bounded_height;
            }
            //当前节点高度小于栈顶元素高度时，元素进栈
            stack.push(current++);
        }
        return result;
    }

    /**
     * Time complexity: O(n). Single iteration of O(n).
     Space complexity: O(1) extra space. Only constant space required for left, right, left_maxleft_max and right_maxright_max.
     * @param height
     * @return
     */
    public static int trap4(int[] height){
        int result = 0, left = 0, right = height.length - 1, maxleft = 0, maxright = 0;
      //两个指针从两边开始变化。法2是左边和右边分开执行，这里使用两个指针从两边开始迭代。
        while (left < right){
            if (height[left] < height[right]){
                if (height[left] >= maxleft)maxleft = height[left];
                else result += maxleft - height[left];
                left++;
            }else {
                if (height[right] >= maxright)maxright = height[right];
                else result += maxright - height[right];
                right--;
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap1(a));
        System.out.println(trap2(a));
        System.out.println(trap3(a));
        System.out.println(trap4(a));
    }
}
