package practiceproblems;

/**
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * https://www.youtube.com/watch?v=PwDqpOMwg6U&ab_channel=TusharRoy-CodingMadeSimple
 * +---------------+   +---------+----+   +---+-----------+   +---------+----+   +---+----------+
 * |               |   |         |    |   |   |           |   |         |    |   |   |          |
 * |   (r1,c1)     |   |         |    |   |   |           |   |         |    |   |   |          |
 * |   +------+    |   |         |    |   |   |           |   +---------+    |   +---+          |
 * |   |      |    | = |         |    | - |   |           | - |    (r1-1,c2) | + | (r1-1,c1-1)  |
 * |   |      |    |   |         |    |   |   |           |   |              |   |              |
 * |   +------+    |   +---------+    |   +---+           |   |              |   |              |
 * |        (r2,c2)|   |       (r2,c2)|   |   (r2,c1-1)   |   |              |   |              |
 * +---------------+   +--------------+   +---------------+   +--------------+   +--------------+
 */
public class RangeSum2D {
    int[][] dp;

    public RangeSum2D(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m + 1][n + 1];

        /**
         * +-----+-+-------+     +--------+-----+     +-----+---------+     +-----+--------+
         * |     | |       |     |        |     |     |     |         |     |     |        |
         * |     | |       |     |        |     |     |     |         |     |     |        |
         * +-----+-+       |     +--------+     |     |     |         |     +-----+        |
         * |     | |       |  =  |              |  +  |     |         |  -  |              |
         * +-----+-+       |     |              |     +-----+         |     |              |
         * |               |     |              |     |               |     |              |
         * |               |     |              |     |               |     |              |
         * +---------------+     +--------------+     +---------------+     +--------------+
         *
         *    sums[i][j]      =    sums[i-1][j]    +     sums[i][j-1]    -   sums[i-1][j-1]   +
         *
         *                         matrix[i-1][j-1]
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // converting to m+1,n+1 dp[][] index
        row1 += 1;
        row2 += 1;
        col1 += 1;
        col2 += 1;

        return dp[row2][col2] - dp[row2][col1 - 1] - dp[row1 - 1][col2] + dp[row1 - 1][col1 - 1];
    }

    /**
     * https://leetcode.com/problems/matrix-block-sum
     *
     * Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:
     *
     *     i - k <= r <= i + k,
     *     j - k <= c <= j + k, and
     *     (r, c) is a valid position in the matrix.
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1]; // sum[i][j] is sum of all elements from rectangle (0,0,i,j) as left, top, right, bottom corresponding
        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                sum[r][c] = sum[r - 1][c] + sum[r][c - 1] - sum[r - 1][c - 1] + mat[r - 1][c - 1];
            }
        }
        int[][] ans = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int r1 = Math.max(0, r - K);
                int c1 = Math.max(0, c - K);
                int r2 = Math.min(m - 1, r + K);
                int c2 = Math.min(n - 1, c + K);
                r1++;
                c1++;
                r2++;
                c2++; // Since `sum` start with 1 so we need to increase r1, c1, r2, c2 by 1
                ans[r][c] = sum[r2][c2] - sum[r2][c1 - 1] - sum[r1 - 1][c2] + sum[r1 - 1][c1 - 1];
            }
        }
        return ans;
    }
}

