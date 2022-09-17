package practiceproblems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
public class ShortestPathBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] temp = queue.poll();
                if (temp[0] == grid.length - 1 && temp[1] == grid[0].length - 1) return steps + 1;
                for (int[] dir : dirs) {

                    int newX = temp[0] + dir[0];
                    int newY = temp[1] + dir[1];

                    if (newX < 0 || newY < 0 || newX >= grid.length || newY >= grid[0].length || visited[newX][newY] || grid[newX][newY] == 1) {
                        continue;
                    }

                    visited[newX][newY] = true;

                    queue.offer(new int[]{newX, newY});
                }
            }

            steps++;
        }

        return -1;
    }
}
