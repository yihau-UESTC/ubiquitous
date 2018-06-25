package DataStructure;

import java.util.Arrays;

public class UFSets {
    private int[] parent;
    private int size;

    public UFSets(int size) {
        this.size = size;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }
    }

    public int find(int x) {
        while (parent[x] >= 0) x = parent[x];
        return x;
    }

    public boolean union(int x1, int x2) {
        int r1 = find(x1);
        int r2 = find(x2);
        if (r1 != r2) {
            int temp = parent[r1] + parent[r2];
            if (parent[r2] < parent[r1]) {
                parent[r1] = r2;
                parent[r2] = temp;
            } else {
                parent[r2] = r1;
                parent[r1] = temp;
            }
            return true;
        }
        return false;
    }

    public void show() {
        System.out.println(Arrays.toString(parent));
    }

    public static void main(String[] args) {
        UFSets ufSets = new UFSets(10);
        ufSets.union(0, 6);
        ufSets.union(0, 7);
        ufSets.union(0, 8);
        ufSets.show();

    }
}
