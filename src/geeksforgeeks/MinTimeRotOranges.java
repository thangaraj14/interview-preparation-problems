package geeksforgeeks;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://github.com/bibhas-abhishek/projects/tree/master/MinTimeRotOranges
 * https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
 */

public class MinTimeRotOranges {

    private class Pair {

        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "" + y;
        }

    }

    private boolean hasFreshOrange(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    return true;
            }
        }
        return false;
    }

    private boolean isDelimiter(Pair p) {
        return p.x == -1 && p.y == -1;
    }

    private boolean isValidFresh(int row, int col, int[][] grid) {
        int maxRow = grid.length;
        int maxCol = grid[0].length;
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol && grid[row][col] == 1;
    }

    private void rotOranges(Queue<Pair> queue, Pair p, int[][] grid) {
        int[] xMoves = {1, -1, 0, 0};
        int[] yMoves = {0, 0, 1, -1};
        for (int k = 0; k < xMoves.length; k++) {
            int x = p.x + xMoves[k];
            int y = p.y + yMoves[k];
            if (isValidFresh(x, y, grid)) {
                grid[x][y] = 2;
                queue.add(new Pair(x, y));
            }
        }
    }

    public int findMinTime(int[][] grid) {
        int result = 0;
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2)
                    queue.add(new Pair(i, j));
            }
        }

        if (queue.isEmpty())
            return -1;

        queue.add(new Pair(-1, -1));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (!isDelimiter(p))
                rotOranges(queue, p, grid);
            else if (!queue.isEmpty()) {
                queue.add(p); // add back delimiter
                result += 1;
            }
        }

        if (hasFreshOrange(grid))
            return -1;

        return result;
    }

    public static void main(String[] args) {
        int grid[][] = {{2, 1, 0, 1, 1}, {1, 0, 2, 1, 1}, {1, 1, 1, 1, 1}};
        System.out.println(new MinTimeRotOranges().findMinTime(grid));
    }

}