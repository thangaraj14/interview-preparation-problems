package dynamicProgramming.matrix;

class MaximumSquareDP {
    public int maximalSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        int result = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            result = Math.max(result, dp[0][i]);
        }
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            result = Math.max(result, dp[i][0]);
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') continue;

                dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                result = Math.max(result, dp[i][j]);
            }
        }

        return result * result;
    }

    public int maximalSquareRecursion(char[][] matrix) {
        Integer[][] cache = new Integer[matrix.length][matrix[0].length];
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    result = Math.max(result, dfs(matrix, cache, i, j));
                }
            }
        }

        return result * result;
    }

    public int dfs(char[][] matrix, Integer[][] cache, int i, int j) {
        if (i >= matrix.length || j >= matrix[0].length || i < 0 || j < 0 || matrix[i][j] == '0') return 0;

        if (cache[i][j] != null) return cache[i][j];

        cache[i][j] = 1 + Math.min(dfs(matrix, cache, i + 1, j), Math.min(dfs(matrix, cache, i + 1, j + 1), dfs(matrix, cache, i, j + 1)));
        return cache[i][j];
    }
}

