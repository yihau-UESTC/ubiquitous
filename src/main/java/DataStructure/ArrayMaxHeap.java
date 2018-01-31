package DataStructure;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午4:58 17-10-27
 * @Description:
 */
public class ArrayMaxHeap <T extends Comparable<? super T>> implements MaxHeapInterface<T>, java.io.Serializable{

    private T[] heap;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 25;

    public ArrayMaxHeap(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayMaxHeap(int initalCapacity){
        heap = (T[])new Comparable[initalCapacity];
        //这里不能直接new T[25];
        size = 0;
    }

    public ArrayMaxHeap(T[] entries){
        heap = (T[]) new Comparable[entries.length];
        size = entries.length;
        for (int i = 0; i < size; i ++){
            heap[i] = entries[i];
        }
        for (int index = (size - 2)/2; index >= 0; index --){
            reheap(index);
        }
    }
    /**
     * * @param null
     *@return:
     *@Date: 17-10-28
     *@Description: 建堆下滑调整算法
     *
     **/
    private void reheap(int rootIndex) {
        boolean done = false;
        T orphan = heap[rootIndex];
        int largeChildIndex = 2 * rootIndex + 1;
        while (!done && (largeChildIndex < size)){
            int leftChildIndex = largeChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < size && (heap[largeChildIndex].compareTo(heap[rightChildIndex]) < 0)){
                largeChildIndex = rightChildIndex;
            }
            if (orphan.compareTo(heap[largeChildIndex]) < 0){
                heap[rootIndex] = heap[largeChildIndex];
                rootIndex = largeChildIndex;
                largeChildIndex = 2 * rootIndex + 1;
            }else {
                done = true;
            }
        }
        heap[rootIndex] = orphan;
    }

    /**
     * * @param null
     *@return:
     *@Date: 17-10-28
     *@Description: 插入过程是自下而上的调整堆，上滑过程。
     *
     **/
    @Override
    public void add(T newEntry) {
        size ++;
        if (size > heap.length)doubleArray();
        heap[size - 1] = newEntry;
        int j = size - 1, i = (j - 1) / 2;
        while (j > 0){
            if (heap[i].compareTo(newEntry) > 0)break;
            else {
                heap[j] = heap[i];
                j = i;
                i = (j - 1) / 2;
            }
        }
        heap[j] = newEntry;
    }

    private void doubleArray() {
        T[] oldHeap = heap;
        heap = (T[]) new Comparable[size * 2];
        for (int i = 0; i < oldHeap.length; i ++){
            heap[i] = oldHeap[i];
        }
        oldHeap = null;
    }

    @Override
    public T removeMax() {
        T root = null;
        if (!isEmpty()){
            root = heap[0];
            heap[0] = heap[size -1];
            size --;
            reheap(0);
        }
        return root;
    }

    private boolean isEmpty() {
        return size < 0;
    }

    @Override
    public T getMax() {
        T root = null;
        if (!isEmpty())root = heap[0];
        return root;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        for (; size > -1; size--){
            heap[size] = null;
        }
        size = 0;
    }
    /**
     *
     *@return:
     *@Date: 17-10-28
     *@Description: 根据层来打印出heap。
     *
     **/
    public void display(){
        int layer = 0;
        int num = (int) Math.pow(2,layer);
        int displayed = 0;
        int i = 0;
        while (true) {
            for (; i < num&&displayed < size - 1; i++) {
                System.out.print(heap[i] + " ");
                displayed ++;
            }
            System.out.println();
            if (displayed >= size-1)break;
            layer ++;
            num += (int) Math.pow(2,layer);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {1,3,5,7,2,4,8,9};
        ArrayMaxHeap<Integer> heap = new ArrayMaxHeap<>(a);
        System.out.println(heap);
        heap.add(15);
        heap.display();
        Integer i = heap.removeMax();
        System.out.println(i);
        System.out.println(heap.getSize());
        heap.display();
        heap.clear();
        heap.display();

    }
}
