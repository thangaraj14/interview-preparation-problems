package practiceproblems.recursion;

import java.util.Arrays;

/**
 * tricky recursion
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        solveBoard(board, 0, 0);
    }

    private boolean solveBoard(char[][] board, int currentRow, int currentCol) {
        for (int row = currentRow; row < 9; row++, currentCol = 0) {
            for (int col = currentCol; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveBoard(board, row, col + 1)) {
                                return true;
                            }
                            board[row][col] = '.';
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    // Make sure the digit doesn't exist in the current row, col or square.
    private boolean isValid(char[][] board, int row, int col, char num) {
        int regionRow = (row / 3) * 3;  //region start row
        int regionCol = (col / 3) * 3;  //region start col
        for (int i = 0; i < 9; i++) {
            //  [regionRow + i / 3][regionCol + i % 3] check 3*3 block
            if (board[i][col] == num || board[row][i] == num || board[regionRow + i / 3][regionCol + i % 3] == num) {
                return false;
            }
        }

        return true;
    }


    /**
     * start from 0 and go till 81 (9*9), for each cell do a dfs, if not backtrack and change the value
     *
     * @param board
     */
    public void solveSudokuDFS(char[][] board) {
        dfs(board, 0);
    }

    private boolean dfs(char[][] board, int d) {
        if (d == 81) return true; //found solution
        int i = d / 9, j = d % 9;
        if (board[i][j] != '.') return dfs(board, d + 1);//prefill number skip

        boolean[] flag = new boolean[10];
        validate(board, i, j, flag);
        for (int k = 1; k <= 9; k++) {
            if (flag[k]) {
                board[i][j] = (char) ('0' + k);
                if (dfs(board, d + 1)) return true;
            }
        }
        board[i][j] = '.'; //if you can not solve, in the wrong path, change back to '.' and out
        return false;
    }

    // in arr[0..9] fill the values with numbers in row and col as false
    private void validate(char[][] board, int i, int j, boolean[] flag) {
        Arrays.fill(flag, true);
        for (int k = 0; k < 9; k++) {
            if (board[i][k] != '.') flag[board[i][k] - '0'] = false;
            if (board[k][j] != '.') flag[board[k][j] - '0'] = false;
            int r = i / 3 * 3 + k / 3;
            int c = j / 3 * 3 + k % 3;
            if (board[r][c] != '.') flag[board[r][c] - '0'] = false;
        }
    }
}
