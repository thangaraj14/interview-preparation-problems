package geeksforgeeks;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/discuss/interview-question/347457/Amazon-or-OA-2019-or-Treasure-Island
 */
public class TreasureIsland {
    private static final int[][] DIRS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public static int minSteps(char[][] grid) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0, 0));
        grid[0][0] = 'D';
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int[] dir : DIRS) {
                int r = p.r + dir[0];
                int c = p.c + dir[1];

                if (isSafe(grid, r, c)) {
                    if (grid[r][c] == 'X') {
                        return p.steps + 1;
                    }
                    grid[r][c] = 'D';
                    q.add(new Point(r, c, p.steps + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isSafe(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != 'D';
    }

    private static class Point {
        int r;
        int c;
        int steps;

        Point(int r, int c, int steps) {
            this.r = r;
            this.c = c;
            this.steps = steps;
        }

        public String toString() {
            return this.r + "-" + this.c;
        }
    }

    public static void main(String[] args) {

        char[][] grid = { { 'O', 'O', 'O', 'O' }, { 'D', 'O', 'D', 'O' }, { 'O', 'O', 'O', 'O' },
                { 'X', 'D', 'D', 'O' } };

        System.out.println(minSteps(grid));
    }
}