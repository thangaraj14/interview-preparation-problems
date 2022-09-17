package graph.leetcode;

/**
 * https://leetcode.com/problems/bomb-enemy/
 */
public class BombEnemy {

    private int row;
    private int col;
    private int max = 0;
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        row = grid.length;
        col = grid[0].length;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (grid[i][j] == '0')
                    max = Math.max(max, bfs(grid, i, j));
        return max;
    }

    private int bfs(char[][] grid, int i, int j) {
        int count = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            while (x < row && y < col && x >= 0 && y >= 0 && grid[x][y] != 'W') {
                if (grid[x][y] == 'E')
                    count++;
                x = x + dir[0];
                y = y + dir[1];
            }
        }
        return count;

    }
}
