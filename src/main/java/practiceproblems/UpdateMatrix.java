package practiceproblems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/01-matrix/
 *
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 */
public class UpdateMatrix {

    /**
     * Naive BFS invoked multiple times (Slow)
     * i) Iterate over the matrix with a nested for-loop to find cells containing 1
     * ii) Apply BFS algo on those cells -> pass those cells to a BFS helper to find distance to closest 0
     * iii) update the matrix cell with the distance
     * <p>
     * Optimized BFS invoked only once (Fast)
     * Idea
     * <p>
     * Instead of invoking BFS for each land cell to see how far we can get away from that source, we flip the problem.
     * The flipped problem is to start from target (sea) and to figure our the closest source (land)
     * This allows us to run a single BFS search that emerges from different places
     * (all the targets aka all the zero cells) in the grid
     * <p>
     * Add all the targets (all zero cells) into the queue.
     * While you're at it, also mark those targets as visited (add to a visited set)
     * <p>
     * Run a single BFS on the pre-processed queue and investigate neighbours.
     * <p>
     * if neighbour cell has not been visited --> then it must bea land cell
     * (since all the sea cells have been marked visited):
     * append the neighbour cell into the queue and mutate the gird
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new ArrayDeque<>();
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int row = cur[0] + dir[i][0];
                int col = cur[1] + dir[i][1];
                if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) {
                    continue;
                }
                visited[row][col] = true;
                /**
                 * since, we popped out the cell (cell(0),cell(1)) and now looking at all its four adjacent cells
                 * and since we're sure that the cell has its minimum distance from zero ,
                 * so, in case any of its four cells value(calling it child cell)
                 * (which is child's distance from zero cell except when its Max Integer value)
                 * has value more than the {value in cell} + 1 ,
                 * we update the child cell to {value in cell} + 1 .. + 1 is used because
                 * when add +1 as going from a cell to next adjacent cell increases path by 1
                 */
                matrix[row][col] = matrix[cur[0]][cur[1]] + 1;
                queue.offer(new int[]{row, col});
            }
        }
        return matrix;
    }

    /**
     * In this problem, a cell has at most 4 neighbors that are left, top, right, bottom.
     * If we use dynamic programming to compute the distance of the current cell based on 4 neighbors simultaneously,
     * it's impossible because we are not sure if distance of neighboring cells is already computed or not
     *
     * For those who are asking why DP is done in two passes, in DP we can only use the values which are previously calculated.
     * When we are parsing from top left and coming down to bottom right,
     * we can only use the values of above and left because only those two values are precomputed,
     * if we take right and down, those values are not yet computed,
     * if we work with those values we will get suboptimal answer.
     */
    public int[][] updateMatrixDP(int[][] matrix) {
        int maxValue = matrix.length * matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) continue;

                int top = i - 1 >= 0 ? matrix[i - 1][j] : maxValue;
                int left = j - 1 >= 0 ? matrix[i][j - 1] : maxValue;

                matrix[i][j] = Math.min(top, left) + 1;
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                int bottom = i + 1 < matrix.length ? matrix[i + 1][j] : maxValue;
                int right = j + 1 < matrix[0].length ? matrix[i][j + 1] : maxValue;

                matrix[i][j] = Math.min(matrix[i][j], Math.min(bottom, right) + 1);
            }
        }

        return matrix;

    }
}
