package DataStructure;

import java.util.Arrays;

public class ShortestPath {

    public static double[] dijkstra(DriectGraph graph, Object v) {
        int n = graph.numberOfVertices();
        //维护一个最短路径数组
        boolean S[] = new boolean[n];
        double dist[] = new double[n];
        int pos = graph.getVertixPos(v);
        //1、初始化顶点集和路径长度
        for (int i = 0; i < n; i++) {
            if (i == pos) {
                S[i] = true;
                dist[i] = 0;
            } else {
                S[i] = false;
                dist[i] = graph.getWeight(v, graph.getValue(i));
            }
        }
        //每次求出一个顶点的最短路径
        for (int i = 0; i < n - 1; i++) {
            double min = 65536;
            //2、计算未在最短路径顶点集中的最短的路径，加入最短路径集合
            for (int j = 0; j < n; j++) {
                if (S[j] == false && dist[j] < min) {
                    pos = j;
                    min = dist[j];
                }
            }
            S[pos] = true;
            //3、根据刚求出的最短路径的顶点来修改剩余不在最短路径集合中节点的路径。
            for (int k = 0; k < n; k++) {
                double w = graph.getWeight(graph.getValue(pos), graph.getValue(k));
                if (S[k] == false && w < 65536 && dist[pos] + w < dist[k]) {
                    dist[k] = dist[pos] + w;
                }
            }
        }
        return dist;
    }

    public static double[] bellman_Ford(DriectGraph graph, Object v) {
        int n = graph.numberOfVertices();
        double dist[] = new double[n];
        int pos = graph.getVertixPos(v);
//        1、初始化dist数组，也是经过一条边的最短路径的长度。
        for (int i = 0; i < n; i++) {
            if (i == pos) dist[i] = 0;
            else dist[i] = graph.getWeight(v, graph.getValue(i));
        }

        DriectGraph.Vertex[] nodeTable = graph.getNodeTable();
        //2、经过k条边的最短路径的长度，k<=n-1;
        for (int k = 2; k < n; k++) {
            //对每条边进行松弛操作，这里由邻接表对每个顶点的邻接表操作。
            for (int i = 0; i < n; i++) {
                if (i != pos) {
                    DriectGraph.Edge edge = nodeTable[i].head;
                    while (edge != null) {
                        double cost = edge.cost;
                        int dest = edge.dest;
                        if (dist[i] != 65536 && dist[i] + cost < dist[dest]) dist[dest] = dist[i] + cost;
                        edge = edge.next;
                    }
                }
            }
        }
        return dist;
    }

    public static double[][] floyd(DriectGraph graph) {
        int n = graph.numberOfVertices();
        double[][] dist = new double[n][n];
        //初始化最短路径矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = graph.getWeight(graph.getValue(i), graph.getValue(j));
            }
        }
        //每次试图在当前最短路径中加入第k个节点来松弛，这里k表示第k个节点，在尝试过所有节点后就完成了整个过程。
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }


    public static void main(String[] args) {
        DriectGraph<Character> graph = new DriectGraph<>(5);
        graph.insertVertex('a');
        graph.insertVertex('b');
        graph.insertVertex('c');
        graph.insertVertex('d');
        graph.insertVertex('e');
        graph.insertEdge('a', 'b', 10);
        graph.insertEdge('a', 'e', 100);
        graph.insertEdge('a', 'd', 30);
        graph.insertEdge('b', 'c', 50);
        graph.insertEdge('c', 'e', 10);
        graph.insertEdge('d', 'c', 20);
        graph.insertEdge('d', 'e', 60);
        double[] as = dijkstra(graph, 'a');
        System.out.println(Arrays.toString(as));

        DriectGraph<Character> graph1 = new DriectGraph<>(7);
        graph1.insertVertex('a');
        graph1.insertVertex('b');
        graph1.insertVertex('c');
        graph1.insertVertex('d');
        graph1.insertVertex('e');
        graph1.insertVertex('f');
        graph1.insertVertex('g');
        graph1.insertEdge('a', 'b', 6);
        graph1.insertEdge('a', 'c', 5);
        graph1.insertEdge('a', 'd', 5);
        graph1.insertEdge('b', 'e', -1);
        graph1.insertEdge('c', 'b', -2);
        graph1.insertEdge('c', 'e', 1);
        graph1.insertEdge('d', 'c', -2);
        graph1.insertEdge('d', 'f', -1);
        graph1.insertEdge('e', 'g', 3);
        graph1.insertEdge('f', 'g', 3);

        double[] as1 = bellman_Ford(graph1, 'a');
        System.out.println(Arrays.toString(as1));

        DriectGraph<Character> graph2 = new DriectGraph<>(4);
        graph2.insertVertex('a');
        graph2.insertVertex('b');
        graph2.insertVertex('c');
        graph2.insertVertex('d');
        graph2.insertEdge('a', 'b', 1);
        graph2.insertEdge('a', 'd', 4);
        graph2.insertEdge('b', 'd', 2);
        graph2.insertEdge('b', 'c', 9);
        graph2.insertEdge('d', 'c', 6);
        graph2.insertEdge('c', 'a', 3);
        graph2.insertEdge('c', 'b', 5);
        graph2.insertEdge('c', 'd', 8);

        double[][] floyd = floyd(graph2);
        for (double[] a : floyd) {
            System.out.println(Arrays.toString(a));
            System.out.println();
        }
    }

}
