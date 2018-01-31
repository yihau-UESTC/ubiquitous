package DataStructure.sort;

import java.util.Arrays;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午7:42 17-10-3
 * 快排是nlog2n复杂度，平均性能最好，不稳定的算法
 */
public class QuickSort {
    public static void main(String[] args){
        int[] a = {3,1,6,5,2,3,9};
        a = sort(a,0,6);
        System.out.println(Arrays.toString(a));
    }

    public static int[] sort(int[] a, int low, int high){
        if (low < high){//这里注意是if不是while，判断递归结束的条件。。
            int privotPos = partition(a, low, high);
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

}
