package practiceproblems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * tricky bfs, consider as 1-D plane
 * https://leetcode.com/problems/snakes-and-ladders/
 */
public class SnakeAndLadder {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1];
        for (int move = 0; !queue.isEmpty(); move++) {

            for (int size = queue.size(); size > 0; size--) {
                int currPos = queue.poll();
                if (visited[currPos]) continue;
                visited[currPos] = true;
                if (currPos == n * n) return move;
                for (int i = 1; i <= 6 && currPos + i <= n * n; i++) {
                    int next = currPos + i;
                    int value = getBoardValue(board, next);
                    if (value > 0) next = value;
                    if (!visited[next]) queue.offer(next);
                }
            }
        }
        return -1;
    }

    /**
     * Let nextPos=1
     * 1/6=0
     * 6-0-1=5 <-- this is the row we are at
     * This will work for 1,2,3,4,5 but not for 6
     * 6/6=1
     * 6-1-1=4, but we are still at row 5
     * So the way to tackle this is by using this:
     * int oldRow=(next_step-1)/n;
     * row=n-1-oldRow;
     * This will make 1,2,3,4,5,6 all in the same row.
     * column is easy all u have to do is int oldCol=(next_step-1)%n;
     * 2. For flipping direction  (i.e snake goes upward in zig-zag fashion )all we are going to dow is we have found oldRow in the previous step.
     * so for every odd oldRow we flip the direction and for every even oldRow we maintain our normal direction.
     * <p>
     * if(x%2==1) col =n-1-oldCol;
     */
    private int getBoardValue(int[][] board, int nextPos) {
        int n = board.length;
        int oldRow = (nextPos - 1) / n;
        int row = n - 1 - oldRow;
        int oldCol = (nextPos - 1) % n;
        int col = oldRow % 2 == 0 ? oldCol : n - 1 - oldCol;

        return board[row][col];
    }

    public static void main(String[] args) {

        int[][] board = {{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 15, -1, -1, -1, -1}};

        System.out.println("Min Dice throws required is " + new SnakeAndLadder().snakesAndLadders(board));

    }
}