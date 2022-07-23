package practiceproblems;

/**
 * https://forum.letstalkalgorithms.com/t/game-of-life/516/2
 * <p>
 * https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {
    // all eight possible directions
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    private static final int ALIVE = 1;
    private static final int DEAD = 0;
    private static final int DEAD_TO_ALIVE = 2;
    private static final int ALIVE_TO_DEAD = 3;

    public void gameOfLife(int[][] board) {
        // iterate through every cell in this 2D array
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                // keep track of the number of alive neighbors
                int alive = 0;

                // for each cell, check all possible 8 directions and count the number of alive neighbors
                for (int[] direction : directions) {
                    alive += isAlive(board, r + direction[0], c + direction[1]) ? 1 : 0;
                }

                // in case current cell is dead but has 3 live neighbors
                if (board[r][c] == DEAD) {
                    if (alive == 3) {

                        //Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
                        board[r][c] = DEAD_TO_ALIVE;
                    }
                }
                // in case current cell is alive
                else {
                    //Any live cell with fewer than two live neighbors dies as if caused by under-population.
                    //Any live cell with more than three live neighbors dies, as if by over-population.
                    //Any live cell with two or three live neighbors lives on to the next generation.
                    // in case, only 2 or 3 neighbors are alive
                    if (alive != 2 && alive != 3) {
                        board[r][c] = ALIVE_TO_DEAD;
                    }
                }
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == DEAD_TO_ALIVE) {
                    board[r][c] = ALIVE;
                } else if (board[r][c] == ALIVE_TO_DEAD) {
                    board[r][c] = DEAD;
                }
            }
        }
    }

    private boolean isAlive(int[][] board, int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board[0].length && (board[r][c] == ALIVE || board[r][c] == ALIVE_TO_DEAD);
    }

}