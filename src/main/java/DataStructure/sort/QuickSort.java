package DataStructure.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午7:42 17-10-3
 * 快排是nlog2n复杂度，平均性能最好，不稳定的算法
 */
public class QuickSort {
    public static void main(String[] args){
        int[] a = {3, 1, 6, 5, 2, 5, 8, 20, 5, 4, 9};
        a = sort3(a, 0, 10);
        System.out.println(Arrays.toString(new QuickSort().qSort(a, 0, 10)));
    }


    public int[] qSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = patition(arr, low, high);
            qSort(arr, low, pivot - 1);
            qSort(arr, pivot + 1, high);
        }
        return arr;
    }

    private int patition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int pivotpos = low;
        for (int i = low + 1; i <= high; i++) {
            if (arr[i] < pivot) {
                pivotpos++;
                if (i != pivotpos) {
                    int temp = arr[i];
                    arr[i] = arr[pivotpos];
                    arr[pivotpos] = temp;
                }
            }
        }
        arr[low] = arr[pivotpos];
        arr[pivotpos] = pivot;
        return pivotpos;
    }

    public static int[] sort(int[] a, int low, int high){
        if (low < high){//这里注意是if不是while，判断递归结束的条件。。
            int privotPos = partition2(a, low, high);
            sort(a, low, privotPos - 1);//递归地对低表排序
            sort(a, privotPos + 1, high);//递归地对高表排序
        }
        return a;
    }

    public static int partition(int[] a, int low, int high){
        //设定表最前的元素为基准元素
        int privotKey = a[low];
        while (low < high){
            while (low < high && a[high] >= privotKey) --high;//从高位元素开始，如果大于基准元素则移动high指针,这里一定要注意是>=不能丢掉=
            int temp = a[high]; a[high] = a[low]; a[low] = temp;//当小于基准元素的时候则交换到低位，交换过后则开始比较低位的元素
            while (low < high && a[low] <= privotKey) ++low;//小于基准元素只增加low指针，这里也不能丢掉等于，如果这两处等于丢掉，如果出现两个相同元素，则会在这里出现死循环
            temp = a[low]; a[low] = a[high]; a[high] = temp;//当大于基准元素交换到高位。
        }
        return low;//返回最后的low指针的位置，low指针的位置即是基准元素的位置，这个是排好序的。下次递归要根据这个位置分割成两半
    }

    public static int partition2(int[] a, int low, int high) {
        //选取数组第一个数为基准
        int pivopos = low;
        int pivot = selectPivotMedianOfThree(a, low, high);
        //检测整个序列，进行划分
        for (int i = low + 1; i <= high; i++) {
            //将小于基准的元素交换到数组的前端
            if (a[i] < pivot) {
                // 每多一个小于基准的数，pivopos就增加1，指向最后一个小的数
                pivopos++;
                //如果当前位置等于pivopos就无须交换
                if (pivopos != i) {
                    int temp = a[pivopos];
                    a[pivopos] = a[i];
                    a[i] = temp;
                }
            }
        }
        //将基准元素与最后一个小于基准的数交换，使得基准元素位于其应该在的位置上。
        a[low] = a[pivopos];
        a[pivopos] = pivot;
        return pivopos;
    }

    //调整基准的选择来平均每次分治两边的长度，如果直接选择第一个元素那种在逆序的情况下快排会退化为冒泡
    // 使得arr[mid] <= arr[low] <= arr[high],并返回arr[low]；
    public static int selectPivotMedianOfThree(int[] arr, int low, int high) {
        int mid = low + ((high - low) >> 1);
        int temp = 0;
        if (arr[mid] > arr[high]) {
            temp = arr[mid];
            arr[mid] = arr[high];
            arr[high] = temp;
        }
        if (arr[low] > arr[high]) {
            temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
        }
        if (arr[mid] > arr[low]) {
            temp = arr[low];
            arr[low] = arr[mid];
            arr[mid] = temp;
        }
        return arr[low];
    }

    public static int[] sort2(int[] a, int low, int high) {
        if (low < high) {//这里注意是if不是while，判断递归结束的条件。。
            int pivopos = low;
            int equpos = low;
            int pivot = selectPivotMedianOfThree(a, low, high);
            //检测整个序列，进行划分
            for (int i = low + 1; i <= high; i++) {
                //将小于基准的元素交换到数组的前端
                if (a[i] <= pivot) {
                    // 每多一个小于基准的数，pivopos就增加1，指向最后一个小的数
                    pivopos++;
                    if (a[i] == pivot) {
                        equpos++;
                        if (equpos != i) {
                            int temp = a[equpos];
                            a[equpos] = a[i];
                            a[i] = temp;
                        }
                    }
                    //将于基准相等的元素交换到前端后，可能会把比基准小的元素交换回来，所以要在判断一次，如果发生了这样的交换则在跟pivopos位的元素交换一次
                    if (a[i] < pivot) {
                        //如果当前位置等于pivopos就无须交换
                        if (pivopos != i) {
                            int temp = a[pivopos];
                            a[pivopos] = a[i];
                            a[i] = temp;
                        }
                    }
                }
            }
            //将于基准元素相等的元素和小于
            int i = low, j = equpos + 1;
            for (; i <= equpos || j <= pivopos; i++, j++) {
                a[i] = a[j];
                a[j] = pivot;
            }

            sort(a, low, pivopos - (equpos - low));//递归地对低表排序
            sort(a, pivopos + 1, high);//递归地对高表排序
        }
        return a;
    }

    public static int[] sort3(int[] a, int low, int high) {
        if (low < high) {//这里注意是if不是while，判断递归结束的条件。。
            int prepos = low;
            int pivopos = low;
            int pivot = selectPivotMedianOfThree(a, low, high);
            //检测整个序列，进行划分
            for (int i = low + 1; i <= high; i++) {
                //将小于基准的元素交换到数组的前端
                if (a[i] <= pivot) {
                    // 每多一个小于基准的数，pivopos就增加1，指向最后一个小的数
                    pivopos++;
                    if (a[i] < pivot) {
                        prepos++;
                        if (prepos != i) {
                            int temp = a[prepos];
                            a[prepos] = a[i];
                            a[i] = temp;
                        }
                    }
                    //将于基准相等的元素交换到前端后，可能会把比基准小的元素交换回来，所以要在判断一次，如果发生了这样的交换则在跟pivopos位的元素交换一次
                    if (a[i] == pivot) {
                        //如果当前位置等于pivopos就无须交换
                        if (pivopos != i) {
                            int temp = a[pivopos];
                            a[pivopos] = a[i];
                            a[i] = temp;
                        }
                    }
                }
            }
            //将于基准元素相等的元素和小于
            a[low] = a[prepos];
            a[prepos] = pivot;

            sort(a, low, prepos - 1);//递归地对低表排序
            sort(a, pivopos + 1, high);//递归地对高表排序
        }
        return a;
    }

    public int[] threeChannelQSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = selectPivotMedianOfThree(arr, low, high);
            int prepos = low, pivotpos = low;
            for (int i = low + 1; i <= high; i++) {
                if (arr[i] <= pivot) {
                    pivotpos++;
                    if (arr[i] < pivot) {
                        prepos++;
                        if (i != prepos) {
                            int temp = arr[i];
                            arr[i] = arr[prepos];
                            arr[prepos] = temp;
                        }
                    }
                    if (arr[i] == pivot) {
                        if (i != pivotpos) {
                            int temp = arr[i];
                            arr[i] = arr[pivotpos];
                            arr[pivotpos] = temp;
                        }
                    }
                }
            }
            arr[low] = arr[prepos];
            arr[prepos] = pivot;
            threeChannelQSort(arr, low, prepos - 1);
            threeChannelQSort(arr, pivotpos + 1, high);
        }
        return arr;
    }

    public int[] threeChannelQSort2(int arr[], int low, int high) {
        if (low < high) {
            int pivot = selectPivotMedianOfThree(arr, low, high);
            int lowPos = low, highPos = high;
            int i = low;
            while (i <= highPos) {
                //比哨兵小的放在前端
                if (arr[i] < pivot) {
                    int temp = arr[i];
                    arr[i] = arr[lowPos];
                    arr[lowPos] = temp;
                    lowPos++;
                    i++;
                    //比哨兵大的放在后端，这时需要把后端的元素交换到当前位置，由于我们不清楚交换过来的元素大小，所以需要再次判断交换过来的元素和哨兵的大小
                } else if (arr[i] > pivot) {
                    int temp = arr[highPos];
                    arr[highPos] = arr[i];
                    arr[i] = temp;
                    highPos--;
                    //根哨兵相等的不变
                } else if (arr[i] == pivot) {
                    i++;
                }
            }
            threeChannelQSort2(arr, low, lowPos - 1);
            threeChannelQSort2(arr, highPos + 1, high);
        }
        return arr;
    }


    @Test
    public void run() {
//        int[] a = {3,1,6,5,2,5,8,20,5,4,9};
//        System.out.println(Arrays.toString(new QuickSort().threeChannelQSort(a, 0, 10)));
        int[] a1 = {3, 1, 6, 5, 2, 5, 8, 20, 5, 4, 9};
        System.out.println(Arrays.toString(new QuickSort().threeChannelQSort2(a1, 0, 10)));
    }
}
