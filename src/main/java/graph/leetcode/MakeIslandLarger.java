package graph.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/making-a-large-island
 *
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 */
public class MakeIslandLarger {

    /**
     * Worst time is O(M*N)^2
     * this is same as island problem but here we need to change any one of the index
     * we change a water area to grid[i][j] = 1; and once calculated its size
     * we revert to  grid[i][j] = 0;
     * so for every i,j we check all the entries.
     *
     * @param grid
     * @return
     */
    public int largestIslandBruteForce(int[][] grid) {
        int result = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) continue;
                grid[i][j] = 1;
                result = Math.max(result, dfs(i, j, new boolean[m][n], grid));
                grid[i][j] = 0;
            }
        }

        return result == 0 ? m * n : result;
    }

    public int dfs(int i, int j, boolean[][] visited, int[][] grid) {
        if (i < 0 || i >= visited.length || j < 0 || j >= visited[0].length || visited[i][j] || grid[i][j] == 0)
            return 0;

        visited[i][j] = true;

        return 1 + dfs(i + 1, j, visited, grid) + dfs(i, j + 1, visited, grid) + dfs(i - 1, j, visited, grid) + dfs(i, j - 1, visited, grid);

    }

    /**
     * https://www.youtube.com/watch?v=_426VVOB8Vo&ab_channel=MichaelMuinos
     * time is O(M*N)
     * <p>
     * For each 1 in the grid, we paint all connected 1 with the next available color (2, 3, and so on).
     * We also remember the size of the island we just painted with that color.
     * <p>
     * Then, we analyze all 0 in the grid,
     * and sum sizes of connected islands (based on the island color).
     * Note that the same island can connect to 0 more than once.
     * The example below demonstrates this idea (the answer is highlighted):
     * input:
     * 0 1 0 1 0
     * 1 1 0 0 1
     * 0 0 1 1 0
     * <p>
     * transformed group: 2 is one group of island, 3 is one group, 4 is one group and 5 is another
     * the length of each group is stored in map
     * 0 2 0 3 0
     * 2 2 0 0 4
     * 0 0 5 5 0
     * <p>
     * we iterate the matrix back, whenever we find 0, we see all the neighbours group value
     * for example at index grid[0][2] we have 2 and 3 as neighbours, we add both of the group's length
     * to sum and add 1 to it because we are considering grid[0][2] is changed to land now
     * sum = 1+ 3(2's length) + 1 (3's length) => 5
     *
     * @param grid
     * @return
     */

    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> map = new HashMap<>(); //Key: color, Val: size of island painted of that color
        map.put(0, 0); //We won't paint island 0, hence make its size 0, we will use this value later
        int n = grid.length;
        int colorIndex = 2; //0 and 1 is already used in grid, hence we start colorIndex from 2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = paint(grid, i, j, colorIndex);
                    map.put(colorIndex, size);
                    colorIndex++;
                }
            }
        }

        //If there is no island 0 from grid, res should be the size of islands of first color
        //If there is no island 1 from grid, res should be 0
        int res = map.getOrDefault(2, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    //We use a set to avoid repeatedly adding islands with the same color
                    Set<Integer> set = new HashSet<>();
                    //If current island is at the boundary, we add 0 to the set, whose value is 0 in the map
                    set.add(i > 0 ? grid[i - 1][j] : 0);
                    set.add(i < n - 1 ? grid[i + 1][j] : 0);
                    set.add(j > 0 ? grid[i][j - 1] : 0);
                    set.add(j < n - 1 ? grid[i][j + 1] : 0);

                    int newSize = 1; //We need to count current island as well, hence we init newSize with 1
                    for (int color : set) newSize += map.get(color);
                    res = Math.max(res, newSize);
                }
            }
        }
        return res;
    }

    //Helper method to paint current island and all its connected neighbors
    //Return the size of all painted islands at the end
    private int paint(int[][] grid, int i, int j, int color) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) return 0;
        grid[i][j] = color;
        return 1 + paint(grid, i + 1, j, color) + paint(grid, i - 1, j, color) + paint(grid, i, j + 1, color) + paint(grid, i, j - 1, color);
    }
}
