package geeksforgeeks;

import java.util.Arrays;

/**
 * https://forum.letstalkalgorithms.com/t/game-of-life/516/2
 * <p>
 * https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {

    int[][] c = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

    int die = 2;
    int live = 3;

    void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int neighbors = getLiveNeigborCount(board, i, j);
                if (board[i][j] == 0 && neighbors == 3) {
                    board[i][j] = live;
                } else if (board[i][j] == 1) {
                    if (neighbors == 2 || neighbors == 3) {
                        continue;
                    }
                    if (neighbors < 2 || neighbors > 3) {
                        board[i][j] = die;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == die) {
                    board[i][j] = 0;
                } else if (board[i][j] == live) {
                    board[i][j] = 1;
                }
            }
        }
    }

    int getLiveNeigborCount(int[][] board, int i, int j) {

        int count = 0;
        for (int m = 0; m < board.length; m++) {
            int mx = c[m][0] + i;
            int my = c[m][1] + j;
            if (mx >= 0 && my >= 0 && mx < board.length && my < board[0].length) {
                if (board[mx][my] == 1 || board[mx][my] == die) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife();
        int[][] arr = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
        System.out.println(Arrays.deepToString(arr));
        gol.gameOfLife(arr);

        System.out.println(Arrays.deepToString(arr));
    }
}