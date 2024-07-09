package backtracking;

/**
 * https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid/
 *
 * Brute force solution
 * DFS
 */
public class DistributeStonesInGrid {
    int ret = Integer.MAX_VALUE;
    public int minimumMoves(int[][] grid) {
        dfs(grid, 0);
        return ret;
    }

    /**
     * Iterate through all possible pairs of source (cell value > 1) and destination cells (cell value == 0)
     * in the grid to check if it's possible to move a stone from the source cell to the destination cell.
     *
     * Check if the Destination cell is empty (contains 0 stones) and if the Source cell contains more than 1 stone.
     * Decrease the number of stones in the Source by 1 and increase stones in the Destination cell by 1.
     * Calculate the Manhattan distance between the Source and the Destination.
     * Backtracking step: After exploration, restore the original grid configuration, i.e, reverse of step 2.
     * The count variable keeps track of the minimum number of moves needed to achieve the desired configuration.
     */
    private void dfs(int[][] grid, int move) {
        int zx = -1;
        int zy = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    zx = i;
                    zy = j;
                    break;

                }
            }
        }

        // this is the base case, because of the constraint
        if (zx == -1 && zy == -1) {
            ret = Math.min(ret, move);
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] > 1) {

                    grid[i][j] -= 1;
                    grid[zx][zy] += 1;

                    int dx = Math.abs(i - zx);
                    int dy = Math.abs(j - zy);

                    dfs(grid, move + dx + dy);

                    // the reason we reset is to try all combinations
                    // if the result changes, it means we have found a better solution
                    // line 28 will take care of updating the result
                    grid[i][j] += 1;
                    grid[zx][zy] -= 1;

                }
            }
        }

    }
}
