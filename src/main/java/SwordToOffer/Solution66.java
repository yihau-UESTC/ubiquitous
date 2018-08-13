package SwordToOffer;

import org.junit.Test;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 * 二维平面中回溯法的常规操作
 * 注意机器人是从0,0开始的，只有这一个起始位置。
 */
public class Solution66 {
    private boolean[][] visited;
    private int count;
    private int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int movingCount(int threshold, int rows, int cols) {
        visited = new boolean[rows][cols];

        if (isValid(0, 0, threshold, rows, cols) && !visited[0][0])
            nextMove(0, 0, threshold, rows, cols);

        return count;
    }

    private void nextMove(int x, int y, int k, int rows, int cols) {
        count++;
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + move[i][0];
            int newY = y + move[i][1];
            if (isValid(newX, newY, k, rows, cols) && !visited[newX][newY]) {
                nextMove(newX, newY, k, rows, cols);
            }
        }
    }

    private boolean isValid(int x, int y, int k, int rows, int cols) {
        if (x < 0 || x >= rows || y < 0 || y >= cols)
            return false;
        int sum = 0, num = x;
        while (num != 0) {
            sum += num % 10;
            num = num / 10;
        }
        num = y;
        while (num != 0) {
            sum += num % 10;
            num = num / 10;
        }
        return sum <= k;
    }

    @Test
    public void run() {
        System.out.println(movingCount(10, 1, 100));
    }
}
