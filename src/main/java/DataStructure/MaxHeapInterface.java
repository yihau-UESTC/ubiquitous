package DataStructure;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午4:56 17-10-27
 * @Description:
 */
public interface MaxHeapInterface<T extends Comparable <? super T>>{
    public void add(T newEntry);
    public T removeMax();
    public T getMax();
    public int getSize();
    public void clear();
}
