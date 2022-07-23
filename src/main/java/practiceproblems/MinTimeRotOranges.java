package practiceproblems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://github.com/bibhas-abhishek/projects/tree/master/MinTimeRotOranges
 * https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
 */

public class MinTimeRotOranges {

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int countFresh = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    countFresh++;
                }
            }
        }
        if (countFresh == 0) return 0;
        int result = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] temp = queue.poll();

                for (int[] dir : dirs) {
                    int newX = temp[0] + dir[0];
                    int newY = temp[1] + dir[1];

                    if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length || grid[newX][newY] == 0 || grid[newX][newY] == 2) {
                        continue;
                    }

                    queue.offer(new int[]{newX, newY});
                    grid[newX][newY] = 2;
                    countFresh--;
                }
            }

            result++;
        }


        return countFresh != 0 ? -1 : result;
    }

    public static void main(String[] args) {
        int grid[][] = {{2, 1, 0, 1, 1}, {1, 0, 2, 1, 1}, {1, 1, 1, 1, 1}};
        System.out.println(new MinTimeRotOranges().orangesRotting(grid));
    }

}