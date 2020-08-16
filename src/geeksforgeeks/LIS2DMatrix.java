package geeksforgeeks;

/**
 * DFS + Memoization
 * <p>
 * Traverse all points in matrix, use every point as starting point to do dfs traversal. DFS function returns max increasing
 * path after comparing four max return distance from four directions.
 */

class LIS2DMatrix {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {

        if (matrix.length == 0) return 0;
        // i+1,j, i-1,j i,j+1 i,j-1
        Integer[][] cache = new Integer[matrix.length][matrix[0].length];
        //Arrays.fill(cache,-1);
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

        int max = 1; // every element is an answer to itself

        for (int[] dir : dirs) {

            int x = i + dir[0];
            int y = j + dir[1];

            int count = 1 + dfsUtil(matrix, x, y, cache, matrix[i][j]);
            max = Math.max(count, max);
        }
        cache[i][j] = max;

        return cache[i][j];

    }


}