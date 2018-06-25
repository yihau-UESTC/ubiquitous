package DataStructure;

import java.util.Arrays;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        int[] arr = {21, 25, 49, 25, 16, 8};
//        bubbleSort(arr);
//        improveBubbleSort(arr);
//        insertSort(arr);
//        selectSort(arr);
//        heapSort(arr);
//        quickSort(arr,0, 5);
        mergeSort(arr, 0, 5);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    public static void improveBubbleSort(int[] a) {
        int n = a.length;
        int i = n;
        while (i > 0) {
            int pos = 0;
            for (int j = 1; j < i; j++) {
                if (a[j - 1] > a[j]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                    pos = j;
                }
            }
            i = pos;
        }
        System.out.println(Arrays.toString(a));
    }

    public static void insertSort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int x = a[i];
            int j = i - 1;
            for (; j >= 0 && a[j] > x; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = x;
        }
        System.out.println(Arrays.toString(a));
    }

    public static void selectSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    min = j;
                }
            }

            if (min != i) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
        System.out.println(Arrays.toString(a));
    }

    public static void heapSort(int[] a) {
        int n = a.length;
        //构建堆,n-2/2是最后一个非叶子节点
        for (int i = (n - 2) / 2; i >= 0; i--) {
            siftDown(a, i, n);
        }
        System.out.println("堆---->" + Arrays.toString(a));
        //堆构建好以后就很自然了，每次拿出堆顶元素放在数组的最后面，然后执行一次堆调整算法。
        for (int i = n - 1; i > 0; i--) {//当剩下一个元素的时候就不用在动元素了，所以这里不需要=0
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            siftDown(a, 0, i);
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 下滑调整，主要用于构建堆，自上向下调整堆，前提是调整前两颗子树已经是堆，所以在构建堆的时候要从最底层的叶子节点开始。
     *
     * @param a
     * @param start
     * @param length
     */
    private static void siftDown(int[] a, int start, int length) {
        int child = 2 * start + 1;
        int temp = a[start];
        while (child < length) {
            if (child + 1 < length && a[child + 1] > a[child]) {
                child = child + 1;
            }
            if (a[child] <= temp) break;
            else {
                a[start] = a[child];
                start = child;
                child = 2 * start + 1;
            }
        }
        a[start] = temp;
    }

    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotpos = partition(a, low, high);
            quickSort(a, low, pivotpos - 1);
            quickSort(a, pivotpos + 1, high);
        }

    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[low];
        int pivotpos = low;//维护小于pivot的指针
        for (int i = low + 1; i <= high; i++) {
            if (a[i] < pivot) {
                pivotpos++;
                if (i != pivotpos) {
                    int temp = a[i];
                    a[i] = a[pivotpos];
                    a[pivotpos] = temp;
                }
            }
        }
        a[low] = a[pivotpos];
        a[pivotpos] = pivot;
        return pivotpos;
    }

    public static void mergeSort(int[] a, int low, int high) {
        if (low < high) {
            int mid = (high + low) / 2;
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
    }

    private static void merge(int[] a, int low, int mid, int high) {
        int[] seq = new int[high - low + 1];
        int i = low, j = mid + 1, c = 0;
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) seq[c++] = a[i++];
            else seq[c++] = a[j++];
        }
        while (i <= mid) seq[c++] = a[i++];
        while (j <= high) seq[c++] = a[j++];
        for (int k = 0; k < seq.length; k++) {
            a[low + k] = seq[k];
        }
    }


}
