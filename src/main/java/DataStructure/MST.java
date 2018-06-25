package DataStructure;

import scala.Char;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MST {


    public static ArrayList<MSTEdgeNode<Character>> kruskal(Graphmtx<Character> graph) {
        int n = graph.numberOfVertices();
        int m = graph.numberOfEdges();
        ArrayList<MSTEdgeNode<Character>> result = new ArrayList<>();
        //使用优先级队列每次出队最小权值的边。
        PriorityQueue<MSTEdgeNode<Character>> mstEdgeNodes = new PriorityQueue<>(m);
        //使用并查集判断边的端点是否属于同一个连通分量，同一连通分量会构成环
        UFSets ufSets = new UFSets(n);
        //初始化优先级队列
        for (int i = 0; i < n; i++) {
            Character a = (Character) graph.getValue(i);
            for (int j = i + 1; j < n; j++) {
                Character b = (Character) graph.getValue(j);
                double cost = graph.getWeight(a, b);
                if (cost != 65536) {
                    mstEdgeNodes.add(new MSTEdgeNode(a, b, cost));
                }
            }
        }
        while (result.size() < n - 1) {
            //最小权值出队
            MSTEdgeNode<Character> node = mstEdgeNodes.poll();
            if (node != null) {
                int u = ufSets.find(graph.getVerticesPos(node.getHead()));
                int v = ufSets.find(graph.getVerticesPos(node.getTail()));
                //判断u和v是否处在同一连通分量中
                if (u != v) {
                    ufSets.union(u, v);
                    result.add(node);
                }
            }
        }
        return result;
    }

    public static ArrayList<MSTEdgeNode<Character>> prim(Graphmtx<Character> graph) {
        ArrayList<MSTEdgeNode<Character>> result = new ArrayList<>();
        int n = graph.numberOfVertices();
        int m = graph.numberOfEdges();
        //维护一个数组来保存已经加入最小值生成树中的节点
        boolean vmst[] = new boolean[n];
        for (int i = 0; i < n; i++) vmst[i] = false;
        vmst[0] = true;
        Character u = (Character) graph.getValue(0);
        PriorityQueue<MSTEdgeNode<Character>> queue = new PriorityQueue<>();
        while (result.size() < n - 1) {
            Character v = graph.getFirstNeighbor(u);
            //遍历现在的最小生成树集合，将符合要求的边加入队列
            while (v != null) {
                if (!vmst[graph.getVerticesPos(v)]) {
                    queue.add(new MSTEdgeNode<>(u, v, graph.getWeight(u, v)));
                }
                v = graph.getNextNeighbor(u, v);
            }

            while (queue.size() > 0) {
                MSTEdgeNode<Character> node = queue.poll();
                if (!vmst[graph.getVerticesPos(node.getHead())]) {
                    result.add(node);
                    u = node.getHead();
                    vmst[graph.getVerticesPos(u)] = true;
                    break;
                }
            }
        }

        return result;
    }

    public static class MSTEdgeNode<T> implements Comparable<MSTEdgeNode> {
        T tail;
        T head;
        double cost;

        public MSTEdgeNode(T tail, T head, double cost) {
            this.tail = tail;
            this.head = head;
            this.cost = cost;
        }

        public T getTail() {
            return tail;
        }

        public void setTail(T tail) {
            this.tail = tail;
        }

        public T getHead() {
            return head;
        }

        public void setHead(T head) {
            this.head = head;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "MSTEdgeNode{" +
                    "tail=" + tail +
                    ", head=" + head +
                    ", cost=" + cost +
                    '}';
        }

        @Override
        public int compareTo(MSTEdgeNode o) {
            return this.cost - o.cost > 0 ? 1 : this.cost - o.cost < 0 ? -1 : 0;
        }
    }

    public static void main(String[] args) {
        Graphmtx<Character> graph = new Graphmtx<Character>(7);
        graph.insertVertex('0');
        graph.insertVertex('1');
        graph.insertVertex('2');
        graph.insertVertex('3');
        graph.insertVertex('4');
        graph.insertVertex('5');
        graph.insertVertex('6');
        graph.insertEdge('0', '1', 28);
        graph.insertEdge('0', '5', 10);
        graph.insertEdge('1', '2', 16);
        graph.insertEdge('1', '6', 14);
        graph.insertEdge('2', '3', 12);
        graph.insertEdge('6', '3', 18);
        graph.insertEdge('4', '3', 22);
        graph.insertEdge('4', '6', 24);
        graph.insertEdge('4', '5', 25);
        ArrayList<MSTEdgeNode<Character>> kruskal = kruskal(graph);
        System.out.println(kruskal);

        ArrayList<MSTEdgeNode<Character>> prim = prim(graph);
        System.out.println(prim);
    }
}
