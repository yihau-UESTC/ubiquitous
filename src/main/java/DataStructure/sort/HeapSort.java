package DataStructure.sort;

import java.util.Arrays;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午1:52 17-10-4
 * 堆排序的最坏情况为nlogn，不稳定
 * 堆排序主要分为两块，一，构建初始堆，二，推出堆顶的元素，调整剩余元素形成堆。
 */
public class HeapSort {
    public static void main(String[] args){
        int[] h = {3,1,5,7,2,4,9,6,10,8};
        sort(h, 10);
        int n = -2 / 2;
        System.out.println("77777" + n);
    }

    public static void sort(int[] h, int length){
        //构建初始堆
        buildHeap(h, length);
        //把堆顶的最大元素放到表的末端，对剩余元素重新调整得到堆
        for (int i = h.length - 1; i > 0; i--){
            int temp = h[i]; h[i] = h[0]; h[0] = temp;//交换堆中最后一个元素和堆顶元素
            HeapAdjust(h,0, i);//调整剩余元素为堆
        }
    }
    /*
    构建初始堆
     */
    private static int[] buildHeap(int[] h, int length) {
        //从第(n-1)/2个元素开始，首先构建这个节点的子树为堆，然后一次向前对各节点为根的子树构建堆，直到第一个元素
        //至此初始堆建成
        for (int i = (length - 1) / 2; i >= 0; i--){
            System.out.println("build");
            h = HeapAdjust(h, i, length);
        }
        return h;
    }

    /*

     */
    private static int[] HeapAdjust(int[] h, int i, int length) {
        int temp = h[i];
        int child = 2 * i + 1;//i节点的左子树位置
        while (child < length){
            if (child + 1 < length && h[child] < h[child + 1]){
                child ++;//选取左右子树中较大的一个
            }
            //如果子树大于根，交换子树与根
            if (h[i] < h[child]){
                h[i] = h[child];
                h[child] = temp;
            }else {
                break;
            }
            //修改需要调整的节点位置，以及其子节点
            i = child;
            child = 2 * i + 1;
        }
        System.out.println("adjust" + Arrays.toString(h));
        return h;
    }
}
