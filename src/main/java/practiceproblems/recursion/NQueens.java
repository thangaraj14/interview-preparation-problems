package practiceproblems.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 * <p>
 * This is a perfect problem for backtracking - place the queens one by one, and when all possibilities are exhausted,
 * backtrack by removing a queen and placing it elsewhere.
 */
public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        if (n == 0) return Collections.emptyList();

        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        nQueensHelper(0, n, board, result);

        return result;
    }

    public void nQueensHelper(int row, int n, char[][] board, List<List<String>> result) {

        if (row == board.length) {
            result.add(construct(board));
            return;
        }
        for (int i = 0; i < n; i++) {
            // check if it can be placed in current i(col) of the row and explore possibilities
            if (isValidPlacement(row, i, board)) {
                board[row][i] = 'Q'; // if yes proceed to next row and explore all possibilities
                nQueensHelper(row + 1, n, board, result);
                board[row][i] = '.';
            }

        }

    }

    public boolean isValidPlacement(int row, int col, char[][] chess) {
        // check all cols
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        int x = row;
        int y = col;
        //checking for upper left diagonal(non-main diagonal), row is decreasing and col too
        while (x >= 0 && y >= 0) {
            if (chess[x][y] == 'Q') {
                return false;
            }
            --x;
            --y;
        }

        //checking for main diagonal(upper right), row is decreasing and col increasing
        while (row >= 0 && col < chess.length) {
            if (chess[row][col] == 'Q') {
                return false;
            }
            --row;
            ++col;
        }
        return true;
    }

    private List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (char[] chars : chess) {
            path.add(new String(chars));
        }
        return path;
    }

}