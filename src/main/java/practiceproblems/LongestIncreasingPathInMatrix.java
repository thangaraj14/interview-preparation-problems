package practiceproblems;

/**
 * tricky dfs
 *
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix
 * <p>
 * Do DFS from every cell
 * Compare every 4 direction and skip cells that are out of boundary or smaller
 * Get matrix max from every cell's max
 * Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
 * The key is to cache the distance because it's highly possible to revisit a cell
 * <p>
 * DFS + Memoization
 * <p>
 * Traverse all points in matrix, use every point as starting point to do dfs traversal. DFS function returns max increasing
 * path after comparing four max return distance from four directions.
 */
public class LongestIncreasingPathInMatrix {

    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        Integer[][] cache = new Integer[matrix.length][matrix[0].length];

        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result = Math.max(dfsUtil(matrix, i, j, cache, Integer.MIN_VALUE), result);
            }
        }

        return result;
    }

    public int dfsUtil(int[][] matrix, int i, int j, Integer[][] cache, int data) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || data >= matrix[i][j]) return 0;


        if (cache[i][j] != null) return cache[i][j];

        // initialize max distance as 1 since the path includes starting point itself
        int max = 1;

        for (int[] dir : dirs) {

            int x = i + dir[0];
            int y = j + dir[1];
            // if next point is a valid point, add curLen by 1 and continue DFS traversal
            int count = 1 + dfsUtil(matrix, x, y, cache, matrix[i][j]);
            max = Math.max(count, max);
        }
        // update max increasing path value starting from current point in cache
        cache[i][j] = max;

        return cache[i][j];

    }
}
