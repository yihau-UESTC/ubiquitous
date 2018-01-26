package DataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图的实现类使用的是邻接矩阵的存储形式，也可以使用邻接表
 * @param <T> 节点类
 */
public class Graphmtx<T> implements Graph{
    private static final int MAX = 65536;//定义边权的最大值
    private int maxVertices;
    private int numEdges;
    private int numVertices;
    private List<T> verticesList;//顶点列表
    private double[][] edges;//邻接矩阵

    public Graphmtx(int sz){
        this.maxVertices = sz;
        this.numEdges = 0;
        this.numVertices = 0;
        this.verticesList = new ArrayList<T>(sz);
        this.edges = new double[sz][sz];//初始化邻接矩阵
        for (int i = 0; i < sz; i++){
            for (int j = 0; j < sz; j++){
                if (i == j)this.edges[i][j] = 0;
                else this.edges[i][j] = MAX;
            }
        }
    }

    public boolean isEmpty() {
        return this.numEdges == 0 ? true : false;
    }

    public double[][] getEdges(){
        return this.edges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVertices; i++){
            sb.append(verticesList.get(i));
            sb.append(" ");
            for (int j = 0; j < numVertices; j++){
                sb.append(edges[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int numberOfVertices() {
        return this.numVertices;
    }

    public int numberOfEdges() {
        return this.numEdges;
    }

    public Object getValue(int i) {
        if (i >= this.maxVertices)throw new IllegalArgumentException("arg is outOfIndex");
        return this.verticesList.get(i);
    }

    public double getWeight(Object t1, Object t2) {
        int v1 = getVerticesPos((T) t1);
        int v2 = getVerticesPos((T) t2);
        if (v1 < 0 || v1 > this.maxVertices || v2 < 0 || v2 > this.maxVertices)throw new IllegalArgumentException("arg is outOfIndex");
        return this.edges[v1][v2];
    }
    /*
    返回t节点在邻接矩阵中的下一个邻接顶点
     */
    public T getFirstNeighbor(Object t) {
        int v = getVerticesPos((T) t);
        if (v > -1 && v < this.maxVertices){
            for (int col = 0; col < numVertices; col++){
                if (this.edges[v][col] > 0 && this.edges[v][col] < MAX) return this.verticesList.get(col);
            }
        }
        return null;
    }

    /**
     * 返回t1在邻接矩阵中t2之后的下一个邻接顶点
     * @param t1
     * @param t2
     * @return
     */
    public T getNextNeighbor(Object t1, Object t2) {
        int v1 = getVerticesPos((T) t1);
        int v2 = getVerticesPos((T) t2);
        if (v1 != -1 && v2 != -1){
            for (int col = v2 + 1; col < numVertices; col++){//在邻接矩阵中v2之后 的下个邻接顶点。
                if (edges[v1][col] > 0 && edges[v1][col] < MAX)return verticesList.get(col);
            }
        }
        return null;
    }

    /**
     * 插入顶点
     * @param vertex
     * @return
     */
    public boolean insertVertex(Object vertex) {
        if (this.numVertices == this.maxVertices)return false;
        this.verticesList.add((T) vertex);
        this.numVertices++;
        for (int i = 0; i < numVertices - 1; i++){
            edges[i][numVertices - 1] = edges[numVertices - 1][i] = MAX;
        }
        edges[numVertices - 1][numVertices - 1] = 0;
        return true;
    }

    /**
     * 插入边
     * @param t1
     * @param t2
     * @param cost
     * @return
     */
    public boolean insertEdge(Object t1, Object t2, double cost) {
        int v1 = getVerticesPos((T) t1);
        int v2 = getVerticesPos((T) t2);
        if (v1 > -1 && v1 < numVertices && v2 > -1 && v2 < numVertices && edges[v1][v2] == MAX){
            edges[v1][v2] = edges[v2][v1] = cost;
            numEdges++;
            return true;
        }
        return false;
    }

    /**
     * 移除顶点t
     * @param t
     * @return
     */
    public boolean removeVertex(Object t) {
        int v = getVerticesPos((T) t);
        if (v < 0 || v > numEdges)return false;
        if (numVertices == 1)return false;
        verticesList.set(v, verticesList.get(numVertices - 1));//最后 一个顶点覆盖移除的顶点
        verticesList.remove(numVertices - 1);//在顶点表中删除最后一个顶点
        for (int i = 0; i < numVertices; i++){//删除与t相关的边。
            if (edges[i][v] > 0 && edges[i][v] < MAX)numEdges--;
        }
        for (int i = 0; i < numVertices; i++){//最后一列覆盖删除的点所在的列
            edges[i][v] = edges[i][numVertices - 1];
        }
        numVertices--;//这个必须在这里，为了保证对角线上的元素为0.
        for (int i = 0; i < numVertices; i++){//最后一行覆盖删除的点所在的列
            edges[v][i] = edges[numVertices][i];
        }
        return true;
    }

    /**
     * 移除边
     * @param t1
     * @param t2
     * @return
     */
    public boolean removeEdge(Object t1, Object t2) {
        int v1 = getVerticesPos((T) t1);
        int v2 = getVerticesPos((T) t2);
        if (v1 > -1 && v1 < numVertices && v2 > -1 && v2 < numVertices && edges[v1][v2] > 0 && edges[v1][v2] < MAX){
            edges[v1][v2] = edges[v2][v1] = MAX;
            numEdges--;
            return true;
        }
        else return false;
    }

    public int getVerticesPos(T vertice){
        return this.verticesList.indexOf(vertice);
    }

    /**
     * 图的深度优先遍历，使用的是递归和回溯的方法
     * @param t
     */
    public void DFS(Object t){
        int v = getVerticesPos((T) t);
        int n = this.numVertices;
        boolean[] visited = new boolean[n];
        for (boolean b:visited)b = false;
        DFSInit(v,visited);
    }

    /**
     * 深度优先遍历的递归体
     * @param v
     * @param visited
     */
    private void DFSInit(int v, boolean[] visited) {
        System.out.println(verticesList.get(v));
        visited[v] = true;
        int w = getVerticesPos(getFirstNeighbor(verticesList.get(v)));
        while (w != -1){
            if (visited[w] == false)DFSInit(w, visited);
            w = getVerticesPos(getNextNeighbor(verticesList.get(v),verticesList.get(w)));
        }
    }

    /**
     * 图的广度优先遍历，逐层访问的方法，使用到队列。
     * @param t
     */
    public void BFS(Object t){
        int v = getVerticesPos((T) t);
        int n = numVertices;
        boolean[] visited = new boolean[n];
        for (boolean b: visited)b = false;
        System.out.println(verticesList.get(v));
        visited[v] = true;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(v);
        while (!q.isEmpty()){
            int current = q.poll();
            int w = getVerticesPos(getFirstNeighbor(verticesList.get(current)));
            while (w != -1){
                if (visited[w] == false){
                    System.out.println(verticesList.get(w));
                    visited[w] = true;
                    q.add(w);
                }
                w = getVerticesPos(getNextNeighbor(verticesList.get(current), verticesList.get(w)));
            }
        }
    }
}
