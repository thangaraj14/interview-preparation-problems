package dsa;

/**
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 * <p>
 * Follow up:
 * <p>
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * https://leetcode.com/problems/set-matrix-zeroes/
 */
public class SetZeroes {

    public void setZeroes(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    setTheRowZero(matrix, i);
                    setTheColumnZero(matrix, j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == -22) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setTheRowZero(int[][] matrix, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            if (matrix[row][i] != 0) {
                matrix[row][i] = -22;
            }
        }
    }

    public void setTheColumnZero(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] != 0) {
                matrix[i][col] = -22;
            }
        }
    }
}
