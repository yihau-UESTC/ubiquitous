package bishi.tp1;

import org.junit.Test;

import java.util.Scanner;

public class Main {
    private static int[][] board;
    private static boolean[][] visited;
    private static int[][] move = {{-1, 0}, {-1, 1}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {+1, -1}, {0, -1}, {-1, -1}};
    private static int m;
    private static int n;
    private static int p;
    private static int q;
    private static int count;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] split = s.split(",");
        m = Integer.valueOf(split[0]);
        n = Integer.valueOf(split[1]);
        board = new int[m][n];
        for (int i = 0; i < m; i++) {
            String s1 = scanner.nextLine();
            String[] split1 = s1.split(",");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.valueOf(split1[j]);
            }
        }
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] == 1) {
                    p += 1;
                    count = 0;
                    find(i, j);
                    q = Math.max(q, count);
                }
            }
        }
        System.out.println(p + "," + q);
    }

    private static void find(int x, int y) {
        visited[x][y] = true;
        count += 1;
        for (int i = 0; i < 8; i++) {
            int newX = x + move[i][0];
            int newY = y + move[i][1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && board[newX][newY] == 1) {
                find(newX, newY);
            }
        }
    }

    @Test
    public void run() {
        m = 10;
        n = 10;
        board = new int[][]
                {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                        {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}};
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] == 1) {
                    p += 1;
                    count = 0;
                    find(i, j);
                    q = Math.max(q, count);
                }
            }
        }
        System.out.println(p + "," + q);
    }

}
