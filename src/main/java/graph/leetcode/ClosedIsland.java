package graph.leetcode;

/**
 * https://leetcode.com/problems/number-of-closed-islands/
 */
public class ClosedIsland {

    public int closedIsland(int[][] grid) {

        int VISITED = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {
                    if (grid[i][j] == 0) {
                        dfs(grid, i, j, VISITED);
                    }
                }
            }
        }

        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 0) {
                    dfs(grid, i, j, VISITED);
                    result++;
                }
            }
        }

        return result;
    }

    public void dfs(int[][] grid, int i, int j, int VISITED) {
        if (i < 0 || j < 0 || j >= grid[0].length || i >= grid.length) return;

        if (grid[i][j] == 1 || grid[i][j] == VISITED) return;

        grid[i][j] = VISITED;

        dfs(grid, i + 1, j, VISITED);
        dfs(grid, i, j + 1, VISITED);
        dfs(grid, i - 1, j, VISITED);
        dfs(grid, i, j - 1, VISITED);
    }
}
