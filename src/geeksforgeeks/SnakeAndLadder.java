package geeksforgeeks;

import java.util.Queue;
import java.util.*;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 */
public class SnakeAndLadder {
    class BoardCells {
        int pos;
        int steps;

        public BoardCells(int pos, int steps) {
            this.pos = pos;
            this.steps = steps;
        }
    }

    private int n;

    public int snakesAndLadders(int[][] board) {
        n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<BoardCells> queue = new LinkedList<>();
        queue.offer(new BoardCells(1, 1));
        visited[1] = true;
       
        while (!queue.isEmpty()) {
            BoardCells cur = queue.poll();
            for (int i = 1; i <= 6; i++) {
                int next = cur.pos + i;
                int[] pos = numToPos(next);
                if (board[pos[0]][pos[1]] > 0) {
                    next = board[pos[0]][pos[1]];
                }
                if (next == n * n) {
                    return cur.steps;
                }
                if (!visited[next]) {
                    queue.offer(new BoardCells(next, cur.steps + 1));
                    visited[next] = true;
                }
            }

        }
        return queue.peek().steps;
    }

    private int[] numToPos(int target) {
        int row = (target - 1) / n, col = (target - 1) % n;
        int x = n - 1 - row, y = row % 2 == 0 ? col : n - 1 - col;
        return new int[] { x, y };
    }

    private int posToNum(int[] position) {
        int row = (n - 1 - position[0]);
        int y = row % 2 == 0 ? position[1] + 1 : n - position[1];
        return row * n + y;
    }

    public static void main(String[] args) {

        int[][] board = { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
                { -1, 35, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, 15, -1, -1, -1, -1 } };

        System.out.println("Min Dice throws required is " + new SnakeAndLadder().snakesAndLadders(board));
    
    }
}