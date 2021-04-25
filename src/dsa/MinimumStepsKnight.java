package dsa;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/minimum-steps-reach-target-knight/
 * <p>
 * https://www.techiedelight.com/chess-knight-problem-find-shortest-path-source-destination/
 */
class MinimumStepsKnight {

    static class Cell {
        int x;
        int y;
        int steps;

        public Cell(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }

    static boolean isInside(int x, int y, int N) {
        if (x >= 1 && x <= N && y >= 1 && y <= N) {
            return true;
        }
        return false;
    }

    static int minStepToReachTarget(int knightPos[], int targetPos[], int N) {
        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

        Queue<Cell> q = new LinkedList<>();

        q.add(new Cell(knightPos[0], knightPos[1], 0));

        Cell cell;
        int x, y;
        boolean visit[][] = new boolean[N + 1][N + 1];

        visit[knightPos[0]][knightPos[1]] = true;

        while (!q.isEmpty()) {
            cell = q.remove();

            if (cell.x == targetPos[0] && cell.y == targetPos[1]) {
                return cell.steps;
            }

            for (int i = 0; i < 8; i++) {
                x = cell.x + dx[i];
                y = cell.y + dy[i];

                if (isInside(x, y, N) && !visit[x][y]) {
                    visit[x][y] = true;
                    q.add(new Cell(x, y, cell.steps + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int N = 30;
        int[] knightPos = { 1, 1 };
        int[] targetPos = { 30, 30 };
        System.out.println(minStepToReachTarget(knightPos, targetPos, N));
    }
}