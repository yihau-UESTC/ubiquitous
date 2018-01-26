package DataStructure;

/**
 * 定义图的接口，包含了图的一些通用方法
 * @param <T>
 */
public interface Graph<T>{
    boolean isEmpty();//判断图是否空
    int numberOfVertices();//返回当前图的结点个数
    int numberOfEdges();//返回当前图边的个数
    T getValue(int i);//返回序号对应的节点
    double getWeight(T v1, T v2);//返回边对应的权值
    T getFirstNeighbor(T v);//返回v节点的下一个邻接顶点
    T getNextNeighbor(T v1, T v2);//返回v1在v2之后的下一个邻接顶点
    boolean insertVertex(T vertex);//插入一个顶点
    boolean insertEdge(T v1, T v2, double cost);//插入一条边
    boolean removeVertex(T v);//移除一个顶点
    boolean removeEdge(T v1, T v2);//移除一条边
}
