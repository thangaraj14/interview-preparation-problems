package dsa;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralMatrixII {

    public static int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];

        if (n == 0) {
            return matrix;
        }

        // Normal Case
        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        int num = 1; // change

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = num++; // change
            }
            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                matrix[i][colEnd] = num++; // change
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    matrix[rowEnd][i] = num++; // change
                }
            }
            rowEnd--;

            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    matrix[i][colStart] = num++; // change
                }
            }
            colStart++;

            System.out.println(Arrays.deepToString(matrix));
        }
        return matrix;
    }

    public static void main(String[] args) {
        generateMatrix(4);
    }
}
