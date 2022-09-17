package dynamicProgramming.matrix;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-falling-path-sum/
 */
public class MinimumFallingPathMatrix {

    public static int minFallingPathSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 1; i < m; i += 1) {
            for (int j = 0; j < n; j += 1) {
                int min = matrix[i - 1][j];
                if (j > 0) {
                    min = Math.min(min, matrix[i - 1][j - 1]);
                }

                if (j < n - 1) {
                    min = Math.min(min, matrix[i - 1][j + 1]);
                }

                matrix[i][j] += min;
            }
        }

        return Arrays.stream(matrix[m - 1]).min().getAsInt();
    }

    public static void main(String[] args) {
        minFallingPathSum(new int[][]{{2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}});
    }
}
