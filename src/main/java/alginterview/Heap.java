package alginterview;

import java.util.Arrays;

/**
 * 大顶堆
 */
public class Heap {

    /**
     * 自顶向下调整算法
     *
     * @param arr
     * @param node
     * @param length
     */
    private static void siftDown(int[] arr, int node, int length) {
        int child = 2 * node + 1;
        int adjustVal = arr[node];
        while (child < length) {
            if (child + 1 < length && arr[child] < arr[child + 1]) child++;
            if (arr[child] <= adjustVal) break;
            else {
                //子节点上移，指针下滑
                arr[node] = arr[child];
                node = child;
                child = 2 * node + 1;
            }
        }
        arr[node] = adjustVal;
    }

    /**
     * 自底向上调整
     *
     * @param arr
     * @param node
     */
    private static void siftUp(int[] arr, int node) {
        int father = (node - 1) / 2;
        int adjustVal = arr[node];

        while (node > 0) {
            if (arr[father] >= adjustVal) break;
            else {
                arr[node] = arr[father];
                node = father;
                //这里需要注意当前node是0的时候-1/2还是0，所以多加一点判断
                father = (node - 1) / 2;
            }
        }
        arr[node] = adjustVal;
    }

    public static void main(String[] args) {
        int[] h = {3, 1, 5, 7, 2, 4, 9, 6, 10, 8};
        //build heap
        int n = h.length;
        for (int i = (n - 2) / 2; i >= 0; i--) {
            siftDown(h, i, n);
        }
        System.out.println("初始大顶堆：" + Arrays.toString(h));
        System.out.println("添加元素-->100");
        int[] h1 = new int[n + 1];
        System.arraycopy(h, 0, h1, 0, n);
        h1[n] = 100;
        siftUp(h1, n);
        System.out.println("添加元素过后大顶堆：" + Arrays.toString(h1));
    }

}
