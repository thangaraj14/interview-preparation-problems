package dynamicProgramming;

import java.util.Arrays;

/**
 * Freshworks interview question:
 *  Miner can start at any of the i,j in first column
 *  miner can move in right, right upper diagonal and right lower diagonal
 */
public class MaxGoldAMinerCanGet {

    public static int getMaxGoldMinerCanGet(int[][] matrix) {
        if (matrix.length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int result = -1;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int left = matrix[i][j - 1];
                int leftUpperDiagonal = i - 1 >= 0 ? matrix[i - 1][j - 1] : 0;
                int leftLowerDiagonal = i + 1 < m ? matrix[i + 1][j - 1] : 0;

                matrix[i][j] = Math.max(left, Math.max(leftUpperDiagonal, leftLowerDiagonal)) + matrix[i][j];
                result = Math.max(result, matrix[i][j]);
            }
        }

        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println(getMaxGoldMinerCanGet(new int[][]{{1, 3, 1, 5},
                {2, 2, 4, 1},
                {5, 0, 2, 3},
                {0, 6, 1, 2}}
        ));
        System.out.println(getMaxGoldMinerCanGet(new int[][]{{1, 3, 3},
                {2, 1, 4},
                {0, 6, 4}}
        ));
    }
}
