package DataStructure;

import java.util.Arrays;
import java.util.HashMap;

public class DriectGraph<T> implements Graph {

    private Vertex<T>[] nodeTable;
    private int numEdges;
    private int numVertices;
    private int currentVertices;

    public DriectGraph(int numVertices) {
        this.numVertices = numVertices;
        this.currentVertices = 0;
        this.numEdges = 0;
        this.nodeTable = new Vertex[numVertices];
    }

    public Vertex<T>[] getNodeTable() {
        return nodeTable;
    }

    @Override
    public boolean isEmpty() {
        return numVertices == 0;
    }

    @Override
    public int numberOfVertices() {
        return this.numVertices;
    }

    @Override
    public int numberOfEdges() {
        return this.numEdges;
    }

    @Override
    public Object getValue(int i) {
        return nodeTable[i].data;
    }

    public int getVertixPos(Object v) {
        for (int i = 0; i < numVertices; i++) {
            if (v.equals(nodeTable[i].data)) return i;
        }
        return -1;
    }

    @Override
    public double getWeight(Object v1, Object v2) {
        int pos = getVertixPos(v1);
        int v2pos = getVertixPos(v2);
        if (pos != -1) {
            Edge edge = nodeTable[pos].head;
            while (edge != null) {
                if (edge.dest == v2pos) {
                    return edge.cost;
                } else {
                    edge = edge.next;
                }
            }
        }
        return 65536;
    }

    @Override
    public Object getFirstNeighbor(Object v) {
        int pos = getVertixPos(v);
        if (pos != -1) {
            Edge edge = nodeTable[pos].head;
            if (edge != null) {
                return getValue(edge.dest);
            }
        }
        return null;
    }

    @Override
    public Object getNextNeighbor(Object v1, Object v2) {
        int pos = getVertixPos(v1);
        int v2pos = getVertixPos(v2);
        if (pos != -1) {
            Edge edge = nodeTable[pos].head;
            while (edge != null) {
                if (edge.dest == v2pos) {
                    Object result = getValue(edge.next.dest);
                    return result;
                } else {
                    edge = edge.next;
                }
            }
        }
        return null;
    }

    @Override
    public boolean insertVertex(Object vertex) {
        nodeTable[currentVertices] = new Vertex(vertex, null);
        currentVertices++;
        return true;
    }

    @Override
    public boolean insertEdge(Object v1, Object v2, double cost) {
        int pos = getVertixPos(v1);
        int posv2 = getVertixPos(v2);
        if (pos != -1) {
            Edge edge = nodeTable[pos].head;
            if (edge == null) nodeTable[pos].head = new Edge(posv2, cost, null);
            else {
                Edge next = null;
                while ((next = edge.next) != null) {
                    edge = next;
                }
                edge.next = new Edge(posv2, cost, null);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeVertex(Object v) {
        return false;
    }

    @Override
    public boolean removeEdge(Object v1, Object v2) {
        return false;
    }

    public static class Edge {
        int dest;
        double cost;
        Edge next;

        public Edge(int dest, double cost, Edge next) {
            this.dest = dest;
            this.cost = cost;
            this.next = next;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public Edge getNext() {
            return next;
        }

        public void setNext(Edge next) {
            this.next = next;
        }
    }

    public static class Vertex<T> {
        T data;
        Edge head;

        public Vertex(T data, Edge head) {
            this.data = data;
            this.head = head;
        }
    }


    public static void main(String[] args) {
        DriectGraph<Character> graph = new DriectGraph<>(5);
        graph.insertVertex('A');
        graph.insertVertex('B');
        graph.insertVertex('C');
        graph.insertVertex('D');
        graph.insertVertex('E');
        graph.insertEdge('A', 'B', 1);
        graph.insertEdge('A', 'D', 1);
        graph.insertEdge('B', 'C', 1);
        graph.insertEdge('C', 'D', 1);
        graph.insertEdge('D', 'E', 1);
        graph.insertEdge('E', 'A', 1);
        System.out.println();


    }

}
