package SwordToOffer;

import org.junit.Test;

public class Solution65 {
    private boolean[][] visited;
    private int rows;
    private int cols;
    private char[][] board;
    private String str;
    private int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int k = 0;
        board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = matrix[k++];
            }
        }
        visited = new boolean[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.str = String.valueOf(str);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (findPath(i, j, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean findPath(int x, int y, int index) {
        if (index == str.length() - 1) {
            return board[x][y] == str.charAt(index);
        }
        if (board[x][y] == str.charAt(index)) {
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int newX = x + move[i][0];
                int newY = y + move[i][1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols
                        && !visited[newX][newY]
                        && findPath(newX, newY, index + 1))
                    return true;
            }
            visited[x][y] = false;
        }
        return false;
    }

    @Test
    public void run() {
        String s = "ABCESFCSADEE";
        String p = "ABCCED";
        System.out.println(hasPath(s.toCharArray(), 3, 4, p.toCharArray()));
        ;
    }
}
