package graph.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 *
 * tricky flood fill variant
 *
 * The matrix is the continent with water on it and the boundaries are the oceans. Left and top being Pacific and right and bottom being the Atlantic.
 * The water on the continent (in the matrix) wants to flow out in the ocean. (Nature huh.)
 * The numbers in the matrix is the height of the water for that point.
 * For every point you have to ask the question. Can the water at this point and this height flow out in both the oceans
 * under the constraints of flowing through only four(up, down, right, left) directions and flow into channels with same height or less height?
 * If yes you return the coordinate of that point. Else you ignore it.
 */
public class PacificAtlantic {

    /**
     * Now, if we start from the cells connected to altantic ocean and
     * visit all cells having height greater than current cell (water can only flow from a cell to another one with height equal or lower),
     * we are able to reach some subset of cells (let's call them A).
     *
     * Next, we start from the cells connected to pacific ocean and repeat the same process, we find another subset (let's call this one B).
     *
     * The final answer we get will be the intersection of sets A and B (A ∩ B).
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        List<List<Integer>> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        //We create a new boolean[][] matrix like above, all the beaches is marked as True (1) in the beginning, which means they can connect to the ocean,
        // then we explore from the beach to find out all the paths. The idea is the same for Pacific and Atlantic.


        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m - 1);
        }
        for (int i = 0; i < m; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n - 1, i);
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (pacific[i][j] && atlantic[i][j])
                    res.add(Arrays.asList(i, j));
        return res;
    }

    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y) {
        int n = matrix.length, m = matrix[0].length;
        // since we start from ocean we check increasing height condition matrix[x][y] < height
        // the problem asks decreasing height condition so that water can flow
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || matrix[x][y] < height)
            return;
        visited[x][y] = true;
        for (int[] d : dir) {
            dfs(matrix, visited, matrix[x][y], x + d[0], y + d[1]);
        }
    }
}
