package geeksforgeeks;

/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78308/15ms-Concise-Java-Solution
 */
public class LongestIncreasingPath {

    /**
     * DFS + Memoization
     * <p>
     * Traverse all points in matrix, use every point as starting point to do dfs traversal. DFS function returns max increasing
     * path after comparing four max return distance from four directions.
     *
     * @param cache: cache[i][j] represents longest increasing path starts from point matrix[i][j]
     * @param prev: previous value used by DFS traversal, to compare whether current value is greater than previous value
     */
    static final int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int result = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        Integer[][] cache = new Integer[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curLen = dfsUtil(matrix, i, j, cache, -1);
                result = Math.max(result, curLen);
            }
        }
        return result;
    }

    public static int dfsUtil(int[][] matrix, int i, int j, Integer[][] cache, int data) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || data >= matrix[i][j]) {
            return 0;
        }

        if (cache[i][j] != null) {
            return cache[i][j];
        }

        int max = 1;

        for (int[] dir : dirs) {

            int x = i + dir[0];
            int y = j + dir[1];

            int count = 1 + dfsUtil(matrix, x, y, cache, matrix[i][j]);
            max = Math.max(count, max);
        }
        cache[i][j] = max;

        return cache[i][j];
    }

    public static void main(String[] args) {
        int[][] arr = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
        System.out.println(longestIncreasingPath(arr));
    }
}
