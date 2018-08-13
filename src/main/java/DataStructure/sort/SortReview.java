package DataStructure.sort;

import org.junit.Test;

import java.util.Arrays;


public class SortReview {
    @Test
    public void run() {
        int[] arr = {3, 1, 7, 4, 2, 5, 6};
//        bubble(arr);
//        insert(arr);
//        shell(arr);
//        select(arr);
//        heapSort(arr);
//        int[] quick = quick(arr, 0, 6);
//        System.out.println(Arrays.toString(quick));
        mergeSort(arr, 0, 6);
        System.out.println(Arrays.toString(arr));
        Class<String> stringClass = String.class;

    }

    /**
     * 稳定
     * N^2
     *
     * @param arr
     */
    public void bubble(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 稳定
     * N^2
     *
     * @param arr
     */
    public void insert(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int x = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > x; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = x;
        }
        System.out.println(Arrays.toString(arr));
    }

    //希尔,不稳定
    public void shell(int[] arr) {
        int n = arr.length;
        int gap = n;
        while (gap > 1) {
            gap = gap / 3 + 1;
            for (int i = gap; i < n; i++) {
                int x = arr[i];
                int j = i - gap;
                for (; j >= 0 && arr[j] > x; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = x;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //选择
    public void select(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    index = j;
                }
            }
            swap(arr, i, index);
        }
        System.out.println(Arrays.toString(arr));
    }

    //堆排
    public void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapAdjust(arr, i, n);
        }
        for (int i = 0; i < n; i++) {
            //每次都把最大的元素(第一个)放到数组末端，然后调整堆大小。
            swap(arr, 0, n - i - 1);
            heapAdjust(arr, 0, n - i - 1);
        }
        System.out.println(Arrays.toString(arr));
    }

    private void heapAdjust(int[] arr, int start, int len) {
        int child = 2 * start + 1;
        int x = arr[start];
        while (child < len) {
            if (child + 1 < len && arr[child + 1] > arr[child])
                child = child + 1;
            if (arr[child] <= x)
                break;
            arr[start] = arr[child];
            start = child;
            child = 2 * start + 1;
        }
        arr[start] = x;
    }

    //快排
    public int[] quick(int[] arr, int low, int high) {
        if (low < high) {
            int pivotPos = partition(arr, low, high);
            quick(arr, low, pivotPos - 1);
            quick(arr, pivotPos + 1, high);
        }
        return arr;
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int pivotPos = low;
        for (int i = low + 1; i <= high; i++) {
            if (arr[i] < pivot) {
                pivotPos++;
                swap(arr, i, pivotPos);
            }
        }
        swap(arr, pivotPos, low);
        return pivotPos;
    }

    //归并,先划分，再合并
    public void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) >>> 1;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low, j = mid + 1, c = 0;
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j])
                temp[c++] = arr[i++];
            else
                temp[c++] = arr[j++];
        }
        if (i <= mid)
            System.arraycopy(arr, i, temp, c, mid - i + 1);
        if (j <= high)
            System.arraycopy(arr, j, temp, c, high - j + 1);
        System.arraycopy(temp, 0, arr, low, high - low + 1);
    }


    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
