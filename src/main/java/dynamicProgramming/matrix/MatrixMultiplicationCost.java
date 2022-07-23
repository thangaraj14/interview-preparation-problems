package dynamicProgramming.matrix;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
 * https://www.youtube.com/watch?v=vgLJZMUfnsU&t=316s
 */
public class MatrixMultiplicationCost {


    public static int matrixMultiplication(int[] arr, int N) {
        Integer[][] dp = new Integer[N + 1][N + 1];
        return recursionHelper(arr, 1, N - 1, dp);
    }

    public static int recursionHelper(int[] arr, int i, int j, Integer[][] dp) {
        if (i == j) return 0;
        if (dp[i][j] != null) return dp[i][j];
        int min = Integer.MAX_VALUE;
// Run a loop from 'i' to 'j' - 1 and calculate for all possible combination
        for (int k = i; k < j; k++) {
            min = Math.min(min, arr[i - 1] * arr[k] * arr[j] + recursionHelper(arr, i, k, dp) + recursionHelper(arr, k + 1, j, dp));
        }

        return dp[i][j] = min;
    }

    public static int matrixMultiplicationTabulation(int[] arr, int N) {
        int[][] dp = new int[N][N];

        for (int i = N - 1; i > 0; i--) {
            for (int j = i + 1; j < N; j++) {

                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    min = Math.min(min, arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j]);
                }
                dp[i][j] = min;
            }
        }

        return dp[1][N - 1];
    }
}