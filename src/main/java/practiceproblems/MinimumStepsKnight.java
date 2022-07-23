package practiceproblems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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

    // Utility method returns true if (x, y) lies
    // inside Board
    static boolean isInside(int x, int y, int N) {
        if (x >= 1 && x <= N && y >= 1 && y <= N) {
            return true;
        }
        return false;
    }

    // Method returns minimum step
    // to reach target position
    static int minStepToReachTarget(int knightPos[], int targetPos[], int N) {
        // x and y direction, where a knight can move
        int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
        int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};

        // queue for storing states of knight in board
        Queue<Cell> q = new LinkedList<>();

        // push starting position of knight with 0 distance
        q.add(new Cell(knightPos[0], knightPos[1], 0));

        Cell cell;
        int x, y;
        boolean visit[][] = new boolean[N + 1][N + 1];

        // make all cell unvisited
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                visit[i][j] = false;

        // visit starting state
        visit[knightPos[0]][knightPos[1]] = true;

        // loop untill we have one element in queue
        while (!q.isEmpty()) {
            cell = q.remove();

            // if current cell is equal to target cell,
            // return its distance
            if (cell.x == targetPos[0] && cell.y == targetPos[1]) {
                return cell.steps;
            }

            // loop for all reachable states
            for (int i = 0; i < 8; i++) {
                x = cell.x + dx[i];
                y = cell.y + dy[i];

                // If reachable state is not yet visited and
                // inside board, push that state into queue
                if (isInside(x, y, N) && !visit[x][y]) {
                    visit[x][y] = true;
                    q.add(new Cell(x, y, cell.steps + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);   // If we can reach x,y in one quadrant then we can do it for all others in the same number of moves too.
        if (x == 1 && y == 1)
            return 2;     // Special case for 1,1 because there is a more efficient way to reach it if we allow negative positions
        int[][] dirs = new int[][]{{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        Deque<int[]> queue = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        queue.addLast(new int[]{0, 0});
        seen.add("0,0");
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();    // Number of unique positions reachable with exactly result moves.
            for (int i = 0; i < size; ++i) {
                int[] currPos = queue.removeFirst();
                if (currPos[0] == x && currPos[1] == y) return result;
                for (int[] currDir : dirs) {
                    int newX = currPos[0] + currDir[0];
                    int newY = currPos[1] + currDir[1];
                    if (newX >= 0 && newY >= 0 && !seen.contains(newX + "," + newY)) {
                        seen.add(newX + "," + newY);
                        queue.addLast(new int[]{newX, newY});
                    }
                }
            }
            result++;
        }
        return -1;    // Can't be reached on an infinite chess board.
    }

    public static void main(String[] args) {
        int N = 30;
        int[] knightPos = {1, 1};
        int[] targetPos = {30, 30};
        System.out.println(minStepToReachTarget(knightPos, targetPos, N));
    }
}