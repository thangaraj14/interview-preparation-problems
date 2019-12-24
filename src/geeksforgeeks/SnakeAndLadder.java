package geeksforgeeks;

import java.util.Queue;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 */
public class SnakeAndLadder {

    static class QEntry {
        int vertex;
        int noOfMoves;
    }

     int getMinDiceThrows(int[][] board, int n) { 
        //int n = board.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1];
        for (int move = 0; !queue.isEmpty(); move++) {
            for (int size = queue.size(); size > 0; size--) {
                int num = queue.poll();
                if (visited[num]) continue;
                visited[num] = true;
                if (num == n * n) return move;
                for (int i = 1; i <= 6 && num + i <= n * n; i++) {
                    int next = num + i;
                    int value = getBoardValue(board, next);
                    if (value > 0) next = value;
                    if (!visited[next]) queue.offer(next);
                }
            }
        }
        return -1;
    }

    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int r = (num - 1) / n;
        int x = n - 1 - r;
        int y = r % 2 == 0 ? num - 1 - r * n : n + r * n - num;
        return board[x][y];
    }

    // public static void main(String[] args) {

    //     int N = 30;
    //     int moves[] = new int[N];
    //     for (int i = 0; i < N; i++)
    //         moves[i] = -1;

    //     // Ladders
    //     moves[2] = 21;
    //     moves[4] = 7;
    //     moves[10] = 25;
    //     moves[19] = 28;

    //     // Snakes
    //     moves[3] = 1;
    //     moves[23] = 8;
    //     moves[16] = 3;
    //     moves[18] = 6;

    //     System.out.println("Min Dice throws required is " + getMinDiceThrows(moves, N));
    // }
}