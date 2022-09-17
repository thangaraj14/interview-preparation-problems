package graph.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://leetcode.com/problems/path-with-minimum-effort/
 * <p>
 * https://leetcode.com/problems/swim-in-rising-water/
 * <p>
 * both are almost similar
 */
public class MinPathHavingMaxDifference {
    public int minimumEffortPath(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        int[][] dist = new int[m][n];

        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        queue.offer(new int[]{0, 0, 0});
        dist[0][0] = 0;
        Set<String> set = new HashSet<>();
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int result = 0;
        while (!queue.isEmpty()) {

            int[] node = queue.poll();

            int x = node[0];
            int y = node[1];
            int distance = node[2];

            if (distance > dist[x][y]) continue;

            result = Math.max(result, distance);
            if (x == m - 1 && y == n - 1) return result;

            if (!set.add(x + "-" + y)) continue;
            for (int[] dir : dirs) {

                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX < 0 || newY < 0 || newX >= m || newY >= n) continue;

                int diff = Math.abs(heights[x][y] - heights[newX][newY]);
                dist[newX][newY] = diff;
                queue.offer(new int[]{newX, newY, diff});
            }
        }

        return -1;
    }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[n][n];
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        visited[0][0] = true;
        pq.offer(new int[]{0, 0, grid[0][0]});
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int i = info[0], j = info[1], max = info[2];
            for (int[] dir : dirs) {
                int newI = dir[0] + i, newJ = dir[1] + j;
                if (newI < 0 || newI >= n || newJ < 0 || newJ >= n) continue;
                if (!visited[newI][newJ]) {
                    visited[newI][newJ] = true;
                    int newmax = Math.max(max, grid[newI][newJ]);
                    if (newI == n - 1 && newJ == n - 1) return newmax;
                    pq.offer(new int[]{newI, newJ, newmax});
                }
            }
        }

        return 0;
    }
}
