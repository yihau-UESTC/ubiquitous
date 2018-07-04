package alginterview;

import org.junit.Test;

public class LeetCode37 {
    //分别用来标记行、列、子box中某个数是否存在
    private boolean[][] row, col, box;

    public void solveSudoku(char[][] board) {
        row = new boolean[9][9];
        col = new boolean[9][9];
        box = new boolean[9][9];
        //初始化row、col、box
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - 48;
                    row[i][num - 1] = true;
                    col[j][num - 1] = true;
                    box[getBox(i, j)][num - 1] = true;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    putNum(board, i, j);
                }
            }
        }
    }

    private boolean putNum(char[][] board, int x, int y) {
        int i = x, j = y;
        for (; i < 9; i++) {
            for (; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (int k = 1; k < 10; k++) {
                        //在三个数组中判断是否符合要求
                        if (!row[i][k - 1] && !col[j][k - 1] && !box[(getBox(i, j))][k - 1]) {
                            board[i][j] = (char) (48 + k);
                            row[i][k - 1] = true;
                            col[j][k - 1] = true;
                            box[getBox(i, j)][k - 1] = true;
                            //如果递归的下层返回了true，说明需要回退。
                            if (!putNum(board, i, j)) {
                                board[i][j] = '.';
                                row[i][k - 1] = false;
                                col[j][k - 1] = false;
                                box[getBox(i, j)][k - 1] = false;
                            } else {
                                return true;
                            }
                        }
                    }
                    //每个数都不符合要求需要回退
                    return false;
                }
            }
            //这里很重要，j到头后，i++,如果不把j重置，那么会跳过i+1行的[0,y)的位置
            j = 0;
        }
        //所有的‘.’都填完了成功返回
        return true;
    }

    private int getBox(int x, int y) {
        return (x / 3) * 3 + (y / 3);
    }


    @Test
    public void run() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };
        solveSudoku(board);
        System.out.println(board.toString());
    }
}
