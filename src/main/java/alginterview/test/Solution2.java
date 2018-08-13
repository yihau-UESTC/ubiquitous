package alginterview.test;

import DataStructure.DriectGraph;

import java.util.*;

public class Solution2 {
    public static class Node {
        int nodeNum;
        int latency;
        List<Node> linkNode;

        public Node(int nodeNum, int latency) {
            this.nodeNum = nodeNum;
            this.latency = latency;
        }
    }

    public static void method(List<Node> list, int n) {
        Stack<Node> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        int[][] max = new int[n][2];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            max[i][0] = list.get(i).latency;
            max[i][1] = 1;
        }
        stack.push(list.get(0));
        while (!stack.empty()) {
            Node node = stack.pop();
            int idx = node.nodeNum;
            visited[idx] = true;
            List<Node> linkNode = node.linkNode;
            if (linkNode != null) {
                for (int i = 0; i < linkNode.size(); i++) {
                    Node link = linkNode.get(i);
                    if (!visited[link.nodeNum]) {
                        stack.add(link);
                        if (link.latency + max[idx][0] > max[link.nodeNum][0]) {
                            max[link.nodeNum][0] = link.latency + max[idx][0];
                            max[link.nodeNum][1] = max[idx][1] + 1;
                        }
                    }
                }
            }
        }
        int maxL = 0;
        for (int i = 0; i < n; i++)
            maxL = Math.max(maxL, max[i][0]);
        System.out.println(maxL);
    }


    static int calculate(Integer[] x, Integer[] y) {
        int num = x.length;
        int[][] dist = new int[num + 1][num];
        for (int i = 0; i < num; i++) {//求各点距离 
            for (int j = 0; j < num; j++) {
                if (i != 0)
                    dist[i][j] = Math.abs(x[j] - x[i]) + Math.abs(y[j] - y[i]);
                else
                    dist[i][j] = Math.abs(x[j]) + Math.abs(y[j]);
            }
        }
        int min = dist[0][0];
        for (int i = 0; i < num - 1; i++)
            min += dist[i + 2][i];
        for (int j = 0; j < num; j++) {//求最短距离 
            int temp = 0;
            temp += dist[0][j];
            for (int i1 = 1; i1 < num && i1 != j; i1++) {
                temp += dist[i1][j];
                for (int i2 = 1; i2 < num && i2 != j && i2 != i1; i2++) {
                    temp += dist[i2][i1];
                    for (int i3 = 1; i3 < num && i3 != j && i3 != i1 && i3 != i2; i3++) {
                        temp += dist[i3][i2];
                        if (temp < min) min = temp;
                    }
                }
            }
        }
        return min;
    }
}
