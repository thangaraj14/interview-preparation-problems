package practiceproblems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/356150/amazon-oa-2019-shortest-path-from-multiple-sources
 * */

public class TreasureIslandII {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static int minDist(char[][] grid) {
        Queue<Point> q = collectSources(grid);
        for (int dist = 0; !q.isEmpty(); dist++) {
            for (int sz = q.size(); sz > 0; sz--) {
                Point p = q.poll();

                if (grid[p.r][p.c] == 'X')
                    return dist;
                grid[p.r][p.c] = 'D'; // mark as visited

                for (int[] dir : DIRS) {
                    int r = p.r + dir[0];
                    int c = p.c + dir[1];
                    if (isSafe(grid, r, c)) {
                        q.add(new Point(r, c));
                    }
                }
            }
        }
        return -1;
    }

    private static Queue<Point> collectSources(char[][] grid) {
        Queue<Point> sources = new ArrayDeque<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 'S') {
                    sources.add(new Point(r, c));
                }
            }
        }
        return sources;
    }

    private static boolean isSafe(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != 'D';
    }

    private static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public String toString() {
            return r + "-" + c;
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'S', 'O', 'O', 'S', 'S'}, 
            {'D', 'O', 'D', 'O', 'D'}, 
            {'O', 'O', 'O', 'O', 'X'},
            {'X', 'D', 'D', 'O', 'O'}, 
            {'X', 'D', 'D', 'D', 'O'}};
        test(minDist(grid), 3);
    }

    private static void test(int actual, int expected) {
        if (actual == expected) {
            System.out.println("PASSED!");
        } else {
            System.out.println(String.format("FAILED! Expected: %d, but got: %d", expected, actual));
        }
    }
}